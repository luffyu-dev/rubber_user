package com.rubber.user.service.login.bean;

import com.rubber.user.service.constant.ErrCodeEnums;
import lombok.Data;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Data
public class UserLoginBean {
    /**
     * 登录成功之后的uid
     */
    private Integer uid;

    /**
     * 错误编码
     */
    private ErrCodeEnums errCode;


    public UserLoginBean(Integer uid, ErrCodeEnums errCode) {
        this.uid = uid;
        this.errCode = errCode;
    }


    public UserLoginBean(ErrCodeEnums errCode) {
        this.errCode = errCode;
    }

    public UserLoginBean() {
    }
}
