package com.heroxin.gulimall.product.web;

/*
    @Author Heroxin
    
    @Create 2023-05-10-17:34

    @Description:
*/

import com.heroxin.gulimall.product.entity.CategoryEntity;
import com.heroxin.gulimall.product.service.CategoryService;
import com.heroxin.gulimall.product.vo.Catelog2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {
//        查出所有的一级分类
        List<CategoryEntity> categoryEntityList = categoryService.getLevel1Category();

        model.addAttribute("categories", categoryEntityList);
        return "index";
    }

    @GetMapping(value = "/index/catalog.json")
    @ResponseBody
    public Map<String, List<Catelog2Vo>> getCatalogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();

        return catalogJson;

    }
}
