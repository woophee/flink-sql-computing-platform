package com.woophee.platform.server.master.common;

import com.alibaba.fastjson.JSON;
import com.woophee.platform.server.master.exception.ServiceException;

import java.io.Serializable;

public class RestResult<T> implements Serializable {
    private static final long serialVersionUID = 4690098495552657153L;

    private boolean state = true;
    private T data;
    private ErrorType errorType;
    private String errorMessage;
    private String errorStack;

    public RestResult(){
    }

    public RestResult(boolean state){
        this.state = state;
    }

    public static <T> RestResult<T> succeed(T data){
        RestResult<T> restResult = new RestResult<>(true);
        restResult.setData(data);
        return restResult;
    }

    public static <T> RestResult<T> failed(Exception e){
        RestResult<T> restResult = new RestResult<>(false);
        if(e instanceof ServiceException){
            restResult.setErrorType(((ServiceException) e).getErrorType());
        }else{
            restResult.setErrorType(ErrorType.UNKNOWN_ERROR);
        }
        restResult.setErrorMessage(e.getMessage());
        return restResult;
    }

    public static <T> RestResult<T> failed(ErrorType errorType, String errorMessage, String errorStack){
        RestResult<T> restResult = new RestResult<>(false);
        restResult.setErrorMessage(errorMessage);
        restResult.setErrorType(errorType);
        restResult.setErrorStack(errorStack);
        return restResult;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }
}
