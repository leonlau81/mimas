package com.vanseed.mimas.eureka.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author leon
 * 
 */

@Configuration
//@ConfigurationProperties(prefix=ServerConfig.PREFIX)
public class ServerConfig {
	public static final String PREFIX = "spring.eureka";
	private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.custome.commonMsg}")
	private String commonMsg;

}

