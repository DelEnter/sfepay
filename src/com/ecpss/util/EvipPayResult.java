package com.ecpss.util;

public class EvipPayResult {
	private static String name;
	private static String[] st = {
			"00;Approved or Completed successfully",
			"03;Invalid merchant",
			"04;Your transaction was not approved by your bank.Please contact your Bank #(04)",
			"05;Your Transaction was declined by Your Bank please verify with your Bank to allow to process your transaction or remove the restrictions from your card.Thank you #(05)",
			"07;Card could be stolen confirm your card with your Bank.#(07)",
			"12;Invalid transaction",
			"13;Invalid amount",
			"14;You have entered Invalid card number,please try again or use different card.#(14)",
			"15;You have entered inccorect Bank name, please enter correct Bank name of your card",
			"19;Try again please",
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
			"51;Your Card has insuffucient funds please check your balance and try again.Thank you#(51)",
			"54;Expired card",
			"55;Incorrect personal identification number",
			"57;Transaction not permitted to cardholder",
			"58;Transaction not permitted to terminal",
			"59;Transaction Declined due to fraud issues. Please contact your bank",
			"60;Card acceptor contact acquirer",
			"61;Exceeds withdrawal amount limit",
			"62;Your Card is restricted! Please try using your different card or contact your Bank.(62)",
			"63;Security violation",
			"66;Card acceptor call acquirer's security department",
			"68;Response received too late",
			"75;Allowable number of PIN tries exceeded",
			"76;Product Code Error",
			"77;Try again with different amount",
			"89;Bad Terminal ID",
			"91;Issuer or switch is inoperative",
			"92;Financial institution or intermediate network Facility cannot be found for routing",
			"93;Transaction cannot be completed. Violation of Law",
			"94;Duplicate transaction", 
			"95;Reconcile error",
			"96;System malfunction", 
			"98;SW couldn't get reply from IS",
			"99;PIN Block Error", "XX;Chip Card Not Support",
			"YX;Card Bin not allow DCC transaction",
			"YY;Card Bin allow DCC transaction", 
			"YZ;Dual Currency Card" };

	public static String getName(String code) {
		String tem = "";
		for (int i = 0; i < st.length; i++) {
			if (code.equals(st[i].split(";")[0])) {
				name = st[i].split(";")[1];
			}
		}
		if(name==null){
			name="Payment Decline";
		}
		return name;
	}
	public static String getVpnName(String code) {
        return getName(code.substring(2, 4));
	}
	public void setName(String name) {
		this.name = name;
	}

}
