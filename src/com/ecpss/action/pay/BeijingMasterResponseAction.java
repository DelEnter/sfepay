package com.ecpss.action.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.StatusUtil;
import com.ecpss.vo.ImportCheck;

public class BeijingMasterResponseAction extends BaseAction {

	/**
	 * 接受参数信息
	 */

	private String BillNo;
	private String Amount;
	private String Currency;
	private String Succeed;
	private String remark;
	// private Long terminalId;//终端id

	private String successTradeList ;

	/**
	 * 返回支付结果信息
	 */
	private String finalPosNumer; // 最终使用终端号
	private String authorizationNo; // 授权号
	private String batchNo; // 批次号

	public String beijingResponse() {
		if (StringUtils.isNotBlank(successTradeList)) {
			String o[] = successTradeList.split("-");
			for (String imc : o) {
				if(StringUtils.isNotBlank(imc)){
					String que = "select ti from InternationalTradeinfo ti where ti.orderNo='"+imc+"'" ;
					InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService
					.uniqueResult(que);
					if(ti!=null){
						ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 5, "1"));
						ti.setGouduiTime(new Date());
						this.commonService.update(ti);
					}
				}
			}
		} else {
			String hql = "select ti from InternationalTradeinfo ti where ti.orderNo='"
					+ BillNo.trim() + "' ";
			InternationalTradeinfo ti = (InternationalTradeinfo) this.commonService
					.uniqueResult(hql);
			if (ti != null) {
				ti.setLastDate(new Date());
				ti.setLastMan("system");
				ti.setVIPAuthorizationNo(authorizationNo);
				ti.setVIPBatchNo(batchNo);
				if (Succeed.equals("88")) {
					ti.setTradeState(StatusUtil.updateStatus(
							ti.getTradeState(), 1, "1"));
				} else {
					ti.setTradeState(StatusUtil.updateStatus(
							ti.getTradeState(), 1, "0"));
					ti.setRemark(remark);
				}
				ti.setVIPDisposeDate(new Date());
				ti.setVIPDisposePorson("system");
				// if(StringUtils.isNotBlank(finalPosNumer)){
				// ti.setVIPTerminalNo(finalPosNumer);
				// }
				this.commonService.update(ti);
				
				
				//清除持卡人cvv,有效期
				this.commonService
				.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
						+ ti.getId() + "'");
				
			
			}
		}
		return SUCCESS;
	}

	public String getBillNo() {
		return BillNo;
	}

	public void setBillNo(String billNo) {
		BillNo = billNo;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getSucceed() {
		return Succeed;
	}

	public void setSucceed(String succeed) {
		Succeed = succeed;
	}

	public String getFinalPosNumer() {
		return finalPosNumer;
	}

	public void setFinalPosNumer(String finalPosNumer) {
		this.finalPosNumer = finalPosNumer;
	}

	public String getAuthorizationNo() {
		return authorizationNo;
	}

	public void setAuthorizationNo(String authorizationNo) {
		this.authorizationNo = authorizationNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSuccessTradeList() {
		return successTradeList;
	}

	public void setSuccessTradeList(String successTradeList) {
		this.successTradeList = successTradeList;
	}

}
