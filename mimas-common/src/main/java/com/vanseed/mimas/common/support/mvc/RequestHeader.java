/**
 * 
 */
package com.vanseed.mimas.common.support.mvc;

/**
 * @author leon
 *
 */
public class RequestHeader extends Header {
	private String userAgent;
	private String sessionId;

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
