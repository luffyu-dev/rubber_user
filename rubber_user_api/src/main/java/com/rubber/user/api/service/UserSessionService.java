package com.rubber.user.api.service;

import com.rubber.common.utils.result.ResultMsg;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.request.UserSession;


/**
 * @author luffyu
 * Created on 2021/10/21
 */
public interface UserSessionService {

    /**
     * 创建登录的session
     * @param request
     */
    String createSession(UserLoginRequest request);

    /**
     * @param userSession
     * @return
     */
    UserSession checkSession(UserSession userSession);
}
