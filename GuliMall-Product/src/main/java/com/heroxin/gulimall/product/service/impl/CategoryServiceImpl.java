package com.heroxin.gulimall.product.service.impl;

import com.heroxin.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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