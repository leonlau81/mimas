package com.vanseed.mimas.mq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vanseed.mimas.common.utils.JacksonJsonUtil;

/**
 * @author leon
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MQTests {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void contextLoads() {
	}
	
	//模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。    
	private MockMvc mockMvc; 
	
	//注入WebApplicationContext   
	@Autowired    
    private WebApplicationContext wac;  
	
	//初始化工作    
	@Before 
    public void setup() {    
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();    
    } 

	@Test    
    public void testSendMsg() throws Exception {    
        Map<String, String> map = new HashMap<>();  
        map.put("status", "1");  
          
        MvcResult result = mockMvc.perform(get("/rocket/sendMsg")
        		.contentType(MediaType.APPLICATION_JSON_UTF8) //请求媒体类型
        		.content(""))  //发送请求数据
                .andExpect(status().isOk())// 预期返回httpstatus状态       
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8    
                .andReturn();// 返回执行请求的结果    
            
        //logger.info("======= rest=" + result.getResponse().getContentAsString());    
    } 
	
	@Test    
    public void testTxnMsg() throws Exception {    
        Map<String, String> map = new HashMap<>();  
        map.put("status", "1");  
          
        MvcResult result = mockMvc.perform(get("/rocket/sendTransactionMsg")
        		.contentType(MediaType.APPLICATION_JSON_UTF8) //请求媒体类型
        		.content(""))  //发送请求数据
                .andExpect(status().isOk())// 预期返回httpstatus状态       
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8    
                .andReturn();// 返回执行请求的结果    
            
        //logger.info("======= rest=" + result.getResponse().getContentAsString());    
    } 
	
	@Test    
    public void testOrderMsg() throws Exception {    
        Map<String, String> map = new HashMap<>();  
        map.put("status", "1");  
          
        MvcResult result = mockMvc.perform(get("/rocket/sendMsgOrder")
        		.contentType(MediaType.APPLICATION_JSON_UTF8) //请求媒体类型
        		.content(""))  //发送请求数据
                .andExpect(status().isOk())// 预期返回httpstatus状态       
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8    
                .andReturn();// 返回执行请求的结果    
            
        //logger.info("======= rest=" + result.getResponse().getContentAsString());    
    } 
}
