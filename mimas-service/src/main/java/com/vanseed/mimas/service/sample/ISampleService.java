/**
 * 
 */
package com.vanseed.mimas.service.sample;

import java.math.BigDecimal;
import java.util.List;

import com.vanseed.mimas.domain.model.user.Sample;


/**
 * @author leon
 *
 */
public interface ISampleService {
	Sample addSample(Sample sample);

	Sample modifySample(Sample sample);
	
	List<Sample> findAll();

	Sample findSampleById(Long id);
	
	Sample findSampleByName(String name);
	
	Sample modifyAmount(Long sampleId, BigDecimal amount);
	
	Sample testTxn();	
}
