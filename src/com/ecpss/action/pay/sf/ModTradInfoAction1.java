package com.ecpss.action.pay.sf;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;
import vpn.HJPayMessage;
import vpn.HJPayUtil;
import vpn.HJWPayMessage;
import vpn.HJWPayUtil;
import vpn.MotoDCCMessage;
import vpn.VpnUtil_Moto;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.DirectCarderInfoAction;
import com.ecpss.model.payment.HuakuanedException;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.GetBatchNo;
import com.ecpss.util.StatusUtil;

public class ModTradInfoAction1 extends BaseAction {
	private String res_orderStatus;
	private String orderNo;
	private String remark;
	private String merOrderNo;
	private String refundAmount;
	Logger logger = Logger.getLogger(ModTradInfoAction.class.getName());
	//同步重跑交易数据
	public String modTradInfoStatus(){
		logger.info("进入同步vip交易数据，订单号："+orderNo+"   状态："+res_orderStatus);
		if(StringUtils.isNotBlank(res_orderStatus)&&StringUtils.isNotBlank(orderNo)){
			InternationalTradeinfo trade=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where orderNo='"+orderNo+"'");
			InternationalCardholdersInfo ic=(InternationalCardholdersInfo) commonService.uniqueResult("from InternationalCardholdersInfo where tradeId='"+trade.getId()+"'");
			if(("1").equals(res_orderStatus)){
				trade.setTradeState("1"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
//				trade.setRemark("Payment Declined!" + tm.getErrorCode());
				trade.setRemark("Payment Success!");
				this.commonService.update(trade);
				if(!"1731".equals((trade.getOrderNo()).substring(0,4))){
					EmailInfo emailinfo = new EmailInfo();
					String mailinfo = emailinfo.getPaymentResultEmail(
							ic.getEmail(),
							trade.getTradeAmount(),
							getStates().getCurrencyTypeByNo(
									trade.getMoneyType().intValue()),
							trade.getTradeUrl(), trade.getTradeTime(),
							remark, trade.getMerchantOrderNo(),
							trade.getOrderNo());
					try {
						CCSendMail.setSendMail(ic.getEmail(), mailinfo,
								"xingbill@xingbill.com");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else if(("0").equals(res_orderStatus)){
				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark("Payment Declined!" + remark);
				this.commonService.update(trade);
			}else if(("-2").equals(res_orderStatus)||("-1").equals(res_orderStatus)){
				trade.setTradeState("2"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				this.commonService.update(trade);
			}
			logger.info("同步数据结束！");
		}
		return SUCCESS;
	}
	//同步退款数据
	public String synRefund(){
		GetBatchNo g = new GetBatchNo();
		String batch = g.getBatchNo();
		InternationalTradeinfo trade=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where merchantOrderNo='"+merOrderNo+"'");
		InternationalExchangerate er = (InternationalExchangerate) this.commonService.load(InternationalExchangerate.class, trade.getTradeRate());
		InternationalCardholdersInfo c = (InternationalCardholdersInfo) this.commonService.uniqueResult("from InternationalCardholdersInfo where tradeId='"+trade.getId()+"'");
		InternationalRefundManager rm = new InternationalRefundManager();
		if (("3982".equals(trade.getTradeChannel())) || ("3983".equals(trade.getTradeChannel())) || ("3984".equals(trade.getTradeChannel()))) {
		      this.logger.info("****进入HJ退款通道******");
		      HJPayUtil HJ = new HJPayUtil();
		      HJPayMessage hm = new HJPayMessage();
		      hm.setAcctNo("huajinrong");
		      hm.setAgent_AcctNo("huajinrong3");
		      hm.setOrderID(trade.getOrderNo());
		      hm.setCurrCode("156");
		      Double amountAndFee = Double.valueOf(Double.parseDouble(this.refundAmount));
		      if (trade.getChannelFee() != null) {
		        amountAndFee = Double.valueOf(amountAndFee.doubleValue() * (trade.getChannelFee().doubleValue() + 1.0D));
		        amountAndFee = Double.valueOf(Math.round(amountAndFee.doubleValue() * 100.0D) / 100.0D);
		      }
		      hm.setAmount((int)(amountAndFee *100)+"");
		      hm.setIpAddress(c.getIp());
		      hm.setCardPAN(getCarNo(c.getCardNo()));
		      String jiamiqian = "";
		      jiamiqian = "dsfhue2568415sfh" + hm.getAcctNo() + hm.getOrderID() + hm.getAmount() + hm.getCurrCode();

		      BASE64Encoder base64E = new BASE64Encoder();
		      String value = null;
		      try {
		        MessageDigest getMd5 = MessageDigest.getInstance("MD5");
		        value = base64E.encode(getMd5.digest(jiamiqian.getBytes("UTF-8")));
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		      System.out.println("加密前：" + jiamiqian);
		      System.out.println("加密后：" + value);
		      hm.setHashValue(value);
		      hm.setTxnID(trade.getVIPAuthorizationNo());
		      hm.setTxnType("03");
		      HJ.get(hm);
		      if (!("00".equals(hm.getRes_success()))) {
		        this.logger.info("退款失败，返回状态码：" + hm.getRes_success());
		        return "error";
		      }
		    }
		    rm.setApplyDate(new Date());
		    rm.setTradeId(trade.getId());
		    rm.setRefundAmount(Double.valueOf(Double.parseDouble(this.refundAmount)));
		    if (Double.parseDouble(this.refundAmount) == trade.getTradeAmount().doubleValue()){
		      rm.setRefundRMBAmount(trade.getRmbAmount());
		    } else{
		      rm.setRefundRMBAmount(Double.valueOf(Double.parseDouble(this.refundAmount) * er.getRate().doubleValue()));
		    }
		    trade.setBackCount(rm.getRefundAmount());
		    if (rm.getRefundAmount().doubleValue() < trade.getTradeAmount().doubleValue()){
		      trade.setTradeState(StatusUtil.updateStatus(trade.getTradeState(), 2, "2"));
		    }else{
		      trade.setTradeState(StatusUtil.updateStatus(trade.getTradeState(), 2, "1"));
		    }
		    this.commonService.update(trade);
		    if (rm.getRefundAmount().doubleValue() < trade.getTradeAmount().doubleValue()){
		      rm.setRefundState("5");
		    }else{
		      rm.setRefundState("4");
		    }
		    rm.setRefundDate(new Date());
		    rm.setBatchNo(batch);
		    rm.setLastMan("system");
		    rm.setLastDate(new Date());
		    this.commonService.save(rm);

		    if (trade.getTradeState().substring(7, 8).equals("1")) {
		      String ehql = "select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId=" + trade.getId();
		      List list = this.commonService.list(ehql, new Object[0]);
		      if (list.size() == 0) {
		        HuakuanedException he = new HuakuanedException();
		        if (rm.getRefundAmount().doubleValue() < trade.getTradeAmount().doubleValue()){
		          he.setTradeType("4");
		        }else{
		          he.setTradeType("5");
		        }
		        he.setTradeId(trade.getId());
		        he.setIsAudit("0");
		        he.setLastDate(new Date());
		        this.commonService.save(he);
		      }
		    }
		    return SUCCESS;
	}
	public String getRes_orderStatus() {
		return res_orderStatus;
	}
	public void setRes_orderStatus(String res_orderStatus) {
		this.res_orderStatus = res_orderStatus;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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

}
