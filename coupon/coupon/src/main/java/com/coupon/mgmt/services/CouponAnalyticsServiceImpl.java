package com.coupon.mgmt.services;

import com.coupon.mgmt.entity.Coupon;
import com.coupon.mgmt.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CouponAnalyticsServiceImpl implements  CouponAnalyticsService{


    @Autowired
    CouponRepository couponRepository;
    @Override
    public List<Coupon> getTopPerformingCoupons(int limit) {
        return couponRepository.findAll().stream()
                .sorted((c1, c2) -> Double.compare(c2.getTotalRevenueGenerated(), c1.getTotalRevenueGenerated()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Coupon> getLeastUsedCoupons(int limit) {
        return couponRepository.findAll().stream()
                .sorted((c1, c2) -> Integer.compare(c1.getRedemptionCount(), c2.getRedemptionCount()))
                .limit(limit)
                .collect(Collectors.toList());

    }

    @Override
    public void generatePerformanceReport() {

    }

    @Override
    public void generatePerformanceReportUses() {

    }
}
