package com.rubber.user.service.session.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rubber.base.components.redis.RedisClientProxy;
import com.rubber.base.components.util.dispatch.bean.DispatchConfig;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.request.UserSession;
import com.rubber.user.api.service.response.UserLoginResponse;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.constant.SessionLoginType;
import com.rubber.user.service.exception.UserRegisterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author luffyu
 * Created on 2021/10/21
 */
@Slf4j
@Service
@DispatchConfig(key = SessionLoginType.COOKIE,type = UserSessionHandler.class)
public class CookieUserSessionHandler implements UserSessionHandler  {

    private static final int TIME_OUT = 60 * 60 * 24 * 10;

    @Autowired
    private RedisClientProxy redisClientProxy;

    /**
     * 创建登录的session
     *
     * @param request
     */
    @Override
    public String createSession(UserLoginRequest request) {
        String sessionKey = IdUtil.fastSimpleUUID();
        redisClientProxy.set(sessionKey, JSON.toJSONString(request),TIME_OUT);
        return sessionKey;
    }

    /**
     * @param key
     * @return
     */
    @Override
    public UserSession checkSession(String key) {
        String value = redisClientProxy.get(key);
        if (StrUtil.isEmpty(value)){
            throw new UserRegisterException(ErrCodeEnums.LOGIN_FAIL);
        }
        UserLoginResponse response = JSON.parseObject(value,UserLoginResponse.class);
        if (response == null || response.getUid() == null){
            throw new UserRegisterException(ErrCodeEnums.LOGIN_FAIL);
        }
        UserSession userSession = new UserSession();
        userSession.setUid(response.getUid());
        return userSession;
    }
}
