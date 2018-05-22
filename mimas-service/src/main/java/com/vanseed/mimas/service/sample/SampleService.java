/**
 * 
 */
package com.vanseed.mimas.service.sample;

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
import com.vanseed.mimas.domain.model.sample.Sample;
import com.vanseed.mimas.domain.mybatis.mapper.SampleMapper;
import com.vanseed.mimas.domain.repository.sample.SampleRepository;
import com.vanseed.mimas.service.base.CombBaseService;

/**
 * @author leon
 *
 */
@Component("sampleService")
public class SampleService extends CombBaseService implements ISampleService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());  
		
	@Autowired
	private SampleRepository sampleRepository;
	
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
		sample = sampleRepository.save(new Sample("aaa"));
		sampleMapper.insert("BBB", new BigDecimal(11.11));		
		
		if(sample!=null) {
			//throw new ServiceException("test txn exception");
		}
			
		return sample;
	}

}
