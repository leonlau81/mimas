/**
 * 
 */
package com.vanseed.mimas.service.acct;

import java.util.List;

import com.vanseed.mimas.domain.model.acct.AcctInfo;


/**
 * @author leon
 *
 */
public interface IAcctInfoService {

	public AcctInfo addAccount(AcctInfo userInfo);

	public AcctInfo modifyAccount(AcctInfo userInfo);

	public AcctInfo findAccountById(Long id);
	
	public AcctInfo findAccountByNo(String acctNo);
	
	public List<AcctInfo> findAll();
}
