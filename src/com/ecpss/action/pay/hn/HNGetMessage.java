package com.ecpss.action.pay.hn;

import org.apache.commons.lang.StringUtils;

public class HNGetMessage {

	private String txn_id; // 交易类别

	private String pan; // 卡号

	private String exp_date; // 有效期 YYMM

	private String cvv; // CVV

	private String currency_type;  //币种 156
	
	private String txn_amt; // 交易金额 保留2位小数

	private String systrace; // 流水号 6位不唯一

	private String org_systrace; // 原始流水号

	private String rrn; // 参考号 12位

	private String invoice; // 凭证号
	
	private String bank_txn_date; // 银行交易日期 YYYYMMDDHHMMSS

	private String pay_txn_date; // 第三方交易时间 YYYYMMDDHHMMSS
	
	private String org_txn_date; //原始交易日期

	private String respcode; // 响应码 00:成功

	private String auth_no; // 授权号

	private String org_txnid; //原始交易类型
	
	private String batchno;  //票据好
	
	private String posid;  //终端号
	
	private String pos_merchant_no;  //终端商户号
	
	private String res_remark;  //响应码描述
	
	
	public String getMessage() {
		/**
		 * 01 交易 02 消费撤销 03 退货 05 冲正
		 */
		String tranDataStr="";
		//交易类型
		if(StringUtils.isNotBlank(txn_id)){
			tranDataStr+=getTLVStr("931",txn_id);
		}
		//卡号
		if(StringUtils.isNotBlank(pan)){
			tranDataStr+=getTLVStr("002",pan);
		}
		//金额
		if(StringUtils.isNotBlank(txn_amt)){
			tranDataStr+=getTLVStr("004",txn_amt);
		}
		//终端流水号
		if(StringUtils.isNotBlank(systrace)){
			tranDataStr+=getTLVStr("011",systrace);
		}
		//银行交易日期
		if(StringUtils.isNotBlank(bank_txn_date)){
			tranDataStr+=getTLVStr("012",bank_txn_date);
		}
		//第三方支付交易日期
		if(StringUtils.isNotBlank(pay_txn_date)){
			tranDataStr+=getTLVStr("013",pay_txn_date);
		}
		//有效期
		if(StringUtils.isNotBlank(this.exp_date)){
			tranDataStr+=getTLVStr("014",exp_date);
		}
		//币种
		if(StringUtils.isNotBlank(currency_type)){
			tranDataStr+=getTLVStr("018",currency_type);
		}
		//系统参考号
		if(StringUtils.isNotBlank(rrn)){
			tranDataStr+=getTLVStr("037",rrn);
		}
		//授权码
		if(StringUtils.isNotBlank(auth_no)){
			tranDataStr+=getTLVStr("038",auth_no);
		}
		//响应码
		if(StringUtils.isNotBlank(respcode)){
			tranDataStr+=getTLVStr("039",respcode);
		}
		//终端号
		if(StringUtils.isNotBlank(posid)){
			tranDataStr+=getTLVStr("041",posid);
		}
		//终端商户号
		if(StringUtils.isNotBlank(pos_merchant_no)){
			tranDataStr+=getTLVStr("042",pos_merchant_no);
		}
		
		//cvv
		if(StringUtils.isNotBlank(cvv)){
			tranDataStr+=getTLVStr("048",cvv);
		}
		//交易批次号
		if(StringUtils.isNotBlank(batchno)){
			tranDataStr+=getTLVStr("060",batchno);
		}
		//交易票据号
		if(StringUtils.isNotBlank(invoice)){
			tranDataStr+=getTLVStr("061",invoice);
		}
		//原始交易流水号
		if(StringUtils.isNotBlank(org_systrace)){
			tranDataStr+=getTLVStr("062",org_systrace);
		}
		//原是交易日期
		if(StringUtils.isNotBlank(org_txn_date)){
			tranDataStr+=getTLVStr("063",org_txn_date);
		}
		//响应码描述
		if(StringUtils.isNotBlank(res_remark)){
			tranDataStr+=getTLVStr("999",res_remark);
		}
		
		//长度补零
		//String length = String.format("%04d", tranDataStr.length());
		String add = "600000050031";
		int len = tranDataStr.length()/2 + 6;
		System.out.println("总长度-"+len);
		String leng = Integer.toHexString(len);
		if(leng.length()==2){
			leng="00"+leng;
			System.out.println(leng);
		}
		if(leng.length()==3){
			leng="0"+leng;
			System.out.println(leng);
		}
		if(leng.length()==1){
			leng="0000"+leng;
			System.out.println(leng);
		}
		String str = leng+add+tranDataStr;
		return str;
	}
	public static void main(String[] args) {
		String a = getTLVStr("012","20110622180002");
		System.out.println(a);
	}
	/**
	 * 组成报文TLV内容
	 * @param txn_id
	 * @param txn_value
	 * @return
	 */
	public static final String getTLVStr(String txn_tye,String txn_value){
		String tranDataStr="";
		//标签
		String txn_id_16 = bytesToHexString(txn_tye.getBytes());
		//长度
		String txn_id_len = Integer.toHexString(txn_value.length());
		if(txn_id_len.length()==1){
			txn_id_len="000"+txn_id_len;
		}else{
			txn_id_len="00"+txn_id_len;
		}
		//内容
		String txn_id_val = bytesToHexString(txn_value.getBytes());
		
		tranDataStr=txn_id_16+txn_id_len+txn_id_val;
		return tranDataStr;
	}
	
	
	/** */
	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
	
	/*
	 * 把16进制字符串转换成字节数组 @param hex @return
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}
	
	
	public String getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getExp_date() {
		return exp_date;
	}

	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getTxn_amt() {
		return txn_amt;
	}

	public void setTxn_amt(String txn_amt) {
		this.txn_amt = txn_amt;
	}

	public String getSystrace() {
		return systrace;
	}

	public void setSystrace(String systrace) {
		this.systrace = systrace;
	}

	public String getOrg_systrace() {
		return org_systrace;
	}

	public void setOrg_systrace(String org_systrace) {
		this.org_systrace = org_systrace;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}


	public String getBank_txn_date() {
		return bank_txn_date;
	}

	public void setBank_txn_date(String bank_txn_date) {
		this.bank_txn_date = bank_txn_date;
	}

	public String getPay_txn_date() {
		return pay_txn_date;
	}

	public void setPay_txn_date(String pay_txn_date) {
		this.pay_txn_date = pay_txn_date;
	}

	public String getRespcode() {
		return respcode;
	}

	public void setRespcode(String respcode) {
		this.respcode = respcode;
	}

	public String getAuth_no() {
		return auth_no;
	}

	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getOrg_txnid() {
		return org_txnid;
	}

	public void setOrg_txnid(String org_txnid) {
		this.org_txnid = org_txnid;
	}

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public String getPos_merchant_no() {
		return pos_merchant_no;
	}

	public void setPos_merchant_no(String pos_merchant_no) {
		this.pos_merchant_no = pos_merchant_no;
	}
	public String getCurrency_type() {
		return currency_type;
	}
	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}
	public String getOrg_txn_date() {
		return org_txn_date;
	}
	public void setOrg_txn_date(String org_txn_date) {
		this.org_txn_date = org_txn_date;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getRes_remark() {
		return res_remark;
	}
	public void setRes_remark(String res_remark) {
		this.res_remark = res_remark;
	}

}
