package com.vanseed.mimas.api.acct.controller;

import com.vanseed.mimas.api.acct.handler.factory.AcctHandlerFactory;
import com.vanseed.mimas.common.support.handler.BaseHandler;
import com.vanseed.mimas.common.support.mvc.RequestHeader;
import com.vanseed.mimas.common.support.mvc.RequestUtils;
import com.vanseed.mimas.common.support.mvc.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("acct/")
public class AcctController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${spring.cloud.client.ipAddress}")
    String ip;

	@Value("${server.port")
    String port;

	@Autowired
    AcctHandlerFactory acctHandlerFactory;

	@RequestMapping(value = "/test")
	public String test(@RequestParam String name) {
		String hostInfo = ip +":"+port;
	    logger.info("========" + hostInfo + " response!");
		return "hi, acct service from" + hostInfo;
	}

    @RequestMapping(value = "/get")
    public Response getAcct(HttpServletRequest request, @RequestParam Long acctId) {
        RequestHeader reqHeader = RequestUtils.getReqHeader(request);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("acctId", acctId);

        BaseHandler acctInfoHandler = acctHandlerFactory.getAcctInfoHandler(reqHeader);
        return acctInfoHandler.doHandler(null, reqHeader, paraMap,null);
    }

    @RequestMapping(value = "/open")
    public Response openAcct(HttpServletRequest request, @RequestParam Map<String, Object> paraMap) {
        RequestHeader reqHeader = RequestUtils.getReqHeader(request);

        BaseHandler acctOpenHandler = acctHandlerFactory.getAcctOpenHandler(reqHeader);
        return acctOpenHandler.doHandler(null, reqHeader, paraMap,null);
    }

}
