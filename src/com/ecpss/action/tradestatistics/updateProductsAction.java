package com.ecpss.action.tradestatistics;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;
import vpn.CaibaoMessage;
import vpn.CaibaoUtil;
import vpn.GooPayMessage;
import vpn.GooPayUtil;
import vpn.HJPayMessage;
import vpn.HJPayUtil;
import vpn.HJWPayMessage;
import vpn.HJWPayUtil;
import vpn.HRPayMessage;
import vpn.HRPayUtil;
import vpn.MasaPayMessage;
import vpn.MasaPayUtil;
import vpn.VpnUtil;
import vpn.VpnUtil_Moto;
import vpn.YoungPayMessage;
import vpn.YoungPayUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.action.TemporarySynThread;
import com.ecpss.action.pay.util.MaxMindExample;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTemporaryTradInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.risk.InternationalRiskItems;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.util.AES;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.MD5;
import com.ecpss.util.StatusUtil;
import com.ecpss.web.PageInfo;

public class updateProductsAction extends BaseAction {
	Logger logger = Logger.getLogger(updateProductsAction.class.getName());
	private PageInfo info = new PageInfo();
	private MaxMindExample exam = new MaxMindExample();
	private HashMap h = new HashMap();
	String bankCountry = null;
	private String orderNo;
	private String startDate;
	private String endDate;
	private String channelName;
	private String ids;
	private String msg;
	private String orderId;
	private String statusType;//重跑页面类型 0 重跑页面   1复审重跑页面   2重跑失败复审
	private String merchantNo;
	private String remark;
	private String qip;
	private String qemail;
	private List tempDetail=new ArrayList();
	private String tranUrl;
	private String tradtime;
	private String isRisk;
	private String webType;
	private String openTime;
	private String tradeNum;
	private String updateproduct;

	
	public String toTemporaryInfo(){
		StringBuffer sb=new StringBuffer("select tt.id,tt.orderNo,it.tradeAmount,it.moneyType,it.rmbAmount,tt.createTime,ic,it.tradeUrl,tt.remark,it.merchantOrderNo,ic.productInfo,ic.product from InternationalTemporaryTradInfo tt,InternationalTradeinfo it,InternationalCardholdersInfo ic where tt.orderNo=it.orderNo and it.id=ic.tradeId ");
		if(StringUtils.isNotBlank(orderId)){
			sb.append(" and tt.id='"+orderId+"'");
		}
		if(StringUtils.isNotBlank(merchantNo)){
			sb.append(" and substr(tt.orderNo,1,4)='"+merchantNo+"'");
		}
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and tt.orderNo='"+orderNo+"'");
		}
		if(StringUtils.isNotBlank(startDate)){
			sb.append(" and tt.createTime>=to_date('"+startDate+" 00:00:00','yyyy-MM-dd hh24:mi:ss') ");
		}
		if(StringUtils.isNotBlank(endDate)){
			sb.append(" and tt.createTime<=to_date('"+endDate+" 23:59:59','yyyy-MM-dd hh24:mi:ss') ");
		}
		if(StringUtils.isNotBlank(qip)){
			sb.append(" and ic.ip like '%"+qip+"%'");
		}
		if(StringUtils.isNotBlank(qemail)){
			sb.append(" and ic.email like '%"+qemail+"%'");
		}
		sb.append(" order by tt.createTime desc");
		info = commonService.listQueryResultByHql(sb.toString(), info);
		return SUCCESS;
	}

	public String updateproducts(){
		if(StringUtils.isNotBlank(ids)){
			String[] id=ids.split(",");
			for(int i=0;i<id.length;i++){
				InternationalTemporaryTradInfo tem=(InternationalTemporaryTradInfo) commonService.uniqueResult("from InternationalTemporaryTradInfo where id='"+id[i]+"'");
				InternationalTradeinfo tra=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where orderNo='"+tem.getOrderNo()+"'");
				InternationalCardholdersInfo card = (InternationalCardholdersInfo) commonService.uniqueResult("from InternationalCardholdersInfo cc where cc.tradeId='"+tra.getId()+"'");				
				card.setProduct(updateproduct);
				commonService.update(card);
			}
		}
		toTemporaryInfo();
		msg="已修改产品信息！";
		return SUCCESS;
	}	
	
	public String getUpdateproduct() {
		return updateproduct;
	}

	public void setUpdateproduct(String updateproduct) {
		this.updateproduct = updateproduct;
	}

	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public MaxMindExample getExam() {
		return exam;
	}
	public void setExam(MaxMindExample exam) {
		this.exam = exam;
	}
	public String getBankCountry() {
		return bankCountry;
	}
	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getQip() {
		return qip;
	}
	public void setQip(String qip) {
		this.qip = qip;
	}
	public String getQemail() {
		return qemail;
	}
	public void setQemail(String qemail) {
		this.qemail = qemail;
	}
	public List getTempDetail() {
		return tempDetail;
	}
	public void setTempDetail(List tempDetail) {
		this.tempDetail = tempDetail;
	}
	public String getTranUrl() {
		return tranUrl;
	}
	public void setTranUrl(String tranUrl) {
		this.tranUrl = tranUrl;
	}
	public String getTradtime() {
		return tradtime;
	}
	public void setTradtime(String tradtime) {
		this.tradtime = tradtime;
	}
	public String getIsRisk() {
		return isRisk;
	}
	public void setIsRisk(String isRisk) {
		this.isRisk = isRisk;
	}
	public String getWebType() {
		return webType;
	}
	public void setWebType(String webType) {
		this.webType = webType;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getTradeNum() {
		return tradeNum;
	}
	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}

}
