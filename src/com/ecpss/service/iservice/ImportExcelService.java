package com.ecpss.service.iservice;

import com.ecpss.vo.ImportRefundHistory;

public interface ImportExcelService {
	
	/**
	 * 判断卡号在否在黑卡库存在
	 * @param card
	 * @return
	 */
	public boolean getBackCardBean(String card);
	
	/**
	 * 添加黑卡信息
	 * @param IP
	 * @param cardNo
	 * @param Email
	 * @param cookie
	 * @param remark
	 */
	public void saveBackCardInfo(String IP,String cardNo,String Email,String cookie,String remark);
	/**
	 * 添加高风险卡卡信息
	 * @param IP
	 * @param cardNo
	 * @param Email
	 * @param cookie
	 * @param remark
	 */
	public void saveRickCardInfo(String IP,String cardNo,String Email,String cookie,String remark);
	
	/**
	 * 保存退款上传记录
	 * @param rh
	 */
	public void saveRefundByBank(ImportRefundHistory rh,String fileName,String batchno);
	
	public Boolean getFileNameByRefund(String filename);
	
}
