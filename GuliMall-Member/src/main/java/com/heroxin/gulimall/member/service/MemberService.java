package com.heroxin.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author heroxin
 * @email hero_xin59@163.com
 * @date 2023-03-27 10:40:16
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

