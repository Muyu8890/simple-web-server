package com.github.likeabook.webserver.exception;

public enum CoreExceptionEnum {

    // business
    CODE_11(""),

    // error
    CODE_81("获取对象属性异常"),
    CODE_82("设置集合异常"),
    CODE_83("{0}不是集合属性"),
    CODE_84("设置id值异常"),
    ;

    public String message;
    CoreExceptionEnum(String message) {
        this.message = message;
    }

}
