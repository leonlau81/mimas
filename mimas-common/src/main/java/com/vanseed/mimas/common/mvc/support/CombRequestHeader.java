/**
 * 
 */
package com.vanseed.mimas.common.mvc.support;

/**
 * @author leon
 *
 */
public class CombRequestHeader extends CombHeader{
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
