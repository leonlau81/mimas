package com.vanseed.mimas.api.acct.handler.factory;

import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import com.vanseed.mimas.api.acct.handler.acct.AcctInfoHandler;
import com.vanseed.mimas.api.acct.handler.acct.AcctOpenHandler;
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
public class AcctHandlerFactory extends BaseHandlerFactory {
		
	@Autowired
	private AcctOpenHandler acctOpenHandler;
	
	@Autowired
	private AcctInfoHandler acctInfoHandler;
	
	private SortedMap<String, BaseHandler> acctOpenHandlerMap = new TreeMap<String, BaseHandler>(super.versionComparator);
	private SortedMap<String, BaseHandler> acctInfoHandlerMap = new TreeMap<String, BaseHandler>(super.versionComparator);

	@PostConstruct
	public void init(){
        acctOpenHandlerMap.put(acctOpenHandler.getVersion(), acctOpenHandler);

        acctInfoHandlerMap.put(acctInfoHandler.getVersion(), acctInfoHandler);
	}
	
	//获取用户注册处理器
	public BaseHandler  getAcctOpenHandler(RequestHeader requestHeader){
		return getServiceHandler(requestHeader, acctOpenHandlerMap);
	}
	
	//获取保存用户信息处理器
	public BaseHandler getAcctInfoHandler(RequestHeader requestHeader){
		return getServiceHandler(requestHeader, acctInfoHandlerMap);
	}

}
