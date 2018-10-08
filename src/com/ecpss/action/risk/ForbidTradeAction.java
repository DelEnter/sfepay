package com.ecpss.action.risk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.payment.InternationalCardholdersInfo;
import com.ecpss.model.risk.InternationalBacklist;
import com.ecpss.model.risk.InternationalRisklist;
import com.ecpss.service.common.CommonService;
import com.ecpss.web.PageInfo;

public class ForbidTradeAction extends BaseAction{
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	private InternationalBacklist back = new InternationalBacklist();
	private InternationalRisklist risk = new InternationalRisklist();
	private InternationalCardholdersInfo card = new InternationalCardholdersInfo();
	private String hql;
	private StringBuffer sb;
	private String startTime;
	private String endTime;
	private List list = new ArrayList();
	private PageInfo info = new PageInfo();
	/**
	 * 跳转到禁止交易页面
	 * @return
	 */
	public String toForbidTrade(){
		try{
			sb = new StringBuffer("SELECT ");
			if((card.getIp()!=null && card.getIp().equals("true")) || (risk.getIp()!=null && !risk.getIp().equals(""))){
				sb.append("card.ip, ");
			}else{
				sb.append("'',");
			} 
			if((card.getCardNo()!=null && card.getCardNo().equals("true")) || (risk.getCardno()!=null && !risk.getCardno().equals(""))){
				sb.append("card.cardNo, ");
			}else{
				sb.append("'',");
			} 
			if((card.getEmail()!=null && card.getEmail().equals("true")) || (risk.getEmail()!=null && !risk.getEmail().equals(""))){
				sb.append("card.email, ");
			}else{
				sb.append("'',");
			} 
			if((card.getCookie()!=null && card.getCookie().equals("true")) || (risk.getCookie()!=null && !risk.getCookie().equals(""))){
				sb.append("card.cookie, ");
			}else{
				sb.append("'',");
			} 
			
			sb.append(" count(*) FROM InternationalCardholdersInfo card WHERE 1=1");
			/*if(startTime!=null && !startTime.equals("")){
				sb.append(" and t.tradetime>=to_date('"+startTime+"','yyyy-mm-dd')");
			}
			if(endTime!=null && !endTime.equals("")){
				sb.append(" and t.tradetime<to_date('"+endTime+ " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
			}	*/
			if(risk.getIp()!=null && !risk.getIp().equals("")){
				sb.append(" AND card.ip='"+risk.getIp().trim()+"'");
			}
			if(risk.getCardno()!=null && !risk.getCardno().equals("")){
				sb.append(" AND card.cardNo='"+risk.getCardno().trim()+"'");
			}
			if(risk.getCookie()!=null && !risk.getCookie().equals("")){
				sb.append(" AND card.cookie='"+risk.getCookie().trim()+"'");
			}
			if(risk.getEmail()!=null && !risk.getEmail().equals("")){
				sb.append(" AND card.email='"+risk.getEmail().trim()+"'");	
			}
			sb.append("GROUP BY ");
			if((card.getIp()!=null && card.getIp().equals("true")) || (risk.getIp()!=null && !risk.getIp().equals(""))){
				sb.append("card.ip, ");
			}else{
				sb.append("'',");
			} 
				
			if((card.getCardNo()!=null && card.getCardNo().equals("true")) || (risk.getCardno()!=null && !risk.getCardno().equals(""))){
				sb.append("card.cardNo, ");
			}else{
				sb.append("'',");
			} 
			if((card.getEmail()!=null && card.getEmail().equals("true")) || (risk.getEmail()!=null && !risk.getEmail().equals(""))){
				sb.append("card.email, ");
			}else{
				sb.append("'',");
			} 
			if((card.getCookie()!=null && card.getCookie().equals("true")) || (risk.getCookie()!=null && !risk.getCookie().equals(""))){
				sb.append("card.cookie");
			}else{
				sb.append("''");
			} 
			sb.append(" having count(*)>1");
			hql = sb.toString();
			info = commonService.listQueryResultByHqlGroupBy(hql, info);
			//info.setPageSize(20);
			//commonService.list(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到禁止交易页面失败";
			return this.OPERATE_ERROR;
		}		
	}
	
	/**
	 * 禁止黑卡交易
	 */
	public String forbidBackTrade(){
		try{
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "禁止黑卡交易";
			return this.OPERATE_ERROR;
		}
	}
	/** 
	 * 禁止高风险卡交易
	 */
	public String forbidRiskTrade(){
		try{
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "禁止黑卡交易";
			return this.OPERATE_ERROR;
		}
	}
	
	/**
	 * 明细禁止交易
	 */
	public String forbidTradeList(){
		try{
			sb = new StringBuffer("FROM InternationalCardholdersInfo card, InternationalTradeinfo trade," +
					"InternationalMerchant merchant, InternationalMoneykindname money, InternationalChannels chann WHERE trade.id=card.tradeId " +
					"AND merchant.id=trade.merchantId AND trade.moneyType=money.moneykindno AND chann.id=trade.tradeChannel");
			if(card.getIp()!=null && !card.getIp().equals("")){
				sb.append(" AND card.ip='"+card.getIp()+"'");
			}
			if(card.getCardNo()!=null && !card.getCardNo().equals("")){
				sb.append(" AND card.cardNo='"+card.getCardNo()+"'");
			}
			if(card.getEmail()!=null && !card.getEmail().equals("")){
				sb.append(" AND card.email='"+card.getEmail()+"'");
			}
			if(card.getCookie()!=null && !card.getCookie().equals("")){
				sb.append(" AND card.cookie='"+card.getCookie()+"'");
			}
			hql = sb.toString();
			list = commonService.list(hql);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "明细禁止交易失败";
			return this.OPERATE_ERROR;
		}
	}
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public InternationalBacklist getBack() {
		return back;
	}
	public void setBack(InternationalBacklist back) {
		this.back = back;
	}
	public InternationalRisklist getRisk() {
		return risk;
	}
	public void setRisk(InternationalRisklist risk) {
		this.risk = risk;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public InternationalCardholdersInfo getCard() {
		return card;
	}

	public void setCard(InternationalCardholdersInfo card) {
		this.card = card;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}
}
