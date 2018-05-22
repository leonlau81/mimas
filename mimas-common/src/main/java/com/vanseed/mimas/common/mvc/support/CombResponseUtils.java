/**
 * 
 */
package com.vanseed.mimas.common.mvc.support;

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
public class CombResponseUtils {
	
	public static final int ERROR = 500;
	public static final int NOTLOGIN = 401;
	public static final int UNAUTHORIZED = 403;
	
	public static final String ERROR_MESSAGE = "操作失败！";
	public static final String ERROR_NOTLOGIN = "没有登录！";
	public static final String ERROR_UNAUTHORIZED = "没有权限！";
	
	public static final String DEFAULT_SUCCESS_MSG_CODE = "web.common.operate.success";
	public static final String DEFAULT_FAIL_MSG_CODE = "web.common.operate.fail";
	
	public static CombResponse getErrorRespose() {
		return getErrorRespose("");
	}
	
	public static CombResponse getErrorRespose(String code) {
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new CombResponse(ERROR, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new CombResponse(ERROR, ERROR_MESSAGE);
		}
	}
	
	public static CombResponse getErrorRespose(String code, Object[] args) {
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new CombResponse(ERROR, MessageSourceUtils.getMessage(code,args,locale));
		}else{
			return new CombResponse(ERROR, ERROR_MESSAGE);
		}
	}
	
	public static CombResponse getResponseNotLogin(String code){
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new CombResponse(NOTLOGIN, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new CombResponse(NOTLOGIN, ERROR_NOTLOGIN);
		}
	}
	
	public static CombResponse getResponseUnauthorized(String code){
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new CombResponse(UNAUTHORIZED, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new CombResponse(UNAUTHORIZED, ERROR_UNAUTHORIZED);
		}
	}
	
	public static CombResponse getErrorRespose(ServiceException exp) {
		if(StringUtils.hasLength(exp.getErrorCode())) {
			return getErrorRespose(exp.getErrorCode());
		} else {
			return new CombResponse(ERROR, exp.getMessage());
		}
	}
	
	public static CombResponse getSuccessRespose(Object obj) {
		String memo = "";
		try{
			memo = MessageSourceUtils.getMessage(DEFAULT_SUCCESS_MSG_CODE, LocaleUtil.toLocale(ThreadLocalUtils.getLanguage()));			
		}catch(Exception e){
			memo = DEFAULT_SUCCESS_MSG_CODE;
		}
		CombResponse combResponse = CombResponse.newSuccess(obj);
		combResponse.setMemo(memo);
		return combResponse;
	}
}
