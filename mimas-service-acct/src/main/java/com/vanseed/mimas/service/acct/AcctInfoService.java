/**
 * 
 */
package com.vanseed.mimas.service.acct;

import java.util.List;

import com.vanseed.mimas.domain.model.acct.AcctInfo;
import com.vanseed.mimas.domain.repository.acct.AcctInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leon
 *
 */
@Component("acctInfoService")
public class AcctInfoService implements IAcctInfoService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());  
		
	@Autowired
	private AcctInfoRepository acctInfoRepository;

	@Override
	public AcctInfo addAccount(AcctInfo acctInfo) {
        acctInfoRepository.save(acctInfo);
		return acctInfo;
	}

	@Override
	public AcctInfo modifyAccount(AcctInfo acctInfo) {
        acctInfoRepository.save(acctInfo);
		return acctInfo;
	}

	@Override
	public AcctInfo findAccountById(Long id) {
        AcctInfo acctInfo = acctInfoRepository.findOne(id);
		return acctInfo;
	}
	
	@Override
	public AcctInfo findAccountByNo(String acctNo) {
        AcctInfo acctInfo = acctInfoRepository.findByAcctNo(acctNo);
		return acctInfo;
	}
	
	@Override
	public List<AcctInfo> findAll() {
		List<AcctInfo> list = acctInfoRepository.findAll();
		return list;
	}
	
}
