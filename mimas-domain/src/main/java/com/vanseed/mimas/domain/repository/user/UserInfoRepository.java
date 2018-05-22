/**
 * 
 */
package com.vanseed.mimas.domain.repository.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vanseed.mimas.domain.model.user.UserInfo;


/**
 * @author leon
 *
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>,JpaSpecificationExecutor<UserInfo> {
	
	public UserInfo findByName(String name);
	
	public Page<UserInfo> findByStatus(Integer status,Pageable pageable);
	
	public List<UserInfo> findByIdIn(List<Long> ids);
	
}
