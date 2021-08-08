package com.rubber.user.api.service;

import com.rubber.user.api.service.dto.UserAccountInfoDto;
import com.rubber.user.api.service.dto.UserRegisterInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public interface UserLoginService {

    /**
     * 登录接口
     * @param userAccountInfoDto 当前的账户信息
     * @return 返回登录之后的信息
     */
    UserInfoResponse login(UserAccountInfoDto userAccountInfoDto);



}
