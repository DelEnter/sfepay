package com.ecpss.action.moneytypesetting;

import java.util.List;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchantCurrency;

/**
 * 币种设置
 * 
 * @author huhongguang
 * 
 */
public class MoneyTypeSettingAction extends BaseAction {

	private Long merid; // 商户id

	private List<InternationalMerchantCurrency> moneyTypeList; // 商户币种集合

	private InternationalMerchantCurrency internationalMerchantCurrency; // 商户币种实体

	/**
	 * 跳转到商户币种管理页面
	 * 
	 * @return
	 */
	public String toMerCreditMoneyType() {
		moneyTypeList = this.commonService
				.list("from InternationalMerchantCurrency im where im.merchanId = "
						+ merid);
		this.getLoaction().setReload(true);
		return SUCCESS;
	}

	public Long getMerid() {
		return merid;
	}

	public void setMerid(Long merid) {
		this.merid = merid;
	}

	public List<InternationalMerchantCurrency> getMoneyTypeList() {
		return moneyTypeList;
	}

	public void setMoneyTypeList(
			List<InternationalMerchantCurrency> moneyTypeList) {
		this.moneyTypeList = moneyTypeList;
	}

	public InternationalMerchantCurrency getInternationalMerchantCurrency() {
		return internationalMerchantCurrency;
	}

	public void setInternationalMerchantCurrency(
			InternationalMerchantCurrency internationalMerchantCurrency) {
		this.internationalMerchantCurrency = internationalMerchantCurrency;
	}

}
