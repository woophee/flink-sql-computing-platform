package com.woophee.platform.server.master.exception;

import com.woophee.platform.server.master.common.ErrorType;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 6853254200083478951L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ErrorType getErrorType() {
        return ErrorType.SERVICE_ERROR;
    }
}
