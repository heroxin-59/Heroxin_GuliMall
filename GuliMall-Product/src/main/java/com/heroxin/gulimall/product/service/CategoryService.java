package com.heroxin.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.product.entity.CategoryEntity;

import java.util.Map;

/**
 * 商品三级分类
 *
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-25 18:35:22
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

