package com.vanseed.mimas.common.utils;

import java.security.*;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.vanseed.mimas.common.exception.ExceptionUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@SuppressWarnings("restriction")
public class EncryptUtils {
	private static Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

    private static SecureRandom random = new SecureRandom();

    private static final String SHA = "SHA";
    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";

    /**
     * SHA-1 散列
     */
    public static String SHA1(String input) {
        return SHA1(input, null, 1);
    }

    /**
     * SHA-1 散列
     */
    public static String SHA1(String input, String salt, int iterations) {
        return encodeHex( digest(input.getBytes(), SHA1, salt!=null?salt.getBytes():null, iterations) );
    }

    /**
     * SHA 散列
     */
    public static String SHA(String input) {
        return encodeHex(digest(input.getBytes(), SHA, null, 1));
    }

    /**
     * MD5 散列
     */
	public static String MD5(String input) {
	    return encodeHex(digest(input.getBytes(), MD5, null, 1));
	}

    /**
     * 对字符串进行散列, 支持md5, sha, sha1算法.
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

	/**
	 * AES 加密
	 */
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

	/**
	 * AES 解密
	 */
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
	 */
	public static byte[] encryptDES(String content, String key) throws Exception {
		Key k = toKey(decodeBASE64(key));
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		byte[] byteContent = content.getBytes("utf-8");

		return cipher.doFinal(byteContent);
	}
	
	/**
	 * DES解密
	 */
	public static byte[] decryptDES(byte[] data, String key) throws Exception {
		Key k = toKey(decodeBASE64(key));

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, k);

		return cipher.doFinal(data);
	}

    /**
     * BASE64遍码. URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     */
    public static String encodeBASE64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

	/**
	 * BASE64解码.
	 */
	public static byte[] decodeBASE64(String input) {
		try {
            return Base64.decodeBase64(input);
		} catch (Exception e) {
		}
		return null;
	}

    /**
     * Hex编码.
     */
    public static String encodeHex(byte[] input)
    {
        return Hex.encodeHexString(input);
    }

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input)
	{
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			logger.error("Hex解码失败，请检查!", e);
			throw new RuntimeException(e);
		}
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

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param numBytes byte数组的大小
     */
    public static String generateSalt(int numBytes) {
        //Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return encodeHex(bytes);
    }
}
