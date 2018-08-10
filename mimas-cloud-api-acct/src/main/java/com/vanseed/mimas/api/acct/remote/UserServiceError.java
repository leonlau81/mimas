package com.vanseed.mimas.api.acct.remote;

import com.vanseed.mimas.common.support.mvc.Response;
import com.vanseed.mimas.common.support.mvc.ResponseUtils;
import org.springframework.stereotype.Component;

@Component
public class UserServiceError implements UserServiceRemote {
	public Response getUserInfo(Long userId) {
		return ResponseUtils.getErrorRespose();
	}
}
