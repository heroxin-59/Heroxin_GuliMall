package com.heroxin.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/*
    @Author Heroxin

    @Create 2023-04-07-19:51

    @Description:
*/

@Data
@ToString
public class SpuItemAttrGroupVo {

    private String groupName;

    private List<Attr> attrs;

}
