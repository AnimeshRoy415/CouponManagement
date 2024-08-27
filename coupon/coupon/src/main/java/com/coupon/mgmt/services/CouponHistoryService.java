package com.coupon.mgmt.services;

import com.coupon.mgmt.dtos.CouponUsageHistResponseDto;
import com.coupon.mgmt.entity.CouponUsageHistory;

import java.util.List;

public interface CouponHistoryService {
    List<CouponUsageHistResponseDto> getAllCouponUsageHistory();

    CouponUsageHistResponseDto getCouponUsageHistoryById(Long id);

    List<CouponUsageHistResponseDto> getCouponUsageHistoryListById(List<Long> ids);

}
