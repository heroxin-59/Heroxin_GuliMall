package com.heroxin.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.product.entity.AttrEntity;
import com.heroxin.gulimall.product.vo.AttrGroupRelationVo;
import com.heroxin.gulimall.product.vo.AttrResponseVo;
import com.heroxin.gulimall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-25 18:35:22
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    PageUtils querySellAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    void saveAttr(AttrVo attr);

    AttrResponseVo getAttrInfo(Long attrId);

    void updateAttr(AttrResponseVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void delAttrRelation(AttrGroupRelationVo[] attrGroupRelationVo);

    PageUtils getNoAttrRelation(Map<String, Object> params, Long attrgroupId);
}

