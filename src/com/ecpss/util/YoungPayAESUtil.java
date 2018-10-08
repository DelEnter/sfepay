package com.ecpss.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * «©√˚—È«©
 */
public class YoungPayAESUtil {
	private static final String AESTYPE = "AES/ECB/PKCS5Padding";

	public static String AES_Encrypt(String keyStr, String plainText) {
		byte[] encrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypt = cipher.doFinal(plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(encrypt));
	}

	public static String AES_Decrypt(String keyStr, String encryptData) {
		byte[] decrypt = null;
		try {
			Key key = generateKey(keyStr);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(decrypt).trim();
	}

	private static Key generateKey(String key) throws Exception {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(HexString2Bytes(key), "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
		byte ret = (byte) (_b0 | _b1);
		//char a = (char) ret;
		return ret;
	}

	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < src.length() / 2; ++i) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

	public static void main(String[] args) {
		MD5 md5=new MD5(); 
		String key = md5.getMD5ofStr("0YVvOfCaqOyoa6kZA6B6YhsC0rFVQdrx");
		System.err.println(key);
		System.err.println(AES_Encrypt(key, "4283428306045009"));
	}
}
