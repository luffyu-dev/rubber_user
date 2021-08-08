package com.rubber.user.web.starter.controller;

import com.rubber.common.utils.result.ResultMsg;
import com.rubber.user.api.service.UserLoginService;
import com.rubber.user.api.service.UserRegisterService;
import com.rubber.user.api.service.dto.UserAccountInfoDto;
import com.rubber.user.api.service.dto.UserRegisterInfoDto;
import com.rubber.user.api.service.response.UserInfoResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserLoginService userLoginService;

    @Resource
    private UserRegisterService userRegisterService;


    @PostMapping("/login")
    public ResultMsg login(UserAccountInfoDto userAccountInfoDto){
        UserInfoResponse userInfoResponse = userLoginService.login(userAccountInfoDto);
        return ResultMsg.success(userInfoResponse);
    }


    @PostMapping("/register")
    public ResultMsg login(UserRegisterInfoDto registerInfoDto){
        UserInfoResponse userInfoResponse = userRegisterService.register(registerInfoDto);
        return ResultMsg.success(userInfoResponse);
    }

}
