package com.heroxin.gulimall.product.feign;

/*
    @Author Heroxin
    
    @Create 2023-05-08-20:30

    @Description:
*/

import com.heroxin.gulimall.common.to.SkuHasStockVo;
import com.heroxin.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("GuliMall-Ware")
public interface WareFeignService {

    @PostMapping("/ware/waresku/hasstock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);
}
