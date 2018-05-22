package com.vanseed.mimas.web.security;

import org.apache.shiro.authc.*;  
import org.apache.shiro.authz.AuthorizationInfo;  
import org.apache.shiro.authz.SimpleAuthorizationInfo;  
import org.apache.shiro.realm.AuthorizingRealm;  
import org.apache.shiro.subject.PrincipalCollection;  
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vanseed.mimas.domain.model.user.UserInfo;
import com.vanseed.mimas.service.user.IUserInfoService;

import javax.annotation.Resource;  
  
/**
 * @author leon
 * 
 */

public class ShiroRealm extends AuthorizingRealm {  
	private final Logger logger = LoggerFactory.getLogger(getClass());  
	
	@Autowired
	private IUserInfoService userInfoService;  
  
	@Override  
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
		logger.debug("权限验证 --> ShiroRealm.doGetAuthorizationInfo()");  
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();  
        //TODO:查找角色
//        for (SysRole role : userInfo.getRoleList()) {  
//            authorizationInfo.addRole(role.getRole());  
//            for (SysPermission p : role.getPermissions()) {  
//                authorizationInfo.addStringPermission(p.getPermission());  
//            }  
//        }  
        return authorizationInfo;  
    }  
  
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/  
    @Override  
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  
			throws AuthenticationException {  
    	logger.debug("登录验证 --> ShiroRealm.doGetAuthenticationInfo()");  
        //获取用户的输入的账号.  
        String username = (String) token.getPrincipal();   
        
        //通过username从数据库中查找 User对象，如果找到，没找到.  
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法  
        UserInfo userInfo = userInfoService.findUserByName(username);  
        logger.debug("----->>userInfo="+userInfo);  
        if (userInfo == null) {  
        	throw new AccountException("帐号或密码不正确！");
        }  
        if (userInfo.getStatus() == -1) { //账户被禁止 
            throw new DisabledAccountException("帐号被禁止！");  
        }
        if (userInfo.getStatus() == 0) { //账户冻结  
            throw new LockedAccountException("帐号被锁定！");  
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
                userInfo, //用户名  
                userInfo.getPassword(), //密码  
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt  
                getName()  //realm name  
        );  
        return authenticationInfo;  
    }  
  
}  
