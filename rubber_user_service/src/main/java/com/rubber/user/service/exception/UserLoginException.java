package com.rubber.user.service.exception;

import com.rubber.common.utils.result.code.ICodeHandle;
import com.rubber.common.utils.result.exception.BaseResultRunTimeException;

/**
 * @author luffyu
 * Created on 2021/8/8
 */
public class UserLoginException extends BaseResultRunTimeException {



    public UserLoginException(ICodeHandle handle, Object data) {
        super(handle, data);
    }

    public UserLoginException(ICodeHandle handle) {
        super(handle);
    }
}
