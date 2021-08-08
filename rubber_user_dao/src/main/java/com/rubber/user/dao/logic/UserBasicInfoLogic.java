package com.rubber.user.dao.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rubber.user.dao.entity.UserBasicInfo;
import com.rubber.user.dao.exception.UserDaoException;
import com.rubber.user.dao.mapper.UserBasicInfoMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Component
public class UserBasicInfoLogic {

    @Resource
    private UserBasicInfoMapper userBasicInfoMapper;


    /**
     * 保存用户的基本信息
     * @param userBasicInfo 当前用户的基本信息
     */
    @Transactional(
            rollbackFor = Throwable.class
    )
    public boolean addUserBasic(UserBasicInfo userBasicInfo){
        if (userBasicInfo == null){
            throw new UserDaoException("install userAccountInfo is null");
        }

        if (userBasicInfo.getModifyTime() == null || userBasicInfo.getCreateTime() == null){
            LocalDateTime now = LocalDateTime.now();
            userBasicInfo.setModifyTime(now);
            userBasicInfo.setCreateTime(now);
        }
        if (userBasicInfo.getVersion() == null){
            userBasicInfo.setVersion(0);
        }
        return userBasicInfoMapper.insert(userBasicInfo) > 0;
    }


    /**
     * 通过uid查询相关信息
     * @param uid 当前的uid
     * @return 返回基本信息
     */
    public UserBasicInfo getByUid(Integer uid){
        if(uid == null){
            return null;
        }
        QueryWrapper<UserBasicInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        return userBasicInfoMapper.selectOne(queryWrapper);
    }
}
