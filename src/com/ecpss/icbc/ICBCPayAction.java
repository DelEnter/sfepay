package com.ecpss.icbc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ecpss.action.BaseAction;

public class ICBCPayAction extends BaseAction{
	
	private String interfaceName;
	private String interfaceVersion;
	private String tranData;
	private String merSignMsg;
	private String merCert;
	private String icbcpayurl;
	private IcbcUtils icbcUtils = new IcbcUtils();
	/**
	 * 工商银行支付
	 * @return
	 */
	public String icbcpay(){
		icbcpayurl = "https://mybank.icbc.com.cn/servlet/NewB2cMerPayReqServlet";
		interfaceName = "ICBC_PERBANK_B2C";
		interfaceVersion = "1.0.0.3";
		DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowdate = formatter.format(new Date());
		String tranDataStr = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>" +
				"<B2CReq>" +
					"<interfaceName>ICBC_PERBANK_B2C</interfaceName>" +
					"<interfaceVersion>1.0.0.3</interfaceVersion>" +
					"<orderInfo>" +
						"<orderDate>"+nowdate+"</orderDate>" +
						"<orderid>005</orderid>" +
						"<amount>1</amount>" +
						"<curType>001</curType>" +
						"<merID>2102EC23720701</merID>" +
						"<merAcct>2102102019300750985</merAcct>" +
					"</orderInfo>" +
					"<custom>" +
						"<verifyJoinFlag>0</verifyJoinFlag>" +
						"<Language>ZH_CN</Language>" +
					"</custom>" +
					"<message>" +
						"<goodsID>Test001</goodsID>" +
						"<goodsName></goodsName>" +
						"<goodsNum></goodsNum>" +
						"<carriageAmt></carriageAmt>" +
						"<merHint></merHint>" +
						"<remark1></remark1>" +
						"<remark2></remark2>" +
						"<merURL>http://localhost:8888/ecpss/Newb2cPayRes</merURL>" +
						"<merVAR>test</merVAR>" +
					"</message>" +
				"</B2CReq>";
		tranData = icbcUtils.getBase64Str(tranDataStr);
		merSignMsg = icbcUtils.getSignMsgBase64(tranDataStr, "12345678");
		merCert = icbcUtils.getCertMsgBase64(tranDataStr, "12345678");
		
		return SUCCESS;
	}
	
	
	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getInterfaceVersion() {
		return interfaceVersion;
	}
	public void setInterfaceVersion(String interfaceVersion) {
		this.interfaceVersion = interfaceVersion;
	}
	public String getTranData() {
		return tranData;
	}
	public void setTranData(String tranData) {
		this.tranData = tranData;
	}
	public String getMerSignMsg() {
		return merSignMsg;
	}
	public void setMerSignMsg(String merSignMsg) {
		this.merSignMsg = merSignMsg;
	}
	public String getMerCert() {
		return merCert;
	}
	public void setMerCert(String merCert) {
		this.merCert = merCert;
	}
	public String getIcbcpayurl() {
		return icbcpayurl;
	}
	public void setIcbcpayurl(String icbcpayurl) {
		this.icbcpayurl = icbcpayurl;
	}
}
