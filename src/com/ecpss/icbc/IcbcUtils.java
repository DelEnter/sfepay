package com.ecpss.icbc;

import java.io.FileInputStream;

import cn.com.infosec.icbc.ReturnValue;

public class IcbcUtils {
	
	//private static final String SignMsgBase64 = null;

	/**
	 * 转换成BASE64编码
	 * @param baseStr
	 * @return
	 */
	public String getBase64Str(String baseStr){
		byte[] byte64 = ReturnValue.base64enc(baseStr.getBytes());
	    String base64str =new String(byte64).toString();
	    return base64str;
	}
	
	/**
	 * 订单签名数据 获取 merSignMsg
	 * @param baseStr
	 * @param pwd
	 * @return
	 */
	public String getSignMsgBase64(String baseStr,String pwd){
		String SignMsgBase64 = null;
		try{
			byte[] byteSrc = baseStr.getBytes();
			char[] keyPass = pwd.toCharArray();
			FileInputStream in1 = new FileInputStream("d:\\anxun.cer");
			byte[] bcert = new byte[in1.available()];
			in1.read(bcert);
			in1.close();
			FileInputStream in2 = new FileInputStream("d:\\anxun.key");
			byte[] bkey = new byte[in2.available()];
			in2.read(bkey);
			in2.close();
		
		    byte[] sign =ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass);
		    if (sign==null) {
		    	System.out.println("签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
		    }else{
		    	System.out.println("签名成功");
		   
			    byte[] EncSign = ReturnValue.base64enc(sign);
			    SignMsgBase64=new String(EncSign).toString();
				
			    //byte[] EncCert=ReturnValue.base64enc(bcert);
				//CertBase64=new String(EncCert).toString();
			}
			
		}catch (Exception e){
			System.out.println(e);
		}
		return SignMsgBase64;
	}
	/**
	 * 订单签名数据 获取 merCertMsg
	 * @param baseStr
	 * @param pwd
	 * @return
	 */
	public String getCertMsgBase64(String baseStr,String pwd){
		String CertBase64 = null;
		try{
			byte[] byteSrc = baseStr.getBytes();
			char[] keyPass = pwd.toCharArray();
			FileInputStream in1 = new FileInputStream("d:\\anxun.cer");
			byte[] bcert = new byte[in1.available()];
			in1.read(bcert);
			in1.close();
			FileInputStream in2 = new FileInputStream("d:\\anxun.key");
			byte[] bkey = new byte[in2.available()];
			in2.read(bkey);
			in2.close();
			
			byte[] sign =ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass);
			if (sign==null) {
				System.out.println("签名失败,签名返回为空。请检查证书私钥和私钥保护口令是否正确。");
			}else{
				System.out.println("签名成功");
				//byte[] EncSign = ReturnValue.base64enc(sign);
				//SignMsgBase64=new String(EncSign).toString();
				byte[] EncCert=ReturnValue.base64enc(bcert);
				CertBase64=new String(EncCert).toString();
			}
			
		}catch (Exception e){
			System.out.println(e);
		}
		return CertBase64;
	}
	
	/**
	 * 签名信息BASE64解码
	 * @param EncSign
	 * @return
	 */
	public String signdec(String EncSign){
		String base64str = null;
		try{
			byte[] byteEncSign = EncSign.getBytes();
			byte[] DecSign = ReturnValue.base64dec(byteEncSign);
		    if (DecSign!=null){
		    	base64str =new String(DecSign).toString();
		    }
			
		}catch (Exception e){
			System.out.println(e);
		}
		return base64str;
	}
	
	/**
	 * 对银行返回的信息 验签
	 * @return
	 */
	public boolean verifySignres(String tranData,String DecCertStr,String DecSignStr){
		System.out.println("<font face='Arial' size='4' color='Green'>明文：</font>"+tranData+"<br>");
		String password = "12345678";
		try{
			byte[] byteSrc = tranData.getBytes();
			char[] keyPass = password.toCharArray();
			
			FileInputStream in1 = new FileInputStream("d:\\anxun.cer");
			byte[] bcert = new byte[in1.available()];
			in1.read(bcert);
			in1.close();
			FileInputStream in2 = new FileInputStream("d:\\anxun.key");
			byte[] bkey = new byte[in2.available()];
			in2.read(bkey);
			in2.close();
			

		    byte[] sign =ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass);
		    if (sign==null) {
		    	System.out.println("<font face='Arial' size='4' color='Red'>签名失败,签名返回为空。<br>请检查证书私钥和私钥保护口令是否正确。</font><br>");
		    }else{
		    	System.out.println("<font face='Arial' size='4' color='Green'>签名成功</font><br>");
		   
			    byte[] EncSign = ReturnValue.base64enc(sign);
			    String SignMsgBase64=new String(EncSign).toString();
			    System.out.println("<font face='Arial' size='4' color='Green'>签名信息BASE64编码：</font>"+SignMsgBase64.substring(0,100)+"...<br>");
			    
				byte[] EncCert=ReturnValue.base64enc(bcert);
				String CertBase64=new String(EncCert).toString();
				System.out.println("<font face='Arial' size='4' color='Green'>证书公钥BASE64编码：</font>"+CertBase64.substring(0,100)+"...<br>");
			
				byte[] DecSign = ReturnValue.base64dec(EncSign);
			    if (DecSign!=null){
			    	System.out.println("<font face='Arial' size='4' color='Green'>签名信息BASE64解码成功</font><br>");
			    	byte[] DecCert = ReturnValue.base64dec(EncCert);
			    	if (DecCert!=null){
			    		System.out.println("<font face='Arial' size='4' color='Green'>证书公钥BASE64解码成功</font><br>");
			    		int a=ReturnValue.verifySign(byteSrc,byteSrc.length,DecCert,DecSign);
			    		if (a==0) System.out.println("<font face='Arial' size='4' color='Green'>验签成功</font><br>");
			    		else System.out.println("<font face='Arial' size='4' color='Red'>验签失败<br>验证返回码：</font><br>"+a);	    		
			    	}else System.out.println("<font face='Arial' size='4' color='Red'>证书BASE64解码失败</font><br>");
			    }else System.out.println("<font face='Arial' size='4' color='Red'>签名信息BASE64解码失败</font><br>");	
			}
			
		}catch (Exception e){
			System.out.println(e);
		}
		return false;
	}
	
}
