/**
 * 
 */
package com.vanseed.mimas.service.acct;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vanseed.mimas.domain.model.user.UserInfo;
import com.vanseed.mimas.domain.repository.user.UserInfoRepository;
import com.vanseed.mimas.service.base.BaseService;

/**
 * @author leon
 *
 */
@Component("userInfoService")
public class UserInfoService extends BaseService implements IUserInfoService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());  
		
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo addUser(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
		return userInfo;
	}

	@Override
	public UserInfo modifyUser(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
		return userInfo;
	}

	@Override
	public UserInfo findUserById(Long id) {
		UserInfo userInfo = userInfoRepository.findOne(id);
		return userInfo;
	}
	
	@Override
	public UserInfo findUserByName(String name) {
		UserInfo userInfo = userInfoRepository.findByName(name);
		return userInfo;
	}
	
	@Override
	public List<UserInfo> findAll() {
		List<UserInfo> list = userInfoRepository.findAll();
		return list;
	}
	
}
