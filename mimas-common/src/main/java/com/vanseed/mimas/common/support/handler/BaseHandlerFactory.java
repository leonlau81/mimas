package com.vanseed.mimas.common.support.handler;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import com.vanseed.mimas.common.enums.ClientType;
import com.vanseed.mimas.common.support.mvc.RequestHeader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 * @author leon
 *
 */
@Component
public class BaseHandlerFactory {
	protected Comparator<String> versionComparator= new Comparator<String>(){
		public int compare(String s1, String s2) {          
	        if(s1.equalsIgnoreCase(s2)){
	        	return 0;
	        }else{
	        	return s2.compareToIgnoreCase(s1);  
	        }	        
		} 
	};
	
	public Boolean isLatestHandler(RequestHeader requestHeader){
		if(requestHeader.getClientType() == ClientType.APP.getIndex()){
			return false;
		}else{
			return true;
		}
	}
	
	public Boolean isOldestHandler(RequestHeader requestHeader){
		return false;
	}
	
	public BaseHandler getServiceHandler(RequestHeader requestHeader, SortedMap<String, BaseHandler> handlerMap){
		if(!StringUtils.isEmpty(requestHeader.getProtocolVersion())){ //按照指定接口版本查找
			return handlerMap.get( requestHeader.getProtocolVersion() );
		}
		else if(isLatestHandler(requestHeader)){//取最新的
			return handlerMap.get( handlerMap.firstKey() );
		}
		else if(isOldestHandler(requestHeader)){ //取最旧的
			return handlerMap.get( handlerMap.lastKey() );
		}
		else if(!StringUtils.isEmpty(requestHeader.getClientVersion())){//通过客户端版本查找
			for(Map.Entry<String,BaseHandler> entry : handlerMap.entrySet()){
				if( versionComparator.compare(entry.getKey(), requestHeader.getClientVersion()) <=0 ){
					return entry.getValue();
				}
			}
			return handlerMap.get( handlerMap.lastKey() );	
		}
		else{
			return null;
		}
	}

}
