package com.rubber.user.api.service.request;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Data
public class UserLoginRequest {

    /**
     * 用户登录类型 0表示账号密码登录 1表示手机验短登录 2表示微信扫码登录
     */
    private int accountType;

    /**
     * 用户登录账号
     */
    private String userAccount;

    /**
     * 用户手机号
     */
    private String userPhone;


    /**
     * 第三方登录账户id
     */
    private String thirdAccountId;

    /**
     * 第三方登录账户相关密钥信息
     */
    private String thirdAccountKey;


    /**
     * 用户邮箱
     */
    private String userEmail;


    /**
     * 账户密码
     */
    private String accountPwd;


    /**
     * 登录密钥
     */
    private String loginCipher;







}
