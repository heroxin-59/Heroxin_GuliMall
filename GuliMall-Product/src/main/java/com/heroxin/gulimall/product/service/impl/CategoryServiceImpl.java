package com.heroxin.gulimall.product.service.impl;

import com.heroxin.gulimall.product.service.CategoryBrandRelationService;
import com.heroxin.gulimall.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.common.utils.Query;

import com.heroxin.gulimall.product.dao.CategoryDao;
import com.heroxin.gulimall.product.entity.CategoryEntity;
import com.heroxin.gulimall.product.service.CategoryService;
import org.springframework.util.StringUtils;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
//        查出所有分类
        List<CategoryEntity> entityList = baseMapper.selectList(null);
//        组装成树形结构
//          找出一级分类
        List<CategoryEntity> entitiesMenu = entityList.stream().filter((categoryEntity) ->
                categoryEntity.getParentCid() == 0
        ).map((menu) -> {
            menu.setChildren(getChildren(menu, entityList));
            return menu;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return entitiesMenu;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
//        逻辑删除，使用标识位，表示是否存在

        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        List<Long> paretPath = findParetPath(catelogId, path);
        Collections.reverse(paretPath);


        return paretPath.toArray(new Long[paretPath.size()]);
    }

    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        System.out.println("--------------sdfsdf-------------");
        System.out.println(category.getName());
        System.out.println("---------------sdfsdf-------------");
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    @Override
    public List<CategoryEntity> getLevel1Category() {
        List<CategoryEntity> entities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));

        return entities;
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
//        查出所有一级分类
        List<CategoryEntity> level1Category = getLevel1Category();
//        封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Category.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
//            查询当前分类的所有二级分类
            List<CategoryEntity> level2Catelog = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", v.getCatId()));
            List<Catelog2Vo> collect = null;
            if (level2Catelog != null) {
                collect = level2Catelog.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
//                    查询当前分类的所有三级分类
                    List<CategoryEntity> level3Catelog = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", l2.getCatId()));
                    if (level3Catelog != null) {
//                        封装为指定格式
                        List<Catelog2Vo.Category3Vo> collect1 = level3Catelog.stream().map(l3 -> {
                            Catelog2Vo.Category3Vo category3Vo = new Catelog2Vo.Category3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return category3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect1);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return collect;
        }));

        return parent_cid;
    }


    private List<Long> findParetPath(Long catelogId, List<Long> path) {
        path.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParetPath(byId.getParentCid(), path);
        }
        return path;

    }

    //    获取所有菜单的子菜单
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> entities = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(categoryEntity -> {
            //            找到子菜单
            categoryEntity.setChildren(getChildren(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return entities;
    }

}