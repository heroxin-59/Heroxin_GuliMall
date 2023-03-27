package com.heroxin.gulimall.order.dao;

import com.heroxin.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-27 10:49:59
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
