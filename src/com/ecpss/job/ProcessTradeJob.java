package com.ecpss.job;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;
import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.common.CommonService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.MD5;
import com.ecpss.util.StateUtils;
import com.ecpss.web.VcpUtil;

public class ProcessTradeJob extends QuartzJobBean {

	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	@Qualifier("commonService")
	private CommonService commonService;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		List list = shopManagerService.getTransaction();
		if (list.size() > 0) {
			BigDecimal tradeId; // 交易id
			String cardNo; // 持卡人卡号
			String cardCVV2; // 持卡人cvv2
			String cardExDate; // 持卡人信用卡有效期
			String tradeOrderNo; // 交易流水号
			BigDecimal tradeAmount; // 交易RMB金额
			String tradeRMBAmount; // 交易RMB金额
			String posNumber; // 终端号
			String posMerchantNo;// 商户号
			String posBillingAdd; // 账单地址
			String tradeState; // 交易状态
			String cardhorderEmail; // 持卡人邮箱
			String tradeUrl;// 交易地址
			Date tradeTime;// 交易日期
			String merchantOrderNo; // 商户订单
			if (list.size() > 0) {
				Object[] o = (Object[]) list.get(0);
				tradeId = (BigDecimal) o[0];
				if(o[4].toString().trim().length()>17){
					cardNo = AES.getCarNo(o[4].toString().trim());
				}else{
					cardNo = o[4].toString().trim();
				}
				if(o[5].toString().trim().length()>3){
					cardCVV2 = AES.getCarNo(o[5].toString().trim());
				}else{
					cardCVV2 = o[5].toString().trim();
				}
				if(o[6].toString().trim().length()>4){
					cardExDate = AES.getCarNo(o[6].toString().trim());
				}else{
					cardExDate = o[6].toString().trim();
				}
				
				//cardCVV2 = o[5].toString().trim();
				//cardExDate = o[6].toString().trim();
				tradeOrderNo = o[1].toString().trim();
				tradeAmount = (BigDecimal) o[11];
				tradeRMBAmount = o[2].toString().trim();
				posNumber = o[3].toString().trim();
				posMerchantNo = o[7].toString().trim();
				posBillingAdd = o[10].toString().trim();
				tradeState = o[9].toString().trim();
				cardhorderEmail = o[8].toString().trim();
				tradeUrl = o[12].toString().trim();
				tradeTime = (Date) o[13];
				merchantOrderNo = o[14].toString().trim();

				this.commonService
						.deleteBySql("update  international_tradeinfo t  "
								+ "set t.tradeState=substr(t.tradeState,1,12)||'1'||substr(t.tradeState,14,(length(t.tradeState)-1)) "
								+ "where t.id='" + tradeId + "'");

				System.out.println("自动处理交易" + tradeId);
				String sqlquery = "select substr(ti.tradeState,1,1) from InternationalTradeinfo ti where ti.id="
						+ tradeId;
				try {
					String str = (String) this.commonService
							.uniqueResult(sqlquery);
					if (!str.equals("2")) {
						System.out.println("已经处理过,跳出");
						return;
					}
				} catch (Exception e) {
					return;
					// TODO: handle exception
				}
				// ---------------------处理交易---------------------------------------------------------------

				///连接ECPSS系统   s-to-s模式支付
		        java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
		        String dateTime = format2.format(tradeTime);

				String md5src;  //加密字符串    
			    md5src = "1002" + tradeOrderNo + "3" + tradeRMBAmount + "en" + tradeUrl + dateTime + "sfepaymd";
			    String MD5info_; //MD5加密后的字符串
			    MD5 md5 = new MD5();
			    MD5info_ = md5.getMD5ofStr(md5src);
				String returnurl="https://security.sslepay.com/evippayment";
				URL url;
				System.out.println("post网址+++++++++++"+returnurl);
				try {
					url = new URL(returnurl);
					
					URLConnection connection = url.openConnection(); 
					connection.setDoOutput(true); 
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
					String parte = 
					"cardnum="+cardNo+
					"&cvv2="+cardCVV2+
					"&month="+cardExDate.substring(0,2)+
					"&year="+cardExDate.substring(2,4)+
					"&cardbank="+"bank"+
					
					"&MerNo="+"1002"+
					"&BillNo="+tradeOrderNo+
					"&Amount="+tradeRMBAmount+
					"&Currency="+"3"+
					"&email="+cardhorderEmail+
					"&Language="+"en"+
					"&MD5info="+MD5info_+
					
					"&dateTime="+dateTime+
					"&ip="+"127.0.0.1"+
					"&ReturnURL="+tradeUrl;
					
					out.write(parte); //这里组织提交信息 
					out.flush(); 
					out.close(); 
					//获取返回数据 
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
					String line = null; 
					StringBuffer content= new StringBuffer(); 
					while((line = in.readLine()) != null) 
					{ 
					   //line为返回值，这就可以判断是否成功、 
					    content.append(line);
					    System.out.println(content);
					} 
					Map responseFields = createMapFromResponse(content.toString().trim());
					// 金额:
					//String PaymentOrderNo = VcpUtil.null2unknown("PaymentOrderNo",responseFields);
					String Succeed = VcpUtil.null2unknown("Succeed",responseFields);
					String paymentmessage = VcpUtil.null2unknown("Result",responseFields);
					if(Succeed.equals("88")){
		    			//支付成功
		    			String message = "Payment Success!";
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "' where t.id='"
										+ tradeId
										+ "'");
		    		}else if(Succeed.equals("19")){
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1))' where t.id='"
										+ tradeId
										+ "'");
	
		    		}else{
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ paymentmessage
										+ "'  where t.id='"
										+ tradeId
										+ "'");
		    		}
					
				} catch (Exception e) {
					System.out.println("system has exception");
				}
				
				
			}
		}
	}

	public String buzero(String RMBMoney) {
		String RMB = "000000000000";
		String zero_12 = "000000000000";
		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		if (StringUtils.isNotBlank(RMBMoney)
				&& RMBMoney.replace(".", "").matches("\\d+")) {
			String refundRMBStr = Double.valueOf((decimalFormat.format(Double
					.valueOf(RMBMoney))))
					* 100 + "";
			String refundRMB_0 = zero_12
					+ refundRMBStr.substring(0, refundRMBStr.indexOf("."));
			RMB = refundRMB_0.substring(refundRMB_0.length() - 12, refundRMB_0
					.length());
		}
		return RMB;
	}
	private Map createMapFromResponse(String queryString) {
		Map map = new HashMap();
		StringTokenizer st = new StringTokenizer(queryString, "&");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			int i = token.indexOf('=');
			if (i > 0) {
				try {
					String key = token.substring(0, i);
					String value = URLDecoder.decode(token.substring(i + 1,
							token.length()));
					map.put(key, value);
				} catch (Exception ex) {
					// Do Nothing and keep looping through data
				}
			}
		}
		return map;
	}
	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}

	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

}
