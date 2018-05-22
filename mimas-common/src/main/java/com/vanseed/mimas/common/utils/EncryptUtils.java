package com.vanseed.mimas.common.utils;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@SuppressWarnings("restriction")
public class EncryptUtils {
	private static Logger logger = Logger.getLogger(EncryptUtils.class);
	
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());

			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String SHA(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}
		return "";
	}

	public static String MD5(String input) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(input.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}

		return "";
	}

	public static byte[] encryptAES(String content, String password) {
		try {
			if(StringUtils.isEmpty(password) || password.length() <16 || content == null) {
				return null;
			}

			String raw = password.substring(0, 16);
			SecretKeySpec secretKey = new SecretKeySpec(raw.getBytes(), "AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			
			byte[] result = cipher.doFinal(byteContent);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static byte[] decryptAES(byte[] content, String password) {
		try {
			if(StringUtils.isEmpty(password) || password.length() <16 || content == null) {
				return null;
			}
			
			String raw = password.substring(0, 16);
			SecretKeySpec secretKey = new SecretKeySpec(raw.getBytes(), "AES");
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			byte[] result = cipher.doFinal(content);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		return null;
	}
	
	/**
	 * DES 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptDES(String content, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		byte[] byteContent = content.getBytes("utf-8");

		return cipher.doFinal(byteContent);
	}
	
	/**
	 * DES解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptDES(byte[] data, String key) throws Exception {
		Key k = toKey(decryptBASE64(key));

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, k);

		return cipher.doFinal(data);
	}

	public static byte[] decryptBASE64(String key) {
		try {
			return new BASE64Decoder().decodeBuffer(key);
		} catch (Exception e) {
		}
		return null;
	}

	public static String encryptBASE64(byte[] key) {
		return (new BASE64Encoder()).encodeBuffer(key);
		
	}
	
	public static String byte2hex(byte[] bytes) {
		StringBuffer hexString = new StringBuffer();
		
		// 字节数组转换为 十六进制 数
		for (int i = 0; i < bytes.length; i++) {
			String shaHex = Integer.toHexString(bytes[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		
		return hexString.toString();
	}
	
	/**
	 * 转换密钥<br>
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static Key toKey(byte[] key) throws Exception 
	{
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(dks);

		// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
		// SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);

		return secretKey;
	}
}
