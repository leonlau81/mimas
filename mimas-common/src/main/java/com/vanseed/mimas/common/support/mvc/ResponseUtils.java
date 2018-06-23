/**
 * 
 */
package com.vanseed.mimas.common.support.mvc;

import java.util.Locale;

import org.springframework.util.StringUtils;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.utils.LocaleUtil;
import com.vanseed.mimas.common.utils.MessageSourceUtils;
import com.vanseed.mimas.common.utils.ThreadLocalUtils;


/**
 * @author leon
 *
 */
public class ResponseUtils {
	
	public static final int ERROR = 500;
	public static final int NOTLOGIN = 401;
	public static final int UNAUTHORIZED = 403;
	
	public static final String ERROR_MESSAGE = "操作失败！";
	public static final String ERROR_NOTLOGIN = "没有登录！";
	public static final String ERROR_UNAUTHORIZED = "没有权限！";
	
	public static final String DEFAULT_SUCCESS_MSG_CODE = "web.common.operate.success";
	public static final String DEFAULT_FAIL_MSG_CODE = "web.common.operate.fail";
	
	public static Response getErrorRespose() {
		return getErrorRespose("");
	}
	
	public static Response getErrorRespose(String code) {
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new Response(ERROR, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new Response(ERROR, ERROR_MESSAGE);
		}
	}
	
	public static Response getErrorRespose(String code, Object[] args) {
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new Response(ERROR, MessageSourceUtils.getMessage(code,args,locale));
		}else{
			return new Response(ERROR, ERROR_MESSAGE);
		}
	}
	
	public static Response getResponseNotLogin(String code){
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new Response(NOTLOGIN, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new Response(NOTLOGIN, ERROR_NOTLOGIN);
		}
	}
	
	public static Response getResponseUnauthorized(String code){
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new Response(UNAUTHORIZED, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new Response(UNAUTHORIZED, ERROR_UNAUTHORIZED);
		}
	}
	
	public static Response getErrorRespose(ServiceException exp) {
		if(StringUtils.hasLength(exp.getErrorCode())) {
			return getErrorRespose(exp.getErrorCode());
		} else {
			return new Response(ERROR, exp.getMessage());
		}
	}
	
	public static Response getSuccessRespose(Object obj) {
		String memo = "";
		try{
			memo = MessageSourceUtils.getMessage(DEFAULT_SUCCESS_MSG_CODE, LocaleUtil.toLocale(ThreadLocalUtils.getLanguage()));			
		}catch(Exception e){
			memo = DEFAULT_SUCCESS_MSG_CODE;
		}
		Response response = Response.newSuccess(obj);
		response.setMemo(memo);
		return response;
	}
}
