package com.rubber.user.service.register.handler;

import cn.hutool.core.util.StrUtil;
import com.rubber.user.api.service.dto.UserRegisterInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.exception.UserRegisterException;
import com.rubber.user.service.register.logic.UserAccountRegisterLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 通过手机号注册的service
 * @author luffyu
 * Created on 2021/8/8
 */
@Slf4j
@Component(value = "userThirdRegisterHandler")
public class UserThirdRegisterHandler implements UserRegisterHandler{

    @Resource
    private UserAccountRegisterLogic userAccountRegisterLogic;


    /**
     * 注册接口
     *
     * @param userRegisterInfoDto 用户注册的基本信息
     * @return 返回注册之后的用户信息
     */
    @Override
    public UserInfoResponse register(UserRegisterInfoDto userRegisterInfoDto) {
        if (StrUtil.isEmpty(userRegisterInfoDto.getThirdAccountId())){
            //空号校验等
            log.error("注册的时候第三方账户为空");
            throw new UserRegisterException(ErrCodeEnums.PARAM_ERROR);
        }
        userRegisterInfoDto.setUserAccount(userRegisterInfoDto.getThirdAccountId());
        return userAccountRegisterLogic.addUser(userRegisterInfoDto);
    }
}
