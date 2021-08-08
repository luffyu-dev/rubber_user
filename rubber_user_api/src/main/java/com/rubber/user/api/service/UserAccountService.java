package com.rubber.user.api.service;


import com.rubber.common.utils.result.ResultMsg;
import com.rubber.user.api.service.dto.UserChangePasswordDto;
import com.rubber.user.api.service.response.UserInfoResponse;

/**
 * @author luffyu
 * Created on 2021/8/7
 */
public interface UserAccountService {

    /**
     * 修改密码的接口
     * @param userChangePasswordDto 修改密码的入参
     * @return 返回入参信息
     */
    ResultMsg changePassword(UserChangePasswordDto userChangePasswordDto);

    /**
     * 通过uid查询信息的接口
     * @param uid 当前的uid
     * @return 返回用户的基本信息
     */
    UserInfoResponse queryByInfo(int uid);

}
