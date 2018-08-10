package com.vanseed.mimas.api.user.controller;

import com.vanseed.mimas.api.user.handler.factory.UserHandlerFactory;
import com.vanseed.mimas.common.support.handler.BaseHandler;
import com.vanseed.mimas.common.support.mvc.RequestHeader;
import com.vanseed.mimas.common.support.mvc.RequestUtils;
import com.vanseed.mimas.common.support.mvc.Response;
import com.vanseed.mimas.domain.enums.user.UserStatus;
import com.vanseed.mimas.domain.model.user.UserInfo;
import com.vanseed.mimas.service.user.IUserInfoService;
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
@RequestMapping("user/")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${spring.cloud.client.ipAddress}")
    String ip;

	@Value("${server.port}")
    String port;

	@Autowired
    UserHandlerFactory userHandlerFactory;

    @RequestMapping(value = "/test")
    public String test(@RequestParam String name) {
        String hostInfo = ip +":"+port;
        logger.info("========" + hostInfo + " response!");
        return "hi, user service from" + hostInfo;
    }

    @RequestMapping(value = "/get")
    public Response getUser(HttpServletRequest request, @RequestParam Long userId) {
        RequestHeader reqHeader = RequestUtils.getReqHeader(request);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("userId", userId);

        BaseHandler userInfoHandler = userHandlerFactory.getUserInfoHandler(reqHeader);
        return userInfoHandler.doHandler(null, reqHeader, paraMap,null);
    }

    @RequestMapping(value = "/register")
    public Response registerUser(HttpServletRequest request, @RequestParam Map<String, Object> paraMap) {
        RequestHeader reqHeader = RequestUtils.getReqHeader(request);

        BaseHandler userRegisterHandler = userHandlerFactory.getUserRegisterHandler(reqHeader);
        return userRegisterHandler.doHandler(null, reqHeader, paraMap,null);
    }

}
