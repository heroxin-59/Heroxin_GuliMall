package com.heroxin.gulimall.auth.feign;

/*
    @Author Heroxin
    
    @Create 2023-06-01-16:06

    @Description:
*/

import com.heroxin.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("GuliMall-ThirdParty")
public interface ThirdPartFeignService {
    @GetMapping("/sms/sendcode")
    R sendCode(@RequestParam(value = "phone") String phone, @RequestParam(value = "code") String code);
}
