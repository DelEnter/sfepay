package com.ecpss.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.ecpss.util.MD5;

public class STest {
	public static void main(String[] args) {
		
		
		//MD5 md5 = new MD5();
		//String a = md5.getMD5ofStr("5004213720821025.01enhttps://securepayments.telemoneyworld.com/easypay2/wedopayendmessage.do]rOHEFAd");
		//System.out.println(a);
		
	    String MD5key; //MD5key值
	    MD5key = "]rOHEFAd";

	    String MerNo;   //商户ID
	    MerNo = "50042";

	    String BillNo;  //订单编号
	    BillNo = "1372082";
	    String Currency;    //币种
	    Currency = "10";
	    String Amount;  //支付金额
	    Amount = "25.01";

	    String Language;    //支付语言
	    Language = "en";

	    String ReturnURL;   //返回地址
	    
	    ReturnURL = "https://securepayments.telemoneyworld.com/easypay2/wedopayendmessage.do";

	    String md5src;  //加密字符串    
	    md5src = MerNo + BillNo + Currency + Amount + Language + ReturnURL + MD5key ;
	    MD5 md5 = new MD5();
	    String MD5info; //MD5加密后的字符串
	    MD5info = md5.getMD5ofStr(md5src);
	    
	    
	    //String returnurl="http://security.ecpss.com/merchantToPaymentGateway";
		String returnurl="https://secure.wedopay.net/straightLinePay";
	    //String returnurl="http://192.168.2.102:8888/wedopay/straightLinePay";
		//----------------给商户网站post数据-----------
		URL url;
		System.out.println("post网址+++++++++++"+returnurl);
		try {
			url = new URL(returnurl);
		
			URLConnection connection = url.openConnection(); 
			connection.setDoOutput(true); 
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
			String parte = "cardnum="+"4000000000000002"+
			"&cvv2="+"156"+
			"&month="+"05"+
			"&year="+"12"+
			"&cardbank="+"bank of china"+
			"&Amount="+Amount+
			
			"&Currency="+Currency+
			"&MerNo="+MerNo+
			"&BillNo="+BillNo+
			"&email="+"89610614@qq.com"+
			"&Language="+Language+
			"&MD5info="+MD5info+
			"&ReturnURL="+ReturnURL;
			
//			String poststr = "OType="+"001"+
//			"&PayOrderNo="+"1685114296382642645"+
//			"&MerchantOrderNo="+"315190521"+
//			"&Amount="+"0.01"+
//			"&RefundAmount="+"0.01"+
//			"&ReturnURL="+ReturnURL;
			
			out.write(parte); //这里组织提交信息 
			out.flush(); 
			out.close(); 
			//获取返回数据 
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			String line = null; 
			StringBuffer content= new StringBuffer(); 
			while((line = in.readLine()) != null) 
			{ 
			   //line为返回值，这就可以判断是否成功、 
			    content.append(line);
			    System.out.println(content);
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
//
//		String socket_ip = "192.168.2.101";
//		int socket_port = 5000;
//		// socket服务器ip地址
//		// socket服务器监听端口
//		Socket socket = null;
//		BufferedReader in = null;
//		PrintWriter out = null;
//		// 请求报文
//
//		String answerMSG = "";
//		try {
//			socket = new Socket(socket_ip, socket_port);
//			if (socket.isConnected()) {
//				in = new BufferedReader(new InputStreamReader(socket
//						.getInputStream()));
//				out = new PrintWriter(new BufferedWriter(
//						new OutputStreamWriter(socket.getOutputStream())), true);
//				// 发送请求报文
//				out.println("http://www.google.com.hk");
//				// 接收应答报文
//				//answerMSG = in.readLine();
//			}
//		} catch (UnknownHostException e) {
//			// System.err.println("未知的主机位置：" + socket_ip);
//			// e.printStackTrace();
//		} catch (IOException e) {
//			// System.err.println("IO出现异常");
//			// e.printStackTrace();
//		} finally {
//			try {
//				in.close();
//				out.close();
//				socket.close();
//			} catch (IOException e) {
//				// e.printStackTrace();
//			}
//			
//		}
//		
//		System.err.println("未知的主机位置：" + socket_ip);
//		System.out.println("执行完毕...");
	}
}
