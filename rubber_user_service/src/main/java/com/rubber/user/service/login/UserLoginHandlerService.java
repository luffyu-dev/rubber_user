package com.rubber.user.service.login;

import com.rubber.user.api.service.UserLoginService;
import com.rubber.user.api.service.dto.UserAccountInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.service.login.handler.UserAccountLoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Service("userLoginService")
public class UserLoginHandlerService implements UserLoginService {

    @Autowired
    private UserAccountLoginHandler userAccountLoginHandler;


    /**
     * 登录接口
     *
     * @param userAccountInfoDto 当前的账户信息
     * @return 返回登录之后的信息
     */
    @Override
    public UserInfoResponse login(UserAccountInfoDto userAccountInfoDto) {
        return userAccountLoginHandler.login(userAccountInfoDto);
    }


}
