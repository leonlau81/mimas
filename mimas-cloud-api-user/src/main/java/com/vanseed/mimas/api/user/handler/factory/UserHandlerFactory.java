package com.vanseed.mimas.api.user.handler.factory;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import com.vanseed.mimas.api.user.handler.user.UserInfoHandler;
import com.vanseed.mimas.api.user.handler.user.UserRegisterHandler;
import com.vanseed.mimas.common.support.handler.BaseHandler;
import com.vanseed.mimas.common.support.handler.BaseHandlerFactory;

import com.vanseed.mimas.common.support.mvc.RequestHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author leon
 *
 */
@Component
public class UserHandlerFactory extends BaseHandlerFactory {
		
	@Autowired
	private UserRegisterHandler userRegisterHandler;
	
	@Autowired
	private UserInfoHandler userInfoHandler;
	
	private SortedMap<String, BaseHandler> userRegisterHandlerMap = new TreeMap<String, BaseHandler>(super.versionComparator);
	private SortedMap<String, BaseHandler> userInfoHandlerMap = new TreeMap<String, BaseHandler>(super.versionComparator);

	@PostConstruct
	public void init(){
		userRegisterHandlerMap.put(userRegisterHandler.getVersion(), userRegisterHandler);

        userInfoHandlerMap.put(userInfoHandler.getVersion(), userInfoHandler);
	}
	
	//获取用户注册处理器
	public BaseHandler  getUserRegisterHandler(RequestHeader requestHeader){
		return getServiceHandler(requestHeader, userRegisterHandlerMap);
	}
	
	//获取保存用户信息处理器
	public BaseHandler getUserInfoHandler(RequestHeader requestHeader){
		return getServiceHandler(requestHeader, userInfoHandlerMap);
	}

}
