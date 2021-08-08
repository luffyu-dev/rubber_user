package com.rubber.user.service.exception;

import com.rubber.common.utils.result.code.ICodeHandle;
import com.rubber.common.utils.result.exception.BaseResultRunTimeException;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public class UserRegisterException extends BaseResultRunTimeException {


    public UserRegisterException(String msg) {
        super(msg);
    }

    public UserRegisterException(ICodeHandle handle) {
        super(handle);
    }

    public UserRegisterException(ICodeHandle handle, Object data) {
        super(handle, data);
    }
}
