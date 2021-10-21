package com.rubber.user.api.service.response;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2021/10/21
 */
@Data
public class UserLoginResponse {


    /**
     * 当前登录的uid
     */
    private Integer uid;


    /**
     * 登录的账户
     */
    private String loginAccount;


    /**
     * sessionKey值
     */
    private String sessionKey;


}
