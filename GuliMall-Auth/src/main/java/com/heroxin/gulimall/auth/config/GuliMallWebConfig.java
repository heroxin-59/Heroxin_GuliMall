package com.heroxin.gulimall.auth.config;

/*
    @Author Heroxin
    
    @Create 2023-06-01-9:25

    @Description:
*/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GuliMallWebConfig implements WebMvcConfigurer {
   /*
   * 视图映射
   * */
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/login.html").setViewName("login");
      registry.addViewController("/reg.html").setViewName("reg");

   }
}
