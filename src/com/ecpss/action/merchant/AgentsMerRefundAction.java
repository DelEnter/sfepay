package com.ecpss.action.merchant;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.service.common.CommonService;
import com.ecpss.service.iservice.RefundService;
//代理商户退款
public class AgentsMerRefundAction extends BaseAction {
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	@Autowired
	@Qualifier("refundService")
	private RefundService refundService;
	private String orderId;
	private Double applyRefund;
	private InternationalTradeinfo trade;
	private Double refundAmount;
	private String tradeId;
	//代理商户退款
	public String toAgentsRefund(){
		if(StringUtils.isNotBlank(orderId)){
			trade=(InternationalTradeinfo) commonService.load(InternationalTradeinfo.class,new Long(orderId));
			List<InternationalRefundManager> rf = refundService.getRefundByTradeId(trade.getId());
			BigDecimal tradeAmout=new BigDecimal(trade.getTradeAmount());
			for(int i=0;i<rf.size();i++){
				InternationalRefundManager ref=rf.get(i);
				BigDecimal refundNum=new BigDecimal(ref.getRefundAmount());
				tradeAmout=tradeAmout.subtract(refundNum);
			}
			applyRefund=Double.valueOf(tradeAmout.toString());
			}
			return SUCCESS;

	}
	public String agentsRefund(){
		try{
			List<InternationalRefundManager> r = refundService.getRefundByTradeId(new Long(tradeId));
			InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, new Long(tradeId));
			if(r.size()>0){
				BigDecimal tradeAmount=new BigDecimal(ti.getTradeAmount());
				for(InternationalRefundManager rf:r){
					BigDecimal refundNum=new BigDecimal(rf.getRefundAmount());
					tradeAmount=tradeAmount.subtract(refundNum);
				}
				System.out.println(tradeAmount.doubleValue());
				if(tradeAmount.doubleValue()==0.0||tradeAmount.doubleValue()<=0.0){
					//已经申请过退款
					this.messageAction="Application has been submitted.Please refund check in the view of history！";
					return SUCCESS;
				}
			}
			if(ti.getTradeState().substring(2, 3).equals("1")){
				//如果该交易已经拒付,不可以申请退款
				this.messageAction="The transaction has been chargeback.Can not apply for a refund.";
				return SUCCESS;
			}
			InternationalExchangerate er = (InternationalExchangerate) this.commonService.load(InternationalExchangerate.class, ti.getTradeRate());
			InternationalRefundManager rm = new InternationalRefundManager();
			rm.setApplyDate(new Date());
			rm.setLastDate(new Date());
			rm.setRefundState("2");
			rm.setTradeId(new Long(tradeId));
			rm.setRefundAmount(refundAmount);
			if(refundAmount==ti.getTradeAmount().doubleValue()){
				rm.setRefundRMBAmount(ti.getRmbAmount());
			}else{
				rm.setRefundRMBAmount(refundAmount*er.getRate());
			}
			this.commonService.save(rm);
			this.messageAction="Submitted success!";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public RefundService getRefundService() {
		return refundService;
	}
	public void setRefundService(RefundService refundService) {
		this.refundService = refundService;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Double getApplyRefund() {
		return applyRefund;
	}
	public void setApplyRefund(Double applyRefund) {
		this.applyRefund = applyRefund;
	}
	public InternationalTradeinfo getTrade() {
		return trade;
	}
	public void setTrade(InternationalTradeinfo trade) {
		this.trade = trade;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
}
