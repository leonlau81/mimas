package com.vanseed.mimas.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
@RequestMapping("config")
public class ConfigController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	//@Value("${remote.config.name}")
    String configName;

	@RequestMapping(value = "/test")
	public String test() {
		logger.info("=========get config from remote");
		return configName;
	}

}
