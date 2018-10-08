package com.ecpss.action.pay.tc;

public class XMLGetMessage {

	private String txn_id; // 交易类别

	private String pan; // 卡号

	private String exp_date; // 有效期 YYMM

	private String cvv; // CVV

	private String txn_amt; // 交易金额 保留2位小数

	private String systrace; // 流水号 6位不唯一

	private String org_systrace; // 原始流水号

	private String rrn; // 参考号 12位

	private String invoice; // 凭证号
	
	private String txn_date; // 交易日期 yyyymmdd

	private String txn_time; // 交易时间 HHMMSS

	private String respcode; // 响应码 00:成功

	private String auth_no; // 授权号

	private String org_txnid; //原始交易类型
	
	private String posid;  //终端号
	
	public String getMessage(String type) {
		/**
		 * 0200 交易 0220 消费撤销 0400 冲正
		 */
		String tranDataStr="";
		if (type.equals("0200")) {
			//消费
			tranDataStr = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>"
					+ "<ap>"
						+ "<txn_id>"+this.txn_id+"</txn_id>"
						+ "<systrace>"+this.systrace+"</systrace>"
						+ "<pan>"+this.pan+"</pan>"
						+ "<exp_date>"+this.exp_date+"</exp_date>"
						+ "<cvv>"+this.cvv+"</cvv>"
						+ "<posid>"+this.posid+"</posid>"
						+ "<txn_amt>"+this.txn_amt+"</txn_amt>" 
					+ "</ap></xml>";
		}else if(type.equals("0220")){
			
			///撤销
			tranDataStr = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>"
				+ "<ap>"
					+ "<txn_id>"+this.txn_id+"</txn_id>"
					+ "<systrace>"+this.systrace+"</systrace>"
					+ "<invoice>"+this.invoice+"</invoice>"
					+ "<txn_date>"+this.txn_date+"</txn_date>"
					+ "<txn_time>"+this.txn_time+"</txn_time>"
					+ "<rrn>"+this.rrn+"</rrn>" 
					+ "<pan>"+this.pan+"</pan>" 
					+ "<txn_amt>"+this.txn_amt+"</txn_amt>" 
					+ "<org_systrace>"+this.org_systrace+"</org_systrace>" 
					+ "<posid>"+this.posid+"</posid>"
					+ "<auth_no>"+this.auth_no+"</auth_no>" 
				+ "</ap></xml>";
		}else if(type.equals("0260")){
			
			//refund
			tranDataStr = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>"
				+ "<ap>"
					+ "<txn_id>"+this.txn_id+"</txn_id>"
					+ "<systrace>"+this.systrace+"</systrace>"
					+ "<invoice>"+this.invoice+"</invoice>"
					+ "<txn_date>"+this.txn_date+"</txn_date>"
					+ "<txn_time>"+this.txn_time+"</txn_time>"
					+ "<rrn>"+this.rrn+"</rrn>" 
					+ "<pan>"+this.pan+"</pan>" 
					+ "<posid>"+this.posid+"</posid>" 
					+ "<exp_date>"+this.exp_date+"</exp_date>" 
					+ "<txn_amt>"+this.txn_amt+"</txn_amt>" 
					+ "<org_systrace>"+this.org_systrace+"</org_systrace>" 
					+ "<posid>"+this.posid+"</posid>"
					+ "<auth_no>"+this.auth_no+"</auth_no>" 
				+ "</ap></xml>";
		}else if(type.equals("0800")){
			
			//冲正
			tranDataStr = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"no\"?>"
				+ "<ap>"
					+ "<txn_id>"+this.txn_id+"</txn_id>"
					+ "<posid>"+this.posid+"</posid>"
				+ "</ap></xml>";
		}else if(type.equals("0400")){
			
		}
		//长度补零
		String length = String.format("%04d", tranDataStr.length());
//		System.out.println(tranDataStr.length());
//		String length = tranDataStr.length()+"";
//		if(length.length()<4){
//			if(length.length()==3){
//				length="0"+length;
//			}
//		}
		//System.out.println("request:"+length+tranDataStr);
		return length+tranDataStr;
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

	public String getTxn_date() {
		return txn_date;
	}

	public void setTxn_date(String txn_date) {
		this.txn_date = txn_date;
	}

	public String getTxn_time() {
		return txn_time;
	}

	public void setTxn_time(String txn_time) {
		this.txn_time = txn_time;
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

}
