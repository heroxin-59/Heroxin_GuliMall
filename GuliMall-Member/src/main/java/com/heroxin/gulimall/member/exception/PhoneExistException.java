package com.heroxin.gulimall.member.exception;

/*
    @Author Heroxin
    
    @Create 2023-06-04-14:45

    @Description:
*/

public class PhoneExistException extends RuntimeException {
    public PhoneExistException() {
        super("手机号已存在！");
    }
}
