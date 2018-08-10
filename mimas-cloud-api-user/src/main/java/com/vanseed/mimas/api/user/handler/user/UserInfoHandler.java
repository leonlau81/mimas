package com.vanseed.mimas.api.user.handler.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.support.handler.BaseHandler;
import com.vanseed.mimas.common.support.mvc.RequestHeader;
import com.vanseed.mimas.common.support.mvc.Response;
import com.vanseed.mimas.common.support.mvc.ResponseUtils;
import com.vanseed.mimas.common.utils.ThreadLocalUtils;
import com.vanseed.mimas.domain.model.user.UserInfo;
import com.vanseed.mimas.service.user.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoHandler implements BaseHandler {

	private String version = "v1";
	
	@Autowired
	private IUserInfoService userService;
	
	@Override
	public Response doHandler(Object inputs,
                              RequestHeader header, Map<String, Object> params,
                              Object outs) {
		return getUserInfo(inputs, header, params, outs);
	}

	public String getVersion() {
		return version;
	}

	private Response getUserInfo(Object inputs,
			RequestHeader header, Map<String, Object> params,
			Object outs) {
        Long userId = (Long)params.get("userId");
		if(userId == null){
			return ResponseUtils.getErrorRespose("web.user.notLogin");
		}

		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		try 
		{
            UserInfo user = userService.findUserById(userId);
            returnMap.put("user_info", user);
		} catch (ServiceException e) {
			return ResponseUtils.getErrorRespose(e);
		}

        return Response.newSuccess(returnMap);
	}

}
