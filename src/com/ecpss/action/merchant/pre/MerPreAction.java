package com.ecpss.action.merchant.pre;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.ecpss.web.PageInfo;

public class MerPreAction extends BaseAction {
	private PageInfo info;

	// private StateUtils state = new StateUtils();mtf
	private String subtype;
	private String pretype;
	private String orderNo;
	private String orderNo2;
	private String merOrderNo;
	private String startDate;
	private String endDate;
	private Long tradeId = 0l;
	private Double refundAmount;
	private InternationalTradeinfo applyRefund;
	private List<InternationalTradeinfo> preList;
	private String merchantOrderNo;
	private Long[] refundIds;

	private Long refundId;

	private List previewRefundList;

	/**
	 * 商户进入退款申请页面
	 * 
	 * @return
	 */
	public String toApplyPre() {
		
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -3);    //得到前一天
		String  yestedayDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	//	System.out.print(yesteday);
		if (this.tradeId != null) {
			if (this.tradeId != 0) {
               if(this.pretype.equals("0")&&this.subtype.equals("0")){
				List li = this.commonService
						.list(" from InternationalTradeinfo f where f.merchantId='"
								+ this.getMerchantBean().getMerchantId()
								+ "' and f.id='"
								+ orderNo.trim()
								+ "' and f.tradeState like '6%' and substr(f.tradeState,14,1)='0'");
				if (li.size() > 0) {
					System.out
							.println(" update INTERNATIONAL_TRADEINFO t set t.pre_money="
									+ this.refundAmount
									+ " , t.tradeState=substr(t.tradeState,1,13)||'2'||substr(t,tradeState,15,(length(t.tradeState)-14)) where t.id='"
									+ tradeId + "'  ");
					this.commonService
							.deleteBySql(" update INTERNATIONAL_TRADEINFO t set t.pre_money="
									+ this.refundAmount
									+ " , t.tradeState=substr(t.tradeState,1,13)||'2'||substr(t.tradeState,15,(length(t.tradeState)-14)) where t.id='"
									+ tradeId + "'  ");
					this.orderNo = "";
					this.refundAmount = 0d;
					this.tradeId = 0l;
				}
			}else if(this.pretype.equals("1")){
				
				this.commonService
				.deleteBySql(" update INTERNATIONAL_TRADEINFO t set t.pre_money="
						+ 0
						+ " , t.tradeState=substr(t.tradeState,1,13)||'0'||substr(t.tradeState,15,(length(t.tradeState)-14)) where t.id='"
						+ tradeId + "'  ");	
				
				this.orderNo = "";
				this.refundAmount = 0d;
				this.tradeId = 0l;
			}
               
			
			}
		}
		if (this.info == null) {
			this.info = new PageInfo();
		}
		if (StringUtils.isNotBlank(orderNo)) {
			List li = this.commonService
					.list(" from InternationalTradeinfo f where f.merchantId='"
							+ this.getMerchantBean().getMerchantId()
							+ "' and f.id='"
							+ orderNo.trim()
							+ "' and f.tradeState like '6%' and substr(f.tradeState,14,1)='0'");
			if (li.size() > 0) {

				this.orderNo = "";

				applyRefund = (InternationalTradeinfo) li.get(0);
			}
		}
		this.info.setPageSize(10);
		// previewRefundList = this.refundService.getRefundPreview(); and t.tradetime>to_date('"+yestedayDate+"','yyyy-MM-dd hh24:mi:ss') 
		StringBuffer sb = new StringBuffer();
		sb
				.append(" from InternationalTradeinfo t where t.tradeState like '6%' and substr(t.tradeState,14,1)in ('0','2','3') and t.merchantId='"
						+ this.getMerchantBean().getMerchantId() + "'");
		if (StringUtils.isNotBlank(this.orderNo2)) {
			sb.append(" and t.orderNo='" + this.orderNo2.trim() + "'");
		}
		if (StringUtils.isNotBlank(this.merchantOrderNo)) {
			sb.append(" and t.merchantOrderNo='" + this.merchantOrderNo.trim()
					+ "'");
		}
		if (StringUtils.isNotBlank(startDate)) { // 开始日期
			sb.append(" and t.tradeTime>=to_date('" + startDate
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		if (StringUtils.isNotBlank(endDate)) { // 结束日期
			sb.append(" and t.tradeTime<=to_date('" + endDate + "23:59:59"
					+ "','yyyy-MM-dd hh24:mi:ss') ");
		}
		this.commonService.listQueryResultByHql(sb.toString(), info);
		return SUCCESS;
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

	public InternationalTradeinfo getApplyRefund() {
		return applyRefund;
	}

	public void setApplyRefund(InternationalTradeinfo applyRefund) {
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

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public List<InternationalTradeinfo> getPreList() {
		return preList;
	}

	public void setPreList(List<InternationalTradeinfo> preList) {
		this.preList = preList;
	}

	public String getOrderNo2() {
		return orderNo2;
	}

	public void setOrderNo2(String orderNo2) {
		this.orderNo2 = orderNo2;
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

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getPretype() {
		return pretype;
	}

	public void setPretype(String pretype) {
		this.pretype = pretype;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

}
