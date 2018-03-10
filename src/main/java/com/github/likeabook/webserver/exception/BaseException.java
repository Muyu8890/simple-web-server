package com.github.likeabook.webserver.exception;

/**
 * Created by 刘少年 on 2016/11/23.
 */
public interface BaseException {

    Enum getErrorEnum();

    BaseException setErrorEnum(Enum errorEnum);

    int getErrorCode();

    BaseException setErrorCode(int errorCode);

    String getErrorMessage();

    BaseException setErrorMessage(String errorMessage);

    Object getErrorResult();

    BaseException setErrorResult(Object errorResult);

//    Exception getE();
//
//    BaseException setE(Exception e);
}
