package com.heroxin.gulimall.auth.feign;

/*
    @Author Heroxin
    
    @Create 2023-06-04-15:54

    @Description:
*/

import com.heroxin.gulimall.auth.vo.UserLoginVo;
import com.heroxin.gulimall.auth.vo.UserRegisterVo;
import com.heroxin.gulimall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("GuliMall-Member")
public interface MemberFeignService {
    @PostMapping("/member/member/register")
    R register(@RequestBody UserRegisterVo vo);
    @PostMapping("/member/member/login")
    R login(@RequestBody UserLoginVo vo);
}
