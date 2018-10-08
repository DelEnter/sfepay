package com.ecpss.action.risk;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.risk.InternationalBacklist;
import com.ecpss.model.risk.InternationalRisklist;
import com.ecpss.service.common.CommonService;
import com.ecpss.web.PageInfo;

public class RiskTradeAction extends BaseAction{
	@Autowired
	@Qualifier("commonService")
	private CommonService commonService;
	private InternationalBacklist back = new InternationalBacklist();
	private InternationalRisklist risk = new InternationalRisklist();
	private String flag;
	private PageInfo info = new PageInfo();
	private String yesOrNo;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * 跳转到问题单处理页面
	 */
	public String toBackCardMake(){
		try{
			String hql="select t,rownum from InternationalBacklist t where 1=1 ";
			if(StringUtils.isNotEmpty(yesOrNo)){
				if(StringUtils.isNotBlank(back.getCardno())){
					hql=hql+" and t.cardno like '%"+back.getCardno()+"%'";
				}
				if(StringUtils.isNotBlank(back.getEmail())){
					hql=hql+" and t.email like '%"+back.getEmail()+"%'";
				}
				if(StringUtils.isNotBlank(back.getIp())){
					hql=hql+" and t.ip like '%"+back.getIp()+"%'";
				}
				if(StringUtils.isNotBlank(back.getCookie())){
					hql=hql+" and t.cookie like '%"+back.getCookie()+"%'";
				}
				if(StringUtils.isNotBlank(back.getRemark())){
					hql=hql+" and t.remark like '%"+back.getRemark()+"%'";
				}
			}else{
				if(StringUtils.isNotBlank(back.getCardno())){
					hql=hql+" and t.cardno ='"+back.getCardno()+"'";
				}
				if(StringUtils.isNotBlank(back.getEmail())){
					hql=hql+" and t.email ='"+back.getEmail()+"'";
				}
				if(StringUtils.isNotBlank(back.getIp())){
					hql=hql+" and t.ip ='"+back.getIp()+"'";
				}
				if(StringUtils.isNotBlank(back.getCookie())){
					hql=hql+" and t.cookie ='"+back.getCookie()+"'";
				}
				if(StringUtils.isNotBlank(back.getRemark())){
					hql=hql+" and t.remark ='"+back.getRemark()+"'";
				}
			}
			hql=hql+" order by t.ip desc,rownum asc ";
			info=commonService.listQueryResultByHql(hql, info);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到问题单处理页面失败";
			return this.OPERATE_ERROR;
		}
	}
	
	/**
	 * 列为黑卡
	 */
	public String backCardMake(){
		try{
			commonService.save(back);
			flag = "1";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "系统出现问题!";
			return this.OPERATE_ERROR;
		}
	}
	//删除黑卡
	public String delBackCardMake(){
		try{
			commonService.delete(InternationalBacklist.class, back.getId());;
			flag = "2";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "系统出现问题!";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 跳转到列为高风险卡页面
	 */
	public String toRiskCardMake(){
		try{
			String hql="from InternationalRisklist where 1=1 ";
			if(StringUtils.isNotBlank(risk.getCardno())){
				hql=hql+" and cardno like '%"+risk.getCardno()+"%'";
			}
			if(StringUtils.isNotBlank(risk.getEmail())){
				hql=hql+" and email like '%"+risk.getEmail()+"%'";
			}
			if(StringUtils.isNotBlank(risk.getIp())){
				hql=hql+" and ip like '%"+risk.getIp()+"%'";
			}
			if(StringUtils.isNotBlank(risk.getCookie())){
				hql=hql+" and cookie like '%"+risk.getCookie()+"%'";
			}
			if(StringUtils.isNotBlank(risk.getRemark())){
				hql=hql+" and remark like '%"+risk.getRemark()+"%'";
			}
			if(StringUtils.isNotBlank(risk.getTradeUrl())){
				hql=hql+" and tradeUrl like '%"+risk.getTradeUrl()+"%'";
			}
			hql=hql+" order by ip desc ";
			info=commonService.listQueryResultByHql(hql, info);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "跳转到列为高风险卡页面失败";
			return this.OPERATE_ERROR;
		}
	}
	/**
	 * 列为高风险卡
	 */
	public String riskCardMake(){
		try{
			commonService.save(risk);
			flag = "1";
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.messageAction = "列为高风险卡失败";
			return this.OPERATE_ERROR;
		}
	}
	//删除高风险卡
		public String delRiskCardMake(){
			try{
				commonService.delete(InternationalRisklist.class, risk.getId());;
				flag = "2";
				return SUCCESS;
			}catch(Exception e){
				e.printStackTrace();
				this.messageAction = "系统出现问题!";
				return this.OPERATE_ERROR;
			}
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

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public String getYesOrNo() {
		return yesOrNo;
	}

	public void setYesOrNo(String yesOrNo) {
		this.yesOrNo = yesOrNo;
	}
}
