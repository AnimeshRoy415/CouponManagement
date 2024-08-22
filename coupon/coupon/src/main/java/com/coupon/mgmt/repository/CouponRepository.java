package com.coupon.mgmt.repository;

import com.coupon.mgmt.entity.Coupon;
import com.coupon.mgmt.entity.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByType(CouponType type);


    @Query("SELECT c FROM Coupon c WHERE c.expirationDate >= :currentTime OR c.expirationDate IS NULL")
    List<Coupon> findAllActiveCoupon(Long currentTime);

    @Query("SELECT c FROM Coupon c WHERE c.expirationDate < :currentTime OR c.expirationDate IS NULL")
    List<Coupon> findAllDeActiveCoupon(Long currentTime);


}
