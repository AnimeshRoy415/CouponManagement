package com.coupon.mgmt.exception;

public class CouponNotFound extends RuntimeException{

    public CouponNotFound() {
    }

    public CouponNotFound(String message) {
        super(message);
    }
}
