
package com.ecpss.action.pay.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;
import com.ecpss.action.pay.util.CheckCardNo;
import com.ecpss.action.pay.util.MaxMindExample;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.model.shop.InternationalTradecondition;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;

public class CarderInfoAction extends BaseAction {

	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	private String thesame="";
	private HashMap h = new HashMap();
	// 抛过来的持卡人信息
	private String rorderno = ""; // 商户流水号
	private Date birthday; // 生日日期
	private String year; // 年
	private String month; // 月
	private Long date; // 日
	private String firstname;
	private String lastname;
	private String country; // 国家
	private String state; // 州
	private String city; // 城市
	private String address; // 地址
	private String zipcode; // 邮编编号
	private String email;
	private String phone;
	private String cardbank;

	private String ReturnURL;
	private String MD5key;	
	// 卡信息
	private String cardnum; // 卡号
	private String cvv2;
	private String ip;
	private String cookie;
	private String cookieId;
  
	//定单
	private Double ordercount;   //订单金额
	
	//返回参数  
	private int responseCode;

	private String remark;      //备注	
	private String MD5info;   //MD5加密


	//md5 数据校验
	private String tradeMoneyType;
	private String merchantOrderNo;		
	private String md5Value;   //支付网关对商户信息进行加密		
	private String message;		
	
	// 2.5方支付参数
	private String vpc_Card;
	// maxmin参数
	private String maxmindValue;
	String bankName = null;
	String bankCountry = null;
	String bankPhone = null;
	private String values;
	private String SECURE_SECRET;
	private MaxMindExample exam = new MaxMindExample();
//shipping Address 信息
//	shippingAddress
	private String  shippingFirstName;
	private String  shippingLastName;
	private String  shippingEmail;
	private String  shippingPhone;
	private String  shippingZipcode;
	private String  shippingAddress;
	private String  shippingCity;
	private String  shippingSstate;	
	private String  shippingCountry;
	private String products;
	
	//非三D参数
	private String  virtualPaymentClientURL;//请求地址
	private String accessCode;//接入码

	private String merchantId;//商户ID
    private String vpc_CardExp;//卡有效期
	private int tradeOrder;//交易金额
	private String connectURL;//返回地址
    private String	vpc_CardNum;//卡号

	
    //dcc 参数
    private String  amount_Transaction_Foreign;
    private String  conversion_Rate;//汇率
    private String currency_Code_Foreign;
	private String tradeType;   //交易类型 
	private String local_money;//本地
	private String foreign_money;//外币
	private String conversion_Rate_show;
    private	String  car_termanal;//返回终端	
    private String cardMessage;
	public String getCardMessage() {
		return cardMessage;
	}

	public void setCardMessage(String cardMessage) {
		this.cardMessage = cardMessage;
	}

	/**
	 * 添加持卡人信息
	 * input vip   success 非三D  
	 * @return添加流程 卡号，商户号确定通道，风控，mixmad， 通道抛人民币处理。
	 */
	public String addCardMessage() {
		try{
			if(this.thesame.equals("1")){
				this.firstname=this.shippingFirstName;
				this.lastname=this.shippingLastName;
				this.country=this.shippingCountry ;// 国家
				this.state=this.shippingSstate; // 州
				this.city=this.shippingCity; // 城市
				this.address=this.shippingAddress; // 地址
				this.zipcode=this.shippingZipcode; // 邮编编号
				this.email=this.shippingEmail;
				this.phone=this.shippingPhone;
			}
			
			MD5 md5 = new MD5();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
	       //输入值验证
			if(checkAll()){
				this.responseCode=25;
				message = "Payment Declined";
				return "cardholderError";
			}
			String expirydate = month + year;
			// 交易
			InternationalTradeinfo trade = new InternationalTradeinfo();
			String hql = "from InternationalTradeinfo t where t.orderNo='"
					+ rorderno + "'";
	
			List<InternationalTradeinfo> tradl = this.commonService.list(hql);
			// ECPSS流水号不唯一
			if (tradl==null) {
				this.responseCode=0;		
		        return ERROR;
			}else if(tradl.size()!=1){
				return ERROR;	
			}
			trade = tradl.get(0);
			
			//转换成标准金额
			ordercount = trade.getTradeAmount();
	
			DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
			String ordercountValue = decimalFormat.format(ordercount);		
			
			tradeMoneyType=trade.getMoneyType()+"";
			merchantOrderNo=trade.getMerchantOrderNo()+"";
			
			// InternationalMerCreditCard
	        this.ReturnURL=trade.getReturnUrl();
			InternationalMerchant merchant = (InternationalMerchant) this.commonService
					.load(InternationalMerchant.class, trade.getMerchantId());
			// 校验是否商户号对上交易
			if (merchant == null) {
				return ERROR;	
			}
	
			// 获取ip
			ip = getIpAddr(request);
			GetBatchNo ut = new GetBatchNo();
			if (cookieId == null || cookieId.length() == 5) {
				cookie = "ecpss.com" + ut.getCookies();
				Cookie cook = new Cookie("ids", cookie);
				// 生命周期
				cook.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(cook);
			}
			/**
			 * 读cookie
			 */
			// cookie = (String)session.getAttribute("id");
			Cookie[] cookies = request.getCookies();
	
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (c.getName().equalsIgnoreCase("ids")) {
						cookie = c.getValue();
					}
				}
			}
			
			
			
	
			/** ******************风险控制******************** */
			// 获取信用卡前6位号码
			String backCardnum6 = (new StringBuilder(cardnum)).delete(6,
					cardnum.length()).toString();
			String backCardnum9 = (new StringBuilder(cardnum)).delete(9,
					cardnum.length()).toString();
	
			// 判断卡类型
			int cartype=0;
			if (cardnum != null) {
				if (cardnum.matches("[4]\\d+")) {
					cartype=4;
					vpc_Card = "Visa";
				}
				if (cardnum.matches("[5]\\d+")) {
					cartype=5;
					vpc_Card = "Mastercard";
				}
			}
           if(cartype==5){
	        	 this.cardMessage="I declare that I have been offered a choice of payment currencies and my choice is final.";
           }			
           else{
	        	 this.cardMessage="I declare that I have been offered a choice of payment currencies and my choice is final.I understand that the currency conversion is not provided by Visa.";
       	   
           }
			
			// 根据商户号,卡种信息获取通道		
			String sql = "select a.channelname , d.id ,b.id as tid from international_channels a ,international_creditcardtype b,international_mercreditcard c ,international_merchantchannels d where a.id=d.channelid and d.merchantid='"+merchant.getId()+"' and b.shortname='"
					+ cartype
					+ "' and d.id=c.merchannelid and b.id=c.creditcardid and d.onoff='1' and c.onoff='1'";
			List chanellist = this.commonService.getByList(sql);
			// 商户通道ID
			String merchanID = "";
			// 通道名称
			String chanelName = "";
			//卡种ID
			Long carType=0l;
			if (chanellist.size() > 0||chanellist.size()==1) {
				Object[] tem = (Object[]) chanellist.get(0);
				merchanID = tem[1].toString();
				chanelName = tem[0].toString();
				carType=Long.valueOf(tem[2].toString());
			} else {
				message = "Payment Declined";
				remark = "通道未设置";
				responseCode = 16;
	
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	
				md5Value = md5.getMD5ofStr(MD5info);  	
				return this.INPUT;
				}
			// 商户通道
			InternationalMerchantChannels im = (InternationalMerchantChannels) this.commonService
					.load(InternationalMerchantChannels.class, Long.valueOf(merchanID));
			InternationalChannels ic=(InternationalChannels)this.commonService.load(InternationalChannels.class,im.getChannelId()); 
	//		String cardTypeNum = chanelName.substring(0, 3).toString();
			String cardTypeNum = chanelName.substring(0, 3).toString();
			// 根据商户号,卡种信息获取通道
			System.out.println(cartype + "开头的卡走的通道是:"+cardTypeNum);
			System.out.println(trade.getId());
			String carlist="select count(*) from international_cardholdersinfo t where t.tradeid='"+trade.getId()+"'";
			int carInfoList =0;
			carInfoList=this.tradeManager.intBySql(carlist);
			if(carInfoList>0){
				message = "Payment Declined";
				remark = "同一笔交易重复支付";
				responseCode = 31;
	
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  	
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				
				return this.INPUT;				
			}
//			this.commonService.deleteBySql("delete  from international_cardholdersinfo t where t.tradeid='"+trade.getId()+"'");
			InternationalCardholdersInfo card = new InternationalCardholdersInfo();
			card.setTradeId(trade.getId());
		// card.setTradetime(trade.getTradeTime());
			card.setIp(ip);
			card.setCookie(cookie);
			// card.setRiskvalue(maxmind);
			card.setCvv2(cvv2);
			card.setCardNo(cardnum);
			card.setFirstName(firstname);
			card.setLastName(lastname);
			// card.setUserName(firstname+" "+lastname);
			card.setEmail(email);
			card.setPhone(phone);
			 card.setExpiryDate(expirydate);
			// card.setResulturl(ReturnURL);
			card.setCountry(country);
			card.setState(state);
			card.setCity(city);
			card.setAddress(address);
			card.setZipcode(zipcode);
	//		 card(cardbank);
	  		card.setShippingAddress(this.getShippingAddress());
	  		card.setShippingCity(this.getShippingCity());
	  		card.setShippingCountry(this.getShippingCountry());
	  		card.setShippingEmail(this.getShippingEmail());
	  		card.setShippingFullName(this.getShippingFirstName()+" "+this.getShippingLastName());
	  		card.setShippingPhone(this.getShippingPhone());
	  		card.setShippingState(this.getState());
	  		card.setShippingZip(this.getShippingZipcode());
	  		card.setProductInfo(this.getProducts());
			this.commonService.save(card);	
			this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradechannel='"+merchanID+"' where t.id='"+trade.getId()+"'");
			// 黑卡,高风险卡宾
			
			
//			if (backCardValue6 > 0 || backCardValue9> 0) {
//				message = "Payment Declined";
//				remark = "黑卡bin";
//				responseCode = 3;
//	
//				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
//	
//				md5Value = md5.getMD5ofStr(MD5info);  		
//				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"';");
//				return INPUT;
//			}
	        //黑卡，风险卡
			int backCardValue=this.tradeManager.intBySql("select count(*) from Internationalbacklist t where t.cardno='"+cardnum+"' ");
			int riskCardValue=this.tradeManager.intBySql("select count(*) from InternationalRisklist t where t.cardno='"+cardnum+"' ");
			
			
			
			
			// 根据ip或cardNumber或Email，COCKET 信息查询持卡人24小时内交易的次数(成功的，待处理，待确认的交易)
			int ipcount=0;
			ipcount=this.tradeManager.intBySql("select count(*) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.ip='"+ip+"' and t.merchantid='"+merchant.getId()+"' and substr(t.tradestate,1,1) in(1,2,4) and  ROUND(TO_NUMBER(sysdate - t.tradetime)*24)<24");
            int telcout=0;
            telcout=this.tradeManager.intBySql("select count(*) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.phone='"+this.phone+"' and t.merchantid='"+merchant.getId()+"' and substr(t.tradestate,1,1) in(1,2,4) and  ROUND(TO_NUMBER(sysdate - t.tradetime)*24)<24");
			int emailcount=0;
			emailcount=this.tradeManager.intBySql("select count(*) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.email='"+this.email+"' and t.merchantid='"+merchant.getId()+"' and substr(t.tradestate,1,1) in(1,2,4) and  ROUND(TO_NUMBER(sysdate - t.tradetime)*24)<24");
			int cocketcount=0;
			cocketcount=this.tradeManager.intBySql("select count(*) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.cookie='"+this.cookie+"' and t.merchantid='"+merchant.getId()+"' and substr(t.tradestate,1,1) in(1,2,4) and  ROUND(TO_NUMBER(sysdate - t.tradetime)*24)<24");
			int carno=0;
			carno=this.tradeManager.intBySql("select count(*) from international_cardholdersinfo f, international_tradeinfo t where f.tradeid=t.id and f.cardno='"+this.cardnum+"' and t.merchantid='"+merchant.getId()+"' and substr(t.tradestate,1,1) in(1,2,4) and  ROUND(TO_NUMBER(sysdate - t.tradetime)*24)<24");		
			//获取交易条件设置信息
			List<InternationalTradecondition> localrisk;
			localrisk=this.commonService.list("from  InternationalTradecondition f where f.merchantId='"+merchant.getId()+"'");
			if(localrisk.size()==0){
				localrisk=this.commonService.list("from  InternationalTradecondition f where f.merchantId is null");
			}
			Long localIP=0l;
			Long localEMAIL=0l;
			Long localCOCKET=0l;
			Long localCarNO=0l;
			Long localPhone=0l;
			for(int i=0;i<localrisk.size();i++){
				InternationalTradecondition tem=localrisk.get(i)	;
				if("1".equals(tem.getItemno()+"")){
					localIP=tem.getTradenumber();
				}
				if("2".equals(tem.getItemno()+"")){
					localEMAIL=tem.getTradenumber();
				}
				if("3".equals(tem.getItemno()+"")){
					localCarNO=tem.getTradenumber();
				}
				if("4".equals(tem.getItemno()+"")){
					localPhone=tem.getTradenumber();
				}					
				if("5".equals(tem.getItemno()+"")){
					localCOCKET=tem.getTradenumber();
				}			
				
			}
			//获取商户管理值
			List<InternationalMerchantManager> merchantmanagers=new ArrayList();
			merchantmanagers=this.commonService.list("from  InternationalMerchantManager f where f.merchantId='"+merchant.getId()+"'");
			if(merchantmanagers.size()==0){
				merchantmanagers=this.commonService.list("from  InternationalMerchantManager f where f.merchantId is null ");
			}
			InternationalMerchantManager merchantmanager=merchantmanagers.get(0);
			//获取月交易金额
			Double monthTradeMoney = 0d;
			monthTradeMoney =this.tradeManager.getAcount(merchant.getId());
			//获取POS
	        List<InternationalTerminalManager>  it=this.commonService.list("from InternationalTerminalManager t where t.channelId='"+im.getChannelId()+"' and t.creditCardId='"+carType+"' and t.onoff='1' and t.isuses='0'");
	        if(it.size()<=0){
	        	
	        this.commonService.deleteBySql("update international_terminalmanager t set t.isuses='0' where  t.id in (select tf.id  from International_TerminalManager tf where tf.channelId='"+im.getChannelId()+"' and tf.creditCardId='"+carType+"')");	
	            it=this.commonService.list("from InternationalTerminalManager t where t.channelId='"+im.getChannelId()+"' and t.creditCardId='"+carType+"' and t.onoff='1' and t.isuses='0'");
	            if(it.size()<=0){
					message = "Payment Declined";
					remark = "通道未分配终端";
					responseCode = 27;
		
					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
		
					md5Value = md5.getMD5ofStr(MD5info);  		
					this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
					return INPUT;	            	
	            }
	        }
	        String posNumber="";
	        String posMerchantNo="";
	        if(it.size()>0){
	    	     posNumber=it.get(0).getTerminalNo(); 
	    	     posMerchantNo=it.get(0).getMerchantNo();
	       }
	       System.out.println("pos=================="+posNumber);
	       this.commonService.deleteBySql("update international_terminalmanager t set t.isuses='1' where  t.id='"+it.get(0).getId()+"'");
	       //更新终端
	       this.commonService.deleteBySql("update  international_tradeinfo t  set t.VIPTerminalNo='"+posNumber+"' where t.id='"+trade.getId()+"'");
	       
			//获取交易金额
			 //单笔交易默认不能大于600元
	
			int backCardValue6 = this.tradeManager.intBySql("select count(*) from Internationalbacklist t where t.cardno='"+backCardnum6+"' ");
			int backCardValue9 = this.tradeManager.intBySql("select count(*) from Internationalbacklist t where t.cardno='"+backCardnum9+"' ");
			String chnals = chanelName.split("-")[0];	
			//输入值验证
			if(checkAll()){
				this.responseCode=25;
				remark = "信息有误";
				System.out.println("返回状态码+++++++++"+responseCode);
				message = "Payment Declined";
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
			}else if(trade.getTradeAmount() >merchantmanager.getPenQuota() ){
				message = "Payment Declined";
				remark = "单笔超限";
				responseCode = 3;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
			}
			//黑卡库
			else if(backCardValue>0){
				message = "Payment Declined";
				remark = "黑卡";
				responseCode = 2;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  	
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
	
				System.out.println("responseCode--------------"+responseCode);
				return INPUT;
			}
			//黑卡bean
			else if(backCardValue6 >0 || backCardValue9>0){
				message = "Payment Declined";
				remark = "黑卡";
				responseCode = 17;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
				//response.sendRedirect(returnurl);
			}
			
			//同一ip
		
			else if(Long.valueOf(ipcount) >= localIP){
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 5;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
			}
			//同一邮箱
		    else if(Long.valueOf(emailcount) >= localEMAIL){
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 6;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
			}
			
			//同一卡号
		    else if(Long.valueOf(carno) >= localCarNO){
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 7;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
			}
			//同一电话
		    else if(Long.valueOf(telcout) >= localPhone){
				message = "Payment Declined";
				remark = "重复交易";
				responseCode = 30;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
			}			
			//同一cookies
		
		    else if(Long.valueOf(cocketcount) >= localCOCKET){
		    	message = "Payment Declined";
		    	remark = "重复交易";
				responseCode = 8;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				return INPUT;
		    }
			 //当商户交易的金额已超过商户交易条件设置金额则不能够再进行交易，并且已邮件的方式通知商户
			
			else if(monthTradeMoney+ordercount > merchantmanager.getMonthQuota()){
	//			//先把需要发送邮件的信息保存到数据库
	//			saveMailInfo(merEmail,num1,"ecpss@ecpss.com");
				message = "Payment Declined";
				remark = "月交易量超限";
				responseCode = 4;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				if(chnals.equals("VIP")){
					this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
					}else{
						this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
						
					}
				//超过限额给商户发送邮件提醒
				shopManagerService.addSendMessages(merchant.getMeremail(), "ecpss@ecpss.cc", merchant.getMerno()+" "+EmailInfo.getMoreMoney(), "0");
				return INPUT;
	
			}
		
			//高风险卡   VIP为待确认 ，其他的为失败
			else if(riskCardValue>0){
				message = "Your Payment is being Processed! " ;
				remark = "高风险卡";
				responseCode = 1;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);
				if(chnals.equals("VIP")){
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
				}else{
					this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
					
				}
				return INPUT;
				
	
			}		
			//当商户交易的金额达到商户交易条件设置金额的85%则以邮件的方式通知商户
			if(monthTradeMoney+ordercount > merchantmanager.getMonthQuota() * 0.85){
				//发送邮件
				shopManagerService.addSendMessages(merchant.getMeremail(), "ecpss@ecpss.cc", merchant.getMerno()+" "+EmailInfo.getMoreMoneyPart(monthTradeMoney+ordercount), "0");
	//			this.saveMailInfo(merEmail, num2, "ecpss@ecpss.com");
			}
			// 与商户的风控制对比，然后进行对比
	
			// 连接maxmind系统
	    
			 HashMap hm = new HashMap();
			 // 加密串 license_key : UxQh0mA4aLqw 调试和正式运行时要加上,才会返回分数
			 // 上海key: CxsRZ1xPPRbR;
			 // 广州key: UxQh0mA4aLqw
			 hm.put("license_key", "CxsRZ1xPPRbR");
			 hm.put("i", ip);
			 hm.put("domain", email);
			 hm.put("custPhone", phone);
			 hm.put("country", country);
			 hm.put("city", city);
			 hm.put("region", state);
			 hm.put("shipAddr", address);
			 hm.put("postal", zipcode.toString());
			 hm.put("bin", cardnum);
			 hm.put("binName", cardbank);
			
			 // standard 低级
			 // premium 高级
			 // 正式运行的时候要用这个 premium ; standard为调试用的标准
			 hm.put("requested_type", "standard");
	      try{		
//			 Hashtable ht = getmmValue(hm);
//			 maxmindValue = (String) ht.get("values");
//			 bankName = (String) ht.get("bankName");
//			 bankCountry = (String) ht.get("bankCountry");
//			 bankPhone = (String) ht.get("bankPhone");
			 // System.out.println("maxmindValue--------------"+maxmindValue);
			 // System.out.println("riskValue--------------"+riskValue);
	      }catch(Exception ex){
				 Double maxmind = 2d;
					
				 if(maxmind>merchantmanager.getMarkValue()){

					 if(chnals.equals("VIP")){
						this.commonService.deleteBySql("update  international_tradeinfo t  set t.maxmindValue='"+maxmind+"', t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
						message = "Your Payment is being Processed! " ;
						remark = "风险值过大！";
						responseCode = 9;
						System.out.println("返回状态码+++++++++"+responseCode);
						MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
						md5Value = md5.getMD5ofStr(MD5info);
						}else{
							message = "Your Payment is being Processed! " ;
							remark = "风险值过大！";
							responseCode = 9;
							System.out.println("返回状态码+++++++++"+responseCode);
							MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
							md5Value = md5.getMD5ofStr(MD5info);							
							this.commonService.deleteBySql("update  international_tradeinfo t  set t.maxmindValue='"+maxmind+"', t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
							
						}
						return INPUT;
					}		
	      }
	      
			 Double maxmind = 0d;
			 // 返回分值
			 if (maxmindValue != null && maxmindValue != "") {
			 maxmind = Double.valueOf(maxmindValue);
			 }
				//高风险卡   VIP为待确认 ，其他的为失败
			 if(maxmind>merchantmanager.getMarkValue()){
					message = "Your Payment is being Processed! " ;
					remark = "风险值过大！";
					responseCode = 9;
					System.out.println("返回状态码+++++++++"+responseCode);
					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
					md5Value = md5.getMD5ofStr(MD5info);
					if(chnals.equals("VIP")){
					this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='4'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
					}else{
						this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
						
					}
					return INPUT;
					
		
				}		
	         this.commonService.deleteBySql(" update international_cardholdersinfo t set t.maxmindValue='"+maxmind+"',t.bankcountry='"+bankCountry+"',t.bankname='"+bankName+"',t.bankphone='"+bankPhone+"' where t.id='"+card.getId()+"' ");
//	         String queryarea = "select m.id from MerchantRiskControl m where m.merchantId="+merchant.getId()+" and substr(m.area,1,2) like '"+bankCountry+"'";
//		      System.out.println(queryarea);
//		      List queryarealist = this.commonService.list(queryarea);
//		      System.out.println(queryarealist.size());
//		      if(queryarealist.size()>0){
//		    	  	message = "Payment Declined";
//			    	remark = "禁止交易地区";
//					responseCode = 31;
//					System.out.println("返回状态码+++++++++"+responseCode);
//					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
//					md5Value = md5.getMD5ofStr(MD5info);  		
//					this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+remark+"' where t.id='"+trade.getId()+"'");
//					return INPUT;
//		      }
	         if (chnals.equals("EVIP")) {
	        		//交易查询	
	           	 DCCMessage dcc=new DCCMessage();
	        	 dcc.setMessageType("0800");//类型
	        	 dcc.setPrimary_Account_Number(card.getCardNo());//账号2
	        	 dcc.setProcessing_Code("970000");//处理代码3
	        	 dcc.setAmount_Transaction_Local(this.buzero(trade.getRmbAmount()+""));//4 本地交易金额
	        	 dcc.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//11  交易流水号
	        	 dcc.setDATE_EXPIRATION(card.getExpiryDate().substring(2, 4)
							+ card.getExpiryDate().substring(0, 2));//14   有效期
	        	 dcc.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
	        	 dcc.setNETWORK_INTL_IDENTIFIER("0098");//24  收单商户号
	        	 dcc.setPOS_CONDITION_CODE("00");//25 商户编码
	        	 dcc.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);//41  商户终端号
	        	 dcc.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);//42 商户编号 
	        	 dcc.setInvoice_Number(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//62	
	        	 DccUtil dc=new DccUtil();
	        	 dc.setDccMessage(dcc);
	        	 try{
	        	 dcc=dc.getDccMessage();
	        	 }catch(Exception ex){
		        	 //传递的参数     
	        		this.responseCode=19;
	 				message = "Your payment is being processed";
	 				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	 				md5Value = md5.getMD5ofStr(MD5info);  		
	 				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"+trade.getId()+"'");
	 		        return "paybydcc";     		 
	        	 }
	        	 if(dcc.getRESPONSE_CODE().equals("YX")){
	             	 DCCMessage dcc3=new DCCMessage();
	            	 dcc3.setMessageType("0200");//类型
	            	 dcc3.setPrimary_Account_Number(card.getCardNo());//账号2
	            	 dcc3.setProcessing_Code("000000");//处理代码3
	            	 dcc3.setAmount_Transaction_Local(this.buzero(trade.getRmbAmount()+""));//4 本地交易金额
	            	 dcc3.setSYSTEMS_TRACE_AUDIT_NUMBER(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//11  交易流水号
	            	 dcc3.setDATE_EXPIRATION(card.getExpiryDate().substring(2, 4)
	 						+ card.getExpiryDate().substring(0, 2));//14   有效期
	            	 dcc3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");//22 POS进入模式
	            	 dcc3.setNETWORK_INTL_IDENTIFIER("0017");//24  收单商户号
	                 dcc3.setCARD_ACCEPTOR_TERMINAL_ID("00000000");//41  商户终端号
	            	 dcc3.setCARD_ACCEPTOR_ID_CODE("021207999000000");//42 商户编号 
	            	 dcc3.setCVV2_OR_CVC2(card.getCvv2());//cv2 61
	            	 dcc3.setInvoice_Number(trade.getOrderNo().substring(trade.getOrderNo().length()-6,trade.getOrderNo().length()));//62	
	            	 DccUtil dc2=new DccUtil();
	            	 dc2.setDccMessage(dcc3);
	            	 try{
	            	 dcc3=dc2.getDccMessage();  
	            	 }
	            	 catch(Exception ex){
	                	 //传递的参数    
	         		    responseCode=19;
	         			message = "Your payment is being processed";
	         			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	         			md5Value = md5.getMD5ofStr(MD5info);  		
	         			this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"+trade.getId()+"'");
	         	        return INPUT;    		 
	            	 }
	            	 if(dcc3.getRESPONSE_CODE().equals("00")){
	            			this.message="Payment Success!";	 
	            		    this.responseCode=88;
	            		    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id='"+trade.getId()+"'");
	            			
	            			MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	            			md5Value = md5.getMD5ofStr(MD5info);	
	            			return INPUT;	
	            			 }
	            			 else{
	            					this.message="Payment Declined!";	
	            				    this.responseCode=Integer.valueOf(dcc3.getRESPONSE_CODE());
	             				    this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"+message+"' where t.id='"+trade.getId()+"'");
	            					MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
	            					md5Value = md5.getMD5ofStr(MD5info);		 
	            					return INPUT;
	            			 }   	
	            	         	    	         	    
	        	 }
//	        	 //传递的参数
        	    this.amount_Transaction_Foreign=dcc.getAmount_Transaction_Foreign();
//	        	 this.amount_Transaction_Foreign="000000000001";
        	    this.conversion_Rate=dcc.getConversion_Rate();//汇率
//	        	 this.conversion_Rate="74512544";
        	    this.currency_Code_Foreign=dcc.getCurrency_Code_Foreign();//国家代码
//	        	 this.currency_Code_Foreign="098";
	        	
        	    String curen="";
        	    int index=Integer.valueOf(dcc.getRESERVED_PRIVATE().substring(dcc.getRESERVED_PRIVATE().length()-1, dcc.getRESERVED_PRIVATE().length()));
        	    if(index==0){
        	  	  curen=dcc.getAmount_Transaction_Foreign();
        	    }else{        	
            	    if(this.amount_Transaction_Foreign.length()==12){
              	      curen=amount_Transaction_Foreign.substring(0,12-index)+"." +amount_Transaction_Foreign.substring(12-index, 12);
              	    }        	    
        	    
        	    }
        	    this.tradeType=dcc.getRESPONSE_CODE();   //交易类型 	  
//        	    this.tradeType="YY";
        		Country coy=new Country();
        		coy.setCode("142");
        	  this.local_money=coy.getName()+"  "+trade.getRmbAmount();// 本币交易金额
        	  coy.setCode(currency_Code_Foreign);//外币
        	  this.foreign_money=dcc.getRESERVED_PRIVATE().substring(0, dcc.getRESERVED_PRIVATE().length()-1)+"   "+Double.valueOf(curen);
              this.conversion_Rate_show=this.getRate(conversion_Rate);
        	 this.car_termanal=dcc.getCARD_ACCEPTOR_TERMINAL_ID();
//              this.car_termanal="22222222";
        	 
				message = "Your payment is being processed";
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.VIPTerminalNo='"+this.car_termanal+"' where t.id='"+trade.getId()+"'");
	
				return "paybydcc";        
			} else if (chnals.equals("VIP")) {
				//remark = "VIP待处理";
				message = "Your payment is being processed";
				responseCode = 19;
				System.out.println("返回状态码+++++++++"+responseCode);
				MD5info = trade.getMerchantOrderNo() + trade.getMoneyType() + ordercountValue + responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);  		
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"+trade.getId()+"'");
	
				return INPUT;
	
			} else if (chnals.equals("VC")) {
				//非3D通道
				this.virtualPaymentClientURL=ic.getBankUrl();//请求地址
				this.accessCode=ic.getAccessCode();//接入码

				this.merchantId=ic.getBankMerchantId();//商户ID
				this.vpc_CardExp= year+month;
				this.tradeOrder=(int) (trade.getRmbAmount()*100);//交易金额
				this.connectURL="https://security.sslepay.com/Pays/payResponseAction";//返回地址
			    this.vpc_CardNum=cardnum;//卡号				
			    this.SECURE_SECRET = ic.getMd5();
				this.commonService.deleteBySql("update  international_tradeinfo t  set t.tradestate='5'||substr(t.tradestate,2,(length(t.tradestate)-1)) where t.id='"+trade.getId()+"'");
			    
				System.out.println("返回状态码+++++++++"+responseCode);
	            return SUCCESS;	
			} else if (chnals.equals("MC")) {
				//非3D通道
				this.virtualPaymentClientURL=ic.getBankUrl();//请求地址
				this.accessCode=ic.getAccessCode();//接入码

				this.merchantId=ic.getBankMerchantId();//商户ID
				this.vpc_CardExp=card.getExpiryDate();
				this.tradeOrder=(int) (trade.getRmbAmount()*100);//交易金额
				this.connectURL="http://192.168.1.23:8888/pay/Pays/payResponseAction";//返回地址
			    this.vpc_CardNum=cardnum;//卡号				
				
	            return SUCCESS;	
			}
	
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
			
		}
	}

	public boolean checkAll(){
		boolean check=false;
		//验证邮箱
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(this.getEmail());
        //Matcher m1 = p.matcher(this.getShippingEmail());
        if(!m.find()){
        	check = true;
        }
        //else if(!m1.find()){
        //	check = true;
       // }
        else if(this.getCvv2().length()!=3){
        	check = true;
        }else if (!StringUtils.isNotBlank(this.getMonth())){
        	check = true;
        }else if (!StringUtils.isNotBlank(this.getYear())){
        	check = true;
        }else if (!CheckCardNo.isValid(this.getCardnum())){
        	check = true;
        }
		return check;
	}
	
	/**
	 * ip
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// System.out.println("ip----------------"+ip);
		return ip;

	}

	public Hashtable getmmValue(HashMap hm) {

		h = exam.maxMindScore(hm);
		// 把MaxMind返回的参数打印出来,
		for (Iterator i = h.keySet().iterator(); i.hasNext();) {
			String key = (String) i.next();
			maxmindValue = (String) h.get(key);
			if (key.equals("score")) {
				values = maxmindValue;

			}
			if (key.equals("binName")) {
				bankName = maxmindValue;
			}
			if (key.equals("binCountry")) {
				bankCountry = maxmindValue;
			}
			if (key.equals("binPhone")) {
				bankPhone = maxmindValue;
			}
		}
		Hashtable ht = new Hashtable();
		ht.put("values", values);
		ht.put("bankName", bankName);
		ht.put("bankCountry", bankCountry);
		ht.put("bankPhone", bankPhone);
		return ht;
	}

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardbank() {
		return cardbank;
	}

	public void setCardbank(String cardbank) {
		this.cardbank = cardbank;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCookieId() {
		return cookieId;
	}

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public String getVpc_Card() {
		return vpc_Card;
	}

	public void setVpc_Card(String vpc_Card) {
		this.vpc_Card = vpc_Card;
	}

	public String getMaxmindValue() {
		return maxmindValue;
	}

	public void setMaxmindValue(String maxmindValue) {
		this.maxmindValue = maxmindValue;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCountry() {
		return bankCountry;
	}

	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}

	public String getBankPhone() {
		return bankPhone;
	}

	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public MaxMindExample getExam() {
		return exam;
	}

	public void setExam(MaxMindExample exam) {
		this.exam = exam;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getMD5key() {
		return MD5key;
	}

	public void setMD5key(String md5key) {
		MD5key = md5key;
	}

	public Double getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(Double ordercount) {
		this.ordercount = ordercount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMD5info() {
		return MD5info;
	}

	public void setMD5info(String md5info) {
		MD5info = md5info;
	}

	public String getTradeMoneyType() {
		return tradeMoneyType;
	}

	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShippingEmail() {
		return shippingEmail;
	}

	public void setShippingEmail(String shippingEmail) {
		this.shippingEmail = shippingEmail;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public String getShippingZipcode() {
		return shippingZipcode;
	}

	public void setShippingZipcode(String shippingZipcode) {
		this.shippingZipcode = shippingZipcode;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingSstate() {
		return shippingSstate;
	}

	public void setShippingSstate(String shippingSstate) {
		this.shippingSstate = shippingSstate;
	}

	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public String getVpc_CardExp() {
		return vpc_CardExp;
	}

	public void setVpc_CardExp(String vpc_CardExp) {
		this.vpc_CardExp = vpc_CardExp;
	}

	public String getShippingFirstName() {
		return shippingFirstName;
	}

	public void setShippingFirstName(String shippingFirstName) {
		this.shippingFirstName = shippingFirstName;
	}

	public String getShippingLastName() {
		return shippingLastName;
	}

	public void setShippingLastName(String shippingLastName) {
		this.shippingLastName = shippingLastName;
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

	public TradeManager getTradeManager() {
		return tradeManager;
	}

	public void setTradeManager(TradeManager tradeManager) {
		this.tradeManager = tradeManager;
	}

	public String getVirtualPaymentClientURL() {
		return virtualPaymentClientURL;
	}

	public void setVirtualPaymentClientURL(String virtualPaymentClientURL) {
		this.virtualPaymentClientURL = virtualPaymentClientURL;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}



	public int getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(int tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	public String getConnectURL() {
		return connectURL;
	}

	public void setConnectURL(String connectURL) {
		this.connectURL = connectURL;
	}

	public String getVpc_CardNum() {
		return vpc_CardNum;
	}

	public void setVpc_CardNum(String vpc_CardNum) {
		this.vpc_CardNum = vpc_CardNum;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String buzero(String refundRMBMoney){
		String refundRMB = "000000000000";
		String zero_12 = "000000000000";
		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		if(StringUtils.isNotBlank(refundRMBMoney) && refundRMBMoney.replace(".", "").matches("\\d+")){		
				String refundRMBStr = Double.valueOf((decimalFormat.format(Double.valueOf(refundRMBMoney))))*100+"";
				String refundRMB_0 = zero_12 + refundRMBStr.substring(0, refundRMBStr.indexOf("."));
				refundRMB = refundRMB_0.substring(refundRMB_0.length()-12, refundRMB_0.length());
		}
		return refundRMB;
	}
		

	public String getThesame() {
		return thesame;
	}

	public void setThesame(String thesame) {
		this.thesame = thesame;
	}

	public String getSECURE_SECRET() {
		return SECURE_SECRET;
	}

	public void setSECURE_SECRET(String secure_secret) {
		SECURE_SECRET = secure_secret;
	}

	public String getLocal_money() {
		return local_money;
	}

	public void setLocal_money(String local_money) {
		this.local_money = local_money;
	}

	public String getForeign_money() {
		return foreign_money;
	}

	public void setForeign_money(String foreign_money) {
		this.foreign_money = foreign_money;
	}
	public String getRate(String rate){
		String tem="";
		String index=rate.substring(0, 1);
		if(Integer.valueOf(index)==7){
			tem="0."+rate.substring(1);
		}else{
			tem=rate.substring(1, 7-Integer.valueOf(index)+1)+"."+rate.substring(7-Integer.valueOf(index)+1, 8);
		}
		return tem;
		
	}

	public String getConversion_Rate_show() {
		return conversion_Rate_show;
	}

	public void setConversion_Rate_show(String conversion_Rate_show) {
		this.conversion_Rate_show = conversion_Rate_show;
	}

	public String getCar_termanal() {
		return car_termanal;
	}

	public void setCar_termanal(String car_termanal) {
		this.car_termanal = car_termanal;
	}
}
