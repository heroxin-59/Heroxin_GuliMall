package com.heroxin.gulimall.member.exception;

/*
    @Author Heroxin
    
    @Create 2023-06-04-14:45

    @Description:
*/

public class UserNameExistException extends RuntimeException{
    public UserNameExistException() {
        super("用户名已存在！");
    }
}
