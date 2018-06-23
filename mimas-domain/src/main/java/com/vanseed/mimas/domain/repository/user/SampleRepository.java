/**
 * 
 */
package com.vanseed.mimas.domain.repository.user;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vanseed.mimas.domain.model.user.Sample;


/**
 * @author leon
 *
 */
public interface SampleRepository extends JpaRepository<Sample, Long>,JpaSpecificationExecutor<Sample> {
	
	public Sample findByName(String name);

	@Transactional
	@Modifying 
	@Query("update Sample s set s.name = ?2, s.amount = ?3 where s.id = ?1")
	public void updateAdminUserWithMobile(Long id, String name, BigDecimal amount);
	
	public Page<Sample> findByStatus(Integer status,Pageable pageable);
	
	public List<Sample> findByIdIn(List<Long> ids);
	
}
