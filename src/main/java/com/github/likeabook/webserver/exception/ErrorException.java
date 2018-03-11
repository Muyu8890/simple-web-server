package com.github.likeabook.webserver.exception;

/**
 * @author 刘加胜
 * 2015年10月12日
 */
public class ErrorException extends RuntimeException implements BaseException {
    private Enum errorEnum;
    private int errorCode = 2;
    private String errorMessage = "系统异常";
    private Object errorResult;

    public ErrorException(){
    }
    public ErrorException(Enum errorEnum){
        ExceptionUtils.setExceptionParam(this, errorEnum);
    }

    public ErrorException(Enum errorEnum, Exception e){
        super(e);
        ExceptionUtils.setExceptionParam(this, errorEnum);
    }
    public ErrorException(Enum errorEnum, Object errorResult){
        ExceptionUtils.setExceptionParam(this, errorEnum, errorResult);
    }
    public ErrorException(Enum errorEnum, Exception e, Object errorResult){
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
    public ErrorException setErrorEnum(Enum errorEnum) {
        this.errorEnum = errorEnum;
        return this;
    }

    @Override
    public ErrorException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @Override
    public ErrorException setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public ErrorException setErrorResult(Object errorResult) {
        this.errorResult = errorResult;
        return this;
    }

//    @Override
//    public ErrorException setE(Exception e) {
//        this.e = e;
//        return this;
//    }
}
