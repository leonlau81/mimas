package com.vanseed.mimas.dubbo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.vanseed.mimas.dubbo.service.IDubboService;

@Service
public class DubboServiceImpl implements IDubboService{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public String echo(String name) {
		String displayMsg = "Hello,"+name+"!";
		logger.info(displayMsg);
		return displayMsg;
	}
}
