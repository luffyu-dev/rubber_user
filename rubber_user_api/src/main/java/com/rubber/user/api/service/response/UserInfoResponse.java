package com.rubber.user.api.service.response;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
@Data
public class UserInfoResponse {

    /**
     * 外表关联的uid
     */
    private Integer uid;

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
     * 当前的登录态
     */
    private String loginToken;
}
