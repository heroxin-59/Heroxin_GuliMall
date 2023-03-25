package com.heroxin.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.product.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-25 18:35:22
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

