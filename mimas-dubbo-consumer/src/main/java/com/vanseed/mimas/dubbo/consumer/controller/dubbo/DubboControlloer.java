package com.vanseed.mimas.dubbo.consumer.controller.dubbo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.RpcContext;
import com.reger.dubbo.annotation.Inject;
import com.vanseed.mimas.common.mvc.support.CombResponse;
import com.vanseed.mimas.common.mvc.support.CombResponseUtils;
import com.vanseed.mimas.dubbo.consumer.controller.BaseController;
import com.vanseed.mimas.dubbo.service.IDubboService;

/**
 * @author leon
 * 
 */
@Controller
@RequestMapping("dubbo")
public class DubboControlloer extends BaseController{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Reference
	@Inject 
	IDubboService dubboService; 
	
	@RequestMapping("echo/{name}")
	@ResponseBody
	public CombResponse echo(@PathVariable String name, 
			HttpServletRequest request, HttpServletResponse response) {
		RpcContext.getContext().setAttachment("s_tid", "123456789");
		RpcContext.getContext().setAttachment("s_sid", "1.1");
		String result = dubboService.echo(name);
		//logger.info("controller:ok!");
		return CombResponseUtils.getSuccessRespose(result);
	}
}
