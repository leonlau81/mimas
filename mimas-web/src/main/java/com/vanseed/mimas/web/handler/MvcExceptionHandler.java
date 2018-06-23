package com.vanseed.mimas.web.handler;

import javax.servlet.http.HttpServletRequest;

import com.vanseed.mimas.common.support.mvc.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.support.mvc.ResponseUtils;

//@ControllerAdvice
public class MvcExceptionHandler {
	public static final String SERVICE_EXCEPTION_VIEW = "service_error";
	public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = ServiceException.class)
    public ModelAndView serviceExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
        ModelAndView mav = new ModelAndView();
        Response response = ResponseUtils.getErrorRespose(se);
        mav.addObject("response", response);
        mav.addObject("exception", se);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(SERVICE_EXCEPTION_VIEW);
        return mav;
    }
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
