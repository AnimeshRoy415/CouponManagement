package com.coupon.mgmt.exception;

public class InvalidInput extends RuntimeException{

    public InvalidInput() {
    }

    public InvalidInput(String message) {
        super(message);
    }
}
