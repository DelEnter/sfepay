package com.ecpss.action.pay.hn;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.ecpss.action.pay.tc.XMLGetMessage;

public class ClientBoc {
	
	private HNGetMessage hnGetMessage;
	
	public HNGetMessage getMessage() {
		String poststr = hnGetMessage.getMessage();
		System.out.println(poststr);
//		String socket_ip = "172.20.5.100";
//		int socket_port = 3800;
//		Socket socket = null;
//		OutputStream output = null;
//		InputStream input = null;
//		byte clientByte[] = new byte[2]; // 发送给服务端的字节数组
//		byte serverByte[] = new byte[1024];// 接收给服务端的字节数组
//		clientByte[0] = (byte) 0xc1; // 给数组赋值
//		clientByte[1] = (byte) 0x01; // 给数组赋值
//		String answerMSG="";
//		try {
//			socket = new Socket(socket_ip, socket_port); // 连接服务端
//			output = socket.getOutputStream(); // 获得socket 连接的输出流
//			input = socket.getInputStream(); // 获得socket
//			//System.out.println("request:"+poststr);									// 连接的输入流,需要提前构造，否则无法接收数据
//			//output.write(clientByte); // 把clientByte写入输出流
//			byte bb[] = poststr.getBytes();
//			output.write(bb); // 把clientByte写入输出流
//			output.flush(); // 刷新此输出流并强制写出所有缓冲的输出字节。
//
//			// 读取输入流返回为0，提示没收到字节！
//			// 因为使用了缓存数组1024，所以一次性读满1024。否则将使用while进行循环读取，直到input.read(serverByte)
//			// == -1
//			
//			int len = input.read(serverByte);
//			if (len > 0) {// 读取到字节
//				//System.out.println("读取到字节。！ " + len);
//			} else if (len == 0) {// 没有读取到字节
//				//System.out.println("没有取到字节。！");
//				// 读取输入流返回为-1，提示没有可用的字节 ！
//			} else if (len == -1) {// 读取结束
//				//System.out.println("读取结束");
//			}
//			// 循环显示读取到的数据
////			//System.out.println(new String(serverByte));
////			answerMSG = new String(serverByte);
////			//System.out.println("response:"+answerMSG);
////			int index = answerMSG.lastIndexOf("</ap>");
////			answerMSG = answerMSG.substring(0,index+5);
////			answerMSG = answerMSG.replaceAll("\n","").substring(4);
////			HNGetMessage xmlset = new HNGetMessage();
////			xmlset.reciveRes(answerMSG);
////			hnGetMessage = xmlset.getXmlget();
////			System.out.println("rrn:"+hnGetMessage.getRrn());
////			System.out.println("amount:"+hnGetMessage.getTxn_amt());
////			System.out.println("authno:"+hnGetMessage.getAuth_no());   
////			System.out.println("invoice:"+hnGetMessage.getInvoice());   
////			System.out.println("response: "+hnGetMessage.getRespcode());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (Exception e) {
//				}
//			}
//			if (output != null) {
//				try {
//					output.close();
//				} catch (Exception e) {
//				}
//			}
//			if (socket != null) {
//				try {
//					socket.close();
//				} catch (Exception e) {
//				}
//			}
//		}
		return hnGetMessage;
	}

	public HNGetMessage getHnGetMessage() {
		return hnGetMessage;
	}

	public void setHnGetMessage(HNGetMessage hnGetMessage) {
		this.hnGetMessage = hnGetMessage;
	}


}
