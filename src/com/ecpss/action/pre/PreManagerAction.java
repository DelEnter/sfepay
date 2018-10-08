package com.ecpss.action.pre;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import vpn.VpnUtil;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.AES;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.EvipPayResult;
import com.ecpss.web.PageInfo;

public class PreManagerAction extends BaseAction{
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	private Long[] id;
	private String typesname = "";
	private String remark;
	private PageInfo info;
	private Long tradeId;
	private InternationalMerchant tradeinfo = new InternationalMerchant();
	private String orderdno = "";	
	private String kuaidi;	
	public ShopManagerService getShopManagerService() {
		return shopManagerService;
	}
	public void setShopManagerService(ShopManagerService shopManagerService) {
		this.shopManagerService = shopManagerService;
	}
	public String toauditPre(){
		
		// 审核通过
		String temID = "";
		if (this.id != null) {
			if (this.id.length > 0) {
				for (int i = 0; i < id.length; i++) {
					temID = temID + id[i] + ',';
				}

				temID = temID.substring(0, temID.length() - 1);
				// 1 代表审核通过 2 代表审核不通过
				if (this.typesname.equals("1")) {
					this.commonService
							.deleteBySql("update international_tradeinfo a set a.tradestate=substr(a.tradestate,1,13)||'3'||substr(a.tradestate,15, length(a.tradestate)-14)  where a.id in("
									+ temID + ")");
				} else if (this.typesname.equals("2")) {
					this.commonService
							.deleteBySql("update international_tradeinfo a set  a.tradestate=substr(a.tradestate,1,13)||'5'||substr(a.tradestate,15, length(a.tradestate)-14)   where a.id in("
									+ temID + ")");

				}

			}

		}
		if (this.info == null) {
			this.info = new PageInfo();
		}
		this.tradeinfo.setMerno(this.tradeinfo.getMerno());
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select t,m from InternationalTradeinfo t ,InternationalMerchant m  where m.id=t.merchantId and substr(t.tradeState,14,1)='2' and substr(t.tradeState,1,1)='6' ");
		if ((this.tradeinfo.getMerno() != null)) {
			if (this.tradeinfo.getMerno() != 0) {

				sb.append(" and  m.merno='" + this.tradeinfo.getMerno() + "'");
				// this.tradeList = this.commonService.list(sb.toString());

			}
		}
		if (StringUtils.isNotBlank(this.orderdno)) {
			sb.append(" and t.orderNo='" + this.orderdno.trim() + "'");
		}
		if (StringUtils.isNotBlank(kuaidi)) {
			sb.append(" and t.isTrackNo like '" + kuaidi.trim() + "%' ");
		}
		this.info.setPageSize(10);
		this.info = this.commonService
				.listQueryResultByHql(sb.toString(), info);
		return "success";
		
	}
	public String toListPre(){
		System.out.print("============================================");
		if(this.typesname.equals("1")){
			InternationalTradeinfo trade=(InternationalTradeinfo)this.commonService.load(InternationalTradeinfo.class, this.tradeId);
			InternationalCardholdersInfo card=(InternationalCardholdersInfo)this.commonService.list("from InternationalCardholdersInfo i where i.tradeId='"+trade.getId()+"'").get(0);
			InternationalMerchant merchant=(InternationalMerchant)this.commonService.load(InternationalMerchant.class, trade.getMerchantId());
	String ter = "select tm from InternationalTerminalManager tm where tm.andterminalNo='"
			+ trade.getVIPTerminalNo() + "' ";
	List<InternationalTerminalManager> list = this.commonService
			.list(ter);
	System.out.println("终端号：+++++++++++++++"
			+ list.get(0).getTerminalNo());					
		       vpn.DCCMessage dcc8 = new vpn.DCCMessage();
		          dcc8.setHashCode(list.get(0).getHashcode());
			      dcc8.setTrans_Type("auth");
			      dcc8.setTrans_Model("M");
			      dcc8.setMerchant_Id(list.get(0).getMerchantNo());
			      dcc8.setAuthor_Str(list.get(0).getAuthcode());
			      dcc8.setTerminal_Id(list.get(0).getTerminalNo());
			      dcc8.setInvoice_No(trade.getOrderNo().substring(
							trade.getOrderNo().length() - 6,
							trade.getOrderNo().length()));
			      dcc8.setCurrency_Code_T("156");
			      dcc8.setAmount_Loc(this.buzero(trade
							.getRmbAmount()
							+ ""));
			      dcc8.setCard_No(AES.getCarNo(card.getCardNo()));
			      dcc8.setExp_Date(AES.getCarNo(card.getExpiryDate()).substring(2, 4)
							+ AES.getCarNo(card.getExpiryDate()).substring(0, 2));
			      dcc8.setCSC(AES.getCarNo(card.getCvv2()));
			      dcc8.setOrder_No(trade.getOrderNo());
			      dcc8.setCustom(trade.getOrderNo());
			      VpnUtil  vu8=new VpnUtil();		
				     try{ 
					      dcc8=vu8.getDCCvalue(dcc8,"3");
					} catch (Exception e){
					System.out.println("银行系统出错"+dcc8.getResp_Code());
					// 传递的参数
 
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradeState,2,11)||'11'||substr(t.tradeState,15,(length(t.tradeState)-15)) where t.id='"
									+ trade.getId() + "'");
					return SUCCESS;
					}
					if (dcc8.getResp_Code().equals("0000")) {


						String message = "Pre-authorized Success!";
					this.commonService
							.deleteBySql("update  international_tradeinfo t  set t.tradestate='6'||substr(t.tradeState,2,12)||'0'||substr(t.tradestate,15,(length(t.tradestate)-14)),t.remark='"
									+ message
									+ "',t.VIPTerminalNo='"
									+ list.get(0).getTerminalNo()
									+ "',t.VIPAuthorizationNo='"
									+ dcc8
											.getAuth_Code()
									+ "' ,t.VIPBatchNo='XXXXXX' ,T.ref_No='"+dcc8.getRef_No()+"' where t.id='"
									+ trade.getId() + "'");

					// /---------------给持卡人发送邮件-----------------------////
					EmailInfo emailinfo = new EmailInfo();
					String mailinfo = emailinfo.getPaymentResultEmail(card
							.getEmail(), trade.getTradeAmount(),
							getStates().getCurrencyTypeByNo(
									trade.getMoneyType().intValue()), trade
									.getTradeUrl(), trade.getTradeTime(),
							list.get(0).getBillingAddress(), trade
									.getMerchantOrderNo(), trade
									.getOrderNo(),merchant);
					try {
						// 发送邮件,如果发送失败插入数据库发送
						CCSendMail.setSendMail(card.getEmail(), mailinfo,
								"ecpss@ecpss.cc");
						System.out.println("邮件立马发出");
					} catch (Exception e) {
						// 往数据库插入等待发送邮件
						shopManagerService.addSendMessages(card.getEmail(),
								"ecpss@ecpss.cc", mailinfo, "0");
						System.out.println("邮件等待稍后发出");
						return SUCCESS;
					}
					return SUCCESS;
										
						
					}else if (dcc8.getResp_Code().equals("1101")
							|| dcc8.getResp_Code().equals("No Value Returned")
							|| dcc8.getResp_Code().equals("1102")
							|| dcc8.getResp_Code()==null
							|| dcc8.getResp_Code().equals("1103")
							|| dcc8.getResp_Code().equals("1104")
							|| dcc8.getResp_Code().equals("1105")
							|| dcc8.getResp_Code().equals("1106")
							|| dcc8.getResp_Code().equals("3301")
							|| dcc8.getResp_Code().equals("3302")
							|| dcc8.getResp_Code().equals("3304")
							|| dcc8.getResp_Code().equals("3305")
							|| dcc8.getResp_Code().equals("3306")
							|| dcc8.getResp_Code().equals("3307")
							|| dcc8.getResp_Code().equals("3308")
							|| dcc8.getResp_Code().equals("3309")
							|| dcc8.getResp_Code().equals("3310")
							|| dcc8.getResp_Code().equals("3311")
							|| dcc8.getResp_Code().equals("3312")
							|| dcc8.getResp_Code().equals("3313")
							|| dcc8.getResp_Code().equals("3314")
							|| dcc8.getResp_Code().equals("3315")
							|| dcc8.getResp_Code().equals("3316")
							|| dcc8.getResp_Code().equals("3317")
							|| dcc8.getResp_Code().equals("3318")
							|| dcc8.getResp_Code().equals("3319")
							|| dcc8.getResp_Code().equals("3320")
							|| dcc8.getResp_Code().equals("3321")
							|| dcc8.getResp_Code().equals("3322")
							|| dcc8.getResp_Code().equals("4401")
							|| dcc8.getResp_Code().equals("4404")
							|| dcc8.getResp_Code().equals("9903")
							|| dcc8.getResp_Code().equals("9937")
							|| dcc8.getResp_Code().equals("9977")
							|| dcc8.getResp_Code().equals("9989")
							|| dcc8.getResp_Code().equals("9994")
							|| dcc8.getResp_Code().equals("9995")
							|| dcc8.getResp_Code().equals("9996")
							|| dcc8.getResp_Code().equals("9998")
							|| dcc8.getResp_Code().equals("9999")) {
						System.out.println("银行系统出错"+dcc8.getResp_Code());
 
					String	message = "Your payment is being processed";

						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='2'||substr(t.tradeState,2,11)||'11'||substr(t.tradeState,15,(length(t.tradeState)-15)) where t.id='"
										+ trade.getId() + "'");
						return SUCCESS;
						
						
					}else {
						 
						String	message = "Payment Declined!"
								+ dcc8.getResp_Code();
						 
				
						this.commonService
								.deleteBySql("update  international_tradeinfo t  set t.tradestate='0'||substr(t.tradestate,2,(length(t.tradestate)-15)),t.remark='"
										+ message
										+ "',t.VIPTerminalNo='"
										+ list.get(0).getTerminalNo()
										+ "' where t.id='"
										+ trade.getId()
										+ "'");
						
						//清除持卡人cvv,有效期
//						this.commonService
//						.deleteBySql("update  international_cardholdersinfo t  set t.cvv2='XXX',t.expiryDate='0000' where t.tradeId='"
//								+ trade.getId() + "'");

					}					
			      
			      
			      
		}

		if (this.info == null) {
			this.info = new PageInfo();
		}
		this.tradeinfo.setMerno(this.tradeinfo.getMerno());
		StringBuffer sb = new StringBuffer();
		sb
				.append(" select t,m from InternationalTradeinfo t ,InternationalMerchant m  where m.id=t.merchantId and substr(t.tradeState,14,1)='1' and substr(t.tradeState,1,1)='2' ");
		if ((this.tradeinfo.getMerno() != null)) {
			if (this.tradeinfo.getMerno() != 0) {

				sb.append(" and  m.merno='" + this.tradeinfo.getMerno() + "'");
				// this.tradeList = this.commonService.list(sb.toString());

			}
		}
		if (StringUtils.isNotBlank(this.orderdno)) {
			sb.append(" and t.orderNo='" + this.orderdno.trim() + "'");
		}
		if (StringUtils.isNotBlank(kuaidi)) {
			sb.append(" and t.isTrackNo like '" + kuaidi.trim() + "%' ");
		}
		this.info.setPageSize(10);
		this.info = this.commonService
				.listQueryResultByHql(sb.toString(), info);
		return "success";
		
	}	
	public Long[] getId() {
		return id;
	}
	public void setId(Long[] id) {
		this.id = id;
	}
	public String getTypesname() {
		return typesname;
	}
	public void setTypesname(String typesname) {
		this.typesname = typesname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public InternationalMerchant getTradeinfo() {
		return tradeinfo;
	}
	public void setTradeinfo(InternationalMerchant tradeinfo) {
		this.tradeinfo = tradeinfo;
	}
	public String getOrderdno() {
		return orderdno;
	}
	public void setOrderdno(String orderdno) {
		this.orderdno = orderdno;
	}
	public String getKuaidi() {
		return kuaidi;
	}
	public void setKuaidi(String kuaidi) {
		this.kuaidi = kuaidi;
	}
	
	public String buzero(String refundRMBMoney) {
		String refundRMB = "000000000000";
		String zero_12 = "000000000000";
		DecimalFormat decimalFormat = new DecimalFormat("##############0.00");
		if (StringUtils.isNotBlank(refundRMBMoney)
				&& refundRMBMoney.replace(".", "").matches("\\d+")) {
			String refundRMBStr = Double.valueOf((decimalFormat.format(Double
					.valueOf(refundRMBMoney))))
					* 100 + "";
			String refundRMB_0 = zero_12
					+ refundRMBStr.substring(0, refundRMBStr.indexOf("."));
			refundRMB = refundRMB_0.substring(refundRMB_0.length() - 12,
					refundRMB_0.length());
		}
		return refundRMB;
	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}	
	
	
	

}
