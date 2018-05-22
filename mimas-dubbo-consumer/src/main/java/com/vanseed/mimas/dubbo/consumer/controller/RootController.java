
/**
 * 
 */
package com.vanseed.mimas.dubbo.consumer.controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.mvc.support.CombResponse;
import com.vanseed.mimas.common.mvc.support.CombResponseUtils;

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
		model.addAttribute("host", "comb user");
		return "login";
	}
	
//	/**
//	 * 登录
//	 */
//	@RequestMapping(value = "/login")
//	public @ResponseBody CombResponse login(
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
//			OssUser user = ossUserService.login(userName, password);
//			//处理session
//			HttpSession session = request.getSession(); 
////			OssUser sessionUser = new OssUser();
////			sessionUser.setId(user.getId());
////			sessionUser.setUserName(user.getUserName());
//			AdminUtils.setSessionUser(session, user);
//			
//			rtnMap.put("forwardUrl", "/admin/index.do");
//		}catch(ServiceException se){
//			return CombResponseUtils.getErrorWokeRespose(se);
//		}
//		
//		return CombResponseUtils.getSuccessWokeRespose(rtnMap);
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