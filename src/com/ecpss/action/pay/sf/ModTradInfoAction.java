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

public class ModTradInfoAction extends BaseAction {
	private String res_orderStatus;
	private String orderNo;
	private String remark;
	private String merOrderNo;
	private String refundAmount;
	Logger logger = Logger.getLogger(ModTradInfoAction.class.getName());
	//同步退款数据
	public String synRefund(){
		try{
		GetBatchNo g = new GetBatchNo();
		String batch = g.getBatchNo();
		InternationalTradeinfo trade=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where merchantOrderNo='"+merOrderNo+"'");
		InternationalExchangerate er = (InternationalExchangerate) this.commonService.load(InternationalExchangerate.class, trade.getTradeRate());
		InternationalCardholdersInfo c = (InternationalCardholdersInfo) this.commonService.uniqueResult("from InternationalCardholdersInfo where tradeId='"+trade.getId()+"'");
		InternationalRefundManager rm = new InternationalRefundManager();
		List listtm=this.commonService.list("select it,ic.channelName from InternationalMerchantChannels im,InternationalTerminalManager it,InternationalChannels ic where im.channelId=ic.id and it.channelId=ic.id and im.id='"+trade.getTradeChannel()+"' and it.onoff='1'");
		InternationalTerminalManager tm=new InternationalTerminalManager();
		String chnals="0";
		String chnalType="";
		if(listtm.size()>0){
		Object[] tem = (Object[]) listtm.get(0);
		tm=(InternationalTerminalManager)tem[0];
		String chanelName1=tem[1].toString();
		chnals = chanelName1.split("-")[0];
		chnalType = chanelName1.split("-")[1];
		}
		if("VPN".equals(chnals)){
			logger.info("****进入VPN退款通道******");
			MotoDCCMessage dcc=new MotoDCCMessage();
			dcc.setTrans_Type("refund");
			dcc.setMerchant_Id(tm.getMerchantNo());
			dcc.setAuthor_Str(tm.getAuthcode());
			dcc.setTerminal_Id(trade.getVIPTerminalNo());
			dcc.setInvoice_No(trade.getVIPAuthorizationNo());
			dcc.setCurrency_Code_T("156");
			BigDecimal AmountOri=new BigDecimal(Double.toString(trade.getRmbAmount())).multiply(new BigDecimal(Double.toString(100.0)));
			BigDecimal AmountLoc=new BigDecimal(Double.toString(rm.getRefundRMBAmount())).multiply(new BigDecimal(Double.toString(100.0)));
			dcc.setAmount_Ori(String.valueOf(AmountOri.intValue()));
			dcc.setAmount_Loc(String.valueOf(AmountLoc.intValue()));
			dcc.setCard_No(getCarNo(c.getCardNo()));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
			dcc.setTran_Date_Ori(sdf.format(trade.getTradeTime()));
			dcc.setRef_No(trade.getRef_No());
			dcc.setHashCode(tm.getHashcode());
			dcc.setOrder_No(trade.getOrderNo());
			dcc.setCustom(trade.getOrderNo());
			VpnUtil_Moto vpn = new VpnUtil_Moto();
			dcc = vpn.getDCCvalue(dcc, "5");
			logger.info("***退款返回码***"+dcc.getResp_Code());
			if(!"0000".equals(dcc.getResp_Code())){
				logger.info("退款失败，返回状态码："+dcc.getResp_Code());
				return "error";
			}
		}else if("HJ".equals(chnals)){
				logger.info("****进入HJ退款通道******");
				HJPayUtil HJ=new HJPayUtil();
				HJPayMessage hm=new HJPayMessage();
				hm.setAcctNo("huajinrong");
				hm.setAgent_AcctNo("huajinrong3");
				hm.setOrderID(trade.getOrderNo());
				hm.setCurrCode("156");
				Double amountAndFee=Double.parseDouble(refundAmount);
				if(trade.getChannelFee()!=null){
					amountAndFee=amountAndFee*(trade.getChannelFee()+1.0);
					amountAndFee = (double) (Math.round((double) amountAndFee * 100) / 100.00);
				}
				hm.setAmount((int)(amountAndFee *100)+"");
				hm.setIpAddress(c.getIp());
				hm.setCardPAN(getCarNo(c.getCardNo()));
				String jiamiqian="";
				jiamiqian="dsfhue2568415sfh"+ hm.getAcctNo() + hm.getOrderID() + hm.getAmount() + hm.getCurrCode();
				MessageDigest getMd5;
				BASE64Encoder base64E = new BASE64Encoder();
				String value = null;
				try {
					getMd5 = MessageDigest.getInstance("MD5");
					value =  base64E.encode(getMd5.digest(jiamiqian.getBytes("UTF-8")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("加密前："+jiamiqian);
				System.out.println("加密后："+value);
				hm.setHashValue(value);
				hm.setTxnID(trade.getVIPAuthorizationNo());
				hm.setTxnType("03");
				HJ.get(hm);
				if(!"00".equals(hm.getRes_success())){
					logger.info("退款失败，返回状态码："+hm.getRes_success());
					return "error";
				}
			}else if("HW".equals(chnals)){
				logger.info("****进入HW退款通道******");
				HJWPayUtil hjw=new HJWPayUtil();
				HJWPayMessage hwm=new HJWPayMessage();
				hwm.setMerchantId("188001000261324");
				hwm.setMd5key("CYNCEPKR29VCQ4GX48TU8X7NURHYMKBX");
				GetBatchNo ut = new GetBatchNo();
				hwm.setBillNo(ut.getOrderinfo(trade.getOrderNo().substring(0, 4)));
				hwm.setJcTradeId(trade.getVIPAuthorizationNo());
				Double amountAndFee=Double.parseDouble(refundAmount);
				if(trade.getChannelFee()!=null){
					amountAndFee=amountAndFee*(trade.getChannelFee()+1.0);
					amountAndFee = (double) (Math.round((double) amountAndFee * 100) / 100.00);
				}
				hwm.setAmount(amountAndFee+"");
				hwm.setTradType("2");
				hjw.get(hwm);
				if(!"00".equals(hwm.getRes_responseCode())){
					logger.info("退款失败，返回状态码："+hwm.getRes_responseCode());
					return "error";
				}
			}
			rm.setApplyDate(new Date());
			rm.setTradeId(trade.getId());
			rm.setRefundAmount(Double.parseDouble(refundAmount));
			if(Double.parseDouble(refundAmount)==trade.getTradeAmount().doubleValue()){
				rm.setRefundRMBAmount(trade.getRmbAmount());
			}else{
				rm.setRefundRMBAmount(Double.parseDouble(refundAmount)*er.getRate());
			}
			BigDecimal backCount=new BigDecimal(trade.getBackCount());
			BigDecimal refundCount=new BigDecimal(rm.getRefundAmount());
			backCount=backCount.add(refundCount);
			trade.setBackCount(Double.valueOf(backCount.toString()));
			if(trade.getBackCount()<trade.getTradeAmount()){
				trade.setTradeState(StatusUtil.updateStatus(trade.getTradeState(), 2, "2"));
			}else{
				trade.setTradeState(StatusUtil.updateStatus(trade.getTradeState(), 2, "1"));
			}
			this.commonService.update(trade);
			if(rm.getRefundAmount()<trade.getTradeAmount()){
				rm.setRefundState("5");
			}else{
				rm.setRefundState("4");
			}
			rm.setRefundDate(new Date());
			rm.setBatchNo(batch);
			rm.setLastMan("system");
			rm.setLastDate(new Date());
			this.commonService.save(rm);
			
				//**********************如果是已经划款过的交易退款插入划款异常表************************
					if(trade.getTradeState().substring(7,8).equals("1")){
						String ehql="select h.id from HuakuanedException h where h.tradeType in (4,5) and h.tradeId="+trade.getId();
						List list = this.commonService.list(ehql);
						if(list.size()==0){
							HuakuanedException he = new HuakuanedException();
							if(rm.getRefundAmount()<trade.getTradeAmount()){
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
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
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
