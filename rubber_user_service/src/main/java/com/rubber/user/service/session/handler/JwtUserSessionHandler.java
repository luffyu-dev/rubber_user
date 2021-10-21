package com.rubber.user.service.session.handler;

import com.rubber.base.components.util.dispatch.bean.DispatchConfig;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.request.UserSession;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.constant.SessionLoginType;
import com.rubber.user.service.exception.UserRegisterException;
import com.rubber.user.service.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author luffyu
 * Created on 2021/10/21
 */
@Slf4j
@Service
@DispatchConfig(key = SessionLoginType.JWT,type = UserSessionHandler.class)
public class JwtUserSessionHandler implements UserSessionHandler {



    private static final int TIME_OUT = 60 * 60 * 24 * 10;


    private static final String SECRET_KEY = "NIoVjZPMsZC";

    /**
     * 创建登录的session
     *
     * @param request
     */
    @Override
    public String createSession(UserLoginRequest request) {
        long now = System.currentTimeMillis();
        HashMap<String,Object> map = new HashMap<>(4);
        map.put("v",1);
        map.put("uid",request.getUid());
        return JwtUtil.createJwtDefault(request.getUserAccount(), now, TIME_OUT,map, SECRET_KEY);
    }

    /**
     * @param sessionKey
     * @return
     */
    @Override
    public UserSession checkSession(String sessionKey) {
        try {
            Claims claims = JwtUtil.checkToken(sessionKey, SECRET_KEY);
            //登陆名称
            String subject = claims.getSubject();
            Integer version = Integer.valueOf(claims.get("v").toString());
            Integer uid = Integer.valueOf(claims.get("uid").toString());
            log.info("当前验证登录态成功 uid={},subject={},version={}",uid,subject,version);
            UserSession usr = new UserSession();
            usr.setUid(uid);
            return usr;
        }catch (ExpiredJwtException e){
            throw new UserRegisterException(ErrCodeEnums.LOGIN_FAIL);
        } catch (Exception e) {
            log.error("登录出现了异常信息-msg={}",e.getMessage());
            throw new UserRegisterException(ErrCodeEnums.LOGIN_FAIL);
        }
    }
}
