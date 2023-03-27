package com.heroxin.gulimall.product;

import com.heroxin.gulimall.product.entity.BrandEntity;
import com.heroxin.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuliMallProductApplicationTests {

    @Autowired
    private BrandService brandService;
    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("heroxin");
        brandEntity.setName("heroxin");
        brandService.save(brandEntity);
        System.out.println("c");
    }

}
