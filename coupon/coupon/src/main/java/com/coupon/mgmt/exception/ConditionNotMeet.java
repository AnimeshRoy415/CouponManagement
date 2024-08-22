package com.coupon.mgmt.exception;

public class ConditionNotMeet extends RuntimeException{

    public ConditionNotMeet() {
    }

    public ConditionNotMeet(String message) {
        super(message);
    }
}
