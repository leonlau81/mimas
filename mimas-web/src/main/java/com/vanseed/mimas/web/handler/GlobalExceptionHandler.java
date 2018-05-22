package com.vanseed.mimas.web.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.mvc.support.CombResponse;
import com.vanseed.mimas.common.mvc.support.CombResponseUtils;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public CombResponse serviceExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
		CombResponse response = CombResponseUtils.getErrorRespose(se);       
        return response;
    }
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CombResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		CombResponse response = CombResponseUtils.getErrorRespose();
        return response;
    }
	
	//shiro未认证异常（未登录）
	@ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseBody
    public CombResponse unauthenticatedExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
		CombResponse response = CombResponseUtils.getResponseNotLogin("");       
        return response;
    }
	
	//shiro没有权限异常（未授权）
	@ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public CombResponse UnauthorizedExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
		CombResponse response = CombResponseUtils.getErrorRespose(se);       
        return response;
    }
}
