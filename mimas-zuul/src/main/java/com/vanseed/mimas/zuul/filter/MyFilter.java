package com.vanseed.mimas.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class MyFilter extends ZuulFilter {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
	 * pre：路由之前
	 * routing：路由之时
	 * post： 路由之后
	 * error：发送错误调用
	 * 
	 * filterOrder：过滤的顺序
	 * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
	 * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限 
	 */
	
	@Override
    public String filterType() {
        return "pre";	// 前置过滤器  
    }

    @Override
    public int filterOrder() {
        return 0;		// 优先级为0，数字越大，优先级越低  
    }

    @Override
    public boolean shouldFilter() {
        return true; 	// 是否执行该过滤器，此处为true，说明需要过滤  
    }

    @Override
    public Object run() {
    	RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        String requestURI = request.getRequestURI();
        
        if(requestURI.indexOf("api-user")>0) {
        	logger.warn("request intercepted!");
        	 ctx.setSendZuulResponse(false); 	
             ctx.setResponseStatusCode(401);
             ctx.setResponseBody("invalide request!");
             return null;
        }
        
        if(accessToken == null) {
        	logger.warn("token is empty");
            ctx.setSendZuulResponse(false); 	// 通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
            ctx.setResponseStatusCode(401);		// 然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        logger.info("ok");
        
    	return null;
    }
	
}
