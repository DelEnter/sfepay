package com.ecpss.action.pay.dcc;

public class AnalysisDCC {
    private	DCCMessage cmesag=new DCCMessage();
           

	// 解析DCC数据
	public void analysisDcc(String bytString) {
//		char[] bigmap = DCCMessageUtil.getbitMap(bytString);
		// 数据长度		
           		String temString=bytString.substring(18, bytString.length());
		char[]	bigmap = DCCMessageUtil.getbitMap(temString.substring(0,16));
		temString=temString.substring(16, temString.length());
		int point = 8;
		if (bigmap[1] == '1') {
			point += 18;
			this.cmesag.setPrimary_Account_Number(temString.substring(2, 18));
			temString=temString.substring(18, temString.length());
		}
		if (bigmap[2] == '1') {

			point += 6;
			this.cmesag.setProcessing_Code(temString.substring(0, 6));
			temString=temString.substring(6, temString.length());
		}
		if (bigmap[3] == '1') {

			point += 12;
			this.cmesag.setAmount_Transaction_Local(temString.substring(0, 12));
			temString=temString.substring(12, temString.length());
		}
		if (bigmap[4] == '1') {

			point += 12;
			// tembdc+=DCCMessageUtil.toHexValue(Amount_Transaction_Foreign,12);
			this.cmesag.setAmount_Transaction_Foreign(temString.substring(0, 12));
			temString=temString.substring(12, temString.length());
		}
		if (bigmap[8] == '1') {

			point += 8;
			// tembdc+=DCCMessageUtil.toHexValue(Conversion_Rate,8);
			this.cmesag.setConversion_Rate(temString.substring(0, 8));
			temString=temString.substring(8, temString.length());
		}
		if (bigmap[10] == '1') {

			point += 6;

			this.cmesag.setSYSTEMS_TRACE_AUDIT_NUMBER(temString.substring(0, 6));
			temString=temString.substring(6, temString.length());
		}
		if (bigmap[11] == '1') {
			point += 6;

			this.cmesag.setTIME_LOCAL_TRANSACTION(temString.substring(0, 6));
			temString=temString.substring(6, temString.length());
		}
		if (bigmap[12] == '1') {

			point += 4;

			this.cmesag.setDATE_LOCAL_TRANSACTION(temString.substring(0, 4));
			temString=temString.substring(4, temString.length());
		}
		if (bigmap[13] == '1') {
			point += 4;

			this.cmesag.setDATE_EXPIRATION(temString.substring(0, 4));
			temString=temString.substring(4, temString.length());
		}
		if (bigmap[21] == '1') {

			point += 4;
			// tembdc+=DCCMessageUtil.toHexValue(POINT_OF_SERVICE_ENTRY_CODE,3);
			this.cmesag.setPOINT_OF_SERVICE_ENTRY_CODE(temString.substring(0, 4));
			temString=temString.substring(4, temString.length());
		}
		if (bigmap[23] == '1') {

			point += 4;
			// tembdc+=DCCMessageUtil.toHexValue(NETWORK_INTL_IDENTIFIER,3);
			this.cmesag.setNETWORK_INTL_IDENTIFIER(temString.substring(0, 4));
			temString=temString.substring(4, temString.length());
		}
		if (bigmap[24] == '1') {
			point += 2;
			// tembdc+=DCCMessageUtil.toHexValue(POS_CONDITION_CODE,2);
			this.cmesag.setPOS_CONDITION_CODE(temString.substring(0, 2));
			temString=temString.substring(2, temString.length());
		}
		if (bigmap[36] == '1') {
			point += 12;
			
			// temASII+=DCCMessageUtil.toASCIIValue(RETRIEVAL_REFERENCE_NUMBER,12);
			this.cmesag.setRETRIEVAL_REFERENCE_NUMBER(new String(DCCMessageUtil.hexStringToByte(temString.substring(0, 24))));
			temString=temString.substring(24, temString.length());	
		}
		if (bigmap[37] == '1') {
			point += 6;
			this.cmesag.setAUTH_IDENTIFICATION_RESPONSE(new String(DCCMessageUtil.hexStringToByte(temString.substring(0, 12))));
			temString=temString.substring(12, temString.length());	
		}
		if (bigmap[38] == '1') {

			point += 2;
			// temASII+=DCCMessageUtil.toASCIIValue(RESPONSE_CODE,2);
			this.cmesag.setRESPONSE_CODE(new String(DCCMessageUtil.hexStringToByte(temString.substring(0, 4))));
			temString=temString.substring(4, temString.length());	
		}
		if (bigmap[40] == '1') {
			point += 16;
			// temASII+=DCCMessageUtil.toASCIIValue(CARD_ACCEPTOR_TERMINAL_ID,8);
			this.cmesag.setCARD_ACCEPTOR_TERMINAL_ID(new String(DCCMessageUtil.hexStringToByte(temString.substring(0, 16))));
			temString=temString.substring(16, temString.length());	
		}
		if (bigmap[41] == '1') {
			point += 30;
			// temASII+=DCCMessageUtil.toASCIIValue(CARD_ACCEPTOR_ID_CODE,15);
			this.cmesag.setCARD_ACCEPTOR_ID_CODE(new String(DCCMessageUtil.hexStringToByte(temString.substring(0, 30))));
			temString=temString.substring(30, temString.length());	
		}
		if (bigmap[48] == '1') {
			point += 6;
			// temASII+=DCCMessageUtil.toASCIIValue(Currency_Code_Foreign,3);
			this.cmesag.setCurrency_Code_Foreign(new String(DCCMessageUtil.hexStringToByte(temString.substring(0, 6))));
			temString=temString.substring(6, temString.length());	
		}
		if (bigmap[59] == '1') {
			point += 12;
			// temASII+=DCCMessageUtil.toASCIIValue(Batch_Number,6);
			this.cmesag.setBatch_Number(new String(DCCMessageUtil.hexStringToByte(temString.substring(0, 12))));
			temString=temString.substring(12, temString.length());	
		}
		if (bigmap[60] == '1') {
			point += 10;
			// temASII+=DCCMessageUtil.toASCIIValue(CVV2_OR_CVC2,3);
			this.cmesag.setCVV2_OR_CVC2(new String(DCCMessageUtil.hexStringToByte(temString.substring(4, 10))));
			temString=temString.substring(10, temString.length());	
		}
		if (bigmap[61] == '1') {
			point += 16;
			// temASII+=DCCMessageUtil.toASCIIValue(Invoice_Number,6);
			this.cmesag.setInvoice_Number(new String(DCCMessageUtil.hexStringToByte(temString.substring(4, 16))));
			temString=temString.substring(16, temString.length());	
		}
		if (bigmap[62] == '1') {
			point += 999;
			// temASII+=DCCMessageUtil.toASCIIValue(RESERVED_PRIVATE,999);
			String tem=temString.substring(0, 4);
			int index=Integer.valueOf(tem);
			this.cmesag.setRESERVED_PRIVATE(new String(DCCMessageUtil.hexStringToByte(temString.substring(4, 4+index*2))));
          System.out.println(this.cmesag.getRESERVED_PRIVATE());
			//			temString=temString.substring(19, temString.length());	
		}
	}

	public DCCMessage getCmesag() {
		return cmesag;
	}

	public void setCmesag(DCCMessage cmesag) {
		this.cmesag = cmesag;
	}
}
