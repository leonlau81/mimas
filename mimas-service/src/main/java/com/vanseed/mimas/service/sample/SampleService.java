/**
 * 
 */
package com.vanseed.mimas.service.sample;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.vanseed.mimas.domain.model.acct.AcctInfo;
import com.vanseed.mimas.domain.repository.acct.AcctInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vanseed.mimas.common.exception.ServiceException;
import com.vanseed.mimas.domain.model.user.Sample;
import com.vanseed.mimas.domain.mybatis.user.SampleMapper;
import com.vanseed.mimas.domain.repository.user.SampleRepository;
import com.vanseed.mimas.service.base.BaseService;

/**
 * @author leon
 *
 */
@Component("sampleService")
public class SampleService extends BaseService implements ISampleService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());  
		
	@Autowired
	private SampleRepository sampleRepository;

    @Autowired
    private AcctInfoRepository acctRepository;
	
	@Autowired
	private SampleMapper sampleMapper;

	@Override
	public Sample addSample(Sample sample) {
		sampleRepository.save(sample);
		return sample;
	}

	@Override
	public Sample modifySample(Sample sample) {
		sampleRepository.save(sample);
		return sample;
	}

	@Override
	public Sample findSampleById(Long id) {
		Sample sample = sampleRepository.findOne(id);
		return sample;
	}
	
	@Override
	public Sample findSampleByName(String name) {
		Sample sample = sampleRepository.findByName(name);
		return sample;
	}
	
	@Override
	public List<Sample> findAll() {
		List<Sample> list = sampleRepository.findAll();
		return list;
	}
	
	@Transactional
	@Override
	public Sample modifyAmount(Long id, BigDecimal amount) {
		if (id == null) {
			throw new ServiceException("service.sample.notExisted", "");
		}

		Sample sample = sampleRepository.findOne(id);
		if (sample == null) {
			throw new ServiceException("service.sample.notExisted", "");
		}

		sample.setAmount(amount);

		try {
			sample = sampleRepository.save(sample);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return sample;
	}
	
	
	
	@Transactional
	@Override
	public Sample testTxn() {
		Sample sample = null;
		sample = sampleRepository.save(new Sample("ddd"));
		//sampleMapper.insert("BBB", new BigDecimal(11.11));

        AcctInfo acct = new AcctInfo();
        acct.setUserId(1l);
        acct.setAcctNo("9555000000001002");
        acct.setAcctName("钢铁侠定期户");
        acct.setStatus(1);
        acct.setCreateId(1l);
        acct.setUpdateId(1l);
        acct.setCreateTime(new Date());
        acct.setUpdateTime(new Date());

        acct = acctRepository.save(acct);
		
		if(sample!=null) {
			throw new ServiceException("test txn exception");
		}
			
		return sample;
	}

}
