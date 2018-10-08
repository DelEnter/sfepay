package com.ecpss.job;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

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
import com.ecpss.util.StateUtils;

public class ProcessALTradeJob extends QuartzJobBean {

	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	@Qualifier("commonService")
	private CommonService commonService;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		List list = shopManagerService.getTransactionByAL();
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
				posNumber = "77772503";
				posMerchantNo = "021801109000001";
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

				System.out.println("Al自动处理交易" + tradeId);
				
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
					System.out.println("出现异常,跳出");
					return;
				}
				System.out.println("自动处理DCC走++++++++++  "
						+ dcc.getRESPONSE_CODE());
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

						// return;
					}
					if(StringUtils.isBlank(msg2.getRESPONSE_CODE())){
						System.out.println("返回码空,跳出");
						return;
					}
					if (msg2.getRESPONSE_CODE().equals("00")) {
						// 交易成功
						String message = "Payment Success!";

						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
										+ message
										+ "' ,t.VIPAuthorizationNo='"
										+ msg2
												.getAUTH_IDENTIFICATION_RESPONSE()
										+ "' ,t.VIPDisposePorson='System523' "
										+ " ,t.VIPDisposeDate=sysdate "
										+ " ,t.VIPBatchNo='XXXXXX',t.VIPTerminalNo='"
										+ posNumber
										+ "' where t.id='"
										+ tradeId + "'");
						
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
						return;
					} else {
//						// 交易失败
//						String message = "Payment Declined!"
//								+ msg2.getRESPONSE_CODE();
//						// this.responseCode = 0;
//						this.commonService
//								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//										+ message
//										+ "' ,t.VIPDisposePorson='System523' "
//										+ " ,t.VIPDisposeDate=sysdate "
//										+ "  where t.id='" + tradeId + "'");
						//清除持卡人cvv,有效期
//						this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ tradeId + "'");
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
							String message = "Payment Success!";

							this.commonService
									.deleteBySql("update  international_tradeinfo t  set t.tradestate='1'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
											+ message
											+ "' ,t.VIPAuthorizationNo='"
											+ dcc3
													.getAUTH_IDENTIFICATION_RESPONSE()
											+ "' ,t.VIPDisposePorson='System523' "
											+ " ,t.VIPDisposeDate=sysdate "
											+ " ,t.VIPBatchNo='XXXXXX',t.VIPTerminalNo='"
											+ posNumber
											+ "' where t.id='"
											+ tradeId + "'");
//							//清除持卡人cvv,有效期
//							this.commonService
//							.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//									+ tradeId + "'");
//							// ****************发送持卡人邮件****************************//
//							String mailinfo="";
//							try {
//								String sql = "select m from InternationalMerchant m," +
//										"InternationalTradeinfo ti where ti.merchantId=m.id " +
//										"and ti.id="+tradeId;
//								InternationalMerchant merchant = (InternationalMerchant) this.commonService.uniqueResult(sql);
//								EmailInfo emailinfo = new EmailInfo();
//								StateUtils states = new StateUtils();
//								mailinfo = emailinfo.getPaymentResultEmail(
//									cardhorderEmail, tradeAmount.doubleValue(),
//									states.getCurrencyTypeByNo(Integer
//											.parseInt(tradeState)), tradeUrl,
//									tradeTime, posBillingAdd, merchantOrderNo,
//									tradeOrderNo,merchant);
//								// 发送邮件,如果发送失败插入数据库发送
//								CCSendMail.setSendMail(cardhorderEmail,
//										mailinfo,
//										"visamasterpay@visamasterpay.com");
//								System.out.println("邮件立马发出");
//							} catch (Exception e) {
//								// 往数据库插入等待发送邮件
//								shopManagerService.addSendMessages(
//										cardhorderEmail,
//										"visamasterpay@visamasterpay.com",
//										mailinfo, "0");
//								System.out.println("邮件等待稍后发出");
//								// return;
//							}
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
							return;
						} else {
//							// 交易失败
//							String message = "Payment Declined!"
//									+ dcc3.getRESPONSE_CODE();
//							// this.responseCode = 0;
//							this.commonService
//									.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-1)),t.remark='"
//											+ message
//											+ "' ,t.VIPDisposePorson='System' "
//											+ " ,t.VIPDisposeDate=sysdate "
//											+ "  where t.id='" + tradeId + "'");
//							//清除持卡人cvv,有效期
//							this.commonService
//							.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//									+ tradeId + "'");
							// return;
						}
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
