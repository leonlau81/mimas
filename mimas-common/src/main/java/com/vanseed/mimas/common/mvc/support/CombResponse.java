/**
 * 
 */
package com.vanseed.mimas.common.mvc.support;

/**
 * @author kunrey
 * 
 */
public class CombResponse {

	public static final int SUCCESS = 200;
	public static final int ERROR = 500;

	private int status;

	private String memo;

	private Object result;

	public CombResponse(int status, String memo) {
		super();
		this.status = status;
		this.memo = memo;
	}

	public static CombResponse newSuccess(Object result) {
		CombResponse response = new CombResponse(CombResponse.SUCCESS,
				"success");
		response.result = result;
		return response;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}