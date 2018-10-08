package com.ecpss.action.pay.hn;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.pay.dcc.DCCMessageUtil;

public class HNSetMessage {
	
	private HNGetMessage hnGetMessage=new HNGetMessage();
	
	private String str="008A600500000032393331000230313030320010343838383838383838383838383838383031310006303030303031303132000E3230313130363232313830303032303337000C3230313130363232313830303033380006363038393431303339000230303034310008313233343536373830363000063030303030313939390008bdbbd2d7b3c9b9a60d0a" ;
	
	public void reciveRes(String str){
		int totalLength = Integer.parseInt(str.substring(0, 4),16);
		System.out.println(totalLength);
		String restlv = str.substring(16, str.length());
		System.out.println(restlv);
		int p=0;
		int t = restlv.length();
		boolean flag=true;
		while(flag){
			String biaoqian16 = restlv.substring(p, p+6);
			String biaoqian = new String(DCCMessageUtil.hexStringToByte(biaoqian16));
			//System.out.println("标签:"+biaoqian);
			p+=6;
			String l = restlv.substring(p, p+4);
			p+=4;
			int xl = Integer.parseInt(l,16);
			//System.out.println("val长度是:"+xl);
			String val = restlv.substring(p, p+2*xl);
			//System.out.println("标签val:"+new String(DCCMessageUtil.hexStringToByte(val)));
			p+=(xl*2);
			setResStr(biaoqian,new String(DCCMessageUtil.hexStringToByte(val)));
			if(p==t){
				flag=false;
			}
		}
		
	}
	
	
	public void setResStr(String key,String value){
		if(key.equals("002")){
			hnGetMessage.setPan(value);
		}
		if(key.equals("004")){
			hnGetMessage.setTxn_amt(value);
		}
		if(key.equals("011")){
			hnGetMessage.setSystrace(value);
		}
		if(key.equals("012")){
			hnGetMessage.setBank_txn_date(value);
		}
		if(key.equals("013")){
			hnGetMessage.setPay_txn_date(value);
		}
		if(key.equals("014")){
			hnGetMessage.setExp_date(value);
		}
		if(key.equals("018")){
			hnGetMessage.setCurrency_type(value);
		}
		if(key.equals("037")){
			hnGetMessage.setRrn(value);
		}
		if(key.equals("038")){
			hnGetMessage.setAuth_no(value);
		}
		if(key.equals("039")){
			hnGetMessage.setRespcode(value);
		}
		if(key.equals("041")){
			hnGetMessage.setPosid(value);
		}
		if(key.equals("042")){
			hnGetMessage.setPos_merchant_no(value);
		}
		if(key.equals("048")){
			hnGetMessage.setCvv(value);
		}
		if(key.equals("060")){
			hnGetMessage.setBatchno(value);
		}
		if(key.equals("061")){
			hnGetMessage.setInvoice(value);
		}
		if(key.equals("062")){
			hnGetMessage.setOrg_systrace(value);
		}
		if(key.equals("063")){
			hnGetMessage.setOrg_txn_date(value);
		}
		if(key.equals("931")){
			hnGetMessage.setTxn_id(value);
		}
	}


	public HNGetMessage getHnGetMessage() {
		return hnGetMessage;
	}

	public void setHnGetMessage(HNGetMessage hnGetMessage) {
		this.hnGetMessage = hnGetMessage;
	}
	
	
	
}
