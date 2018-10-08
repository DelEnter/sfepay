package com.ecpss.util;

public class BOCPayResult {
	private static String name;
	private static String[] st = {
			"00;Payment Success",
			"01:Refer to Card Issuer",
			"03;Invalid Merchant",
			"05;Do Not Honour",
			"08;Honour with I.D. Only",
			"12;Invalid Message",
			"13;Invalid Dollar Amount",
			"14;Invalid Card Number",
			"21;No Action Taken",
			"24;No File Update Allowed",
			"25;Record Not Found in File",
			"26;Duplicate Record on File",
			"28;Record or File Locked",
			"29;File Update Denied",
			"30;Format Error",
			"31;Invalid Acquirer Institution",
			"33;Card Expired",
			"38;Capture Card - PIN Retries Exceeded",
			"39;No Credit Account",
			"40;Invalid Function",
			"41;Lost Card",
			"43;Card is Hot/Stolen",
			"51;Insufficient Funds",
			"54;Card Expired - Do not pickup",
			"55;Invalid PIN - Retry",
			"56;Card Holder not on File",
			"57;Transaction not allowed for Card",
			"61;Exceeds Withdrawal or Transfer Limit",
			"62;Restricted Card",
			"63;Invalid MAC",
			"64;Invalid Original Amount",
			"65;Number of Withdrawals or Transfer Exceeded",
			"67;Force Capture of Card",
			"75;PIN Retries exceeded",
			"76;Invalid Interchange Amount",
			"77;Invalid Business Date",
			"78;Deactivated Card",
			"79;Invalid Account",
			"80;Transaction Denied",
			"81;Cancelled Card",
			"82;No acknowledgement from ATM ",
			"83;Host Refuse",
			"84;Issuer Down",
			"85;Invalid Originator or Processor",
			"86;Not Allowed on Device",
			"87;PIN Key Sync Error",
			"88;MAC Key Sync Error",
			"89;External Switch Decline",
			"90;Cutoff in Progress",
			"91;Message Timed Out",
			"92;Issuer Not Found",
			"94;Possible Duplicated Transaction ",
			"96;System malfunction",
			"97;Invalid ATM/POS IDs",
			"98;SW couldn’t get reply from IS",
			"99;PIN Block Error",
			"N0;Unmatched Transaction",
			"N1;Valid Unmatched Transaction",
			"Q2;Expiry Date does not Match with Database",
			"SK;Invalid Card Verification Value (CVV)",
			"R5;转帐货币不一致",
			"JJ;重复提交交易",
			"R6;转入行无此帐户",
			"N2;它行银联卡不支持人工授权交易",
	};
	public static String getName(String code) {
	String tem="";
	for(int i=0;i<st.length;i++){
		if(code.equals(st[i].split(";")[0])){
			
	   name=st[i].split(";")[1];
		}
		
	}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
