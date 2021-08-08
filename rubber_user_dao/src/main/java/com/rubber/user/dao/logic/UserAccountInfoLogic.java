package com.rubber.user.dao.logic;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.dao.exception.UserDaoException;
import com.rubber.user.dao.mapper.UserAccountInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Component
public class UserAccountInfoLogic {

    @Resource
    private UserAccountInfoMapper userAccountInfoMapper;

    @Resource
    private GlobalUidGeneratorLogic globalUidGeneratorLogic;


    /**
     * 通过uid查询一个账户信息 如果存在多个账户，那么默认返回第一条信息
     * @param uid 当前的uid
     * @return 返回账户信息
     */
    public UserAccountInfo getByUid(Integer uid){
        if (uid == null){
            return null;
        }
        QueryWrapper<UserAccountInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.last(" limit 1");
        return userAccountInfoMapper.selectOne(queryWrapper);
    }


    /**
     * 通过账户信息查询uid
     * @param userAccount 当前的账户信息
     * @return 返回uid
     */
    public Integer getUidByAccount(String userAccount){
        QueryWrapper<UserAccountInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("uid");
        queryWrapper.eq("user_account",userAccount);
        UserAccountInfo userAccountInfo = userAccountInfoMapper.selectOne(queryWrapper);
        if (userAccountInfo == null || userAccountInfo.getUid() == null){
            return null;
        }
        return userAccountInfo.getUid();
    }


    /**
     * 通过账户信息查询账户信息
     * @param userAccount 当前的账户信息
     * @return 返回uid
     */
    public UserAccountInfo getInfoByAccount(String userAccount){
        if (StrUtil.isEmpty(userAccount)){
            return null;
        }
        QueryWrapper<UserAccountInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount);
        UserAccountInfo userAccountInfo = userAccountInfoMapper.selectOne(queryWrapper);
        if (userAccountInfo == null || userAccountInfo.getUid() == null){
            return null;
        }
        return userAccountInfo;
    }


    /**
     * 新增一个账户的方法
     * @param userAccountInfo 当前的uid信息
     */
    @Transactional(
            rollbackFor = Throwable.class
    )
    public boolean addUserAccount(UserAccountInfo userAccountInfo){
        if (userAccountInfo == null){
            throw new UserDaoException("install userAccountInfo is null");
        }
        if (userAccountInfo.getUid() == null){
            int uid = globalUidGeneratorLogic.createGlobalUid();
            userAccountInfo.setUid(uid);
        }
        if (userAccountInfo.getModifyTime() == null || userAccountInfo.getCreateTime() == null){
            LocalDateTime now = LocalDateTime.now();
            userAccountInfo.setModifyTime(now);
            userAccountInfo.setCreateTime(now);
        }
        if (userAccountInfo.getVersion() == null){
            userAccountInfo.setVersion(0);
        }
        return userAccountInfoMapper.insert(userAccountInfo) > 0;
    }



    /**
     * 更新一个账户的方法
     * @param userAccountInfo 当前的uid信息
     */
    @Transactional(
            rollbackFor = Throwable.class
    )
    public boolean updateUserAccountByUid(UserAccountInfo userAccountInfo){
        if (userAccountInfo == null || userAccountInfo.getUid() == null){
            throw new UserDaoException("install userAccountInfo is null");
        }
        QueryWrapper<UserAccountInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",userAccountInfo.getUid());
        return userAccountInfoMapper.update(userAccountInfo,queryWrapper) <= 0;
    }



}
