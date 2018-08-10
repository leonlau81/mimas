package com.vanseed.mimas.common.support.handler;

import com.vanseed.mimas.common.support.mvc.RequestHeader;
import com.vanseed.mimas.common.support.mvc.Response;

import java.util.Map;

public interface BaseHandler {

	public Response doHandler(Object inputs, RequestHeader header,
                              Map<String, Object> params, Object outs);
}
