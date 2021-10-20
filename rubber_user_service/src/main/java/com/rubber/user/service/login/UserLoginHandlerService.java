package com.rubber.user.service.login;

import com.rubber.user.api.service.UserLoginService;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.api.service.response.UserLoginResponse;
import com.rubber.user.service.login.handler.UserLoginHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Service("userLoginService")
public class UserLoginHandlerService implements UserLoginService {

    @Resource(name = "userAccountLoginHandler")
    private UserLoginHandler userLoginHandler;

    /**
     * 登录接口
     *
     * @param request 当前的账户信息
     * @return 返回登录之后的信息
     */
    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response;
        //支持不同类型的登录方式
        switch (request.getAccountType()){
            case 0:
            case 1:
            case 2:
            default:
                response = userLoginHandler.login(request);
        }
        return response;
    }


}
