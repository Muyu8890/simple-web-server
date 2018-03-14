package com.github.likeabook.webserver.exception;


public class BusinessRuntimeException extends RuntimeException implements BaseException {

    private Enum errorEnum;
    private int errorCode;
    private String errorMessage;
    private Object errorResult;

    public BusinessRuntimeException(Enum errorEnum){
        ExceptionUtils.setExceptionParam(this, errorEnum);
    }

    public BusinessRuntimeException(Enum errorEnum, Exception e){
        super(e);
        ExceptionUtils.setExceptionParam(this, errorEnum, e);
    }
    public BusinessRuntimeException(Enum errorEnum, Object errorResult){
        ExceptionUtils.setExceptionParam(this, errorEnum, errorResult);
    }
    public BusinessRuntimeException(Enum errorEnum, Exception e, Object errorResult){
        super(e);
        ExceptionUtils.setExceptionParam(this, errorEnum, errorResult);
    }


    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public Object getErrorResult() {
        return errorResult;
    }

//    @Override
//    public Exception getE() {
//        return e;
//    }


    @Override
    public Enum getErrorEnum() {
        return errorEnum;
    }

    @Override
    public BusinessRuntimeException setErrorEnum(Enum errorEnum) {
        this.errorEnum = errorEnum;
        return this;
    }

    @Override
    public BusinessRuntimeException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @Override
    public BusinessRuntimeException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public BusinessRuntimeException setErrorResult(Object errorResult) {
        this.errorResult = errorResult;
        return this;
    }

//    @Override
//    public BusinessException setE(Exception e) {
//        this.e = e;
//        return this;
//    }
}
