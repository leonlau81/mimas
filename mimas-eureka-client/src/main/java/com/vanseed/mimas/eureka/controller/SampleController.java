package com.vanseed.mimas.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cloud")
public class SampleController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${server.port}")
    String port;

	@RequestMapping(value = "/test")
	public String test(@RequestParam String name) {
		logger.info("========server:"+port+" response!"); 
		return "hi "+name+",i am from port:" +port;
	}

}
