package com.ecpss.action.newpay;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import vpn.DCCMessage;
import vpn.VpnUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.util.AES;
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
    public String paybyAuth(){

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
  //做非DCC交易
String ter = " from InternationalTerminalManager tm where tm.terminalNo='"+ tim.getAndterminalNo().trim() + "' ";
List<InternationalTerminalManager> list = this.commonService
		.list(ter);    	

DCCMessage dcc3 = new DCCMessage();
	dcc3.setTrans_Type("auth");// 类型
	dcc3.setTrans_Model("M");
	dcc3.setMerchant_Id(list.get(0).getMerchantNo());// 42 商户编号
	dcc3.setAuthor_Str(list.get(0).getAuthcode());
	dcc3.setHashCode(list.get(0).getHashcode());
	dcc3.setTerminal_Id(list.get(0)
		.getTerminalNo());// 41 商户终端号
	dcc3.setInvoice_No(trade.getOrderNo().substring(
		trade.getOrderNo().length() - 6,
		trade.getOrderNo().length()));// 11 交易流水号
	dcc3.setCurrency_Code_T("156");// 货币代码
	dcc3.setAmount_Loc(this.buzero(trade
		.getRmbAmount()
		+ ""));// 4 本地交易金额
	dcc3.setCard_No(AES.getCarNo(card.getCardNo()));// 账号2
	dcc3.setExp_Date(AES.getCarNo(card.getExpiryDate())
		.substring(2, 4)
		+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
	dcc3.setCSC(AES.getCarNo(card.getCvv2()));
	dcc3.setOrder_No(trade.getOrderNo());// 62
	dcc3.setCustom(trade.getOrderNo());   	   
VpnUtil  vu3=new VpnUtil();
 // dcc3.setResp_Code("0000");
dcc3=vu3.getDCCvalue(dcc3,"3");    	   
// ref=dcc3.getRef_No();
System.out.println("===============结果"+dcc3.getResp_Code());    	

    	 if(dcc3.getResp_Code().equals("0000")){
    			this.message="Payment Success!";	 
    		    this.responseCode=88;
				this.commonService
				.deleteBySql("update  international_tradeinfo t  set t.tradestate='6'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						+ message
						+ "',t.VIPTerminalNo='"
						+ list.get(0).getTerminalNo()
						+ "',t.VIPAuthorizationNo='"
						+ dcc3
								.getAuth_Code()
						+ "' ,t.VIPBatchNo='XXXXXX' ,t.ref_No='"+dcc3.getRef_No()+"' where t.id='"
						+ trade.getId() + "'");    			
    			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
    			md5Value = md5.getMD5ofStr(MD5info);	
    			return SUCCESS;	
    			 }
    			 else{
    					this.message="Payment Declined!"+dcc3.getResp_Code();	
    				    this.responseCode=Integer.valueOf(dcc3.getResp_Code());
     				    this.commonService.deleteBySql(" update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"', t.ref_No='"+dcc3.getRef_No()+"' where t.id="+trade.getId()+"");
    					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
    					md5Value = md5.getMD5ofStr(MD5info);		 
    					return SUCCESS;
    			 }   	
    
		}catch(Exception ex){
			return ERROR;
		}
	   	
    	
    	
    }

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
		DCCMessage dcc2 = new DCCMessage();
		
		dcc2.setTrans_Type("dccsales");// 类型
		dcc2.setTrans_Model("M");
		dcc2.setHashCode(tim.getHashcode());
		dcc2.setMerchant_Id(tim.getMerchantNo());// 42 商户编号
		dcc2.setAuthor_Str(tim.getAuthcode());
		dcc2.setTerminal_Id(trade.getVIPTerminalNo());// 41 商户终端号
		dcc2.setInvoice_No(trade.getOrderNo().substring(
				trade.getOrderNo().length() - 6,
				trade.getOrderNo().length()));// 11 交易流水号
		dcc2.setCurrency_Code_T("156");// 货币代码
		dcc2.setAmount_Loc(this.buzero(trade.getRmbAmount()+""));// 4 本地交易金额
		dcc2.setCard_No(AES.getCarNo(card.getCardNo()));// 账号2
		dcc2.setExp_Date(AES.getCarNo(card.getExpiryDate()).substring(
				2, 4)
				+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
		dcc2.setCSC(AES.getCarNo(card.getCvv2()));
		dcc2.setOrder_No(trade.getOrderNo());// 62
		dcc2.setCustom(trade.getOrderNo());
		dcc2.setCurrency_Code(this.currency_Code_Foreign);
		dcc2.setAmount_For(this.amount_Transaction_Foreign);
    VpnUtil  vu2=new VpnUtil();
        dcc2=vu2.getDCCvalue(dcc2,"2");    	 
    System.out.println("===============结果"+dcc2.getResp_Code());  
    	
	
 if(dcc2.getResp_Code().equals("0000")){
	this.message="Payment Success!";	 
    this.responseCode=88;
//    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id='"+trade.getId()+"'");
	this.commonService
	.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
			+ message
			+ "',"
			+ " t.VIPAuthorizationNo='"
			+ dcc2
					.getAuth_Code()
			+ "' ,t.VIPBatchNo='XXXXXX' ,t.refoNo='"+dcc2.getRef_No()+"' where t.id='"
			+ trade.getId() + "'");	
	MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	md5Value = md5.getMD5ofStr(MD5info);	
	return SUCCESS;
	 }
	 else{
		    this.responseCode=Integer.valueOf(dcc2.getResp_Code());//返回代码
			this.message="Payment Declined!"+this.responseCode;
		    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id="+trade.getId()+"");
			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
			md5Value = md5.getMD5ofStr(MD5info);	
			return SUCCESS;
	 }
   	 
    }
    if(this.tradeType.equals("YX")){
  //做非DCC交易
String ter = " from InternationalTerminalManager tm where tm.terminalNo='"+ tim.getAndterminalNo().trim() + "' ";
List<InternationalTerminalManager> list = this.commonService
		.list(ter);    	

DCCMessage dcc3 = new DCCMessage();
	dcc3.setTrans_Type("auth");// 类型
	dcc3.setTrans_Model("M");
	dcc3.setMerchant_Id(list.get(0).getMerchantNo());// 42 商户编号
	dcc3.setHashCode(list.get(0).getHashcode());
	dcc3.setAuthor_Str(list.get(0).getAuthcode());
	dcc3.setTerminal_Id(list.get(0)
		.getTerminalNo());// 41 商户终端号
	dcc3.setInvoice_No(trade.getOrderNo().substring(
		trade.getOrderNo().length() - 6,
		trade.getOrderNo().length()));// 11 交易流水号
	dcc3.setCurrency_Code_T("156");// 货币代码
	dcc3.setAmount_Loc(this.buzero(trade
		.getRmbAmount()
		+ ""));// 4 本地交易金额
	dcc3.setCard_No(AES.getCarNo(card.getCardNo()));// 账号2
	dcc3.setExp_Date(AES.getCarNo(card.getExpiryDate())
		.substring(2, 4)
		+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));// 14 有效期
	dcc3.setCSC(AES.getCarNo(card.getCvv2()));
	dcc3.setOrder_No(trade.getOrderNo());// 62
	dcc3.setCustom(trade.getOrderNo());   	   
VpnUtil  vu3=new VpnUtil();
dcc3=vu3.getDCCvalue(dcc3,"3");    	   
// ref=dcc3.getRef_No();
System.out.println("===============结果"+dcc3.getResp_Code());    	

    	 if(dcc3.getResp_Code().equals("0000")){
    			this.message="Payment Success!";	 
    		    this.responseCode=88;
				this.commonService
				.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
						+ message
						+ "',t.VIPTerminalNo='"
						+ list.get(0).getTerminalNo()
						+ "',t.VIPAuthorizationNo='"
						+ dcc3
								.getAuth_Code()
						+ "' ,t.VIPBatchNo='XXXXXX' ,t.refoNo='"+dcc3.getRef_No()+"' where t.id='"
						+ trade.getId() + "'");    			
    			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
    			md5Value = md5.getMD5ofStr(MD5info);	
    			return SUCCESS;	
    			 }
    			 else{
    					this.message="Payment Declined!"+dcc3.getResp_Code();	
    				    this.responseCode=Integer.valueOf(dcc3.getResp_Code());
     				    this.commonService.deleteBySql(" update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id="+trade.getId()+"");
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
	private Map createMapFromResponse(String queryString) {
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
}
