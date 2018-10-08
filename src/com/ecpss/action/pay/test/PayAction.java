package com.ecpss.action.pay.test;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.model.shop.InternationalPayVersion;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.MD5;

public class PayAction extends BaseAction {
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	// 抛过来的商户，订单，信息
	private String MerNo; // 商户ID
	private String rorderno; // 交易流水号
	private Date tradetime; // 交易时间
	private Long merchantnoValue;
	private boolean checkMerch;// 交易商户是否存在
	private long isDredge;// 检验商户通道是否开通
	private String Amount; // 支付金额

	private Double tradeMoney;// 支付金额
	private double rmbmoney = 0; // 人民币交易金额

	private Double tradeAmount; // 交易金额（外币）

	private String channels; // 使用的商户通道F
	private String BillNo; // 订单编号

	private String Currency; // 币种
	private String currencyName;
	private String Language = "en"; // 支付语言
	private String ReturnURL; // 返回地址
	private String md5src; // 网关加密md5
	private String cookie; // cookies
	private String cooknumber;
	private String MD5info;   //MD5加密
	// 程序处理需要的参数
	private int responseCode;//响应代号；
	private String MD5key; //MD5key值
	private String tradeAdd;
	private Long moneyType;
	private String message;  
	
	//md5 数据校验
	private String tradeMoneyType;
	private String ordercount; // 支付金额(转换为支付金额)
	private String md5Value;   //支付网关对商户信息进行加密
	private String merchantOrderNo;	

	private String resultMd5;
	// Q 支付请求.校验顺序：1.交易商户是否存在 ，2.检验商户通道是否开通 ;3.返回网址必须注册 4.信息是否被篡改。
	
	
//持卡人信息带过来   billingAddress
	private String  firstname;
	private String  lastname;
	private String  cardbank;
	private String  email;
	private String  phone;
	private String  zipcode;
	private String  address;
	private String  city;
	private String  state;
	private String  country;
	private String products;	
//	shippingAddress
    private String shippingFirstName;
    private String shippingLastName;
	private String  shippingEmail;
	private String  shippingPhone;
	private String  shippingZipcode;
	private String  shippingAddress;
	private String  shippingCity;
	private String  shippingSstate;	
	private String  shippingCountry;
	public String payRequest() {
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			//HttpSession session = request.getSession();
			System.out.println("returnurl---------------"+this.getReturnURL());
			if (MerNo == null) {
				return ERROR;
			}
			MD5 md5 = new MD5();
			if (StringUtils.isNotBlank(Amount)) {
				ordercount = Amount.replace(",", "");
				ordercount = ordercount.replace(" 0", "");
				tradeMoney = Double.valueOf(ordercount);			
			}else{
				responseCode = 26;
	        	resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;	
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	//不存在该商户号
	        	return INPUT;
			}
			//
			if(StringUtils.isBlank(BillNo)){
				responseCode = 26;
	        	resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;	
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	//不存在该商户号
	        	return INPUT;
			}
			merchantOrderNo = BillNo;
	    	tradeMoneyType = Currency;
	    	currencyName = getStates().getCurrencyTypeByNo(Integer.valueOf(tradeMoneyType));
			Long begin = System.currentTimeMillis();
			// 交易商户是否存在
			String sql1 = " from InternationalMerchant t where t.merno='"
					+ this.MerNo + "'";
			//获取商户资料
			
			InternationalMerchant merchant=new InternationalMerchant();	
			List<InternationalMerchant> merchantList=this.commonService.list(sql1);
	        if(merchantList==null||merchantList.size()==0){
	        	responseCode = 10;
	        	resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;	
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	//不存在该商户号
	        	return INPUT;
	        	
	        }  
	        merchant=merchantList.get(0); 
			//获取mD5key值
			if(merchant!=null){
				if(merchant.getMd5key()==null){
					responseCode = 11;	
					resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;	
		        	md5Value = md5.getMD5ofStr(resultMd5);
					message = "Payment Declined";
		        	return INPUT;  				
				}else{
				MD5key = merchant.getMd5key();
	
				}}  
			// 获取交易流水号
			GetBatchNo ut = new GetBatchNo();
			rorderno = ut.getOrderinfo(MerNo);
			// 校验交易流水号是否重复
			String hql = "select count(*) from international_tradeinfo t where t.ORDERNO='"
					+ rorderno + "'";
			int trlist = this.tradeManager.intBySql(hql);
			if (trlist > 0) {
				this.responseCode=24;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;	
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				return INPUT;
			}
	
			if(Double.valueOf(Amount)>100000L)
			{
				this.responseCode=26;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;		
	        	md5Value = md5.getMD5ofStr(resultMd5);
	 			return INPUT;        			
			}
			
			// 获取交易时间
			tradetime = ut.getTime();
			if (MerNo != null) {
				merchantnoValue = Long.valueOf(MerNo);
			} else {
				merchantnoValue = 0l;
			}		
	
	        
	        if(merchant.getIsopen().equals("0")){
	        	//商户未开通
	        	responseCode = 15;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;		
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	return INPUT;        	
	        	
	        }
	
			
			// 检验商户通道是否开通
			String sql2 = "select count(*) from international_merchantchannels t where t.merchantid='"+merchant.getId()+"' and t.onoff='1'";
			int isDredge = this.tradeManager.intBySql(sql2);
			if(isDredge==0){
				responseCode = 16;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;		
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	return INPUT;        			
			}
	
	
			//币种  //select t.*, t.rowid from international_moneykindname t where t.id=(select f.moneykindno from international_merchantcurrency f where f.merchanid='1' )
//			 List<InternationalMoneykindname>  moneykinds=this.commonService.list(" from InternationalMoneykindname t " +
//			 		"where t.id in (select f.moneyKindNo from InternationalMerchantCurrency f " +
//			 		"where f.merchanId='"+merchant.getId()+"' )");
//        if(moneykinds.size()==0||moneykinds==null){
//					
//					responseCode = 12;	
//					resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;		
//		        	md5Value = md5.getMD5ofStr(resultMd5);
//					message = "Payment Declined";
//		        	return INPUT;    			 
//			 }
//			 InternationalMoneykindname  moneykind=moneykinds.get(0);
//			//检验币种
//			if(!(Currency.equals(moneykind.getMoneykindno()+""))){
//				
//				responseCode = 12;	
//				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;			
//	        	md5Value = md5.getMD5ofStr(resultMd5);
//				message = "Payment Declined";
//	        	return INPUT;      			
//			}
	//select t.*,t.rowid from international_exchangerate t  where t.executetime in (select max(f.executetime) from international_exchangerate f where f.moneykindno=1 group by f.type)		
			// 获取交易汇率
			List   changerates=this.commonService.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id " +
					"and m.moneykindno="+this.Currency+" and t.executetime<sysdate-1   and t.type='1' order by t.executetime desc  "); //交易汇率
			// 获取结算汇率
			List   changeratesbalance=this.commonService.getByList("select t.id,t.rate,t.type from international_exchangerate t,international_moneykindname m  where t.moneykindno=m.id " +
					"and m.moneykindno="+this.Currency+" and t.executetime<sysdate-1   and t.type='2' order by t.executetime desc  "); //结算汇率
		
			if(changerates.size()<1 && changeratesbalance.size()<1){
				responseCode = 12;	
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;		
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	return INPUT;    			
			}
			InternationalExchangerate changerate=new InternationalExchangerate();
			InternationalExchangerate settlementrate=new InternationalExchangerate();
			for(int i=0;i<changerates.size();i++){
				Object[] tem=(Object[]) changerates.get(0);
				changerate.setId(Long.valueOf(tem[0].toString()));
				changerate.setRate(Double.valueOf(tem[1].toString()));
				changerate.setType("1");
			}
			for(int i=0;i<changeratesbalance.size();i++){
				Object[] tem=(Object[]) changeratesbalance.get(0);
				settlementrate.setId(Long.valueOf(tem[0].toString()));
				settlementrate.setRate(Double.valueOf(tem[1].toString()));
				settlementrate.setType("2");
			}
				// 获取某个币种当前的汇率
			Double traderate = Double.valueOf(changerate.getRate());
			if (traderate != null) {
				rmbmoney = traderate * tradeMoney;
			}
			else{
				//商户币种没设置
				responseCode = 12;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;	
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
				return INPUT;  		
			}
				
				
			
			//验证交易网址是否存在		
			String url = request.getHeader("Referer");
			String a [] = url.split("/");
			tradeAdd = a[2];
			System.out.println("==========================================="+tradeAdd);
			String sqlCheckWeb=" select count(*) from International_Webchannels t where t.tradeWebsite='"+tradeAdd+"' and t.merchanid='"+merchant.getId()+"' ";
			int checkWeb=this.tradeManager.intBySql(sqlCheckWeb);
			String sqlCheckWebReturn=" select count(*) from International_Webchannels t where t.website='"+this.ReturnURL+"' and t.merchanid='"+merchant.getId()+"' ";
			int checkWebReturn=this.tradeManager.intBySql(sqlCheckWebReturn);
			//交易网址必须注册
			 if(checkWeb==0){
				responseCode = 22;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;			
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	return INPUT;    	
			}
			//返回网址必须注册
			 if(checkWebReturn==0){
				responseCode = 14;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;	
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	return INPUT;    	
			}		 
			 
			//获取交易金额
			md5src = merchantnoValue + BillNo + Currency + Amount + Language + ReturnURL + MD5key ;
	
			md5src = md5.getMD5ofStr(md5src);		
			//获取通道
			
			//信息被篡改
			//System.out.println("========"+merchantnoValue +"========"+ BillNo+"========" + moneykind.getMoneykindno()+"========" + Amount+"========" + Language+"========" + ReturnURL+"========" + MD5key+"========" +"========5555============"+md5src);
			//System.out.println("===============666666============="+MD5info);		
			 if(!(md5src.equals(MD5info))){
				responseCode = 13;
				resultMd5 = BillNo+Currency+Amount+responseCode+MD5key;		
	        	md5Value = md5.getMD5ofStr(resultMd5);
				message = "Payment Declined";
	        	return INPUT;  			
			 }
			tradeMoney = (double)(Math.round((double)tradeMoney*100)/100.00);
			rmbmoney = (double)(Math.round((double)rmbmoney*100)/100.00);		
			InternationalTradeinfo trade = new InternationalTradeinfo();
			trade.setOrderNo(rorderno);
			trade.setMerchantOrderNo(merchantOrderNo);	
			trade.setMerchantId(merchant.getId());
			trade.setTradeTime(tradetime);
			trade.setTradeAmount(Double.valueOf(this.Amount));		
			trade.setRmbAmount(this.rmbmoney);
			trade.setMoneyType(Long.valueOf(Currency));
			trade.setTradeState("30000000000000000000");		
			trade.setTradeRate(changerate.getId());
			trade.setBalanceRate(settlementrate.getId());	
			trade.setTradeUrl(tradeAdd);
			trade.setReturnUrl(this.ReturnURL);
			trade.setLastDate(new Date());
			trade.setBackCount(0d);
			this.commonService.saveOrUpdate(trade);
			responseCode = 0;
			List<InternationalPayVersion> ipv = this.commonService
			.list(" from  InternationalPayVersion t where t.merchantId='"
					+ merchant.getId() + "'");
	if (ipv.size() == 0) {
		return SUCCESS;
	} else {
		InternationalPayVersion ipvs = ipv.get(0);
		if (ipvs.getVersion().equals("1")) {
			return SUCCESS;
		}
		if (ipvs.getVersion().equals("2")) {
			return "success2";
		}
		if (ipvs.getVersion().equals("3")) {
			return "success3";
		}
		if (ipvs.getVersion().equals("4")) {
			return "success4";
		}
	}			
			return SUCCESS;
		}
		catch(Exception e){
			 e.printStackTrace();
			 return ERROR;
		 }
	}
	public TradeManager getTradeManager() {
		return tradeManager;
	}
	public void setTradeManager(TradeManager tradeManager) {
		this.tradeManager = tradeManager;
	}
	public String getMerNo() {
		return MerNo;
	}
	public void setMerNo(String merNo) {
		MerNo = merNo;
	}
	public String getRorderno() {
		return rorderno;
	}
	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}
	public Date getTradetime() {
		return tradetime;
	}
	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}
	public Long getMerchantnoValue() {
		return merchantnoValue;
	}
	public void setMerchantnoValue(Long merchantnoValue) {
		this.merchantnoValue = merchantnoValue;
	}
	public boolean isCheckMerch() {
		return checkMerch;
	}
	public void setCheckMerch(boolean checkMerch) {
		this.checkMerch = checkMerch;
	}
	public long getIsDredge() {
		return isDredge;
	}
	public void setIsDredge(long isDredge) {
		this.isDredge = isDredge;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getOrdercount() {
		return ordercount;
	}
	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
	}
	public Double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(Double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public double getRmbmoney() {
		return rmbmoney;
	}
	public void setRmbmoney(double rmbmoney) {
		this.rmbmoney = rmbmoney;
	}
	public Double getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
	public String getBillNo() {
		return BillNo;
	}
	public void setBillNo(String billNo) {
		BillNo = billNo;
	}
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String language) {
		Language = language;
	}
	public String getReturnURL() {
		return ReturnURL;
	}
	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}
	public String getMd5src() {
		return md5src;
	}
	public void setMd5src(String md5src) {
		this.md5src = md5src;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getCooknumber() {
		return cooknumber;
	}
	public void setCooknumber(String cooknumber) {
		this.cooknumber = cooknumber;
	}
	public String getMD5info() {
		return MD5info;
	}
	public void setMD5info(String md5info) {
		MD5info = md5info;
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
	public String getTradeAdd() {
		return tradeAdd;
	}
	public void setTradeAdd(String tradeAdd) {
		this.tradeAdd = tradeAdd;
	}
	public Long getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(Long moneyType) {
		this.moneyType = moneyType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResultMd5() {
		return resultMd5;
	}
	public void setResultMd5(String resultMd5) {
		this.resultMd5 = resultMd5;
	}
	public String getTradeMoneyType() {
		return tradeMoneyType;
	}
	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}
	public String getMd5Value() {
		return md5Value;
	}
	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
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
	public String getCardbank() {
		return cardbank;
	}
	public void setCardbank(String cardbank) {
		this.cardbank = cardbank;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
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
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

}
