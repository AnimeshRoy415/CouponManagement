package com.coupon.mgmt.entity;

import com.coupon.mgmt.dtos.CouponDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Embedded
    private DiscountDetails details;

    private Long expirationDate;

    private int repetitionLimit; // For BxGy type

    private int redemptionCount = 0;
    private double totalRevenueGenerated = 0.0;
//    public boolean isValid() {
//        return expirationDate == null || new Date().before(expirationDate);
//    }

    public void incrementRedemptionCount() {
        this.redemptionCount++;
    }

    public void addToRevenue(double revenue) {
        this.totalRevenueGenerated += revenue;
    }


}