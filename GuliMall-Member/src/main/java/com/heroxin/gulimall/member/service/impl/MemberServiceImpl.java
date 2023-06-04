package com.heroxin.gulimall.member.service.impl;

import com.heroxin.gulimall.member.dao.MemberLevelDao;
import com.heroxin.gulimall.member.entity.MemberLevelEntity;
import com.heroxin.gulimall.member.exception.PhoneExistException;
import com.heroxin.gulimall.member.exception.UserNameExistException;
import com.heroxin.gulimall.member.vo.MemberLoginVo;
import com.heroxin.gulimall.member.vo.MemberRegistVo;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heroxin.gulimall.common.utils.PageUtils;
import com.heroxin.gulimall.common.utils.Query;

import com.heroxin.gulimall.member.dao.MemberDao;
import com.heroxin.gulimall.member.entity.MemberEntity;
import com.heroxin.gulimall.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {
    @Autowired
    private MemberLevelDao memberLevelDao;
    @Autowired
    private MemberDao memberDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void register(MemberRegistVo vo) {
        MemberEntity entity = new MemberEntity();
//        检查用户名和手机号是否唯一，为了让controller 感知异常，可以使用抛出异常机制
        checkEmailUnique(vo.getPhone());
        checkUserNameUnique(vo.getUserName());

//        设置默认等级
        MemberLevelEntity levelEntity = memberLevelDao.getDefaultLevel();
        entity.setLevelId(levelEntity.getId());

//        设置手机号
        entity.setMobile(vo.getPhone());
//        设置用户名
        entity.setUsername(vo.getUserName());
        entity.setNickname(vo.getUserName());
//        设置密码
        /*
         * 密码在数据库中加密处理
         * */
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(vo.getPassword());
        entity.setPassword(encode);
        entity.setEmail(vo.getPhone());
        entity.setGender(0);
        entity.setCreateTime(new Date());
        memberDao.insert(entity);
    }

    @Override
    public void checkEmailUnique(String phone) throws PhoneExistException {
        Integer integer = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if (integer > 0) {
            throw new PhoneExistException();
        }
    }

    @Override
    public void checkUserNameUnique(String userName) throws UserNameExistException {
        Integer integer = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("username", userName));
        if (integer > 0) {
            throw new UserNameExistException();
        }
    }

    @Override
    public MemberEntity login(MemberLoginVo vo) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        MemberEntity memberEntity = memberDao.selectOne(new QueryWrapper<MemberEntity>().eq("username", vo.getLoginacct()).or().eq("mobile", vo.getLoginacct()).or().eq("email", vo.loginacct));
        if (memberEntity == null) {
            return null;
        } else {
            String passwordDB = memberEntity.getPassword();
            boolean matches = bCryptPasswordEncoder.matches(vo.getPassword(), passwordDB);
            if (matches) {
                return memberEntity;
            } else {
                return null;
            }
        }

    }

}