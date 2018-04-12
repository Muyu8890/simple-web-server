package com.github.likeabook.webserver.controller;

import com.github.likeabook.webserver.result.MoreResultBody;
import com.github.likeabook.webserver.result.ResultBodyUtils;
import com.github.likeabook.webserver.result.SimpleResultBody;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

	public static Logger logger = Logger.getLogger(BaseController.class);


	@Autowired
	public HttpServletRequest request;

	@Autowired
	public HttpServletResponse response;

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
