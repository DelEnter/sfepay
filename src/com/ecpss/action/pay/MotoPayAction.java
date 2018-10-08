package com.ecpss.action.pay;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import vpn.VpnUtil_Moto;

import com.ecpss.action.BaseAction;
import com.ecpss.action.PayNoticeThread;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.model.shop.InternationalTerminalManager;
import com.ecpss.service.iservice.ShopManagerService;
import com.ecpss.util.CCSendMail;
import com.ecpss.util.EmailInfo;
import com.ecpss.util.MD5;

public class MotoPayAction extends BaseAction {
	@Autowired
	@Qualifier("shopManagerService")
	private ShopManagerService shopManagerService;
	private String HASH;
	private String Trans_Type;
	private String Merchant_Id;
	private String Author_Str;
	private String Terminal_Id;
	private String Invoice_No;
	private String Currency_Code_T;
	private String Amount_Loc;
	private String Card_No;
	private String Exp_Date;
	private String Order_No;
	private String Custom;
	private String Trans_Model;
	private String Conversion_Rate;
	private String Currency_Code;
	private String Amount_For;
	private String bocs_ReturnURL;
	private String Ref_No;
	private String Amount_Ref;
	private String Tran_Date_Ori;
	private String Resp_Code;
	private String Auth_Code;
	private String Currency;
	private String Amount_Ori;
	private String token_id;
	private String Send_Url;
	//vpn风险交易推送结果
	public String payResponseMoto(){
		int responseCode;
		String message;
		String MD5info;
		String md5Value;
		InternationalTradeinfo trade = new InternationalTradeinfo();
		String hql = "from InternationalTradeinfo t where t.orderNo='"
				+ Order_No + "'";
		List<InternationalTradeinfo> tradl = this.commonService.list(hql);
		trade = tradl.get(0);
		// 转换成标准金额
		DecimalFormat decimalFormat = new DecimalFormat(
							"##############0.00");
		MD5 md5 = new MD5();
		String ordercountValue = decimalFormat.format(trade.getTradeAmount());	
		InternationalMerchant merchant = (InternationalMerchant) this.commonService
				.load(InternationalMerchant.class, trade.getMerchantId());
		InternationalCardholdersInfo card =(InternationalCardholdersInfo)this.commonService
				.load(InternationalCardholdersInfo.class,trade.getId());
		if (this.getResp_Code().equals("0000")) {
			vpn.MotoDCCMessage moto = new vpn.MotoDCCMessage();
			moto.setTrans_Type("dccsales");// 类型
			moto.setMerchant_Id(Merchant_Id);// 42 商户编号
			moto.setAuthor_Str(Author_Str);
			moto.setTerminal_Id(Terminal_Id);// 41 商户终端号
			moto.setInvoice_No(Invoice_No);

			moto.setTrans_Model("M");//moto通道
			moto.setCurrency_Code_T("156");// 货币代码
			moto.setAmount_Loc(Amount_Loc);// 4
			// 本地交易金额
			moto.setCard_No("");// 账号2
			moto.setExp_Date("");// 14 有效期
			moto.setCSC("");
			moto.setCurrency_Code("");
			moto.setBocs_ReturnURL("http://172.20.66.2/sfe");
			moto.setAmount_For("");
			moto.setOrder_No(Order_No);
			moto.setCustom(Custom);
			moto.setHashCode(HASH);
			VpnUtil_Moto vm=new VpnUtil_Moto();
			//VpnUtil vu2 = new VpnUtil();
			try {
				// type 2 dcc交易
				System.out.println("开始moto DCC交易");
				moto = vm.getDCCvalue(moto, "2");
			} catch (Exception e) {
				responseCode = 19;
				message = "Your payment is being processed";
				MD5info = trade.getMerchantOrderNo()
						+ trade.getMoneyType() + ordercountValue
						+ responseCode + merchant.getMd5key();
				md5Value = md5.getMD5ofStr(MD5info);

				trade.setTradeState("0"
						+ trade.getTradeState().substring(1,
								trade.getTradeState().length()));
				trade.setRemark(message);
				trade.setVIPDisposePorson("System");
				trade.setVIPDisposeDate(new Date());
				this.commonService.update(trade);
				return INPUT;
			}
		if (moto.getResp_Code().equals("0000")) {//交易成功
			message = "Payment Success!";
			responseCode = 88;
			trade.setTradeState("1"
					+ trade.getTradeState().substring(1,
							trade.getTradeState().length()));
			trade.setRemark(message);
			trade.setVIPDisposePorson("System");
			trade.setVIPDisposeDate(new Date());
			trade.setVIPBatchNo(moto.getAuth_Code());
			trade.setVIPTerminalNo(Terminal_Id);
			trade.setVIPAuthorizationNo(moto.getInvoice_No());
			this.commonService.update(trade);
			card.setExpiryDate("0000");
			card.setCvv2("XXX");
			this.commonService.update(card);
			MD5info = trade.getMerchantOrderNo()
					+ trade.getMoneyType() + ordercountValue
					+ responseCode + merchant.getMd5key();
			md5Value = md5.getMD5ofStr(MD5info);
			// 发送邮件
			List<InternationalTerminalManager> tmm = this.commonService
					.list("select tm from InternationalTerminalManager tm where tm.terminalNo='"
							+ Terminal_Id+ "' ");
			String billaddressby = null;
			if (tmm.size() > 0) {
				InternationalTerminalManager tm = tmm.get(0);
				billaddressby = tm.getBillingAddress();
			}
			String mailinfo = null;
			try {
				EmailInfo emailinfo = new EmailInfo();
				mailinfo = emailinfo.getPaymentResultEmail(
						card.getEmail(),
						trade.getTradeAmount(),
						getStates().getCurrencyTypeByNo(
								trade.getMoneyType().intValue()),
						trade.getTradeUrl(), trade.getTradeTime(),
						billaddressby, trade.getMerchantOrderNo(),
						trade.getOrderNo(), merchant);
				// 发送邮件,如果发送失败插入数据库发送
				if (merchant.getStatutes().substring(4, 5)
						.equals("0")) {
					CCSendMail.setSendMail(card.getEmail(),
							mailinfo, "sfepay@sfepay.com");
					System.out.println("邮件立马发出");
				}
			} catch (Exception e) {
				// 往数据库插入等待发送邮件
				shopManagerService.addSendMessages(card.getEmail(),
						"sfepay@sfepay.com", mailinfo, "0");
				System.out.println("邮件等待稍后发出");
				return INPUT;
			}
			return INPUT;
		} else {
			message = "Payment Declined!"+moto.getResp_Code();
			responseCode = Integer.valueOf(moto.getResp_Code());
			
			trade.setTradeState("0"
					+ trade.getTradeState().substring(1,
							trade.getTradeState().length()));
			trade.setRemark(message);
			trade.setVIPDisposePorson("System");
			trade.setVIPDisposeDate(new Date());
			this.commonService.update(trade);
			MD5info = trade.getMerchantOrderNo()
					+ trade.getMoneyType() + ordercountValue
					+ responseCode + merchant.getMd5key();
			md5Value = md5.getMD5ofStr(MD5info);
			
			return INPUT;
		}
		}
		
		return "";
	}
	public String getHASH() {
		return HASH;
	}
	public void setHASH(String hASH) {
		HASH = hASH;
	}
	public String getTrans_Type() {
		return Trans_Type;
	}
	public void setTrans_Type(String trans_Type) {
		Trans_Type = trans_Type;
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
	public String getCard_No() {
		return Card_No;
	}
	public void setCard_No(String card_No) {
		Card_No = card_No;
	}
	public String getExp_Date() {
		return Exp_Date;
	}
	public void setExp_Date(String exp_Date) {
		Exp_Date = exp_Date;
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
	public String getTrans_Model() {
		return Trans_Model;
	}
	public void setTrans_Model(String trans_Model) {
		Trans_Model = trans_Model;
	}
	public String getConversion_Rate() {
		return Conversion_Rate;
	}
	public void setConversion_Rate(String conversion_Rate) {
		Conversion_Rate = conversion_Rate;
	}
	public String getCurrency_Code() {
		return Currency_Code;
	}
	public void setCurrency_Code(String currency_Code) {
		Currency_Code = currency_Code;
	}
	public String getAmount_For() {
		return Amount_For;
	}
	public void setAmount_For(String amount_For) {
		Amount_For = amount_For;
	}
	public String getBocs_ReturnURL() {
		return bocs_ReturnURL;
	}
	public void setBocs_ReturnURL(String bocs_ReturnURL) {
		this.bocs_ReturnURL = bocs_ReturnURL;
	}
	public String getRef_No() {
		return Ref_No;
	}
	public void setRef_No(String ref_No) {
		Ref_No = ref_No;
	}
	public String getAmount_Ref() {
		return Amount_Ref;
	}
	public void setAmount_Ref(String amount_Ref) {
		Amount_Ref = amount_Ref;
	}
	public String getTran_Date_Ori() {
		return Tran_Date_Ori;
	}
	public void setTran_Date_Ori(String tran_Date_Ori) {
		Tran_Date_Ori = tran_Date_Ori;
	}
	public String getResp_Code() {
		return Resp_Code;
	}
	public void setResp_Code(String resp_Code) {
		Resp_Code = resp_Code;
	}
	public String getAuth_Code() {
		return Auth_Code;
	}
	public void setAuth_Code(String auth_Code) {
		Auth_Code = auth_Code;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getAmount_Ori() {
		return Amount_Ori;
	}
	public void setAmount_Ori(String amount_Ori) {
		Amount_Ori = amount_Ori;
	}
	public String getToken_id() {
		return token_id;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public String getSend_Url() {
		return Send_Url;
	}
	public void setSend_Url(String send_Url) {
		Send_Url = send_Url;
	}

}
