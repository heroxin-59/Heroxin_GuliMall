package com.heroxin.gulimall.product.feign;

/*
    @Author Heroxin
    
    @Create 2023-05-09-16:05

    @Description:
*/

import com.heroxin.gulimall.common.to.SkuEsModel;
import com.heroxin.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("GuliMall-Search")
public interface SearchFeignService {
    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
