package com.coupon.mgmt.controller;

import com.coupon.mgmt.ApiResponseModel.BaseApiDelegate;
import com.coupon.mgmt.CouponRequestModule.ApplyCouponsRequest;
import com.coupon.mgmt.dtos.CouponDto;
import com.coupon.mgmt.dtos.CouponResponseDto;
import com.coupon.mgmt.entity.Cart;
import com.coupon.mgmt.entity.Coupon;
import com.coupon.mgmt.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController extends BaseApiDelegate{
    @Autowired
    private CouponService couponService;

    @PostMapping
    public ResponseEntity createCoupon(@RequestBody CouponDto couponDto) {
        return formApiResponse(couponService.createCoupon(couponDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity getCouponById(@PathVariable Long id) {
        return formApiResponse(couponService.getCouponById(id));
    }

    @GetMapping
    public ResponseEntity getAllCoupons(@RequestParam(required = false) Boolean isActive) {
        return formApiResponse(couponService.getAllCoupons(isActive));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
        return formApiResponse(couponService.updateCoupon(id, coupon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/applicable-coupons")
    public ResponseEntity getApplicableCoupons(@RequestBody Cart cart) {
        System.out.println(cart);
        return formApiResponse(couponService.findApplicableCoupons(cart));
    }

    @PostMapping("/apply-coupons")
    public ResponseEntity<Cart> applyMultipleCoupons(@RequestBody ApplyCouponsRequest request) {
        Cart updatedCart = couponService.applyMultipleCoupons(request.getCouponIds(), request.getCart());
        return ResponseEntity.ok(updatedCart);
    }
}
