package com.vanseed.mimas.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonUtil 
{
	private static final Logger logger = LoggerFactory.getLogger(JacksonJsonUtil.class);  
	
	/** 
     * 将java对象转换成json字符串 
     * @param obj 准备转换的对象 
     * @return json字符串 
     * @throws Exception  
     */  
    public static String beanToJson(Object obj)  {  
        try {  
            ObjectMapper objectMapper = new ObjectMapper();  
            String json =objectMapper.writeValueAsString(obj);  
            return json;  
        } catch (Exception e) {
        	logger.error("bean to json error",e);
            return null;  
        }         
    }  
      
      
    /** 
     * 将json字符串转换成java对象 
     * @param json 准备转换的json字符串 
     * @param cls  准备转换的类 
     * @return  
     * @throws Exception  
     */  
    public static <T> T jsonToBean(String json, Class<T> cls)  {  
    	try {  
        	ObjectMapper objectMapper = new ObjectMapper();   
        	T obj = objectMapper.readValue(json, cls);   
            return obj;  
        } catch (Exception e) { 
        	logger.error("json to bean error", e);
        	return null;  
        }     
    }  
    
    /** 
     * 将json字符串转换成Map<String,String>对象  
     * @param json字符串
     * @return  Map<String,String> 转换结果
     * @throws Exception  
     */
	public static Map<String, String> jsonToMap(String strJson)
	{
		Map<String, String> rtnMap = new HashMap<String, String>();
		try
		{		
			ObjectMapper mapper = new ObjectMapper();
			//rtnMap = acct.readValue(strJson, Map.class);
			//读代码的同学看一下java泛型的擦除机制-leon
			rtnMap = mapper.readValue(strJson, new TypeReference<Map<String, String>>(){});
		}
		catch(Exception e)
		{
			logger.error("json to map error", e);
			rtnMap = null;
		}
		
		return rtnMap;
	}
	
	/** 
     * 将Map<String,String>对象转换成json字符串 
     * @param Map<String,String> 准备转换的对象 
     * @return json字符串 
     * @throws Exception  
     */ 
	public static String mapToJson(Map<String, String> jsonMap)
	{
		String rtnString = "";
		try
		{		
			ObjectMapper mapper = new ObjectMapper();
			rtnString = mapper.writeValueAsString(jsonMap);	
		}
		catch(Exception e)
		{
			logger.error("map to json error", e);
			rtnString = null;
		}
		
		return rtnString;
	}
	
	/** 
     * 将json字符串转换成Map<String,String>对象  
     * @param json字符串
     * @return  Map<String,String> 转换结果
     * @throws Exception  
     */
	public static String[] jsonToStringArray(String strJson)
	{
		String[] rtnArray = null;
		try
		{		
			ObjectMapper mapper = new ObjectMapper();
			//rtnMap = acct.readValue(strJson, Map.class);
			//读代码的同学看一下java泛型的擦除机制-leon
			rtnArray = mapper.readValue(strJson, new TypeReference<String[]>(){});
		}
		catch(Exception e)
		{
			logger.error("json to array error", e);
			rtnArray = null;
		}
		
		return rtnArray;
	}
	
	/** 
     * 将Map<String,String>对象转换成json字符串 
     * @param Map<String,String> 准备转换的对象 
     * @return json字符串 
     * @throws Exception  
     */ 
	public static String StringArrayToJson(String[] stringArray)
	{
		String rtnString = "";
		try
		{		
			ObjectMapper mapper = new ObjectMapper();
			rtnString = mapper.writeValueAsString(stringArray);	
		}
		catch(Exception e)
		{
			logger.error("array to json error", e);
			rtnString = null;
		}
		
		return rtnString;
	}
	
	public static Map<String, Object> jsonToObjectMap(String strJson)
	{
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		try
		{		
			ObjectMapper mapper = new ObjectMapper();
			//rtnMap = acct.readValue(strJson,  Map.class);
			rtnMap = mapper.readValue(strJson, new TypeReference<Map<String, Object>>(){});
		}
		catch(Exception e)
		{
			rtnMap = null;
		}
		
		return rtnMap;
	}
	
	public static String objectMapToJson(Map<String, Object> jsonMap)
	{
		String rtnString = "";
		try
		{		
			ObjectMapper mapper = new ObjectMapper();
			rtnString = mapper.writeValueAsString(jsonMap);	
		}
		catch(Exception e)
		{
			rtnString = null;
		}
		
		return rtnString;
	}	
}
