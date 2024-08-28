package com.coupon.mgmt.controller;


import com.coupon.mgmt.ApiResponseModel.BaseApiDelegate;
import com.coupon.mgmt.services.CouponAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class CouponAnalyticsController extends BaseApiDelegate {


    @Autowired
    private CouponAnalyticsService couponAnalyticsService;


    @GetMapping("/top-performing-coupons")
    public ResponseEntity getTopPerformingCoupons(@RequestParam(defaultValue = "10") int limit) {
        return formApiResponse(couponAnalyticsService.getTopPerformingCoupons(limit));
    }

    @GetMapping("/least-used-coupons")
    public ResponseEntity getLeastUsedCoupons(@RequestParam(defaultValue = "10") int limit) {
        return formApiResponse(couponAnalyticsService.getLeastUsedCoupons(limit));
    }

    @GetMapping("/coupons-expiring-soon")
    public ResponseEntity getCouponsExpiringSoon(@RequestParam(defaultValue = "7") int days) {
        return formApiResponse(couponAnalyticsService.getCouponsExpiringSoon(days));
    }
}
