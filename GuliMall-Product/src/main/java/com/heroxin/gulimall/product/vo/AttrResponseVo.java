package com.heroxin.gulimall.product.vo;

/*
    @Author Heroxin
    
    @Create 2023-04-06-17:56

    @Description:
*/

import lombok.Data;

@Data
public class AttrResponseVo extends AttrVo {
    private String catelogName;
    private String groupName;
    private Long[] catelogPath;
}
