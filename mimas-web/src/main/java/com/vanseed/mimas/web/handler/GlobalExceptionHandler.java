package com.vanseed.mimas.web.handler;

import javax.servlet.http.HttpServletRequest;

import com.vanseed.mimas.common.support.mvc.Response;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.support.mvc.ResponseUtils;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Response serviceExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
		Response response = ResponseUtils.getErrorRespose(se);
        return response;
    }
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		Response response = ResponseUtils.getErrorRespose();
        return response;
    }
	
	//shiro未认证异常（未登录）
	@ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseBody
    public Response unauthenticatedExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
		Response response = ResponseUtils.getResponseNotLogin("");
        return response;
    }
	
	//shiro没有权限异常（未授权）
	@ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Response UnauthorizedExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
		Response response = ResponseUtils.getErrorRespose(se);
        return response;
    }
}
