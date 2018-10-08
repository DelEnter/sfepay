package com.ecpss.action.mersetting;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.cardtype.InternationalCreditCardType;
import com.ecpss.model.cardtype.InternationalMerCreditCard;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.iservice.ChannelService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.vo.UserBean;

public class MerRelationCardTypeAction extends BaseAction {
	
	@Autowired
	@Qualifier("channelService")
	private ChannelService channelService;

	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	
	private Long merid;  //商户id
	private Long merno;  //商户号
	private Long channelId;
	private String onoff;
	private List merCreditCardList;
	private InternationalMerchant merchant;
	private List<InternationalChannels> channelList;
	private List<InternationalCreditCardType> creditCardList;
	
	private InternationalMerCreditCard mcc;
	
	/**
	 * 卡通通道id
	 */
	private Long mercreditcardId;
	
	/**
	 * 跳转到商户卡种管理页面
	 * @return
	 */
	public String toMerCreditCard(){
		if(merno!=null){
			String hlq="select m.id from InternationalMerchant m where m.merno="+merno;
			merid = (Long) this.commonService.uniqueResult(hlq);
			merCreditCardList = shopManagerService.getMerCreditCardManagerList(merid);
		}else{
			merCreditCardList = shopManagerService.getMerCreditCardManagerList(merid);
		}
		this.getLoaction().setReload(true);
		return SUCCESS;
	}

	/**
	 * 新增商户通道卡种管理
	 * @return
	 */
	public String toAddMerCreditCard(){
		merchant = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, merid);
		channelList = channelService.getChannelByMerid(merid);
		String hql="from InternationalCreditCardType";
		creditCardList = this.commonService.list(hql);
		return SUCCESS;
	}
	
	/**
	 * 添加卡种
	 * @return
	 */
	public String addMerCreditCard(){
		
		String hql = "select mc.id from InternationalMerchantChannels mc " +
				"where mc.merchantId="+merid + " " +
						"and mc.channelId="+ channelId;
		if(this.commonService.list(hql).size()==0){
			this.messageAction="该商户未添加通道.";
			return this.OPERATE_SUCCESS;
		}
		Long merChannelID = (Long) this.commonService.list(hql).get(0);
		mcc.setMerChannelId(merChannelID);
		mcc.setLastDate(new Date());
		mcc.setLastMan(getUserBean().getUserName());
		mcc.setOnoff("1");
		//如果是修改的话就
		if(mercreditcardId!=null){
			InternationalMerCreditCard modifymcc = (InternationalMerCreditCard) this.commonService.load(InternationalMerCreditCard.class, mercreditcardId);
			modifymcc.setCreditCardId(mcc.getCreditCardId());
			modifymcc.setMerChannelId(mcc.getMerChannelId());
			modifymcc.setLastDate(mcc.getLastDate());
			modifymcc.setLastMan(mcc.getLastMan());
			modifymcc.setOnoff(mcc.getOnoff());
			this.commonService.update(modifymcc);
			this.messageAction="卡种通道修改设置成功";		
			return this.OPERATE_SUCCESS;
		}
			this.commonService.save(mcc);
			this.messageAction="卡种通道设置成功";		
			return this.OPERATE_SUCCESS;
	}
	/**
	 * 修改卡种通道
	 * @return
	 */
	public String toModifykazhong(){
		
		mcc = (InternationalMerCreditCard) this.commonService.load(InternationalMerCreditCard.class, mercreditcardId);
		merchant = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, merid);
		String hql = "select mc.channelId from InternationalMerchantChannels mc where mc.id="+mcc.getMerChannelId();
		channelId = (Long) this.commonService.uniqueResult(hql);
		channelList = channelService.getChannelByMerid(merid);
		String hql1="from InternationalCreditCardType";
		creditCardList = this.commonService.list(hql1);
		return SUCCESS;
	}
	
	
	/**
	 * 打开通道开关
	 * @return
	 */
	public String kazhongkaiguan(){
		if(onoff.equals("1")){
			onoff="0";
			this.messageAction="通道已关闭";
		}else if(onoff.equals("0")){
			onoff="1";
			this.messageAction="通道已开通";
		}
		InternationalMerCreditCard mcc = (InternationalMerCreditCard) this.commonService.load(InternationalMerCreditCard.class, channelId);
		mcc.setOnoff(onoff);
		mcc.setLastDate(new Date());
		mcc.setLastMan(getUserBean().getUserName());
		this.commonService.update(mcc);
		return this.OPERATE_SUCCESS;
	}
	
	
	
	
	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}

	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}

	public Long getMerid() {
		return merid;
	}

	public void setMerid(Long merid) {
		this.merid = merid;
	}

	public List getMerCreditCardList() {
		return merCreditCardList;
	}

	public void setMerCreditCardList(List merCreditCardList) {
		this.merCreditCardList = merCreditCardList;
	}

	public InternationalMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	public List<InternationalChannels> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<InternationalChannels> channelList) {
		this.channelList = channelList;
	}

	public List<InternationalCreditCardType> getCreditCardList() {
		return creditCardList;
	}

	public void setCreditCardList(List<InternationalCreditCardType> creditCardList) {
		this.creditCardList = creditCardList;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public InternationalMerCreditCard getMcc() {
		return mcc;
	}

	public void setMcc(InternationalMerCreditCard mcc) {
		this.mcc = mcc;
	}

	public Long getMerno() {
		return merno;
	}

	public void setMerno(Long merno) {
		this.merno = merno;
	}

	public Long getMercreditcardId() {
		return mercreditcardId;
	}

	public void setMercreditcardId(Long mercreditcardId) {
		this.mercreditcardId = mercreditcardId;
	}
	
}
