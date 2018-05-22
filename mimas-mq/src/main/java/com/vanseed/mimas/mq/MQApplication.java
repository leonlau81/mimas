package com.vanseed.mimas.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.vanseed.mimas"})  
public class MQApplication {

	public static void main(String[] args) {
		SpringApplication.run(MQApplication.class, args);
	}
}
