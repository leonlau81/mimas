/**
 * 
 */
package com.vanseed.mimas.service.user;

import java.util.List;
import java.util.Map;

import com.vanseed.mimas.domain.model.user.UserInfo;


/**
 * @author leon
 *
 */
public interface IUserInfoService {

	public UserInfo addUser(UserInfo userInfo);

    public UserInfo register(Map<String, Object> paraMap);

	public UserInfo modifyUser(UserInfo userInfo);

	public UserInfo findUserById(Long id);
	
	public UserInfo findUserByName(String name);
	
	public List<UserInfo> findAll();
}
