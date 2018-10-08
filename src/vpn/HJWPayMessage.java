package vpn;

import java.security.MessageDigest;

import com.ecpss.util.MD5;

public class HJWPayMessage {
		
		private String merchantId;
		private String md5key;
		private String amount;
		private String currency ;
		private String billNo ;
		private String cardAsn ;
		private String validity ;
		private String cvv ;
		private String cardType ;
	    private String srcUrl;
	    private String md5Data;
	    private String firstName;
	    private String lastName ;
	    private String address;
	    private String remark;
	    private String email;
	    private String telephone ;
	    private String jcTradeId;
	    private String tradType;
	 // 返回属性
		private String res_merchantId ="";
		private String res_billNo ="";
		private String res_cardAsn ="";
		private String res_amount ="";
		private String res_currency ="";
		private String res_rmb ="";
		private String res_jcTradeId ="";
		private String res_jcTime ="";
		private String res_responseCode ="";
		private String res_addMsg ="";
		private String res_remark ="";
		private String res_md5Data ="";
		public String getMerchantId() {
			return merchantId;
		}
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
		public String getCardAsn() {
			return cardAsn;
		}
		public void setCardAsn(String cardAsn) {
			this.cardAsn = cardAsn;
		}
		public String getValidity() {
			return validity;
		}
		public void setValidity(String validity) {
			this.validity = validity;
		}
		public String getCvv() {
			return cvv;
		}
		public void setCvv(String cvv) {
			this.cvv = cvv;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public String getSrcUrl() {
			return srcUrl;
		}
		public void setSrcUrl(String srcUrl) {
			this.srcUrl = srcUrl;
		}
		public String getMd5Data() {
			MD5 md5=new MD5();
			String strData="";
			if("1".equals(tradType)){
				strData=merchantId +billNo+cardAsn+validity+cvv+ amount + currency  + remark + md5key;
			}
			if("2".equals(tradType)){
				strData=merchantId+billNo+jcTradeId+amount+md5key;
			}
			return md5.getMD5ofStr(strData);
		}
		public void setMd5Data(String md5Data) {
			this.md5Data =md5Data ;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public String getRes_merchantId() {
			return res_merchantId;
		}
		public void setRes_merchantId(String res_merchantId) {
			this.res_merchantId = res_merchantId;
		}
		public String getRes_billNo() {
			return res_billNo;
		}
		public void setRes_billNo(String res_billNo) {
			this.res_billNo = res_billNo;
		}
		public String getRes_cardAsn() {
			return res_cardAsn;
		}
		public void setRes_cardAsn(String res_cardAsn) {
			this.res_cardAsn = res_cardAsn;
		}
		public String getRes_amount() {
			return res_amount;
		}
		public void setRes_amount(String res_amount) {
			this.res_amount = res_amount;
		}
		public String getRes_currency() {
			return res_currency;
		}
		public void setRes_currency(String res_currency) {
			this.res_currency = res_currency;
		}
		public String getRes_rmb() {
			return res_rmb;
		}
		public void setRes_rmb(String res_rmb) {
			this.res_rmb = res_rmb;
		}
		public String getRes_jcTradeId() {
			return res_jcTradeId;
		}
		public void setRes_jcTradeId(String res_jcTradeId) {
			this.res_jcTradeId = res_jcTradeId;
		}
		public String getRes_jcTime() {
			return res_jcTime;
		}
		public void setRes_jcTime(String res_jcTime) {
			this.res_jcTime = res_jcTime;
		}
		public String getRes_responseCode() {
			return res_responseCode;
		}
		public void setRes_responseCode(String res_responseCode) {
			this.res_responseCode = res_responseCode;
		}
		public String getRes_addMsg() {
			return res_addMsg;
		}
		public void setRes_addMsg(String res_addMsg) {
			this.res_addMsg = res_addMsg;
		}
		public String getRes_remark() {
			return res_remark;
		}
		public void setRes_remark(String res_remark) {
			this.res_remark = res_remark;
		}
		public String getRes_md5Data() {
			return res_md5Data;
		}
		public void setRes_md5Data(String res_md5Data) {
			this.res_md5Data = res_md5Data;
		}
		public String getMd5key() {
			return md5key;
		}
		public void setMd5key(String md5key) {
			this.md5key = md5key;
		}
		public String getJcTradeId() {
			return jcTradeId;
		}
		public void setJcTradeId(String jcTradeId) {
			this.jcTradeId = jcTradeId;
		}
		public String getTradType() {
			return tradType;
		}
		public void setTradType(String tradType) {
			this.tradType = tradType;
		}


}
