package com.jeecode.core.result;

/**
 * 默认的JSON结果体
 */
public class SimpleResultBody {
    // 结果编码
    private int errorCode = 0;
    // 结果信息
    private String message;
    // 结果集
    private Object result;

    public int getErrorCode() {
        return errorCode;
    }

    public SimpleResultBody setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SimpleResultBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public SimpleResultBody setResult(Object result) {
        this.result = result;
        return this;
    }
}
