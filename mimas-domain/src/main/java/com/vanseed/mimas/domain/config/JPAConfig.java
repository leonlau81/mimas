package com.vanseed.mimas.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author leon
 * @version 1.0.0
 * @date 18/4/4 下午9:11.
 */

@Configuration
@EnableJpaRepositories(basePackages="com.vanseed.mimas.domain.repository")  
@EntityScan(basePackages="com.vanseed.mimas.domain.model")   
public class JPAConfig {
 
}
