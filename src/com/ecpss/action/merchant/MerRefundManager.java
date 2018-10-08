package com.ecpss.action.merchant;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.refund.InternationalRefundManager;
import com.ecpss.tools.TableExport;
import com.ecpss.tools.TableExportFactory;
import com.ecpss.util.DownloadUtils;
import com.ecpss.util.GetBatchNo;
import com.ecpss.web.PageInfo;

public class MerRefundManager extends BaseAction{
	
	private PageInfo info = new PageInfo();
	private Long merchantNo;
	private String orderNo;
	private String merchantOrderNo;
	private Long refundId;
	private String exportX;
	private String startDate;
	private String endDate;
	private String exportOrNo="0";
	/**
	 * 商户退款管理页面查看
	 * @return
	 * @throws IOException 
	 */
	public String refundManager() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select rm,ti " +
				" from InternationalRefundManager rm," +
				" InternationalTradeinfo ti," +
				" InternationalMerchant m " +
				" where rm.tradeId=ti.id " +
				" and m.id="+ getMerchantBean().getMerchantId() +              //根据登陆商户id
				" and ti.merchantId=m.id "); 
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and ti.orderNo='"+orderNo.trim()+"' ");
		}
		if(StringUtils.isNotBlank(merchantOrderNo)){
			sb.append(" and ti.merchantOrderNo='"+merchantOrderNo.trim()+"' ");
		}
		if(StringUtils.isNotBlank(startDate)){  //开始日期
			sb.append(" and rm.applyDate>=to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss') ");
		}
		if(StringUtils.isNotBlank(endDate)){   //结束日期
			sb.append(" and rm.applyDate<=to_date('"+endDate+"23:59:59"+"','yyyy-MM-dd hh24:mi:ss') ");
		}
		sb.append(" order by rm.refundState,rm.applyDate desc ");
		
		if(StringUtils.isNotBlank(exportX)){
			List<Object[]> objList  = commonService.list(sb.toString());
			this.processExport(objList);
			return null;
		}else{
			info=commonService.listQueryResultByHql(sb.toString(), info);
		}
		return SUCCESS;
	}
	
	private void processExport(List<Object[]> oArray) throws IOException {	
		TableExport export = TableExportFactory.createExcelTableExport();
		if("1".equals(exportOrNo)){
			export.addTitle(new String[]{"交易流水号","商户订单号","交易日期","申请日期","交易金额","退款金额","退款时间","退款状态","退款流水号"});
		}else{
		export.addTitle(new String[]{"交易流水号","商户订单号","交易日期","申请日期","交易金额","退款金额","退款时间","退款状态"});
		}
		export.setTableName(GetBatchNo.getTime()+"下载退款记录");
		InternationalRefundManager r = new InternationalRefundManager();
		InternationalTradeinfo t = new InternationalTradeinfo();
		if("1".equals(exportOrNo)){
			for(Object[] obj:oArray){
				r = (InternationalRefundManager) obj[0];
				t = (InternationalTradeinfo) obj[1];
				export.addRow(new Object[]{t.getOrderNo(),t.getMerchantOrderNo(),t.getTradeTime(),
						r.getApplyDate(),t.getTradeAmount(),t.getBackCount(),r.getRefundDate(),getStates().getRefundDetailState(Long.valueOf(r.getRefundState()).intValue()),r.getRefundNo()});	
			}
		}else{
			for(Object[] obj:oArray){
				r = (InternationalRefundManager) obj[0];
				t = (InternationalTradeinfo) obj[1];
				export.addRow(new Object[]{t.getOrderNo(),t.getMerchantOrderNo(),t.getTradeTime(),
						r.getApplyDate(),t.getTradeAmount(),t.getBackCount(),r.getRefundDate(),getStates().getRefundDetailState(Long.valueOf(r.getRefundState()).intValue())});	
			}
		}
		OutputStream os = DownloadUtils.getResponseOutput(GetBatchNo.getTime()+"退款记录.xls");
		export.export(os);
		DownloadUtils.closeResponseOutput();
	}
	
	/**
	 * 退款管理那里取消退款
	 * @return
	 * @throws Exception 
	 */
	public String deleteRefund() throws Exception{
		this.commonService.delete(InternationalRefundManager.class, refundId);
		return refundManager();
	}
	
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public Long getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(Long merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}


	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public String getExportX() {
		return exportX;
	}

	public void setExportX(String exportX) {
		this.exportX = exportX;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExportOrNo() {
		return exportOrNo;
	}

	public void setExportOrNo(String exportOrNo) {
		this.exportOrNo = exportOrNo;
	}
	
	
}
