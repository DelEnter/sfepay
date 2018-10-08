package com.ecpss.action.merchant;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.risk.InternationalBacklist;
//商户自主黑卡控制
public class ProhibitCategoryAction extends BaseAction {
	private String blackCardNo;
	private String blackEmail;
	private String blackIp;
	private Long id;
	private List<InternationalBacklist> backList;
	public String toProhibitCard(){
		backList=this.commonService.list("from InternationalBacklist t where t.merId='"+getMerchantBean().getMerchantId()+"'");
		for(InternationalBacklist backCardInfo:backList){
			String cardNo=backCardInfo.getCardno();
			if(StringUtils.isNotBlank(cardNo)){
			backCardInfo.setCardno(cardNo.substring(0, 6)+"******"+cardNo.substring(cardNo.length()-4, cardNo.length()));
			}
		}
		return SUCCESS;
	}
	public String addBlackCardInfo(){
		InternationalBacklist backCard=new InternationalBacklist();
		backCard.setCardno(blackCardNo);
		backCard.setEmail(blackEmail);
		backCard.setIp(blackIp);
		backCard.setMerId(getMerchantBean().getMerchantId());
		this.commonService.save(backCard);
		toProhibitCard();
		return SUCCESS;
	}
	public String delBlackCardInfo(){
		this.commonService.delete(InternationalBacklist.class, id);
		toProhibitCard();
		return SUCCESS;
	}
	public String getBlackCardNo() {
		return blackCardNo;
	}
	public void setBlackCardNo(String blackCardNo) {
		this.blackCardNo = blackCardNo;
	}
	public String getBlackEmail() {
		return blackEmail;
	}
	public void setBlackEmail(String blackEmail) {
		this.blackEmail = blackEmail;
	}
	public String getBlackIp() {
		return blackIp;
	}
	public void setBlackIp(String blackIp) {
		this.blackIp = blackIp;
	}
	public List<InternationalBacklist> getBackList() {
		return backList;
	}
	public void setBackList(List<InternationalBacklist> backList) {
		this.backList = backList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
