package com.heroxin.gulimall.product;


import com.heroxin.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class GuliMallProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    @Test
    public void redisson(){
        System.out.println(redissonClient);
    }
    @Test
    public void testredis() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("hello", "world" + UUID.randomUUID().toString());

        System.out.println(ops.get("hello"));
    }

    @Test
    public void terst() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        System.out.println(Arrays.toString(catelogPath));
    }


}
