package com.github.likeabook.webserver.exception;



public interface BaseException {

    Enum getErrorEnum();

    BaseException setErrorEnum(Enum errorEnum);

    int getErrorCode();

    BaseException setErrorCode(int errorCode);

    String getErrorMessage();

    BaseException setErrorMessage(String errorMessage);

    Object getErrorResult();

    BaseException setErrorResult(Object errorResult);

}
