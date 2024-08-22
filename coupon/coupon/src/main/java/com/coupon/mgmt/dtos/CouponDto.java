package com.coupon.mgmt.dtos;

import com.coupon.mgmt.entity.Coupon;
import com.coupon.mgmt.entity.CouponType;
import com.coupon.mgmt.entity.DiscountDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.impl.cookie.DateUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    private String type;

    @Embedded
    private DiscountDetails details;

    private int validationDays;

    private int repetitionLimit; // For BxGy type


    public static Coupon toEntity(CouponDto dto) {
        if (dto == null) {
            return null;
        }

        Coupon coupon = new Coupon();
        coupon.setType(CouponType.valueOf(dto.getType())); // Assuming CouponType is an Enum
        coupon.setDetails(dto.getDetails());
        int validationDay= dto.getValidationDays();
        if (Objects.isNull(validationDay) || validationDay<=0){
            validationDay= 7;
        }
        coupon.setExpirationDate(System.currentTimeMillis()+ TimeUnit.DAYS.toMillis(validationDay));
        coupon.setRepetitionLimit(dto.getRepetitionLimit());
        return coupon;
    }



    public static CouponDto toDto(Coupon entity) {
        if (entity == null) {
            return null;
        }

        CouponDto couponDto = new CouponDto();
        couponDto.setType(entity.getType().name()); // Assuming CouponType is an Enum
        couponDto.setDetails(entity.getDetails());
        couponDto.setRepetitionLimit(entity.getRepetitionLimit());
        return couponDto;
    }
}