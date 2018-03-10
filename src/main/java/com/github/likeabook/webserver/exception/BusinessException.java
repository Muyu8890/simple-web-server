package com.github.likeabook.webserver.exception;

/**
 * 业务异常的基础类

 * @description

 * @author 刘加胜

 * 2015年10月12日

 */
public class BusinessException extends Exception implements BaseException {

    private Enum errorEnum;
    private int errorCode;
    private String errorMessage;
    private Object errorResult;
//    private Exception e;

    public BusinessException(Enum errorEnum){
        ExceptionUtils.setExceptionParam(this, errorEnum);
    }

    public BusinessException(Enum errorEnum, Exception e){
        super(e);
        ExceptionUtils.setExceptionParam(this, errorEnum, e);
    }
    public BusinessException(Enum errorEnum, Object errorResult){
        ExceptionUtils.setExceptionParam(this, errorEnum, errorResult);
    }
    public BusinessException(Enum errorEnum, Exception e, Object errorResult){
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
    public BusinessException setErrorEnum(Enum errorEnum) {
        this.errorEnum = errorEnum;
        return this;
    }

    @Override
    public BusinessException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @Override
    public BusinessException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public BusinessException setErrorResult(Object errorResult) {
        this.errorResult = errorResult;
        return this;
    }

//    @Override
//    public BusinessException setE(Exception e) {
//        this.e = e;
//        return this;
//    }
}
