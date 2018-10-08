package com.ecpss.action.express;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import vpn.ZMTTradeurlMessage;
import vpn.ZMTTradeurlUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.action.TemporarySynThread;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;

public class GoopayAction extends BaseAction {
	
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	
	private String BillNo;
	private String Succeed;
	private String Remark;
	private String GRN;
	
	private int responseCode;
	private String message;
	
	Logger logger = Logger.getLogger(GoopayAction.class.getName());	


	public void Goopay() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		//logger.info(request+"");
		logger.info("Goopay:"+request.getQueryString()+"");
		logger.info("Goopay异步返回信息:"+request.getParameter("BillNo"));
		//Goopay的异步通知
		logger.info("BillNo:"+BillNo+"*********Succeed:"+Succeed+"*********Remark:"+Remark+"*********GRN:"+GRN);
		InternationalTradeinfo trade=(InternationalTradeinfo) commonService.uniqueResult("from InternationalTradeinfo where orderNo='"+request.getParameter("BillNo")+"'");
		InternationalMerchant mer=(InternationalMerchant) commonService.uniqueResult("from InternationalMerchant where id='"+trade.getMerchantId()+"'");
		InternationalCardholdersInfo card=(InternationalCardholdersInfo) commonService.uniqueResult("from InternationalCardholdersInfo where tradeId='"+trade.getId()+"'");
/*		
		String cardNo=AES.getCarNo(card.getCardNo());		
		String expiryDate = AES.getCarNo(card.getExpiryDate());
		String month = expiryDate.substring(0,2);
		String year = expiryDate.substring(2,4);
		String cvv = AES.getCarNo(card.getCvv2());
		*/	
		StringBuffer buffer = new StringBuffer(trade.getTradeState());
		 	if(request.getParameter("succeed").equals("88")){				 		
		 		buffer.replace(0, 1, "1");	
		 		trade.setRemark("Payment Success!");
		 		this.responseCode=88;
		 		/*MD5info = trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue
						+ responseCode + MD5key;
				md5Value = md5.getMD5ofStr(MD5info);*/		 
				if("1001".equals((trade.getOrderNo()).substring(0,4))||"4136".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.xingbill.com/synTradeInfo",trade.getMerchantOrderNo(), "1", trade.getRemark());
					ts.start();
				}
				if("4160".equals((trade.getOrderNo()).substring(0,4))||"4161".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.ipasspay.biz/index.php/Thirdpay/Sfepay/notifyUrl",trade.getMerchantOrderNo(), "1", trade.getRemark());
					ts.start();
				}
				if("4169".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.jjqsc.com/PayResult.php",trade.getMerchantOrderNo(), "1", trade.getRemark());
					ts.start();
				}
				if("4165".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.youkutuan.com/do.php?act=charge_okok",trade.getMerchantOrderNo(), "1", trade.getRemark());
					ts.start();
				}
				if("3918".equals((trade.getOrderNo()).substring(0,4))||"4110".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.win4mall.com/OrderAutomatic",trade.getMerchantOrderNo(),"1", trade.getRemark());
					ts.start();
				}
		 	}else if(request.getParameter("succeed").equals("0")){
		 		buffer.replace(0, 1, "0");
		 		trade.setRemark(Remark);
		 		this.responseCode=0;
		 		this.message = "Payment Declined!"; 
				if("1001".equals((trade.getOrderNo()).substring(0,4))||"4136".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.xingbill.com/synTradeInfo",trade.getMerchantOrderNo(), "0",trade.getRemark());
					ts.start();
				}
				if("4160".equals((trade.getOrderNo()).substring(0,4))||"4161".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.ipasspay.biz/index.php/Thirdpay/Sfepay/notifyUrl",trade.getMerchantOrderNo(), "0", trade.getRemark());
					ts.start();
				}		
				if("4169".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.jjqsc.com/PayResult.php",trade.getMerchantOrderNo(), "0", trade.getRemark());
					ts.start();
				}
				if("4165".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.youkutuan.com/do.php?act=charge_okok",trade.getMerchantOrderNo(), "0", trade.getRemark());
					ts.start();
				}
				if("3918".equals((trade.getOrderNo()).substring(0,4))||"4110".equals((trade.getOrderNo()).substring(0,4))){
					TemporarySynThread ts=new TemporarySynThread("http://www.win4mall.com/OrderAutomatic",trade.getMerchantOrderNo(),"0", trade.getRemark());
					ts.start();
				}
		 		//shopManagerService.addTemporaryTradInfo(trade.getOrderNo(), year, month,cvv,card.getCountry(),mer.getMd5key(), card.getIp(),"MSIE 10.0","WR"+respMsg);
		 	}
		 trade.setTradeState(buffer.toString());
		 commonService.update(trade);
		 logger.info("**************支付返回码*************"+responseCode);
		 ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		 out.print("OK");
		 //return "success";
	 }
	

	public CommonService getCommonService() {
		return commonService;
	}


	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}


	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}


	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}


	public String getBillNo() {
		return BillNo;
	}


	public void setBillNo(String billNo) {
		BillNo = billNo;
	}


	public String getSucceed() {
		return Succeed;
	}


	public void setSucceed(String succeed) {
		Succeed = succeed;
	}


	public String getRemark() {
		return Remark;
	}


	public void setRemark(String remark) {
		Remark = remark;
	}


	public String getGRN() {
		return GRN;
	}


	public void setGRN(String gRN) {
		GRN = gRN;
	}

	
}
