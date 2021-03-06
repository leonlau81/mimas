package com.vanseed.mimas.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vanseed.mimas.common.page.PageInfo;
import com.vanseed.mimas.domain.model.user.Sample;
import com.vanseed.mimas.domain.mybatis.user.SampleMapper;
import com.vanseed.mimas.domain.repository.user.SampleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

	@Autowired
	private SampleRepository sampleRepository;
	
	@Autowired
	private SampleMapper sampleMapper;

	@Before
	public void setUp() {
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
	@Rollback
	public void test() throws Exception {

		sampleRepository.save(new Sample("aaa"));
		sampleRepository.save(new Sample("bbb"));
		sampleRepository.save(new Sample("ccc"));
		
		Assert.assertEquals(4, sampleRepository.findAll().size());
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void findByName() throws Exception {
		//sampleMapper.insert("operation", new BigDecimal(11.11));
		Sample u = sampleMapper.findByName("operation");
		Assert.assertEquals("operation", u.getName());
	}
	
	//翻页测试
	@Test
	public void findByPage() throws Exception {
		PageHelper.startPage(1, 0, true);
		Page<Sample> listUser = sampleMapper.findByStatusPaging(1);
		PageInfo<Sample> pageInfo = new PageInfo<>(listUser);
		pageInfo.getTotalCounts();
		Assert.assertEquals(pageInfo.getTotalCounts(), 3l);
	}
	
	
	//通过xml配置动态sql查询测试
	@Test
	public void findByMapping() throws Exception {
		//sampleMapper.insert("operation", new BigDecimal(11.11));
		Sample u = sampleMapper.findSampleMapping("operation",1);
		Assert.assertEquals("operation", u.getName());
	}

}
