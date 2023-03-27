package com.heroxin.gulimall.member.dao;

import com.heroxin.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-27 10:40:16
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
