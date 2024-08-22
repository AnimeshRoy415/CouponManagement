package com.coupon.mgmt.entity;

public enum CouponType {
    CART_WISE("cart-wise"),
    PRODUCT_WISE("product-wise"),
    BX_GY("bxgy");
    private final String value;

    CouponType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
