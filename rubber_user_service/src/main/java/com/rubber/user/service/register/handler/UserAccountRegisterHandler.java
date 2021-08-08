package com.rubber.user.service.register.handler;

import com.rubber.user.api.service.dto.UserRegisterInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.service.register.logic.UserAccountRegisterLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Slf4j
@Component(value = "userAccountRegisterHandler")
public class UserAccountRegisterHandler implements UserRegisterHandler {

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
        return userAccountRegisterLogic.addUser(userRegisterInfoDto);
    }
}
