package com.coupon.mgmt.services;

import com.coupon.mgmt.dtos.CouponDto;
import com.coupon.mgmt.dtos.CouponResponseDto;
import com.coupon.mgmt.entity.Cart;
import com.coupon.mgmt.entity.CartItem;
import com.coupon.mgmt.entity.Coupon;
import com.coupon.mgmt.entity.ProductQuantity;
import com.coupon.mgmt.exception.ConditionNotMeet;
import com.coupon.mgmt.exception.CouponExpire;
import com.coupon.mgmt.exception.CouponNotFound;
import com.coupon.mgmt.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {


    @Autowired
    private CouponRepository couponRepository;


    @Override
    public Coupon createCoupon(CouponDto couponDto) {

        Coupon coupon= CouponDto.toEntity(couponDto);
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon getCouponById(Long id) {

        Optional<Coupon> optionalCoupon= couponRepository.findById(id);
        if (Objects.isNull(optionalCoupon)){
            throw new CouponNotFound("Coupon not found");
        }
        return optionalCoupon.get();
    }

    @Override
    public List<Coupon> getAllCoupons(Boolean isActive) {
        if (Objects.isNull(isActive)) {
            return couponRepository.findAll();
        } else if (isActive) {
            return couponRepository.findAllActiveCoupon(System.currentTimeMillis());
        }
        else {
            return couponRepository.findAllDeActiveCoupon(System.currentTimeMillis());
        }
    }

    @Override
    public Coupon updateCoupon(Long id, Coupon updatedCoupon) {
        return couponRepository.findById(id)
                .map(coupon -> {
                    coupon.setType(updatedCoupon.getType());
                    coupon.setDetails(updatedCoupon.getDetails());
//                    coupon.setExpirationDate(updatedCoupon.getExpirationDate());
                    coupon.setRepetitionLimit(updatedCoupon.getRepetitionLimit());
                    return couponRepository.save(coupon);
                })
                .orElseThrow(() -> new CouponNotFound("Coupon not found"));
    }

    @Override
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public List<CouponResponseDto> findApplicableCoupons(Cart cart) {
        List<CouponResponseDto> applicableCoupons = new ArrayList<>();
        List<Coupon> allCoupons = couponRepository.findAllActiveCoupon(System.currentTimeMillis());

        for (Coupon coupon : allCoupons) {

            CouponResponseDto couponResponseDto= new CouponResponseDto();

            double discount = 0;

            switch (coupon.getType()) {
                case BX_GY:
                    discount = calculateBxGyDiscount(coupon, cart);
                    couponResponseDto.setId(coupon.getId());
                    if (discount > 0) {
                        couponResponseDto.setDiscount(discount);
                    }
                    couponResponseDto.setType(coupon.getType());
                    break;
                case CART_WISE:
                    discount = calculateCartWiseDiscount(coupon, cart);
                    couponResponseDto.setId(coupon.getId());
                    if (discount > 0) {
                        couponResponseDto.setDiscount(discount);
                    }
                    couponResponseDto.setType(coupon.getType());
                    break;
                case PRODUCT_WISE:
                    discount = calculateProductWiseDiscount(coupon, cart);
                    couponResponseDto.setId(coupon.getId());
                    if (discount > 0){
                    couponResponseDto.setDiscount(discount);
                    }

                    couponResponseDto.setType(coupon.getType());
                    break;
            }

             // Assume Coupon has a field `discountAmount` to store the calculated discount
            if(Objects.nonNull(couponResponseDto.getDiscount()) && couponResponseDto.getDiscount()>0)
                applicableCoupons.add(couponResponseDto);

        }

        return applicableCoupons;
    }

    @Override
    public Cart applyCoupon(Long couponId, Cart cart) {

        Optional<Coupon> optCoupon= couponRepository.findById(couponId);
        if (optCoupon.isEmpty()){
            throw new CouponNotFound("Coupon not found!!!");
        }
        Coupon coupon= optCoupon.get();

        if (coupon.getExpirationDate()<System.currentTimeMillis()){
            throw new CouponExpire("Coupon Expire!!!");
        }

        double discount = 0;

        switch (coupon.getType()) {
            case BX_GY:
                discount = calculateBxGyDiscount(coupon, cart);

                break;
            case CART_WISE:
                discount = calculateCartWiseDiscount(coupon, cart);

                break;
            case PRODUCT_WISE:
                discount = calculateProductWiseDiscount(coupon, cart);
                break;
        }

        return cart;
    }

    private double calculateBxGyDiscount(Coupon coupon, Cart cart) {

        if (coupon.getExpirationDate()<System.currentTimeMillis()){
            throw new CouponExpire("Coupon Expire!!!");
        }
        double totalDiscount = 0;
        List<ProductQuantity> buyProducts = coupon.getDetails().getBuyProducts();
        List<ProductQuantity> getProducts = coupon.getDetails().getGetProducts();

        int repetitionLimit = coupon.getRepetitionLimit();
        int totalApplicableRepetitions = Integer.MAX_VALUE; // Initialize with a high value

        // Calculate the maximum number of times the coupon can be applied based on buy products
        for (ProductQuantity buyProduct : buyProducts) {
            CartItem cartItem = cart.getItems().stream()
                    .filter(item ->item.getProductId().equals(buyProduct.getProductId()))
                    .findFirst()
                    .orElse(null);

            if (cartItem != null) {
                int timesApplicable = cartItem.getQuantity() / buyProduct.getQuantity();
                totalApplicableRepetitions = Math.min(totalApplicableRepetitions, timesApplicable);
            }
//            else {
//                // If any required buy product is missing, coupon cannot be applied
//                return 0;
//            }
        }

        // Apply repetition limit
        totalApplicableRepetitions = Math.min(totalApplicableRepetitions, repetitionLimit);

        // Calculate discount for each get product
        for (ProductQuantity getProduct : getProducts) {
            int finalTotalApplicableRepetitions = totalApplicableRepetitions;
            CartItem freeItem = cart.getItems().stream()
                    .filter(item -> {
                        if (item.getProductId().equals(getProduct.getProductId())){
                            item.setTotalDiscount(finalTotalApplicableRepetitions * getProduct.getQuantity() * item.getPrice());
                        }

                        return item.getProductId().equals(getProduct.getProductId());
                    })
                    .findFirst()
                    .orElse(null);

            if (freeItem != null) {
                totalDiscount += totalApplicableRepetitions * getProduct.getQuantity() * freeItem.getPrice().doubleValue();
            }
            else {

            }

        }

        double totalCartPrice = cart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();

        cart.setTotalPrice(totalCartPrice);
        cart.setTotalDiscount(totalDiscount);
        cart.setFinalPrice(totalCartPrice- totalDiscount);
        return totalDiscount;
    }


    private double calculateCartWiseDiscount(Coupon coupon, Cart cart) {

        if (coupon.getExpirationDate()<System.currentTimeMillis()){
            throw new CouponExpire("Coupon Expire!!!");
        }
        double totalPrice = 0;
        for (CartItem item : cart.getItems()) {

            totalPrice += item.getQuantity() * item.getPrice();
        }
        if (totalPrice < coupon.getDetails().getThreshold()){
            throw new ConditionNotMeet("Condition not meet!!!");
        }

        if (totalPrice >= coupon.getDetails().getThreshold()) {
            for (CartItem item : cart.getItems()) {

                double discount = item.getQuantity() * item.getPrice() * (coupon.getDetails().getDiscountPercentage() / 100);
                item.setTotalDiscount(discount);
            }
        }
        if (totalPrice >= coupon.getDetails().getThreshold()) {
            double totalDiscount= totalPrice * (coupon.getDetails().getDiscountPercentage() / 100);
            cart.setTotalPrice(totalPrice);
            cart.setTotalDiscount(totalDiscount);
            cart.setFinalPrice(totalPrice-totalDiscount);
            return totalDiscount;
        }
        return 0;
    }

    private double calculateProductWiseDiscount(Coupon coupon, Cart cart) {
        double discount = 0;

        if (coupon.getExpirationDate()<System.currentTimeMillis()){
            throw new CouponExpire("Coupon Expire!!!");
        }

        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(coupon.getDetails().getProductId())) {
                discount += item.getPrice() * item.getQuantity() * (coupon.getDetails().getProductDiscountPercentage() / 100);
                item.setTotalDiscount(discount);
            }
        }
        if (discount<= 0){
            throw  new ConditionNotMeet("Condition not meet");
        }

        double totalCartPrice = cart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
        cart.setTotalPrice(totalCartPrice);
        cart.setTotalDiscount(discount);
        cart.setFinalPrice(totalCartPrice-discount);
        return discount;
    }
}
