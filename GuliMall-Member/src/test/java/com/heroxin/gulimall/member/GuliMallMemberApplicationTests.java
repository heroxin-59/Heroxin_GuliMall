package com.heroxin.gulimall.member;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.jupiter.api.Test;

class GuliMallMemberApplicationTests {

    @Test
    public void testMd5(){
        String s = DigestUtils.md5Hex("heroxin");
        System.out.println(s);
//        盐值加密
        String s1 = Md5Crypt.md5Crypt("heroxin".getBytes(),"$1$heroxinx");
        System.out.println(s1);
    }

}
