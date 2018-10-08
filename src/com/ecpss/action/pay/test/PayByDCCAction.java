package com.ecpss.action.pay.test;

import java.text.DecimalFormat;
import java.util.List;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.util.MD5;

public class PayByDCCAction extends BaseAction {
    private String  amount_Transaction_Foreign;
    private String  conversion_Rate;//汇率
    private String currency_Code_Foreign;
	private String tradeType;   //交易类型 
	private String rorderno;//订单流水号
	private String MD5info;
	private String MD5key;	
	private String md5Value;   //支付网关对商户信息进行加密	
	private int responseCode; 	//返回参数  	
	private String message;
	
	private String ReturnURL;
	private String merchantOrderNo;
	private String tradeMoneyType;
	private String car_termanal;

	private Double ordercount;   //订单金额	
	
	private String checktrade;


	public String paybydcc(){
		try{
		List listtrade=this.commonService.list(" select t from InternationalTradeinfo t where  t.orderNo='"+this.rorderno+"'");
		List listcarinfo=this.commonService.list(" select f from InternationalTradeinfo t, InternationalCardholdersInfo f where t.id=f.tradeId and t.orderNo='"+this.rorderno+"'");

		InternationalTradeinfo trade=new InternationalTradeinfo();
		InternationalCardholdersInfo card=new InternationalCardholdersInfo();
		InternationalChannels chnal= new InternationalChannels();
		MD5 md5 = new MD5();
	    
		if(listtrade.size()==1){
			trade=(InternationalTradeinfo)listtrade.get(0);	
		}if(listcarinfo.size()==1){
			card=(InternationalCardholdersInfo)listcarinfo.get(0);
		}
		InternationalMerchant im=(InternationalMerchant)this.commonService.load(InternationalMerchant.class, trade.getMerchantId());	
		//转换成标准金额
		ordercount = trade.getTradeAmount();

		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		String ordercountValue = decimalFormat.format(ordercount);	
		
		this.ReturnURL=trade.getReturnUrl();
		this.merchantOrderNo=trade.getMerchantOrderNo();
		this.tradeMoneyType=trade.getMoneyType()+"";
		this.MD5key=im.getMd5key();

	List<InternationalChannels> ic=this.commonService.list(" select t from InternationalChannels t,InternationalMerchantChannels f where t.id=f.channelId and f.id='"+trade.getTradeChannel()+"'"); 
	if(ic.size()>0){
		chnal=ic.get(0);	
		}	
    List<InternationalTerminalManager>  timlist=this.commonService.list(" from InternationalTerminalManager t where t.terminalNo='"+trade.getVIPTerminalNo()+"'");
    InternationalTerminalManager tim=timlist.get(0);
    if(this.tradeType.equals("YY")){		
    	//交易

   	 DCCMessage msg2=new DCCMessage();
   	 msg2.setMessageType("0200");//类型
   	 msg2.setPrimary_Account_Number(card.getCardNo());//账号2
   	 msg2.setProcessing_Code("000000");//处理代码3
   	 msg2.setAmount_Transaction_Local(this.buzero(trade.getRmbAmount()+""));//4 本地交易金额
   	 msg2.setAmount_Transaction_Foreign(this.amount_Transaction_Foreign);//5  0810
   	 msg2.setConversion_Rate(this.conversion_Rate);//9    0810
   	 msg2.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//11  交易流水号
   	 msg2.setDATE_EXPIRATION(card.getExpiryDate().substring(
				2, 4)
				+ card.getExpiryDate().substring(0, 2));//14   有效期
   	 msg2.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
   	 msg2.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
   	 msg2.setPOS_CONDITION_CODE("00");//25 商户编码
//   	 msg2.setRETRIEVAL_REFERENCE_NUMBER("");//37
   	 msg2.setCARD_ACCEPTOR_TERMINAL_ID(trade.getVIPTerminalNo());//41  商户终端号
   	 msg2.setCARD_ACCEPTOR_ID_CODE(tim.getMerchantNo());//42 商户编号 
   	 msg2.setCurrency_Code_Foreign(this.currency_Code_Foreign);//49 货币代码-----0810
   	 msg2.setCVV2_OR_CVC2(card.getCvv2());//cv2
   	 msg2.setInvoice_Number(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//62	
	 DccUtil dc=new DccUtil();
	 dc.setDccMessage(msg2);
	 try{
	 msg2=dc.getDccMessage();
	 }
	 catch(Exception ex){
     	 DCCMessage msg6=new DCCMessage();
       	 msg6.setMessageType("0400");//类型
       	 msg6.setPrimary_Account_Number(card.getCardNo());//账号2
       	 msg6.setProcessing_Code("000000");//处理代码3
       	 msg6.setAmount_Transaction_Local(this.buzero(trade.getRmbAmount()+""));//4 本地交易金额
       	 msg6.setAmount_Transaction_Foreign(this.amount_Transaction_Foreign);//5  0810
       	 msg6.setConversion_Rate(this.conversion_Rate);//9    0810
       	 msg6.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//11  交易流水号
       	 msg6.setDATE_EXPIRATION(card.getExpiryDate().substring(
					2, 4)
					+ card.getExpiryDate().substring(0, 2));//14   有效期
       	 msg6.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
       	 msg6.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
       	 msg6.setPOS_CONDITION_CODE("00");//25 商户编码
       	 msg6.setRETRIEVAL_REFERENCE_NUMBER(msg2.getRETRIEVAL_REFERENCE_NUMBER());//37
       	 msg6.setCARD_ACCEPTOR_TERMINAL_ID(trade.getVIPTerminalNo());//41  商户终端号
       	 msg6.setCARD_ACCEPTOR_ID_CODE(tim.getMerchantNo());//42 商户编号 
       	 msg6.setCurrency_Code_Foreign(this.currency_Code_Foreign);//49 货币代码-----0810
       	 msg6.setInvoice_Number(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//62	
    	 DccUtil dc6=new DccUtil();
    	 dc6.setDccMessage(msg6);

    	 msg6=dc6.getDccMessage();
    	 System.out.println("===============================yy交易冲正(撤销冲正):"+msg6.getRESPONSE_CODE());		 
    	 //传递的参数    
		    responseCode=19;
			message = "Your payment is being processed";
			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
			md5Value = md5.getMD5ofStr(MD5info);  		
			this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"+trade.getId()+"'");
	        return SUCCESS;     		 		 
	 }
	 if(msg2.getRESPONSE_CODE().equals("00")){
	this.message="Payment Success!";	 
    this.responseCode=88;
    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id='"+trade.getId()+"'");
	
	MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	md5Value = md5.getMD5ofStr(MD5info);	
	return SUCCESS;
	 }
	 else{
		    this.responseCode=Integer.valueOf(msg2.getRESPONSE_CODE());
			this.message="Payment Declined!";
		    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id='"+trade.getId()+"'");
			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
			md5Value = md5.getMD5ofStr(MD5info);	
			return SUCCESS;
	 }
   	 
    }
    if(this.tradeType.equals("YX")){

String ter = "select tm from InternationalTerminalManager tm where tm.terminalNo='"
		+ tim.getAndterminalNo().trim() + "' ";
List<InternationalTerminalManager> list = this.commonService
		.list(ter);    	
     	 DCCMessage dcc3=new DCCMessage();
    	 dcc3.setMessageType("0200");//类型
    	 dcc3.setPrimary_Account_Number(card.getCardNo());//账号2
    	 dcc3.setProcessing_Code("000000");//处理代码3
    	 dcc3.setAmount_Transaction_Local(this.buzero(trade.getRmbAmount()+""));//4 本地交易金额
    	 dcc3.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//11  交易流水号
    	 dcc3.setDATE_EXPIRATION(card.getExpiryDate().substring(
					2, 4)
					+ card.getExpiryDate().substring(0, 2));//14   有效期
    	 dcc3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
    	 dcc3.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
         dcc3.setCARD_ACCEPTOR_TERMINAL_ID(list.get(0).getTerminalNo());//41  商户终端号
    	 dcc3.setCARD_ACCEPTOR_ID_CODE(list.get(0).getMerchantNo());//42 商户编号 
    	 dcc3.setCVV2_OR_CVC2(card.getCvv2());//cv2 61
    	 dcc3.setInvoice_Number(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//62	
    	 DccUtil dc=new DccUtil();
    	 dc.setDccMessage(dcc3);
    	 try{
    	 dcc3=dc.getDccMessage();  
    	 }
    	 catch(Exception ex){
         	 DCCMessage msg7=new DCCMessage();
           	 msg7.setMessageType("0400");//类型
           	 msg7.setPrimary_Account_Number(card.getCardNo());//账号2
           	 msg7.setProcessing_Code("000000");//处理代码3
           	 msg7.setAmount_Transaction_Local(this.buzero(trade.getRmbAmount()+""));//4 本地交易金额
//           	 msg7.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5  0810
//           	 msg7.setConversion_Rate(dcc.getConversion_Rate());//9    0810
           	 msg7.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//11  交易流水号
           	 msg7.setDATE_EXPIRATION(card.getExpiryDate().substring(
						2, 4)
						+ card.getExpiryDate().substring(0, 2));//14   有效期
           	 msg7.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
           	 msg7.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
           	 msg7.setPOS_CONDITION_CODE("00");//25 商户编码
           	 msg7.setRETRIEVAL_REFERENCE_NUMBER(dcc3.getRETRIEVAL_REFERENCE_NUMBER());//37
           	 msg7.setCARD_ACCEPTOR_TERMINAL_ID(list.get(0).getTerminalNo());//41  商户终端号
           	 msg7.setCARD_ACCEPTOR_ID_CODE(list.get(0).getMerchantNo());//42 商户编号 
 //          	 msg7.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49 货币代码-----0810
//           	 msg7.setCVV2_OR_CVC2(cvv2);//cv2
           	 msg7.setInvoice_Number(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//62	
        	 DccUtil dc7=new DccUtil();
        	 dc7.setDccMessage(msg7);

        	 msg7=dc7.getDccMessage();
        	 System.out.println("=====================yx交易冲正:"+msg7.getRESPONSE_CODE());     		     		 
        	 //传递的参数    
 		    responseCode=19;
 			message = "Your payment is being processed";
 			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
 			md5Value = md5.getMD5ofStr(MD5info);  		
 			this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"+trade.getId()+"'");
 	        return SUCCESS;    		 
    	 }
    	 if(dcc3.getRESPONSE_CODE().equals("00")){
    			this.message="Payment Success!";	 
    		    this.responseCode=88;
    		    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id='"+trade.getId()+"'");
    			
    			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
    			md5Value = md5.getMD5ofStr(MD5info);	
    			return SUCCESS;	
    			 }
    			 else{
    					this.message="Payment Declined!";	
    				    this.responseCode=Integer.valueOf(dcc3.getRESPONSE_CODE());
     				    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id='"+trade.getId()+"'");
    					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
    					md5Value = md5.getMD5ofStr(MD5info);		 
    					return SUCCESS;
    			 }   	
    }

   	return this.SUCCESS;
		}catch(Exception ex){
			return ERROR;
		}
	}


	public String getAmount_Transaction_Foreign() {
		return amount_Transaction_Foreign;
	}


	public void setAmount_Transaction_Foreign(String amount_Transaction_Foreign) {
		this.amount_Transaction_Foreign = amount_Transaction_Foreign;
	}


	public String getConversion_Rate() {
		return conversion_Rate;
	}


	public void setConversion_Rate(String conversion_Rate) {
		this.conversion_Rate = conversion_Rate;
	}


	public String getCurrency_Code_Foreign() {
		return currency_Code_Foreign;
	}


	public void setCurrency_Code_Foreign(String currency_Code_Foreign) {
		this.currency_Code_Foreign = currency_Code_Foreign;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public String getRorderno() {
		return rorderno;
	}


	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}


	public String getMd5Value() {
		return md5Value;
	}


	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}


	public int getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getReturnURL() {
		return ReturnURL;
	}


	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}


	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}


	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}


	public String getTradeMoneyType() {
		return tradeMoneyType;
	}


	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}


	public Double getOrdercount() {
		return ordercount;
	}


	public void setOrdercount(Double ordercount) {
		this.ordercount = ordercount;
	}
	
	public String getChecktrade() {
		return checktrade;
	}


	public void setChecktrade(String checktrade) {
		this.checktrade = checktrade;
	}
	public String buzero(String len){
		String tem="";
		if(len.indexOf(".")==-1){
			int lenght=len.length();
           for(int i=0;i<10-lenght;i++){
        	   tem="0"+tem;
           }
           tem=tem+"00";
		}else{
		    tem=len.substring(0,len.indexOf("."));
			String tem2=len.substring(len.indexOf(".")+1,len.length());
			tem=tem+tem2;
			int lenght=tem.length();
	        for(int i=0;i<12-lenght;i++){
	     	   tem="0"+tem;
	        }				
		}
		return tem;
	}


	public String getCar_termanal() {
		return car_termanal;
	}


	public void setCar_termanal(String car_termanal) {
		this.car_termanal = car_termanal;
	}
}
