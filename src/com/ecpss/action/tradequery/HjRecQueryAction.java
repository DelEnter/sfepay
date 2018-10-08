package com.ecpss.action.tradequery;

import java.security.MessageDigest;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;
import vpn.HJPayMessage;
import vpn.HJPayUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.action.TemporarySynThread;
import com.ecpss.action.tradestatistics.TemporaryTradInfoAction;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTemporaryTradInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.util.AES;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;

//华金融订单查询 

public class HjRecQueryAction extends BaseAction{
	Logger logger = Logger.getLogger(HjRecQueryAction.class.getName());
	private String orderNo;
	private InternationalTradeinfo tradeinfo;
	private String hjRemark;
	private String tradeId;
	private String hjBilladdress;
	private String hjQueOrderNo;
	private String errMsg;
	public String queryHjPay(){
		if(StringUtils.isNotBlank(orderNo)){
			tradeinfo=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where orderNo='"+orderNo.trim()+"'");
			InternationalCardholdersInfo ic=(InternationalCardholdersInfo) commonService.uniqueResult("from InternationalCardholdersInfo where tradeId='"+tradeinfo.getId()+"'");
			String cardNo=AES.getCarNo(ic.getCardNo());
			String channelId="";
			if("4".equals(cardNo.substring(0, 1))){
				channelId="270";
			}
			if("5".equals(cardNo.substring(0, 1))){
				channelId="271";
			}
			if("3".equals(cardNo.substring(0, 1))){
				channelId="272";
			}
			InternationalTerminalManager tim=(InternationalTerminalManager) commonService.uniqueResult("from InternationalTerminalManager where id='"+channelId+"'");
			logger.info("***********进入hj订单状态查询："+orderNo);
			HJPayUtil HJ=new HJPayUtil();
			HJPayMessage hm=new HJPayMessage();
			hm.setAcctNo(tim.getMerchantNo());
			hm.setAgent_AcctNo(tim.getTerminalNo());
			hm.setOrderID(tradeinfo.getOrderNo());
			hm.setCurrCode("156");
			Double amountAndFee=tradeinfo.getRmbAmount();
			if(tradeinfo.getChannelFee()!=null){
				amountAndFee=amountAndFee*(tradeinfo.getChannelFee()+1.0);
				amountAndFee = (double) (Math.round((double) amountAndFee * 100) / 100.00);
			}
			hm.setAmount((int)(tradeinfo.getRmbAmount()*100)+"");
			String ipstr[]=ic.getIp().split(",");
			if(ipstr.length>1){
				hm.setIpAddress(ipstr[0]);
			}else{
				hm.setIpAddress(ic.getIp());
			}
			hm.setCardPAN(cardNo);
			String jiamiqian="";
			jiamiqian=tim.getHashcode()+ hm.getAcctNo() + hm.getOrderID() + hm.getAmount() + hm.getCurrCode();
			MessageDigest getMd5;
			BASE64Encoder base64E = new BASE64Encoder();
			String value = null;
			try {
				getMd5 = MessageDigest.getInstance("MD5");
				System.out.println(jiamiqian+"的base64md5====="+getMd5.digest(jiamiqian.getBytes("UTF-8")));
				value =  base64E.encode(getMd5.digest(jiamiqian.getBytes("UTF-8")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("加密前："+jiamiqian);
			System.out.println("加密后："+value);
			hm.setHashValue(value);
			hm.setTxnType("05");
			HJ.get(hm);
			logger.info("查询交易结果："+hm.getRes_success());
			if(StringUtils.isNotBlank(hm.getRes_message())){
				hjRemark=hm.getRes_message();
				hjBilladdress=hm.getRes_billaddress();
				hjQueOrderNo=hm.getRes_queOrderNo();
			}
		}
		return SUCCESS;
	}
	public String updateTrade(){
		logger.info("********HJ查询交易订单状态更新************");
		InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, new Long(tradeId));
		InternationalCardholdersInfo ic=(InternationalCardholdersInfo) commonService.uniqueResult("from InternationalCardholdersInfo where tradeId='"+ti.getId()+"'");
		InternationalTemporaryTradInfo tem=(InternationalTemporaryTradInfo) commonService.uniqueResult("from InternationalTemporaryTradInfo where orderNo='"+ti.getOrderNo()+"'");
		String cardNo=AES.getCarNo(ic.getCardNo());
		String channelId="";
		if("4".equals(cardNo.substring(0, 1))){
			channelId="270";
		}
		if("5".equals(cardNo.substring(0, 1))){
			channelId="271";
		}
		if("3".equals(cardNo.substring(0, 1))){
			channelId="272";
		}
		InternationalTerminalManager tim=(InternationalTerminalManager) commonService.uniqueResult("from InternationalTerminalManager where id='"+channelId+"'");
		List<InternationalMerchantChannels> merChannel=this.commonService.list("from InternationalMerchantChannels mc where mc.merchantId='"+ti.getMerchantId()+"' and mc.channelId='"+tim.getChannelId()+"'");
		if(merChannel!=null&&merChannel.size()>0){
			InternationalMerchantChannels mc=merChannel.get(0);
			logger.info("*********更新通道*************"+ti.getOrderNo());
			ti.setTradeChannel(Long.valueOf(mc.getId()));
		}
		ti.setTradeState("1"
				+ ti.getTradeState().substring(1,
						ti.getTradeState().length()));
		ti.setVIPAuthorizationNo(hjQueOrderNo);
		ti.setRemark("Payment Success!");
		commonService.update(ti);
		commonService.delete(tem);
		if("3918".equals((ti.getOrderNo()).substring(0,4))){
			TemporarySynThread ts=new TemporarySynThread("http://www.win4mall.com/OrderAutomatic",ti.getMerchantOrderNo(), "1", hjBilladdress);
			ts.start();
		}else if("1001".equals((ti.getOrderNo()).substring(0,4))||"4136".equals((ti.getOrderNo()).substring(0,4))){
			TemporarySynThread ts=new TemporarySynThread("https://www.xingbill.com/synTradeInfo",ti.getMerchantOrderNo(), "1", "hj"+hjBilladdress);
			ts.start();
		}else{
			EmailInfo emailinfo = new EmailInfo();
			String mailinfo = emailinfo.getPaymentResultEmail(
					ic.getEmail(),
					ti.getTradeAmount(),
					getStates().getCurrencyTypeByNo(
							ti.getMoneyType().intValue()),
							ti.getTradeUrl(), ti.getTradeTime(),
							hjBilladdress, ti.getMerchantOrderNo(),
					ti.getOrderNo());
			try {
				CCSendMail.setSendMail(ic.getEmail(), mailinfo,
						"sfepay@sfepay.com");
			}catch(Exception e){
				e.printStackTrace();
				}
		}
		errMsg="更新成功！";
		orderNo="";
		queryHjPay();
		return SUCCESS;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public InternationalTradeinfo getTradeinfo() {
		return tradeinfo;
	}
	public void setTradeinfo(InternationalTradeinfo tradeinfo) {
		this.tradeinfo = tradeinfo;
	}
	public String getHjRemark() {
		return hjRemark;
	}
	public void setHjRemark(String hjRemark) {
		this.hjRemark = hjRemark;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getHjBilladdress() {
		return hjBilladdress;
	}
	public void setHjBilladdress(String hjBilladdress) {
		this.hjBilladdress = hjBilladdress;
	}
	public String getHjQueOrderNo() {
		return hjQueOrderNo;
	}
	public void setHjQueOrderNo(String hjQueOrderNo) {
		this.hjQueOrderNo = hjQueOrderNo;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	} 
	
}
