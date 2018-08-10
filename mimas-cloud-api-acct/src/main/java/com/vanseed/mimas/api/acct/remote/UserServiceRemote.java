package com.vanseed.mimas.api.acct.remote;

import com.vanseed.mimas.common.support.mvc.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "API-USER", path = "user",
			//configuration = FeignClientConfig.class, 
			fallback = UserServiceError.class)
public interface UserServiceRemote {
	@RequestMapping(value = "/get")
    Response getUserInfo(@RequestParam(value = "userId") Long userId);
}
