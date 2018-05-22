/**
 * 
 */
package com.vanseed.mimas.web.controller.sample;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Producer;
import com.vanseed.mimas.common.utils.GraphicCodeUtils;

/**
 * @author leon
 *
 */
@Controller
@RequestMapping("public")
public class GraphicCodeController {
	
	@Autowired
	private Producer captchaProducer;
	
	@RequestMapping( value="/code/{code}")
	public void code(
			@PathVariable String code, 
			HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        GraphicCodeUtils.GraphicCode vc = GraphicCodeUtils.getCode(code);
		
		//TODO:集群环境需要修改
		//request.getSession(true).setAttribute("graphicCode", vc.getCode());
				
		try {
			ImageIO.write(vc.getImage(), "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping( value="/randomCode")
	public void randomCode(
			HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        GraphicCodeUtils.GraphicCode vc = GraphicCodeUtils.getRandcode();
				
		try {
			ImageIO.write(vc.getImage(), "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/captcha/{code}")  
	public void getCaptchaImage(
    		@PathVariable String code, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		response.setDateHeader("Expires", 0);          
        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");          
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");            
        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");            
        // return a jpeg  
        response.setContentType("image/jpeg");            
        // create the text for the image  
        String capText = captchaProducer.createText();           
          
        // create the image with the text  
        try {
        	BufferedImage bi = captchaProducer.createImage(capText);  
			ImageIO.write(bi, "JPEG", response.getOutputStream());
		} catch (IOException ie) {
			ie.printStackTrace();
		}
        catch (Exception e) {
			e.printStackTrace();
		}
    }  
  
}
