package com.ecpss.vo.websitesetting;

import java.util.Date;

/**
 * 记录网址设置日志
 * 
 * @author huhongguang
 * 
 */
public class InternationalWebSiteLog {

	private Long Id;

	private Long merchanid;

	private String website;

	private String tradeWebsite;

	private String channels;

	private Date executetime;

	private String operator;

	private String remark;

	private Long merchanidafter;

	private String websiteafter;

	private String tradeWebsiteafter;

	private String channelsafter;

	private Date executetimeafter;

	private String operatorafter;

	private String remarkafter;

	private String merno; // 商户号

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	/**
	 * 记录修改前后的网址记录
	 * 
	 * @return
	 */
	public String getUpdateWebSiteLog() {
		StringBuffer sb = new StringBuffer("配置的商户为：" + this.merno + "\r\n");
		if (this.website != null &&!(this.website.equals(this.websiteafter))) {
			sb.append("网址:" + this.website + "------------>"
					+ this.websiteafter + "\r\n");
		}
		if (this.tradeWebsiteafter != null && !(this.tradeWebsite.equals(this.tradeWebsiteafter))) {
			sb.append("交易网址:" + this.tradeWebsite + "------------>"
					+ this.tradeWebsiteafter + "\r\n");
		}
		if (this.executetime != null && !(this.executetime.equals(this.executetimeafter))) {
			sb.append("绑定日期:" + this.executetime + "------------>"
					+ this.executetimeafter + "\r\n");
		}
		if (this.operator != null && !(this.operator.equals(this.operatorafter))) {
			sb.append("操作员:" + this.operator + "------------>"
					+ this.operatorafter + "\r\n");
		}
		if (this.remark != null &&!(this.remark.equals(this.remarkafter))) {
			sb.append("备注:" + this.remark + "------------>" + this.remarkafter
					+ "\r\n");
		}

		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 记录添加的网址
	 * @return
	 */
	public String getSaveWebSiteLog() {
		StringBuffer sb = new StringBuffer("配置的商户为：" + this.merno + "\r\n");
		if (this.website != null && !(this.website.equals(""))) {
			sb.append("网址:" + this.website + "\r\n");
		}
		if (this.tradeWebsite!= null && !(this.tradeWebsite.equals(""))) {
			sb.append("交易网址:" + this.tradeWebsite + "\r\n");
		}
		if (this.executetime != null && !(this.executetime.equals(""))) {
			sb.append("绑定日期:" + this.executetime + "\r\n");
		}
		if (this.operator != null && !(this.operator.equals(""))) {
			sb.append("操作员:" + this.operator + "\r\n");
		}
		if (this.remark != null && !(this.remark.equals(""))) {
			sb.append("备注:" + this.remark + "\r\n");
		}
		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 记录删除的网址
	 * @return
	 */
	public String getDeleteWebSiteLog() {
		StringBuffer sb = new StringBuffer("配置的商户为：" + this.merno + "\r\n");
		if (this.website != null && !(this.website.equals(""))) {
			sb.append("网址:" + this.website + "\r\n");
		}
		if (this.tradeWebsite!= null && !(this.tradeWebsite.equals(""))) {
			sb.append("交易网址:" + this.tradeWebsite + "\r\n");
		}
		if (this.executetime != null && !(this.executetime.equals(""))) {
			sb.append("绑定日期:" + this.executetime + "\r\n");
		}
		if (this.operator != null && !(this.operator.equals(""))) {
			sb.append("操作员:" + this.operator + "\r\n");
		}
		if (this.remark != null && !(this.remark.equals(""))) {
			sb.append("备注:" + this.remark + "\r\n");
		}
		System.out.println(sb);
		return sb.toString();
	}
	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getMerchanid() {
		return merchanid;
	}

	public void setMerchanid(Long merchanid) {
		this.merchanid = merchanid;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTradeWebsite() {
		return tradeWebsite;
	}

	public void setTradeWebsite(String tradeWebsite) {
		this.tradeWebsite = tradeWebsite;
	}

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public Date getExecutetime() {
		return executetime;
	}

	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getMerchanidafter() {
		return merchanidafter;
	}

	public void setMerchanidafter(Long merchanidafter) {
		this.merchanidafter = merchanidafter;
	}

	public String getWebsiteafter() {
		return websiteafter;
	}

	public void setWebsiteafter(String websiteafter) {
		this.websiteafter = websiteafter;
	}

	public String getTradeWebsiteafter() {
		return tradeWebsiteafter;
	}

	public void setTradeWebsiteafter(String tradeWebsiteafter) {
		this.tradeWebsiteafter = tradeWebsiteafter;
	}

	public String getChannelsafter() {
		return channelsafter;
	}

	public void setChannelsafter(String channelsafter) {
		this.channelsafter = channelsafter;
	}

	public Date getExecutetimeafter() {
		return executetimeafter;
	}

	public void setExecutetimeafter(Date executetimeafter) {
		this.executetimeafter = executetimeafter;
	}

	public String getOperatorafter() {
		return operatorafter;
	}

	public void setOperatorafter(String operatorafter) {
		this.operatorafter = operatorafter;
	}

	public String getRemarkafter() {
		return remarkafter;
	}

	public void setRemarkafter(String remarkafter) {
		this.remarkafter = remarkafter;
	}

}
