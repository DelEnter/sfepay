package com.ecpss.action.tradedispose;

import java.util.StringTokenizer;

public class checkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "  31502144 000004 5466322220291441     20110613 000344      10616.24         32.32       1,583.92  00593Z PCEP 0000 MCCR 116316127457";
		StringTokenizer st = null;
		st = new StringTokenizer(a , " ");
		String str []  = new String[st.countTokens()];
		for (int i = 0; i < str.length; i++) {
			str[i]=st.nextToken();
		}
		System.out.println("posid--"+str[0]);
		System.out.println("cardnum-"+str[2]);
		System.out.println("date-"+str[3].substring(4, 8));
		System.out.println("time-"+str[4]);
		System.out.println("amount-"+str[5]);
		System.out.println("authno-"+str[8]);
		System.out.println("rrn-"+str[12]);
		
		System.out.println(Double.valueOf(str[5].replace(",","")));
		System.out.println(a.trim());
		System.out.println(a.trim().startsWith("3150"));
	}

}
