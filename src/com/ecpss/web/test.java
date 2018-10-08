package com.ecpss.web;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class test {
	private static String hexString = "0123456789ABCDEF";

	public static void main(String[] args) {
//		String bytes = "00 8E 60 00 00 05 00 31 39 33 31 00 02 30 31 30 30 32 00 10 34 38 38 38 38 38 38 38 38 38 38 38 38 38 38 38 30 30 34 00 0C 30 30 30 30 30 30 30 30 30 31 30 30 30 31 31 00 06 30 30 30 30 30 31 30 31 33 00 0E 32 30 31 31 30 36 32 32 31 38 30 30 30 30 30 31 34 00 04 31 34 30 37 30 34 31 00 08 31 32 33 34 35 36 37 38 30 34 32 00 0F 31 30 34 31 31 30 30 35 33 31 31 30 30 30 31 30 34 38 00 03 33 32 31 30 36 31 00 06 30 30 30 30 30 31";
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(
//				bytes.length() / 2);
//		// 将每2位16进制整数组装成一个字节
//		for (int i = 0; i < bytes.length(); i += 2)
//			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
//					.indexOf(bytes.charAt(i + 1))));
//		String a = new String(baos.toByteArray());
//
//		System.out.println(a);

//		  String a = "127";
//	        char[] ch = a.toCharArray();
//	        System.out.println(ch);
//	        for (int i = 0; i < ch.length; i++) {
//	            printHex(ch[i]);
//	        }
//	        
//	        System.out.println(String.format("%06d", Math.round(Math.random() * 999999)));
//	        System.out.println("1010000".substring(2, 3));
		
		String str="     PaymentOrderNo=1002117913282854066&MerNo=1002&dateTime=20110709012715&BillNo=2662117913271590623&Currency=3&Amount=65.24&Succeed=0&Result=Your transaction was not approved by your bank.Please contact your Bank #(04)&MD5info=FE2DEF9BC5E6053F7C41561F7C5845E6";
		Map responseFields = createMapFromResponse(str.trim());
		// 金额:
		String amount = VcpUtil.null2unknown("PaymentOrderNo",
				responseFields);
		System.out.println(amount);

	}
	private static Map createMapFromResponse(String queryString) {
		Map map = new HashMap();
		StringTokenizer st = new StringTokenizer(queryString, "&");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			int i = token.indexOf('=');
			if (i > 0) {
				try {
					String key = token.substring(0, i);
					String value = URLDecoder.decode(token.substring(i + 1,
							token.length()));
					map.put(key, value);
				} catch (Exception ex) {
					// Do Nothing and keep looping through data
				}
			}
		}
		return map;
	}
	 public static void printHex(int i) {
	        String str = Integer.toString(i, 16); // 十六进制
	        System.out.println(i + "的十六进制:" + str);
	    }

//	public byte[] makeGDLAWriteRtFrame(Map<Character, String> para, int tn,
//			int aut, String pwd, String rtuAddr, int validMins) {
//		byte[] buf = new byte[100];
//		int len = 0;
//		len += makeWriteHead(buf, 0, tn, aut, pwd);
//		Calendar now = Calendar.getInstance();
//		buf[len++] = MsgUtil.HexToBCD((byte) (now.get(Calendar.YEAR) % 100));
//		buf[len++] = MsgUtil.HexToBCD((byte) ((now.get(Calendar.MONTH) + 1)));
//		buf[len++] = MsgUtil.HexToBCD((byte) (now.get(Calendar.DAY_OF_MONTH)));
//		buf[len++] = MsgUtil.HexToBCD((byte) (now.get(Calendar.HOUR_OF_DAY)));
//		buf[len++] = MsgUtil.HexToBCD((byte) (now.get(Calendar.MINUTE)));
//		buf[len++] = MsgUtil.HexToBCD((byte) (validMins & 0xFF));
//		len += FillItemData(buf, len, para);
//		return makeFrame((byte) 0x07, rtuAddr, buf, len);
//	}
}
