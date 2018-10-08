package com.ecpss.action.risk;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ecpss.action.BaseAction;
import com.ecpss.model.risk.InternationalHighRisklist;
import com.ecpss.model.risk.InternationalRiskItems;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.web.PageInfo;
/**
 * 风控项控制（包括国家，网站类型等）
 * @author jiahui
 *
 */
public class RiskItemsAction extends BaseAction {
	Logger logger = Logger.getLogger(RiskItemsAction.class.getName());
	private PageInfo info = new PageInfo();
	private InternationalRiskItems riskItemsInfo;
	private String qitemName;
	private String qitemType;
	private String qremark;
	private String riskIds;
	private String qtradeWeb;
	private String qmerchantNo;
	public String getRiskItems(){
		StringBuffer hql=new StringBuffer(" from InternationalRiskItems t where 1=1");
		if(StringUtils.isNotBlank(qitemName)){
			hql.append(" and t.itemName like '%"+qitemName+"%'");
		}
		if(StringUtils.isNotBlank(qmerchantNo)){
			hql.append(" and t.merchantNo='"+qmerchantNo+"'");
		}
		if(StringUtils.isNotBlank(qtradeWeb)){
			hql.append(" and t.tradeWeb like '%"+qtradeWeb+"%'");
		}
		if(StringUtils.isNotBlank(qitemType)){
			hql.append(" and t.itemType ='"+qitemType+"'");
		}
		if(StringUtils.isNotBlank(qremark)){
			hql.append(" and t.remark like '%"+qremark+"%'");
		}
		hql.append(" order by t.lastDate desc");
		info=commonService.listQueryResultByHql(hql.toString(), info);
		return SUCCESS;
	}
	public String addRiskItems(){
		InternationalMerchant mer=null;
		if(StringUtils.isNotBlank(riskItemsInfo.getMerchantNo())){
		mer=(InternationalMerchant) commonService.uniqueResult("from InternationalMerchant m where m.merno='"+riskItemsInfo.getMerchantNo().trim()+"'");
		}
		if(mer==null){
			riskItemsInfo.setMerchantNo("");
		}
		riskItemsInfo.setLastDate(new Date());
		riskItemsInfo.setLastMan(getUserBean().getUserName());
		commonService.save(riskItemsInfo);
		return getRiskItems();
	}
	public String delRiskItems(){
		if(StringUtils.isNotBlank(riskIds)){
			String[] riskId=riskIds.split(",");
			for(int i=0;i<riskId.length;i++){
				InternationalRiskItems ri=(InternationalRiskItems) commonService.load(InternationalRiskItems.class, new Long(riskId[i]));
				logger.info(getUserBean().getUserName()+"对"+ri.getItemName()+"进行删除操作！");
				commonService.delete(ri);
			}
		}
		return getRiskItems();
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public InternationalRiskItems getRiskItemsInfo() {
		return riskItemsInfo;
	}
	public void setRiskItemsInfo(InternationalRiskItems riskItemsInfo) {
		this.riskItemsInfo = riskItemsInfo;
	}
	public String getQitemName() {
		return qitemName;
	}
	public void setQitemName(String qitemName) {
		this.qitemName = qitemName;
	}
	public String getQitemType() {
		return qitemType;
	}
	public void setQitemType(String qitemType) {
		this.qitemType = qitemType;
	}
	public String getQremark() {
		return qremark;
	}
	public void setQremark(String qremark) {
		this.qremark = qremark;
	}
	public String getRiskIds() {
		return riskIds;
	}
	public void setRiskIds(String riskIds) {
		this.riskIds = riskIds;
	}
	public String getQtradeWeb() {
		return qtradeWeb;
	}
	public void setQtradeWeb(String qtradeWeb) {
		this.qtradeWeb = qtradeWeb;
	}
	public String getQmerchantNo() {
		return qmerchantNo;
	}
	public void setQmerchantNo(String qmerchantNo) {
		this.qmerchantNo = qmerchantNo;
	}
	
}
