package com.github.likeabook.webserver.controller;

import com.github.likeabook.webserver.result.MoreResultBody;
import com.github.likeabook.webserver.result.ResultBodyUtils;
import com.github.likeabook.webserver.result.SimpleResultBody;
import org.apache.log4j.Logger;

public class BaseController {

	public static Logger logger = Logger.getLogger(BaseController.class);

	public SimpleResultBody result(){
		return ResultBodyUtils.getSuccessSimpleResultBody();
	}
	public SimpleResultBody result(Object result){
		return ResultBodyUtils.getSuccessSimpleResultBody(result);
	}
	public MoreResultBody result(Object result, Object moreResult){
		return ResultBodyUtils.getSuccessMoreResultBody(result, moreResult);
	}

}
