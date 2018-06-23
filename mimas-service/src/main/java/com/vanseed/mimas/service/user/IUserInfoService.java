/**
 * 
 */
package com.vanseed.mimas.service.user;

import java.util.List;

import com.vanseed.mimas.domain.model.user.UserInfo;


/**
 * @author leon
 *
 */
public interface IUserInfoService {

	public UserInfo addUser(UserInfo userInfo);

	public UserInfo modifyUser(UserInfo userInfo);

	public UserInfo findUserById(Long id);
	
	public UserInfo findUserByName(String name);
	
	public List<UserInfo> findAll();
}
