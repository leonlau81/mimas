/**
 * 
 */
package com.vanseed.mimas.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * @author leon
 *
 */
public class ThreadLocalUtils {
	private static ThreadLocal<Map<String,Object>> contextLocal = new ThreadLocal<Map<String,Object>>();
	private static String USER_ID_KEY = "userId";
	private static String REQUEST_START_TIME = "requestStartTime";
	private static String REQUEST_END_TIME = "requestEndTime";
	private static String LANGUAGE_KEY = "language";
	
	
	public static void setUserId(Long userId) 
	{
		if(contextLocal.get()!=null)
		{
			contextLocal.get().put(USER_ID_KEY, userId);
		}				
		else
		{
			Map<String,Object> threadLocalMap = new HashMap<String,Object>();
			threadLocalMap.put(USER_ID_KEY, userId);
			contextLocal.set(threadLocalMap);
		}
	}
	
	public static Long getUserId() 
	{
		if(contextLocal.get()!=null)
			return (Long)contextLocal.get().get(USER_ID_KEY);
		else
			return null;
	}
	
	public static void setLanguage(String language) 
	{
		if(contextLocal.get()!=null)
		{
			contextLocal.get().put(LANGUAGE_KEY, language);
		}				
		else
		{
			Map<String,Object> threadLocalMap = new HashMap<String,Object>();
			threadLocalMap.put(LANGUAGE_KEY, language);
			contextLocal.set(threadLocalMap);
		}
	}
	
	public static String getLanguage() 
	{
		if(contextLocal.get()!=null)
			return (String)contextLocal.get().get(LANGUAGE_KEY);
		else
			return null;
	}
	
	public static void setRequestStartTime(Date startTime) 
	{
		if(contextLocal.get()!=null)
		{
			contextLocal.get().put(REQUEST_START_TIME, startTime);
		}				
		else
		{
			Map<String,Object> threadLocalMap = new HashMap<String,Object>();
			threadLocalMap.put(REQUEST_START_TIME, startTime);
			contextLocal.set(threadLocalMap);
		}
	}
	
	public static Date getRequestStartTime() 
	{
		if(contextLocal.get()!=null)
			return (Date)contextLocal.get().get(REQUEST_START_TIME);
		else
			return null;
	}
	
	public static void setRequestEndTime(Date endTime) 
	{
		if(contextLocal.get()!=null)
		{
			contextLocal.get().put(REQUEST_END_TIME, endTime);
		}				
		else
		{
			Map<String,Object> threadLocalMap = new HashMap<String,Object>();
			threadLocalMap.put(REQUEST_END_TIME, endTime);
			contextLocal.set(threadLocalMap);
		}
	}
	
	public static Date getRequestEndTime() 
	{
		if(contextLocal.get()!=null)
			return (Date)contextLocal.get().get(REQUEST_END_TIME);
		else
			return null;
	}
	
	public static Object getCustomValue(String key) 
	{
		if(contextLocal.get()!=null)
			return contextLocal.get().get(key);
		else
			return null;
	}
	
	public static void setCustomValue(String key, Object value) 
	{
		if(contextLocal.get()!=null)
		{
			contextLocal.get().put(key, value);
		}				
		else
		{
			Map<String,Object> threadLocalMap = new HashMap<String,Object>();
			threadLocalMap.put(key, value);
			contextLocal.set(threadLocalMap);
		}	
	}
}
