package com.vanseed.mimas.api.acct.handler.acct;

import java.util.HashMap;
import java.util.Map;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.support.handler.BaseHandler;
import com.vanseed.mimas.common.support.mvc.RequestHeader;
import com.vanseed.mimas.common.support.mvc.Response;
import com.vanseed.mimas.common.support.mvc.ResponseUtils;
import com.vanseed.mimas.domain.model.acct.AcctInfo;
import com.vanseed.mimas.service.acct.IAcctInfoService;;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AcctOpenHandler implements BaseHandler {

	private String version = "v1";
	
	@Autowired
    private IAcctInfoService acctService;
	
	private Log logger = LogFactory.getLog(getClass());
	
	private String messageImageUrl;
	
	@Override
	public Response doHandler(Object inputs,
                              RequestHeader header, Map<String, Object> params,
                              Object outs) {
		return openAcct(inputs, header, params, outs);
	}

	public String getVersion() {
		return version;
	}

	private Response openAcct(Object inputs, RequestHeader header, Map<String, Object> params, Object outs)
	{
		AcctInfo acct = new AcctInfo();
			
		try {
            acct = acctService.addAccount( acct);
		} catch (ServiceException e) {
			return ResponseUtils.getErrorRespose(e);
		}
		
		if(acct==null){
			return ResponseUtils.getErrorRespose("web.common.server.error");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		return Response.newSuccess(map);
	}

}
