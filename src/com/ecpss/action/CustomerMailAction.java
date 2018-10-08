package com.ecpss.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.model.complaint.InternationalComplaintVisamaster;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.CCSendMail;

public class CustomerMailAction extends BaseAction{
	//定义属性变量
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	//定义属性变量
	private String orderno;
	private String username;
	private String email;
	private String amount;
	private String date;
	private String reason;
	private String message; 
	private String toemail;   //收件人地址
	private String content;
	
	public String getCustomerMail(){
		
		try{
			toemail = "csreason@visamasterpay.com";
			
			content = "MERCHANTNO: "+orderno+"\n" +
					  "NAME: "+username+"\n" +
					  "EMAIL: "+email+"\n" +
					  "AMOUNT: "+amount+"\n" +
					  "DATE: "+date+"\n" +
					  "------------------------REASON------------------------\n" +
					  ""+reason+""; 
//			try {
//		    	//发送邮件,如果发送失败插入数据库发送
//		    	//CCSendMail.setSendMail(toemail, content, "cs@visamasterpay.com");
//		    	//System.out.println("邮件立马发出");
//				
//				InternationalComplaintVisamaster visa = new InternationalComplaintVisamaster();
//				visa.setCmEmail(email);
//				visa.setContent(content);
//				visa.setLastDate(new Date());
//				visa.setOrderNo(orderno);
//				visa.setDates(date);
//				visa.setAmount(amount);
//				visa.setProcessingResults("0");
//				this.commonService.save(visa);
//			} catch (Exception e) {
//				//往数据库插入等待发送邮件
//				//shopManagerService.addSendMessages(toemail, "cs@visamasterpay.com", content, "0");
//				//System.out.println("邮件等待稍后发出");
//				return SUCCESS;
//			}
//			message = "邮件稍后发出!";
			return SUCCESS;
		}catch(Exception e){
			System.out.println(e.getMessage());
			message = "系统出现异常!";
			return INPUT;
		}
	}
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}


	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}


	public String getToemail() {
		return toemail;
	}


	public void setToemail(String toemail) {
		this.toemail = toemail;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
}
