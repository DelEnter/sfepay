package com.ecpss.service.iservice;

import java.util.List;

import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.model.shop.InternationalMerchant;

public interface RefundService {
	
	/**
	 * 根据流水订单号和商户订单好查询订单 
	 * @param tradeNo
	 * @param merOrderNo
	 * @return
	 */
	public InternationalTradeinfo getTradeByMerNo(String tradeNo,String merOrderNo);
	/**
	 * 获取商户下所有已申请状态的退款记录进行预览确定提交退款
	 * @return
	 */
	public List getRefundPreview();
	
	/**
	 * 根据交易ID查询出退款记录
	 * @param tradeId
	 * @return
	 */
	public List<InternationalRefundManager> getRefundByTradeId(Long tradeId);
	
	/**
	 * 生成退款
	 * @param refundIds
	 */
	public void createRefund(Long [] refundIds,String batchNo);
	/**
	 * 根据批次号查询出退款交易
	 * @param batchNo
	 * @return
	 */
	public List getRefundDetailByBatchNo(String batchNo,String refStatus);
	
	/**
	 * 获取已经存在的已审核的退款记录
	 * @return
	 */
	public List<InternationalMerchant> getMerchantList();
	
	/**
	 * 根据退款状态获取不同状态的退款记录中的终端号
	 * @param refundstate
	 * @return
	 */
	public List<String> getTerminalNoByRefund(Long refundstate);
		
	/**
	 * 批量退款并生成文件
	 * @return
	 */
	public String batchRefund();

	/**
	 * 批量退款并生成文件   MIGS 退款
	 * @return
	 */
	public String batchRefundByVC();


}
