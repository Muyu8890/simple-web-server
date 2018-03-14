package com.github.likeabook.webserver.result;

import com.github.likeabook.webserver.exception.BaseException;


public class ResultBodyUtils {
    public static SimpleResultBody getSuccessSimpleResultBody() {
        return getSuccessSimpleResultBody(null);
    }
    public static SimpleResultBody getSuccessSimpleResultBody(Object result){
        SimpleResultBody resultBody = new SimpleResultBody();
        resultBody.setErrorCode(0);
        resultBody.setMessage("操作成功");
        resultBody.setResult(result);
        return resultBody;
    }

    public static MoreResultBody getSuccessMoreResultBody(Object result, Object moreResult){
        MoreResultBody resultBody = new MoreResultBody();
        resultBody.setErrorCode(0);
        resultBody.setMessage("操作成功");
        resultBody.setResult(result);
        resultBody.setMoreResult(moreResult);
        return resultBody;
    }


    public static SimpleResultBody getErrorSimpleResultBody() {
        return getErrorSimpleResultBody(null);
    }

    public static SimpleResultBody getErrorSimpleResultBody(BaseException baseException){
        SimpleResultBody resultBody = new SimpleResultBody();
        if (baseException != null) {
            resultBody.setErrorCode(baseException.getErrorCode());
            resultBody.setMessage(baseException.getErrorMessage());
            resultBody.setResult(baseException.getErrorResult());
        } else {
            resultBody.setErrorCode(1);
            resultBody.setMessage("系统异常");
        }
        return resultBody;
    }

}
