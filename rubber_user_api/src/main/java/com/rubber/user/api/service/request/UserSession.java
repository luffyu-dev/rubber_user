package com.rubber.user.api.service.request;

import lombok.Data;

/**
 * @author luffyu
 * Created on 2021/10/21
 */
@Data
public class UserSession {

    /**
     * 用户的sessionId
     */
    private String sessionId;

    /**
     * 当前登录态
     */
    private String token;

    /**
     * 当前的uid
     */
    private Integer uid;


}
