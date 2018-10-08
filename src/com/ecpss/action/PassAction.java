package com.ecpss.action;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.service.common.CommonService;
import com.ecpss.util.AES;

public class PassAction extends BaseAction{
	private Long beginno=0l;
	private Long endno=0l;

	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;

	
	
	public String addfast(){
		Long tim=System.currentTimeMillis();
		List<InternationalCardholdersInfo> list=this.commonService.list(" from InternationalCardholdersInfo p where p.id>="+beginno+" and p.id<="+endno+"");
		for(int i=0;i<list.size();i++){
			InternationalCardholdersInfo tem=list.get(i);
			if(tem.getCardNo().length()==16){
				String pw=AES.setCarNo(tem.getCardNo().trim());
				//String cvv=AES.setCarNo(tem.getCvv2().trim());
				//String exp= AES.setCarNo(tem.getExpiryDate().trim());
				this.commonService.deleteBySql(" update international_cardholdersinfo t set  t.cardNo='"+pw+"' where t.id='"+tem.getId()+"' ");
			}
		}
		System.out.print(System.currentTimeMillis()-tim);
		return "success";
	}
//	public String addfast2(){
//		Long tim=System.currentTimeMillis();
//		List<InternationalCardholdersInfo> list=this.commonService.list(" from InternationalCardholdersInfo p where p.tradeId>="+beginno+" and p.tradeId<="+endno+"");
//		for(int i=0;i<list.size();i++){
//			InternationalCardholdersInfo tem=list.get(i);
//			if(tem.getCardNo().length()!=16){
//				String pw=AES.getCarNo(tem.getCardNo().trim());
//				String cvv=AES.getCarNo(tem.getCvv2().trim());
//				String exp= AES.getCarNo(tem.getExpiryDate().trim());
//				this.commonService.deleteBySql(" update international_cardholdersinfo t set  t.cardNo='"+pw+"' ,t.cvv2='"+cvv+"' ,t.expiryDate='"+exp+"' where t.id='"+tem.getId()+"' ");
//			}
//		}
//		System.out.print(System.currentTimeMillis()-tim);
//		return "success";
//	}	
//	public String updatePro(){
//		this.pw=(PWard)this.commonService.load(PWard.class, this.pw.getId());
//		if(!this.valueFile.equals("")){
//		    String tem=AES.getDecrypt(pw.getPassword(), this.valueFile);
//		    PWard  pw2=new PWard();
//		    pw2.setConstatus("3");
//		    pw2.setPassword(tem);
//		    pw2.setLevote("2");
//		    pw2.setType(0l);
//		    pw2.setUsename(this.pw.getUsename());
//		    pw2.setUserId(this.getUserBean().getUserName());
//		    pw2.setFiledate(new Date());
//		    this.commonService.save(pw2);
//		    this.pw.setConstatus("0");
//		    
//		}
//		
//		return "success";
//	}



	public Long getBeginno() {
		return beginno;
	}



	public void setBeginno(Long beginno) {
		this.beginno = beginno;
	}



	public Long getEndno() {
		return endno;
	}



	public void setEndno(Long endno) {
		this.endno = endno;
	}
	



}
