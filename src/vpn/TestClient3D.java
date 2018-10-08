package vpn;

import java.security.MessageDigest;
import java.util.Date;
import com.ecpss.util.DateUtil;

public class TestClient3D {

	/**
	 * @param args
	 */
	static DCCMessage dcc3 = new DCCMessage();

	public static void main(String[] args) {
		
	
		TestClient3D t = new TestClient3D();
//		t.cxhl();//查询汇率；
		t.cxhl();//3D消费交易
//		t.ty_hlcx();
	}
	//查询汇率
	public void cxhl(){
		try {
			//http://172.20.66.2/sfe?Trans_Type=enqrate&Merchant_Id=021817326000001&Author_Str=gnldal9k&Terminal_Id=07882349&Invoice_No=987232&Ref_No=335411987232&Resp_Code=99YY&Order_No=sfe000120131220114832&Custom=549&Trans_Date=20131220&Trans_Time=114826&Currency_Code_T=156&Currency_Code=840&Currency=USD&Amount_For=17&Amount_Loc=100&Conversion_Rate=0.171300&Cur_exp=2&markup_n=400&markup_a=FOUR PT. ZERO ZERO&HASH=DE677BD922643BFEF54BE9E9C2390FC0DD75AB3DBC507E912153A53D54BC1323
			dcc3.setTrans_Type("enqrate");
			dcc3.setMerchant_Id("021817326000001");
			dcc3.setAuthor_Str("gnldal9k");
			dcc3.setTerminal_Id("07882349");
			dcc3.setInvoice_No("987232");
			dcc3.setCurrency_Code_T("156");
			dcc3.setAmount_Loc("100");
			dcc3.setCard_No("4096661351389917");
			dcc3.setExp_Date("0217");
			dcc3.setOrder_No("sfe"+"0001"+ DateUtil.getDateString(new Date()));
			dcc3.setCustom("549");
			dcc3.setBocs_ReturnURL("http://172.20.66.2/sfe");
			dcc3.setHashCode("5gqddlw971e20c07edek4mcdnxhrst8j");
			String buf = "rsn8h9p5fjrlk901vpnkm2dhvm80rm0uABCD1234";
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "1");
			System.out.println(dcc3.getResp_Code()+"-----------");
			System.out.println(dcc3.getInvoice_No()+"----------");
			byte[] ba = (byte[]) null;
			MessageDigest md5 = MessageDigest.getInstance("SHA-256");
			ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}
	// 消费交易
		public void xfjy() { 
			System.out.println("消费交易");
			try {
				dcc3.setTrans_Type("dccsales");
				dcc3.setTrans_Model("E");
//
//				dcc3.setMerchant_Id("021817325000000");
//				dcc3.setTerminal_Id("07992368");
				
				dcc3.setMerchant_Id("021817326000001");
				dcc3.setTerminal_Id("07882349");
				
				dcc3.setAuthor_Str("gnldal9k");
				dcc3.setInvoice_No("987281");
				dcc3.setCurrency_Code_T("156");
				dcc3.setAmount_Loc("100");
				dcc3.setCard_No("4096661351389917");
				dcc3.setExp_Date("1702");
				dcc3.setCSC("549");
				dcc3.setOrder_No("sfe"+"0001"+ DateUtil.getDateString(new Date()));
				dcc3.setCustom("test1");
				dcc3.setCurrency_Code("840");
				dcc3.setAmount_For("17");
				dcc3.setBocs_ReturnURL("http://172.20.66.2/ret");
				dcc3.setEnd_ReturnURL("http://172.20.66.2/sfe");
				dcc3.setHashCode("5gqddlw971e20c07edek4mcdnxhrst8j");    
				String buf = "rsn8h9p5fjrlk901vpnkm2dhvm80rm0uABCD1234";
				VpnUtil2 vpn = new VpnUtil2();
				dcc3 = vpn.getDCCvalue(dcc3, "13");
				System.out.println(dcc3.getResp_Code());
				byte[] ba = (byte[]) null;
				MessageDigest md5 = MessageDigest.getInstance("SHA-256");
				ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
			} catch (Exception e) {
				e.printStackTrace();
//				com.ecpss.action.pay.CarderInfoAction
//				com.ecpss.action.shop.TerminalManagerAction
//				com.ecpss.vo.ImportTerminalNo
//				vpn.DCCMessage
//				vpn.VpnUtil
//				jsp/ibank/mer_interface.jsp
//				shopmanager/terminalform.jsp
//				shopmanager/terminalManager.jsp
			}
		}
	// 非DCC消费交易
		public void fdccxfjy() {
			System.out.println("非DCC消费交易");
			try {
				dcc3.setTrans_Type("sales");
				dcc3.setAmount_Loc("1");
				dcc3.setCard_No("4283428306045009");
				dcc3.setExp_Date("1312");
				dcc3.setCSC("123");
				dcc3.setTrans_Model("M");
				// 十位数以上的随即数
				String buf = "rsn8h9p5fjrlk901vpnkm2dhvm80rm0uABCD1234";
				VpnUtil2 vpn = new VpnUtil2();
				dcc3 = vpn.getDCCvalue(dcc3, "3");
				byte[] ba = (byte[]) null;
				MessageDigest md5 = MessageDigest.getInstance("SHA-256");
				ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
			} catch (Exception e) {
				e.printStackTrace();
//				com.ecpss.action.pay.CarderInfoAction
//				com.ecpss.action.shop.TerminalManagerAction
//				com.ecpss.vo.ImportTerminalNo
//				vpn.DCCMessage
//				vpn.VpnUtil
//				jsp/ibank/mer_interface.jsp
//				shopmanager/terminalform.jsp
//				shopmanager/terminalManager.jsp
			}
		}

	// DCC消费交易
	public void dccxfjy() {
		System.out.println("DCC消费交易通道3");
		try {
			dcc3.setTrans_Type("dccsales");
			dcc3.setAmount_Loc("1");
			dcc3.setCard_No("4283428306045009");
			dcc3.setExp_Date("1312");
			dcc3.setCSC("123");
			dcc3.setCurrency("840");
			dcc3.setAmount_For("1");
			dcc3.setTrans_Model("M");
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "9");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 非DCC预授权
	public void fdccysq() {
		try {
			System.out.println("非DCC预授权通道3");
			dcc3.setTrans_Type("auth");
			dcc3.setTrans_Model("M");
			dcc3.setAmount_Loc("100");
			dcc3.setCard_No("4934680133575003");
			dcc3.setExp_Date("1210");
			dcc3.setCSC("123");
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "3");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 通用汇率查询
	public void ty_hlcx() {
		System.out.println("通用汇率查询通道1");
		try {
			dcc3.setTrans_Type("enqrate");
			dcc3.setAmount_Loc("62976");
			dcc3.setCard_No("5324505038027815");
			dcc3.setExp_Date("1201");
			// 十位数以上的随即数sfe1000040000390999
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通用交易撤销
	public void ty_jycx() {
		System.out.println("通用交易撤消 通道6");
		try {
			dcc3.setTrans_Type("void");
			dcc3.setAmount_Loc("130");
			dcc3.setCard_No("4283428306045009");
			dcc3.setRef_No("102314360600");// 这个卡号要替换掉
			dcc3.setInvoice_No("737522");


			/* dcc3.setExp_Date("1210");
			 dcc3.setCSC("123");*/
			// 十位数以上的随即数
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "6");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void ty_sqwcjy() {
		System.out.println("通用授权完成交易edc-通道10");
		try {
			dcc3.setTrans_Type("authcomp");
			dcc3.setAmount_Loc("100");
			dcc3.setCard_No("4934680133575003");
			dcc3.setAuth_Code("016533");
			// 十位数以上的随即数
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "10");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tydcc_sqwcjy() {
		System.out.println("通用授权完成交易dcc-通道9");
		try {
			dcc3.setTrans_Type("dcccomp");
			dcc3.setAmount_Loc("100");
			dcc3.setCard_No("4283428306045009");
			dcc3.setAuth_Code("354130");
			dcc3.setCurrency_Code("344");
			dcc3.setAmount_For("131");
			dcc3.setInvoice_No("715656");
			// 十位数以上的随即数
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "9");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ty_jytk() {
		System.out.println("通用交易退款-通道5");
		try {
			dcc3.setTrans_Type("refund");
			dcc3.setAmount_Ori("100");
			dcc3.setAmount_Loc("130");
			dcc3.setTran_Date_Ori("20100901");// zitian
			dcc3.setCard_No("4283428306045009");
			dcc3.setRef_No("102314331214");
			// 十位数以上的随即数
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "5");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ty_tradeQuery() {
		System.out.println("通用交易查询-通道7");
		try {
			dcc3.setTrans_Type("enqtrans");
			dcc3.setCard_No("4283428306045009");
			dcc3.setRef_No("102210502603");
			dcc3.setInvoice_No("373481");
			
			
			dcc3.setCard_No("4283428306045009");
			dcc3.setRef_No("102120381300");// 这个卡号要替换掉
			dcc3.setInvoice_No("540644");
			// 十位数以上的随即数
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "7");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ty_tradeSettle() {
		System.out.println("通用交易结算-通道8");
		try {
			dcc3.setTrans_Type("settlement");
			dcc3.setAuthor_Str("");
			// 十位数以上的随即数
			VpnUtil2 vpn = new VpnUtil2();
			dcc3 = vpn.getDCCvalue(dcc3, "8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// private static Map createMapFromResponse(String queryString) {
	// Map map = new HashMap();
	// StringTokenizer st = new StringTokenizer(queryString, "&");
	// while (st.hasMoreTokens()) {
	// String token = st.nextToken();
	// int i = token.indexOf('=');
	// if (i > 0) {
	// try {
	// String key = token.substring(0, i);
	// String value = URLDecoder.decode(token.substring(i + 1,
	// token.length()));
	// map.put(key, value);
	// } catch (Exception ex) {
	// // Do Nothing and keep looping through data
	// }
	// }
	// }
	// return map;
	// }
	//
	// public static String null2unknown(String in, Map responseFields) {
	// if (in == null || in.length() == 0
	// || (String) responseFields.get(in) == null) {
	// return "No Value Returned";
	// } else {
	// return (String) responseFields.get(in);
	// }
	// } // null2unknown()

}
