package com.ecpss.service.implservice;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;
import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.payment.InternationalTemporaryTradInfo;
import com.ecpss.model.payment.InternationalTradeRun;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMailInfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.model.user.User;
import com.ecpss.service.BaseService;
import com.ecpss.service.common.CommonService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.StateUtils;
import com.ecpss.util.StatusUtil;
import com.ecpss.web.VcpUtil;

@Service("shopManagerService")
@Transactional
public class ShopManagerServiceImpl extends BaseService implements
		ShopManagerService {
	@Autowired
	@Qualifier("commonDao")
	private CommonDao commonDao;
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;

	public void regedit(Object obj) {
		this.commonDao.save(obj);
	}

	public List getMerCreditCardManagerList(Long merid) {
		String hql = "select m.merno," + "c.channelName," + "cct.cardName,"
				+ "mcc " + "from InternationalMerchant m,"
				+ "InternationalMerCreditCard mcc,"
				+ "InternationalChannels c,"
				+ "InternationalCreditCardType cct,"
				+ "InternationalMerchantChannels mc where "
				+ "mc.merchantId=m.id and " + "mc.channelId=c.id and "
				+ "mcc.merChannelId=mc.id and "
				+ "mcc.creditCardId=cct.id and " + "m.id=" + merid;
		List list = this.commonDao.list(hql);
		return list;
	}

	public void cancelHignRickTrade() {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select ti from InternationalTradeinfo ti where substr(ti.tradeState,1,1)=4 "
						+ "and sysdate-ti.tradeTime>1");
		List<InternationalTradeinfo> list = this.commonDao.list(sb.toString());
		if (list.size() > 0) {
			for (InternationalTradeinfo ti : list) {
				ti.setTradeState(StatusUtil.updateStatus(ti.getTradeState(), 1,
						"3"));
				this.commonDao.update(ti);
			}
		}
	}


	public void addSendMessages(String cardholdsemail, String sendemail,
			String mailinfo, String type) {
		InternationalMailInfo info = new InternationalMailInfo();
		info.setCardhorderEmail(cardholdsemail);
		info.setSendEmail(sendemail);
		info.setMailInfo(mailinfo);
		info.setType(type);
		this.commonDao.save(info);
	}

	public InternationalMailInfo getResultMail(String type) {
		String hql = "select m from InternationalMailInfo m where m.type='"
				+ type + "' order by m.id";
		List list = this.commonDao.list(hql);
		InternationalMailInfo m = null;
		if (list.size() > 0) {
			m = (InternationalMailInfo) list.get(0);
		}
		return m;
	}

	public void deleteInfo(InternationalMailInfo info) {
		this.commonDao.delete(info);

	}

	public void runGoudui() {
		// 流水号，金额，授权号，批次号，商户号，接入码，对账URL,状态
		String sql = "select * from (select ti.orderNo,ti.rmbAmount,ti.VIPAuthorizationNo,ti.VIPBatchNo,ti.VIPTerminalNo,mmn.accessCode,mmn.checkUrl,ti.tradeState "
				+ "from International_Tradeinfo ti,international_migsmerchantno mmn "
				+ "where mmn.bankMerchantId=ti.VIPTerminalNo "
				+ "and substr(ti.tradeState,1,1) in (0,1,5) "
				+ "and substr(ti.tradeState,5,1)=0  "
				+ "and substr(ti.tradeState,11,1)=0 "
				+ "and (sysdate-ti.tradetime) * 24 * 60 * 60>600 order by ti.tradetime ) c "
				+ "where rownum=1 ";
		List list = this.commonDao.getBySql(sql);
		if (list.size() > 0) {
			Object[] tem = (Object[]) list.get(0);
			String parte = "vpc_Version=" + "1" + "&vpc_Command=" + "queryDR"
					+ "&vpc_AccessCode=" + tem[5].toString() + "&vpc_Merchant="
					+ tem[4].toString() + "&vpc_MerchTxnRef="
					+ tem[0].toString() + "&vpc_User=" + "goudui"
					+ "&vpc_Password=" + "ecpss12345";
			System.out.println("=====" + parte);
			Double rmbamount = new Double(tem[1].toString());
			String rmbformate = new DecimalFormat("#00")
					.format(rmbamount * 100);

			try {

				String str = VcpUtil.doPost(
						"https://migs.mastercard.com.au/vpcdps", parte, false,
						"", 443);
				Map responseFields = this.createMapFromResponse(str);
				// 金额:
				String amount = VcpUtil.null2unknown("vpc_Amount",
						responseFields);
				// 授权号
				String authorizeID = VcpUtil.null2unknown("vpc_AuthorizeId",
						responseFields);
				// 批次号
				String transactionNo = VcpUtil.null2unknown(
						"vpc_TransactionNo", responseFields);
				// 交易时返回的代码
				String txnResponseCode = VcpUtil.null2unknown(
						"vpc_TxnResponseCode", responseFields);
				String message = getResponseDescription(txnResponseCode);
				// 未返回状态 ,失败
				if (tem[7].toString().substring(0, 1).equals("5")
						|| tem[7].toString().substring(0, 1).equals("0")) {
					if (txnResponseCode.equals("0")) {
						if (amount.equals(rmbformate)) {
							this.commonDao
									.deleteBySql("update International_Tradeinfo t set t.tradeState='1'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,(length(t.tradestate)-5)),t.VIPAuthorizationNo='"
											+ authorizeID
											+ "',t.VIPBatchNo='"
											+ transactionNo
											+ "',t.gouduiTime=sysdate,t.remark='"+message+"' where t.orderNo='"
											+ tem[0].toString() + "'");
						} else {
							if (amount.substring(0, amount.length() - 1)
									.equals(
											rmbformate.substring(0, rmbformate
													.length() - 1))) {
								this.commonDao
										.deleteBySql("update International_Tradeinfo t set t.tradeState='1'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,5)||'1'||substr(t.tradestate,12,(length(t.tradestate)-11)),t.VIPAuthorizationNo='"
												+ authorizeID
												+ "',t.VIPBatchNo='"
												+ transactionNo
												+ "' ,t.gouduiTime=sysdate,t.remark='"+message+"' where t.orderNo='"
												+ tem[0].toString() + "'");
							} else {
								this.commonDao
										.deleteBySql("update International_Tradeinfo t set t.tradeState='0'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,5)||'1'||substr(t.tradestate,12,(length(t.tradestate)-11)),t.VIPAuthorizationNo='"
												+ authorizeID
												+ "',t.VIPBatchNo='"
												+ transactionNo
												+ "' ,t.gouduiTime=sysdate,t.remark='"+message+"' where t.orderNo='"
												+ tem[0].toString() + "'");
							}

						}
					} else {
						this.commonDao
								.deleteBySql("update International_Tradeinfo t set t.tradeState='0'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,(length(t.tradestate)-5)) ,t.gouduiTime=sysdate,t.remark='"+message+"' where t.orderNo='"
										+ tem[0].toString() + "'");
					}
				}
				// 成功交易的单子
				if (tem[7].toString().substring(0, 1).equals("1")) {
					if (txnResponseCode.equals("0")) {
						if (transactionNo.equals(tem[3].toString())
								& authorizeID.equals(tem[2].toString())
								& amount.equals(rmbformate)) {
							this.commonDao
									.deleteBySql("update International_Tradeinfo t set t.tradeState='1'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,(length(t.tradestate)-5)),t.VIPAuthorizationNo='"
											+ authorizeID
											+ "',t.VIPBatchNo='"
											+ transactionNo
											+ "' ,t.gouduiTime=sysdate,t.remark='"+message+"' where t.orderNo='"
											+ tem[0].toString() + "'");
						} else {
							if (amount.substring(0, amount.length() - 1)
									.equals(
											rmbformate.substring(0, rmbformate
													.length() - 1))) {
								this.commonDao
										.deleteBySql("update International_Tradeinfo t set t.tradeState='1'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,5)||'0'||substr(t.tradestate,12,(length(t.tradestate)-11)),t.VIPAuthorizationNo='"
												+ authorizeID
												+ "',t.VIPBatchNo='"
												+ transactionNo
												+ "' ,t.gouduiTime=sysdate,t.remark='"+message+"' where t.orderNo='"
												+ tem[0].toString() + "'");
							} else {
								this.commonDao
										.deleteBySql("update International_Tradeinfo t set t.tradeState='1'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,5)||'1'||substr(t.tradestate,12,(length(t.tradestate)-11)),t.VIPAuthorizationNo='"
												+ authorizeID
												+ "',t.VIPBatchNo='"
												+ transactionNo
												+ "' ,t.gouduiTime=sysdate,t.remark='"+message+"' where t.orderNo='"
												+ tem[0].toString() + "'");
							}
						}
					} else {
						this.commonDao
								.deleteBySql("update International_Tradeinfo t set t.tradeState='1'||substr(t.tradestate,2,3)||'1'||substr(t.tradestate,6,(length(t.tradestate)-5)) ,t.gouduiTime=sysdate,t.remark='"+message+"'  where t.orderNo='"
										+ tem[0].toString() + "'");
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
    String getResponseDescription(String vResponseCode) {

        String result = "";
        // check if a single digit response code
            // Java cannot switch on a string so turn everything to a char
            char input = vResponseCode.charAt(0);
            switch (input){
                case '0' : result = "Transaction Successful"; break;
                case '1' : result = "Unknown Error"; break;
                case '2' : result = "Bank Declined Transaction"; break;
                case '3' : result = "No Reply from Bank"; break;
                case '4' : result = "Expired Card"; break;
                case '5' : result = "Insufficient Funds"; break;
                case '6' : result = "Error Communicating with Bank"; break;
                case '7' : result = "Payment Server System Error"; break;
                case '8' : result = "Transaction Type Not Supported"; break;
                case '9' : result = "Bank declined transaction (Do not contact Bank)"; break;
                case 'A' : result = "Transaction Aborted"; break;
                case 'C' : result = "Transaction Cancelled"; break;
                case 'D' : result = "Deferred transaction has been received and is awaiting processing"; break;
                case 'F' : result = "3D Secure Authentication failed"; break;
                case 'I' : result = "Card Security Code verification failed"; break;
                case 'L' : result = "Shopping Transaction Locked (Please try the transaction again later)"; break;
                case 'N' : result = "Cardholder is not enrolled in Authentication Scheme"; break;
                case 'P' : result = "Transaction has been received by the Payment Adaptor and is being processed"; break;
                case 'R' : result = "Transaction was not processed - Reached limit of retry attempts allowed"; break;
                case 'S' : result = "Duplicate SessionID (OrderInfo)"; break;
                case 'T' : result = "Address Verification Failed"; break;
                case 'U' : result = "Card Security Code Failed"; break;
                case 'V' : result = "Address Verification and Card Security Code Failed"; break;
                case '?' : result = "Transaction status is unknown"; break;
                default  : result = "Unable to be determined";
            }
            return result;
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
	
	public List getTransaction(){
		String sql = "select * from "
			+ "(select ti.id,"
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
			+ "from International_Tradeinfo ti,"
			+ "international_cardholdersinfo ci,"
			+ "International_TerminalManager tm "
			+ " where ti.id=ci.tradeId "
			+ " and tm.terminalNo=ti.VIPTerminalNo "
			+ " and tm.isauto=1 "
			+ " and substr(ti.tradeState,1,1)=2 "
			+ " and substr(ti.tradeState,13,1)=0 "
			+ " and (sysdate-ti.tradetime) * 24 * 60 * 60>3600 order by ti.tradeTime ) c "
			+ "where rownum=1 ";
		List list = this.commonDao.getBySql(sql);
//		BigDecimal tradeId; // 交易id
//		if (list.size() > 0) {
//			Object[] o = (Object[]) list.get(0);
//			tradeId = (BigDecimal) o[0];
//			this.commonService
//			.deleteBySql("update  international_tradeinfo t  " +
//					"set t.tradeState=substr(t.tradeState,1,12)||'1'||substr(t.tradeState,14,(length(t.tradeState)-1)) " +
//					"where t.id='" + tradeId + "'");
//		}
		return list;
	}
	
	
	public List getTransactionByAL(){
		String sql = "select * from "
			+ "(select ti.id,"
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
			+ "from International_Tradeinfo ti,"
			+ "international_cardholdersinfo ci,"
			+ "International_TerminalManager tm "
			+ " where ti.id=ci.tradeId "
			+ " and tm.terminalNo=ti.VIPTerminalNo "
			+ " "
			+ " and substr(ti.tradeState,1,1)=1 "
			+ " and substr(ti.tradeState,13,1)=0 "
			+ " and ti.VIPTerminalNo='22222222' " +
					" and ti.orderNo like '2126%' "
			+ " order by ti.tradeTime ) c "
			+ "where rownum=1 ";
		List list = this.commonDao.getBySql(sql);
//		BigDecimal tradeId; // 交易id
//		if (list.size() > 0) {
//			Object[] o = (Object[]) list.get(0);
//			tradeId = (BigDecimal) o[0];
//			this.commonService
//			.deleteBySql("update  international_tradeinfo t  " +
//					"set t.tradeState=substr(t.tradeState,1,12)||'1'||substr(t.tradeState,14,(length(t.tradeState)-1)) " +
//					"where t.id='" + tradeId + "'");
//		}
		return list;
	}
	
	
	
	public void processTransactions(List list) {
		System.out.println("自动处理交易");
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
			cardNo = o[4].toString().trim();
			cardCVV2 = o[5].toString().trim();
			cardExDate = o[6].toString().trim();
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
			String sqlquery="select substr(ti.tradeState,1,1) from International_Tradeinfo ti where ti.id="+tradeId;
			List list11 = this.commonDao.getBySql(sqlquery);
			try {
				String str = (String) list11.get(0);
				if(!str.equals("2")){
					return;
				}
			} catch (Exception e) {
				return ;
				// TODO: handle exception
			}
			// ---------------------处理交易---------------------------------------------------------------
			// 交易查询
			DCCMessage dcc = new DCCMessage();
			dcc.setMessageType("0800");// 类型
			dcc.setPrimary_Account_Number(cardNo);// 账号2
			dcc.setProcessing_Code("970000");// 处理代码3
			dcc.setAmount_Transaction_Local(this.buzero(tradeRMBAmount + ""));// 4
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
			dcc.setInvoice_Number(tradeOrderNo.substring(
					tradeOrderNo.length() - 6, tradeOrderNo.length()));// 62
			DccUtil dc = new DccUtil();
			dc.setDccMessage(dcc);

			try {
				if(StringUtils.isBlank(dcc.getRESPONSE_CODE())){
					dcc = dc.getDccMessage();
					if (cardNo.startsWith("5")) {
						dcc.setRESPONSE_CODE("YX");
					}
				}else{
					
				}
			} catch (Exception ex) {
				// 连接不了DCC就返回为待处理
				return;
			}
			System.out.println("DCC走++++++++++  " + dcc.getRESPONSE_CODE());
			// 做DCC交易
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
				msg2.setCurrency_Code_Foreign(dcc.getCurrency_Code_Foreign());// 49
				// 货币代码-----0810
				msg2.setCVV2_OR_CVC2(cardCVV2);// cv2
				msg2.setInvoice_Number(tradeOrderNo.substring(tradeOrderNo
						.length() - 6, tradeOrderNo.length()));// 62
				DccUtil dc2 = new DccUtil();
				dc2.setDccMessage(msg2);
				try {
					msg2 = dc2.getDccMessage();
				} catch (Exception ex) {
					// 交易冲正
					DCCMessage msg6 = new DCCMessage();
					msg6.setMessageType("0400");// 类型
					msg6.setPrimary_Account_Number(cardNo);// 账号2
					msg6.setProcessing_Code("000000");// 处理代码3
					msg6.setAmount_Transaction_Local(this.buzero(tradeRMBAmount
							+ ""));// 4 本地交易金额
					msg6.setAmount_Transaction_Foreign(dcc
							.getAmount_Transaction_Foreign());// 5 0810
					msg6.setConversion_Rate(dcc.getConversion_Rate());// 9
					// 0810
					msg6.setSYSTEMS_TRACE_AUDIT_NUMBER(tradeOrderNo.substring(
							tradeOrderNo.length() - 6, tradeOrderNo.length()));// 11
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
					msg6.setInvoice_Number(tradeOrderNo.substring(tradeOrderNo
							.length() - 6, tradeOrderNo.length()));// 62
					DccUtil dc6 = new DccUtil();
					dc6.setDccMessage(msg6);

					msg6 = dc6.getDccMessage();
					System.out
							.println("===============================yy交易冲正(撤销冲正):"
									+ msg6.getRESPONSE_CODE());

					//return;
				}

				if (msg2.getRESPONSE_CODE().equals("00")) {
					// 交易成功
					String message = "Payment Success!";

					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ message
									+ "' ,t.VIPAuthorizationNo='"
									+ msg2.getAUTH_IDENTIFICATION_RESPONSE()
									+ "' ,t.VIPDisposePorson='System' "
									+ " ,t.VIPDisposeDate=sysdate "
									+ " ,t.VIPBatchNo='XXXXXX',t.VIPTerminalNo='"
									+ posNumber
									+ "' where t.id='"
									+ tradeId
									+ "'");
					// ****************发送持卡人邮件****************************//
					EmailInfo emailinfo = new EmailInfo();
					StateUtils states = new StateUtils();
					String mailinfo = emailinfo.getPaymentResultEmail(
							cardhorderEmail, tradeAmount.doubleValue(), states
									.getCurrencyTypeByNo(Integer
											.parseInt(tradeState)), tradeUrl,
							tradeTime, posBillingAdd, merchantOrderNo,
							tradeOrderNo);
					try {
						// 发送邮件,如果发送失败插入数据库发送
						CCSendMail.setSendMail(cardhorderEmail, mailinfo,
								"ecpss@ecpss.cc");
						System.out.println("邮件立马发出");
					} catch (Exception e) {
						// 往数据库插入等待发送邮件
						this.addSendMessages(cardhorderEmail, "ecpss@ecpss.cc",
								mailinfo, "0");
						System.out.println("邮件等待稍后发出");
						//return;
					}
					//return;
				} else {
					// 交易失败
					String message = "Payment Declined!"
							+ msg2.getRESPONSE_CODE();
					// this.responseCode = 0;
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
									+ message
									+ "' ,t.VIPDisposePorson='System' "
									+ " ,t.VIPDisposeDate=sysdate "
									+ "  where t.id='" + tradeId + "'");
					//return;
				}

			} else if (dcc.getRESPONSE_CODE().equals("YX")) {
				String ter = "select tm from InternationalTerminalManager tm where tm.channelId=2 and tm.terminalNo='"
						+ posNumber.trim() + "' ";
				List list1 = this.commonDao.list(ter);
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
					dcc3.setAmount_Transaction_Local(this.buzero(tradeRMBAmount
							+ ""));// 4
					// 本地交易金额
					dcc3.setSYSTEMS_TRACE_AUDIT_NUMBER(tradeOrderNo.substring(
							tradeOrderNo.length() - 6, tradeOrderNo.length()));// 11
					// 交易流水号
					dcc3.setDATE_EXPIRATION(cardExDate.substring(2, 4)
							+ cardExDate.substring(0, 2));// 14 有效期
					dcc3.setPOINT_OF_SERVICE_ENTRY_CODE("0012");// 22 POS进入模式
					dcc3.setNETWORK_INTL_IDENTIFIER("0017");// 24 收单商户号
					dcc3.setCARD_ACCEPTOR_TERMINAL_ID(listt.get(0)
							.getTerminalNo());// 41
					// 商户终端号
					dcc3.setCARD_ACCEPTOR_ID_CODE(listt.get(0).getMerchantNo());// 42
					// 商户编号
					dcc3.setCVV2_OR_CVC2(cardCVV2);// cv2 61
					dcc3.setInvoice_Number(tradeOrderNo.substring(tradeOrderNo
							.length() - 6, tradeOrderNo.length()));// 62
					DccUtil dc3 = new DccUtil();
					dc3.setDccMessage(dcc3);
					try {
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
						//return;
					}
					if (dcc3.getRESPONSE_CODE().equals("00")) {
						// 交易成功
						String message = "Payment Success!";

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
						// ****************发送持卡人邮件****************************//
						EmailInfo emailinfo = new EmailInfo();
						StateUtils states = new StateUtils();
						String mailinfo = emailinfo.getPaymentResultEmail(
								cardhorderEmail, tradeAmount.doubleValue(),
								states.getCurrencyTypeByNo(Integer
										.parseInt(tradeState)), tradeUrl,
								tradeTime, listt.get(0).getBillingAddress(),
								merchantOrderNo, tradeOrderNo);
						try {
							// 发送邮件,如果发送失败插入数据库发送
							CCSendMail.setSendMail(cardhorderEmail, mailinfo,
									"ecpss@ecpss.cc");
							System.out.println("邮件立马发出");
						} catch (Exception e) {
							// 往数据库插入等待发送邮件
							this.addSendMessages(cardhorderEmail,
									"ecpss@ecpss.cc", mailinfo, "0");
							System.out.println("邮件等待稍后发出");
							//return;
						}
						//return;
					} else {

						// 交易失败
						String message = "Payment Declined!"
								+ dcc3.getRESPONSE_CODE();
						// this.responseCode = 0;
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "' ,t.VIPDisposePorson='System' "
										+ " ,t.VIPDisposeDate=sysdate "
										+ "  where t.id='" + tradeId + "'");
						//return;

					}
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
	public void addTemporaryTradInfo(String orderNo,String expirationYear,String expirationMonth,String cvv2,String country,String md5Info,String payIp,String userAgent,String remark){
		InternationalTemporaryTradInfo tm=new InternationalTemporaryTradInfo();
		tm.setOrderNo(orderNo);
		tm.setExpirationYear(expirationYear);
		tm.setExpirationMonth(expirationMonth);
		tm.setMd5key(md5Info);
		tm.setCvv2(cvv2);
		tm.setPayIp(payIp);
		tm.setCountry(country);
		tm.setRemark(remark);
		tm.setUserAgent(userAgent);
		tm.setCreateTime(new Date());
		if("04".equals(remark.substring(remark.length()-2, remark.length()))){
			tm.setStatus("3");
		}else{
		tm.setStatus("0");
		}
		commonService.save(tm);
		
	}

	public void addTraderun(String orderNo, String expirationYear,
			String expirationMonth, String cvv2, String country,
			String md5Info, String payIp, String userAgent, String remark) {
		InternationalTradeRun tr = new InternationalTradeRun();
		
		tr.setOrderNo(orderNo);
		tr.setExpirationYear(expirationYear);
		tr.setExpirationMonth(expirationMonth);		
		tr.setMd5key(md5Info);
		tr.setCvv2(cvv2);
		tr.setPayIp(payIp);
		tr.setCountry(country);
		tr.setRemark(remark);		
		tr.setUserAgent(userAgent);
		tr.setCreateTime(new Date());
		if("04".equals(remark.substring(remark.length()-2, remark.length()))){
			tr.setStatus("3");
		}else{
			tr.setStatus("0");
		}			
		commonService.save(tr);	
	
	}

}
