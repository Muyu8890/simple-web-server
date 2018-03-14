package com.github.likeabook.webserver.exception;

public class ExceptionUtils {
    static void setExceptionParam(BaseException baseException, Enum errorEnum){
        baseException.setErrorCode(Integer.parseInt(errorEnum.name().split("_")[1]));
        try {
            baseException.setErrorMessage((String)errorEnum.getClass().getDeclaredField("message").get(errorEnum));
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
        }
    }
    static void setExceptionParam(BaseException baseException, Enum errorEnum, Object errorResult){
        setExceptionParam(baseException, errorEnum);
        baseException.setErrorResult(errorResult);
    }
}
