package com.ecpss.job;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import vpn.VpnUtil;

import com.ecpss.action.pay.dcc.DCCMessage;
import com.ecpss.action.pay.dcc.DccUtil;
import com.ecpss.dao.common.CommonDao;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.payment.VpnPAY;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.common.CommonService;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.StateUtils;

public class ProcessPRETradeJob extends QuartzJobBean {

	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	@Qualifier("commonService")
	private CommonService commonService;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
//		String hql=" select * from (select * from InternationalTradeinfo ti where  substr(ti.tradeState,1,1)=6 and substr(ti.tradeState,14,1)=3  and (sysdate-ti.tradetime) * 24 * 60 * 60>3600 order by ti.tradeTime ) c where rownum=1";
		
		
		
		String sql2 = "select * from "
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
//			+ " and tm.isauto=1 "
			+ " and substr(ti.tradeState,1,1)=6 "
			+ " and substr(ti.tradeState,14,2)='30' "
			+ "  order by ti.tradeTime ) c "
			+ "where rownum=1 ";	
		List list = commonService.getByList(sql2);
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
				cardNo = AES.getCarNo(o[4].toString().trim());
//				cardCVV2 = AES.getCarNo(o[5].toString().trim());
				cardExDate = "1211";
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
								+ "set t.tradeState=substr(t.tradeState,1,14)||'3'||substr(t.tradeState,15,(length(t.tradeState)-15)) "
								+ "where t.id='" + tradeId + "'");

				System.out.println("自动处理交易" + tradeId);
				String sqlquery = "select substr(ti.tradeState,15,1) from InternationalTradeinfo ti where ti.id="
						+ tradeId;
				try {
					String str = (String) this.commonService
							.uniqueResult(sqlquery);
					if (!str.equals("3")) {
						System.out.println("已经处理过,跳出");
						return;
					}
				} catch (Exception e) {
					return;
					// TODO: handle exception
				}
				//获取关联终端   要做DCC交易
				String ters = " from InternationalTerminalManager tm where tm.andterminalNo='"+ posNumber.trim() + "' ";
				List<InternationalTerminalManager> it = this.commonService
						.list(ters);    	
				
				if(it.size()>0){
					InternationalTradeinfo trade=(InternationalTradeinfo)this.commonService.load(InternationalTradeinfo.class, tradeId.longValue()) ;	
				//DCC汇率查询
				//汇率查询
				vpn.DCCMessage dcc = new vpn.DCCMessage();
				dcc.setTrans_Type("enqrate");// 类型
				dcc.setHashCode(it.get(0).getHashcode());
				dcc.setMerchant_Id(it.get(0).getMerchantNo());// 42 商户编号
				dcc.setAuthor_Str(it.get(0).getAuthcode());
				dcc.setTerminal_Id(it.get(0).getTerminalNo());// 41 商户终端号
				dcc.setInvoice_No(tradeOrderNo.substring(tradeOrderNo
						.length() - 8, tradeOrderNo.length()-2));// 11 交易流水号
				dcc.setCurrency_Code_T("156");// 货币代码
				dcc.setAmount_Loc(this
						.buzero(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()) + ""));// 4 本地交易金额
				dcc.setCard_No(cardNo);// 账号2
				dcc.setExp_Date(cardExDate.substring(2, 4)
						+ cardExDate.substring(0, 2));// 14 有效期
				dcc.setOrder_No(tradeOrderNo);// 62
				dcc.setCustom(tradeOrderNo);
		        VpnUtil  vu=new VpnUtil();
		        //type   1 汇率查询
		        Long tim=System.currentTimeMillis();
		        try {
					dcc=vu.getDCCvalue(dcc,"1");
				} catch (HttpException e) {
					   VpnPAY vp=new VpnPAY();
					   vp.setApplyTime(new Date());
					   vp.setAuditTime(new Date());
					   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
							.length() - 7, tradeOrderNo.length()-1));
					   vp.setOperaterTime(new Date());
					   vp.setRemark("系统出错");
					   vp.setOperatorMan("system");
//					   vp.setOperatorStatus(dcc10.getResp_Code());
					   vp.setOperatorType("9");
//					   vp.setRefNo(dcc10.getRef_No());
					   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
					   vp.setTradeId(trade.getId());
					   this.commonService.saveOrUpdate(vp);		
					   return;
					   } catch (IOException e) {
						   VpnPAY vp=new VpnPAY();
						   vp.setApplyTime(new Date());
						   vp.setAuditTime(new Date());
						   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
								.length() - 7, tradeOrderNo.length()-1));
						   vp.setOperaterTime(new Date());
						   vp.setRemark("系统出错");
						   vp.setOperatorMan("system");
//						   vp.setOperatorStatus(dcc10.getResp_Code());
						   vp.setOperatorType("9");
//						   vp.setRefNo(dcc10.getRef_No());
						   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
						   vp.setTradeId(trade.getId());
						   this.commonService.saveOrUpdate(vp);		
						   return;
				}
		        //dcc.setResp_Code("99YX");
		        System.out.println("===查汇===========返回码:"+dcc.getResp_Code()+"\n");
		        if(dcc.getResp_Code().equals("99YY")){
		        //DCC授权完成
		            vpn.DCCMessage dcc9 = new vpn.DCCMessage(); 
		            dcc9.setHashCode(it.get(0).getHashcode());
		            dcc9.setTrans_Type("dcccomp");
		            dcc9.setMerchant_Id(it.get(0).getMerchantNo());
		            dcc9.setAuthor_Str(it.get(0).getAuthcode());
		            dcc9.setTerminal_Id(it.get(0).getTerminalNo());
		            dcc9.setInvoice_No(tradeOrderNo.substring(tradeOrderNo
							.length() - 8, tradeOrderNo.length()-2));
		            dcc9.setCurrency_Code_T("156");
		            dcc9.setAmount_Loc(this
							.buzero(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount())  + ""));
		            dcc9.setCard_No(cardNo);
		            dcc9.setAuth_Code(trade.getVIPAuthorizationNo());
		            dcc9.setOrder_No(tradeOrderNo);
		            dcc9.setCustom(tradeOrderNo);
		            dcc9.setCurrency_Code(dcc.getCurrency_Code());
		            dcc9.setAmount_For(dcc.getAmount_For());
		            VpnUtil  vu9=new VpnUtil();
		            try {
						dcc9=vu9.getDCCvalue(dcc9,"9");
					} catch (HttpException e) {
						   VpnPAY vp=new VpnPAY();
						   vp.setApplyTime(new Date());
						   vp.setAuditTime(new Date());
						   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
								.length() - 7, tradeOrderNo.length()-1));
						   vp.setOperaterTime(new Date());
						   vp.setOperatorMan("system");
//						   vp.setOperatorStatus(dcc10.getResp_Code());
						   vp.setOperatorType("9");
						   vp.setRemark("系统出错");
//						   vp.setRefNo(dcc10.getRef_No());
						   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
						   vp.setTradeId(trade.getId());
						   this.commonService.saveOrUpdate(vp);	
						   return;
					} catch (IOException e) {
						   VpnPAY vp=new VpnPAY();
						   vp.setApplyTime(new Date());
						   vp.setAuditTime(new Date());
						   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
								.length() - 7, tradeOrderNo.length()-1));
						   vp.setOperaterTime(new Date());
						   vp.setRemark("系统出错");
						   vp.setOperatorMan("system");
//						   vp.setOperatorStatus(dcc10.getResp_Code());
						   vp.setOperatorType("9");
//						   vp.setRefNo(dcc10.getRef_No());
						   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
						   vp.setTradeId(trade.getId());
						   this.commonService.saveOrUpdate(vp);		
						   return;
					}  
					//保存到操作表
		          //  dcc9.setResp_Code("0000");
					   VpnPAY vp=new VpnPAY();
					   vp.setApplyTime(new Date());
					   vp.setAuditTime(new Date());
					   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
							.length() - 6, tradeOrderNo.length()));
					   vp.setOperaterTime(new Date());
					   vp.setOperatorMan("system");
					   vp.setOperatorStatus(dcc9.getResp_Code());
					   vp.setOperatorType("7");
					   vp.setRefNo(dcc9.getRef_No());
					   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
					   vp.setTradeId(trade.getId());
					   this.commonService.saveOrUpdate(vp);
		     
					if(dcc9.getResp_Code().equals("0000")){
						System.out.print(" update International_Tradeinfo t set t.tradeState='1'||substr(t.tradeState,2,)||substr(t.tradeState,2,(length(t.tradeState)-1)),t.beginmoney="+trade.getTradeAmount()+",t.pre_money_rmb="+trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount())+",t.tradeAmount="+trade.getPre_money()+" t.VIPTerminalNo='"+it.get(0).getTerminalNo()+"' where t.id="+trade.getId()+" ");
					//更新数据库
					this.commonService.deleteBySql(" update International_Tradeinfo t set t.tradeState='1'||substr(t.tradeState,2,12)||'4'||substr(t.tradeState,15,(length(t.tradeState)-14)),t.beginmoney="+trade.getTradeAmount()+",t.rmbAmount="+trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount())+",t.pre_money_rmb="+trade.getRmbAmount()+",t.tradeAmount="+trade.getPre_money()+",t.VIPTerminalNo='"+it.get(0).getTerminalNo()+"'  where t.id="+trade.getId()+" ");
					//发送成功邮件
					}
					
		            System.out.println(dcc9.getResp_Code());     		        	
		        	
		        }else if(dcc.getResp_Code().equals("99YX")){
					String ters2 = " from InternationalTerminalManager tm where tm.terminalNo='"+ posNumber.trim() + "' ";
					List<InternationalTerminalManager> it2 = this.commonService.list(ters2);    		        //非DCC授权完成
//		          预授权完成 EDC
		            vpn.DCCMessage dcc10 = new vpn.DCCMessage(); 
		            dcc10.setHashCode(it2.get(0).getHashcode());
		            dcc10.setTrans_Type("authcomp");
		            dcc10.setMerchant_Id(posMerchantNo);
		            dcc10.setAuthor_Str(it2.get(0).getAuthcode());
		            dcc10.setTerminal_Id(it2.get(0).getTerminalNo());
		            dcc10.setInvoice_No(tradeOrderNo.substring(tradeOrderNo
							.length() - 7, tradeOrderNo.length()-1));
		            dcc10.setCurrency_Code_T("156");
		            dcc10.setAmount_Loc(this
							.buzero(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount())  + ""));
		            dcc10.setCard_No(cardNo);
		            dcc10.setAuth_Code(trade.getVIPAuthorizationNo());
		            dcc10.setOrder_No(tradeOrderNo);
		            dcc10.setCustom(tradeOrderNo);
		            VpnUtil  vu10=new VpnUtil();
		            try {
						dcc10=vu10.getDCCvalue(dcc10,"10");
					} catch (HttpException e) {
						   VpnPAY vp=new VpnPAY();
						   vp.setApplyTime(new Date());
						   vp.setAuditTime(new Date());
						   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
								.length() - 7, tradeOrderNo.length()-1));
						   vp.setOperaterTime(new Date());
						   vp.setOperatorMan("system");
						   vp.setRemark("系统出错");
//						   vp.setOperatorStatus(dcc10.getResp_Code());
						   vp.setOperatorType("9");
//						   vp.setRefNo(dcc10.getRef_No());
						   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
						   vp.setTradeId(trade.getId());
						   this.commonService.saveOrUpdate(vp);		
                            return ;
					} catch (IOException e) {
						   VpnPAY vp=new VpnPAY();
						   vp.setApplyTime(new Date());
						   vp.setAuditTime(new Date());
						   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
								.length() - 7, tradeOrderNo.length()-1));
						   vp.setOperaterTime(new Date());
						   vp.setOperatorMan("system");
						   vp.setRemark("系统出错");
//						   vp.setOperatorStatus(dcc10.getResp_Code());
						   vp.setOperatorType("9");
//						   vp.setRefNo(dcc10.getRef_No());
						   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
						   vp.setTradeId(trade.getId());
						   this.commonService.saveOrUpdate(vp);		
						   return;
					}    	   
					   VpnPAY vp=new VpnPAY();
					   vp.setApplyTime(new Date());
					   vp.setAuditTime(new Date());
					   vp.setInvoice(tradeOrderNo.substring(tradeOrderNo
							.length() - 7, tradeOrderNo.length()-1));
					   vp.setOperaterTime(new Date());
					   vp.setOperatorMan("system");
					   vp.setOperatorStatus(dcc10.getResp_Code());
					   vp.setOperatorType("8");
					   vp.setRefNo(dcc10.getRef_No());
					   vp.setRefundAmount(trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount()));
					   vp.setTradeId(trade.getId());
					   this.commonService.saveOrUpdate(vp);		            
		         //   dcc10.setResp_Code("0000");
					if(dcc10.getResp_Code().equals("0000")){
						System.out.print(" update International_Tradeinfo t set t.tradeState='1'||substr(t.tradeState,2,(length(t.tradeState)-1)),t.beginmoney="+trade.getTradeAmount()+",t.pre_money_rmb="+trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount())+",t.tradeAmount="+trade.getPre_money()+" t.VIPTerminalNo='"+it.get(0).getTerminalNo()+"' where t.id="+trade.getId()+" ");
					//更新数据库
						this.commonService.deleteBySql(" update International_Tradeinfo t set t.tradeState='1'||substr(t.tradeState,2,12)||'4'||substr(t.tradeState,15,(length(t.tradeState)-14)),t.beginmoney="+trade.getTradeAmount()+",t.rmbAmount="+trade.getRmbAmount()*(trade.getPre_money()/trade.getTradeAmount())+",t.pre_money_rmb="+trade.getRmbAmount()+",t.tradeAmount="+trade.getPre_money()+" where t.id="+trade.getId()+" ");
					//发送成功邮件
					
					//保存到操作表

						
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
