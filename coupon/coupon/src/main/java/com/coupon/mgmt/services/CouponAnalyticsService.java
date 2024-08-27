package com.coupon.mgmt.services;

import com.coupon.mgmt.entity.Coupon;

import java.util.List;

public interface CouponAnalyticsService {

    public List<Coupon> getTopPerformingCoupons(int limit);
    public List<Coupon> getLeastUsedCoupons(int limit);
    public void generatePerformanceReport();
    public void generatePerformanceReportUses();
}
