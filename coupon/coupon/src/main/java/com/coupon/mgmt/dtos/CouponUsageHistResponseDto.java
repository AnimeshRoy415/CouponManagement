package com.coupon.mgmt.dtos;

import com.coupon.mgmt.entity.Cart;
import com.coupon.mgmt.entity.Coupon;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;


@Data
public class CouponUsageHistResponseDto {

    private Long id;
    private Coupon coupon;
    private Cart cart;
    private LocalDateTime usedAt; // Using LocalDateTime instead of Timestamp
    private Double discountApplied;
}
