package com.ecpss.action.pay;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import sun.misc.BASE64Encoder;
import vpn.HJPayMessage;
import vpn.HJPayUtil;
import vpn.HJWPayMessage;
import vpn.HJWPayUtil;
import vpn.MotoDCCMessage;
import vpn.VpnUtil_Moto;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.HuakuanedException;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.implservice.RefundServiceImpl;
import com.ecpss.service.iservice.RefundService;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.StatusUtil;
import com.ecpss.vo.UserBean;
import com.opensymphony.xwork2.ActionContext;

public class RefundinterAction extends BaseAction {
	Logger logger = Logger.getLogger(RefundinterAction.class.getName());
	@Autowired
	@Qualifier("refundService")
	private RefundService refundService;
	private String tradeNo;
	private String merOrderNo;
	private String refundAmount;
	private String refundNo;
	private String md5Info;
	private String res_orderStatus="00";
	private String md5Key;
	public String merchatRefund(){
		logger.info("tradeNo:"+tradeNo);
		logger.info("refundAmount:"+refundAmount);
		GetBatchNo ut = new GetBatchNo();
		refundNo=ut.getOrderinfo("t");
		logger.info("退款编号:"+refundNo);
		try{
			String hql="from InternationalTradeinfo where substr(tradeState,1,1)='1'";
			if(StringUtils.isNotBlank(tradeNo)){
				hql=hql+" and orderNo='"+tradeNo+"' ";
			}
			if(StringUtils.isNotBlank(merOrderNo)){
				hql=hql+" and merchantOrderNo='"+merOrderNo+"' ";
			}
			InternationalTradeinfo trade=(InternationalTradeinfo) commonService.uniqueResult(hql);
			if(trade==null){
				res_orderStatus="01";
				logger.info("不存在符合条件的交易!");
				return SUCCESS;
			}
			List<InternationalRefundManager> r = refundService.getRefundByTradeId(trade.getId());
			InternationalMerchant mer = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, trade.getMerchantId());
			md5Key=mer.getMd5key();
			BigDecimal tradeAmount=new BigDecimal(trade.getTradeAmount());
			BigDecimal refAmount=new BigDecimal(refundAmount);
			if(tradeAmount.doubleValue()<refAmount.doubleValue()){
				res_orderStatus="02";
				logger.info("退款金额超过交易金额!");
				return SUCCESS;
			}
			if(r.size()>0){
				
				for(InternationalRefundManager rf:r){
					BigDecimal refundNum=new BigDecimal(rf.getRefundAmount());
					tradeAmount=tradeAmount.subtract(refundNum);
				}
				System.out.println(tradeAmount.doubleValue());
				if(tradeAmount.doubleValue()==0.0||tradeAmount.doubleValue()<refAmount.doubleValue()){
					//已经申请过退款
					res_orderStatus="02";
					logger.info("退款金额超过交易金额!");
					return SUCCESS;
				}
			}
			if(trade.getTradeState().substring(2, 3).equals("1")){
				//如果该交易已经拒付,不可以申请退款
				res_orderStatus="03";
				logger.info("交易已经拒付不可以退款!");
				return SUCCESS;
			}
			InternationalExchangerate er = (InternationalExchangerate) this.commonService.load(InternationalExchangerate.class, trade.getTradeRate());
			InternationalRefundManager rm = new InternationalRefundManager();
			rm.setRefundNo(refundNo);
			rm.setApplyDate(new Date());
			rm.setLastDate(new Date());
			rm.setRefundState("2");
			rm.setTradeId(trade.getId());
			rm.setRefundAmount(refAmount.doubleValue());
			rm.setRefundRMBAmount(refAmount.doubleValue()*er.getRate());
			this.commonService.save(rm);
			logger.info("退款成功!");
			}catch(Exception e){
				e.printStackTrace();
				logger.info("系统异常!");
				res_orderStatus="04";
			}
		return SUCCESS;
	}
	public String getMerOrderNo() {
		return merOrderNo;
	}
	public void setMerOrderNo(String merOrderNo) {
		this.merOrderNo = merOrderNo;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getMd5Info() {
		return md5Info;
	}
	public void setMd5Info(String md5Info) {
		this.md5Info = md5Info;
	}
	public String getRes_orderStatus() {
		return res_orderStatus;
	}
	public void setRes_orderStatus(String res_orderStatus) {
		this.res_orderStatus = res_orderStatus;
	}
	public String getMd5Key() {
		return md5Key;
	}
	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public RefundService getRefundService() {
		return refundService;
	}
	public void setRefundService(RefundService refundService) {
		this.refundService = refundService;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
}
