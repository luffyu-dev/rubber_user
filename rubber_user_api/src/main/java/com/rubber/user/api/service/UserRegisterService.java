package com.rubber.user.api.service;

import com.rubber.user.api.service.dto.UserRegisterInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public interface UserRegisterService {


    /**
     * 注册接口
     * @param userRegisterInfoDto 用户注册的基本信息
     * @return 返回注册之后的用户信息
     */
    UserInfoResponse register(UserRegisterInfoDto userRegisterInfoDto);
}
