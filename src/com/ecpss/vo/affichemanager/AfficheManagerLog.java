package com.ecpss.vo.affichemanager;

import java.util.Date;

public class AfficheManagerLog {

	private Long id; // 数据表id 自增

	private String afficheContext; // 公告内容

	private Date affichedate; // 公告时间

	private String url; // 公告链接

	private String afficheContextafter; // 记录修改后的公告内容

	private String urlafter; // 记录修改后的链接

	private Date affichedateafter; // 记录公告修改后的时间

	public String getSaveAfficheLog() {
		StringBuffer sb = new StringBuffer();
		if (this.afficheContext != null && !(this.afficheContext.equals(""))) {
			sb.append("公告内容:" + this.afficheContext + "\r\n");
		}
		if (this.affichedate != null && !(this.affichedate.equals(""))) {
			sb.append("公告时间:" + this.affichedate + "\r\n");
		}
		if (this.url != null && !(this.url.equals(""))) {
			sb.append("链接地址:" + this.url + "\r\n");
		}
		System.out.println(sb);
		return sb.toString();
	}

	/**
	 * 记录修改前后的状态
	 * @return
	 */
	public String getUpdateAfficheLog() {
		StringBuffer sb = new StringBuffer();
		if (this.afficheContext != null
				&& this.afficheContext != this.afficheContextafter) {
			sb.append("公告内容:" + this.afficheContext + "------------>"
					+ this.afficheContextafter + "\r\n");
		}
		if (this.affichedate != null && !(this.affichedate.equals(""))) {
			sb.append("公告时间:" + this.affichedate + "------------>"
					+ this.affichedateafter + "\r\n");
		}
		if (this.url != null && !(this.url.equals(""))) {
			sb.append("公告链接:" + this.url + "------------>"
					+ this.urlafter + "\r\n");
		}
		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 记录删除的网址
	 * @return
	 */
	public String getDeleteAfficheLog() {
		StringBuffer sb = new StringBuffer();
		if (this.afficheContext != null && !(this.afficheContext.equals(""))) {
			sb.append("公告内容:" + this.afficheContext + "\r\n");
		}
		if (this.affichedate!= null && !(this.affichedate.equals(""))) {
			sb.append("公告时间:" + this.affichedate + "\r\n");
		}
		if (this.url != null && !(this.url.equals(""))) {
			sb.append("公告链接:" + this.url + "\r\n");
		}
		System.out.println(sb);
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAfficheContext() {
		return afficheContext;
	}

	public void setAfficheContext(String afficheContext) {
		this.afficheContext = afficheContext;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAfficheContextafter() {
		return afficheContextafter;
	}

	public void setAfficheContextafter(String afficheContextafter) {
		this.afficheContextafter = afficheContextafter;
	}

	public String getUrlafter() {
		return urlafter;
	}

	public void setUrlafter(String urlafter) {
		this.urlafter = urlafter;
	}

	public Date getAffichedate() {
		return affichedate;
	}

	public void setAffichedate(Date affichedate) {
		this.affichedate = affichedate;
	}

	public Date getAffichedateafter() {
		return affichedateafter;
	}

	public void setAffichedateafter(Date affichedateafter) {
		this.affichedateafter = affichedateafter;
	}

}
