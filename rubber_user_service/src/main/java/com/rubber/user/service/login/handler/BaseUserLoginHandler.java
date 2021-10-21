package com.rubber.user.service.login.handler;

import com.rubber.user.api.service.UserSessionService;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.response.UserLoginResponse;
import com.rubber.user.dao.constant.UserStatusEnums;
import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.exception.UserRegisterException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author luffyu
 * Created on 2021/8/8
 */
public abstract class BaseUserLoginHandler implements UserLoginHandler {


    @Autowired
    private UserSessionService userSessionService;


    /**
     * 查询账户信息
     * @param userAccountInfoDto
     * @return
     */
    public abstract UserAccountInfo getBdUserAccount(UserLoginRequest userAccountInfoDto);

    /**
     * 登录操作
     */
    public abstract UserLoginResponse doLogin(UserAccountInfo accountInfo, UserLoginRequest accountInfoDto);


    /**
     * 注册操作
     */
    public abstract UserLoginResponse doRegister(UserLoginRequest userAccountInfoDto);

    /**
     * 登录接口
     *
     * @param request 当前的账户信息
     * @return 返回登录之后的信息
     */
    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        //登录的前置操作
        doVerifyRequest(request);
        UserAccountInfo accountInfo = getBdUserAccount(request);
        UserLoginResponse loginBean ;
        if (accountInfo == null){
            loginBean = doRegister(request);
        }else {
            loginBean = doLogin(accountInfo,request);
        }
        doPostLoginSuccess(accountInfo,request,loginBean);
        return loginBean;
    }


    private void doVerifyRequest(UserLoginRequest dto){

    }



    /**
     * 登录成功之后的操作
     */
    private void doPostLoginSuccess(UserAccountInfo accountInfo,UserLoginRequest request,UserLoginResponse response){
        if (!UserStatusEnums.NORMAL.getUserStatus().equals(accountInfo.getUserStatus())){
            throw new UserRegisterException(ErrCodeEnums.PWD_IS_ERROR);
        }
        request.setUid(accountInfo.getUid());
        response.setUid(accountInfo.getUid());
        String token = userSessionService.createSession(request);
        response.setSessionKey(token);
    }



}
