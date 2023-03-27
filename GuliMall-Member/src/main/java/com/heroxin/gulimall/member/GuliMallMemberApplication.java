package com.heroxin.gulimall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/*
* 1.使用feign调用远程服务
* 2.引入依赖
* 3.编写一个接口，告诉springcloud这个接口需要远程调用服务
* 4.声明接口的每一个方法是调用服务的哪个请求
* 5.开启远程调用功能
* */
//@ComponentScan("com.heroxin.gulimall.member.feign")

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GuliMallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliMallMemberApplication.class, args);
    }

}
