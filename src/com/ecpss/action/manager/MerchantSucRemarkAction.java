package com.ecpss.action.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.permissions.InternationalMerchantSucRate;
import com.ecpss.model.refund.InternationalRefundManager;

public class MerchantSucRemarkAction extends BaseAction {
	private List<InternationalMerchantSucRate> li = new ArrayList();
	private InternationalMerchantSucRate merSucRate=new InternationalMerchantSucRate();
	public String toMerSucRateInfo(){
		String hql="from InternationalMerchantSucRate m ";
			li =commonService.list(hql);
			return SUCCESS;
	}
	public String toAddMerSucRate(){
		if(merSucRate.getId()!=null){
			merSucRate=(InternationalMerchantSucRate) commonService.load(InternationalMerchantSucRate.class,merSucRate.getId());
		}
		return SUCCESS;
	}
	public String addorUpdateMerSucRate(){
		InternationalMerchantSucRate merSuc=new InternationalMerchantSucRate();
		if(merSucRate.getId()!=null){
			merSuc=(InternationalMerchantSucRate) commonService.load(InternationalMerchantSucRate.class,merSucRate.getId());
			merSuc.setStatus(merSucRate.getStatus());
			merSuc.setTradeRemark(merSucRate.getTradeRemark());
			merSuc.setLastDate(new Date());
			merSuc.setLastMan(getUserBean().getUserName());
			commonService.update(merSuc);
			messageAction = "更新成功!";
		}else{
			merSuc.setStatus(merSucRate.getStatus());
			merSuc.setTradeRemark(merSucRate.getTradeRemark());
			merSuc.setCreateDate(new Date());
			merSuc.setCreateName(getUserBean().getUserName());
			merSuc.setLastDate(new Date());
			merSuc.setLastMan(getUserBean().getUserName());
			commonService.save(merSuc);
			messageAction = "添加成功!";
		}
		return OPERATE_SUCCESS;
	}
	public String deleteMerSucRate(){
		if(merSucRate.getId()!=null){
			this.commonService.delete(merSucRate);
		}
		messageAction = "删除成功!";
		return OPERATE_SUCCESS;
	}
	
	//商户成功率统计
	public String merSucRateAll(){
		return SUCCESS;
	}
	
	public List<InternationalMerchantSucRate> getLi() {
		return li;
	}
	public void setLi(List<InternationalMerchantSucRate> li) {
		this.li = li;
	}
	public InternationalMerchantSucRate getMerSucRate() {
		return merSucRate;
	}
	public void setMerSucRate(InternationalMerchantSucRate merSucRate) {
		this.merSucRate = merSucRate;
	}


}
