package vpn;

import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.ecpss.util.DateUtil;
import com.ecpss.util.GetBatchNo;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * ven loye in 2013.10.9 18:00 can't delete thanks VpnUtil vpn = new
		 * VpnUtil(); DCCMessage msg = new DCCMessage();
		 * msg.setTrans_Type("auth"); msg.setTrans_Model("M");
		 * msg.setMerchant_Id("021814001000000");
		 * 
		 * try { vpn.getDccMessage(msg, "auth"); } catch (IOException io ) { lo
		 * }
		 **/
		/**
		 * 中银连接测试
		 */
		try {

			DCCMessage dcc3 = new DCCMessage();
			dcc3.setTrans_Type("sales");
			dcc3.setTrans_Model("M");
			dcc3.setMerchant_Id("021817005000000");
			dcc3.setAuthor_Str("0e4asb3j");
			dcc3.setTerminal_Id("99991705");
			GetBatchNo ut = new GetBatchNo();
			String orderinfo = ut.getOrderinfo("test");
			orderinfo.substring(orderinfo.length() - 6, orderinfo.length());
			dcc3.setInvoice_No(orderinfo.substring(orderinfo.length() - 6,
					orderinfo.length()));
			System.out.println(dcc3.getInvoice_No());
			dcc3.setCurrency_Code_T("156");
			dcc3.setAmount_Loc("1");
			dcc3.setCard_No("4934680133575003");
			dcc3.setExp_Date("1210");
			dcc3.setCSC("327");
			dcc3.setHashCode("rsn8h9p5fjrlk901vpnkm2dhvm80rm0u");
			
			dcc3.setOrder_No("sfe"+"0001"+ DateUtil.getDateString(new Date()));
			dcc3.setCustom("test1");

			// dcc3.setCurrency_Code(currency_Code);
			// dcc3.setAmount_For(amount_For);

			// dcc3.setTrans_Type("enqrate");// 类型
			// dcc3.setMerchant_Id("021817006000001");// 42 商户编号
			// dcc3.setAuthor_Str("0e4asb3j");// 交易接入码
			// dcc3.setTerminal_Id("99991705");// 41 商户终端号
			// dcc3.setInvoice_No("000066");// 11 交易流水号
			// dcc3.setCurrency_Code_T("156");// 货币代码
			// dcc3.setAmount_Loc("1");// 4 本地交易金额
			// dcc3.setCard_No("4096661351389917");// 账号2
			// dcc3.setExp_Date("1702");// 14 有效期
			// dcc3.setOrder_No("Test000001");// 62
			// dcc3.setCustom("");
			// dcc3.setHashCode("rsn8h9p5fjrlk90lvpnkm2dhvm80rm0u");

			VpnUtil vpn = new VpnUtil();
			 dcc3 = vpn.getDCCvalue(dcc3, "3");
//			String buf = "rsn8h9p5fjrlk901vpnkm2dhvm80rm0uABCD1234";
//			byte[] ba = (byte[]) null;
//			MessageDigest md5 = MessageDigest.getInstance("SHA-256");
//			ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
//			System.out.println(hex(ba));
			System.out.println("wait" + dcc3.getResp_Code());
		} catch (Exception e) {

		}

		/*
		 * http://localhost/ecpss/getRate
		 * ?Trans_Type=enqrate&Merchant_Id=021209999000000&Author_Str=hab471t0
		 * &Terminal_Id=22222222&Invoice_No=000112&Ref_No=
		 * &Resp_Code=1106&Order_No
		 * =127315151545&Custom=1111554515451&Trans_Date=
		 * 20101223&Trans_Time=155435
		 * &Currency_Code_T=156&Currency_Code=&Currency
		 * =&Amount_For=&Amount_Loc=100
		 * &Conversion_Rate=&HASH=B4D40BF1E186244CAEFAE3030D221D21 Location
		 */

		// System.out.println(TestClient.null2unknown("Merchant_Id",TestClient.createMapFromResponse("http://localhost/ecpss/getRate?Trans_Type=enqrate&Merchant_Id=021209999000000&Author_Str=hab471t0&Terminal_Id=22222222&Invoice_No=000112&Ref_No=&Resp_Code=1106&Order_No=127315151545&Custom=1111554515451&Trans_Date=20101223&Trans_Time=155435&Currency_Code_T=156&Currency_Code=&Currency=&Amount_For=&Amount_Loc=100&Conversion_Rate=&HASH=B4D40BF1E186244CAEFAE3030D221D21")));

	}

	public static String hex(byte[] input) {
		char[] HEX_TABLE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer sb = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
		      sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
		      sb.append(HEX_TABLE[input[i] & 0xf]);
		}
		return sb.toString();
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


	public static String null2unknown(String in, Map responseFields) {
		if (in == null || in.length() == 0
				|| (String) responseFields.get(in) == null) {
			return "No Value Returned";
		} else {
			return (String) responseFields.get(in);
		}
	} // null2unknown()
}
