package com.rubber.user.service.login.logic;

import cn.hutool.core.util.StrUtil;
import com.rubber.user.api.service.dto.UserAccountInfoDto;
import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.dao.logic.UserAccountInfoLogic;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.encrypt.IEncryptHandler;
import com.rubber.user.service.exception.UserLoginException;
import com.rubber.user.service.exception.UserRegisterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Slf4j
@Component
public class UserAccountLoginLogic {


    @Resource
    private UserAccountInfoLogic userAccountInfoLogic;

    @Resource(name = "encryptProvider")
    private IEncryptHandler iEncryptHandler;


    /**
     * 通过账户登录的接口
     * @param userAccountInfoDto 当前的账户信息
     * @return 返回
     */
    public Integer loginByAccount(UserAccountInfoDto userAccountInfoDto){
        //基础参数验证
        verifyRequestParam(userAccountInfoDto);

        UserAccountInfo userAccountInfo = userAccountInfoLogic.getInfoByAccount(userAccountInfoDto.getUserAccount());
        if (userAccountInfo == null){
           throw new UserLoginException(ErrCodeEnums.ACCOUNT_NOT_FOUNT);
        }
        verifyLogicData(userAccountInfo,userAccountInfoDto);

        return userAccountInfo.getUid();
    }


    /**
     * 必要的参数验证
     */
    private void verifyRequestParam(UserAccountInfoDto userAccountInfoDto){
        if (StrUtil.isEmpty(userAccountInfoDto.getUserAccount()) || StrUtil.isEmpty(userAccountInfoDto.getAccountPwd())){
            log.error("账户信息不能为空");
            throw new UserRegisterException(ErrCodeEnums.PARAM_ERROR);
        }
    }

    /**
     * 逻辑数据验证
     */
    private void verifyLogicData(UserAccountInfo userAccountInfo,UserAccountInfoDto userAccountInfoDto){
        boolean result = iEncryptHandler.matches(userAccountInfoDto.getAccountPwd(),userAccountInfo.getUserSalt(),userAccountInfo.getUserPassword());
        if (!result) {
            throw new UserRegisterException(ErrCodeEnums.PWD_IS_ERROR);
        }
    }

}
