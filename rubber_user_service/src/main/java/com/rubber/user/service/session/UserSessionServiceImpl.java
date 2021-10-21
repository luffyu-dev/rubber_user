package com.rubber.user.service.session;

import com.rubber.base.components.util.dispatch.DispatchHandlerContext;
import com.rubber.common.utils.result.code.SysCode;
import com.rubber.user.api.service.UserSessionService;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.request.UserSession;
import com.rubber.user.service.constant.SessionLoginType;
import com.rubber.user.service.exception.UserRegisterException;
import com.rubber.user.service.session.handler.UserSessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luffyu
 * Created on 2021/10/21
 */
@Service
public class UserSessionServiceImpl implements UserSessionService {



    @Autowired
    private DispatchHandlerContext dispatchHandlerContext;


    private static final String SESSION_TYPE = SessionLoginType.JWT;


    /**
     * 创建登录的session
     *
     * @param request
     */
    @Override
    public String createSession(UserLoginRequest request) {
        UserSessionHandler sessionHandler = dispatchHandlerContext.dispatch(UserSessionHandler.class,SESSION_TYPE);
        if (sessionHandler == null){
            throw new UserRegisterException(SysCode.SYSTEM_ERROR);
        }
        return sessionHandler.createSession(request);
    }

    /**
     * @param userSession
     * @return
     */
    @Override
    public UserSession checkSession(UserSession userSession) {
        UserSessionHandler sessionHandler = dispatchHandlerContext.dispatch(UserSessionHandler.class,SESSION_TYPE);
        if (sessionHandler == null){
            throw new UserRegisterException(SysCode.SYSTEM_ERROR);
        }
        return sessionHandler.checkSession(userSession.getToken());
    }
}
