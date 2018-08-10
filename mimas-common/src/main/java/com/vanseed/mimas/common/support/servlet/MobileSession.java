package com.vanseed.mimas.common.support.servlet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MobileSession implements Serializable
{
    private static final long serialVersionUID = 3635284619978215627L;

    @JsonProperty("session_id")
	private String sessionId;
	
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("creat_time")
	private Long creatTime;

	@JsonProperty("last_access_time")
	private Long lastAccessTime;
	
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}	
}
