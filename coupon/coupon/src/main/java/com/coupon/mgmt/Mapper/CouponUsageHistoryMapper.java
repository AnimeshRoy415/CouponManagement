package com.coupon.mgmt.mapper;

import com.coupon.mgmt.Utils.IBasisJsonUtils;
import com.coupon.mgmt.dtos.CouponUsageHistResponseDto;
import com.coupon.mgmt.entity.Cart;
import com.coupon.mgmt.entity.CouponUsageHistory;
import com.fasterxml.jackson.core.type.TypeReference;

public class CouponUsageHistoryMapper {

    public static CouponUsageHistResponseDto toDto(CouponUsageHistory usageHistory) {
        CouponUsageHistResponseDto dto = new CouponUsageHistResponseDto();

        dto.setId(usageHistory.getId());
        dto.setCoupon(usageHistory.getCoupon());
        dto.setCart(IBasisJsonUtils.deserializeClass(usageHistory.getCart(), new TypeReference<Cart>() {
        })); // Assuming cart is stored as JSON string
        dto.setUsedAt(usageHistory.getUsedAt());
        dto.setDiscountApplied(usageHistory.getDiscountApplied());

        return dto;
    }

}
