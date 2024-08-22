package com.coupon.mgmt.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private List<CartItem> items;
    private Double totalPrice;
    private Double totalDiscount;
    private Double finalPrice;

    public void calculateTotals() {
        totalPrice = items.stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
        finalPrice = totalPrice - totalDiscount;
    }
}