package com.ecpss.action.pay.test;

import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;

public class temTest {
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String posNum="22222222";// 终端号
		String merchant="021209999000000";//商户号
		String amount="000000000010";//本地交易金额
//		String Transation_Local="";
		String Exp="1210";//有效期
		String cardno="4402602810763408";//卡号
		String cvv2="123";
		String orderNo="021023";//流水号	
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
    	 System.out.println("=============================查询返回交易类型"+dcc.getRESPONSE_CODE());
     	 
       //0200  交易
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
    	 dc2.setDccMessage(msg2);

    	 msg2=dc2.getDccMessage();
    	 System.out.println("===============================yy交易:"+msg2.getRESPONSE_CODE());
     	 }
     	 else if(dcc.getRESPONSE_CODE().equals("YX")){
    	 
    //0200	 
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
       	 msg3.setCARD_ACCEPTOR_TERMINAL_ID(dcc.getCARD_ACCEPTOR_TERMINAL_ID());//41  商户终端号
       	 msg3.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
       	 msg3.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49 货币代码-----0810
       	 msg3.setCVV2_OR_CVC2(cvv2);//cv2
       	 msg3.setInvoice_Number(orderNo);//62	
    	 DccUtil dc3=new DccUtil();
    	 dc3.setDccMessage(msg3);

    	 msg3=dc3.getDccMessage();
    	 System.out.println("=====================yx交易:"+msg3.getRESPONSE_CODE());
     	 }
  //撤销交易 0200(YY)   	 
     	 if(dcc.getRESPONSE_CODE().equals("YY")){
     	 DCCMessage msg4=new DCCMessage();
       	 msg4.setMessageType("0200");//类型
       	 msg4.setPrimary_Account_Number(cardno);//账号2
       	 msg4.setProcessing_Code("020000");//处理代码3
       	 msg4.setAmount_Transaction_Local(amount);//4 本地交易金额
       	 msg4.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5  0810
       	 msg4.setConversion_Rate(dcc.getConversion_Rate());//9    0810
       	 msg4.setSYSTEMS_TRACE_AUDIT_NUMBER(orderNo);//11  交易流水号
       	 msg4.setDATE_EXPIRATION(Exp);//14   有效期
       	 msg4.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
       	 msg4.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
       	 msg4.setPOS_CONDITION_CODE("00");//25 商户编码
       	 msg4.setRETRIEVAL_REFERENCE_NUMBER(dcc.getRETRIEVAL_REFERENCE_NUMBER());//37
       	 msg4.setAUTH_IDENTIFICATION_RESPONSE(dcc.getAUTH_IDENTIFICATION_RESPONSE());//38
       	 msg4.setCARD_ACCEPTOR_TERMINAL_ID(posNum);//41  商户终端号
       	 msg4.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
       	 msg4.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49 货币代码-----0810
//     	 msg4.setCVV2_OR_CVC2(cvv2);//cv2
       	 msg4.setInvoice_Number(orderNo);//62	
    	 DccUtil dc4=new DccUtil();
    	 dc4.setDccMessage(msg4);

    	 msg4=dc4.getDccMessage();
    	 System.out.println("============================YY撤销交易:"+msg4.getRESPONSE_CODE());
     	 }else if(dcc.getRESPONSE_CODE().equals("YX")){
         	 DCCMessage msg5=new DCCMessage();
           	 msg5.setMessageType("0200");//类型
           	 msg5.setPrimary_Account_Number(cardno);//账号2
           	 msg5.setProcessing_Code("020000");//处理代码3
           	 msg5.setAmount_Transaction_Local(amount);//4 本地交易金额
//          	 msg5.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5  0810
           	 msg5.setConversion_Rate(dcc.getConversion_Rate());//9    0810
           	 msg5.setSYSTEMS_TRACE_AUDIT_NUMBER(orderNo);//11  交易流水号
           	 msg5.setDATE_EXPIRATION(Exp);//14   有效期
           	 msg5.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
           	 msg5.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
           	 msg5.setPOS_CONDITION_CODE("00");//25 商户编码
           	 msg5.setRETRIEVAL_REFERENCE_NUMBER(dcc.getRETRIEVAL_REFERENCE_NUMBER());//37
           	 msg5.setAUTH_IDENTIFICATION_RESPONSE(dcc.getAUTH_IDENTIFICATION_RESPONSE());//38
           	 msg5.setCARD_ACCEPTOR_TERMINAL_ID(dcc.getCARD_ACCEPTOR_TERMINAL_ID());//41  商户终端号
           	 msg5.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
           	 msg5.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49 货币代码-----0810
           	 msg5.setCVV2_OR_CVC2(cvv2);//cv2
           	 msg5.setInvoice_Number(orderNo);//62	
        	 DccUtil dc5=new DccUtil();
        	 dc5.setDccMessage(msg5);

        	 msg5=dc5.getDccMessage();
        	 System.out.println("=====================msg5撤销yx交易:"+msg5.getRESPONSE_CODE());     		 
     		 
     	 }
     	 //冲正  0400
     	 if(dcc.getRESPONSE_CODE().equals("YY")){
         	 DCCMessage msg6=new DCCMessage();
           	 msg6.setMessageType("0400");//类型
           	 msg6.setPrimary_Account_Number(cardno);//账号2
           	 msg6.setProcessing_Code("000000");//处理代码3
           	 msg6.setAmount_Transaction_Local(amount);//4 本地交易金额
           	 msg6.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5  0810
           	 msg6.setConversion_Rate(dcc.getConversion_Rate());//9    0810
           	 msg6.setSYSTEMS_TRACE_AUDIT_NUMBER(orderNo);//11  交易流水号
           	 msg6.setDATE_EXPIRATION(Exp);//14   有效期
           	 msg6.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
           	 msg6.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
           	 msg6.setPOS_CONDITION_CODE("00");//25 商户编码
           	 msg6.setRETRIEVAL_REFERENCE_NUMBER(dcc.getRETRIEVAL_REFERENCE_NUMBER());//37
           	 msg6.setCARD_ACCEPTOR_TERMINAL_ID(posNum);//41  商户终端号
           	 msg6.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
           	 msg6.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49 货币代码-----0810
           	 msg6.setInvoice_Number(orderNo);//62	
        	 DccUtil dc6=new DccUtil();
        	 dc6.setDccMessage(msg6);

        	 msg6=dc6.getDccMessage();
        	 System.out.println("===============================yy交易冲正(撤销冲正):"+msg6.getRESPONSE_CODE());
    		 
     		 
     	 }else if(dcc.getRESPONSE_CODE().equals("YX")){
         	 DCCMessage msg7=new DCCMessage();
           	 msg7.setMessageType("0400");//类型
           	 msg7.setPrimary_Account_Number(cardno);//账号2
           	 msg7.setProcessing_Code("000000");//处理代码3
           	 msg7.setAmount_Transaction_Local(amount);//4 本地交易金额
//           	 msg7.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5  0810
//           	 msg7.setConversion_Rate(dcc.getConversion_Rate());//9    0810
           	 msg7.setSYSTEMS_TRACE_AUDIT_NUMBER(orderNo);//11  交易流水号
           	 msg7.setDATE_EXPIRATION(Exp);//14   有效期
           	 msg7.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
           	 msg7.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
           	 msg7.setPOS_CONDITION_CODE("00");//25 商户编码
           	 msg7.setRETRIEVAL_REFERENCE_NUMBER(dcc.getRETRIEVAL_REFERENCE_NUMBER());//37
           	 msg7.setCARD_ACCEPTOR_TERMINAL_ID(dcc.getCARD_ACCEPTOR_TERMINAL_ID());//41  商户终端号
           	 msg7.setCARD_ACCEPTOR_ID_CODE(merchant);//42 商户编号 
 //          	 msg7.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49 货币代码-----0810
//           	 msg7.setCVV2_OR_CVC2(cvv2);//cv2
           	 msg7.setInvoice_Number(orderNo);//62	
        	 DccUtil dc7=new DccUtil();
        	 dc7.setDccMessage(msg7);

        	 msg7=dc7.getDccMessage();
        	 System.out.println("=====================yx交易冲正:"+msg7.getRESPONSE_CODE());     		 
     		 
     	 }
     	 
     	 
     	 
	}

}
