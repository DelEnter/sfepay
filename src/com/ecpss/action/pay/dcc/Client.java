package com.ecpss.action.pay.dcc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.ParseException;


public class Client extends Thread{
	private Socket sock;

//	public Client(Socket socket) {
//		sock = socket;
//	}
	public void run() {
		byte[] lenbuf = new byte[2];
		try {
			//For high volume apps you will be better off only reading the stream in one thread
			//and then using another thread to parse the buffers and process the responses
			//Otherwise the network buffer might fill up and you can miss a message.
			while (sock != null && sock.isConnected() && !isInterrupted()) {
				sock.getInputStream().read(lenbuf);
				int size = ((lenbuf[0] & 0xff) << 8) | (lenbuf[1] & 0xff);
				byte[] buf = new byte[size];
				//We're not expecting ETX in this case
				if (sock.getInputStream().read(buf) == size) {
					try {

					} catch (Exception ex) {
					
					}
				} else {
				
					return;
				}
			}
		} catch (IOException ex) {
		
		} finally {
			try {
				sock.close();
			} catch (IOException ex) {};
		}
}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//查询通道
//		DCCMessage msg=new DCCMessage();
//		msg.setMessageType("0800");
//		msg.setPrimary_Account_Number("4000000000000002");
//		msg.setProcessing_Code("970000");
//		msg.setAmount_Transaction_Local("000000102050");
//		msg.setSYSTEMS_TRACE_AUDIT_NUMBER("865454");
//		msg.setDATE_EXPIRATION("0506");
//		msg.setPOINT_OF_SERVICE_ENTRY_CODE("012");
//		msg.setNETWORK_INTL_IDENTIFIER("098");
//		msg.setPOS_CONDITION_CODE("00");
//        msg.setCARD_ACCEPTOR_TERMINAL_ID("985652565236523");
//        msg.setCARD_ACCEPTOR_ID_CODE("85858585");
//        msg.setInvoice_Number("856958");
	//交易查询	
   	 DCCMessage dcc=new DCCMessage();
	 dcc.setMessageType("0800");//类型
	 dcc.setPrimary_Account_Number("4447961117443552");//账号2
	 dcc.setProcessing_Code("970000");//处理代码3
	 dcc.setAmount_Transaction_Local("000000032190");//4 本地交易金额
	 dcc.setSYSTEMS_TRACE_AUDIT_NUMBER("000005");//11  交易流水号
	 dcc.setDATE_EXPIRATION("1201");//14   有效期
	 dcc.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
	 dcc.setNETWORK_INTL_IDENTIFIER("0098");//24  收单商户号
	 dcc.setPOS_CONDITION_CODE("00");//25 商户编码
	 dcc.setCARD_ACCEPTOR_TERMINAL_ID("22222222");//41  商户终端号
	 dcc.setCARD_ACCEPTOR_ID_CODE("021209999000000");//42 商户编号 
	 dcc.setInvoice_Number("000005");//62		
	//交易S

  	 DCCMessage msg2=new DCCMessage();
	 msg2.setMessageType("0200");//类型
	 msg2.setPrimary_Account_Number("4447961117443552");//账号2
	 msg2.setProcessing_Code("000000");//处理代码3
	 msg2.setAmount_Transaction_Local("000000032190");//4 本地交易金额
	 msg2.setAmount_Transaction_Foreign("000000004867");//5  0810
	 msg2.setConversion_Rate("71511880");//9    0810
	 msg2.setSYSTEMS_TRACE_AUDIT_NUMBER("000005");//11  交易流水号
	 msg2.setDATE_EXPIRATION("1201");//14   有效期
	 msg2.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
	 msg2.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
	 msg2.setPOS_CONDITION_CODE("00");//25 商户编码
	 msg2.setRETRIEVAL_REFERENCE_NUMBER("101180000032");//37
	 msg2.setCARD_ACCEPTOR_TERMINAL_ID("22222222");//41  商户终端号
	 msg2.setCARD_ACCEPTOR_ID_CODE("021209999000000");//42 商户编号 
	 msg2.setCurrency_Code_Foreign("840");//49 货币代码-----0810
	 msg2.setCVV2_OR_CVC2("778");//cv2
	 msg2.setInvoice_Number("000005");//62		
	 
	 
  	 DCCMessage dcc3=new DCCMessage();
	 dcc3.setMessageType("0200");//类型
	 dcc3.setPrimary_Account_Number("164402602810763408");//账号2
	 dcc3.setProcessing_Code("000000");//处理代码3
	 dcc3.setAmount_Transaction_Local("000000000100");//4 本地交易金额
	 dcc3.setSYSTEMS_TRACE_AUDIT_NUMBER("000006");//11  交易流水号
	 dcc3.setDATE_EXPIRATION("0810");//14   有效期
	 dcc3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
	 dcc3.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
     dcc3.setCARD_ACCEPTOR_TERMINAL_ID("88885500");//41  商户终端号
	 dcc3.setCARD_ACCEPTOR_ID_CODE("021801055000001");//42 商户编号 
	 dcc3.setCVV2_OR_CVC2("218");//cv2 61
	 dcc3.setInvoice_Number("000006");//62			 
	 
	 
//       byte [] byt=msg.getMessage();
//        byte [] biaozhu="0800".getBytes();
//        byte [] byts=new byte[byt.length+biaozhu.length];
//        System.arraycopy(biaozhu, 0, byts, 0, biaozhu.length);
//        System.arraycopy(byt, 0,  byts,biaozhu.length,byt.length);
        String temssss="005c6000173000020078a4058000c0800c1644026028107634080000000000000001000000000000117114964800000608100012001700383838383535303030323138303130353530303030303139373800033231380006303030303036";
       String temss222="004a600098300008007024058000c000041644026028107634089700000000000001000000050810001200980038383838353530303032313830313035353030303030310006303030303035";
       String temsss333="0041600098300008007024018000c000041644026028107634089700000000000001000000051105098003838383835353030303231383031303535303030303031303036303030303035";
        String test="004f600017300002007024050000c0000c16523265457854521400000000000000010000000608100012001738353435323132333032313132313231323132313231320003303331330006303030303036";
       byte [] byts=msg2.getMessage();      
  //   byte [] byts=DCCMessageUtil.str2Bcd(temssss);
        InputStream in = null;
		OutputStream outs=null;
		
		  
        try {
			Socket sockets = new Socket("222.92.198.164", 6500);
			if (sockets.isConnected()) {
				Client.printHexString(byts);
				outs = sockets.getOutputStream();				
     			outs.write(byts);
				in = sockets.getInputStream();
			byte[] bytss=new byte[300];
			    in.read(bytss);
				System.out.println(DCCMessageUtil.bcd2Str(byts)+"\n");
				Client.printHexString(bytss);
				System.out.println("\n"+Client.getHexString(bytss));
//				Client.printHexString(bytss);
				
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {

				outs.close();
				in.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}		
       
		
//		Client cli=new Client();
//	String ask=	cli.communicateHost("222.92.198.164", 6500,"720000003080000016530998787654534290000000000000010020030802063132333435360009123456001083938373635343332");
//        System.out.println(ask);
	}
	// 和主机进行实时交互(socket 客户端通信)
	public String communicateHost(String socket_ip,int socket_port,String msg) {
		 // socket服务器ip地址
		 // socket服务器监听端口
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		// 请求报文
		
		String answerMSG="";
		try {
			socket = new Socket(socket_ip, socket_port);
			if (socket.isConnected()) {
				in = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);
				// 发送请求报文
				out.println(msg);
				// 接收应答报文
				 answerMSG = in.readLine();
	
			}
		} catch (UnknownHostException e) {
//			System.err.println("未知的主机位置：" + socket_ip);
//			e.printStackTrace();
		} catch (IOException e) {
//			System.err.println("IO出现异常");
//			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
		return  answerMSG;
	}		
	
	public static void printHexString( byte[] b) {     
		   for (int i = 0; i < b.length; i++) {    
		     String hex = Integer.toHexString(b[i] & 0xFF);    
		     if (hex.length() == 1) {    
		       hex = '0' + hex;    
		     }    
		     System.out.print(hex.toUpperCase() );    
		   }    
		  
		}  
	public static String getHexString( byte[] b) {
		String tem="";
		   for (int i = 0; i < b.length; i++) {    
		     String hex = Integer.toHexString(b[i] & 0xFF);    
		     if (hex.length() == 1) {    
		       hex = '0' + hex;    
		     }    
		    tem+=hex.toUpperCase();    
		   }    
		  return tem;
		}	
	
}


//发送交易指令
//DCCMessage msg2=new DCCMessage();
//msg2.setPrimary_Account_Number("512345013100138189");
//msg2.setProcessing_Code("000000");
//msg2.setAmount_Transaction_Local("000000102050");
//msg2.setAmount_Transaction_Foreign("000001102050");
//msg2.setSYSTEMS_TRACE_AUDIT_NUMBER("985654");
//msg2.setConversion_Rate("0.65");
//msg2.setDATE_EXPIRATION("1002");
//msg2.setPOINT_OF_SERVICE_ENTRY_CODE("012");
//msg2.setNETWORK_INTL_IDENTIFIER("017");
//msg2.setPOS_CONDITION_CODE("00");
//msg2.setRETRIEVAL_REFERENCE_NUMBER("123652458574");
//msg2.setCARD_ACCEPTOR_TERMINAL_ID("88888888");
//msg2.setCARD_ACCEPTOR_ID_CODE("700888558541125");
//msg2.setCurrency_Code_Foreign("784");
//msg2.setCVV2_OR_CVC2("569");
//msg2.setInvoice_Number("885454");
