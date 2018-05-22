package com.vanseed.mimas.web.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;  
import org.apache.shiro.mgt.SecurityManager;    
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;  
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;  
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;  
import org.crazycake.shiro.RedisCacheManager;  
import org.crazycake.shiro.RedisManager;  
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;

import com.vanseed.mimas.web.security.ShiroSessionManager;
import com.vanseed.mimas.web.security.ShiroRealm;

import java.util.LinkedHashMap;  
import java.util.Map;  
  
/**
 * @author leon
 * 
 */

@Configuration  
public class ShiroConfig {  
  
	@Value("${spring.redis.shiro.host}")  
	private String host;  
	@Value("${spring.redis.shiro.port}")  
	private int port;  
	@Value("${spring.redis.shiro.timeout}")  
	private int timeout;  
	@Value("${spring.redis.shiro.password}")  
	private String password; 
	@Value("${spring.redis.shiro.database}")  
	private int database;
	
	private final Logger logger = LoggerFactory.getLogger(getClass()); 
  
    @Bean  
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {  
    	logger.info("ShiroConfiguration.shirFilter()");  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        shiroFilterFactoryBean.setSecurityManager(securityManager);  
        
        //如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        
  
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
        //注意过滤器配置顺序 不能颠倒       
        // 配置不会被拦截的链接 顺序判断  
        filterChainDefinitionMap.put("/static/**", "anon");  
        filterChainDefinitionMap.put("/public/**", "anon"); 
        //这个配置可保证对任何url不会再多走一遍authc过滤器
        filterChainDefinitionMap.put("/favicon.ico", "anon");      
        //filterChainDefinitionMap.put("/ajaxLogin", "anon");  
        filterChainDefinitionMap.put("/login", "authc"); 
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl 
        filterChainDefinitionMap.put("/logout", "logout"); 
        filterChainDefinitionMap.put("/**", "authc"); 
        
         
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        return shiroFilterFactoryBean;  
    }  
  
    /**  
     * 凭证匹配器  
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了  
     * ）  
     *  
     * @return  
     */  
    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher() {  
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;  
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));  
        return hashedCredentialsMatcher;  
    }  
  
    @Bean  
    public ShiroRealm myShiroRealm() {  
    	ShiroRealm myShiroRealm = new ShiroRealm();  
    	myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
        return myShiroRealm;  
    }  
  
  
    @Bean  
    public SecurityManager securityManager() {  
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();  
        securityManager.setRealm(myShiroRealm());  
        // 自定义session管理 使用redis  
        securityManager.setSessionManager(sessionManager());  
        // 自定义缓存实现 使用redis  
        securityManager.setCacheManager(cacheManager());  
        return securityManager;  
    }  
  
    //自定义sessionManager  
    @Bean  
    public ShiroSessionManager sessionManager() {  
    	ShiroSessionManager mySessionManager = new ShiroSessionManager();  
    	mySessionManager.setSessionDAO(redisSessionDAO());  
        return mySessionManager;  
    }  
  
    /** 
     * 配置shiro redisManager 
     * <p> 
     * 使用的是shiro-redis开源插件 
     * 
     * @return 
     */  
    public RedisManager redisManager() {  
        RedisManager redisManager = new RedisManager();  
        redisManager.setHost(host);  
        redisManager.setPort(port);  
        redisManager.setTimeout(timeout);  //这个是连接redis服务器的超时时间
        redisManager.setPassword(password);  
        redisManager.setDatabase(database);
        return redisManager;  
    }  
  
    /** 
     * cacheManager 缓存 redis实现 
     * <p> 
     * 使用的是shiro-redis开源插件 
     * 
     * @return 
     */  
    @Bean  
    public RedisCacheManager cacheManager() {  
        RedisCacheManager redisCacheManager = new RedisCacheManager();  
        redisCacheManager.setRedisManager(redisManager());  
        return redisCacheManager;  
    }  
  
    /** 
     * RedisSessionDAO shiro sessionDao层的实现 通过redis 
     * <p> 
     * 使用的是shiro-redis开源插件 
     */  
    @Bean  
    public RedisSessionDAO redisSessionDAO() {  
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();  
        redisSessionDAO.setRedisManager(redisManager()); 
        redisSessionDAO.setExpire(1800);// 这个才是session的过期时间
        return redisSessionDAO;  
    }  
  
    /** 
     * 开启shiro aop注解支持. 
     * 使用代理方式;所以需要开启代码支持; 
     * 
     * @param securityManager 
     * @return 
     */  
    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
        return authorizationAttributeSourceAdvisor;  
    }  
}  
