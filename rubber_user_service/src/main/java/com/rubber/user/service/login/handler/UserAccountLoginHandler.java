package com.rubber.user.service.login.handler;

import cn.hutool.core.util.StrUtil;
import com.rubber.user.api.service.dto.UserAccountInfoDto;
import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.dao.logic.UserAccountInfoLogic;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.encrypt.IEncryptHandler;
import com.rubber.user.service.login.bean.UserLoginBean;
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


    /**
     * 登录操作
     *
     */
    @Override
    public UserLoginBean doLogin(UserAccountInfoDto userAccountInfoDto) {
        UserLoginBean userLoginBean = new UserLoginBean();
        if(!verifyRequestParam(userLoginBean,userAccountInfoDto)){
            return userLoginBean;
        }
        UserAccountInfo userAccountInfo = userAccountInfoLogic.getInfoByAccount(userAccountInfoDto.getUserAccount());
        if (userAccountInfo == null){
            userLoginBean.setErrCode(ErrCodeEnums.ACCOUNT_NOT_FOUNT);
            return userLoginBean;
        }
        if(!verifyLogicData(userLoginBean,userAccountInfo,userAccountInfoDto)){
            return userLoginBean;
        }
        userLoginBean.setUid(userAccountInfo.getUid());
        return userLoginBean;
    }

    /**
     * 验证码校验
     *
     * @param userAccountInfoDto
     */
    @Override
    public void doCheckCipher(UserAccountInfoDto userAccountInfoDto) {

    }







    /**
     * 必要的参数验证
     */
    private boolean verifyRequestParam(UserLoginBean userLoginBean,UserAccountInfoDto userAccountInfoDto){
        if (StrUtil.isEmpty(userAccountInfoDto.getUserAccount()) || StrUtil.isEmpty(userAccountInfoDto.getAccountPwd())){
            log.error("账户信息不能为空");
            userLoginBean.setErrCode(ErrCodeEnums.PARAM_ERROR);
            return false;
        }
        return true;
    }

    /**
     * 逻辑数据验证
     */
    private boolean verifyLogicData(UserLoginBean userLoginBean,UserAccountInfo userAccountInfo,UserAccountInfoDto userAccountInfoDto){
        boolean result = iEncryptHandler.matches(userAccountInfoDto.getAccountPwd(),userAccountInfo.getUserSalt(),userAccountInfo.getUserPassword());
        if (!result){
            userLoginBean.setErrCode(ErrCodeEnums.PWD_IS_ERROR);
            return false;
        }
        return true;
    }
}
