package com.vanseed.mimas.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("ribbon")
public class RibbonController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    RestTemplate restTemplate;

	@RequestMapping(value = "/test")
	@HystrixCommand(fallbackMethod = "testError")
	public String test(@RequestParam String name) {
		logger.info("=========ribbon client");
		return restTemplate.getForObject("http://SERVICE-CLOUD/cloud/test?name="+name,String.class);
	}
	
	public String testError(String name) {
		return "Sorry! this is error msg from ribbon!";
	}

}
