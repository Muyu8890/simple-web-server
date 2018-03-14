package com.github.likeabook.webserver.result;


public class MoreResultBody extends SimpleResultBody{

    private Object moreResult;

    public Object getMoreResult() {
        return moreResult;
    }

    public MoreResultBody setMoreResult(Object moreResult) {
        this.moreResult = moreResult;
        return this;
    }
}
