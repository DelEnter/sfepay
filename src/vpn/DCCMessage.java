package vpn;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DCCMessage {
	private String hashCode;
	private String Trans_Type;
	private String Merchant_Id;
	private String Author_Str;
	private String Terminal_Id;
	private String Invoice_No;
	private String Currency_Code_T;
	private String Amount_Loc;
	private String Card_No;
	private String Exp_Date;
	private String Order_No;
	private String Custom;
	private String Trans_Model;
	private String CSC;
	private String Conversion_Rate;
	private String Currency_Code;
	private String Amount_For;
	private String bocs_ReturnURL;
	private String Ref_No;
	private String Amount_Ref;
	private String Tran_Date_Ori;
	private String Resp_Code;
	private String Auth_Code;
	private String Currency;
	private String Amount_Ori;
	private String token_id;
	private String Send_Url;
	
	private String End_ReturnURL;
	private Map fields = new HashMap();

	public String getHash() {
		String tem = "";
		if (this.getTrans_Type() != null) {
			if (tem.equals("")) {
				tem = tem + "Trans_Type=" + this.getTrans_Type();
				fields.put("Trans_Type", this.getTrans_Type());
			} else {
				tem = tem + "&Trans_Type=" + this.getTrans_Type();
				fields.put("Trans_Type", this.getTrans_Type());
			}
		}
		if (this.getTrans_Model() != null) {
			if (tem.equals("")) {
				fields.put("Trans_Model", this.getTrans_Model());
				tem = tem + "Trans_Model=" + this.getTrans_Model();
			} else {
				fields.put("Trans_Model", this.getTrans_Model());
				tem = tem + "&Trans_Model=" + this.getTrans_Model();
			}
		}
		if (this.getMerchant_Id() != null) {
			if (tem.equals("")) {
				fields.put("Merchant_Id", this.getMerchant_Id());
				tem = tem + "Merchant_Id=" + this.getMerchant_Id();
			} else {
				fields.put("Merchant_Id", this.getMerchant_Id());
				tem = tem + "&Merchant_Id=" + this.getMerchant_Id();
			}
		}
		if (this.getAuthor_Str() != null) {
			if (tem.equals("")) {
				fields.put("Author_Str", this.getAuthor_Str());
				tem = tem + "Author_Str=" + this.getAuthor_Str();
			} else {
				fields.put("Author_Str", this.getAuthor_Str());
				tem = tem + "&Author_Str=" + this.getAuthor_Str();
			}
		}
		if (this.getTerminal_Id() != null) {
			if (tem.equals("")) {
				fields.put("Terminal_Id", this.getTerminal_Id());
				tem = tem + "Terminal_Id=" + this.getTerminal_Id();
			} else {
				fields.put("Terminal_Id", this.getTerminal_Id());
				tem = tem + "&Terminal_Id=" + this.getTerminal_Id();
			}
		}

		if (this.getInvoice_No() != null) {
			if (tem.equals("")) {
				fields.put("Invoice_No", this.getInvoice_No());
				tem = tem + "Invoice_No=" + this.getInvoice_No();
			} else {
				fields.put("Invoice_No", this.getInvoice_No());
				tem = tem + "&Invoice_No=" + this.getInvoice_No();
			}
		}
		if (this.getCurrency_Code_T() != null) {
			if (tem.equals("")) {
				fields.put("Currency_Code_T", this.getCurrency_Code_T());
				tem = tem + "Currency_Code_T=" + this.getCurrency_Code_T();
			} else {
				fields.put("Currency_Code_T", this.getCurrency_Code_T());
				tem = tem + "&Currency_Code_T=" + this.getCurrency_Code_T();
			}
		}
		if (this.getAmount_Ori() != null) {
			if (tem.equals("")) {
				fields.put("Amount_Ori", this.getAmount_Ori());
				tem = tem + "Amount_Ori=" + this.getAmount_Ori();
			} else {
				fields.put("Amount_Ori", this.getAmount_Ori());
				tem = tem + "&Amount_Ori=" + this.getAmount_Ori();
			}
		}
		if (this.getAmount_Loc() != null) {
			if (tem.equals("")) {
				fields.put("Amount_Loc", this.getAmount_Loc());
				tem = tem + "Amount_Loc=" + this.getAmount_Loc();
			} else {
				fields.put("Amount_Loc", this.getAmount_Loc());
				tem = tem + "&Amount_Loc=" + this.getAmount_Loc();
			}
		}
		if (this.getTran_Date_Ori() != null) {
			if (tem.equals("")) {
				fields.put("Tran_Date_Ori", this.getTran_Date_Ori());
				tem = tem + "Tran_Date_Ori=" + this.getTran_Date_Ori();
			} else {
				fields.put("Tran_Date_Ori", this.getTran_Date_Ori());
				tem = tem + "&Tran_Date_Ori=" + this.getTran_Date_Ori();
			}
		}
		if (this.getAuth_Code() != null) {
			if (tem.equals("")) {
				fields.put("Auth_Code", this.getAuth_Code());
				tem = tem + "Auth_Code=" + this.getAuth_Code();
			} else {
				fields.put("Auth_Code", this.getAuth_Code());
				tem = tem + "&Auth_Code=" + this.getAuth_Code();
			}
		}
		if (this.getCard_No() != null) {
			if (tem.equals("")) {
				fields.put("Card_No", this.getCard_No());
				tem = tem + "Card_No=" + this.getCard_No();
			} else {
				fields.put("Card_No", this.getCard_No());
				tem = tem + "&Card_No=" + this.getCard_No();
			}
		}
		if (this.getRef_No() != null) {
			if (tem.equals("")) {
				fields.put("Ref_No", this.getRef_No());
				tem = tem + "Ref_No=" + this.getRef_No();
			} else {
				fields.put("Ref_No", this.getRef_No());
				tem = tem + "&Ref_No=" + this.getRef_No();
			}
		}
		if (this.getExp_Date() != null) {
			if (tem.equals("")) {
				fields.put("Exp_Date", this.getExp_Date());
				tem = tem + "Exp_Date=" + this.getExp_Date();
			} else {
				fields.put("Exp_Date", this.getExp_Date());
				tem = tem + "&Exp_Date=" + this.getExp_Date();
			}
		}
		if (this.getCSC() != null) {
			if (tem.equals("")) {
				fields.put("CSC", this.getCSC());
				tem = tem + "CSC=" + this.getCSC();
			} else {
				fields.put("CSC", this.getCSC());
				tem = tem + "&CSC=" + this.getCSC();
			}
		}
		if (this.getAmount_Ref() != null) {
			if (tem.equals("")) {
				fields.put("Amount_Ref", this.getAmount_Ref());
				tem = tem + "Amount_Ref=" + this.getAmount_Ref();
			} else {
				fields.put("Amount_Ref", this.getAmount_Ref());
				tem = tem + "&Amount_Ref=" + this.getAmount_Ref();
			}
		}
		if (this.getOrder_No() != null) {
			if (tem.equals("")) {
				fields.put("Order_No", this.getOrder_No());
				tem = tem + "Order_No=" + this.getOrder_No();
			} else {
				fields.put("Order_No", this.getOrder_No());
				tem = tem + "&Order_No=" + this.getOrder_No();
			}
		}
		if (this.getCustom() != null) {
			if (tem.equals("")) {
				fields.put("Custom", this.getCustom());
				tem = tem + "Custom=" + this.getCustom();
			} else {
				fields.put("Custom", this.getCustom());
				tem = tem + "&Custom=" + this.getCustom();
			}
		}

		if (this.getConversion_Rate() != null) {
			if (tem.equals("")) {
				fields.put("Conversion_Rate", this.getConversion_Rate());
				tem = tem + "Conversion_Rate=" + this.getConversion_Rate();
			} else {
				fields.put("Conversion_Rate", this.getConversion_Rate());
				tem = tem + "&Conversion_Rate=" + this.getConversion_Rate();
			}
		}
		if (this.getCurrency_Code() != null) {
			if (tem.equals("")) {
				fields.put("Currency_Code", this.getCurrency_Code());
				tem = tem + "Currency_Code=" + this.getCurrency_Code();
			} else {
				fields.put("Currency_Code", this.getCurrency_Code());
				tem = tem + "&Currency_Code=" + this.getCurrency_Code();
			}
		}
		if (this.getAmount_For() != null) {
			if (tem.equals("")) {
				fields.put("Amount_For", this.getAmount_For());
				tem = tem + "Amount_For=" + this.getAmount_For();
			} else {
				fields.put("Amount_For", this.getAmount_For());
				tem = tem + "&Amount_For=" + this.getAmount_For();
			}
		}
		if (this.getBocs_ReturnURL() != null) {
			if (tem.equals("")) {
				fields.put("bocs_ReturnURL", this.getBocs_ReturnURL());
				tem = tem + "bocs_ReturnURL=" + this.getBocs_ReturnURL();
			} else {
				fields.put("bocs_ReturnURL", this.getBocs_ReturnURL());
				tem = tem + "&bocs_ReturnURL=" + this.getBocs_ReturnURL();
			}
		}
		if (this.getEnd_ReturnURL() != null) {
			if (tem.equals("")) {
				fields.put("end_ReturnURL", this.getEnd_ReturnURL());
				tem = tem + "end_ReturnURL=" + this.getEnd_ReturnURL();
			} else {
				fields.put("end_ReturnURL", this.getEnd_ReturnURL());
				tem = tem + "&end_ReturnURL=" + this.getEnd_ReturnURL();
			}
		}
		
		return hashAllFields(fields);
	}

	// public String getPostStr() {
	//
	// String tem = "";
	// if (this.getTrans_Type() != null) {
	// if (tem.equals("")) {
	// tem = tem + "Trans_Type=" + this.getTrans_Type();
	// fields.put("Trans_Type", this.getTrans_Type());
	// } else {
	// tem = tem + "&Trans_Type=" + this.getTrans_Type();
	// fields.put("Trans_Type", this.getTrans_Type());
	// }
	// }
	// if (this.getTrans_Model() != null) {
	// if (tem.equals("")) {
	// fields.put("Trans_Model", this.getTrans_Model());
	// tem = tem + "Trans_Model=" + this.getTrans_Model();
	// } else {
	// fields.put("Trans_Model", this.getTrans_Model());
	// tem = tem + "&Trans_Model=" + this.getTrans_Model();
	// }
	// }
	// if (this.getMerchant_Id() != null) {
	// if (tem.equals("")) {
	// fields.put("Merchant_Id", this.getMerchant_Id());
	// tem = tem + "Merchant_Id=" + this.getMerchant_Id();
	// } else {
	// fields.put("Merchant_Id", this.getMerchant_Id());
	// tem = tem + "&Merchant_Id=" + this.getMerchant_Id();
	// }
	// }
	// if (this.getAuthor_Str() != null) {
	// if (tem.equals("")) {
	// fields.put("Author_Str", this.getAuthor_Str());
	// tem = tem + "Author_Str=" + this.getAuthor_Str();
	// } else {
	// fields.put("Author_Str", this.getAuthor_Str());
	// tem = tem + "&Author_Str=" + this.getAuthor_Str();
	// }
	// }
	// if (this.getTerminal_Id() != null) {
	// if (tem.equals("")) {
	// fields.put("Terminal_Id", this.getTerminal_Id());
	// tem = tem + "Terminal_Id=" + this.getTerminal_Id();
	// } else {
	// fields.put("Terminal_Id", this.getTerminal_Id());
	// tem = tem + "&Terminal_Id=" + this.getTerminal_Id();
	// }
	// }
	// if (this.getInvoice_No() != null) {
	// if (tem.equals("")) {
	// fields.put("Invoice_No", this.getInvoice_No());
	// tem = tem + "Invoice_No=" + this.getInvoice_No();
	// } else {
	// fields.put("Invoice_No", this.getInvoice_No());
	// tem = tem + "&Invoice_No=" + this.getInvoice_No();
	// }
	// }
	// if (this.getCurrency_Code_T() != null) {
	// if (tem.equals("")) {
	// fields.put("Currency_Code_T", this.getCurrency_Code_T());
	// tem = tem + "Currency_Code_T=" + this.getCurrency_Code_T();
	// } else {
	// fields.put("Currency_Code_T", this.getCurrency_Code_T());
	// tem = tem + "&Currency_Code_T=" + this.getCurrency_Code_T();
	// }
	// }
	// if (this.getAmount_Loc() != null) {
	// if (tem.equals("")) {
	// fields.put("Amount_Loc", this.getAmount_Loc());
	// tem = tem + "Amount_Loc=" + this.getAmount_Loc();
	// } else {
	// fields.put("Amount_Loc", this.getAmount_Loc());
	// tem = tem + "&Amount_Loc=" + this.getAmount_Loc();
	// }
	// }
	// if (this.getCard_No() != null) {
	// if (tem.equals("")) {
	// fields.put("Card_No", this.getCard_No());
	// tem = tem + "Card_No=" + this.getCard_No();
	// } else {
	// fields.put("Card_No", this.getCard_No());
	// tem = tem + "&Card_No=" + this.getCard_No();
	// }
	// }
	// if (this.getExp_Date() != null) {
	// if (tem.equals("")) {
	// fields.put("Exp_Date", this.getExp_Date());
	// tem = tem + "Exp_Date=" + this.getExp_Date();
	// } else {
	// fields.put("Exp_Date", this.getExp_Date());
	// tem = tem + "&Exp_Date=" + this.getExp_Date();
	// }
	// }
	// if (this.getCSC() != null) {
	// if (tem.equals("")) {
	// fields.put("CSC", this.getCSC());
	// tem = tem + "CSC=" + this.getCSC();
	// } else {
	// fields.put("CSC", this.getCSC());
	// tem = tem + "&CSC=" + this.getCSC();
	// }
	// }
	//
	// if (this.getOrder_No() != null) {
	// if (tem.equals("")) {
	// fields.put("Order_No", this.getOrder_No());
	// tem = tem + "Order_No=" + this.getOrder_No();
	// } else {
	// fields.put("Order_No", this.getOrder_No());
	// tem = tem + "&Order_No=" + this.getOrder_No();
	// }
	// }
	// if (this.getCustom() != null) {
	// if (tem.equals("")) {
	// fields.put("Custom", this.getCustom());
	// tem = tem + "Custom=" + this.getCustom();
	// } else {
	// fields.put("Custom", this.getCustom());
	// tem = tem + "&Custom=" + this.getCustom();
	// }
	// }
	//
	// if (this.getConversion_Rate() != null) {
	// if (tem.equals("")) {
	// fields.put("Conversion_Rate", this.getConversion_Rate());
	// tem = tem + "Conversion_Rate=" + this.getConversion_Rate();
	// } else {
	// fields.put("Conversion_Rate", this.getConversion_Rate());
	// tem = tem + "&Conversion_Rate=" + this.getConversion_Rate();
	// }
	// }
	// if (this.getCurrency_Code() != null) {
	// if (tem.equals("")) {
	// fields.put("Currency_Code", this.getCurrency_Code());
	// tem = tem + "Currency_Code=" + this.getCurrency_Code();
	// } else {
	// fields.put("Currency_Code", this.getCurrency_Code());
	// tem = tem + "&Currency_Code=" + this.getCurrency_Code();
	// }
	// }
	// if (this.getAmount_For() != null) {
	// if (tem.equals("")) {
	// fields.put("Amount_For", this.getAmount_For());
	// tem = tem + "Amount_For=" + this.getAmount_For();
	// } else {
	// fields.put("Amount_For", this.getAmount_For());
	// tem = tem + "&Amount_For=" + this.getAmount_For();
	// }
	// }
	//
	// return tem + "&HASH=" + hashAllFields(fields);
	// //return hashAllFields(fields);
	//
	// }

	public String getTrans_Type() {
		return Trans_Type;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public String getSend_Url() {
		return Send_Url;
	}

	public void setSend_Url(String send_Url) {
		Send_Url = send_Url;
	}

	public void setTrans_Type(String trans_Type) {
		Trans_Type = trans_Type;
	}

	public String getMerchant_Id() {
		return Merchant_Id;
	}

	public void setMerchant_Id(String merchant_Id) {
		Merchant_Id = merchant_Id;
	}

	public String getAuthor_Str() {
		return Author_Str;
	}

	public void setAuthor_Str(String author_Str) {
		Author_Str = author_Str;
	}

	public String getTerminal_Id() {
		return Terminal_Id;
	}

	public void setTerminal_Id(String terminal_Id) {
		Terminal_Id = terminal_Id;
	}

	public String getInvoice_No() {
		return Invoice_No;
	}

	public void setInvoice_No(String invoice_No) {
		Invoice_No = invoice_No;
	}

	public String getCurrency_Code_T() {
		return Currency_Code_T;
	}

	public void setCurrency_Code_T(String currency_Code_T) {
		Currency_Code_T = currency_Code_T;
	}

	public String getAmount_Loc() {
		return Amount_Loc;
	}

	public void setAmount_Loc(String amount_Loc) {
		Amount_Loc = amount_Loc;
	}

	public String getCard_No() {
		return Card_No;
	}

	public void setCard_No(String card_No) {
		Card_No = card_No;
	}

	public String getExp_Date() {
		return Exp_Date;
	}

	public void setExp_Date(String exp_Date) {
		Exp_Date = exp_Date;
	}

	public String getOrder_No() {
		return Order_No;
	}

	public void setOrder_No(String order_No) {
		Order_No = order_No;
	}

	public String getCustom() {
		return Custom;
	}

	public void setCustom(String custom) {
		Custom = custom;
	}

	public String getTrans_Model() {
		return Trans_Model;
	}

	public void setTrans_Model(String trans_Model) {
		Trans_Model = trans_Model;
	}

	public String getCSC() {
		return CSC;
	}

	public void setCSC(String csc) {
		CSC = csc;
	}

	public String getConversion_Rate() {
		return Conversion_Rate;
	}

	public void setConversion_Rate(String conversion_Rate) {
		Conversion_Rate = conversion_Rate;
	}

	public String getCurrency_Code() {
		return Currency_Code;
	}

	public void setCurrency_Code(String currency_Code) {
		Currency_Code = currency_Code;
	}

	public String getAmount_For() {
		return Amount_For;
	}

	public String getEnd_ReturnURL() {
		return End_ReturnURL;
	}

	public void setEnd_ReturnURL(String end_ReturnURL) {
		End_ReturnURL = end_ReturnURL;
	}

	public void setAmount_For(String amount_For) {
		Amount_For = amount_For;
	}

	public String hashAllFields(Map<String, String> fields) {
		// fields.put("submit", "Continue");
		String SECURE_SECRET = this.getHashCode();
		List fieldNames = new ArrayList(fields.keySet());
		Collections.sort(fieldNames);
		StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) fields.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				buf.append(fieldValue);
			}
		}
		MessageDigest md5 = null;
		byte[] ba = (byte[]) null;
		try {
			md5 = MessageDigest.getInstance("SHA-256");
			ba = md5.digest(buf.toString().getBytes("UTF-8"));
		} catch (Exception localException) {
		}
		return hex(ba);
	}

	static final char[] HEX_TABLE = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String hex(byte[] input) {
		StringBuffer sb = new StringBuffer(input.length * 2);
		for (int i = 0; i < input.length; i++) {
			sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
			sb.append(HEX_TABLE[input[i] & 0xf]);
		}
		return sb.toString();
	}

	// String hashAllFields(Map fields) {
	//
	// fields.put("submit", "Continue");
	// String hashKeys = "";
	// String hashValues = "";
	// List fieldNames = new ArrayList(fields.keySet());
	// Collections.sort(fieldNames);
	//
	// StringBuffer buf = new StringBuffer();
	// buf.append(this.getHashCode());
	//
	// Iterator itr = fieldNames.iterator();
	//
	// while (itr.hasNext()) {
	// String fieldName = (String) itr.next();
	// String fieldValue = (String) fields.get(fieldName);
	// // hashKeys += fieldName + ", ";
	// if ((fieldValue != null) && (fieldValue.length() > 0)) {
	// buf.append(fieldValue);
	// }
	// }
	// System.out.println("buf="+buf);
	// MessageDigest md5 = null;
	// byte[] ba = null;
	//
	// try {
	// md5 = MessageDigest.getInstance("SHA-256");
	// ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
	//
	// } catch (Exception e) {
	// }
	//
	// // hashValues = buf.toString();
	// return hex(ba);
	//
	// }

	// static String SECURE_SECRET = "ncvb40n880aech0uxcj91sj0cj8a2xu1";
	// public static String hex(byte[] input) {
	// char[] HEX_TABLE = {
	// '0', '1', '2', '3', '4', '5', '6', '7',
	// '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	// StringBuffer sb = new StringBuffer(input.length * 2);
	// for (int i = 0; i < input.length; i++) {
	// sb.append(HEX_TABLE[(input[i] >> 4 & 0xF)]);
	// sb.append(HEX_TABLE[(input[i] & 0xF)]);
	// }
	// return sb.toString();
	// }
	public String getBocs_ReturnURL() {
		return bocs_ReturnURL;
	}

	public void setBocs_ReturnURL(String bocs_ReturnURL) {
		this.bocs_ReturnURL = bocs_ReturnURL;
	}

	public String getRef_No() {
		return Ref_No;
	}

	public void setRef_No(String ref_No) {
		Ref_No = ref_No;
	}

	public String getAmount_Ref() {
		return Amount_Ref;
	}

	public void setAmount_Ref(String amount_Ref) {
		Amount_Ref = amount_Ref;
	}

	public String getTran_Date_Ori() {
		return Tran_Date_Ori;
	}

	public void setTran_Date_Ori(String tran_Date_Ori) {
		Tran_Date_Ori = tran_Date_Ori;
	}

	public String getResp_Code() {
		return Resp_Code;
	}

	public void setResp_Code(String resp_Code) {
		Resp_Code = resp_Code;
	}

	public String getAuth_Code() {
		return Auth_Code;
	}

	public void setAuth_Code(String auth_Code) {
		Auth_Code = auth_Code;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getAmount_Ori() {
		return Amount_Ori;
	}

	public void setAmount_Ori(String amount_Ori) {
		Amount_Ori = amount_Ori;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

}
