package com.ecpss.action.merchant;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.complaint.InternationalComplaintsContent;
import com.ecpss.model.complaint.InternationalComplaintsManager;
import com.ecpss.web.PageInfo;

public class MerchantComplaintsAction extends BaseAction{

	private PageInfo info = new PageInfo();
	private String merchantOrderNo;
	private String orderNo;
	private Long complainId;
	private List<Object[]> comList;
	private String content;
	
	/**
	 * 商户后台投诉管理查询
	 * @return
	 * @throws Exception
	 */
	public String merchantComplaintsQuery() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select cm from InternationalComplaintsManager cm where 1=1 " +
				"and cm.merchantNo="+getMerchantBean().getMerChantNo());
		if(StringUtils.isNotBlank(merchantOrderNo)){
			sb.append(" and cm.merchantOrderNo='"+merchantOrderNo.trim()+"' ");
		}
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and cm.orderNo='"+orderNo.trim()+"' ");
		}
		sb.append(" order by cm.processingResults,cm.lastDate desc");
		info = commonService.listQueryResultByHql(sb.toString(), info);
		return SUCCESS;
	}

	
	/**
	 * 查看
	 * @return
	 */
	public String viewMerchantComDetails(){
		String hql="select cm,cc from InternationalComplaintsManager cm,InternationalComplaintsContent cc" +
				" where cm.id = cc.complaintId " +
				"and cm.id="+complainId+
				" order by cc.newdate ";
		comList = commonService.list(hql);
		return SUCCESS;
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String meraddViewContent(){
		InternationalComplaintsContent cc = new InternationalComplaintsContent();
		cc.setComplaintId(complainId);
		cc.setContent(content);
		cc.setContenttype("2");
		cc.setNewdate(new Date());
		cc.setLastDate(new Date());
		cc.setLastMan(getMerchantBean().getMerchantUserName());
		commonService.save(cc);
		InternationalComplaintsManager c = (InternationalComplaintsManager) commonService.load(InternationalComplaintsManager.class, complainId);
		c.setProcessingResults("1");
		c.setLastDate(new Date());
		c.setLastMan(getMerchantBean().getMerchantUserName());
		commonService.update(c);
		this.messageAction="添加成功";	
		return viewMerchantComDetails();
	}
	
	public PageInfo getInfo() {
		return info;
	}

	public void setInfo(PageInfo info) {
		this.info = info;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public Long getComplainId() {
		return complainId;
	}


	public void setComplainId(Long complainId) {
		this.complainId = complainId;
	}


	public List<Object[]> getComList() {
		return comList;
	}


	public void setComList(List<Object[]> comList) {
		this.comList = comList;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	
}
