package com.heroxin.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heroxin.gulimall.product.entity.CategoryEntity;
import com.heroxin.gulimall.product.service.CategoryService;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.common.utils.R;



/**
 * 商品三级分类
 *
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-25 18:35:22
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * heroxin
     * 列表
     * 查出所有分类及其子分类，以树形结构组装
     */
    @RequestMapping("/list/tree")
    public R list(){
        List<CategoryEntity> entityList = categoryService.listWithTree();
        return R.ok().put("page", entityList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateCascade(category);
//        categoryService.updateById(category);
        return R.ok();
    }

    /**
     * heroxin
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
//        检查当前被删除的菜单是否被引用
		categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
