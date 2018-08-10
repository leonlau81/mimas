/**
 * 
 */
package com.vanseed.mimas.common.support.mvc;

import java.util.Map;

/**
 * @author leon
 * 
 */
public class Response {

	public static final int SUCCESS = 200;
	public static final int ERROR = 500;

	private int status;

	private String memo;

	private Map<String, Object> result;

    public Response() {

    }

	public Response(int status, String memo) {
		this.status = status;
		this.memo = memo;
	}

	public static Response newSuccess(Map<String, Object> result) {
		Response response = new Response(Response.SUCCESS,
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

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

    public boolean isSuccess() {
        if(this.status == SUCCESS){
            return true;
        }else{
            return false;
        }
    }
}