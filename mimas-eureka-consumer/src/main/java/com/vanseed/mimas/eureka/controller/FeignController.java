package com.vanseed.mimas.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanseed.mimas.eureka.service.FeignServiceCloud;

@RestController
@RequestMapping("feign")
public class FeignController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	FeignServiceCloud feignServiceCloud;

	@RequestMapping(value = "/test")
	public String test(@RequestParam String name) {
		logger.info("=========feign client");
		return feignServiceCloud.test(name);
	}

}
