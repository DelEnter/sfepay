package com.ecpss.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.opensymphony.xwork2.util.Key;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ThreeDES {
	 	private Cipher cipher = null;   
	    // base64编码   
	    private BASE64Encoder base64Encode = new BASE64Encoder();   
	    private BASE64Decoder base64Decode = new BASE64Decoder();   
	    // 密钥   
	    private String key=""; 
	    
	    public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		private final static String algorithm = "DESede/ECB/PKCS7Padding"; 
	    private final Cipher initCipher(int mode)   
	    {   
	        try  
	        {   
	            // 添加新安全算法:PKCS7   
	            Security.addProvider(new BouncyCastleProvider());    
//	            SecretKey desKey = new SecretKeySpec((new BASE64Decoder()).decodeBuffer(key), algorithm);   
	            SecretKey desKey = new SecretKeySpec(key.getBytes("UTF-8"), algorithm);   
	            Cipher tcipher = Cipher.getInstance(algorithm);   
	            tcipher.init(mode, desKey);   
	            return tcipher;   
	        } catch (Exception e)   
	        {   
	            e.printStackTrace();   
	        }   
	        return null;   
	    }   
	  
	    /**  
	     * 加密以charset编码做为密文  
	     *   
	     * @param src  
	     *            明文  
	     * @param charset  
	     *            编码,例：UTF8、BASE64  
	     * @return  
	     */  
	    public String encrypt(String src, String charset)   
	    {   
	        try  
	        {   
	            return URLEncoder.encode(encrypt(src), charset);   
	        } catch (Exception e)   
	        {   
	            e.printStackTrace();   
	        }   
	        return null;   
	    }   
	  
	    /**  
	     * 解密  
	     * @param src 二进制数组  
	     * @return  
	     * @throws Exception  
	     */  
	    private byte[] decrypt(byte[] src) throws Exception   
	    {   
	        cipher = initCipher(Cipher.DECRYPT_MODE);   
	        return cipher.doFinal(src);   
	    }   
	    /**  
	     * 解密  
	     * @param src 密文  
	     * @return  
	     * @throws Exception  
	     */  
	    public  String decrypt(String src) throws Exception   
	    {   
	        byte[] bt=base64Decode.decodeBuffer(src);   
	        byte[] sbt=decrypt(bt);   
	        return new String(sbt,"UTF-8");   
	    }   
	    /**  
	     * 加密以base64做为密文  
	     *   
	     * @param src  
	     *            明文  
	     * @return 密文  
	     */  
	    public String encrypt(String src)   
	    {   
	        cipher = initCipher(Cipher.ENCRYPT_MODE);   
	        byte[] dd = encrypt(src.getBytes());   
	        String str = base64Encode.encode(dd);   
	        str = str.replaceAll("\r", "");   
	        str = str.replaceAll("\n", "");   
	        return str;   
	    }   
	  
	    /**  
	     *   
	     * @param src  
	     * @return  
	     */  
	    public byte[] encrypt(byte[] src)   
	    {   
	        try  
	        {   
	            return cipher.doFinal(src);   
	        } catch (Exception e)   
	        {   
	            e.printStackTrace();   
	        }   
	        return null;   
	    }   
	  
	    public static void main(String[] args) throws Exception   
	    {   
	        ThreeDES des = new ThreeDES(); 
	        des.setKey("1BoHbM2YrhsOeui0");
	        Security.addProvider(new com.sun.crypto.provider.SunJCE());     
	        System.out.println(des.decrypt("4C5SyMbgL5AS9f1W7TY0vmiTMkPXPwlEOP9lAMvD8zLfKOYsZavNHuxy0Abx8bBfQM4Fjc2Yp0M0THMGrQXzwstU51urhSChu1C+VLSqaBtB0cv3HsGf6S60N6uInVH6vNgWHd1DpGAaPeErFFjUlK8YzpJ5wUkO9Eppe0YTojrAr9A+KhmIVg=="));   
	    }   

}
