package com.coupon.mgmt.entity;

import com.coupon.mgmt.Utils.JsonUtils;
import com.coupon.mgmt.dtos.CouponUsageHistResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponUsageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Coupon.class, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "JSON")
    private String cart;

    @Column(nullable = false)
    private LocalDateTime usedAt; // Using LocalDateTime instead of Timestamp

    @Column(nullable = false)
    private Double discountApplied;
    // Additional fields if needed


    public static CouponUsageHistResponseDto toDto(CouponUsageHistory usageHistory) {
        CouponUsageHistResponseDto dto = new CouponUsageHistResponseDto();

        dto.setId(usageHistory.getId());
        dto.setCoupon(usageHistory.getCoupon());
        dto.setCart(JsonUtils.deserializeClass(usageHistory.getCart(), new TypeReference<Cart>() {
        })); // Assuming cart is stored as JSON string
        dto.setUsedAt(usageHistory.getUsedAt());
        dto.setDiscountApplied(usageHistory.getDiscountApplied());

        return dto;
    }
}
