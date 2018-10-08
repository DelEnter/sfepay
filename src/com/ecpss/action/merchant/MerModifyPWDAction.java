package com.ecpss.action.merchant;

import java.security.MessageDigest;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;
import com.opensymphony.xwork2.ActionContext;

public class MerModifyPWDAction extends BaseAction{

	private InternationalMerchant merchant;
	private String password;
	private String newpassword;
	private String confirmPwd;
	private String shenfenzheng;
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toMerModifyPwd(){
		if(getMerchantBean()!=null){
			merchant = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, getMerchantBean().getMerchantId());
			return SUCCESS;
		}else{
			//登陆
			this.messageAction="登陆错误,或者登陆超时.请重新登陆";
			return INPUT;
		}
	}
	
	
	public String modifyMerchantPwd() throws Exception{
		
		if(StringUtils.isBlank(password) ){
			this.messageAction="Please input Original password";
			return "modify";
		}
		if(StringUtils.isBlank(newpassword) || StringUtils.isBlank(confirmPwd)){
			this.messageAction="Please input new password.";
			return "modify";
		}
		String hql="select s.id from InternationalMerchant m,ShopOpera s " +
				"where s.merchantId=m.id " +
				"and m.merno="+merchant.getMerno()+" " +
						"and s.userName='"+merchant.getUsername()+"' ";
		int a = this.commonService.list(hql).size();
		if(a==1){
			//商户号下的操作员不可以修改密码
			this.messageAction="权限不足,禁止修改密码.";
			return SUCCESS;
		}else{
			MessageDigest md5;
			BASE64Encoder base64en = new BASE64Encoder();
			md5 = MessageDigest.getInstance("MD5");
			String passwords = base64en.encode(md5.digest(password.getBytes("utf-8")));
			String hql1="select m from InternationalMerchant m " +
					"where m.merno="+merchant.getMerno()+" " +
							"and m.username='"+merchant.getUsername()+"' " +
									"and m.password='"+passwords+"' ";
			InternationalMerchant mer = (InternationalMerchant) this.commonService.uniqueResult(hql1);
			if(mer==null){
				//ActionContext.getContext().put("passwordError", true);
				this.messageAction="Original password is incorrect.";
				return "modify";
			}else{
				if(!mer.getCertificateno().equals(shenfenzheng)){
					this.messageAction="Authentication fails, enter the correct information";
					return "modify";
				}
				if(newpassword.equals(confirmPwd)){
					String newpwd = base64en.encode(md5.digest(newpassword.getBytes("utf-8")));
					mer.setPassword(newpwd);
					mer.setModifyPwdTime(new Date());
					this.commonService.update(mer);
					this.messageAction="Changed successfully, please use the new password to operate.";
					return SUCCESS;
				}else{
					//ActionContext.getContext().put("notSamePwd", true);
					this.messageAction="Enter the new password twice inconsistent.";
					return "modify";
				}
			}
		}
	}
	
	public InternationalMerchant getMerchant() {
		return merchant;
	}
	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNewpassword() {
		return newpassword;
	}


	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}


	public String getConfirmPwd() {
		return confirmPwd;
	}


	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}


	public String getShenfenzheng() {
		return shenfenzheng;
	}


	public void setShenfenzheng(String shenfenzheng) {
		this.shenfenzheng = shenfenzheng;
	}
	
}
