package com.heroxin.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@MapperScan("com.heroxin.gulimall.product.dao")
@EnableRedisHttpSession
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class GuliMallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliMallProductApplication.class, args);
    }

}
