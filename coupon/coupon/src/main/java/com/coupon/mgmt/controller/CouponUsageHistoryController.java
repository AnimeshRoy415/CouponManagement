package com.coupon.mgmt.controller;

import com.coupon.mgmt.ApiResponseModel.BaseApiDelegate;
import com.coupon.mgmt.dtos.CouponUsageHistResponseDto;
import com.coupon.mgmt.entity.CouponUsageHistory;
import com.coupon.mgmt.services.CouponHistoryService;
import com.coupon.mgmt.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon-usage-history")
public class CouponUsageHistoryController extends BaseApiDelegate {

    @Autowired
    private CouponHistoryService couponHistoryService;

    @GetMapping("all")
    public ResponseEntity getAllUsageHistory() {
        List<CouponUsageHistResponseDto> usageHistories = couponHistoryService.getAllCouponUsageHistory();
        return formApiResponse(usageHistories);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUsageHistoryById(@PathVariable Long id) {
        CouponUsageHistResponseDto usageHistory = couponHistoryService.getCouponUsageHistoryById(id);
        return formApiResponse(usageHistory);
    }

    @GetMapping
    public ResponseEntity getUsageHistoryListById(@RequestParam List<Long> ids) {
        List<CouponUsageHistResponseDto> usageHistoryList = couponHistoryService.getCouponUsageHistoryListById(ids);
        return formApiResponse(usageHistoryList);
    }
}
