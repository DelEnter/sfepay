package com.ecpss.action.merchant;

import java.util.List;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalWebchannels;

/**
 * 商户网址action
 * 
 * @author huhongguang
 * 
 */
public class MerchantWebSiteAction extends BaseAction {

	private String merno; // 商户号

	private List<InternationalWebchannels> internationalWebchannelsList; // 存放商户网址的集合

	/**
	 * 去商户网址查询页面
	 * 
	 * @return
	 */
	public String toMerchantWebSite() {
		return SUCCESS;
	}

	/**
	 * 根据商户id查询商户网址
	 * 
	 * @return
	 */
	public String findMerchantWebSiteByMerchantId() {
		if (merno != null && !merno.trim().equals("")) {
			internationalWebchannelsList = this.commonService
					.list("from com.ecpss.model.shop.InternationalWebchannels i where "
							+ "i.merchanid=(select f.id from com.ecpss.model.shop.InternationalMerchant f where f.merno = "
							+ merno + ")");
			System.out.println(internationalWebchannelsList.size());
		}
		return SUCCESS;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public List<InternationalWebchannels> getInternationalWebchannelsList() {
		return internationalWebchannelsList;
	}

	public void setInternationalWebchannelsList(
			List<InternationalWebchannels> internationalWebchannelsList) {
		this.internationalWebchannelsList = internationalWebchannelsList;
	}

}
