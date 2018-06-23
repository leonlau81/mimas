package com.vanseed.mimas.common.support.servlet;

import java.io.Serializable;

public final class ApplicationSession implements Serializable {

	private static final long serialVersionUID = 3978422529196962354L;

	private String id;

	private Long userId;
	
	private Long creatTime;
	
	private Long lastAccessTime;

	public ApplicationSession(String id,Long userId) {
		this.id = id;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Long creatTime) {
		this.creatTime = creatTime;
	}

	public Long getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
}
