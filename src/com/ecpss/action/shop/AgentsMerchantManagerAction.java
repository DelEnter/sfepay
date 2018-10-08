package com.ecpss.action.shop;

import java.util.Date;
import java.util.List;

import com.ecpss.action.BaseAction;
import com.ecpss.model.shop.InternationalAgentsMerchant;
import com.ecpss.model.shop.InternationalMerchant;

public class AgentsMerchantManagerAction extends BaseAction {

	private Long proMerno; // 代理商户号
	private Long agentsMerNo; // 查询时候使用
	private Long merno; // 子商户号
	private List agentsMerList;
	private String pwd; // 密码
	private Long agentId; // id
	private List<Long> mernolist;
	
	private List<String> pwdList;

	/**
	 * 进入代理商户管理页面
	 * 
	 * @return
	 */
	public String toAgentsMerchant() {
		mernolist = this.commonService
				.list("select distinct iam.agentsMerchantNo from InternationalAgentsMerchant iam");
		System.out.println(mernolist + "************************************");
		StringBuffer hql = new StringBuffer();
		hql
				.append("select iam,m from InternationalAgentsMerchant iam,InternationalMerchant m where iam.merchantId=m.id ");
		if (agentsMerNo != null && !agentsMerNo.equals("")) {
			hql.append("and iam.agentsMerchantNo = " + agentsMerNo);
		}
		agentsMerList = this.commonService.list(hql.toString());
		return SUCCESS;
	}

	/**
	 * 添加一个新的代理商户号或者给这个代理商户好新增一个字商户号
	 * 
	 * @return
	 */
	public String addAgentsMerchant() {
		if(merno == null || "".equals(merno)) {
			messageAction="请输入商户号";
			return OPERATE_SUCCESS;
		}
//		// 1. 根据子商户号查询商户表是否存在
		InternationalMerchant merchant = (InternationalMerchant) this.commonService
				.uniqueResult("select im from InternationalMerchant im where im.merno= '"
						+ merno + "' ");
		if (merchant == null || proMerno == null) {
			this.messageAction = "子商户号不存在，或者你输入的代理商户号错误。";
			getLoaction().setReload(true);
			return OPERATE_SUCCESS;
		}
		// 一个商户号只能给一个商户代理使用
		Long count = (Long) this.commonService
				.uniqueResult("select count(iam.id) from InternationalAgentsMerchant iam where iam.merchantId = '"
						+ merchant.getId() + "' ");
		if (count == 1 && count != 0) {
			this.messageAction = "该商户已经存在,一个商户号只能给一个商户代理使用";
			getLoaction().setReload(true);
			return OPERATE_SUCCESS;
		} 
		// 创建商户代理号实体对象
		InternationalAgentsMerchant iam = new InternationalAgentsMerchant();
		iam.setMerchantId(merchant.getId());
		iam.setAgentsMerchantNo(proMerno);
		iam.setLastDate(new Date());
		iam.setLastMan(getUserBean().getUserName());
		iam.setPassword(pwd);
		this.commonService.save(iam);
		messageAction="添加成功";
		this.getLoaction().setReload(true);
		return OPERATE_SUCCESS;

	}

	/**
	 * 删除代理商户
	 * 
	 * @return
	 */
	public String deleteAgentMerchant() {
		mernolist = this.commonService
				.list("select distinct iam.agentsMerchantNo from InternationalAgentsMerchant iam");
		InternationalAgentsMerchant iam = (InternationalAgentsMerchant) this.commonService
				.load(InternationalAgentsMerchant.class, agentId);
		if (iam != null) {
			this.commonService.delete(iam);
			this.messageAction="删除成功";
		}
		return OPERATE_SUCCESS;
	}

	public Long getProMerno() {
		return proMerno;
	}

	public void setProMerno(Long proMerno) {
		this.proMerno = proMerno;
	}

	public Long getAgentsMerNo() {
		return agentsMerNo;
	}

	public void setAgentsMerNo(Long agentsMerNo) {
		this.agentsMerNo = agentsMerNo;
	}

	public Long getMerno() {
		return merno;
	}

	public void setMerno(Long merno) {
		this.merno = merno;
	}

	public List getAgentsMerList() {
		return agentsMerList;
	}

	public void setAgentsMerList(List agentsMerList) {
		this.agentsMerList = agentsMerList;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public List<Long> getMernolist() {
		return mernolist;
	}

	public void setMernolist(List<Long> mernolist) {
		this.mernolist = mernolist;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
