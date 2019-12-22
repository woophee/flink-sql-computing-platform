package com.woophee.platform.server.master.common;

public enum ErrorType {
    NOT_LOGIN("No login"),
    AUTHORITY_ERROR("Authority error"),
    PARAM_ERROR("Parameter error"),
    SERVICE_ERROR("Service error"),
    SQL_ERROR("SQL error"),
    NO_DATA("No data"),
    UNKNOWN_ERROR("Unknown error");

    private String errorMessage;

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
