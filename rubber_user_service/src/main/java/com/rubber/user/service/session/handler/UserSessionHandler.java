package com.rubber.user.service.session.handler;

import com.rubber.base.components.util.dispatch.bean.DispatchAble;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.request.UserSession;

/**
 * @author luffyu
 * Created on 2021/10/21
 */
public interface UserSessionHandler extends DispatchAble {

    /**
     * 创建登录的session
     * @param request
     */
    String createSession(UserLoginRequest request);


    /**
     *
     * @param key
     * @return
     */
    UserSession checkSession(String key);
}
