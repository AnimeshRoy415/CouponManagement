package com.coupon.mgmt.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Embeddable
@Data
public class DiscountDetails {
    private Double threshold; // Cart-wise discount details
    private Double discountPercentage; // Cart-wise discount percentage

    private Integer productId; // Product-wise discount details
    private Double productDiscountPercentage; // Product-wise discount percentage

    @ElementCollection
    private List<ProductQuantity> buyProducts; // BxGy buy products

    @ElementCollection
    private List<ProductQuantity> getProducts; // BxGy get products
}
