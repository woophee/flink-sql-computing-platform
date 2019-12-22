package com.woophee.platform.server.master.exception;

import com.woophee.platform.server.master.common.ErrorType;

public class SQLException extends ServiceException{
    private static final long serialVersionUID = -1127705551332192576L;

    public SQLException() {
        super();
    }

    public SQLException(String message) {
        super(message);
    }

    public SQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLException(Throwable cause) {
        super(cause);
    }

    public ErrorType getErrorType() {
        return ErrorType.SERVICE_ERROR;
    }
}
