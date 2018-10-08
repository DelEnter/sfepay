package com.ecpss.action.pay.util;

public class CheckCardNo {
	/**
 	 * 验证信用卡卡号
 	 * @return
 	 */
	 public static boolean isValid (String cardNumber) {
		 	//sum是总和
		    int sum = 0;
		    //digit代表卡号的每一个数字是什么
		    int digit = 0;
		    //小计...每个卡号是奇数还是偶数,,,奇数就直接加进sum,偶数*2判断是否大与10  大于10就-9
		    int subtotal = 0;
		    //用一个boolean来判断这个数字是在奇数还是在偶数
		    boolean flag = false;
		    for (int i = cardNumber.length()-1; i >= 0; i--) {
		      digit = Integer.parseInt(cardNumber.substring(i, i + 1));
		      if (flag) {
		    	  subtotal = digit * 2;
		        if (subtotal > 9) {
		        	subtotal -= 9;
		        }
		      }else {
		    	  subtotal = digit;
		      }
		      sum += subtotal;
		      flag = !flag;
		    }
		    if(sum%10==0){
		    	return true;
		    }else{
		    	return false;
		    }
	 }
	 public static void main(String[] args) {
		 CheckCardNo c = new CheckCardNo();
		 System.out.println(c.isValid("4111111111111111"));  // AE
//		 System.out.println(c.isValid("3566002020360505"));  // jcb
//		 System.out.println(c.isValid("30000000000004"));  // jcb
 	}
}
