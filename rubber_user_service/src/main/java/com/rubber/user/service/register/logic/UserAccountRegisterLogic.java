package com.rubber.user.service.register.logic;

import cn.hutool.core.util.StrUtil;
import com.rubber.user.api.service.dto.UserRegisterInfoDto;
import com.rubber.user.api.service.request.UserLoginRequest;
import com.rubber.user.api.service.response.UserInfoResponse;
import com.rubber.user.dao.constant.UserLoginType;
import com.rubber.user.dao.constant.UserStatusEnums;
import com.rubber.user.dao.entity.UserAccountInfo;
import com.rubber.user.dao.entity.UserBasicInfo;
import com.rubber.user.dao.logic.UserAccountInfoLogic;
import com.rubber.user.dao.logic.UserBasicInfoLogic;
import com.rubber.user.service.constant.ErrCodeEnums;
import com.rubber.user.service.encrypt.IEncryptHandler;
import com.rubber.user.service.exception.UserRegisterException;
import com.rubber.user.service.info.logic.QueryUserInfoLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
@Slf4j
@Component
public class UserAccountRegisterLogic  {


    @Resource
    private UserAccountInfoLogic userAccountInfoLogic;

    @Resource
    private UserBasicInfoLogic userBasicInfoLogic;

    @Autowired
    private QueryUserInfoLogic queryUserInfoLogic;


    @Resource(name = "encryptProvider")
    private IEncryptHandler iEncryptHandler;

    /**
     * 注册接口
     *
     * @param userRegisterInfoDto 用户注册的基本信息
     * @return 返回注册之后的用户信息
     */
    @Transactional(
            rollbackFor = Throwable.class
    )
    @Deprecated
    public UserInfoResponse addUser(UserRegisterInfoDto userRegisterInfoDto) {
        //验证基本的数据信息
        verifyRequestParam(userRegisterInfoDto);
        //逻辑验证
        verifyLogicData(userRegisterInfoDto);
        //初始化账户
        UserAccountInfo accountInfo = initRegisterAccount(userRegisterInfoDto);
        if (userAccountInfoLogic.addUserAccount(accountInfo)){
            //初始化用户基础信息
            UserBasicInfo userBasicInfo = initRegisterBasicInfo(accountInfo, userRegisterInfoDto);
            userBasicInfoLogic.addUserBasic(userBasicInfo);
        }
        //保存成功之后的操作
        return queryUserInfoLogic.getUserInfoByUid(accountInfo.getUid());
    }



    @Transactional(
            rollbackFor = Throwable.class
    )
    public UserAccountInfo register(UserLoginRequest loginRequest) {
        //验证基本的数据信息
        verifyRequestParam(loginRequest);
        //逻辑验证
        verifyLogicData(loginRequest);
        //初始化账户
        UserAccountInfo accountInfo = initRegisterAccount(loginRequest);
        if (userAccountInfoLogic.addUserAccount(accountInfo)){
            //初始化用户基础信息
           return accountInfo;
        }
        //保存成功之后的操作
        return null;
    }

    /**
     * 必要的参数验证
     */
    private void verifyRequestParam(UserLoginRequest loginRequest){
        if (StrUtil.isEmpty(loginRequest.getUserAccount()) || StrUtil.isEmpty(loginRequest.getAccountPwd())){
            log.error("注册的时候用户账户和密码异常");
            throw new UserRegisterException(ErrCodeEnums.PARAM_ERROR);
        }

    }

    /**
     * 逻辑数据验证
     */
    private void verifyLogicData(UserLoginRequest loginRequest){
        Integer uid = userAccountInfoLogic.getUidByAccount(loginRequest.getUserAccount());
        if (uid != null) {
            //表示账户已经存在
            throw new UserRegisterException(ErrCodeEnums.REGISTER_USER_EXIST);
        }
    }

    /**
     * 初始化注册的对象
     */
    private UserAccountInfo initRegisterAccount(UserLoginRequest userRegisterInfoDto){
        UserAccountInfo userAccountInfo = new UserAccountInfo();
        BeanUtils.copyProperties(userRegisterInfoDto,userAccountInfo);
        userAccountInfo.setUserSalt(iEncryptHandler.createSalt(6));
        userAccountInfo.setUserPassword(iEncryptHandler.encrypt(userRegisterInfoDto.getAccountPwd(),userAccountInfo.getUserSalt()));
        userAccountInfo.setAccountType(UserLoginType.ACCOUNT.getCode());
        userAccountInfo.setUserStatus(UserStatusEnums.NORMAL.getUserStatus());
        return userAccountInfo;
    }


    /**
     * 初始化注册的基本信息
     */
    private UserBasicInfo initRegisterBasicInfo(UserAccountInfo accountInfo,UserRegisterInfoDto userRegisterInfoDto){
        UserBasicInfo userBasicInfo = new UserBasicInfo();
        BeanUtils.copyProperties(userRegisterInfoDto,userBasicInfo);
        userBasicInfo.setUid(accountInfo.getUid());
        return userBasicInfo;
    }

}
