package com.vanseed.mimas.eureka.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class FeignServiceError implements FeignServiceCloud{
	public String test(String name) {
		return "Sorry! return by feign error!";
	}
}
