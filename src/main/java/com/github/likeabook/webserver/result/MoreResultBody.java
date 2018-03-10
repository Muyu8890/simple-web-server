package com.github.likeabook.webserver.result;

/**
 * 默认的JSON结果体
 */
public class MoreResultBody extends SimpleResultBody{
    // 更多结果集
    /**
     * 适用于不破坏分页结构，同时又想返回更多信息情况
     */
    private Object moreResult;

    public Object getMoreResult() {
        return moreResult;
    }

    public MoreResultBody setMoreResult(Object moreResult) {
        this.moreResult = moreResult;
        return this;
    }
}
