package com.heroxin.gulimall.member;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


class GuliMallMemberApplicationTests {


    @Test
    public void bCryptPasswordEncoder(){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        boolean b = bc.matches("heroxin", "$2a$10$4Xvl1DviJqS.ggItgMKmTO6JjbGbiX3btz/qdneVkPmNZ.zhO6br2");
//        true
        System.out.println(b);
    }

}
