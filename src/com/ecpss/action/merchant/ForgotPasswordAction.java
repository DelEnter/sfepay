package com.ecpss.action.merchant;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sun.misc.BASE64Encoder;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.iservice.MerchantManagerService;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;

public class ForgotPasswordAction extends BaseAction{
	@Autowired
	@Qualifier("merchantManagerService")
	private MerchantManagerService merchantManagerService;
	
	private Long merno;
	private String userName;
	private String email;
	
	/**
	 * 重置密码
	 * @return
	 * @throws Exception 
	 */
	public String resetPassword() throws Exception{
		if(StringUtils.isNotBlank(userName) && merno!=null && StringUtils.isNotBlank(email)){
			//查询是否存在改用户
			InternationalMerchant merchant = this.merchantManagerService.getMerchantUser(merno, userName,null);
			if(merchant!=null){
				//对比邮箱是否跟注册时候相对应
				if(merchant.getMeremail().trim().toLowerCase().equals(email.trim().toLowerCase())){
					String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
					StringBuffer generateRandStr = new StringBuffer();
					Random rand = new Random();
					for (int i = 0; i < 8; i++) {
						int randNum = rand.nextInt(36);
						generateRandStr.append(radStr.substring(randNum, randNum + 1));
					}
					MessageDigest md5;
					BASE64Encoder base64en = new BASE64Encoder();
					md5 = MessageDigest.getInstance("MD5");
					String passwords = base64en.encode(md5.digest(generateRandStr.toString()
							.getBytes("utf-8")));
					merchant.setPassword(passwords);
					merchant.setModifyPwdTime(new Date());
					commonService.update(merchant);
					//给商户发送邮件通知密码信息
					EmailInfo emailinfo=new EmailInfo();
					//System.out.println("Password is : " + passwords);
					//System.out.println(emailinfo.getResetPwdByEmail(merchant, generateRandStr.toString()));
					CCSendMail.setSendMail(merchant.getMeremail(), emailinfo.getResetPwdByEmail(merchant, generateRandStr.toString()), "ecpss@ecpss.cc");
					this.messageAction="新密码已经发送您的验证邮箱,请进入邮箱查看后重新登陆.";
					return  SUCCESS;
				}else{
					//输入的邮箱验证不正确
					this.messageAction="E-mail验证失败.请输入注册Email.";
					return INPUT;
				}
			}else{
				this.messageAction="输入的商户号,用户名不正确.";
				return INPUT;
			}
		}else{
			//信息都必须填写,不能为空
			this.messageAction="信息都必须输入,不能为空.";
			return INPUT;
		}
	}
	
	
	public Long getMerno() {
		return merno;
	}


	public void setMerno(Long merno) {
		this.merno = merno;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public MerchantManagerService getMerchantManagerService() {
		return merchantManagerService;
	}


	public void setMerchantManagerService(
			MerchantManagerService merchantManagerService) {
		this.merchantManagerService = merchantManagerService;
	}


	
	
	
}
