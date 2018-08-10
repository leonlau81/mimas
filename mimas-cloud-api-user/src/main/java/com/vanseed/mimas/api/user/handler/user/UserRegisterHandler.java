package com.vanseed.mimas.api.user.handler.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.support.handler.BaseHandler;
import com.vanseed.mimas.common.support.mvc.RequestHeader;
import com.vanseed.mimas.common.support.mvc.Response;
import com.vanseed.mimas.common.support.mvc.ResponseUtils;
import com.vanseed.mimas.domain.model.user.UserInfo;
import com.vanseed.mimas.service.user.IUserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class UserRegisterHandler implements BaseHandler {

	private String version = "v1";
	
	@Autowired
	private IUserInfoService userService;
	
	private Log logger = LogFactory.getLog(getClass());
	
	private String messageImageUrl;
	
	@Override
	public Response doHandler(Object inputs,
                              RequestHeader header, Map<String, Object> params,
                              Object outs) {
		return register(inputs, header, params, outs);
	}

	public String getVersion() {
		return version;
	}

	private Response register(Object inputs, RequestHeader header, Map<String, Object> params, Object outs)
	{
		UserInfo user = null;
			
		try {
		    user = userService.register( params);
		} catch (ServiceException e) {
			return ResponseUtils.getErrorRespose(e);
		}
		
		if(user==null){
			return ResponseUtils.getErrorRespose("web.common.server.error");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		return Response.newSuccess(map);
	}

}
