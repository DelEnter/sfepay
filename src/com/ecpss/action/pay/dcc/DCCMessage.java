package com.ecpss.action.pay.dcc;

import java.math.BigDecimal;

public class DCCMessage {
	//消息类型
	private String messageType;
	
	// 2
	private String Primary_Account_Number;
	// 3
	private String Processing_Code;
	// 4
	private String Amount_Transaction_Local;
	// 5
	private String Amount_Transaction_Foreign;
	// 9
	private String Conversion_Rate;
	// 11
	private String SYSTEMS_TRACE_AUDIT_NUMBER;
	// 12
	private String TIME_LOCAL_TRANSACTION;
	// 13
	private String DATE_LOCAL_TRANSACTION;
	// 14
	private String DATE_EXPIRATION;
	// 22
	private String POINT_OF_SERVICE_ENTRY_CODE;
	// 24
	private String NETWORK_INTL_IDENTIFIER;
	// 25
	private String POS_CONDITION_CODE;
	// 37
	private String RETRIEVAL_REFERENCE_NUMBER;
	// 38
	private String AUTH_IDENTIFICATION_RESPONSE;
	// 39
	private String RESPONSE_CODE;
	// 41
	private String CARD_ACCEPTOR_TERMINAL_ID;
	// 42
	private String CARD_ACCEPTOR_ID_CODE;
	// 49
	private String Currency_Code_Foreign;
	// 60
	private String Batch_Number;
	// 61
	private String CVV2_OR_CVC2;
	// 62
	private String Invoice_Number;
	// 63
	private String RESERVED_PRIVATE;

	// 获取拼接后的报文
	public byte[] getMessage() {

		char[] bigmap = new char[64];
		String defaultHeadBit = "0000000000000000000000000000000000000000000000000000000000000000";
		bigmap = defaultHeadBit.toCharArray();
		String tembdc = "";
		String temASII = "";
		String temASII2="";
		if (this.Primary_Account_Number != null) {
			bigmap[1] = '1';
			String varTem = this.Primary_Account_Number.length() + "";
//			tembdc += varTem.length();
			tembdc += DCCMessageUtil.toHexValue("16"+Primary_Account_Number, 18);

		}
		if (this.Processing_Code != null) {
			bigmap[2] = '1';
			tembdc += DCCMessageUtil.toHexValue(Processing_Code, 6);
		}
		if (this.Amount_Transaction_Local != null) {
			bigmap[3] = '1';
			tembdc += DCCMessageUtil.toHexValue(Amount_Transaction_Local, 12);
		}
		if (this.Amount_Transaction_Foreign != null) {
			bigmap[4] = '1';
			tembdc += DCCMessageUtil.toHexValue(Amount_Transaction_Foreign, 12);
		}
		if (this.Conversion_Rate != null) {
			bigmap[8] = '1';
			tembdc += DCCMessageUtil.toHexValue(Conversion_Rate, 8);
		}
		if (this.SYSTEMS_TRACE_AUDIT_NUMBER != null) {
			bigmap[10] = '1';
			tembdc += DCCMessageUtil.toHexValue(SYSTEMS_TRACE_AUDIT_NUMBER, 6);
		}
		if (this.TIME_LOCAL_TRANSACTION != null) {
			bigmap[11] = '1';
			tembdc += DCCMessageUtil.toHexValue(TIME_LOCAL_TRANSACTION, 6);
		}
		if (this.DATE_LOCAL_TRANSACTION != null) {
			bigmap[12] = '1';
			tembdc += DCCMessageUtil.toHexValue(DATE_LOCAL_TRANSACTION, 4);
		}
		if (this.DATE_EXPIRATION != null) {
			bigmap[13] = '1';
			tembdc += DCCMessageUtil.toHexValue(DATE_EXPIRATION, 4);
		}
		if (this.POINT_OF_SERVICE_ENTRY_CODE != null) {
			bigmap[21] = '1';
			tembdc += DCCMessageUtil.toHexValue(POINT_OF_SERVICE_ENTRY_CODE, 3);
		}
		if (this.NETWORK_INTL_IDENTIFIER != null) {
			bigmap[23] = '1';
			tembdc += DCCMessageUtil.toHexValue(NETWORK_INTL_IDENTIFIER, 3);
		}
		if (this.POS_CONDITION_CODE != null) {
			bigmap[24] = '1';
			tembdc += DCCMessageUtil.toHexValue(POS_CONDITION_CODE, 2);
		}
		if (this.RETRIEVAL_REFERENCE_NUMBER != null) {
			bigmap[36] = '1';
			temASII += DCCMessageUtil.bytesToHexString(RETRIEVAL_REFERENCE_NUMBER.getBytes());
			
		}
		if (this.AUTH_IDENTIFICATION_RESPONSE != null) {
			bigmap[37] = '1';
			temASII += DCCMessageUtil.bytesToHexString(AUTH_IDENTIFICATION_RESPONSE.getBytes());
		}
		if (this.RESPONSE_CODE != null) {
			bigmap[38] = '1';
			temASII += DCCMessageUtil.bytesToHexString(RESPONSE_CODE.getBytes());
		}
		if (this.CARD_ACCEPTOR_TERMINAL_ID != null) {
			bigmap[40] = '1';
			temASII += DCCMessageUtil.bytesToHexString(CARD_ACCEPTOR_TERMINAL_ID.getBytes());
		}
		if (this.CARD_ACCEPTOR_ID_CODE != null) {
			bigmap[41] = '1';
			temASII += DCCMessageUtil.bytesToHexString(CARD_ACCEPTOR_ID_CODE.getBytes());
		}
		if (this.Currency_Code_Foreign != null) {
			bigmap[48] = '1';
			temASII2 = temASII2+ DCCMessageUtil.bytesToHexString(Currency_Code_Foreign.getBytes());;
		}
		if (this.Batch_Number != null) {
			bigmap[59] = '1';
			String varTem = this.Batch_Number.length() + "";
//			temASII += varTem.length();
			temASII += DCCMessageUtil.toASCIIValue(Batch_Number, 6);
		}
		if (this.CVV2_OR_CVC2 != null) {
			bigmap[60] = '1';
			String varTem = this.CVV2_OR_CVC2.length() + "";
//			temASII += varTem.length();
			temASII2 = temASII2+"0003"+DCCMessageUtil.bytesToHexString(CVV2_OR_CVC2.getBytes());
			
		}
		if (this.Invoice_Number != null) {
			bigmap[61] = '1';
			String varTem = this.Invoice_Number.length() + "";
//			temASII += varTem.length();
			temASII2 = temASII2+"0006"+DCCMessageUtil.bytesToHexString(Invoice_Number.getBytes());
		}
		if (this.RESERVED_PRIVATE != null) {
			bigmap[62] = '1';
//			String varTem = this.RESERVED_PRIVATE.length() + "";
//			temASII += varTem.length();
			temASII += DCCMessageUtil.toASCIIValue(RESERVED_PRIVATE, 999);
		}
		String bigTem = "";
		for (int i = 0; i < bigmap.length; i++) {
			bigTem += bigmap[i];
		}
	String temType="";
	if(this.messageType.equals("0200")||this.messageType.equals("0210")||this.messageType.equals("0400")){
		temType="6000173000";
	}else{
		temType="6000983000";
		
	}
//	String headMap=Integer.toHexString(Integer.parseInt(bigTem,2));
	String headMap=DCCMessageUtil.getByteFromString(bigTem);
	String asIIC=temASII+temASII2;
	int ling=(temType+this.messageType+headMap+tembdc+asIIC).length()/2;
	 String totleLenght= Integer.toHexString(ling);
	if(totleLenght.length()<4){ 
	int temlenght=totleLenght.length();
	 for(int i=0;i<4-temlenght;i++){
		 totleLenght="0"+totleLenght;	
		 
	 }
	}
	String	totleString=totleLenght+temType+this.messageType+ headMap+tembdc+asIIC;
	System.out.println(totleString+"/n"+totleString.length());	
		
		return DCCMessageUtil.str2Bcd(totleString);

	}

	public String getPrimary_Account_Number() {
		return Primary_Account_Number;
	}

	public void setPrimary_Account_Number(String primary_Account_Number) {
		Primary_Account_Number = primary_Account_Number;
	}

	public String getProcessing_Code() {
		return Processing_Code;
	}

	public void setProcessing_Code(String processing_Code) {
		Processing_Code = processing_Code;
	}

	public String getAmount_Transaction_Local() {
		return Amount_Transaction_Local;
	}

	public void setAmount_Transaction_Local(String amount_Transaction_Local) {
		Amount_Transaction_Local = amount_Transaction_Local;
	}

	public String getAmount_Transaction_Foreign() {
		return Amount_Transaction_Foreign;
	}

	public void setAmount_Transaction_Foreign(String amount_Transaction_Foreign) {
		Amount_Transaction_Foreign = amount_Transaction_Foreign;
	}

	public String getConversion_Rate() {
		return Conversion_Rate;
	}

	public void setConversion_Rate(String conversion_Rate) {
		Conversion_Rate = conversion_Rate;
	}

	public String getSYSTEMS_TRACE_AUDIT_NUMBER() {
		return SYSTEMS_TRACE_AUDIT_NUMBER;
	}

	public void setSYSTEMS_TRACE_AUDIT_NUMBER(String systems_trace_audit_number) {
		SYSTEMS_TRACE_AUDIT_NUMBER = systems_trace_audit_number;
	}

	public String getTIME_LOCAL_TRANSACTION() {
		return TIME_LOCAL_TRANSACTION;
	}

	public void setTIME_LOCAL_TRANSACTION(String time_local_transaction) {
		TIME_LOCAL_TRANSACTION = time_local_transaction;
	}

	public String getDATE_LOCAL_TRANSACTION() {
		return DATE_LOCAL_TRANSACTION;
	}

	public void setDATE_LOCAL_TRANSACTION(String date_local_transaction) {
		DATE_LOCAL_TRANSACTION = date_local_transaction;
	}

	public String getDATE_EXPIRATION() {
		return DATE_EXPIRATION;
	}

	public void setDATE_EXPIRATION(String date_expiration) {
		DATE_EXPIRATION = date_expiration;
	}

	public String getPOINT_OF_SERVICE_ENTRY_CODE() {
		return POINT_OF_SERVICE_ENTRY_CODE;
	}

	public void setPOINT_OF_SERVICE_ENTRY_CODE(
			String point_of_service_entry_code) {
		POINT_OF_SERVICE_ENTRY_CODE = point_of_service_entry_code;
	}

	public String getNETWORK_INTL_IDENTIFIER() {
		return NETWORK_INTL_IDENTIFIER;
	}

	public void setNETWORK_INTL_IDENTIFIER(String network_intl_identifier) {
		NETWORK_INTL_IDENTIFIER = network_intl_identifier;
	}

	public String getPOS_CONDITION_CODE() {
		return POS_CONDITION_CODE;
	}

	public void setPOS_CONDITION_CODE(String pos_condition_code) {
		POS_CONDITION_CODE = pos_condition_code;
	}

	public String getRETRIEVAL_REFERENCE_NUMBER() {
		return RETRIEVAL_REFERENCE_NUMBER;
	}

	public void setRETRIEVAL_REFERENCE_NUMBER(String retrieval_reference_number) {
		RETRIEVAL_REFERENCE_NUMBER = retrieval_reference_number;
	}

	public String getAUTH_IDENTIFICATION_RESPONSE() {
		return AUTH_IDENTIFICATION_RESPONSE;
	}

	public void setAUTH_IDENTIFICATION_RESPONSE(
			String auth_identification_response) {
		AUTH_IDENTIFICATION_RESPONSE = auth_identification_response;
	}

	public String getRESPONSE_CODE() {
		return RESPONSE_CODE;
	}

	public void setRESPONSE_CODE(String response_code) {
		RESPONSE_CODE = response_code;
	}

	public String getCARD_ACCEPTOR_TERMINAL_ID() {
		return CARD_ACCEPTOR_TERMINAL_ID;
	}

	public void setCARD_ACCEPTOR_TERMINAL_ID(String card_acceptor_terminal_id) {
		CARD_ACCEPTOR_TERMINAL_ID = card_acceptor_terminal_id;
	}

	public String getCARD_ACCEPTOR_ID_CODE() {
		return CARD_ACCEPTOR_ID_CODE;
	}

	public void setCARD_ACCEPTOR_ID_CODE(String card_acceptor_id_code) {
		CARD_ACCEPTOR_ID_CODE = card_acceptor_id_code;
	}

	public String getCurrency_Code_Foreign() {
		return Currency_Code_Foreign;
	}

	public void setCurrency_Code_Foreign(String currency_Code_Foreign) {
		Currency_Code_Foreign = currency_Code_Foreign;
	}

	public String getBatch_Number() {
		return Batch_Number;
	}

	public void setBatch_Number(String batch_Number) {
		Batch_Number = batch_Number;
	}

	public String getCVV2_OR_CVC2() {
		return CVV2_OR_CVC2;
	}

	public void setCVV2_OR_CVC2(String cvv2_or_cvc2) {
		CVV2_OR_CVC2 = cvv2_or_cvc2;
	}

	public String getInvoice_Number() {
		return Invoice_Number;
	}

	public void setInvoice_Number(String invoice_Number) {
		Invoice_Number = invoice_Number;
	}

	public String getRESERVED_PRIVATE() {
		return RESERVED_PRIVATE;
	}

	public void setRESERVED_PRIVATE(String reserved_private) {
		RESERVED_PRIVATE = reserved_private;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}
