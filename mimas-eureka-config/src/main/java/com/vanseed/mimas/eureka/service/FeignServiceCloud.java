package com.vanseed.mimas.eureka.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-cloud", path = "cloud", fallback = FeignServiceError.class)
public interface FeignServiceCloud {
	@RequestMapping(value = "/test")
    String test(@RequestParam(value = "name") String name);
}
