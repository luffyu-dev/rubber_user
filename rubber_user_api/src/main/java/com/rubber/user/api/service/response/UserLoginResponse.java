package com.rubber.user.api.service.response;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2021/10/21
 */
@Data
public class UserLoginResponse {

    /**
     * 登录成功的sessionId
     */
    private String sessionId;

    /**
     * 登录成功的token
     */
    private String token;


    private Integer uid;


}
