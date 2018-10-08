package com.ecpss.service.iservice;

import java.io.File;
import java.util.List;

import com.ecpss.action.pay.tc.XMLGetMessage;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalChargeBack;

public interface SystemManagerService {
	
	/**
	 * 根据终端号获取日成功交易笔数
	 * @param TermialNo
	 * @return
	 */
	public Long getTradeCountByTerminalNo(String TermialNo);
	/**
	 * 根据终端号获取月成功交易笔数
	 * @param TermialNo
	 * @return
	 */
	public Long getTradeCountByTerminalNoMonth(String TermialNo);
	
	/**
	 * 关闭超过设置笔数的终端
	 */
	public void closeTerminalNoByMore();
	
	/**
	 * 上传银行给的拒付
	 * @param fileName
	 */
	public String importChargeBack(File fileName,String batchno);
	
	/**
	 * 根据拒付处理表中的卡号跟金额查询是否大于两条以上
	 * @param cardNo
	 * @param amount
	 * @return
	 */
	public boolean getByCardNoAndAmount(String cardNo,Double amount);
	
	/**
	 * 根据交易表Id拒付该交易
	 * @param tradeId
	 */
	public void chargeBack(Long tradeId);
	/**
	 * 拒付具体操作,修改交易表为拒付,并列为黑卡或者风险卡
	 * @param cb
	 * @param isBackCard
	 * @param isRickCard
	 * @return
	 */
	public String chargeBackUpdate(InternationalChargeBack cb,Long tradeId,String isBackCard,String isRickCard);
	
	/**
	 * 根据结算日获取该结算日下的商户
	 * @param cycle
	 * @return
	 */
	public List getMerchantNoByCycle(Long cycle);
	
	/**
	 * BOC撤消交易处理
	 * @param tradeinfo
	 * @param cardinfo
	 * @return
	 */
	public XMLGetMessage BocCancelTrade(InternationalTradeinfo tradeinfo,InternationalCardholdersInfo cardinfo);
	
	/**
	 * BOC退款交易
	 * @param tradeinfo
	 * @param cardinfo
	 * @param refundamount
	 * @return
	 */
	public XMLGetMessage BocRefundTrade(InternationalTradeinfo tradeinfo,InternationalCardholdersInfo cardinfo,Double refundamount,String orderno);
}
