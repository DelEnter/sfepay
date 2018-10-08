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
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.iservice.RefundService;
import com.ecpss.vo.MerchantBean;
import com.opensymphony.xwork2.ActionContext;

public class MerApplyRefundAction extends BaseAction{
	
	@Autowired
	@Qualifier("refundService")
	private RefundService refundService;
	
	//private StateUtils state = new StateUtils();
	
	private String orderNo;
	private String merOrderNo;
	private Long tradeId;
	private Double refundAmount;
	private InternationalTradeinfo trade;
	private Double applyRefund;
	
	private Long [] refundIds;
	
	private Long refundId;
	
	private List previewRefundList;
	/**
	 * 商户进入退款申请页面
	 * @return
	 */
	public String toApplyRefund(){
//		MerchantBean merbean = (MerchantBean)ActionContext.getContext().getSession().get("merchantBean");
//		StringBuffer sql = new StringBuffer("select ti.id,ti.orderNo,ti.merchantOrderNo,ti.tradeTime,ti.tradeAmount,ti.tradeState,rm.refundAmount " +
//		"from International_Tradeinfo ti left join International_RefundManager rm on (ti.id=rm.tradeid)" +
//		"where ti.merchantId=" + merbean.getMerchantId() + 
//				" and substr(ti.tradeState,1,1)='1' ");
//		if(StringUtils.isNotBlank(orderNo)){
//			sql.append(" and ti.orderNo='"+orderNo.trim()+"' ");
//		}
//		if(StringUtils.isNotBlank(merOrderNo)){
//			sql.append(" and ti.merchantOrderNo='"+merOrderNo.trim()+"' ");
//		}
////		InternationalTradeinfo ti = null;
//		List<Object[]> list = commonService.getByList(sql.toString());
//		System.out.println("sql---------------"+sql);
//		if(list.size()>0){
//			applyRefund =(Object[])list.get(0);
//		}
//		if(StringUtils.isNotBlank(orderNo) || StringUtils.isNotBlank(merOrderNo) ){
		if(StringUtils.isNotBlank(orderNo)){
		trade = refundService.getTradeByMerNo(orderNo, merOrderNo);
		List<InternationalRefundManager> rf = refundService.getRefundByTradeId(trade.getId());
		BigDecimal tradeAmout=new BigDecimal(trade.getTradeAmount());
		for(int i=0;i<rf.size();i++){
			InternationalRefundManager ref=rf.get(i);
			BigDecimal refundNum=new BigDecimal(ref.getRefundAmount());
			tradeAmout=tradeAmout.subtract(refundNum);
		}
		applyRefund=Double.valueOf(tradeAmout.toString());
		System.out.println(applyRefund);
		}
//		}
		//previewRefundList = this.refundService.getRefundPreview();
		return SUCCESS;
	}
	/**
	 * 提交退款申请进入预览表
	 * @return
	 */
	public String applyRefund(){
		try{
			List<InternationalRefundManager> r = refundService.getRefundByTradeId(tradeId);
			InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService.load(InternationalTradeinfo.class, tradeId);
			List listtm=this.commonService.list("select ic.id,ic.channelName from InternationalMerchantChannels im,InternationalTerminalManager it,InternationalChannels ic where im.channelId=ic.id and it.channelId=ic.id and im.id='"+ti.getTradeChannel()+"' and it.onoff='1'");
			String chnals="0";
			if(listtm.size()>0){
			Object[] tem = (Object[]) listtm.get(0);
			String chanelName1=tem[1].toString();
			chnals = chanelName1.split("-")[0];
			}
			BigDecimal tradeAmount=new BigDecimal(ti.getTradeAmount());
			BigDecimal refAmount=new BigDecimal(refundAmount);
			if(r.size()>0){
				
				for(InternationalRefundManager rf:r){
					BigDecimal refundNum=new BigDecimal(rf.getRefundAmount());
					tradeAmount=tradeAmount.subtract(refundNum);
				}
				System.out.println(tradeAmount.doubleValue());
				if(tradeAmount.doubleValue()==0.0||tradeAmount.doubleValue()<=0.0){
					//已经申请过退款
					this.messageAction="订单已全额退款，请在历史退款中查看！";
					return this.toApplyRefund();
				}
			}
			if(ti.getTradeState().substring(2, 3).equals("1")){ 
				//如果该交易已经拒付,不可以申请退款
				this.messageAction="交易已经拒付，不能申请退款。";
				return this.toApplyRefund();
			}
			//if(("HW".equals(chnals)||"HJ".equals(chnals)||"HR".equals(chnals))&&tradeAmount.compareTo(refAmount)>0.1 ){
			if(("HW".equals(chnals)||"HR".equals(chnals))&&tradeAmount.compareTo(refAmount)>0.1 ){
				this.messageAction="* 此单交易所走银行不支持部分退款！";
				return this.toApplyRefund();
			}
			InternationalExchangerate er = (InternationalExchangerate) this.commonService.load(InternationalExchangerate.class, ti.getTradeRate());
			InternationalRefundManager rm = new InternationalRefundManager();
			rm.setApplyDate(new Date());
			rm.setLastDate(new Date());
			rm.setRefundState("1");
			rm.setTradeId(tradeId);
			rm.setRefundAmount(refundAmount);
			if(refundAmount==ti.getTradeAmount().doubleValue()){
				rm.setRefundRMBAmount(ti.getRmbAmount());
			}else{
				rm.setRefundRMBAmount(refundAmount*er.getRate());
			}
			this.commonService.save(rm);
			this.messageAction="提交申请成功，请去确认提交退款页面再次确认提交。";
			return this.toApplyRefund();
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 查看该已经申请过的退款交易
	 * @return
	 */
	public String merauditingRefund(){
		previewRefundList = this.refundService.getRefundPreview();
		return SUCCESS;
	}
	
	/**
	 * 选择退款交易进行退款
	 * @return
	 */
	public String submitRefund(){
		for (Long r : refundIds) {
			InternationalRefundManager rm = (InternationalRefundManager) this.commonService.load(InternationalRefundManager.class, r); 
			rm.setRefundState("2");
			rm.setApplyDate(new Date());
			this.commonService.update(rm);
		}
		return this.merauditingRefund();
	}
	/**
	 * 商户预览退款记录  取消操作
	 * @return
	 */
	public String cancelRefund(){
		this.commonService.delete(InternationalRefundManager.class, refundId) ;
		return this.merauditingRefund();
	}
	
	public RefundService getRefundService() {
		return refundService;
	}
	public void setRefundService(RefundService refundService) {
		this.refundService = refundService;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMerOrderNo() {
		return merOrderNo;
	}
	public void setMerOrderNo(String merOrderNo) {
		this.merOrderNo = merOrderNo;
	}
	public List getPreviewRefundList() {
		return previewRefundList;
	}
	public void setPreviewRefundList(List previewRefundList) {
		this.previewRefundList = previewRefundList;
	}
	public InternationalTradeinfo getTrade() {
		return trade;
	}
	public void setTrade(InternationalTradeinfo trade) {
		this.trade = trade;
	}
	public Double getApplyRefund() {
		return applyRefund;
	}
	public void setApplyRefund(Double applyRefund) {
		this.applyRefund = applyRefund;
	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Long[] getRefundIds() {
		return refundIds;
	}
	public void setRefundIds(Long[] refundIds) {
		this.refundIds = refundIds;
	}
	public Long getRefundId() {
		return refundId;
	}
	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}
	

}
