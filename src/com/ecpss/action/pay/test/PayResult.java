package com.ecpss.action.pay.test;

public class PayResult {
	private String code;
	private String name;
	private String[] st = {
			"00;Approved or Completed successfully",
			"01;Refer to card issuer",
			"02;Refer to card issuer's special condition",
			"03;Invalid merchant",
			"04;Pick-up",
			"05;Do not honor",
			"07;Pick-up card, special condition",
			"12;Invalid transaction",
			"13;Invalid amount",
			"14;Invalid card number (no such number)",
			"15;No such issuer",
			"19;Re-enter transaction",
			"30;Format error",
			"33;Expired card",
			"34;Suspected fraud",
			"35;Card acceptor contact security",
			"36;Restricted card",
			"37;Card acceptor call acquirer security",
			"38;Allowable PIN tries exceeded",
			"39;No credit account",
			"41;Lost card",
			"43;Stolen card, Pick-up",
			"51;Not sufficient funds",
			"54;Expired card",
			"55;Incorrect personal identification number",
			"57;Transaction not permitted to cardholder",
			"58;Transaction not permitted to terminal",
			"59;Suspected fraud",
			"60;Card acceptor contact acquirer",
			"61;Exceeds withdrawal amount limit",
			"62;Restricted card",
			"63;Security violation",
			"66;Card acceptor call acquirer's security department",
			"68;Response received too late",
			"75;Allowable number of PIN tries exceeded",
			"76;Product Code Error",
			"77;Reconcole error",
			"89;Bad Terminal ID",
			"91;Issuer or switch is inoperative",
			"92;Financial institution or intermediate networkFacility cannot be found for routing",
			"93;Transaction cannot be completed. Violation of Law",
			"94;Duplicate transaction", "95;Reconcile error",
			"96;System malfunction", "98;SW couldn't get reply from IS",
			"99;PIN Block Error", "XX;Chip Card Not Support",
			"YX;Accept Local Currency", "YY;Accept DCC transaction",
			"YZ;Dual Currency Card", };
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
	String tem="";
	for(int i=0;i<st.length;i++){
		if(this.code.equals(st[i].split(";")[0])){
			
	   this.name=st[i].split(";")[1];
		}
		
	}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
