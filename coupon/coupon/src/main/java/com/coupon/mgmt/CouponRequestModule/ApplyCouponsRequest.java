package com.coupon.mgmt.CouponRequestModule;

import com.coupon.mgmt.entity.Cart;

import java.util.List;

public class ApplyCouponsRequest {
    private List<Long> couponIds;
    private Cart cart;

    // Getters and Setters
    public List<Long> getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(List<Long> couponIds) {
        this.couponIds = couponIds;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}