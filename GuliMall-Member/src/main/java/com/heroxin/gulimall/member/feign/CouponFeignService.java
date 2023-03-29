package com.heroxin.gulimall.member.feign;

/*
    @Author Heroxin
    
    @Create 2023-03-27-18:24

    @Description:
*/

import com.heroxin.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("GuliMall-Coupon")
public interface CouponFeignService {
   @RequestMapping("/coupon/coupon/member/list")
   R membercoupons();
}
