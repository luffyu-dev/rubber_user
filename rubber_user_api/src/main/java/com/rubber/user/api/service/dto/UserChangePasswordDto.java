package com.rubber.user.api.service.dto;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Data
public class UserChangePasswordDto {
    /**
     * 用户登录账号
     */
    private String userAccount;

    /**
     * 老密码
     */
    private String newPassword;

    /**
     * 新密码
     */
    private String oldPassword;

}
