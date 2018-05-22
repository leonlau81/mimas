package com.vanseed.mimas.web;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.vanseed.mimas.common.servlet.support.MobileSession;
import com.vanseed.mimas.service.base.CombSessionService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

	@Autowired
	private CombSessionService sessionService;

	@Before
	public void setUp() {
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	//@Rollback
	public void testSession() throws Exception {

		MobileSession session = new MobileSession();
		session.setSessionId("testSession_0001");
		session.setUserId(1l);
		session.setLastAccessTime((new Date()).getTime());
		sessionService.setSession(session);
		
		Assert.assertEquals(Long.valueOf(1), sessionService.getSession("testSession_0001").getUserId());
	}
}
