package com.heroxin.gulimall.common.to;

import lombok.Data;

import java.math.BigDecimal;

/*
    @Author Heroxin
    
    @Create 2023-04-09-17:32

    @Description:
*/
@Data
public class SpuBoundTo {
    private Long spuId;

    private BigDecimal buyBounds;

    private BigDecimal growBounds;
}
