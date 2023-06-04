package com.heroxin.gulimall.thirdparty.controller;

/*
    @Author Heroxin
    
    @Create 2023-06-01-15:54

    @Description:
*/


import com.heroxin.gulimall.common.utils.R;
import com.heroxin.gulimall.thirdparty.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {
   @Autowired
   private SmsComponent smsComponent;

   @GetMapping("/sendcode")
   public R sendCode(@RequestParam(value = "phone") String phone, @RequestParam(value = "code") String code){
      smsComponent.sendSmsConde(phone,code);
      return R.ok();
   }
}
