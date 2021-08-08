package com.rubber.user.service.info.logic;

import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.dao.entity.UserBasicInfo;
import com.rubber.user.dao.logic.UserBasicInfoLogic;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Component
public class QueryUserInfoLogic {

    @Resource
    private UserBasicInfoLogic userBasicInfoLogic;

    /**
     * 通过uid查询相关信息
     * @param uid 当前的uid
     */
    public UserInfoResponse getUserInfoByUid(Integer uid){
        UserBasicInfo basicInfo = userBasicInfoLogic.getByUid(uid);
        if (basicInfo == null){
            return null;
        }
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(basicInfo,userInfoResponse);
        if (userInfoResponse.getUserNick() == null){
            userInfoResponse.setUserNick(String.valueOf(userInfoResponse.getUid()));
        }
        return userInfoResponse;
    }
}
