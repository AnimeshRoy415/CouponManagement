package com.coupon.mgmt.services;

import com.coupon.mgmt.dtos.CouponUsageHistResponseDto;
import com.coupon.mgmt.entity.CouponUsageHistory;
import com.coupon.mgmt.exception.CouponUsageHistoryNotFound;
import com.coupon.mgmt.repository.CouponUsageHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponHistoryServiceImpl implements CouponHistoryService{


    @Autowired
    CouponUsageHistoryRepository couponUsageHistoryRepository;


    @Override
    public List<CouponUsageHistResponseDto> getAllCouponUsageHistory() {
        List<CouponUsageHistory> usageHistoryList= couponUsageHistoryRepository.findAll();
        if (usageHistoryList.isEmpty()){
            throw new CouponUsageHistoryNotFound("CouponUsageHistory not Exist!!!!");
        }
        return usageHistoryList
                .stream().
                map(couponUsageHistory -> CouponUsageHistory.toDto(couponUsageHistory))
                .collect(Collectors.toList());
    }

    @Override
    public CouponUsageHistResponseDto getCouponUsageHistoryById(Long id) {

        Optional<CouponUsageHistory> optionalCouponUsageHistory= couponUsageHistoryRepository.findById(id);
        if (optionalCouponUsageHistory.isEmpty()) {
            throw new CouponUsageHistoryNotFound("CouponUsageHistory not Exist!!!!");
        }
        return CouponUsageHistory.toDto(optionalCouponUsageHistory.get());
    }

    @Override
    public List<CouponUsageHistResponseDto> getCouponUsageHistoryListById(List<Long> ids) {

        List<CouponUsageHistory> usageHistoryList= couponUsageHistoryRepository.findAllById(ids);
        if (usageHistoryList.isEmpty()){
            throw new CouponUsageHistoryNotFound("CouponUsageHistory not Exist!!!!");
        }

        return usageHistoryList
                .stream()
                .map(couponUsageHistory -> CouponUsageHistory.toDto(couponUsageHistory))
                .collect(Collectors.toList());
    }
}
