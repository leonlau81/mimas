/**
 * 
 */
package com.vanseed.mimas.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.support.servlet.MobileSession;
import com.vanseed.mimas.common.utils.JacksonJsonUtil;


/**
 * @author leon
 * TODO:应该批量发送指令，降低网络开销
 */
@Component
public class SessionService {
	@Autowired
	private StringRedisTemplate sessionTemplate;

	/**
     * 通过sessionid获取userid
     */
	public MobileSession getSession(String jsessonid){
		if(StringUtils.isEmpty(jsessonid)){
			return null;
		}
		return getSessionFromRedis(jsessonid);
	}	
	/**
     * 通过sessionid获取userid
     */
	private MobileSession getSessionFromRedis(String jsessonid){
		if(StringUtils.isEmpty(jsessonid)){
			return null;
		}
		String o = sessionTemplate.opsForValue().get(jsessonid);
		if(!StringUtils.isEmpty(o)){
			MobileSession session = JacksonJsonUtil.jsonToBean(o, MobileSession.class);
			return session;
		}
		return null;
	}
	
	/**
     * 通过userid获取sessionid
     */
	public String getSessionIdByUser(String userId){
		return getSessionIdFromRedisByUser(userId);
	}	
	/**
     * 通过userid从redis中读取sessionid
     */
	private String getSessionIdFromRedisByUser(String userId){
		return sessionTemplate.boundValueOps(userId).get();
	}
	
	
	/**
     * 将当前用户设置到session中
     */
	public void setSession(MobileSession session) throws ServiceException{
		setSessionToRedis(session);
	}	
	/**
     * 将当前用户设置到redis中，为了保证一各用户只能同时在一个终端登录，需要有踢出机制
     */
	private void setSessionToRedis(MobileSession session) throws ServiceException{
		String strSession = JacksonJsonUtil.beanToJson(session);
		if(strSession==null){
			throw new ServiceException("service.common.session.error","");
		}
		sessionTemplate.opsForValue().set(session.getUserId()+"", session.getSessionId());
		sessionTemplate.opsForValue().set(session.getSessionId(), strSession);
	}
	
	/**
     * 移除session
     */
	public void removeSession(String sessionId){
		removeSessionFromRedis(sessionId);
	}
	/**
     * 将redis中的session移除
     */
	private void removeSessionFromRedis(String sessionId){
		MobileSession session = getSessionFromRedis(sessionId);
		if(session!=null && session.getUserId()!=null){
			sessionTemplate.delete(String.valueOf(session.getUserId()));
		}		
		sessionTemplate.delete(sessionId);
	}
	
	/**
     * 移除session
     */
	public void removeSessionByUserId(String userId){
		removeSessionFromRedisByUserId(userId);
	}
	/**
     * 将redis中的session移除
     */
	private void removeSessionFromRedisByUserId(String userId){
		String sessionId = sessionTemplate.opsForValue().get(userId);
		if(sessionId!=null){
			sessionTemplate.delete(sessionId);
		}		
		sessionTemplate.delete(userId);
	}
}
