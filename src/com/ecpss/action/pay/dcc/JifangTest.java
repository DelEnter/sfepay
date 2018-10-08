package com.ecpss.action.pay.dcc;

public class JifangTest {
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String posNum="22222222";// 终端号
		String merchant="021209999000000";//商户号
		String amount="000000000100";//本地交易金额
//		String Transation_Local="";
		String Exp="1210";//有效期
		String cardno="4402602810763408";//卡号
		String cvv2="123";
		String orderNo="021021";//流水号	
		//0800
      	 DCCMessage dcc=new DCCMessage();
    	 dcc.setMessageType("0800");//类型
    	 dcc.setPrimary_Account_Number(cardno);//账号2
    	 dcc.setProcessing_Code("970000");//处理代码3
    	 dcc.setAmount_Transaction_Local(amount);//4 本地交易金额
    	 dcc.setSYSTEMS_TRACE_AUDIT_NUMBER(orderNo);//11  交易流水号
    	 dcc.setDATE_EXPIRATION(Exp);//14   有效期
    	 dcc.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
    	 dcc.setNETWORK_INTL_IDENTIFIER("0098");//24  收单商户号
    	 dcc.setPOS_CONDITION_CODE("00");//25 商户编码
    	 dcc.setCARD_ACCEPTOR_TERMINAL_ID(posNum);//41  商户终端号
    	 dcc.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
    	 dcc.setInvoice_Number(orderNo);//62	
    	 DccUtil dc=new DccUtil();
    	 dc.setDccMessage(dcc);
      	 dcc=dc.getDccMessage();
      	 System.out.println(dcc.getAmount_Transaction_Foreign());
     	 System.out.println(dcc.getConversion_Rate());
    	 System.out.println(dcc.getCurrency_Code_Foreign());
    	 System.out.println(dcc.getRESPONSE_CODE());
     	 
       //0200
     	 if(dcc.getRESPONSE_CODE().equals("YY")){
     	 DCCMessage msg2=new DCCMessage();
       	 msg2.setMessageType("0200");//类型
       	 msg2.setPrimary_Account_Number(cardno);//账号2
       	 msg2.setProcessing_Code("000000");//处理代码3
       	 msg2.setAmount_Transaction_Local(amount);//4 本地交易金额
       	 msg2.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5  0810
       	 msg2.setConversion_Rate(dcc.getConversion_Rate());//9    0810
       	 msg2.setSYSTEMS_TRACE_AUDIT_NUMBER(orderNo);//11  交易流水号
       	 msg2.setDATE_EXPIRATION(Exp);//14   有效期
       	 msg2.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
       	 msg2.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
       	 msg2.setPOS_CONDITION_CODE("00");//25 商户编码
//       	 msg2.setRETRIEVAL_REFERENCE_NUMBER("");//37
       	 msg2.setCARD_ACCEPTOR_TERMINAL_ID(posNum);//41  商户终端号
       	 msg2.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
       	 msg2.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49 货币代码-----0810
       	 msg2.setCVV2_OR_CVC2(cvv2);//cv2
       	 msg2.setInvoice_Number(orderNo);//62	
    	 DccUtil dc2=new DccUtil();
    	 dc.setDccMessage(msg2);

    	 msg2=dc.getDccMessage();
    	 System.out.println(msg2.getRESPONSE_CODE());
     	 }
     	 else if(dcc.getRESPONSE_CODE().equals("YX")){
    	 
    //0200	 
     		/*
     		 * 		String posNum="";// 终端号
		String merchant="";//商户号
		String amount="";//本地交易金额
//		String Transation_Local="";
		String Exp="";//有效期
		String cardno="";//卡号
		String cvv2="";
		String orderNo="";//流水号	
     		 */ 
     		 
     	 DCCMessage msg3=new DCCMessage();
       	 msg3.setMessageType("0200");//类型
       	 msg3.setPrimary_Account_Number(cardno);//账号2
       	 msg3.setProcessing_Code("000000");//处理代码3
       	 msg3.setAmount_Transaction_Local(amount);//4 本地交易金额
       	 msg3.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5  0810
       	 msg3.setConversion_Rate(dcc.getConversion_Rate());//9    0810
       	 msg3.setSYSTEMS_TRACE_AUDIT_NUMBER(orderNo);//11  交易流水号
       	 msg3.setDATE_EXPIRATION(Exp);//14   有效期
       	 msg3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
       	 msg3.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
       	 msg3.setPOS_CONDITION_CODE("00");//25 商户编码
//       	 msg3.setRETRIEVAL_REFERENCE_NUMBER("");//37
       	 msg3.setCARD_ACCEPTOR_TERMINAL_ID(posNum);//41  商户终端号
       	 msg3.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
       	 msg3.setCurrency_Code_Foreign("970");//49 货币代码-----0810
       	 msg3.setCVV2_OR_CVC2(cvv2);//cv2
       	 msg3.setInvoice_Number(orderNo);//62	
    	 DccUtil dc3=new DccUtil();
    	 dc3.setDccMessage(msg3);

    	 msg3=dc3.getDccMessage();
    	 System.out.println(msg3.getRESPONSE_CODE());
     	 }
	}

}
