package com.heroxin.gulimall.product;


import cn.hutool.log.Log;
import com.heroxin.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@Slf4j
@SpringBootTest
class GuliMallProductApplicationTests {

    @Autowired
    CategoryService categoryService;
    @Test
    public void terst(){
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        System.out.println(Arrays.toString(catelogPath));
    }
}
