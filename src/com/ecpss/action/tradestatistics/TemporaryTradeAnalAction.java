package com.ecpss.action.tradestatistics;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ecpss.action.BaseAction;
import com.ecpss.model.log.SystemLog;
import com.ecpss.util.AES;
import com.ecpss.util.AddressIpInfo;
import com.ecpss.util.GetAddressByIp;
import com.ecpss.web.PageInfo;

public class TemporaryTradeAnalAction extends BaseAction {
	private String cardNo6;
	private String cardNo;
	private String email;
	private String ip;
	private List tradeDetial;
	private AddressIpInfo ipInfo;
	//根据卡号获取分析数据
	public String getCardNoAnal(){
		StringBuffer sb = new StringBuffer();
		sb.append("select ti,m.merno,ci from InternationalTradeinfo ti," +
						"InternationalMerchant m," +
						"InternationalCardholdersInfo ci " +
				"where ti.merchantId=m.id " +     //交易表商户ID
				"and ci.tradeId=ti.id ");       //商户通道的通道
		if(StringUtils.isNotBlank(cardNo6)){
			sb.append(" and ci.cardNo6='"+AES.setCarNo(cardNo6.trim())+"'");
		}
		if(StringUtils.isNotBlank(cardNo)){
			sb.append(" and ci.cardNo='"+AES.setCarNo(cardNo.trim())+"'");
		}
		if(StringUtils.isNotBlank(email)){
			sb.append(" and lower(ci.email)='"+email.trim().toLowerCase()+"'");
		}
		if(StringUtils.isNotBlank(ip)){
			String ip1[]=ip.split(",");
			sb.append(" and ci.ip='"+ip.trim()+"'");
			//调用外部api查询ip信息
//			GetAddressByIp getip=new GetAddressByIp();
//			ipInfo=getip.GetAddressByIp(ip1[0]);
		}
		sb.append(" order by ti.tradeTime desc");
		
		tradeDetial=commonService.list(sb.toString());
		
		return SUCCESS;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List getTradeDetial() {
		return tradeDetial;
	}
	public void setTradeDetial(List tradeDetial) {
		this.tradeDetial = tradeDetial;
	}
	public AddressIpInfo getIpInfo() {
		return ipInfo;
	}
	public void setIpInfo(AddressIpInfo ipInfo) {
		this.ipInfo = ipInfo;
	}
	public String getCardNo6() {
		return cardNo6;
	}
	public void setCardNo6(String cardNo6) {
		this.cardNo6 = cardNo6;
	}

}
