package com.ecpss.action.merchant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.TradeManager;
import com.ecpss.model.payment.InternationalSettlment;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.payment.ViewSettlement;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.web.PageInfo;

public class MerchantApplySettleMentAction extends BaseAction{
	private List<Double>  foreignMoney;
	private PageInfo info;
	private Double rmbmoney=0d;
	private String orderNo="";
	private InternationalMerchant tradeinfo = new InternationalMerchant();
	public List listName ;
	@Autowired
	@Qualifier("tradeManager")
	private TradeManager tradeManager;
	private Long batchNo;
    private String	startDate;
    private String	endDate;
    private int totPage;
	private List tradeList2;
	private String totleMoney;
	private Double nosettlementmoney;
	private Double nobanlancesMoney;
	private String banlancesMoney;
    private String remark;
	private List tradeList;	
	private Object[] freezeId;// 选中的ID	
	public String applyMerSettlement(){
		String temID = "";
		if (this.freezeId != null) {
			if (this.freezeId.length > 0) {
				for (int i = 0; i < freezeId.length; i++) {
					temID = temID + freezeId[i] + ',';
				}

				temID = temID.substring(0, temID.length() - 1);

					this.commonService
							.deleteBySql("update international_tradeinfo a set  a.tradestate=substr(a.tradestate,1,5)||'1'||substr(a.tradestate,7, length(a.tradestate)-6),a.applyTime=sysdate where a.id in("
									+ temID + ")");

			}

		}		
		int cycleTime=0;
		 cycleTime=this.tradeManager.intBySql("select min(t.cycletime)-to_char(sysdate,'dd') from settlementcycletime t where  t.cycletime>=to_char(sysdate,'dd') and t.merchant='"+this.getMerchantBean().getMerchantId()+"'");
		if(cycleTime==-1){
			 cycleTime=this.tradeManager.intBySql(" select  trunc(LAST_DAY(sysdate),'dd')-trunc(sysdate,'dd')+min(t.cycletime) from  settlementcycletime t where  t.merchant='"+this.getMerchantBean().getMerchantId()+"'");
	}
		
		this.tradeinfo.setMerno(this.tradeinfo.getMerno());
		StringBuffer sb = new StringBuffer();
				sb
			.append(" select t,f from InternationalTradeinfo t , InternationalMerchant f,  InternationalMerchantChannels g where g.id=t.tradeChannel and t.merchantId=f.id and t.tradeState like '1_0__000000%' ");
		sb.append(" and substr(t.tradeState,2,1)in(0,2) ") ;    
		sb.append(" and substr(t.tradeState,4,1)in(0,2) ") ;    
		sb.append(" and ( t.isTrackNo is not null or t.isPicture is not null ) ");
		sb.append(" and  trunc(sysdate,'dd')-trunc(t.tradeTime)>=(g.balanceCycle-"+cycleTime+")");
		sb
				.append(" and  f.merno='"
						+ this.getMerchantBean().getMerChantNo()+ "'");
		this.tradeList = this.commonService.list(sb.toString());

		return this.SUCCESS;
	}
	public String merSettlementhis(){
		StringBuffer sb = new StringBuffer();

				sb.append(" from InternationalTradeinfo t where substr(t.tradeState,6,1)='1' and substr(t.tradeState,7,1)<>'2' and substr(t.tradeState,8,1)='0' ");
				sb.append(" and t.merchantId='"+this.getMerchantBean().getMerchantId()+"'");
				sb.append(" order by t.tradeTime ");
				this.tradeList = this.commonService.list(sb.toString());

		return this.SUCCESS;		
		
	}
	//跳转到划款历史
	public String toSettlementhis(){

			if (this.info == null) {
				this.info = new PageInfo();
			}

					StringBuffer sb = new StringBuffer();
					StringBuffer sb2 = new StringBuffer();
					sb.append(" select t,m from InternationalSettlment t ,InternationalMerchant m where t.istrue='1' and t.merchantno=m.id and m.id='"+getMerchantBean().getMerchantId()+"'");
					this.info.setPageSize(10);
				if(this.batchNo!=null){
					sb.append("and t.batchno='"+this.batchNo+"'");
				}
				if(StringUtils.isNotBlank(startDate)){  //开始日期
					sb.append(" and t.huakuantime>=to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss') ");
				}
				if(StringUtils.isNotBlank(endDate)){   //结束日期
					sb.append(" and t.huakuantime<=to_date('"+endDate+"','yyyy-MM-dd hh24:mi:ss') ");
				}				
					this.info = this.commonService.listQueryResultByHql(sb
							.toString(), info);

		
		return this.SUCCESS;
	}
	//显示划款总账
	public String merchantShowView(){
		
		this.tradeinfo = (InternationalMerchant) this.commonService
		.list(
				" from InternationalMerchant t where t.id='"+this.getMerchantBean().getMerchantId()+"'").get(0);			
    	this.listName=this.commonService.list("from ViewSettlement t where t.batchno='"+batchNo+"'");
   		this.totPage=listName.size();     
        for(int i=0;i<listName.size();i++){
        	ViewSettlement hf=(ViewSettlement)listName.get(i);
        	
        this.rmbmoney+=hf.getRmbmoney();	
        }
    	this.remark= "";
    	InternationalSettlment   ist= (InternationalSettlment)this.commonService.list("from  InternationalSettlment t where  t.batchno='"+batchNo+"'").get(0);
        this.remark=ist.getRemark();   		
  	return this.SUCCESS;
	}
	//英文后台显示划款总账
	public String merchantShowViewen(){

		this.tradeinfo = (InternationalMerchant) this.commonService
		.list(
				" from InternationalMerchant t where t.id='"+this.getMerchantBean().getMerchantId()+"'").get(0);			
    	this.listName=this.commonService.list("from ViewSettlement t where t.batchno='"+batchNo+"'");
   		this.totPage=listName.size();     
   		foreignMoney =new ArrayList();
        for(int i=0;i<listName.size();i++){
        	ViewSettlement hf=(ViewSettlement)listName.get(i);
        	
        	foreignMoney.add(hf.getBalancemoney()-hf.getProtestFee());
        	
        this.rmbmoney+=foreignMoney.get(i);	
        }
    	this.remark= "";
    	InternationalSettlment   ist= (InternationalSettlment)this.commonService.list("from  InternationalSettlment t where  t.batchno='"+batchNo+"'").get(0);
        this.remark=ist.getRemark();   		
  	return this.SUCCESS;

	}	
	//显示划款明细
	public String showMerMingxi(){
		
		 this.tradeList2=  this.commonService.list(" select t,m,ic from TradeDetails t ,InternationalMerchant m , InternationalMerchantChannels mc, InternationalChannels ic where ic.id=mc.channelId and t.channels=mc.id and t.merchantno=m.id and t.batchno='"+batchNo+"'");
		return this.SUCCESS;
	}
	//余额查询
	public String showLeaveMony(){
		this.nobanlancesMoney=(Double) this.commonService.uniqueResult(" select sum(inf.tradeAmount) from  InternationalTradeinfo inf where substr(inf.tradeState,1,1)='1' and substr(inf.tradeState,10,1)='0' and substr(inf.tradeState,2,1)='0' and substr(inf.tradeState,3,1)='0' and substr(inf.tradeState,4,1) in (0,2)  and inf.merchantId='"+this.getMerchantBean().getMerchantId()+"' ");
        Double second=0d;
        second =(Double) this.commonService.uniqueResult(" select sum(inf.tradeAmount-inf.backCount) from  InternationalTradeinfo inf where substr(inf.tradeState,1,1)='1' and substr(inf.tradeState,10,1)='0' and substr(inf.tradeState,2,1)='2' and substr(inf.tradeState,3,1)='0' and substr(inf.tradeState,4,1) in (0,2)   and inf.merchantId='"+this.getMerchantBean().getMerchantId()+"' ");

		this.nosettlementmoney=(Double) this.commonService.uniqueResult(" select sum(inf.tradeAmount) from  InternationalTradeinfo inf where substr(inf.tradeState,1,1)='1' and substr(inf.tradeState,8,1)='0' and inf.merchantId='"+this.getMerchantBean().getMerchantId()+"' ");
        if(this.nobanlancesMoney!=null){
        	if(second!=null){
        		this.nobanlancesMoney=(this.nobanlancesMoney+second)*0.10;
        	}else{
        		this.nobanlancesMoney=(this.nobanlancesMoney)*0.10;
        	}
        }
	return this.SUCCESS;
	}
	public String toShowtrouble(){
		if (this.info == null) {
			this.info = new PageInfo();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" select t,f,m from InternationalTradeinfo t , InternationalCardholdersInfo f,InternationalMerchant m  where m.id=t.merchantId and substr(t.tradeState,7,1)='2' and f.tradeId=t.id and  t.merchantId='"+this.getMerchantBean().getMerchantId()+"'");
		if(!this.orderNo.equals("")){
			sb.append(" and t.orderNo like '%"+this.orderNo+"%'");
		}
		this.info.setPageSize(10);
		this.info = this.commonService
				.listQueryResultByHql(sb.toString(), info);
		return this.SUCCESS;
	}
	public InternationalMerchant getTradeinfo() {
		return tradeinfo;
	}
	public void setTradeinfo(InternationalMerchant tradeinfo) {
		this.tradeinfo = tradeinfo;
	}
	public List getTradeList() {
		return tradeList;
	}
	public Object[] getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(Object[] freezeId) {
		this.freezeId = freezeId;
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public List getListName() {
		return listName;
	}
	public void setListName(List listName) {
		this.listName = listName;
	}
	public Long getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(Long batchNo) {
		this.batchNo = batchNo;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	public List getTradeList2() {
		return tradeList2;
	}
	public void setTradeList2(List tradeList2) {
		this.tradeList2 = tradeList2;
	}
	public String getTotleMoney() {
		return totleMoney;
	}
	public void setTotleMoney(String totleMoney) {
		this.totleMoney = totleMoney;
	}
	public String getBanlancesMoney() {
		return banlancesMoney;
	}
	public void setBanlancesMoney(String banlancesMoney) {
		this.banlancesMoney = banlancesMoney;
	}
	public Double getNosettlementmoney() {
		return nosettlementmoney;
	}
	public void setNosettlementmoney(Double nosettlementmoney) {
		this.nosettlementmoney = nosettlementmoney;
	}
	public Double getNobanlancesMoney() {
		return nobanlancesMoney;
	}
	public void setNobanlancesMoney(Double nobanlancesMoney) {
		this.nobanlancesMoney = nobanlancesMoney;
	}
	public void setTradeList(List tradeList) {
		this.tradeList = tradeList;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Double getRmbmoney() {
		return rmbmoney;
	}
	public void setRmbmoney(Double rmbmoney) {
		this.rmbmoney = rmbmoney;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<Double> getForeignMoney() {
		return foreignMoney;
	}
	public void setForeignMoney(List<Double> foreignMoney) {
		this.foreignMoney = foreignMoney;
	}


	
	
}
