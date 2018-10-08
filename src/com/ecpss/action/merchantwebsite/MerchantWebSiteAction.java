package com.ecpss.action.merchantwebsite;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.log.SystemLog;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalWebchannels;
import com.ecpss.service.common.CommonService;
import com.ecpss.vo.websitesetting.InternationalWebSiteLog;
import com.ecpss.web.PageInfo;

/**
 * 商户网址
 * 
 * @author huhongguang
 * 
 * 
 * 
 */
public class MerchantWebSiteAction extends BaseAction {

	@Qualifier("commonService")
	private CommonService commonService;
	private String hql;
	private PageInfo info;

	private Long merid; // 商户id

	private Long iid;

	private String merno;

	private InternationalWebchannels website = new InternationalWebchannels(); // 网址实体对象

	private InternationalMerchant merchant = new InternationalMerchant(); // 商户实体
	/** 商户网址实体对象 */
	public List<InternationalWebchannels> merchantWebSiteList;
	//网站分类
	private List webSiteTypeList;
	private String webSiteType;

	/**
	 * 根据商户id查询网址（保留）
	 * 
	 * @return
	 */
	public String findMerchantWebSiteById1() {
		merchantWebSiteList = this.commonService
				.list("select iw,im from InternationalWebchannels iw,InternationalMerchant im where iw.merchanid= '"
						+ merid + "' and im.id= '" + merid + "'   ");
		this.getLoaction().setReload(true);
		return "success";
	}

	/**
	 * 跳转添加网址,根据商户号ID(保留)
	 * 
	 * @return
	 */
	public String toAddMerchantWebSite1() {
		merchant = (InternationalMerchant) this.commonService.load(
				InternationalMerchant.class, merid);
		System.out.println("商户号: " + merchant.getMerno());
		this.merno = merchant.getMerno().toString();
		this.getLoaction().setReload(true);
		return SUCCESS;
	}

	/**
	 * 新增网址(保留)
	 * 
	 * @return
	 */
	public String addMerchantWebSite1() {
		website.setMerchanid(merid);
		website.setExecutetime(new Date());
		website.setOperator(getUserBean().getUserName());
		website.setIsblack("0");//0正常交易1,禁止交易
		this.commonService.save(website);
		// 记录更新前的状态
		iws.setWebsite(website.getWebsite());
		iws.setTradeWebsite(website.getTradeWebsite());
		iws.setExecutetime(website.getExecutetime());
		iws.setOperator(website.getOperator());
		iws.setRemark(website.getRemark());
		iws.setMerno(merno);

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("1");
		sl.setRemarks(iws.getSaveWebSiteLog());
		sl.setRescReow("新增商户网址");
		this.commonService.save(sl);
		this.messageAction = "新增网址成功";
		return this.OPERATE_SUCCESS;
	}

	/**
	 * 跳转到网址修改页面(保留)
	 * 
	 * @return
	 */
	public String toUpdateMerchantWebSite1() {
		website = (InternationalWebchannels) this.commonService.load(
				InternationalWebchannels.class, iid);
		merchant = (InternationalMerchant) this.commonService.load(
				InternationalMerchant.class, website.getMerchanid());
		String hql="select distinct(t.webSiteType) from InternationalWebchannels t where t.webSiteType is not null order by t.webSiteType asc";
		webSiteTypeList=commonService.list(hql);
		return SUCCESS;
	}

	/**
	 * 修改网址信息(保留)
	 * 
	 * @return
	 */

	public String updateMerchantWebSite1() {
		// 创建日志实体对象 sdf
		SystemLog sl = new SystemLog();

		// 创建商户网址vo 记录操作前的状态
		InternationalWebSiteLog iws = new InternationalWebSiteLog();

		System.out.println("update web site id ;+++" + iid);
		InternationalWebchannels iw = (InternationalWebchannels) this.commonService
				.load(InternationalWebchannels.class, iid);

		// 记录更新前的状态
		iws.setWebsite(iw.getWebsite());
		iws.setTradeWebsite(iw.getTradeWebsite());
		iws.setExecutetime(iw.getExecutetime());
		iws.setOperator(iw.getOperator());
		iws.setRemark(iw.getRemark());
		iws.setMerno(merno);

		iw.setWebsite(website.getWebsite());
		iw.setTradeWebsite(website.getTradeWebsite());
		iw.setExecutetime(new Date());
		iw.setOperator(getUserBean().getUserName());
		iw.setIsblack(website.getIsblack());//添加对是否是黑交易网址的更新
		iw.setWebSiteType(webSiteType);
		// iw.setRemark(website.getRemark());
		this.commonService.update(iw);

		// 记录更新后的状态
		iws.setWebsiteafter(iw.getWebsite());
		iws.setTradeWebsiteafter(iw.getTradeWebsite());
		iws.setExecutetimeafter(iw.getExecutetime());
		iws.setOperatorafter(iw.getOperator());
		iws.setRemarkafter(iw.getRemark());

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("2");
		sl.setRemarks(iws.getUpdateWebSiteLog());
		sl.setRescReow("修改商户网址");
		this.commonService.save(sl);

		// 记录更新后的状态
		this.messageAction = "修改成功";
		this.getLoaction().setReload(true);
		return this.OPERATE_SUCCESS;
	}

	// 删除网址(保留)
	public String deleteWebSite1() {
		website = (InternationalWebchannels) this.commonService.load(
				InternationalWebchannels.class, iid);

		merchant = (InternationalMerchant) this.commonService.load(
				InternationalMerchant.class, website.getMerchanid());
		this.merno = merchant.getMerno().toString();

		// 记录更新前的状态
		iws.setWebsite(website.getWebsite());
		iws.setTradeWebsite(website.getTradeWebsite());
		iws.setExecutetime(website.getExecutetime());
		iws.setOperator(website.getOperator());
		iws.setRemark(website.getRemark());
		iws.setMerno(merno);

		this.commonService.delete(website);

		// 记录更新后的状态
		iws.setWebsiteafter(website.getWebsite());
		iws.setTradeWebsiteafter(website.getTradeWebsite());
		iws.setExecutetimeafter(website.getExecutetime());
		iws.setOperatorafter(website.getOperator());
		iws.setRemarkafter(website.getRemark());

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("3");
		sl.setRemarks(iws.getDeleteWebSiteLog());
		sl.setRescReow("删除商户网址");
		this.commonService.save(sl);
		this.messageAction = "删除网址成功";
		return this.OPERATE_SUCCESS;

	}

	/**
	 * 根据商户id查询网址
	 * 
	 * @return
	 */
	public String findMerchantWebSiteById() {
		merchantWebSiteList = this.commonService
				.list("select iw,im from InternationalWebchannels iw,InternationalMerchant im where iw.merchanid= '"
						+ merid + "' and im.id= '" + merid + "'   ");
		this.getLoaction().setReload(true);
		return "success";
	}

	/**
	 * 去网址页面
	 * 
	 * @return
	 */
	public String toMerchantWebSite() {
		return SUCCESS;
	}

	/**
	 * 跳转添加网址,根据商户号ID
	 * 
	 * @return
	 */
	public String toAddMerchantWebSite() {
		merchant = (InternationalMerchant) this.commonService.load(
				InternationalMerchant.class, merid);
		System.out.println("商户号: " + merchant.getMerno());
		this.merno = merchant.getMerno().toString();
		this.getLoaction().setReload(true);
		return SUCCESS;
	}

	/**
	 * 新增网址
	 * 
	 * @return
	 */
	public String addMerchantWebSite() {
		website.setMerchanid(merid);
		website.setExecutetime(new Date());
		website.setOperator(getUserBean().getUserName());
		website.setIsblack("0");//0正常交易1,禁止交易
		this.commonService.save(website);
		// 记录更新前的状态
		iws.setWebsite(website.getWebsite());
		iws.setTradeWebsite(website.getTradeWebsite());
		iws.setExecutetime(website.getExecutetime());
		iws.setOperator(website.getOperator());
		iws.setRemark(website.getRemark());
		iws.setMerno(merno);

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("1");
		sl.setRemarks(iws.getSaveWebSiteLog());
		sl.setRescReow("新增商户网址");
		this.commonService.save(sl);
		this.messageAction = "新增网址成功";
		return this.OPERATE_SUCCESS;
	}

	/**
	 * 跳转到网址修改页面
	 * 
	 * @return
	 */
	public String toUpdateMerchantWebSite() {
		website = (InternationalWebchannels) this.commonService.load(
				InternationalWebchannels.class, iid);
		merchant = (InternationalMerchant) this.commonService.load(
				InternationalMerchant.class, website.getMerchanid());
		// merchantWebSiteList = this.commonService
		// .list("select iw,im from InternationalWebchannels
		// iw,InternationalMerchant im where iw.merchanid= '"
		// + merid + "' and iw.id= '" + iid + "' ");
		return SUCCESS;
	}

	/**
	 * 修改网址信息
	 * 
	 * @return
	 */
	// 创建日志实体对象
	SystemLog sl = new SystemLog();

	// 创建商户网址vo 记录操作前的状态
	InternationalWebSiteLog iws = new InternationalWebSiteLog();

	public String updateMerchantWebSite() {
		System.out.println("update web site id ;+++" + iid);
		InternationalWebchannels iw = (InternationalWebchannels) this.commonService
				.load(InternationalWebchannels.class, iid);

		// 记录更新前的状态
		iws.setWebsite(iw.getWebsite());
		iws.setTradeWebsite(iw.getTradeWebsite());
		iws.setExecutetime(iw.getExecutetime());
		iws.setOperator(iw.getOperator());
		iws.setRemark(iw.getRemark());
		iws.setMerno(merno);

		iw.setWebsite(website.getWebsite());
		iw.setTradeWebsite(website.getTradeWebsite());
		//iw.setIsblack(website.getIsblack());//新增
		iw.setExecutetime(new Date());
		iw.setOperator(getUserBean().getUserName());
		// iw.setRemark(website.getRemark());
		this.commonService.update(iw);

		// 记录更新后的状态
		iws.setWebsiteafter(iw.getWebsite());
		iws.setTradeWebsiteafter(iw.getTradeWebsite());
		iws.setExecutetimeafter(iw.getExecutetime());
		iws.setOperatorafter(iw.getOperator());
		iws.setRemarkafter(iw.getRemark());

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("2");
		sl.setRemarks(iws.getUpdateWebSiteLog());
		sl.setRescReow("修改商户网址");
		this.commonService.save(sl);

		// 记录更新后的状态
		this.messageAction = "修改成功";
		this.getLoaction().setReload(true);
		return this.OPERATE_SUCCESS;
	}

	// 删除网址
	public String deleteWebSite() {
		website = (InternationalWebchannels) this.commonService.load(
				InternationalWebchannels.class, iid);

		merchant = (InternationalMerchant) this.commonService.load(
				InternationalMerchant.class, website.getMerchanid());
		this.merno = merchant.getMerno().toString();

		// 记录更新前的状态
		iws.setWebsite(website.getWebsite());
		iws.setTradeWebsite(website.getTradeWebsite());
		iws.setExecutetime(website.getExecutetime());
		iws.setOperator(website.getOperator());
		iws.setRemark(website.getRemark());
		iws.setMerno(merno);

		this.commonService.delete(website);

		// 记录更新后的状态
		iws.setWebsiteafter(website.getWebsite());
		iws.setTradeWebsiteafter(website.getTradeWebsite());
		iws.setExecutetimeafter(website.getExecutetime());
		iws.setOperatorafter(website.getOperator());
		iws.setRemarkafter(website.getRemark());

		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("3");
		sl.setRemarks(iws.getDeleteWebSiteLog());
		sl.setRescReow("删除商户网址");
		this.commonService.save(sl);
		this.messageAction = "删除网址成功";
		return this.OPERATE_SUCCESS;

	}
	
	
	public String MerchantWebSite1() {
		String sql = "SELECT t.TRADEURL,w.MERCHANID,w.ISBLACK from INTERNATIONAL_TRADEINFO t,"
				+ " INTERNATIONAL_WEBCHANNELS w where t.TRADEURL = w.TRADEWEBSITE and w.ISBLACK = 0;";
		return this.OPERATE_SUCCESS;
	}
	
	

	public Long getMerid() {
		return merid;
	}

	public void setMerid(Long merid) {
		this.merid = merid;
	}

	public List<InternationalWebchannels> getMerchantWebSiteList() {
		return merchantWebSiteList;
	}

	public void setMerchantWebSiteList(
			List<InternationalWebchannels> merchantWebSiteList) {
		this.merchantWebSiteList = merchantWebSiteList;
	}

	public InternationalWebchannels getWebsite() {
		return website;
	}

	public void setWebsite(InternationalWebchannels website) {
		this.website = website;
	}

	public InternationalMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

	public SystemLog getSl() {
		return sl;
	}

	public void setSl(SystemLog sl) {
		this.sl = sl;
	}

	public InternationalWebSiteLog getIws() {
		return iws;
	}

	public void setIws(InternationalWebSiteLog iws) {
		this.iws = iws;
	}

	public List getWebSiteTypeList() {
		return webSiteTypeList;
	}

	public void setWebSiteTypeList(List webSiteTypeList) {
		this.webSiteTypeList = webSiteTypeList;
	}

	public String getWebSiteType() {
		return webSiteType;
	}

	public void setWebSiteType(String webSiteType) {
		this.webSiteType = webSiteType;
	}
}
