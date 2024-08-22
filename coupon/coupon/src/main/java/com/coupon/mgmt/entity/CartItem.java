package com.coupon.mgmt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer productId;
    private Integer quantity;
    private Double price;
    private Double totalDiscount;
}
