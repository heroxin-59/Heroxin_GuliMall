package com.heroxin.gulimall.product.app;

import java.util.Arrays;
import java.util.Map;

import com.heroxin.gulimall.product.vo.AttrResponseVo;
import com.heroxin.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.heroxin.gulimall.product.service.AttrService;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.common.utils.R;


/**
 * 商品属性
 *
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-25 18:35:22
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("catelogId") Long catelogId,
                          @PathVariable("attrType") String attrType
    ) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId,attrType);

        return R.ok().put("page", page);
    }

/*    @GetMapping("/{attrType}/list/{catelogId}")
    public R sellAttrList(
            @RequestParam Map<String, Object> params,
            @PathVariable("catelogId") Long catelogId,
            @PathVariable("attrType") String attrType
    ) {
        PageUtils page = attrService.querySellAttrPage(params, catelogId, attrType);

        return R.ok().put("page", page);
    }*/

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
//        AttrEntity attr = attrService.getById(attrId);
        AttrResponseVo attrResponseVo = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", attrResponseVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrResponseVo attr) {
        attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
