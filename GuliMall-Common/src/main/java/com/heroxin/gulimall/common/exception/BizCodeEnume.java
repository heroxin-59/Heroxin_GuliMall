package com.heroxin.gulimall.common.exception;


/*
    @Author Heroxin
    
    @Create 2023-04-03-19:05

    @Description: 系统错误码

   错误码和错误码信息定义类
   1. 错误码定义规则为 5 位数
   2. 前两位表示业务场景，后三位表示错误码
   3. 维护错误码需要维护错误描述，将他们定义为枚举型

   错误码列表：
    10：通用
           001：未知错误
    11：商品
    12：订单
    13：购物车
    14：物流
*/

public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验失败"),
    PRODUCT_UP_EXCEPTION(11000,"商品上架异常");

    private int code;
    private String msg;

    BizCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
