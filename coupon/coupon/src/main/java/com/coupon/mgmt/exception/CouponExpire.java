package com.coupon.mgmt.exception;

public class CouponExpire extends RuntimeException{
    public CouponExpire() {
    }

    public CouponExpire(String message) {
        super(message);
    }
}
