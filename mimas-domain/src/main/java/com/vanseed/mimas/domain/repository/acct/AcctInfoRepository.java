/**
 * 
 */
package com.vanseed.mimas.domain.repository.acct;


import com.vanseed.mimas.domain.model.acct.AcctInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author leon
 *
 */
public interface AcctInfoRepository extends JpaRepository<AcctInfo, Long>,JpaSpecificationExecutor<AcctInfo> {

	public AcctInfo findByAcctNo(String acctNo);

	@Transactional
	@Modifying
	@Query("update AcctInfo s set s.acctName = ?2 where s.id = ?1")
	public void updateAdminUserWithMobile(Long id, String acctName);

	public Page<AcctInfo> findByStatus(Integer status, Pageable pageable);
	
	public List<AcctInfo> findByIdIn(List<Long> ids);
	
}
