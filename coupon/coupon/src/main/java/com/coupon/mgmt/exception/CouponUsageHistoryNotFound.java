package com.coupon.mgmt.exception;

public class CouponUsageHistoryNotFound extends RuntimeException{
    public CouponUsageHistoryNotFound() {
    }

    public CouponUsageHistoryNotFound(String message) {
        super(message);
    }
}
