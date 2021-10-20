package com.rubber.user.api.service;

import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.api.service.response.UserLoginResponse;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public interface UserLoginService {

    /**
     * 登录接口
     * @param request 当前的账户信息
     * @return 返回登录之后的信息
     */
    UserLoginResponse login(UserLoginRequest request);



}
