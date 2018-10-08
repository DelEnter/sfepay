package com.ecpss.action.pay;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalMoneykindname;
import com.ecpss.model.shop.InternationalWebchannels;

public class GBPPayMentAction extends BaseAction{
	private Long MerNo;
	private String ReturnURL;
	private String MD5key;
	private String tradeAdd;
    private String Currency;
    private String Amount;
    private String BillNo;
    
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

	public String fastPayment() throws Exception {
//		//验证交易网址是否存在
//		String oql = "select wc from InternationalWebchannels wc,InternationalMerchant m " +
//				"where wc.merchanid=m.id and m.merno="+MerNo;
//		List<InternationalWebchannels> wclist = commonService.list(oql);
//		//获取商户支付网址url信息
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String url = request.getHeader("Referer");
//		if(url!=null){
//			String a [] = url.split("/");
//			tradeAdd = a[2];
//		}
//		boolean md5tradeurl = false;
//		if(tradeAdd!=null){
//			if(wclist.size()>0){
//				for (InternationalWebchannels wc : wclist) {
//					if(tradeAdd.equals(wc.getTradeWebsite())){
//						md5tradeurl = true;
//					}
//				}
//			}
//		}
//		//交易网址不正确
//		if(!md5tradeurl){
//			return INPUT;
//		}
		
		//获取商户支付网址url信息
//		String oql1 = "from InternationalWebchannels wc where wc.tradeWebsite='"+tradeAdd+"' ";
//		List<InternationalWebchannels> wcList = commonService.list(oql1);
//		if(wcList.size()>0){
			ReturnURL = "http://security.sslepay.com/payresult.jsp";
//		}
		String olq1 = "from InternationalMerchant m where m.merno="+MerNo;
		List<InternationalMerchant> mList=commonService.list(olq1);
		if(mList.size()>0){
			MD5key = mList.get(0).getMd5key();
			String hql="select mk from InternationalMerchantCurrency mc,InternationalMoneykindname mk " +
					" where mk.id=mc.moneyKindNo and mc.merchanId="+mList.get(0).getId();
			InternationalMoneykindname mc = (InternationalMoneykindname) this.commonService.uniqueResult(hql);
			Currency = mc.getMoneykindno()+"";
		}
		return SUCCESS;
	}

	public Long getMerNo() {
		return MerNo;
	}

	public void setMerNo(Long merNo) {
		MerNo = merNo;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getMD5key() {
		return MD5key;
	}

	public void setMD5key(String md5key) {
		MD5key = md5key;
	}

	public String getTradeAdd() {
		return tradeAdd;
	}

	public void setTradeAdd(String tradeAdd) {
		this.tradeAdd = tradeAdd;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

}
