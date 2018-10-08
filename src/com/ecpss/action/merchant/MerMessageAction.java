package com.ecpss.action.merchant;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.ExpressUserThread;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;
import com.opensymphony.xwork2.ActionContext;

public class MerMessageAction extends BaseAction{
	private String flag="0";
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	private InternationalMerchant mer;
	private String hql;
	//private long merchantno = 16885;
	private String pass;
	private String openTime;
	
	/*private String pt_code;//平台标识(sfepay)
	private String customercode;//平台商户号(mer.getMerNo)
	private String address;//	地址mer.meradress
	private String true_name;//真实姓名mer.mername
	private String phone;//电话mer.mermobile
*/	
	/**
	 * 跳转到查询商户信息页面
	 */
	public String toFindMerMessage(){
		try{
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到查询商户信息页面错误";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 跳转到管理商户信息页面
	 */
	public String managerMerMessage(){
		try{
			//hql = "FROM InternationalMerchant mer WHERE mer.id="+mer.getId()+"";
			hql = "FROM InternationalMerchant mer WHERE mer.merno="+mer.getMerno()+"";
			mer = (InternationalMerchant)commonService.uniqueResult(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到管理商户信息页面失败";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 修改商户管理信息
	 */
	public String updateMerManagerMessage(){
		try{
			InternationalMerchant merchant = (InternationalMerchant)commonService.load(InternationalMerchant.class, mer.getId());
			merchant.setMermobile(mer.getMermobile());
			merchant.setMerphone(mer.getMerphone());
			merchant.setMeremail(mer.getMeremail());
			merchant.setLinkman(mer.getLinkman());
			merchant.setCertificateno(mer.getCertificateno());
			merchant.setLinkmanphone(mer.getLinkmanphone());
			merchant.setLinkmanmobile(mer.getLinkmanmobile());
			merchant.setLinkmanadress(mer.getLinkmanadress());
			merchant.setLinkmanemail(mer.getLinkmanemail());
			merchant.setWebsite(mer.getWebsite());
			merchant.setMd5key(mer.getMd5key());
			merchant.setAccountname(mer.getAccountname());
			merchant.setBank(mer.getBank());
			merchant.setCardno(mer.getCardno());
			merchant.setMerqq(mer.getMerqq());
			merchant.setMeradress(mer.getMeradress());
			merchant.setMername(mer.getMername());
			/*merchant.setExpopenstatus(mer.getExpopenstatus());			
			merchant.setDiscountfee(mer.getDiscountfee());	*/	
			merchant.setDishonor(mer.getDishonor());
			merchant.setLinkmancertificateno(mer.getLinkmancertificateno());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			if(StringUtils.isNotBlank(openTime)){
				merchant.setOpenTime(sdf.parse(openTime+" 00:00:00"));
			}
			merchant.setAnnualFee(mer.getAnnualFee());
			merchant.setRemark(mer.getRemark());
			commonService.update(merchant);
			this.messageAction = "修改商户管理信息成功";
			return this.OPERATE_ERROR;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "修改商户管理信息失败";
			return this.OPERATE_ERROR;
		}
	}
	
	/**
	 * 跳转到修改商户信息页面
	 */
	public String toUpdateMerMessage(){
		try{
			
			//hql = "FROM InternationalMerchant mer WHERE mer.merno="+merchantno+"";
			hql = "FROM InternationalMerchant mer WHERE mer.id="+getMerchantBean().getMerchantId()+"";
			mer = (InternationalMerchant)commonService.uniqueResult(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到修改商户信息页面错误!";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 修改商户信息
	 */
	public String updateMerMessage(){
		try{
			InternationalMerchant merchant = new InternationalMerchant();
			//getMerchantBean().getMerchantId();
			
			merchant = (InternationalMerchant)commonService.load(InternationalMerchant.class, mer.getId());
			//merchant = (InternationalMerchant)commonService.load(InternationalMerchant.class, getMerchantBean().getMerchantId());
			merchant.setMerphone(mer.getMerphone());
			merchant.setMermobile(mer.getMermobile());
			merchant.setMeremail(mer.getMeremail());
			merchant.setLinkman(mer.getLinkman());
			//merchant.setLinkmancertificateno(mer.getLinkmancertificateno());
			merchant.setLinkmanphone(mer.getLinkmanphone());
			merchant.setLinkmanmobile(mer.getLinkmanmobile());
			merchant.setLinkmanadress(mer.getLinkmanadress());
			merchant.setLinkmanemail(mer.getLinkmanemail());
			merchant.setWebsite(mer.getWebsite());
			merchant.setMerqq(mer.getMerqq());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			if(StringUtils.isNotBlank(openTime)){
				merchant.setOpenTime(sdf.parse(openTime+" 00:00:00"));
			}
			merchant.setAnnualFee(mer.getAnnualFee());
			merchant.setRemark(mer.getRemark());
			/*merchant.setExpopenstatus(mer.getExpopenstatus());
			merchant.setDiscountfee(mer.getDiscountfee());*/
			merchant.setDishonor(mer.getDishonor());
			commonService.update(merchant);
			this.flag="1";
			//this.messageAction = "修改商户信息成功!";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.flag="2";
			return SUCCESS;
			//this.messageAction = "修改商户信息失败!";
			//return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 跳转到修改商户密码页面
	 */
	public String toUpdateMerPassword(){
		try{
			hql = "FROM InternationalMerchant mer WHERE mer.id="+getMerchantBean().getMerchantId()+"";
			mer = (InternationalMerchant)commonService.uniqueResult(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到修改商户密码页面失败!";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 修改商户密码
	 */
	public String updateMerPassword(){
		try{
			hql = "FROM InternationalMerchant m where m.id="+getMerchantBean().getMerchantId()+" AND m.password="+pass+" AND m.certificateno='"+mer.getCertificateno()+"'";
			List list = commonService.list(hql);
			if(list.size()==0){
				this.messageAction = "输入的原有密码或身份证号码错误!";
			}else{
				InternationalMerchant merchant = new InternationalMerchant();
				merchant = (InternationalMerchant)commonService.load(InternationalMerchant.class, mer.getId());
				//merchant = (InternationalMerchant)commonService.load(InternationalMerchant.class, getMerchantBean().getMerchantId());
				merchant.setPassword(mer.getPassword());
				commonService.update(merchant);
				this.messageAction = "修改商户密码成功!";
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "修改商户密码失败!";
			return this.OPERATE_SUCCESS;
		}
	}
	
	public static HttpServletRequest getHttpServletRequest(){
		return (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	}
	
	/**
	 * @return the mer
	 */
	public InternationalMerchant getMer() {
		return mer;
	}
	/**
	 * @param mer the mer to set
	 */
	public void setMer(InternationalMerchant mer) {
		this.mer = mer;
	}
	/**
	 * @return the commonService
	 */
	public CommonService getCommonService() {
		return commonService;
	}
	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
/*	public String getPt_code() {
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}*/
		
}
