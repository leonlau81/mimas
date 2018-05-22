/**
 * 
 */
package com.vanseed.mimas.service.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.common.utils.EncryptUtils;
import com.vanseed.mimas.domain.model.user.UserInfo;
import com.vanseed.mimas.domain.repository.user.UserInfoRepository;
import com.vanseed.mimas.service.base.CombBaseService;

/**
 * @author leon
 *
 */
@Component("userInfoService")
public class UserInfoService extends CombBaseService implements IUserInfoService {
	
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
