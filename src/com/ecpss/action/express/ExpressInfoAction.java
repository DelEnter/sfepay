package com.ecpss.action.express;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.ExpressOrderThread;
import com.ecpss.action.ExpressUserThread;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalExpress;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;

public class ExpressInfoAction extends BaseAction {
	
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	
	private String merid;
	
	//物流类型       是蜂皇
	private String pt_code;//平台标识(sfepay)
	private String customercode;//平台商户号(mer.getMerNo)
	
	private String orders;
	
	private String exp_Code;
	private String expressStatus;//物流状态（未发货，已发货，已妥投）
	private String orderNo;//迁易通订单流水号            
	private String expressNo;//物流单号
	private String amout;//订单费用
	
	private String status;	
	private String message;
	
	private String expordermess;
	
	private String address;//	地址mer.meradress
	private String true_name;//真实姓名mer.mername
	private String phone;//电话mer.mermobile
	
	private String expopenstatus;

	Logger logger = Logger.getLogger(ExpressInfoAction.class.getName());	

	
	public String expressOrder(){
		logger.info("********下单************");
		InternationalTradeinfo trade=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where orderNo='"+this.getOrderNo()+"'");
		InternationalMerchant mer = (InternationalMerchant) commonService.uniqueResult("from InternationalMerchant where id='"+trade.getMerchantId()+"'");
		InternationalCardholdersInfo card = (InternationalCardholdersInfo) commonService.uniqueResult("from InternationalCardholdersInfo where tradeid='"+trade.getId()+"'");
			this.setPt_code("sfepay");
			this.setCustomercode(String.valueOf(mer.getMerno()));
	
			///JSONObject json = JSONObject.fromObject(card);
			
			JSONObject json = new JSONObject();
			
			json.put("pt_id", this.getOrderNo());			
			json.put("order_id", String.valueOf(trade.getId()));
			json.put("name", card.getShippingFullName());
			json.put("postcode", card.getShippingZip());
			json.put("phone", card.getPhone());
			json.put("mobile", card.getShippingPhone());
			json.put("country", card.getShippingCountry());	
			json.put("province", card.getShippingState());
			json.put("city", card.getShippingCity());
			json.put("street", card.getShippingAddress());
			json.put("email", card.getShippingEmail());
			//json.put("goods", card.getProductInfo());
			
			JSONObject a = new JSONObject();
			a.put("cnname", card.getProductInfo());
			a.put("enname", card.getProductInfo());
			a.put("count", "1");
			a.put("unit", card.getProductInfo());
			a.put("weight", "");
			a.put("delcarevalue", trade.getTradeAmount());
			a.put("sellurl", trade.getTradeUrl());
			a.put("description", card.getProductInfo());
			
			json.put("goods", "["+a.toString()+"]");
			
			//json.put("goods", a.toString());
			
			orders = json.toString();
			
			logger.info("********订单信息***********,订单集合："+orders);

			//ExpressOrderThread  eut = new ExpressOrderThread(this.getPt_code(), this.getCustomercode(), orders,"http://139.224.211.58:8083/v1/order/push",commonService,trade);
			ExpressOrderThread  eut = new ExpressOrderThread(this.getPt_code(), this.getCustomercode(), orders,"http://localhost:8090/expressPayOrder",commonService,trade);
			
			eut.start();	
			
		return SUCCESS;
	}
	
	
	public String expressOpen(){
		InternationalMerchant mer = (InternationalMerchant) commonService.uniqueResult("from InternationalMerchant where id='"+merid+"'");
		merid = String.valueOf(mer.getMerno());
		if(mer.getExpopenstatus() == null ||mer.getExpopenstatus().equals("0") ){
			status = "0";
			message = "未开通!";
		}else if(mer.getExpopenstatus().equals("1")){
			status = "1";
			message = "已开通!";
		}
	
		return SUCCESS;
	}
	
	
	public String expressExpOrder(){
		InternationalTradeinfo trade=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where orderNo='"+this.getOrderNo()+"'");
		this.setExpordermess(trade.getExpordermess());
		return SUCCESS;
	}
	
	public String expressReg(){
		InternationalMerchant merchant = (InternationalMerchant) commonService.uniqueResult("from InternationalMerchant where merno='"+merid+"'");
		
		 /** 用户点击下单，抓取迁易通流水号，蜂皇将物流商号（估计就是商户号比如3604）以及用户名密码推送过来
		 * 我们通过线程去调蜂皇给的接口地址，同步商户信息（同步用户名和密码）
		 * */
		 
		if(this.expopenstatus.equals("1") && merchant.getExpuername()==null){
			this.setPt_code("sfepay");
			this.setCustomercode(String.valueOf(merchant.getMerno()));
			this.setAddress(merchant.getMeradress());
			this.setTrue_name(merchant.getMername());
			this.setPhone(merchant.getMermobile());
			ExpressUserThread  eut = new ExpressUserThread(commonService,merchant,this.getPt_code(), this.getCustomercode(), this.getAddress(), this.getTrue_name(), this.getPhone(),"http://139.224.211.58:8083/v1/user/info");
			eut.start();
		}
		return SUCCESS;
	}
	
	
	public String getExpopenstatus() {
		return expopenstatus;
	}


	public void setExpopenstatus(String expopenstatus) {
		this.expopenstatus = expopenstatus;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTrue_name() {
		return true_name;
	}


	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMerid() {
		return merid;
	}


	public void setMerid(String merid) {
		this.merid = merid;
	}


	public String getPt_code() {
		return pt_code;
	}


	public void setPt_code(String pt_code) {
		this.pt_code = pt_code;
	}


	public String getCustomercode() {
		return customercode;
	}


	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}


	public String getOrders() {
		return orders;
	}


	public void setOrders(String orders) {
		this.orders = orders;
	}


	public String getExpressStatus() {
		return expressStatus;
	}


	public void setExpressStatus(String expressStatus) {
		this.expressStatus = expressStatus;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getExpressNo() {
		return expressNo;
	}


	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}


	public String getAmout() {
		return amout;
	}


	public void setAmout(String amout) {
		this.amout = amout;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getExp_Code() {
		return exp_Code;
	}


	public void setExp_Code(String exp_Code) {
		this.exp_Code = exp_Code;
	}


	public CommonService getCommonService() {
		return commonService;
	}


	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}


	public String getExpordermess() {
		return expordermess;
	}


	public void setExpordermess(String expordermess) {
		this.expordermess = expordermess;
	}
	
}
