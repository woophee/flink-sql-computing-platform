package com.woophee.platform.server.master.exception;

import com.woophee.platform.server.master.common.ErrorType;

public class ParamException extends ServiceException{
    private static final long serialVersionUID = -6794536781472018193L;

    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ErrorType getErrorType() {
        return ErrorType.PARAM_ERROR;
    }
}
