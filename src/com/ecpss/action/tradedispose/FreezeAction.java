package com.ecpss.action.tradedispose;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.HuakuanedException;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.common.CommonService;
import com.ecpss.web.PageInfo;
/****************zhengmianwei**************/
public class FreezeAction extends BaseAction{
	public List list = new ArrayList();
	private InternationalTradeinfo tradeinfo = new InternationalTradeinfo();
	private InternationalMerchant merchant = new InternationalMerchant();
	private String hql;
	private StringBuffer sb;
	private PageInfo info = new PageInfo();
	private String startTime;
	private String endTime;
	private Object[] disposeId;  
	private String flag;
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	/**
	 * 查询已冻结交易  (成功，未退款，未拒付,已冻结)
	 */
	public String findFreeze(){
		try{
			sb = new StringBuffer("FROM InternationalTradeinfo t, InternationalMerchant m WHERE t.merchantId = m.id AND substr(t.tradeState,1,1)='1'" +
			" AND substr(t.tradeState,2,1)='0' AND substr(t.tradeState,3,1)='0' AND substr(t.tradeState,4,1)='1'");
			if(merchant.getMerno()!=null){
				sb.append(" AND m.merno="+merchant.getMerno()+"");
			}
			if(tradeinfo.getOrderNo()!=null && !tradeinfo.getOrderNo().equals("")){
				sb.append(" AND t.orderNo='"+tradeinfo.getOrderNo().trim()+"'");
			}
			
			/*if(startTime!=null && !startTime.equals("")){
				sb.append(" AND t.tradeTime>=to_date('"+startTime+"','yyyy-mm-dd')");
			}
			if(endTime!=null && !endTime.equals("")){
				sb.append(" AND t.tradeTime<=to_date('"+endTime+"','yyyy-mm-dd')");
			}*/
			hql = sb.toString();
			info = commonService.listQueryResultByHql(hql, info);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转审核解冻页面失败!";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 审核解冻交易
	 */
	public String auditingThaw(){
		try{
			StringBuffer sb1 = new StringBuffer();
			sb = new StringBuffer();
			for(int i=0; i<disposeId.length; i++){
				sb.append(disposeId[i]+",");
			}
			
			String value = sb.toString();
			System.out.println("value-----------"+value.subSequence(0, value.length()-1));
			sb1.append(value.subSequence(0, value.length()-1));
		
			hql = "update international_tradeinfo a set " +
					"a.tradestate=substr(a.tradestate,1,3)||'3'||substr(a.tradestate,5, length(a.tradestate)-4)" +
					" where a.id in("+sb1.toString()+")";
			commonService.deleteBySql(hql);
			System.out.println("hql------------"+hql);
			//this.findFreeze();
			this.flag = "1";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.flag = "2";
			return SUCCESS;
			
		}
	}
	/**
	 * 查询已审核解冻交易
	 */
	public String findThawAuditing(){
		try{
			sb = new StringBuffer("FROM InternationalTradeinfo t, InternationalMerchant m WHERE t.merchantId = m.id AND substr(t.tradeState,1,1)='1'" +
			" AND substr(t.tradeState,2,1)='0' AND substr(t.tradeState,3,1)='0' AND substr(t.tradeState,4,1)='3'");
		
			if(merchant.getMerno()!=null){
				sb.append(" AND m.merno="+merchant.getMerno()+"");
			}
			if(tradeinfo.getOrderNo()!=null && !tradeinfo.getOrderNo().equals("")){
				sb.append(" AND t.orderNo='"+tradeinfo.getOrderNo().trim()+"'");
			}
			/*if(startTime!=null && !startTime.equals("")){
				sb.append(" AND t.tradeTime>=to_date('"+startTime+"','yyyy-mm-dd')");
			}
			if(endTime!=null && !endTime.equals("")){
				sb.append(" AND t.tradeTime<=to_date('"+endTime+"','yyyy-mm-dd')");
			}*/
			hql = sb.toString();
			info.setPageSize(20);
			info = commonService.listQueryResultByHql(hql, info);
			return SUCCESS;
		}catch(Exception e){
			this.messageAction="查询冻结数据失败!";
			return this.OPERATE_SUCCESS;
		}
	}
	/**
	 * 查询未冻结的数据
	 */
	//成功，未退款，未拒付，未冻结
	public String findNoFreeze(){
		try{
			sb = new StringBuffer("FROM InternationalTradeinfo t, InternationalMerchant m WHERE t.merchantId = m.id AND substr(t.tradeState,1,1)='1'" +
			" AND substr(t.tradeState,2,1)='0' AND substr(t.tradeState,3,1)='0' AND substr(t.tradeState,4,1)='0'");

			if(merchant.getMerno()!=null ){
				sb.append(" AND m.merno="+merchant.getMerno()+"");
			}
			if(tradeinfo.getOrderNo()!=null && !tradeinfo.getOrderNo().equals("")){
				sb.append(" AND t.orderNo='"+tradeinfo.getOrderNo().trim()+"'");
			}
			/*if(startTime!=null && !startTime.equals("")){
				sb.append(" AND t.tradeTime>=to_date('"+startTime+"','yyyy-mm-dd')");
			}
			if(endTime!=null && !endTime.equals("")){
				sb.append(" AND t.tradeTime<=to_date('"+endTime+"','yyyy-mm-dd')");
			}*/
		
			
			hql = sb.toString();
			//list = commonService.list(hql);
			info.setPageSize(20);
			info = commonService.listQueryResultByHql(hql, info);
			//System.out.println("test-----------------");
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="查询未冻结数据失败!";
			return this.OPERATE_SUCCESS;
		}
	}
	
	
	/**
	 * 冻结
	 */
	public String freeze(){
		try{
			StringBuffer sb1 = new StringBuffer();
			sb = new StringBuffer();
			for(int i=0; i<disposeId.length; i++){
				sb.append(disposeId[i]+",");
				hql = "FROM InternationalTradeinfo t WHERE substr(t.tradeState,8,1)='1' AND t.id="+disposeId[i]+"";
				InternationalTradeinfo trade = (InternationalTradeinfo)commonService.uniqueResult(hql);
				if(trade!=null){
					HuakuanedException he = new HuakuanedException();
					he.setTradeId(trade.getId());
					he.setIsAudit("0");
					he.setTradeType("6");
					commonService.save(he);
				}
			}
			
			String value = sb.toString();
			//System.out.println("value-----------"+value.subSequence(0, value.length()-1));
			sb1.append(value.subSequence(0, value.length()-1));
		
			hql = "update international_tradeinfo a set " +
					"a.tradestate=substr(a.tradestate,1,3)||'1'||substr(a.tradestate,5, length(a.tradestate)-4)" +
					" where a.id in("+sb1.toString()+")";
			//System.out.println("hql------------"+hql);
			
			
			commonService.deleteBySql(hql);
			this.messageAction="冻结成功!";
			//this.findNoFreeze();
			this.flag = "1";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction="冻结失败!";
			this.flag = "2";
			return SUCCESS;
		}
	}
	
	/**
	 * 解冻
	 */
	public String thaw(){
		try{
			StringBuffer sb1 = new StringBuffer();
			sb = new StringBuffer();
			for(int i=0; i<disposeId.length; i++){
				sb.append(disposeId[i]+",");
				hql = "FROM InternationalTradeinfo t WHERE substr(t.tradeState,8,1)='1' AND t.id="+disposeId[i]+"";
				InternationalTradeinfo trade = (InternationalTradeinfo)commonService.uniqueResult(hql);
				if(trade!=null){
					HuakuanedException he = new HuakuanedException();
					he.setTradeId(trade.getId());
					he.setIsAudit("0");
					he.setTradeType("7");
					commonService.save(he);
				}
			}
			
			String value = sb.toString();
			sb1.append(value.subSequence(0, value.length()-1));
		
			hql = "update international_tradeinfo a set " +
					"a.tradestate=substr(a.tradestate,1,3)||'2'||substr(a.tradestate,5, length(a.tradestate)-4)" +
					" where a.id in("+sb1.toString()+")";
			//System.out.println("hql------------"+hql);
			commonService.deleteBySql(hql);
			//this.findThawAuditing();
			this.flag = "1";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.flag = "2";
			return SUCCESS;
		}
	}
	/**
	 * @return the li
	 */
	
	/**
	 * @return the currencyService
	 */
	/**
	 * @return the commonService
	 */
	public CommonService getCommonService() {
		return commonService;
	}
	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * @return the tradeinfo
	 */
	public InternationalTradeinfo getTradeinfo() {
		return tradeinfo;
	}
	/**
	 * @param tradeinfo the tradeinfo to set
	 */
	public void setTradeinfo(InternationalTradeinfo tradeinfo) {
		this.tradeinfo = tradeinfo;
	}
	/**
	 * @return the info
	 */
	public PageInfo getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(PageInfo info) {
		this.info = info;
	}

	/**
	 * @return the disposeId
	 */
	public Object[] getDisposeId() {
		return disposeId;
	}
	/**
	 * @param disposeId the disposeId to set
	 */
	public void setDisposeId(Object[] disposeId) {
		this.disposeId = disposeId;
	}
	/**
	 * @return the merchant
	 */
	public InternationalMerchant getMerchant() {
		return merchant;
	}
	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
