package com.coupon.mgmt.exception;

public class InvalidDateRangeException extends CouponNotFound{

    public InvalidDateRangeException(String message) {
        super(message);
    }
}
