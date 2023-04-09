package com.heroxin.gulimall.product.feign;

/*
    @Author Heroxin
    
    @Create 2023-04-09-17:27

    @Description:
*/

import com.heroxin.gulimall.common.to.SkuReductionTo;
import com.heroxin.gulimall.common.to.SpuBoundTo;
import com.heroxin.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("GuliMall-Coupon")
public interface CouponFeignService {
    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);
}
