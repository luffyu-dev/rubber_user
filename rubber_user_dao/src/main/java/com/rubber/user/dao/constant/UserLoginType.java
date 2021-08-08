package com.rubber.user.dao.constant;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public enum  UserLoginType {

    /**
     * 0表示账号密码登录 1表示手机验短登录 2表示微信扫码登录
     */
    ACCOUNT("account",0,"账号密码登录"),
    PHONE("phone",1,"手机号码登录"),
    EMAIL("email",2,"微信登录"),
    WEIXIN("wx",3,"微信登录")

    ;


    UserLoginType(String key, Integer code, String label) {
        this.key = key;
        this.code = code;
        this.label = label;
    }

    private String key;

    private Integer code;

    private String label;

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }


    public String getKey() {
        return key;
    }
}
