package com.ecpss.action.sitetopay;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalExchangerate;
import com.ecpss.service.iservice.RefundService;

public class OperationAction extends BaseAction {
	@Autowired
	@Qualifier("refundService")
	private RefundService refundService;
	
	private String OType;
	private String PayOrderNo;
	private String MerchantOrderNo;
	private String Amount;
	private String RefundAmount;
	private String ReturnURL;

	private String RefundStatus;

	/**
	 * 操作类 OType: 001 退款
	 * 
	 * @return
	 */
	public String operations() {
		try {
			// 必输参数为空直接返回
			if (StringUtils.isNotBlank(OType)
					&& StringUtils.isNotBlank(PayOrderNo)
					&& StringUtils.isNotBlank(MerchantOrderNo)
					&& StringUtils.isNotBlank(Amount)
					&& StringUtils.isNotBlank(RefundAmount)
					) {
				if (OType.equals("001")) {
					// 操作退款
					String sql = "select ti from InternationalTradeinfo ti where ti.orderNo='"
							+ PayOrderNo.trim()
							+ "' "
							+ "and ti.merchantOrderNo='"
							+ MerchantOrderNo.trim()
							+ "' "
							+ "and ti.tradeAmount=" + Amount;
					List list = this.commonService.list(sql);
					if (list.size() > 0) {
						InternationalTradeinfo ti = (InternationalTradeinfo) list
								.get(0);
						List<InternationalRefundManager> listr = refundService.getRefundByTradeId(ti.getId());
						if(listr.size()>0){
							//已经申请过退款
							RefundStatus="03";
							return SUCCESS;
						}
						InternationalRefundManager r =listr.get(0);
						if (ti.getTradeState().substring(2, 3).equals("1")) {
							// 如果该交易已经拒付,不可以申请退款
							RefundStatus = "05";
							return SUCCESS;
						}
						if (Double.valueOf(RefundAmount) > Double.valueOf(Amount)) {
							// 退款金额大于原始交易金额
							RefundStatus = "01";
							return SUCCESS;
						}
						InternationalExchangerate er = (InternationalExchangerate) this.commonService
								.load(InternationalExchangerate.class, ti
										.getTradeRate());
						InternationalRefundManager rm = new InternationalRefundManager();
						rm.setApplyDate(new Date());
						rm.setLastDate(new Date());
						rm.setRefundState("2");
						rm.setTradeId(ti.getId());
						rm.setRefundAmount(Double.valueOf(RefundAmount));
						//rm.setRemark(this.ReturnURL);
						if (Double.valueOf(RefundAmount) == ti.getTradeAmount()
								.doubleValue()) {
							rm.setRefundRMBAmount(ti.getRmbAmount());
						} else {
							rm.setRefundRMBAmount(Double.valueOf(RefundAmount)
									* er.getRate());
						}
						rm.setLastMan("server");
						this.commonService.save(rm);
						
						RefundStatus = "00";
						return SUCCESS;
					} else {
						// 未找到匹配交易
						RefundStatus = "02";
						return SUCCESS;
					}

				}else{
					// 输入值为空
					RefundStatus = "04";
					return SUCCESS;
				}
			} else {
				// 输入值为空
				RefundStatus = "04";
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			RefundStatus = "04";
			return SUCCESS;
		}
		//return SUCCESS;
	}

	public String getOType() {
		return OType;
	}

	public void setOType(String type) {
		OType = type;
	}

	public String getPayOrderNo() {
		return PayOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		PayOrderNo = payOrderNo;
	}

	public String getMerchantOrderNo() {
		return MerchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		MerchantOrderNo = merchantOrderNo;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getRefundAmount() {
		return RefundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		RefundAmount = refundAmount;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getRefundStatus() {
		return RefundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		RefundStatus = refundStatus;
	}

	public RefundService getRefundService() {
		return refundService;
	}

	public void setRefundService(RefundService refundService) {
		this.refundService = refundService;
	}

}
