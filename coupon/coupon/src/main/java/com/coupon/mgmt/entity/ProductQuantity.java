package com.coupon.mgmt.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantity {
    private Integer productId;
    private Integer quantity;
    private Double price;
}