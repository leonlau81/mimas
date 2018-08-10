
/**
 * 
 */
package com.vanseed.mimas.dubbo.consumer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author leon
 * 
 */
@Controller
@RequestMapping("/")
public class RootController extends BaseController {
			
	/**
	 * 登录页
	 */
	@RequestMapping(value = "/default")
	public String loginPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		model.addAttribute("host", "comb acct");
		return "login";
	}
	
//	/**
//	 * 登录
//	 */
//	@RequestMapping(value = "/login")
//	public @ResponseBody Response login(
//			@RequestBody Map<String, Object> paraMap,
//			HttpServletRequest request, HttpServletResponse response,
//			Model model){
//		
//		String userName = ParamUtils.convertString(paraMap.get("username"));
//		String password = ParamUtils.convertString(paraMap.get("password"));
//		Map<String, String> rtnMap = new HashMap<String, String>();
//		
//		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
//			//model.addAttribute("cRanking",);
//		}	
//		
//		try{
//			OssUser acct = ossUserService.login(userName, password);
//			//处理session
//			HttpSession session = request.getSession(); 
////			OssUser sessionUser = new OssUser();
////			sessionUser.setId(acct.getId());
////			sessionUser.setUserName(acct.getUserName());
//			AdminUtils.setSessionUser(session, acct);
//			
//			rtnMap.put("forwardUrl", "/admin/index.do");
//		}catch(ServiceException se){
//			return ResponseUtils.getErrorWokeRespose(se);
//		}
//		
//		return ResponseUtils.getSuccessWokeRespose(rtnMap);
//	}
//	
//	/**
//	 * 退出
//	 */
//	@RequestMapping(value = "/logout.do")
//	public String logoutPage(
//			HttpServletRequest request, HttpServletResponse response,
//			Model model){
//		
//		HttpSession session = request.getSession(); 
//		session.removeAttribute(AdminUtils.ADMIN_SESSION_USER_KEY);
//		session.removeAttribute(AdminUtils.ADMIN_SESSION_MENU_KEY);
//		
//		return "admin/login";
//	}
	
	/**
	 * 首页
	 */
	@RequestMapping(value = "/index")
	public String indexPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		
		return "index";
	}
	
	/**
	 * 400
	 */
	@RequestMapping(value = "/404")
	public String errorPage404(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		return "page/error-404";
	}
	
	/**
	 * 505
	 */
	@RequestMapping(value = "/500")
	public String errorPage500(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		return "page/error-500";
	}
	
	/**
	 * 测试
	 */
	@RequestMapping(value = "/testPage")
	public String testPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		
		return "admin/testPage";
	}
		
}