package com.rubber.user.service.login.handler;

import cn.hutool.core.util.StrUtil;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.response.UserLoginResponse;
import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.dao.logic.UserAccountInfoLogic;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.encrypt.IEncryptHandler;
import com.rubber.user.service.exception.UserRegisterException;
import com.rubber.user.service.login.bean.UserLoginBean;
import com.rubber.user.service.register.logic.UserAccountRegisterLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Slf4j
@Component(value = "userAccountLoginHandler")
public class UserAccountLoginHandler extends BaseUserLoginHandler {


    @Resource
    private UserAccountInfoLogic userAccountInfoLogic;

    @Resource(name = "encryptProvider")
    private IEncryptHandler iEncryptHandler;

    @Resource
    private UserAccountRegisterLogic userAccountRegisterLogic;

    /**
     * 查询账户信息
     *
     * @param userAccountInfoDto
     * @return
     */
    @Override
    public UserAccountInfo getBdUserAccount(UserLoginRequest userAccountInfoDto) {
        return userAccountInfoLogic.getInfoByAccount(userAccountInfoDto.getUserAccount());
    }

    /**
     * 登录操作
     *
     */
    @Override
    public UserLoginResponse doLogin(UserAccountInfo accountInfo, UserLoginRequest userAccountInfoDto) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        boolean result = iEncryptHandler.matches(userAccountInfoDto.getAccountPwd(),accountInfo.getUserSalt(),accountInfo.getUserPassword());
        if (!result){
            throw new UserRegisterException(ErrCodeEnums.PWD_IS_ERROR);
        }
        userLoginResponse.setUid(accountInfo.getUid());
        return userLoginResponse;
    }

    /**
     * 注册操作
     *
     * @param request
     */
    @Override
    public UserLoginResponse doRegister(UserLoginRequest request) {
        UserAccountInfo register = userAccountRegisterLogic.register(request);
        if (register == null){
            throw new UserRegisterException(ErrCodeEnums.REGISTER_ERROR);
        }
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setUid(register.getUid());
        return userLoginResponse;
    }




    /**
     * 必要的参数验证
     */
    private boolean verifyRequestParam(UserLoginBean userLoginBean, UserLoginRequest userAccountInfoDto){
        if (StrUtil.isEmpty(userAccountInfoDto.getUserAccount()) || StrUtil.isEmpty(userAccountInfoDto.getAccountPwd())){
            log.error("账户信息不能为空");
            userLoginBean.setErrCode(ErrCodeEnums.PARAM_ERROR);
            return false;
        }
        return true;
    }


}
