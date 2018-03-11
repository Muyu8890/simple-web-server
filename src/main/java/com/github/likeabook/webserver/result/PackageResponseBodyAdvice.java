package com.github.likeabook.webserver.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.likeabook.webserver.exception.BaseException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;


@Order(2147483647)
@ControllerAdvice
public class PackageResponseBodyAdvice implements ResponseBodyAdvice<Object> {
	public static Logger logger = Logger.getLogger(PackageResponseBodyAdvice.class);

	@Value("${jeecode.packageResponseBody:true}")
	boolean packageResponseBody;

	@ExceptionHandler
	@PackageResponseBody(false)
	public void handleExceptions(final Exception e, HandlerMethod handlerMethod, HttpServletResponse response) throws Exception {
		e.printStackTrace();
		// 判断类配置(默认true)
		PackageResponseBody classPackageResponseBody = handlerMethod.getBeanType().getAnnotation(PackageResponseBody.class);
		if (classPackageResponseBody != null && !classPackageResponseBody.value()) {
			throw e;
		}
		// 判断方法配置(默认true)
		PackageResponseBody methodPackageResponseBody = handlerMethod.getMethod().getAnnotation(PackageResponseBody.class);
		if (methodPackageResponseBody != null && !methodPackageResponseBody.value()) {
			throw e;
		}

		SimpleResultBody resultBody;
		if (e instanceof BaseException){
			resultBody = ResultBodyUtils.getErrorSimpleResultBody((BaseException)e);
		} else{
			resultBody = ResultBodyUtils.getErrorSimpleResultBody();
		}
		response.addHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(resultBody));
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// 判断全局配置(默认true)
		if (!packageResponseBody) {
			return false;
		}
		// 判断类配置(默认true)
		PackageResponseBody classPackageResponseBody = returnType.getDeclaringClass().getAnnotation(PackageResponseBody.class);
		if (classPackageResponseBody != null && !classPackageResponseBody.value()) {
			return false;
		}
		// 判断方法配置(默认true)
		PackageResponseBody methodPackageResponseBody = returnType.getMethod().getAnnotation(PackageResponseBody.class);
		if (methodPackageResponseBody != null && !methodPackageResponseBody.value()) {
			return false;
		}

		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
								  Class<? extends HttpMessageConverter<?>> selectedConverterType,
								  ServerHttpRequest request, ServerHttpResponse response) {
		SimpleResultBody resultBody;

		if (body instanceof SimpleResultBody){
			resultBody = (SimpleResultBody)body;
		} else {
			resultBody = ResultBodyUtils.getSuccessSimpleResultBody(body);
		}
		if (body instanceof String || returnType.getMethod().getReturnType().equals(String.class)){
			response.getHeaders().add("Content-type", "text/html;charset=UTF-8");
			return JSON.toJSONString(resultBody, SerializerFeature.WriteMapNullValue );
		}
		return resultBody;
	}
}
