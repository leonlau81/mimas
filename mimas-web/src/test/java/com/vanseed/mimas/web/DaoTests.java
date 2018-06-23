package com.vanseed.mimas.web;

import com.vanseed.mimas.domain.model.acct.AcctInfo;
import com.vanseed.mimas.domain.mybatis.acct.AcctMapper;
import com.vanseed.mimas.domain.repository.acct.AcctInfoRepository;
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

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

	@Autowired
	private SampleRepository sampleRepository;
	
	@Autowired
	private SampleMapper sampleMapper;

    @Autowired
    private AcctInfoRepository acctRepository;

    @Autowired
    private AcctMapper acctMapper;

	@Before
	public void setUp() {
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
    @Rollback(true)
	public void testInsert() throws Exception {

		sampleRepository.save(new Sample("aaa"));
		sampleRepository.save(new Sample("bbb"));
		sampleRepository.save(new Sample("ccc"));
		
		Assert.assertEquals(4, sampleRepository.findAll().size());
	}

    @Test
    @Transactional
    @Rollback(false)
    public void testJTA() throws Exception {

        AcctInfo acct = new AcctInfo();
        acct.setUserId(1l);
        acct.setAcctNo("9555000000001002");
        acct.setAcctName("钢铁侠定期户");
        acct.setAcctType(1);
        acct.setStatus(1);
        acct.setCreateId(1l);
        acct.setUpdateId(1l);
        acct.setCreateTime(new Date());
        acct.setUpdateTime(new Date());

        Sample sample = new Sample("ddd");

        sample = sampleRepository.save(sample);
        acct = acctRepository.save(acct);

        Sample sampleNew = sampleRepository.findByName("ddd");
        AcctInfo acctNew = acctRepository.findByAcctNo("9555000000001002");

        Assert.assertEquals(1, sampleNew.getStatus().intValue() );
        Assert.assertEquals(1, acctNew.getStatus().intValue() );
        Assert.assertTrue(true);
    }

    @Test
    @Transactional("userTxn") //有没有办法两个事务管理器一起起作用
    @Rollback(true)
    public void testMultiDS() throws Exception {

        AcctInfo acct = new AcctInfo();
        acct.setUserId(1l);
        acct.setAcctNo("9555000000002222");
        acct.setAcctName("钢铁侠定期户");
        acct.setStatus(1);
        acct.setCreateId(1l);
        acct.setUpdateId(1l);
        acct.setCreateTime(new Date());
        acct.setUpdateTime(new Date());

        Sample sample = new Sample("ddd");

        sampleRepository.save(sample);
        acctRepository.save(acct);

        Sample sampleNew = sampleRepository.findByName("ddd");
        AcctInfo acctNew = acctRepository.findByAcctNo("9555000000002222");

        Assert.assertEquals(1, sampleNew.getStatus().intValue() );
        Assert.assertEquals(1, acctNew.getStatus().intValue() );
        Assert.assertTrue(true);
    }
	
	@Test
	@Transactional
	@Rollback(false)
	public void findByName() throws Exception {
		//sampleMapper.insert("operation", new BigDecimal(11.11));
		Sample u1 = sampleMapper.findByName("operation");
		Sample u2 = sampleRepository.findByName("operation");
		Assert.assertEquals(u1.getName(), u2.getName());
	}
	
	//翻页测试
	@Test
	public void findByPage() throws Exception {
		PageHelper.startPage(1, 2, true);
		Page<Sample> listUser = sampleMapper.findByStatusPaging(1);
		PageInfo<Sample> pageInfo = new PageInfo<>(listUser);
		pageInfo.getTotalCounts();
		Assert.assertEquals(pageInfo.getTotalCounts(), 4l);
	}
	
	
	//通过xml配置动态sql查询测试
	@Test
	public void findByMapping() throws Exception {
		//sampleMapper.insert("operation", new BigDecimal(11.11));
		Sample u = sampleMapper.findSampleMapping("operation",1);
		Assert.assertEquals("operation", u.getName());
	}

}
