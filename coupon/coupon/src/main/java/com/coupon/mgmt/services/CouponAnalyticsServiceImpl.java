package com.coupon.mgmt.services;

import com.coupon.mgmt.entity.Coupon;
import com.coupon.mgmt.exception.CouponNotFound;
import com.coupon.mgmt.exception.InvalidDateRangeException;
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
    public List<Coupon> getCouponsExpiringSoon(int days) {

        if (days < 0) {
            throw new InvalidDateRangeException("Days parameter cannot be negative.");
        }
        long currentTime = System.currentTimeMillis();
        long endTime = currentTime + (days * 24 * 60 * 60 * 1000L);

        List<Coupon> expiringCoupons = couponRepository.findAll().stream()
                .filter(coupon -> coupon.getExpirationDate() > currentTime && coupon.getExpirationDate() <= endTime)
//                .sorted((c1, c2) -> Long.compare(c1.getExpirationDate(), c2.getExpirationDate()))
                .collect(Collectors.toList());

        if (expiringCoupons.isEmpty()) {
            throw new CouponNotFound("No coupons found expiring within the next " + days + " days.");
        }

        return expiringCoupons;
    }


    @Override
    public void generatePerformanceReport() {

    }

    @Override
    public void generatePerformanceReportUses() {

    }
}
