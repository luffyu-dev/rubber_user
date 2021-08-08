package com.rubber.user.service.login.handler;

import com.rubber.user.api.service.dto.UserAccountInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.exception.UserLoginException;
import com.rubber.user.service.info.logic.QueryUserInfoLogic;
import com.rubber.user.service.login.bean.UserLoginBean;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public abstract class BaseUserLoginHandler implements UserLoginHandler {

    @Resource
    private QueryUserInfoLogic queryUserInfoLogic;


    /**
     * 验证码校验
     */
    public abstract void doCheckCipher(UserAccountInfoDto userAccountInfoDto);



    /**
     * 登录操作
     */
    public abstract UserLoginBean doLogin(UserAccountInfoDto userAccountInfoDto);


    /**
     * 登录接口
     *
     * @param userAccountInfoDto 当前的账户信息
     * @return 返回登录之后的信息
     */
    @Override
    public UserInfoResponse login(UserAccountInfoDto userAccountInfoDto) {
        //登录的前置操作
        doPreLogin(userAccountInfoDto);
        UserLoginBean userLoginBean = doLogin(userAccountInfoDto);
        if (userLoginBean != null && userLoginBean.getUid() != null){
            doPostLoginSuccess(userLoginBean.getUid(),userAccountInfoDto);
            return queryUserInfoLogic.getUserInfoByUid(userLoginBean.getUid());
        }else {
            if (userLoginBean == null){
                userLoginBean = new UserLoginBean();
            }
            doPostLoginFail(userLoginBean,userAccountInfoDto);
            return new UserInfoResponse();
        }
    }


    /**
     * 登录之前的前置操作
     * @param userAccountInfoDto 验证登录密码
     */
    private void doPreLogin(UserAccountInfoDto userAccountInfoDto){
        doCheckCipher(userAccountInfoDto);
    }


    /**
     * 登录成功之后的操作
     */
    private void doPostLoginSuccess(Integer uid,UserAccountInfoDto userAccountInfoDto){

    }

    /**
     * 登录失败之后的操作
     */
    private void doPostLoginFail(UserLoginBean userLoginBean,UserAccountInfoDto userAccountInfoDto){
        throw new UserLoginException(userLoginBean.getErrCode() == null ? ErrCodeEnums.ACCOUNT_NOT_FOUNT : userLoginBean.getErrCode());
    }


}
