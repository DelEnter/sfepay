package vpn;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ecpss.util.ThreeDES;

public class HarbinPayMessage {
			// 接口参数
			private String inputCharset;
			private String pickupUrl;
			private String receiveUrl;
			private String version;
			private String signType;
			private String merchantId;
			private String orderNo;
			private String orderAmount;
			private String orderCurrency;
		    private String orderDatetime ;
		    private String extTL ;
		    private String payType ;
		    private String issuerId;
		    private String tradeNature;
		    private String signMsg;
		    private String firstName ;
		    private String lastName ;
		    private String cardNumber;
		    private String expiryMonth;
		    private String expiryYear;
		    private String cardCvv2;
		    private String md5key;
		    //退款
		    private String refundAmount;
		    private String originalOrderNo;
		    //订单查询
		    private String queryDatetime;
		    
		    //返回参数
		    private String res_paymentOrderId;
		    private String res_orderNo;
		    private String res_orderDatetime;
		    private String res_orderAmount;
		    private String res_payDatetime;
		    private String res_payAmount;
		    private String res_payResult;
		    private String res_errorCode;
		    private String res_returnDatetime;
		    private String res_authNo;
		    private String res_error;
		    //退款返回参数
		    private String res_refundAmount;
		    private String res_refundDatetime;
		    private String res_refundResult;
		    private String res_errorMessage;
		    

	public String getHash() {
		String tem = "";
		if (this.getInputCharset() != null) {
			if (tem.equals("")) {
				tem = tem + this.getInputCharset();
			} else {
				tem = tem + "&" + this.getInputCharset();
			}
		}
		if (this.getPickupUrl() != null) {
			if (tem.equals("")) {
				tem = tem +  this.getPickupUrl();
			} else {
				tem = tem + "&" + this.getPickupUrl();
			}
		}
		if (this.getReceiveUrl() != null) {
			if (tem.equals("")) {
				tem = tem + this.getReceiveUrl();
			} else {
				tem = tem + "&" + this.getReceiveUrl();
			}
		}
		if (this.getVersion() != null) {
			if (tem.equals("")) {
				tem = tem + this.getVersion();
			} else {
				tem = tem + "&" + this.getVersion();
			}
		}
		if (this.getSignType() != null) {
			if (tem.equals("")) {
				tem = tem +this.getSignType();
			} else {
				tem = tem + "&" + this.getSignType();
			}
		}
		if (this.getMerchantId() != null) {
			if (tem.equals("")) {
				tem = tem + this.getMerchantId();
			} else {
				tem = tem + "&" + this.getMerchantId();
			}
		}

		if (this.getOrderNo() != null) {
			if (tem.equals("")) {
				tem = tem + this.getOrderNo();
			} else {
				tem = tem + "&" + this.getOrderNo();
			}
		}
		if (this.getOrderAmount() != null) {
			if (tem.equals("")) {
				tem = tem + this.getOrderAmount();
			} else {
				tem = tem + "&" + this.getOrderAmount();
			}
		}
		if (this.getRefundAmount() != null) {
			if (tem.equals("")) {
				tem = tem + this.getRefundAmount();
			} else {
				tem = tem + "&" + this.getRefundAmount();
			}
		}
		if (this.getOrderCurrency() != null) {
			if (tem.equals("")) {
				tem = tem + this.getOrderCurrency();
			} else {
				tem = tem + "&" + this.getOrderCurrency();
			}
		}
		if (this.getOrderDatetime() != null) {
			if (tem.equals("")) {
				tem = tem + this.getOrderDatetime();
			} else {
				tem = tem + "&" + this.getOrderDatetime();
			}
		}
		if (this.getOriginalOrderNo() != null) {
			if (tem.equals("")) {
				tem = tem + this.getOriginalOrderNo();
			} else {
				tem = tem + "&" + this.getOriginalOrderNo();
			}
		}
		if (this.getExtTL() != null&&this.getRefundAmount()==null) {
			if (tem.equals("")) {
				tem = tem + this.getExtTL();
			} else {
				tem = tem + "&" + this.getExtTL();
			}
		}
		if (this.getPayType() != null) {
			if (tem.equals("")) {
				tem = tem + this.getPayType();
			} else {
				tem = tem + "&" + this.getPayType();
			}
		}
		if (this.getIssuerId() != null) {
			if (tem.equals("")) {
				tem = tem + this.getIssuerId();
			} else {
				tem = tem + "&" + this.getIssuerId();
			}
		}
		if (this.getTradeNature() != null) {
			if (tem.equals("")) {
				tem = tem + this.getTradeNature();
			} else {
				tem = tem + "&" + this.getTradeNature();
			}
		}
		if (this.getMd5key() != null) {
			if (tem.equals("")) {
				tem = tem + this.getMd5key();
			} else {
				tem = tem + "&" + this.getMd5key();
			}
		}
		System.out.println(tem);
		return MD5Encode(tem);
	}
	public String getQueryHash() {
		String tem = "";
		if (this.getMerchantId() != null) {
			if (tem.equals("")) {
				tem = tem + this.getMerchantId();
			} else {
				tem = tem + "&" + this.getMerchantId();
			}
		}
		if (this.getVersion() != null) {
			if (tem.equals("")) {
				tem = tem + this.getVersion();
			} else {
				tem = tem + "&" + this.getVersion();
			}
		}
		if (this.getSignType() != null) {
			if (tem.equals("")) {
				tem = tem + this.getSignType();
			} else {
				tem = tem + "&" + this.getSignType();
			}
		}
		if (this.getOrderNo() != null) {
			if (tem.equals("")) {
				tem = tem + this.getOrderNo();
			} else {
				tem = tem + "&" + this.getOrderNo();
			}
		}
		if (this.getOrderDatetime() != null) {
			if (tem.equals("")) {
				tem = tem + this.getOrderDatetime();
			} else {
				tem = tem + "&" + this.getOrderDatetime();
			}
		}
		if (this.getQueryDatetime() != null) {
			if (tem.equals("")) {
				tem = tem + this.getQueryDatetime();
			} else {
				tem = tem + "&" + this.getQueryDatetime();
			}
		}
		if (this.getMd5key() != null) {
			if (tem.equals("")) {
				tem = tem + this.getMd5key();
			} else {
				tem = tem + "&" + this.getMd5key();
			}
		}
		return MD5Encode(tem);
	}


	public static String MD5Encode(String aData) throws SecurityException {
		String resultString = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = bytes2HexString(md.digest(aData.getBytes("UTF-8")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("MD5运算失败");
		}
		return resultString;
	}

	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}


	public String getPickupUrl() {
		return pickupUrl;
	}


	public void setPickupUrl(String pickupUrl) {
		this.pickupUrl = pickupUrl;
	}


	public String getReceiveUrl() {
		return receiveUrl;
	}


	public void setReceiveUrl(String receiveUrl) {
		this.receiveUrl = receiveUrl;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}


	public String getMerchantId() {
		return merchantId;
	}


	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getOrderAmount() {
		return orderAmount;
	}


	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}


	public String getOrderCurrency() {
		return orderCurrency;
	}


	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}


	public String getOrderDatetime() {
		return orderDatetime;
	}


	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}


	public String getExtTL() {
		JSONObject jsonObject = new JSONObject();  
        jsonObject.put("firstName", firstName);  
        jsonObject.put("lastName", lastName); 
        jsonObject.put("cardNumber", cardNumber);  
        jsonObject.put("expiryMonth", expiryMonth); 
        jsonObject.put("expiryYear", expiryYear);  
        jsonObject.put("cardCvv2", cardCvv2);
        ThreeDES des=new ThreeDES();
        des.setKey(this.getMd5key().substring(0, 16));
        String desInfo = null;
		try {
			desInfo = des.encrypt(jsonObject.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return desInfo;
		
	}


	public void setExtTL(String extTL) {
		this.extTL = extTL;
	}


	public String getPayType() {
		return payType;
	}


	public void setPayType(String payType) {
		this.payType = payType;
	}


	public String getSignMsg() {
		return signMsg;
	}


	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
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


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getExpiryMonth() {
		return expiryMonth;
	}


	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}


	public String getExpiryYear() {
		return expiryYear;
	}


	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}


	public String getCardCvv2() {
		return cardCvv2;
	}


	public void setCardCvv2(String cardCvv2) {
		this.cardCvv2 = cardCvv2;
	}


	public String getMd5key() {
		return md5key;
	}


	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}


	public String getRes_paymentOrderId() {
		return res_paymentOrderId;
	}


	public void setRes_paymentOrderId(String res_paymentOrderId) {
		this.res_paymentOrderId = res_paymentOrderId;
	}


	public String getRes_orderNo() {
		return res_orderNo;
	}


	public void setRes_orderNo(String res_orderNo) {
		this.res_orderNo = res_orderNo;
	}


	public String getRes_orderDatetime() {
		return res_orderDatetime;
	}


	public void setRes_orderDatetime(String res_orderDatetime) {
		this.res_orderDatetime = res_orderDatetime;
	}


	public String getRes_orderAmount() {
		return res_orderAmount;
	}


	public void setRes_orderAmount(String res_orderAmount) {
		this.res_orderAmount = res_orderAmount;
	}


	public String getRes_payDatetime() {
		return res_payDatetime;
	}


	public void setRes_payDatetime(String res_payDatetime) {
		this.res_payDatetime = res_payDatetime;
	}


	public String getRes_payAmount() {
		return res_payAmount;
	}


	public void setRes_payAmount(String res_payAmount) {
		this.res_payAmount = res_payAmount;
	}


	public String getRes_payResult() {
		return res_payResult;
	}


	public void setRes_payResult(String res_payResult) {
		this.res_payResult = res_payResult;
	}


	public String getRes_errorCode() {
		return res_errorCode;
	}


	public void setRes_errorCode(String res_errorCode) {
		this.res_errorCode = res_errorCode;
	}


	public String getRes_returnDatetime() {
		return res_returnDatetime;
	}


	public void setRes_returnDatetime(String res_returnDatetime) {
		this.res_returnDatetime = res_returnDatetime;
	}


	public String getInputCharset() {
		return inputCharset;
	}


	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}


	public String getTradeNature() {
		return tradeNature;
	}


	public void setTradeNature(String tradeNature) {
		this.tradeNature = tradeNature;
	}


	public String getRes_authNo() {
		return res_authNo;
	}


	public void setRes_authNo(String res_authNo) {
		this.res_authNo = res_authNo;
	}


	public String getRefundAmount() {
		return refundAmount;
	}


	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}


	public String getOriginalOrderNo() {
		return originalOrderNo;
	}


	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}


	public String getRes_error() {
		return res_error;
	}


	public void setRes_error(String res_error) {
		this.res_error = res_error;
	}


	public String getRes_refundAmount() {
		return res_refundAmount;
	}


	public void setRes_refundAmount(String res_refundAmount) {
		this.res_refundAmount = res_refundAmount;
	}


	public String getRes_refundDatetime() {
		return res_refundDatetime;
	}


	public void setRes_refundDatetime(String res_refundDatetime) {
		this.res_refundDatetime = res_refundDatetime;
	}


	public String getRes_refundResult() {
		return res_refundResult;
	}


	public void setRes_refundResult(String res_refundResult) {
		this.res_refundResult = res_refundResult;
	}


	public String getRes_errorMessage() {
		return res_errorMessage;
	}


	public void setRes_errorMessage(String res_errorMessage) {
		this.res_errorMessage = res_errorMessage;
	}


	public String getQueryDatetime() {
		return queryDatetime;
	}


	public void setQueryDatetime(String queryDatetime) {
		this.queryDatetime = queryDatetime;
	}


}
