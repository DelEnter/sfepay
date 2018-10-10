package com.ecpss.action.merchant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalIsAuditWeb;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMerchantCurrency;
import com.ecpss.model.shop.InternationalMerchantManager;
import com.ecpss.model.shop.InternationalTradecondition;
import com.ecpss.model.shop.InternationalWebchannels;

/**
 * 商户网址action
 * 
 * @author huhongguang
 * 
 */
public class ShopWebSiteAction extends BaseAction {

	private String merno; // 商户号

	private List<InternationalWebchannels> internationalWebchannelsList; // 存放商户网址的集合

	private Long merid; // 商户id

	/** 商户交易条件实体对象 */
	public List<InternationalTradecondition> internationalTradeconditionList;

	private List<InternationalTradecondition> bigInternationalTradeconditionList;

	/** 商户交易条件 */
	private InternationalTradecondition internationalTradecondition;

	private List<InternationalMerchantCurrency> InternationalMerchantCurrencyList;
	private InternationalIsAuditWeb internationalIsAuditWeb;
	private List<InternationalIsAuditWeb> isWebSite;
	private InternationalWebchannels webChannels;
	private Long isWebSiteId;
	private Long webSiteId;
	private int ival;
	public int getIval() {
		return ival;
	}

	public void setIval(int ival) {
		this.ival = ival;
	}

	private File batchWebFile;
	
	private static int num=1;

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
	/**
	 * 根据商户id查询商户网址
	 * 
	 * @return
	 */
	public String findMerchantWebSiteByMerchant() {
			
			internationalWebchannelsList = this.commonService
					.list("from InternationalWebchannels i where i.merchanid='"
							+ this.getMerchantBean().getMerchantId()+"'");
		return SUCCESS;
	}
	//去商户使用网址修改页面
	public String toUpdateWebSite(){
		num=1;
		webChannels=(InternationalWebchannels) this.commonService.uniqueResult("from InternationalWebchannels where id='"+webSiteId+"'");
		return SUCCESS;
	}
	//商户使用网址修改
	public String updateWebSite(){
		InternationalWebchannels web=(InternationalWebchannels) this.commonService.load(InternationalWebchannels.class, webChannels.getId());
		if(web!=null&&num==1){
		InternationalIsAuditWeb isAuditWeb=new InternationalIsAuditWeb();
		isAuditWeb.setMerchanid(web.getMerchanid());
		isAuditWeb.setTradeWebsite(webChannels.getTradeWebsite().trim());//交易网址
		isAuditWeb.setWebsite(webChannels.getWebsite().trim());//返回网址
		isAuditWeb.setCreatetime(new Date());
		isAuditWeb.setIsAudit("0");
		this.commonService.save(isAuditWeb);
		this.commonService.delete(webChannels);
		}
		toMerchantAuditWebList();
		return SUCCESS;
	}
	//商户使用网址删除
	public String delWebSite(){
		webChannels=(InternationalWebchannels) this.commonService.uniqueResult("from InternationalWebchannels where id='"+webSiteId+"'");
		this.commonService.delete(webChannels);
		findMerchantWebSiteByMerchant();
		return SUCCESS;
	}
	/**
	 * 商户网址审核列表（jiahui create）
	 */
	public String toMerchantAuditWebList(){
		isWebSite=this.commonService.list("from InternationalIsAuditWeb a where a.isAudit!='1' and a.merchanid='"+ this.getMerchantBean().getMerchantId()+"' order by a.createtime desc,a.isAudit asc");
		return SUCCESS;
	}
	//去添加webSite
	public String toMerchantAddWebSite(){
		num=1;
		return SUCCESS;
	}
	//添加
	public String merchantAddWebSite(){
//		String webSite=this.internationalIsAuditWeb.getWebsite().trim();
//		String tradeWebsite=this.internationalIsAuditWeb.getTradeWebsite().trim();
		String tradeWebsite=this.internationalIsAuditWeb.getTradeWebsite().trim();//交易网址
		int i=this.ival;
		String webSite="";
		//拼接返回网址
		switch(i){
		case 1:webSite="http://"+tradeWebsite+"/index.php?main_page=checkout_payresult";//zencart系统
			break;
		case 2:webSite="http://"+tradeWebsite+"/respond.php?code=sfepay";// ecshop系统
			break;
		case 3:webSite="http://"+tradeWebsite+"/sfepay/payment/return/";// magento系统
			break;
		case 4:webSite="http://"+tradeWebsite+"/modules/sfepay/validation.php";//Prestashop系统
			break;
		case 5:webSite="http://"+tradeWebsite+"/index.php?route=payment/sfepay/callback";// Opencart系统
			break;
		case 6:webSite="http://"+tradeWebsite+"/checkout_sfepayresult.php";//oscommerce系统
			break;
		case 7:webSite="http://"+tradeWebsite+"/receive.asp";// asp语言网站
			break;
		case 8:webSite="http://"+tradeWebsite+"/PayResult.aspx";//.net语言网站
			break;
		case 9:webSite="http://"+tradeWebsite+"/PayResult.php";// php语言网站
			break;
		}
		
		if(StringUtils.isNotBlank(webSite)&&StringUtils.isNotBlank(tradeWebsite)&&num==1){
			this.internationalIsAuditWeb.setTradeWebsite(tradeWebsite);//交易网址
			this.internationalIsAuditWeb.setWebsite(webSite);//返回网址
			this.internationalIsAuditWeb.setMerchanid(this.getMerchantBean().getMerchantId());
			this.internationalIsAuditWeb.setCreatetime(new Date());
			this.internationalIsAuditWeb.setIsAudit("0");
			this.commonService.save(internationalIsAuditWeb);
			num++;
		}
//		isWebSite=this.commonService.list("from InternationalIsAuditWeb a where a.merchanid='"+ this.getMerchantBean().getMerchantId()+"'");
		toMerchantAuditWebList();
		return SUCCESS;
	}
	//添加详情列表删除
	public String merchantDelWebSite(){
		if(this.isWebSiteId!=null){
			this.commonService.delete(InternationalIsAuditWeb.class, this.isWebSiteId);
		}
		toMerchantAuditWebList();
		return SUCCESS;
	}
	//去批量上传
	public String toBatchWebUpload(){
		num=1;
		return SUCCESS;
	}
	//上传处理
	public String batchWebUpload(){
		try {
			if(batchWebFile!=null&&num==1){
				Workbook workBook = Workbook.getWorkbook(batchWebFile);
				Sheet sheet=workBook.getSheet(0);
				if("tradeWebsite".equals(sheet.getCell(0,0).getContents().trim())&&"webSite".equals(sheet.getCell(1,0).getContents().trim())){
					int rows=sheet.getRows();
					for (int i=1;i<rows;i++){
						InternationalIsAuditWeb isWebSite=new InternationalIsAuditWeb();
						isWebSite.setTradeWebsite(sheet.getCell(0, i).getContents().trim());
						isWebSite.setWebsite(sheet.getCell(1, i).getContents().trim());
						isWebSite.setMerchanid(this.getMerchantBean().getMerchantId());
						isWebSite.setCreatetime(new Date());
						isWebSite.setIsAudit("0");
						this.commonService.save(isWebSite);
					}
					num++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			toMerchantAuditWebList();
			return SUCCESS;
		}
		toMerchantAuditWebList();
		return SUCCESS;
	}
	/**
	 * @return
	 */
	public String openOrCloseMerchant() {
		String isopen = (String) commonService
				.uniqueResult("select im.isopen from InternationalMerchant im where im.id = '"
						+ merid + "' ");
		System.out.println("是否开通" + isopen);
		if ("1".equals(isopen) || isopen == null) {
			isopen = "0";
			this.messageAction = "商户已关闭";
		} else if ("0".equals(isopen) && isopen != null) {
			// 根据商户Id从商户通道表查询已经开通的通道个数
			Long internationalMerchantChannelsCount = (Long) this.commonService
					.uniqueResult("select count(imc.id) from InternationalMerchantChannels imc where imc.merchantId="
							+ merid + " and imc.onoff='1'");
			if (internationalMerchantChannelsCount == null
					|| internationalMerchantChannelsCount.intValue() == 0) {
				this.messageAction = "商户通道未设置好";
				return OPERATE_SUCCESS;
			}
			// 1.条件设置是否做了
			Long internationalTradeconditionCount = (Long) commonService
					.uniqueResult("select count(it.id) from InternationalTradecondition it where it.merchantId = '"
							+ merid + "' and it.merchantId is not null ");
			if (internationalTradeconditionCount == null
					|| internationalTradeconditionCount.intValue() != 5) {
				messageAction = "商户条件设置未设置好";
				return OPERATE_SUCCESS;
			}
			Long ipCount = (Long) commonService
					.uniqueResult("select count(*) from InternationalTradecondition it where it.merchantId = '"
							+ merid + "' and it.itemno='1'");
			if (ipCount == null || ipCount.intValue() != 1) {
				messageAction = "商户条件设置未设置好(ip未设置好)";
				return OPERATE_SUCCESS;
			}
			Long emailCount = (Long) commonService
					.uniqueResult("select count(*) from InternationalTradecondition it where it.merchantId = '"
							+ merid + "' and it.itemno='2'");
			if (emailCount == null || emailCount.intValue() != 1) {
				messageAction = "商户条件设置未设置好(Email未设置好)";
				return OPERATE_SUCCESS;
			}
			Long cardCount = (Long) commonService
					.uniqueResult("select count(*) from InternationalTradecondition it where it.merchantId = '"
							+ merid + "' and it.itemno='3'");
			if (cardCount == null || cardCount.intValue() != 1) {
				messageAction = "商户条件设置未设置好(cardno未设置好)";
				return OPERATE_SUCCESS;
			}
			Long cookCount = (Long) commonService
					.uniqueResult("select count(*) from InternationalTradecondition it where it.merchantId = '"
							+ merid + "' and it.itemno='4'");
			if (cookCount == null || cookCount.intValue() != 1) {
				messageAction = "商户条件设置未设置好(cookies未设置好)";
				return OPERATE_SUCCESS;
			}
			Long phoneCount = (Long) commonService
					.uniqueResult("select count(*) from InternationalTradecondition it where it.merchantId = '"
							+ merid + "' and it.itemno='5'");
			if (phoneCount == null || phoneCount.intValue() != 1) {
				messageAction = "商户条件设置未设置好(telephone未设置好)";
				return OPERATE_SUCCESS;
			}

			// 2.商户币种
//			Long InternationalMerchantCurrencyCount = (Long) commonService
//					.uniqueResult("select count(imc.id) from InternationalMerchantCurrency imc where imc.merchanId = '"
//							+ merid + "'");
//			if (InternationalMerchantCurrencyCount == null
//					|| InternationalMerchantCurrencyCount.intValue() < 1) {
//				messageAction = "商户币种未设置好";
//				return OPERATE_SUCCESS;
//			}

			// 卡种管理
			List count1 = commonService
					.list("select icct.id from InternationalCreditCardType icct");
			if (count1.size() == 0) {
				messageAction = "卡种管理未设置好";
				return OPERATE_SUCCESS;
			}

			// 根据商户id从商户通道表查询出商户通道id
			List<Long> merChannelId = (List<Long>) commonService
					.list("select imc.id from InternationalMerchantChannels imc where imc.merchantId='"
							+merid+ "' ");
			if (merChannelId.size() == 0) {
				messageAction = "商户通道卡种未设置好";
				return OPERATE_SUCCESS;
			}

			// 3.商户通道卡种
			List creditCardId = commonService
					.list("select imc.creditCardId from InternationalMerCreditCard imc,InternationalMerchantChannels mc" +
							" where imc.merChannelId= mc.id " +
							"and mc.merchantId="+merid+" ");
			if (creditCardId.size() == 0) {
				messageAction = "商户通道卡种未设置好";
				return OPERATE_SUCCESS;
			}
			
			
//			for (int i = 0; i < creditCardId.size(); i++) {
//				Long internationalMerCreditCardCount = (Long) commonService
//						.uniqueResult("select count(it.id) from InternationalMerCreditCard it"
//								+ " where it.merChannelId in ('"
//								+ merChannelId
//								+ "') "
//								+ " and it.creditCardId='"
//								+ creditCardId.get(i)
//								+ "' and it.creditCardId in(select ict.id from InternationalCreditCardType ict)");
//				if (internationalMerCreditCardCount == null
//						|| internationalMerCreditCardCount.intValue() != 1) {
//					messageAction = "商户通道卡种未设置好";
//					return OPERATE_SUCCESS;
//
//				}
//			}

			// 4.管理值
			InternationalMerchantManager imm = (InternationalMerchantManager) commonService
					.uniqueResult("select imm from InternationalMerchantManager imm where imm.merchantId = '"
							+ merid + "' ");
			if (imm == null) {
				messageAction = "商户管理值未设置好";
				return OPERATE_SUCCESS;
			}

			if (imm.getPenQuota() == null || "".equals(imm.getPenQuota())) {
				messageAction = "商户管理值单笔交易限额未设置好";
				return OPERATE_SUCCESS;
			}
			if (imm.getDayQuota() == null || "".equals(imm.getDayQuota())) {
				messageAction = "商户管理值天交易限额未设置好";
				return OPERATE_SUCCESS;
			}
			if (imm.getMonthQuota() == null || "".equals(imm.getMonthQuota())) {
				messageAction = "商户管理值月交易限额未设置好";
				return OPERATE_SUCCESS;
			}
			isopen = "1";
			messageAction = "商户已开通";
		}
		InternationalMerchant im = (InternationalMerchant) commonService.load(
				InternationalMerchant.class, merid);
		im.setIsopen(isopen);
		commonService.update(im);
		return OPERATE_SUCCESS;
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

	public Long getMerid() {
		return merid;
	}

	public void setMerid(Long merid) {
		this.merid = merid;
	}

	public List<InternationalTradecondition> getInternationalTradeconditionList() {
		return internationalTradeconditionList;
	}

	public void setInternationalTradeconditionList(
			List<InternationalTradecondition> internationalTradeconditionList) {
		this.internationalTradeconditionList = internationalTradeconditionList;
	}

	public List<InternationalTradecondition> getBigInternationalTradeconditionList() {
		return bigInternationalTradeconditionList;
	}

	public void setBigInternationalTradeconditionList(
			List<InternationalTradecondition> bigInternationalTradeconditionList) {
		this.bigInternationalTradeconditionList = bigInternationalTradeconditionList;
	}

	public InternationalTradecondition getInternationalTradecondition() {
		return internationalTradecondition;
	}

	public void setInternationalTradecondition(
			InternationalTradecondition internationalTradecondition) {
		this.internationalTradecondition = internationalTradecondition;
	}

	public List<InternationalMerchantCurrency> getInternationalMerchantCurrencyList() {
		return InternationalMerchantCurrencyList;
	}

	public void setInternationalMerchantCurrencyList(
			List<InternationalMerchantCurrency> internationalMerchantCurrencyList) {
		InternationalMerchantCurrencyList = internationalMerchantCurrencyList;
	}

	public InternationalIsAuditWeb getInternationalIsAuditWeb() {
		return internationalIsAuditWeb;
	}

	public void setInternationalIsAuditWeb(
			InternationalIsAuditWeb internationalIsAuditWeb) {
		this.internationalIsAuditWeb = internationalIsAuditWeb;
	}

	public List<InternationalIsAuditWeb> getIsWebSite() {
		return isWebSite;
	}

	public void setIsWebSite(List<InternationalIsAuditWeb> isWebSite) {
		this.isWebSite = isWebSite;
	}

	public Long getIsWebSiteId() {
		return isWebSiteId;
	}

	public void setIsWebSiteId(Long isWebSiteId) {
		this.isWebSiteId = isWebSiteId;
	}

	public Long getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Long webSiteId) {
		this.webSiteId = webSiteId;
	}

	public InternationalWebchannels getWebChannels() {
		return webChannels;
	}

	public void setWebChannels(InternationalWebchannels webChannels) {
		this.webChannels = webChannels;
	}

	public File getBatchWebFile() {
		return batchWebFile;
	}

	public void setBatchWebFile(File batchWebFile) {
		this.batchWebFile = batchWebFile;
	}
}
