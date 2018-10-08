package com.ecpss.action.pay;



import org.apache.http.client.methods.HttpPost;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import vpn.VpnUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.channel.InternationalMigsMerchantNo;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.MD5;

public class ChinaBankAction extends BaseAction {
	// 定义属性变量
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;

	private String BillNo;
	// private String Currency;
	private String tradeMoneyType;
	private String Amount;
	private Long Succeed;
	private String Result;
	// private String MD5info;
	private String md5Value;
	private String mD5key; // MD5key值

	private Long merchantno; // 商户ID
	private String merchantOrderNo; // 订单编号
	private String rorderno; // 交易流水号
	private Date tradetime; // 交易时间

	private String ordercount; // 支付金额
	private String language; // 支付语言
	private String bankName; // 支付银行名称
	private String ReturnURL;
	private String responseCode; // 支付结果代码
	private String currencyName; // 币种名称

	private String merchantType; // 商户类型

	private String vpc_Merchant = "0000";

	private String message = null;
	// private String remark = null;

	private String vpc_SecureHash; // 银行安全序列码

	private String status3DS;
	private String vCSCRequestCode;
	private String txnResponseCode;
	private String hashKeys = new String();
	private String hashValues = new String();

	
	private  String MD5info;
	
	
	//3d
	
	private String token_id;
	public String getMD5info() {
		return MD5info;
	}

	public void setMD5info(String mD5info) {
		MD5info = mD5info;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public String getResp_Code() {
		return Resp_Code;
	}

	public void setResp_Code(String resp_Code) {
		Resp_Code = resp_Code;
	}

	public String getHASH() {
		return HASH;
	}

	public void setHASH(String hASH) {
		HASH = hASH;
	}
	private String Resp_Code; 
	private String HASH;
	
	
	// 定义属性常量
	// 生产：12B9DF804E9FDC08E8049B35C8D3D4B20
	// 测试：461A89BB77F352F849BAE09EBD2D9910
	static String SECURE_SECRET = "2B9DF804E9FDC08E8049B35C8D3D4B20";
	// This is an array for creating hex chars
	static final char[] HEX_TABLE = new char[] { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
//3d fanhui
	public String  payResponsebod(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String hql = "from InternationalTradeinfo t where t.token_id='"
				+ this.token_id + "'";
		List<InternationalTradeinfo> tradel = this.commonService.list(hql);
		InternationalTradeinfo trade=tradel.get(0);
		InternationalCardholdersInfo icf = (InternationalCardholdersInfo) this.commonService
				.list(
						"from InternationalCardholdersInfo t where t.tradeId ='"
								+ trade.getId() + "' ").get(0);
		InternationalMerchant merchant = new InternationalMerchant();
		merchant = (InternationalMerchant) this.commonService.load(
				InternationalMerchant.class, trade.getMerchantId());
		InternationalMerchantChannels im = (InternationalMerchantChannels) this.commonService
				.load(InternationalMerchantChannels.class,
						trade.getTradeChannel());	
		
		MD5 md5 = new MD5();
		String MD5key=merchant.getMd5key();
		// 转换成标准金额
		Double ordercount2 = trade.getTradeAmount();

		DecimalFormat decimalFormat = new DecimalFormat(
				"##############0.00");
		String ordercountValue = decimalFormat.format(ordercount2);
		// 获取POS
		List<InternationalTerminalManager> it = this.commonService
				.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
						+ "where tm.id=mt.terminalId "
						+ "and tm.channelId='"
						+ im.getChannelId()
						+ "' "
						+ "and mt.isopen=1 "
						+ "and mt.merchantId="
						+ merchant.getId());		

		ordercount = ordercountValue + ""; // 交易金额
		tradeMoneyType = trade.getMoneyType() + ""; // 币种
		merchantno = trade.getMerchantId();
		merchantOrderNo = trade.getMerchantOrderNo();
		
		if(this.Resp_Code.equals("0000")){
			// 支付成功
			this.message = "Payment Success!";
			this.responseCode = "88";
			 this.MD5info = trade.getMerchantOrderNo()
					+ trade.getMoneyType() + ordercountValue
					+ responseCode + merchant.getMd5key();
			md5Value = md5.getMD5ofStr(MD5info);
			trade.setTradeState("1"
					+ trade.getTradeState().substring(1,
							trade.getTradeState().length()));
			trade.setRemark(message);
			this.ReturnURL=trade.getReturnUrl();
			this.commonService.update(trade);
//			this.commonService
//					.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//							+ message
//							+ "' where t.id='"
//							+ trade.getId() + "'");

			// ///---------------给持卡人发送邮件-----------------------////
//			EmailInfo emailinfo = new EmailInfo();
//			String mailinfo = emailinfo.getPaymentResultEmail(
//					icf.getEmail(),
//					trade.getTradeAmount(),
//					getStates().getCurrencyTypeByNo(
//							trade.getMoneyType().intValue()),
//					trade.getTradeUrl(), trade.getTradeTime(),
//					it.get(0).getBillingAddress(), trade.getMerchantOrderNo(),
//					trade.getOrderNo());
//			try {
//				// 发送邮件,如果发送失败插入数据库发送
//				CCSendMail.setSendMail(icf.getEmail(), mailinfo,
//						"sfepay@sfepay.com");
//				System.out.println("邮件立马发出");
//			} catch (Exception e) {
//				// 往数据库插入等待发送邮件
//				shopManagerService.addSendMessages(icf.getEmail(),
//						"sfepay@sfepay.com", mailinfo, "0");
//				System.out.println("邮件等待稍后发出");
//				return INPUT;
//			}
			return INPUT;
			
			
			
			
		}
		else{
			this.message = "Payment Declined!" ;
			this.responseCode = "0";
			MD5info = trade.getMerchantOrderNo()
					+ trade.getMoneyType() + ordercountValue
					+ responseCode + MD5key;
			md5Value = md5.getMD5ofStr(MD5info);
			trade.setTradeState("0"
					+ trade.getTradeState().substring(1,
							trade.getTradeState().length()));
			trade.setRemark(this.Resp_Code+message);
			this.ReturnURL=trade.getReturnUrl();
			this.commonService.update(trade);

			return INPUT;
		}
	}
	
// 3d tongzhi
	private String Trans_Type;
	private String Trans_Model;
	private String Merchant_Id;
	private String Author_Str;
	private String Terminal_Id;
	private String Invoice_No;
	private String Ref_No;
	private String Auth_Code;
	private String Order_No;
	private String Custom;
	private String Trans_Date;
	private String Trans_Time;
	private String ECI;
	private String Currency_Code_T;
	private String Amount_Loc;


	
	public String  payResponsebodnote(){
		//
		System.out.println("=====3d通道返回==================="+this.Resp_Code+"====="+this.token_id);
		if(this.Resp_Code.equals("0000")){
			if(!this.token_id.equals("")){
				String hql = "from InternationalTradeinfo t where t.token_id='"
						+ this.token_id + "'";
				List<InternationalTradeinfo> tradel = this.commonService.list(hql);
				InternationalTradeinfo trade=tradel.get(0);
				trade.setTradeState("1"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
                this.commonService.saveOrUpdate(trade);				
				
			}
		}else{
			String hql = "from InternationalTradeinfo t where t.token_id='"
					+ this.token_id + "'";
			List<InternationalTradeinfo> tradel = this.commonService.list(hql);
			InternationalTradeinfo trade=tradel.get(0);
			trade.setTradeState("0"
					+ trade.getTradeState().substring(1,
							trade.getTradeState().length()));
			trade.setRemark(this.Resp_Code+"Payment Declined");
            this.commonService.saveOrUpdate(trade);		
			
		}

		return "ok";
	}
	
	
	
	public String getmD5key() {
		return mD5key;
	}

	public void setmD5key(String mD5key) {
		this.mD5key = mD5key;
	}

	public Date getTradetime() {
		return tradetime;
	}

	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}

	public String getvCSCRequestCode() {
		return vCSCRequestCode;
	}

	public void setvCSCRequestCode(String vCSCRequestCode) {
		this.vCSCRequestCode = vCSCRequestCode;
	}

	public String getTrans_Type() {
		return Trans_Type;
	}

	public void setTrans_Type(String trans_Type) {
		Trans_Type = trans_Type;
	}

	public String getTrans_Model() {
		return Trans_Model;
	}

	public void setTrans_Model(String trans_Model) {
		Trans_Model = trans_Model;
	}

	public String getMerchant_Id() {
		return Merchant_Id;
	}

	public void setMerchant_Id(String merchant_Id) {
		Merchant_Id = merchant_Id;
	}

	public String getAuthor_Str() {
		return Author_Str;
	}

	public void setAuthor_Str(String author_Str) {
		Author_Str = author_Str;
	}

	public String getTerminal_Id() {
		return Terminal_Id;
	}

	public void setTerminal_Id(String terminal_Id) {
		Terminal_Id = terminal_Id;
	}

	public String getInvoice_No() {
		return Invoice_No;
	}

	public void setInvoice_No(String invoice_No) {
		Invoice_No = invoice_No;
	}

	public String getRef_No() {
		return Ref_No;
	}

	public void setRef_No(String ref_No) {
		Ref_No = ref_No;
	}

	public String getAuth_Code() {
		return Auth_Code;
	}

	public void setAuth_Code(String auth_Code) {
		Auth_Code = auth_Code;
	}

	public String getOrder_No() {
		return Order_No;
	}

	public void setOrder_No(String order_No) {
		Order_No = order_No;
	}

	public String getCustom() {
		return Custom;
	}

	public void setCustom(String custom) {
		Custom = custom;
	}

	public String getTrans_Date() {
		return Trans_Date;
	}

	public void setTrans_Date(String trans_Date) {
		Trans_Date = trans_Date;
	}

	public String getTrans_Time() {
		return Trans_Time;
	}

	public void setTrans_Time(String trans_Time) {
		Trans_Time = trans_Time;
	}

	public String getECI() {
		return ECI;
	}

	public void setECI(String eCI) {
		ECI = eCI;
	}

	public String getCurrency_Code_T() {
		return Currency_Code_T;
	}

	public void setCurrency_Code_T(String currency_Code_T) {
		Currency_Code_T = currency_Code_T;
	}

	public String getAmount_Loc() {
		return Amount_Loc;
	}

	public void setAmount_Loc(String amount_Loc) {
		Amount_Loc = amount_Loc;
	}

	// 支付响应返回结果
	public String payResponse() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();

			String authorizeno = null2unknown((String) request
					.getParameter("vpc_AuthorizeId"));
			String vipbacthno = (String) request
					.getParameter("vpc_TransactionNo");
			String migsterminalno = (String) request.getParameter("vpc_Merchant");
			txnResponseCode = (String) request
					.getParameter("vpc_TxnResponseCode");
			ordercount = (String) request.getParameter("vpc_Amount");// 银行返回的人民币金额
			rorderno = (String) request.getParameter("vpc_MerchTxnRef");
			InternationalTradeinfo trade = new InternationalTradeinfo();
			String hql = "from InternationalTradeinfo t where t.orderNo='"
					+ rorderno + "'";
			
			List<InternationalTradeinfo> tradl = this.commonService.list(hql);
			// // ECPSS流水号不唯一
			// if (tradl==null) {
			//
			// }else if(tradl.size()!=1){
			//				
			// }
			trade = tradl.get(0);
			InternationalCardholdersInfo icf = (InternationalCardholdersInfo) this.commonService
					.list(
							"from InternationalCardholdersInfo t where t.tradeId ='"
									+ trade.getId() + "' ").get(0);
			ordercount = trade.getTradeAmount() + ""; // 交易金额
			tradeMoneyType = trade.getMoneyType() + ""; // 币种
			merchantno = trade.getMerchantId();
			merchantOrderNo = trade.getMerchantOrderNo();
			String email = icf.getEmail();
			Date tradetime = trade.getTradeTime();
			// / StringBuffer channel = new StringBuffer(trade.getChannels());
			// / channel.delete(3, channel.length());
			// / String channels = channel.toString();
			// / String currencyName = tradeService.findParameter("moneyType",
			// Long.valueOf(tradeMoneyType));//币种缩写符
//			List<InternationalTerminalManager> timlist = this.commonService
//					.list(" from InternationalTerminalManager t where t.terminalNo='88888888'");
//			String merBill = "";
//			if (timlist.size() == 1) {
//				InternationalTerminalManager tim = timlist.get(0);
//				merBill = tim.getBillingAddress();
//			}

			bankName = "migs";
			// 获取银行的返回参数
			Map fields = new HashMap();
			for (Enumeration e = request.getParameterNames(); e
					.hasMoreElements();) {
				String fieldName = (String) e.nextElement();
				String fieldValue = request.getParameter(fieldName);
				if ((fieldValue != null) && (fieldValue.length() > 0)) {
					fields.put(fieldName, fieldValue);
				}
			}
			String vpc_Txn_Secure_Hash = null2unknown((String) fields
					.remove("vpc_SecureHash"));
			InternationalMerchant merchant = new InternationalMerchant();
			merchant = (InternationalMerchant) this.commonService.load(
					InternationalMerchant.class, trade.getMerchantId());
			
			String sql = "select c.channelName from InternationalTradeinfo ti,InternationalMerchantChannels mc,InternationalChannels c " +
			"where ti.tradeChannel=mc.id " +
			"and mc.channelId=c.id " +
			"and ti.id="+trade.getId();
			String channelname = (String) this.commonService.uniqueResult(sql);
			List<InternationalTerminalManager> it = this.commonService
			.list("select tm from InternationalMerchantTerminal mt,InternationalTerminalManager tm "
					+ "where tm.id=mt.terminalId "
					+ "and (tm.terminalNo like '7777%' or tm.terminalNo like '0788%')  "
					+ "and mt.merchantId="
					+ merchant.getId());
			
			
			if (SECURE_SECRET != null
					&& SECURE_SECRET.length() > 0
					&& (fields.get("vpc_TxnResponseCode") != null || fields
							.get("vpc_TxnResponseCode") != "No Value Returned")) {
				String secureHash = hashAllFields(fields);
				// System.out.println("vpc_Txn_Secure_Hash--------------"+vpc_Txn_Secure_Hash);
				// System.out.println("secureHash--------------"+secureHash);
				// if (vpc_Txn_Secure_Hash.equalsIgnoreCase(secureHash)) {
				if (txnResponseCode.equals("0")) {
					Succeed = 1l;// 插到数据库的状态
					responseCode = "88";
					message = getResponseDescription(txnResponseCode);
					this.commonService
					.deleteBySql("update  international_tradeinfo t  "
							+ "set t.VIPTerminalNo='" + migsterminalno + "'  "
							+ "where t.id='" + trade.getId() + "' ");
					// 向持卡人发送邮件
					// ====================正式使用要解开=============================
					// 邮件内容
					// 根据POS查询出账单地址

				} else {
					/*
					 * if(channels.equals("vip")){ Succeed="2"; }else{
					 * Succeed="0"; }
					 */
					Succeed = 0l;
					responseCode = "100" + txnResponseCode;
					message = "Your payment failed "
							+ getResponseDescription(txnResponseCode);
					if(channelname.startsWith("VVIP")){
						if(it.size()>0){
							Succeed = 2l;
							responseCode = "19";
							message = "Your payment is being processed";
						}
					}
				}
				/*
				 * }else { Succeed = 0l; message = "INVALID HASH"; }
				 */
			} else {
				Succeed = 0l;
				message = "No Value Returned";
			}
			
			// 根据银行返回的结果修改交易表
			Long id = null;
			if (trade != null) {
				id = trade.getId();

				if (txnResponseCode.equals("0")) {
					merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
							+ trade.getTradeAmount());
					this.commonService.update(merchant);
					Succeed = 1l;
				}
				//如果是VVIP   查询到单独终端号就为待处理   查询不到 就是失败
				System.out.println("通道"+channelname+"Response:"+txnResponseCode);
				if(channelname.startsWith("VVIP") && !txnResponseCode.equals("0")){
					if(it.size()>0){
						this.commonService
						.deleteBySql("update  international_tradeinfo t  "
								+ "set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1)) ,"
								+ "t.remark='VVIP Processed', "
								+ "t.VIPTerminalNo='" + it.get(0).getTerminalNo() + "'  "
								+ "where t.id='" + trade.getId() + "' ");
						System.out.println("VVIP终端号:"+trade.getVIPTerminalNo());
						String sqltrade = "select ti.id,"
							+ // 0
							"ti.orderNo,"
							+ // 1
							"ti.rmbAmount,"
							+ // 2
							"ti.VIPTerminalNo,"
							+ // 3
							"ci.cardNo,"
							+ // 4
							"ci.cvv2,"
							+ // 5
							"ci.expiryDate,"
							+ // 6
							"tm.merchantNo,"
							+ // 7
							"ci.email,"
							+ // 8
							"ti.moneyType,"
							+ // 9
							"tm.billingAddress ,"
							+ // 10
							"ti.tradeAmount,"
							+ // 11
							"ti.tradeUrl,"
							+ // 12
							"ti.tradeTime,"
							+ // 13
							"ti.merchantOrderNo "
							+ "from InternationalTradeinfo ti,"
							+ "InternationalCardholdersInfo ci,"
							+ "InternationalTerminalManager tm "
							+ " where ti.id=ci.tradeId "
							+ " and tm.terminalNo=ti.VIPTerminalNo "
							+ " and ti.id="+trade.getId();
						List list = this.commonService.list(sqltrade);
						if (list.size() > 0) {
							Long tradeId; // 交易id
							String cardNo; // 持卡人卡号
							String cardCVV2; // 持卡人cvv2
							String cardExDate; // 持卡人信用卡有效期
							String tradeOrderNo; // 交易流水号
							Double tradeAmount; // 交易RMB金额
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
								tradeId = (Long) o[0];
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
								tradeAmount = (Double) o[11];
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

								System.out.println("vvip自动处理交易" + tradeId);
								String sqlquery = "select substr(ti.tradeState,1,1) from InternationalTradeinfo ti where ti.id="
										+ tradeId;
								try {
									String str = (String) this.commonService
											.uniqueResult(sqlquery);
									if (!str.equals("2")) {
										System.out.println("已经处理过,跳出");
										
									}
								} catch (Exception e) {
									//return;
									// TODO: handle exception
								}
								// ---------------------处理交易---------------------------------------------------------------
								// 交易查询
								DCCMessage dcc = new DCCMessage();
								dcc.setMessageType("0800");// 类型
								dcc.setPrimary_Account_Number(cardNo);// 账号2
								dcc.setProcessing_Code("970000");// 处理代码3
								dcc.setAmount_Transaction_Local(this
										.buzero(tradeRMBAmount + ""));// 4
								// 本地交易金额
								dcc.setSYSTEMS_TRACE_AUDIT_NUMBER(tradeOrderNo.substring(
										tradeOrderNo.length() - 6, tradeOrderNo.length()));// 11
								// 交易流水号
								dcc.setDATE_EXPIRATION(cardExDate.substring(2, 4)
										+ cardExDate.substring(0, 2));// 14 有效期
								dcc.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
								dcc.setNETWORK_INTL_IDENTIFIER("0098");// 24 收单商户号
								dcc.setPOS_CONDITION_CODE("00");// 25 商户编码
								dcc.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);// 41 商户终端号
								dcc.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42 商户编号
								dcc.setInvoice_Number(tradeOrderNo.substring(tradeOrderNo
										.length() - 6, tradeOrderNo.length()));// 62
								DccUtil dc = new DccUtil();
								dc.setDccMessage(dcc);

								try {
									dcc = dc.getDccMessage();
									if (cardNo.startsWith("5")) {
										dcc.setRESPONSE_CODE("YX");
									}
								} catch (Exception ex) {
									// 连接不了DCC就返回为待处理
									System.out.println("vvip出现异常,跳出");
									//return;
									// 计算商户的返回结果Md5
									MD5 md5 = new MD5();
									responseCode = "0";
									message = "Payment Declined";
									mD5key = merchant.getMd5key();
									String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
											+ ordercount + responseCode + mD5key;
									this.ReturnURL = trade.getReturnUrl();
									md5Value = md5.getMD5ofStr(mD5str);
									
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
											+ message
											+ "' ,t.VIPDisposePorson='System' "
											+ " ,t.VIPDisposeDate=sysdate "
											+ "  where t.id='" + tradeId + "'");
									
									return SUCCESS;
								}
								System.out.println("VVIP自动处理DCC走++++++++++  "
										+ dcc.getRESPONSE_CODE());
								// 做DCC交易
								if(StringUtils.isBlank(dcc.getRESPONSE_CODE())){
									System.out.println("返回码空,跳出");
									//return;
									// 计算商户的返回结果Md5
									MD5 md5 = new MD5();
									responseCode = "19";
									//message = "Payment Declined";
									mD5key = merchant.getMd5key();
									String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
											+ ordercount + responseCode + mD5key;
									this.ReturnURL = trade.getReturnUrl();
									md5Value = md5.getMD5ofStr(mD5str);
									this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradestate,2,(length(t.tradestate)-1))," +
											"t.VIPDisposePorson='System' "
											+ " ,t.VIPDisposeDate=sysdate "
											+ "  where t.id='" + tradeId + "'");
									return SUCCESS;
								}
								if (dcc.getRESPONSE_CODE().equals("YY")) {
									// 交易

									DCCMessage msg2 = new DCCMessage();
									msg2.setMessageType("0200");// 类型
									msg2.setPrimary_Account_Number(cardNo);// 账号2
									msg2.setProcessing_Code("000000");// 处理代码3
									msg2.setAmount_Transaction_Local(this.buzero(tradeRMBAmount
											+ ""));// 4
									// 本地交易金额
									msg2.setAmount_Transaction_Foreign(dcc
											.getAmount_Transaction_Foreign());// 5
									// 0810
									msg2.setConversion_Rate(dcc.getConversion_Rate());// 9
									// 0810
									msg2.setSYSTEMS_TRACE_AUDIT_NUMBER(tradeOrderNo.substring(
											tradeOrderNo.length() - 6, tradeOrderNo.length()));// 11
									// 交易流水号
									msg2.setDATE_EXPIRATION(cardExDate.substring(2, 4)
											+ cardExDate.substring(0, 2));// 14 有效期
									msg2.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
									msg2.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
									msg2.setPOS_CONDITION_CODE("00");// 25 商户编码
									// msg2.setRETRIEVAL_REFERENCE_NUMBER("");//37
									msg2.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);// 41
									// 商户终端号
									msg2.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42 商户编号
									msg2.setCurrency_Code_Foreign(dcc
											.getCurrency_Code_Foreign());// 49
									// 货币代码-----0810
									msg2.setCVV2_OR_CVC2(cardCVV2);// cv2
									msg2.setInvoice_Number(tradeOrderNo.substring(tradeOrderNo
											.length() - 6, tradeOrderNo.length()));// 62
									DccUtil dc2 = new DccUtil();
									dc2.setDccMessage(msg2);
									try {
										System.out.println("自动处理交易2" + tradeId);
										// String sqlquery1 = "select substr(ti.tradeState,1,1)
										// from InternationalTradeinfo ti where ti.id="
										// + tradeId;
										try {
											String str = (String) this.commonService
													.uniqueResult(sqlquery);
											if (!str.equals("2")) {
												System.out.println("已经处理过,跳出");
												//return;
												// 计算商户的返回结果Md5
												MD5 md5 = new MD5();
												responseCode = "0";
												message = "Payment Declined";
												mD5key = merchant.getMd5key();
												String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
														+ ordercount + responseCode + mD5key;
												this.ReturnURL = trade.getReturnUrl();
												md5Value = md5.getMD5ofStr(mD5str);
												this.commonService
												.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
														+ message
														+ "' ,t.VIPDisposePorson='System' "
														+ " ,t.VIPDisposeDate=sysdate "
														+ "  where t.id='" + tradeId + "'");
												return SUCCESS;
											}
										} catch (Exception e) {
											System.out.println("出现异常,跳出2");
											// 计算商户的返回结果Md5
											MD5 md5 = new MD5();
											responseCode = "0";
											message = "Payment Declined";
											mD5key = merchant.getMd5key();
											String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
													+ ordercount + responseCode + mD5key;
											this.ReturnURL = trade.getReturnUrl();
											md5Value = md5.getMD5ofStr(mD5str);
											this.commonService
											.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
													+ message
													+ "' ,t.VIPDisposePorson='System' "
													+ " ,t.VIPDisposeDate=sysdate "
													+ "  where t.id='" + tradeId + "'");
											return SUCCESS;
										}
										msg2 = dc2.getDccMessage();
									} catch (Exception ex) {
										// 交易冲正
										DCCMessage msg6 = new DCCMessage();
										msg6.setMessageType("0400");// 类型
										msg6.setPrimary_Account_Number(cardNo);// 账号2
										msg6.setProcessing_Code("000000");// 处理代码3
										msg6.setAmount_Transaction_Local(this
												.buzero(tradeRMBAmount + ""));// 4 本地交易金额
										msg6.setAmount_Transaction_Foreign(dcc
												.getAmount_Transaction_Foreign());// 5 0810
										msg6.setConversion_Rate(dcc.getConversion_Rate());// 9
										// 0810
										msg6.setSYSTEMS_TRACE_AUDIT_NUMBER(tradeOrderNo
												.substring(tradeOrderNo.length() - 6,
														tradeOrderNo.length()));// 11
										// 交易流水号
										msg6.setDATE_EXPIRATION(cardExDate.substring(2, 4)
												+ cardExDate.substring(0, 2));// 14
										// 有效期
										msg6.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22
										// POS进入模式
										msg6.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
										msg6.setPOS_CONDITION_CODE("00");// 25 商户编码
										msg6.setRETRIEVAL_REFERENCE_NUMBER(msg2
												.getRETRIEVAL_REFERENCE_NUMBER());// 37
										msg6.setCARD_ACCEPTOR_TERMINAL_ID(posNumber);// 41
										// 商户终端号
										msg6.setCARD_ACCEPTOR_ID_CODE(posMerchantNo);// 42
										// 商户编号
										msg6.setCurrency_Code_Foreign(dcc
												.getCurrency_Code_Foreign());// 49
										// 货币代码-----0810
										msg6.setInvoice_Number(tradeOrderNo.substring(
												tradeOrderNo.length() - 6, tradeOrderNo
														.length()));// 62
										DccUtil dc6 = new DccUtil();
										dc6.setDccMessage(msg6);

										msg6 = dc6.getDccMessage();
										System.out
												.println("===============================yy交易冲正(撤销冲正):"
														+ msg6.getRESPONSE_CODE());
										// 计算商户的返回结果Md5
										MD5 md5 = new MD5();
										responseCode = "0";
										message = "Payment Declined";
										mD5key = merchant.getMd5key();
										String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
												+ ordercount + responseCode + mD5key;
										this.ReturnURL = trade.getReturnUrl();
										md5Value = md5.getMD5ofStr(mD5str);
										return SUCCESS;
										// return;
									}
									if(StringUtils.isBlank(msg2.getRESPONSE_CODE())){
										System.out.println("返回码空,跳出");
										//return;
										// 计算商户的返回结果Md5
										MD5 md5 = new MD5();
										responseCode = "0";
										message = "Payment Declined";
										mD5key = merchant.getMd5key();
										String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
												+ ordercount + responseCode + mD5key;
										this.ReturnURL = trade.getReturnUrl();
										md5Value = md5.getMD5ofStr(mD5str);
										this.commonService
										.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
												+ message
												+ "' ,t.VIPDisposePorson='System' "
												+ " ,t.VIPDisposeDate=sysdate "
												+ "  where t.id='" + tradeId + "'");
										return SUCCESS;
									}
									if (msg2.getRESPONSE_CODE().equals("00")) {
										// 交易成功
										message = "Payment Success!";

										this.commonService
												.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
														+ message
														+ "' ,t.VIPAuthorizationNo='"
														+ msg2
																.getAUTH_IDENTIFICATION_RESPONSE()
														+ "' ,t.VIPDisposePorson='System' "
														+ " ,t.VIPDisposeDate=sysdate "
														+ " ,t.VIPBatchNo='XXXXXX',t.VIPTerminalNo='"
														+ posNumber
														+ "' where t.id='"
														+ tradeId + "'");
										responseCode = "88";
										message = "Payment Success!";
										// ****************发送持卡人邮件****************************//
										merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
												+ trade.getTradeAmount());
										this.commonService.update(merchant);
										Succeed = 1l;
										// return;
									} else if (msg2.getRESPONSE_CODE().equals("03")
											|| msg2.getRESPONSE_CODE().equals("12")
											|| msg2.getRESPONSE_CODE().equals("13")
											|| msg2.getRESPONSE_CODE().equals("30")
											|| msg2.getRESPONSE_CODE().equals("63")
											|| msg2.getRESPONSE_CODE().equals("68")
											|| msg2.getRESPONSE_CODE().equals("76")
											|| msg2.getRESPONSE_CODE().equals("77")
											|| msg2.getRESPONSE_CODE().equals("89")
											|| msg2.getRESPONSE_CODE().equals("94")
											|| msg2.getRESPONSE_CODE().equals("95")
											|| msg2.getRESPONSE_CODE().equals("96")
											|| msg2.getRESPONSE_CODE().equals("98")
											|| msg2.getRESPONSE_CODE().equals("99")) {
										System.out.println("出现异常状态码"+msg2.getRESPONSE_CODE());
										//return;
										// 交易失败
										message = "Payment Declined!"
												+ msg2.getRESPONSE_CODE();
										this.responseCode = "0";
										this.commonService
												.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
														+ message
														+ "' ,t.VIPDisposePorson='System' "
														+ " ,t.VIPDisposeDate=sysdate "
														+ "  where t.id='" + tradeId + "'");
										this.Succeed=0L;	
									} else {
										// 交易失败
										message = "Payment Declined!"
												+ msg2.getRESPONSE_CODE();
										this.responseCode = msg2.getRESPONSE_CODE();
										this.commonService
												.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
														+ message
														+ "' ,t.VIPDisposePorson='System' "
														+ " ,t.VIPDisposeDate=sysdate "
														+ "  where t.id='" + tradeId + "'");
										this.Succeed=0L;									
										// return;
									}

								} else if (dcc.getRESPONSE_CODE().equals("YX")) {
									String ter = "select tm from InternationalTerminalManager tm where tm.channelId='2' and tm.terminalNo='"
											+ posNumber.trim() + "' ";
									List list1 = this.commonService.list(ter);
									InternationalTerminalManager tm;
									List<InternationalTerminalManager> listt;
									if (list1.size() > 0) {
										tm = (InternationalTerminalManager) list1.get(0);
										String ter1 = "select tm from InternationalTerminalManager tm where tm.terminalNo='"
												+ tm.getAndterminalNo() + "' ";
										listt = this.commonService.list(ter1);

										System.out.println("终端号：+++++++++++++++"
												+ listt.get(0).getTerminalNo());
										DCCMessage dcc3 = new DCCMessage();
										dcc3.setMessageType("0200");// 类型
										dcc3.setPrimary_Account_Number(cardNo);// 账号2
										dcc3.setProcessing_Code("000000");// 处理代码3
										dcc3.setAmount_Transaction_Local(this
												.buzero(tradeRMBAmount + ""));// 4
										// 本地交易金额
										dcc3.setSYSTEMS_TRACE_AUDIT_NUMBER(tradeOrderNo
												.substring(tradeOrderNo.length() - 6,
														tradeOrderNo.length()));// 11
										// 交易流水号
										dcc3.setDATE_EXPIRATION(cardExDate.substring(2, 4)
												+ cardExDate.substring(0, 2));// 14 有效期
										dcc3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22
										// POS进入模式
										dcc3.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
										dcc3.setCARD_ACCEPTOR_TERMINAL_ID(listt.get(0)
												.getTerminalNo());// 41
										// 商户终端号
										dcc3.setCARD_ACCEPTOR_ID_CODE(listt.get(0)
												.getMerchantNo());// 42
										// 商户编号
										dcc3.setCVV2_OR_CVC2(cardCVV2);// cv2 61
										dcc3.setInvoice_Number(tradeOrderNo.substring(
												tradeOrderNo.length() - 6, tradeOrderNo
														.length()));// 62
										DccUtil dc3 = new DccUtil();
										dc3.setDccMessage(dcc3);
										try {
											System.out.println("自动处理交易3" + tradeId);
											// String sqlquery1 = "select
											// substr(ti.tradeState,1,1) from
											// InternationalTradeinfo ti where ti.id="
											// + tradeId;
											try {
												String str = (String) this.commonService
														.uniqueResult(sqlquery);
												if (!str.equals("2")) {
													System.out.println("已经处理过,跳出");
													//return;
													// 计算商户的返回结果Md5
													MD5 md5 = new MD5();

													mD5key = merchant.getMd5key();
													String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
															+ ordercount + responseCode + mD5key;
													this.ReturnURL = trade.getReturnUrl();
													md5Value = md5.getMD5ofStr(mD5str);
													// 交易失败
													this.commonService
													.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
															+ message
															+ "' ,t.VIPDisposePorson='System' "
															+ " ,t.VIPDisposeDate=sysdate "
															+ "  where t.id='" + tradeId + "'");	
													return SUCCESS;
												}
											} catch (Exception e) {
												System.out.println("出现异常,跳出3");
												//return;
												// TODO: handle exception
												// 计算商户的返回结果Md5
												MD5 md5 = new MD5();

												mD5key = merchant.getMd5key();
												String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
														+ ordercount + responseCode + mD5key;
												this.ReturnURL = trade.getReturnUrl();
												md5Value = md5.getMD5ofStr(mD5str);
												this.commonService
												.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
														+ message
														+ "' ,t.VIPDisposePorson='System' "
														+ " ,t.VIPDisposeDate=sysdate "
														+ "  where t.id='" + tradeId + "'");
												return SUCCESS;
											}
											dcc3 = dc3.getDccMessage();
										} catch (Exception ex) {

											DCCMessage msg7 = new DCCMessage();
											msg7.setMessageType("0400");// 类型
											msg7.setPrimary_Account_Number(cardNo);// 账号2
											msg7.setProcessing_Code("000000");// 处理代码3
											msg7.setAmount_Transaction_Local(this
													.buzero(tradeRMBAmount + ""));// 4 本地交易金额
											// msg7.setAmount_Transaction_Foreign(dcc.getAmount_Transaction_Foreign());//5
											// 0810
											// msg7.setConversion_Rate(dcc.getConversion_Rate());//9
											// 0810
											msg7.setSYSTEMS_TRACE_AUDIT_NUMBER(tradeOrderNo
													.substring(tradeOrderNo.length() - 6,
															tradeOrderNo.length()));// 11
											// 交易流水号
											msg7.setDATE_EXPIRATION(cardExDate.substring(2, 4)
													+ cardExDate.substring(0, 2));// 14
											// 有效期
											msg7.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22
											// POS进入模式
											msg7.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
											msg7.setPOS_CONDITION_CODE("00");// 25 商户编码
											msg7.setRETRIEVAL_REFERENCE_NUMBER(dcc3
													.getRETRIEVAL_REFERENCE_NUMBER());// 37
											msg7.setCARD_ACCEPTOR_TERMINAL_ID(listt.get(0)
													.getTerminalNo());// 41
											// 商户终端号
											msg7.setCARD_ACCEPTOR_ID_CODE(listt.get(0)
													.getMerchantNo());// 42
											// 商户编号
											// msg7.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());//49
											// 货币代码-----0810
											// msg7.setCVV2_OR_CVC2(cvv2);//cv2
											msg7.setInvoice_Number(tradeOrderNo.substring(
													tradeOrderNo.length() - 6, tradeOrderNo
															.length()));// 62
											DccUtil dc7 = new DccUtil();
											dc7.setDccMessage(msg7);

											msg7 = dc7.getDccMessage();
											System.out.println("=====================yx交易冲正:"
													+ msg7.getRESPONSE_CODE());
											// return;
										}
										if (dcc3.getRESPONSE_CODE().equals("00")) {
											// 交易成功
											message = "Payment Success!";

											this.commonService
													.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
															+ message
															+ "' ,t.VIPAuthorizationNo='"
															+ dcc3
																	.getAUTH_IDENTIFICATION_RESPONSE()
															+ "' ,t.VIPDisposePorson='System' "
															+ " ,t.VIPDisposeDate=sysdate "
															+ " ,t.VIPBatchNo='XXXXXX',t.VIPTerminalNo='"
															+ posNumber
															+ "' where t.id='"
															+ tradeId + "'");
											//清除持卡人cvv,有效期
											
											// ****************发送持卡人邮件****************************//
											merchant.setMonthTradeMoney(merchant.getMonthTradeMoney()
													+ trade.getTradeAmount());
											this.commonService.update(merchant);
											Succeed = 1l;
											//message = "Payment Success.";
											this.responseCode = "88";
											// return;
										} else if (dcc3.getRESPONSE_CODE().equals("03")
												|| dcc3.getRESPONSE_CODE().equals("12")
												|| dcc3.getRESPONSE_CODE().equals("13")
												|| dcc3.getRESPONSE_CODE().equals("30")
												|| dcc3.getRESPONSE_CODE().equals("63")
												|| dcc3.getRESPONSE_CODE().equals("68")
												|| dcc3.getRESPONSE_CODE().equals("76")
												|| dcc3.getRESPONSE_CODE().equals("77")
												|| dcc3.getRESPONSE_CODE().equals("89")
												|| dcc3.getRESPONSE_CODE().equals("94")
												|| dcc3.getRESPONSE_CODE().equals("95")
												|| dcc3.getRESPONSE_CODE().equals("96")
												|| dcc3.getRESPONSE_CODE().equals("98")
												|| dcc3.getRESPONSE_CODE().equals("99")) {
											// 传递的参数
											System.out.println("出现异常状态码1");
											//return;
											// 计算商户的返回结果Md5
											MD5 md5 = new MD5();
											message = "Payment Declined!";
											this.responseCode = dcc3.getRESPONSE_CODE();
											mD5key = merchant.getMd5key();
											String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
													+ ordercount + responseCode + mD5key;
											this.ReturnURL = trade.getReturnUrl();
											md5Value = md5.getMD5ofStr(mD5str);
											return SUCCESS;
										} else {
											// 交易失败
											message = "Payment Declined!"+dcc3.getRESPONSE_CODE();
											this.responseCode = dcc3.getRESPONSE_CODE();
											this.commonService
													.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
															+ message
															+ "' ,t.VIPDisposePorson='System' "
															+ " ,t.VIPDisposeDate=sysdate "
															+ "  where t.id='" + tradeId + "'");
											Succeed = 0L;
										}
									}
								}
							}
						}
					}else{
						this.commonService
						.deleteBySql("update  international_tradeinfo t  "
								+ "set t.tradestate='"
								+ Succeed
								+ "'||substr(t.tradestate,2,(length(t.tradestate)-1)) ,"
								+ "t.remark='" + message + "', "
								+ "t.VIPAuthorizationNo='" + authorizeno + "',"
								+ "t.VIPBatchNo='" + vipbacthno + "' "
								+ "where t.id='" + trade.getId() + "' ");
					}
					
				}else{
					this.commonService
					.deleteBySql("update  international_tradeinfo t  "
							+ "set t.tradestate='"
							+ Succeed
							+ "'||substr(t.tradestate,2,(length(t.tradestate)-1)) ,"
							+ "t.remark='" + message + "', "
							+ "t.VIPAuthorizationNo='" + authorizeno + "',"
							+ "t.VIPBatchNo='" + vipbacthno + "' "
							+ "where t.id='" + trade.getId() + "' ");
				}
				// 清除持卡人cvv,有效期
//				this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ trade.getId() + "'");
			}
			
			// 计算商户的返回结果Md5
			MD5 md5 = new MD5();

			mD5key = merchant.getMd5key();
			String mD5str = trade.getMerchantOrderNo() + trade.getMoneyType()
					+ ordercount + responseCode + mD5key;
			this.ReturnURL = trade.getReturnUrl();
			md5Value = md5.getMD5ofStr(mD5str);
			// /---------------给持卡人发送邮件-----------------------////
			List<InternationalMigsMerchantNo> tmm = this.commonService
					.list("select tm from InternationalMigsMerchantNo tm where tm.bankMerchantId='"
							+ trade.getVIPTerminalNo() + "' ");
			String terminalNo = null;
			if (tmm.size() > 0) {
				InternationalMigsMerchantNo tm = tmm.get(0);
				terminalNo = tm.getBillingaddress();
			}
			EmailInfo emailinfo = new EmailInfo();
			String mailinfo = emailinfo.getPaymentResultEmail(icf.getEmail(),
					trade.getTradeAmount(), getStates().getCurrencyTypeByNo(
							trade.getMoneyType().intValue()), trade
							.getTradeUrl(), trade.getTradeTime(), terminalNo,
					trade.getMerchantOrderNo(), trade.getOrderNo(), merchant);
			try {
				// 发送邮件,如果发送失败插入数据库发送
				if (Succeed == 1L) {
					if(merchant.getStatutes().substring(4, 5).equals("0")){
						CCSendMail.setSendMail(icf.getEmail(), mailinfo,
						"ecpss@ecpss.cc");
						System.out.println("VC邮件立马发出");
					}else{
						System.out.println("设置商户成功不发邮件");
					}
					
					
				}
			} catch (Exception e) {
				// 往数据库插入等待发送邮件
				shopManagerService.addSendMessages(icf.getEmail(),
						"ecpss@ecpss.cc", mailinfo, "0");
				System.out.println("邮件等待稍后发出");
				return SUCCESS;
			}

			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	

	/**
	 * 生成MD5码的方法
	 */
	String hashAllFields(Map fields) {

		// create a list and sort it
		List fieldNames = new ArrayList(fields.keySet());
		Collections.sort(fieldNames);

		// create a buffer for the md5 input and add the secure secret first
		StringBuffer buf = new StringBuffer();
		buf.append(SECURE_SECRET);

		// iterate through the list and add the remaining field values
		Iterator itr = fieldNames.iterator();

		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) fields.get(fieldName);
			// System.out.println("fieldName: "+fieldName + "**************"
			// +"fieldValue: "+fieldValue);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				buf.append(fieldValue);
			}
		}

		MessageDigest md5 = null;
		byte[] ba = null;

		// create the md5 hash and ISO-8859-1 encode it
		try {
			md5 = MessageDigest.getInstance("MD5");
			ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
		} catch (Exception e) {
		} // wont happen

		return hex(ba);

	} // end hashAllFields()

	// ----------------------------------------------------------------------------

	/*
	 * This method takes a byte array and returns a string of its contents
	 * 
	 * @param input - byte array containing the input data @return String
	 * containing the output String
	 */
	static String hex(byte[] input) {
		// create a StringBuffer 2x the size of the hash array
		StringBuffer sb = new StringBuffer(input.length * 2);

		// retrieve the byte array data, convert it to hex
		// and add it to the StringBuffer
		for (int i = 0; i < input.length; i++) {
			sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
			sb.append(HEX_TABLE[input[i] & 0xf]);
		}
		return sb.toString();
	}

	// ----------------------------------------------------------------------------

	/*
	 * This method takes a data String and returns a predefined value if empty
	 * If data Sting is null, returns string "No Value Returned", else returns
	 * input
	 * 
	 * @param in String containing the data String @return String containing the
	 * output String
	 */
	private static String null2unknown(String in) {
		if (in == null || in.length() == 0) {
			return "No Value Returned";
		} else {
			return in;
		}
	}

	String getResponseDescription(String vResponseCode) {

		String result = "";
		// check if a single digit response code
		// Java cannot switch on a string so turn everything to a char
		char input = vResponseCode.charAt(0);
		switch (input) {
		case '0':
			result = "Transaction Successful";
			break;
		case '1':
			result = "Unknown Error";
			break;
		case '2':
			result = "Bank Declined Transaction";
			break;
		case '3':
			result = "No Reply from Bank";
			break;
		case '4':
			result = "Expired Card";
			break;
		case '5':
			result = "Insufficient Funds";
			break;
		case '6':
			result = "Error Communicating with Bank";
			break;
		case '7':
			result = "Payment Server System Error";
			break;
		case '8':
			result = "Transaction Type Not Supported";
			break;
		case '9':
			result = "Bank declined transaction (Do not contact Bank)";
			break;
		case 'A':
			result = "Transaction Aborted";
			break;
		case 'C':
			result = "Transaction Cancelled";
			break;
		case 'D':
			result = "Deferred transaction has been received and is awaiting processing";
			break;
		case 'F':
			result = "3D Secure Authentication failed";
			break;
		case 'I':
			result = "Card Security Code verification failed";
			break;
		case 'L':
			result = "Shopping Transaction Locked (Please try the transaction again later)";
			break;
		case 'N':
			result = "Cardholder is not enrolled in Authentication Scheme";
			break;
		case 'P':
			result = "Transaction has been received by the Payment Adaptor and is being processed";
			break;
		case 'R':
			result = "Transaction was not processed - Reached limit of retry attempts allowed";
			break;
		case 'S':
			result = "Duplicate SessionID (OrderInfo)";
			break;
		case 'T':
			result = "Address Verification Failed";
			break;
		case 'U':
			result = "Card Security Code Failed";
			break;
		case 'V':
			result = "Address Verification and Card Security Code Failed";
			break;
		case '?':
			result = "Transaction status is unknown";
			break;
		default:
			result = "Unable to be determined";
		}
		return result;
	} // getResponseDescription()

	// ----------------------------------------------------------------------------

	/**
	 * This function uses the QSI AVS Result Code retrieved from the Digital
	 * Receipt and returns an appropriate description for this code.
	 * 
	 * @param vAVSResultCode
	 *            String containing the vpc_AVSResultCode
	 * @return description String containing the appropriate description
	 */
	private String displayAVSResponse(String vAVSResultCode) {

		String result = "";
		if (vAVSResultCode != null || vAVSResultCode.length() == 0) {

			if (vAVSResultCode.equalsIgnoreCase("Unsupported")
					|| vAVSResultCode.equalsIgnoreCase("No Value Returned")) {
				result = "AVS not supported or there was no AVS data provided";
			} else {
				// Java cannot switch on a string so turn everything to a char
				char input = vAVSResultCode.charAt(0);

				switch (input) {
				case 'X':
					result = "Exact match - address and 9 digit ZIP/postal code";
					break;
				case 'Y':
					result = "Exact match - address and 5 digit ZIP/postal code";
					break;
				case 'S':
					result = "Service not supported or address not verified (international transaction)";
					break;
				case 'G':
					result = "Issuer does not participate in AVS (international transaction)";
					break;
				case 'A':
					result = "Address match only";
					break;
				case 'W':
					result = "9 digit ZIP/postal code matched, Address not Matched";
					break;
				case 'Z':
					result = "5 digit ZIP/postal code matched, Address not Matched";
					break;
				case 'R':
					result = "Issuer system is unavailable";
					break;
				case 'U':
					result = "Address unavailable or not verified";
					break;
				case 'E':
					result = "Address and ZIP/postal code not provided";
					break;
				case 'N':
					result = "Address and ZIP/postal code not matched";
					break;
				case '0':
					result = "AVS not requested";
					break;
				default:
					result = "Unable to be determined";
				}
			}
		} else {
			result = "null response";
		}
		return result;
	}

	// ----------------------------------------------------------------------------

	/**
	 * This function uses the QSI CSC Result Code retrieved from the Digital
	 * Receipt and returns an appropriate description for this code.
	 * 
	 * @param vCSCResultCode
	 *            String containing the vpc_CSCResultCode
	 * @return description String containing the appropriate description
	 */
	private String displayCSCResponse(String vCSCResultCode) {

		String result = "";
		if (vCSCResultCode != null || vCSCResultCode.length() == 0) {

			if (vCSCResultCode.equalsIgnoreCase("Unsupported")
					|| vCSCResultCode.equalsIgnoreCase("No Value Returned")) {
				result = "CSC not supported or there was no CSC data provided";
			} else {
				// Java cannot switch on a string so turn everything to a char
				char input = vCSCResultCode.charAt(0);

				switch (input) {
				case 'M':
					result = "Exact code match";
					break;
				case 'S':
					result = "Merchant has indicated that CSC is not present on the card (MOTO situation)";
					break;
				case 'P':
					result = "Code not processed";
					break;
				case 'U':
					result = "Card issuer is not registered and/or certified";
					break;
				case 'N':
					result = "Code invalid or not matched";
					break;
				default:
					result = "Unable to be determined";
				}
			}

		} else {
			result = "null response";
		}
		return result;
	}

	// ----------------------------------------------------------------------------

	/**
	 * This method uses the 3DS verStatus retrieved from the Response and
	 * returns an appropriate description for this code.
	 * 
	 * @param vpc_VerStatus
	 *            String containing the status code
	 * @return description String containing the appropriate description
	 */
	private String getStatusDescription(String vStatus) {

		String result = "";
		if (vStatus != null && !vStatus.equals("")) {

			if (vStatus.equalsIgnoreCase("Unsupported")
					|| vStatus.equals("No Value Returned")) {
				result = "3DS not supported or there was no 3DS data provided";
			} else {

				// Java cannot switch on a string so turn everything to a
				// character
				char input = vStatus.charAt(0);

				switch (input) {
				case 'Y':
					result = "The cardholder was successfully authenticated.";
					break;
				case 'E':
					result = "The cardholder is not enrolled.";
					break;
				case 'N':
					result = "The cardholder was not verified.";
					break;
				case 'U':
					result = "The cardholder's Issuer was unable to authenticate due to some system error at the Issuer.";
					break;
				case 'F':
					result = "There was an error in the format of the request from the merchant.";
					break;
				case 'A':
					result = "Authentication of your Merchant ID and Password to the ACS Directory Failed.";
					break;
				case 'D':
					result = "Error communicating with the Directory Server.";
					break;
				case 'C':
					result = "The card type is not supported for authentication.";
					break;
				case 'S':
					result = "The signature on the response received from the Issuer could not be validated.";
					break;
				case 'P':
					result = "Error parsing input from Issuer.";
					break;
				case 'I':
					result = "Internal Payment Server system error.";
					break;
				default:
					result = "Unable to be determined";
					break;
				}
			}
		} else {
			result = "null response";
		}
		return result;
	}

	public String getHashKeys() {
		return hashKeys;
	}

	public void setHashKeys(String hashKeys) {
		this.hashKeys = hashKeys;
	}

	public String getHashValues() {
		return hashValues;
	}

	public void setHashValues(String hashValues) {
		this.hashValues = hashValues;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMD5key() {
		return mD5key;
	}

	public void setMD5key(String md5key) {
		mD5key = md5key;
	}

	public Long getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(Long merchantno) {
		this.merchantno = merchantno;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
	}

	public String getStatus3DS() {
		return status3DS;
	}

	public void setStatus3DS(String status3DS) {
		this.status3DS = status3DS;
	}

	public String getTxnResponseCode() {
		return txnResponseCode;
	}

	public void setTxnResponseCode(String txnResponseCode) {
		this.txnResponseCode = txnResponseCode;
	}

	public String getVCSCRequestCode() {
		return vCSCRequestCode;
	}

	public void setVCSCRequestCode(String requestCode) {
		vCSCRequestCode = requestCode;
	}

	/*
	 * public String getVpc_ReturnURL() { return vpc_ReturnURL; }
	 * 
	 * public void setVpc_ReturnURL(String vpc_ReturnURL) { this.vpc_ReturnURL =
	 * vpc_ReturnURL; }
	 */

	public String getRorderno() {
		return rorderno;
	}

	public void setRorderno(String rorderno) {
		this.rorderno = rorderno;
	}

	public String getVpc_SecureHash() {
		return vpc_SecureHash;
	}

	public void setVpc_SecureHash(String vpc_SecureHash) {
		this.vpc_SecureHash = vpc_SecureHash;
	}

	public String getVpc_Merchant() {
		return vpc_Merchant;
	}

	public void setVpc_Merchant(String vpc_Merchant) {
		this.vpc_Merchant = vpc_Merchant;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getReturnURL() {
		return ReturnURL;
	}

	public void setReturnURL(String returnURL) {
		ReturnURL = returnURL;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
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

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getTradeMoneyType() {
		return tradeMoneyType;
	}

	public void setTradeMoneyType(String tradeMoneyType) {
		this.tradeMoneyType = tradeMoneyType;
	}

	public Long getSucceed() {
		return Succeed;
	}

	public void setSucceed(Long succeed) {
		Succeed = succeed;
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
}
