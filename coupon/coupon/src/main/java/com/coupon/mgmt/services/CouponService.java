package com.coupon.mgmt.services;

import com.coupon.mgmt.dtos.CouponDto;
import com.coupon.mgmt.dtos.CouponResponseDto;
import com.coupon.mgmt.entity.Cart;
import com.coupon.mgmt.entity.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    Coupon createCoupon(CouponDto coupon);
    Coupon getCouponById(Long id);
    List<Coupon> getAllCoupons(Boolean isActive);
    Coupon updateCoupon(Long id, Coupon coupon);
    void deleteCoupon(Long id);
    List<CouponResponseDto> findApplicableCoupons(Cart cart);
    Cart applyCoupon(Long couponId, Cart cart);
}
