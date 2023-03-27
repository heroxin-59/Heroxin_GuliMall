package com.heroxin.gulimall.coupon.dao;

import com.heroxin.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-27 10:33:04
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
