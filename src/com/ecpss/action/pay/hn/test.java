package com.ecpss.action.pay.hn;

public class test {
	
	
	public static void main(String[] args) {
		
		//String posNum="22222222";// 终端号
		//String merchant="021209999000000";//商户号
		String amount="100";//本地交易金额
//		String Transation_Local="";
		String Exp="1210";//有效期
		String cardno="4402602810763408";//卡号
		String cvv2="123";
		String orderNo="200055";//流水号	
		String txndate = "20110707182134";
		String posid = "31501851";
		String posno = "104310148164465";
		//交易
		HNGetMessage hgm = new  HNGetMessage();
		hgm.setTxn_id("01");   //做交易
		hgm.setPan(cardno);
		hgm.setCvv(cvv2);
		hgm.setExp_date(Exp);
		hgm.setSystrace(orderNo);
		hgm.setTxn_amt(amount);
		hgm.setCurrency_type("156");
		hgm.setPay_txn_date(txndate);
		hgm.setPosid(posid);
		hgm.setPos_merchant_no(posno);
		ClientBoc cb = new ClientBoc();
		cb.setHnGetMessage(hgm);
		hgm = cb.getMessage();
		
//		System.out.println("rrn:"+xgm.getRrn());
//		System.out.println("amount:"+xgm.getTxn_amt());
//		System.out.println("authno:"+xgm.getAuth_no());   
//		System.out.println("invoice:"+xgm.getInvoice());   
//		System.out.println("response: "+xgm.getRespcode());
////		//00 交易成功
//		if(xgm.getRespcode().equals("00")){
////			
////			//撤销
//			XMLGetMessage xgm2 = new XMLGetMessage();
//			xgm2.setTxn_id("0220");
//			xgm2.setSystrace("100879");
//			xgm2.setInvoice(xgm.getInvoice());
//			xgm2.setTxn_date(xgm.getTxn_date());
//			xgm2.setTxn_time(xgm.getTxn_time());
//			xgm2.setRrn(xgm.getRrn());
//			xgm2.setTxn_amt(amount);
//			xgm2.setOrg_systrace(orderNo);
//			xgm2.setAuth_no(xgm.getAuth_no());
//			xgm2.setPan(cardno);
//			xgm2.setPosid(posid);
//			ClientBoc cb2 = new ClientBoc();
//			cb2.setXmlGetMessage(xgm2);
//			xgm2 = cb2.getMessage("0220");
//			
//			System.out.println("invoice:"+xgm2.getInvoice());   
//			System.out.println("resp: "+xgm2.getRespcode());
//			System.out.println("rrn:"+xgm2.getRrn());
////			
////			
////			
//		}
//		
		//冲正
//		XMLGetMessage xgm3 = new XMLGetMessage();
//		xgm3.setTxn_id("0400");   //做交易
//		xgm3.setPan(cardno);
//		xgm3.setSystrace(orderNo);
//		xgm3.setTxn_amt(amount);
//		xgm3.setOrg_txnid("0200");
//		
//		ClientBoc cb3 = new ClientBoc();
//		cb3.setXmlGetMessage(xgm3);
//		xgm3 = cb3.getMessage("0400");
//		System.out.println("amount:"+xgm3.getTxn_amt());
//		System.out.println("response: "+xgm3.getRespcode());
//		System.out.println("org_txnid:"+xgm3.getOrg_txnid());
		
		//退款
//		XMLGetMessage xgm4 = new XMLGetMessage();
//		xgm4.setTxn_id("0260");
//		xgm4.setSystrace("100024");
//		xgm4.setInvoice("000520");
//		xgm4.setTxn_date("0519");
//		xgm4.setTxn_time("093339");
//		xgm4.setRrn("113901104385");
//		xgm4.setTxn_amt("1342.06");
//		xgm4.setPosid(posid);
//		xgm4.setOrg_systrace("000520");
//		xgm4.setAuth_no("03595B");
//		xgm4.setExp_date("1306");
//		xgm4.setPan("5490353814910237");
//		ClientBoc cb4 = new ClientBoc();
//		cb4.setXmlGetMessage(xgm4);
//		xgm4 = cb4.getMessage("0260");
		
//		System.out.println("invoice:"+xgm4.getInvoice());   
//		System.out.println("resp: "+xgm4.getRespcode());
//		System.out.println("rrn:"+xgm4.getRrn());
	}
}
