package com.ecpss.action.merchant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalTemporaryTradInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.util.StatusUtil;

public class CancelTradeByProcessAction extends BaseAction{

	private String orderNo;   //查询流水号
	private List tradeList;
	/**
	 * 获取待处理交易列表
	 * 必须输入流水号查询
	 * @return
	 */
	public String processTradeQuery(){
		StringBuffer sb = new StringBuffer();
		sb.append("select ti from InternationalTradeinfo ti where 1=1 and ti.tradeState like '2%' ");
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and ti.orderNo='"+orderNo.trim()+"'");
			tradeList = this.commonService.list(sb.toString());
		}
		return SUCCESS;
	}
	
	/**
	 * 取消交易
	 * 每天只能取消一笔
	 * @return
	 */
	public String cancelTrade(){
		InternationalMerchant merchant = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, getMerchantBean().getMerchantId());
		if(merchant!=null){
			if(merchant.getProcessModifyDate()==null){
				Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
		        calendar.add(Calendar.DATE, -1);    //得到前一天
				merchant.setProcessModifyDate(calendar.getTime());
				merchant.setProcessModifyCount(0L);
				this.commonService.update(merchant);
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sf.format(new Date());
			//最近的一次取消交易日期 与当前日期一样的就不给取消
			if(!sf.format(merchant.getProcessModifyDate()).equals(date)){
				String hql = "select ti from InternationalTradeinfo ti,InternationalMerchant m where " +
				" ti.merchantId=m.id " +
				"and ti.orderNo='"+orderNo.trim()+"' " +
						"and m.id="+merchant.getId();
				InternationalTradeinfo tradeinfo = (InternationalTradeinfo) this.commonService.uniqueResult(hql);
				if(tradeinfo!=null){
					tradeinfo.setTradeState(StatusUtil.updateStatus(tradeinfo.getTradeState(), 1, "0"));
					tradeinfo.setRemark("商户要求取消");
					tradeinfo.setVIPDisposeDate(new Date());
					tradeinfo.setVIPDisposePorson(getMerchantBean().getMerchantUserName());
					this.commonService.update(tradeinfo);
					merchant.setProcessModifyDate(new Date());
					merchant.setProcessModifyCount(merchant.getProcessModifyCount()+1L);
					this.commonService.update(merchant);
					InternationalTemporaryTradInfo tem=(InternationalTemporaryTradInfo) commonService.uniqueResult("from InternationalTemporaryTradInfo where orderNo='"+tradeinfo.getOrderNo()+"'");
					if(StringUtils.isNotBlank(tem.getOrderNo())){
						commonService.delete(tem);
					}
					this.messageAction="取消成功,一天只能取消一笔.谢谢";
				}else{
					this.messageAction="无此交易,确认登陆信息是否正确.";
				}
			}else{
				this.messageAction="每天只能取消一笔待处理交易,如需要取消更多请联系客服处理.谢谢.";
			}
		}
		return SUCCESS;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List getTradeList() {
		return tradeList;
	}

	public void setTradeList(List tradeList) {
		this.tradeList = tradeList;
	}
}
