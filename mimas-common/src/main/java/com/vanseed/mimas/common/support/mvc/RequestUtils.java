/**
 * 
 */
package com.vanseed.mimas.common.support.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.vanseed.mimas.common.enums.ClientType;
import com.vanseed.mimas.common.utils.JacksonJsonUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

/**
 * @author leon
 * 
 */
public class RequestUtils {
	
	public static RequestHeader getReqHeader(HttpServletRequest request){
		String header = request.getHeader("mimas");
		String userAgent = request.getHeader("acct-agent");
		String sessionId = request.getHeader("jsessionid");
		RequestHeader requestHeader = convertReqHeader(header);
		requestHeader.setUserAgent(userAgent);
		requestHeader.setSessionId(sessionId);
		return requestHeader;
	}
	
	public static RequestHeader getReqHeader(HttpHeaders headers) {
		String wokeHeader = headers.get("mimas").get(0);
		String userAgent = headers.get("acct-agent")!=null?headers.get("acct-agent").get(0):null;
		String sessionId = headers.get("jsessionid")!=null?headers.get("jsessionid").get(0):null;
		
		RequestHeader requestHeader = convertReqHeader(wokeHeader);
		requestHeader.setUserAgent(userAgent);
		requestHeader.setSessionId(sessionId);
		return requestHeader;
	}
	
	private static RequestHeader convertReqHeader(String wokeHeader) {
		RequestHeader header = new RequestHeader();
		if (StringUtils.hasText(wokeHeader)) {
			Map<String,String> headerMap = JacksonJsonUtil.jsonToMap(wokeHeader);
			
			if(!StringUtils.isEmpty(headerMap.get("client-type"))){
				header.setClientType(Integer.valueOf(headerMap.get("client-type")));
			}
			if(!StringUtils.isEmpty(headerMap.get("client-version"))){
				header.setClientVersion(headerMap.get("client-version"));
			}
			if(!StringUtils.isEmpty(headerMap.get("device-type"))){
				header.setDeviceType(Integer.valueOf(headerMap.get("device-type")));
			}
			if(!StringUtils.isEmpty(headerMap.get("device-id"))){
				header.setDeviceId(headerMap.get("device-id"));
			}
			if(!StringUtils.isEmpty(headerMap.get("protocol-id"))){
				header.setProtocolId(headerMap.get("protocol-id"));
			}
			if(!StringUtils.isEmpty(headerMap.get("protocol-version"))){
				header.setProtocolVersion(headerMap.get("protocol-version"));
			}
			if(!StringUtils.isEmpty(headerMap.get("c-ip"))){
				header.setcIp(headerMap.get("c-ip"));
			}
			if(!StringUtils.isEmpty(headerMap.get("c-time"))){
				header.setcTime(Long.valueOf(headerMap.get("c-time")));
			}
			if(!StringUtils.isEmpty(headerMap.get("s-ip"))){
				header.setsIp(headerMap.get("s-ip"));
			}
			if(!StringUtils.isEmpty(headerMap.get("s-time"))){
				header.setsTime(Long.valueOf(headerMap.get("s-time")));
			}
			if(!StringUtils.isEmpty(headerMap.get("language"))){
				header.setLanguage(headerMap.get("language"));
			}
		}
		return header;
	}
	
	public static Boolean validateReqHeader(RequestHeader header){
		if(header.getClientType()==null || header.getClientType() != ClientType.APP.getIndex()){
			return false;
		}
		if(StringUtils.isEmpty(header.getClientVersion()) && StringUtils.isEmpty(header.getProtocolVersion())){
			return false;
		}
		if(StringUtils.isEmpty(header.getProtocolId())){
			return false;
		}
		if(header.getDeviceType()==null || header.getDeviceType()<=0){
			return false;
		}
//		if(StringUtils.isEmpty(header.getDeviceId())){
//			return false;
//		}
		return true;
	}
}
