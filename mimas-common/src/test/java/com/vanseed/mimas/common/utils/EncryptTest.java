package com.vanseed.mimas.common.utils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings("restriction")
public class EncryptTest {
	private static Logger logger = Logger.getLogger(EncryptTest.class);
	
	@Test
	public void md5Test() throws Exception {
		String encStr = EncryptUtils.MD5("admin");
		//21232f297a57a5a743894a0e4a801fc3
		logger.info("md5('admin')="+encStr);
		Assert.assertEquals( encStr, EncryptUtils.MD5("admin"));
	}
}
