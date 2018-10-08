package com.ecpss.action.tradedispose;

import java.text.DecimalFormat;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalTwoDispose;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.DateUtil;
import com.ecpss.util.StateUtils;
import com.ecpss.util.StatusUtil;
import com.ecpss.web.PageInfo;

public class GBPErrorAction extends BaseAction{
	private PageInfo info;
	
	private Long tradeId;
	
	private Object[] errortrade;
	
	private String VIPBatchNo;
	private String VIPAuthorizationNo;
	private String isresult;
	private String remark;
	
	private Double shijikoukuan;
	private Double yingkoukuan;
	private Double chae;
	
	public String findTradeError(){
		String hql = "select ti,ci from InternationalTradeinfo ti," +
				"InternationalCardholdersInfo ci," +
				"InternationalMerchantCurrency mc " +
				"where ci.tradeId=ti.id " +
				"and mc.moneyKindNo='2' " +
				"and mc.merchanId=ti.merchantId " +
				"and substr(ti.tradeState,13,1)=0 " +
				"and ti.tradeTime<=to_date('2010-6-3 03:00:00','yyyy-MM-dd hh24:mi:ss') " +
				"and ti.tradeState like '1%' order by ti.tradeTime desc ";
		info = commonService.listQueryResultByHql(hql, info);
		
		return SUCCESS;
	}

	public String tofindTradeError(){
		String hql = "select ci.cardNo,ci.expiryDate,ci.cvv2,ti.tradeAmount,ti.rmbAmount,ti.tradeAmount*9.63,ti.tradeAmount*(9.63-6.82),ti " +
				"from InternationalTradeinfo ti," +
			"InternationalCardholdersInfo ci," +
			"InternationalMerchantCurrency mc " +
			"where ci.tradeId=ti.id " +
			"and mc.moneyKindNo='2' " +
			"and mc.merchanId=ti.merchantId " +
			"and ti.id=" + tradeId +
			"and ti.tradeState like '1%' ";
		errortrade = (Object[]) this.commonService.uniqueResult(hql);
		return SUCCESS;
	}
	
	public String disposeErrorTrade(){
		InternationalTwoDispose td = new InternationalTwoDispose();
		td.setAuthno(VIPAuthorizationNo);
		td.setBatchno(VIPBatchNo);
		td.setTworesult(isresult);
		td.setRemark(remark);
		td.setChae(chae);
		td.setTradeId(tradeId);
		td.setShijikoukuan(shijikoukuan);
		td.setYingkoukuan(yingkoukuan);
		this.commonService.save(td);
		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		
		StateUtils states = new StateUtils();
		InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, tradeId);
		String hql = "select ci from InternationalCardholdersInfo ci where ci.tradeId="+ti.getId();
		InternationalCardholdersInfo ci = (InternationalCardholdersInfo) this.commonService.uniqueResult(hql);
		StringBuffer setText = new StringBuffer();
		setText.append("The system e-mail,Please do not reply");
		setText.append(" \n\n\n  ");
		setText.append("Dear " + ci.getEmail()+" , \n  ");
		setText.append("Thank you for taking time to read this letter. Please be informed that we're ECPSS who is responsible to collect the payment on your seller's behalf."+"\n\n  ");
	
		setText.append("Refer to your order which details is as following:"+"\n\n ");
		setText.append("Merchant Order No. :  " + ti.getMerchantOrderNo()+"\n ");				
		setText.append("Order No.          :  " +	ti.getOrderNo() +"\n ");			
		setText.append("Payment Date&Time  :  " + DateUtil.returnGreenwich(ti.getTradeTime()) + "\n ");				
		setText.append("Amount             :  " + ti.getTradeAmount() +" "+ states.getCurrencyTypeByNo(ti.getMoneyType().intValue()) + "\n \n");	
		
		setText.append("Because our acquiring bank made a mistake that they only charged you "+decimalFormat.format(shijikoukuan/9.63)+" "+states.getCurrencyTypeByNo(ti.getMoneyType().intValue())+".  Now our acquiring bank let us send an honest email to you to explain the case, and we hope you can be kind enough to pay the rest payment amount "+decimalFormat.format(chae/9.63)+" "+states.getCurrencyTypeByNo(ti.getMoneyType().intValue())+" to our acquiring bank. The payment link is "+"http://security.sslepay.com/BalancePayInterface?MerNo=1838&Amount="+decimalFormat.format(chae/9.63)+"&BillNo="+ci.getEmail()+" .The billing address in your account will be www.vpnpay.com .We're so sorry for this mistake, and we will appreciate it if you could be kind enough to understand this. Thanks and thanks again. Many thanks in advance."+"\n\n");		
		setText.append("If you have any questions, please don't hesitate to contact us, and we will try our best to give you the best resolution."+"\n\n");
		setText.append("Best Regards"+ "\n ");												
		setText.append("ECPSS"+ "\n ");												
		setText.append("Contact details:"+ "\n ");												
		setText.append("Customer service line£º+86-18939704773"+ "\n ");	
		setText.append("Customer service line£º+86-18930279312"+ "\n ");												
		setText.append("Email: csreason@ecpss.com/cs@ecpss.com"+ "\n ");												
		setText.append("Fax:  +86-21-52837771-888"+ "\n ");	
		setText.append("Complaint website:  www.ecpsc.cc"+ "\n ");	
		if(isresult.equals("1")){
			CCSendMail.setSendMail(ci.getEmail(), setText.toString(), "ecpss@ecpss.cc");
			ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 13, "2"));
			this.commonService.update(ti);
		}
		return findTradeError();
	}
	
	

	public PageInfo getInfo() {
		return info;
	}


	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Object[] getErrortrade() {
		return errortrade;
	}

	public void setErrortrade(Object[] errortrade) {
		this.errortrade = errortrade;
	}

	public String getVIPBatchNo() {
		return VIPBatchNo;
	}

	public void setVIPBatchNo(String batchNo) {
		VIPBatchNo = batchNo;
	}

	public String getVIPAuthorizationNo() {
		return VIPAuthorizationNo;
	}

	public void setVIPAuthorizationNo(String authorizationNo) {
		VIPAuthorizationNo = authorizationNo;
	}

	public String getIsresult() {
		return isresult;
	}

	public void setIsresult(String isresult) {
		this.isresult = isresult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getShijikoukuan() {
		return shijikoukuan;
	}

	public void setShijikoukuan(Double shijikoukuan) {
		this.shijikoukuan = shijikoukuan;
	}

	public Double getYingkoukuan() {
		return yingkoukuan;
	}

	public void setYingkoukuan(Double yingkoukuan) {
		this.yingkoukuan = yingkoukuan;
	}

	public Double getChae() {
		return chae;
	}

	public void setChae(Double chae) {
		this.chae = chae;
	}
	
}
