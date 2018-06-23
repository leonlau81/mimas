/**
 * 
 */
package com.vanseed.mimas.common.support.mvc;

import com.vanseed.mimas.common.annotation.ReferClass;
import com.vanseed.mimas.common.enums.ClientType;
import com.vanseed.mimas.common.enums.DeviceType;

/**
 * @author leon
 *
 */

public class Header {
	@ReferClass(clz=ClientType.class)
	private Integer clientType = 0; 	//客户端类型，区分app，web等
	private String clientVersion = "";	//客户端版本
		
	@ReferClass(clz=DeviceType.class)	
	private Integer deviceType = 0;		//设备类型，区分安卓,ios
	private String deviceId = "";		//设备id
	
	private String protocolId = "";		//协议号，根据协议分发不同的处理器
	private String protocolVersion = "";//协议版本，根据协议分发不同的处理器
	
	private String language = "en_US"; 		//语言
	
	private String cIp;
	private Long cTime;
	private String sIp;
	private Long sTime;
	
	
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public String getClientVersion() {
		return clientVersion;
	}
	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getcIp() {
		return cIp;
	}
	public void setcIp(String cIp) {
		this.cIp = cIp;
	}
	public Long getcTime() {
		return cTime;
	}
	public void setcTime(Long cTime) {
		this.cTime = cTime;
	}
	public String getsIp() {
		return sIp;
	}
	public void setsIp(String sIp) {
		this.sIp = sIp;
	}
	public Long getsTime() {
		return sTime;
	}
	public void setsTime(Long sTime) {
		this.sTime = sTime;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
