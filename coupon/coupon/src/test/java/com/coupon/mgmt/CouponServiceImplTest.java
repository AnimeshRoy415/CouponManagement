package com.coupon.mgmt;
import com.coupon.mgmt.dtos.CouponDto;
import com.coupon.mgmt.entity.Cart;
import com.coupon.mgmt.entity.Coupon;
import com.coupon.mgmt.entity.CouponType;
import com.coupon.mgmt.exception.CouponExpire;
import com.coupon.mgmt.exception.CouponNotFound;
import com.coupon.mgmt.repository.CouponRepository;
import com.coupon.mgmt.services.CouponServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceImplTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    private Coupon coupon;
    private CouponDto couponDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        coupon = new Coupon();
        coupon.setId(1L);
        coupon.setType(CouponType.valueOf("CART_WISE"));
        coupon.setExpirationDate(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1));

        couponDto = new CouponDto();
    }

    @Test
    void testCreateCoupon() {
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        Coupon createdCoupon = couponService.createCoupon(couponDto);

        assertNotNull(createdCoupon);
        assertEquals(1L, createdCoupon.getId());
        verify(couponRepository, times(1)).save(any(Coupon.class));
    }

    @Test
    void testGetCouponById_CouponExists() {
        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));

        Coupon fetchedCoupon = couponService.getCouponById(1L);

        assertNotNull(fetchedCoupon);
        assertEquals(1L, fetchedCoupon.getId());
        verify(couponRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCouponById_CouponNotFound() {
        when(couponRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CouponNotFound.class, () -> couponService.getCouponById(1L));
        verify(couponRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);
        when(couponRepository.findAll()).thenReturn(coupons);

        List<Coupon> allCoupons = couponService.getAllCoupons(null);

        assertEquals(1, allCoupons.size());
        verify(couponRepository, times(1)).findAll();
    }

    @Test
    void testApplyCoupon_ValidCoupon() {
        Cart cart = new Cart();
        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));

        Cart updatedCart = couponService.applyCoupon(1L, cart);

        assertNotNull(updatedCart);
        verify(couponRepository, times(1)).findById(1L);
    }

    @Test
    void testApplyCoupon_ExpiredCoupon() {
        coupon.setExpirationDate(System.currentTimeMillis() - 1000); // Past date
        Cart cart = new Cart();
        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));

        assertThrows(CouponExpire.class, () -> couponService.applyCoupon(1L, cart));
        verify(couponRepository, times(1)).findById(1L);
    }

    @Test
    void testApplyCoupon_CouponNotFound() {
        Cart cart = new Cart();
        when(couponRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CouponNotFound.class, () -> couponService.applyCoupon(1L, cart));
        verify(couponRepository, times(1)).findById(1L);
    }
}