/** Copyright 2020 bejson.com */
package com.heroxin.gulimall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

/*
    @Author Heroxin

    @Create 2023-04-07-19:51

    @Description:
*/

@Data
public class MemberPrice {

  private Long id;
  private String name;
  private BigDecimal price;

}
