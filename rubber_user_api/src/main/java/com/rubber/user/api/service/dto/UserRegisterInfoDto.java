package com.rubber.user.api.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Data
public class UserRegisterInfoDto extends  UserAccountInfoDto{

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 用户简介
     */
    private String userMotto;

    /**
     * 用户头像地址
     */
    private String userAvatar;

    /**
     * 0表示男 1表示女 2表示未知
     */
    private Integer userSex;


    /**
     * 生日
     */
    private LocalDateTime userBirthday;

    /**
     * 用户地区
     */
    private String userArea;

    /**
     * 用户地址
     */
    private String userAddress;


}
