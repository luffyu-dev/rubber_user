package com.rubber.user.service.constant;

import com.rubber.common.utils.result.code.ICodeHandle;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public enum  ErrCodeEnums implements ICodeHandle {

    /**
     *     SUCCESS("1000000", "成功"),
     *     SYSTEM_ERROR("2000000", "系统错误"),
     *     PARAM_ERROR("3000000", "参数错误");
     *     1开头表示业务正常
     *     2开头表示系统异常
     *     3开头表示用户入参异常
     *     4开头表示逻辑上异常
     *
     *     第5位表示是业务code，用户服务业务是1
     *     第3位表示是具体的业务类型 其中 1表示注册  2表示登录  3表示查询
     *
     */
    PARAM_ERROR("3010000", "必要参数不能为空"),

    REGISTER_USER_EXIST("3010100", "当中账户已存在"),

    ACCOUNT_NOT_FOUNT("3010200", "账户不存在"),
    PWD_IS_ERROR("3010201", "密码错误"),
    REGISTER_ERROR("3010100", "注册失败"),



    ;

    ErrCodeEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
