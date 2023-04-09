package com.heroxin.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.heroxin.gulimall.product.entity.AttrEntity;
import com.heroxin.gulimall.product.service.AttrAttrgroupRelationService;
import com.heroxin.gulimall.product.service.AttrService;
import com.heroxin.gulimall.product.service.CategoryService;
import com.heroxin.gulimall.product.vo.AttrGroupRelationVo;
import com.heroxin.gulimall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.heroxin.gulimall.product.entity.AttrGroupEntity;
import com.heroxin.gulimall.product.service.AttrGroupService;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.common.utils.R;


/**
 * 属性分组
 *
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-25 18:35:22
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private AttrAttrgroupRelationService relationService;


    /*
    * heroxin
    * 获取分类下所有分组&关联属性
    * /product/attrgroup/{catelogId}/withattr
    * */
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupaWithAttrs(@PathVariable("catelogId")Long catelogId){
//        查出当前分类下的所有属性分组
//        查出每个属性分组下的所有属性
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",vos);
    }


    /*
    * heroxin
    * 获取属性分组关联的所有属性
    * */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> entities = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",entities);
    }
    /*
    * heroxin
    * 获取属性分组没有关联的所有属性
    * */
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R noattrRelation(@RequestParam Map<String, Object> params,@PathVariable("attrgroupId") Long attrgroupId){
        PageUtils page = attrService.getNoAttrRelation(params,attrgroupId);
        return R.ok().put("page",page);
    }
    /*
    * heroxin
    * 删除属性分组关联的属性
    * /product/attrgroup/attr/relation/delete
    * */
    @PostMapping("/attr/relation/delete")
    public R attrRelationDelete(@RequestBody AttrGroupRelationVo[] attrGroupRelationVo){
        attrService.delAttrRelation(attrGroupRelationVo);
        return R.ok();
    }
    /*
    * heroxin
    * 新增属性分组关联的属性
    * /product/attrgroup/attr/relation
    * */
    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos){
        relationService.saveBatch(vos);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
//        PageUtils page = attrGroupService.queryPage(params);

        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        System.out.println(Arrays.toString(catelogPath));
        attrGroup.setCatelogPath(catelogPath);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
