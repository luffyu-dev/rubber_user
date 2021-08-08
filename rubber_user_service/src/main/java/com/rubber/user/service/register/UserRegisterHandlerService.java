package com.rubber.user.service.register;

import com.rubber.user.api.service.UserRegisterService;
import com.rubber.user.api.service.dto.UserRegisterInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.dao.constant.UserLoginType;
import com.rubber.user.service.register.handler.UserAccountRegisterHandler;
import com.rubber.user.service.register.handler.UserPhoneRegisterHandler;
import com.rubber.user.service.register.handler.UserThirdRegisterHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Service("userRegisterService")
public class UserRegisterHandlerService implements UserRegisterService {


    @Resource
    private UserAccountRegisterHandler userAccountRegisterHandler;


    @Resource
    private UserPhoneRegisterHandler userPhoneRegisterHandler;

    @Resource
    private UserThirdRegisterHandler userThirdRegisterHandler;



    /**
     * 注册接口
     *
     * @param userRegisterInfoDto 用户注册的基本信息
     * @return 返回注册之后的用户信息
     */
    @Override
    public UserInfoResponse register(UserRegisterInfoDto userRegisterInfoDto) {
        if (UserLoginType.PHONE.getCode().equals(userRegisterInfoDto.getAccountType())){
            return userPhoneRegisterHandler.register(userRegisterInfoDto);
        }else if (UserLoginType.WEIXIN.getCode().equals(userRegisterInfoDto.getAccountType())){
            return userThirdRegisterHandler.register(userRegisterInfoDto);
        }
        return userAccountRegisterHandler.register(userRegisterInfoDto);
    }
}
