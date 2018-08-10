package com.vanseed.mimas.api.acct.handler.acct;

import java.util.HashMap;
import java.util.Map;

import com.vanseed.mimas.api.acct.remote.UserServiceRemote;
import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.support.handler.BaseHandler;
import com.vanseed.mimas.common.support.mvc.RequestHeader;
import com.vanseed.mimas.common.support.mvc.Response;
import com.vanseed.mimas.common.support.mvc.ResponseUtils;
import com.vanseed.mimas.domain.model.acct.AcctInfo;
import com.vanseed.mimas.service.acct.IAcctInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AcctInfoHandler implements BaseHandler {

	private String version = "v1";
	
	@Autowired
	private IAcctInfoService acctService;

	@Autowired
    @Qualifier("USER-API")
    private UserServiceRemote userServiceRemote;

	@Autowired
    private RestTemplate restTemplate;
	
	@Override
	public Response doHandler(Object inputs,
                              RequestHeader header, Map<String, Object> params,
                              Object outs) {
		return getAcctInfo(inputs, header, params, outs);
	}

	public String getVersion() {
		return version;
	}

	private Response getAcctInfo(Object inputs,
			RequestHeader header, Map<String, Object> params,
			Object outs) {
        Long acctId = (Long)params.get("acctId");
        String acctNo = (String)params.get("acctNo");
		if(acctId == null){
			return ResponseUtils.getErrorRespose("web.user.notLogin");
		}

		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		try 
		{
            AcctInfo acct = acctService.findAccountById(acctId);
            if(acct.getUserId()!=null ){
                //ribbon restTemplate
//                String test = restTemplate.getForObject("http://USER-API/user/test?name=leon", String.class);
//                Response userResponse = restTemplate.getForObject("http://USER-API/user/get?userId="+acct.getUserId(), Response.class);
//                if(userResponse.isSuccess()){
//                    returnMap.putAll(userResponse.getResult());
//                }
                //feign client
                Response userResponse1 = userServiceRemote.getUserInfo(acct.getUserId());
                if(userResponse1.isSuccess()){
                    returnMap.putAll(userResponse1.getResult());
                }

            }
            returnMap.put("acct_info", acct);

		} catch (ServiceException e) {
			return ResponseUtils.getErrorRespose(e);
		}

        return Response.newSuccess(returnMap);
	}

}
