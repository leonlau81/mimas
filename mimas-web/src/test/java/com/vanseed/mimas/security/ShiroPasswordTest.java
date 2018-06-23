package com.vanseed.mimas.security;

import com.vanseed.mimas.common.utils.EncryptUtils;
import com.vanseed.mimas.web.security.ShiroUser;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroPasswordTest
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static String PASSWORED_HASH_ALGORITHM="SHA-1";
    private static Integer PASSWORED_HASH_INTERATIONS=2;
    private static Integer PASSWORED_SALT_LENGTH=8;


    @Test
	public void TestEncryptPassword() throws Exception 
	{
		try
		{
            String strSalt = EncryptUtils.generateSalt(8);
			
			String plainPassword = "123456";

			String hashPassword = EncryptUtils.SHA1(plainPassword, strSalt, 2);
			logger.info("plainPassword="+strSalt);
			logger.info("salt="+strSalt);
			logger.info("encryptPassword="+hashPassword);
			//pair_1:   plainPassword=123456  salt=e88ea27c5903f017  encryptPassword=d49684254474152e991a3976c60935d91fa34a16
			//pair_2: 	plainPassword=123456  salt=f19ed79d91947ba5  encryptPassword=9b1466ba4ec5a3c3be05d448a7539b6dccce594e
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

    @Test
    public void HashTest() throws Exception
    {
        try
        {
            String strTest = "654321";

            String md5 = EncryptUtils.MD5(strTest);
            String sha = EncryptUtils.SHA(strTest);
            String sha1 = EncryptUtils.SHA1(strTest);

            String aes = EncryptUtils.encodeHex( EncryptUtils.encryptAES(strTest, "1234567812345678") );

            logger.info("md5="+md5);
            logger.info("sha="+sha);
            logger.info("sha1="+sha1);
            logger.info("aes="+aes);

        }
        catch(Exception e)
        {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
	
	@Test
	public void TestShiroPassword() throws Exception 
	{
		try
		{
			HashedCredentialsMatcher matcher = new HashedCredentialsMatcher( PASSWORED_HASH_ALGORITHM );
			matcher.setHashIterations( PASSWORED_HASH_INTERATIONS );
			
			UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
			
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
					new ShiroUser("admin", "管理员"),
					"9b1466ba4ec5a3c3be05d448a7539b6dccce594e",
					ByteSource.Util.bytes(EncryptUtils.decodeHex("f19ed79d91947ba5")),
					"test");
			
			boolean b = matcher.doCredentialsMatch(token, authenticationInfo);
			
			assert(b);		
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
