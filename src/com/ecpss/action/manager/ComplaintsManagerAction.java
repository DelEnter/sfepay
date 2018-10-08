package com.ecpss.action.manager;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;
import com.ecpss.model.complaint.InternationalComplaintsContent;
import com.ecpss.model.complaint.InternationalComplaintsManager;
import com.ecpss.model.payment.InternationalTradeinfo;
import com.ecpss.web.PageInfo;

public class ComplaintsManagerAction extends BaseAction{
	private PageInfo info = new PageInfo();
	
	private InternationalComplaintsManager cm;
	private String email;
	private String content;
	private String message;
	private Long complainId;
	private Long merchantno;
	private String orderNo;
	private List<Object[]> comList;
	private String type;
	
	public String showmanager() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select cm from InternationalComplaintsManager cm where 1=1 ");
		if(merchantno!=null){
			sb.append(" and cm.merchantNo="+merchantno);
		}
		if(StringUtils.isNotBlank(orderNo)){
			sb.append(" and cm.orderNo='"+orderNo.trim()+"' ");
		}
		if(StringUtils.isNotBlank(type)){
			sb.append(" and cm.cmType='"+type+"' ");
		}
		sb.append(" order by cm.processingResults,cm.lastDate desc");
		info = commonService.listQueryResultByHql(sb.toString(), info);
		return SUCCESS;
	}
	/**
	 * 跳转到新增客人投诉内容页面
	 * @return
	 */
	public String showAddCom(){
		return SUCCESS;
	}
	
	/**
	 * 新增客户投诉意见
	 * @return
	 * @throws Exception 
	 */
	public String addComplains() throws Exception{
		String hql="select ti.id from InternationalTradeinfo ti,InternationalMerchant m " +
				"where ti.orderNo='"+cm.getOrderNo().trim()+"' " +
				"and ti.merchantOrderNo='"+cm.getMerchantOrderNo()+"' " +
								"and ti.merchantId=m.id " +
								"and m.merno="+cm.getMerchantNo();
		List<InternationalTradeinfo> tilist = commonService.list(hql);
		if(tilist.size()>0){
			String hql1="select ti.id from InternationalComplaintsManager ti where ti.orderNo='"+cm.getOrderNo().trim()+"'";
			List<InternationalComplaintsManager> cmList = commonService.list(hql1);
			if(cmList.size()>0){
				messageAction = "Order already exists";
			}else{
				cm.setProcessingResults("0");
				cm.setCmEmail(email);
				cm.setLastDate(new Date());
				cm.setLastMan(getUserBean().getUserName());
				commonService.save(cm);
				InternationalComplaintsContent cc = new InternationalComplaintsContent();
				cc.setNewdate(new Date());
				cc.setContent(content);
				cc.setComplaintId(cm.getId());
				cc.setContenttype("1");
				cc.setLastDate(new Date());
				cc.setLastMan(getUserBean().getUserName());
				commonService.save(cc);
				
				this.messageAction="Successfully added";		
			}
			return SUCCESS;
		}else{
			messageAction = "The order does not exist";
			return SUCCESS;
		}
	}
	/**
	 * 查看投诉详情
	 * @return
	 */
	public String viewDetails(){
		String hql="select cm,cc from InternationalComplaintsManager cm,InternationalComplaintsContent cc" +
				" where cm.id = cc.complaintId " +
				"and cm.id="+complainId+
				" order by cc.newdate ";
		comList = commonService.list(hql);
		return SUCCESS;
	}
	/**
	 * 详情页面添加客人投诉内容
	 * @return
	 */
	public String addViewContent(){
		InternationalComplaintsContent cc = new InternationalComplaintsContent();
		cc.setComplaintId(complainId);
		cc.setContent(content);
		cc.setContenttype("1");
		cc.setNewdate(new Date());
		cc.setLastDate(new Date());
		cc.setLastMan(getUserBean().getUserName());
		commonService.save(cc);
		InternationalComplaintsManager c = (InternationalComplaintsManager) commonService.load(InternationalComplaintsManager.class, complainId);
		c.setProcessingResults("0");
		c.setLastDate(new Date());
		c.setLastMan(getUserBean().getUserName());
		commonService.update(c);
		this.messageAction="添加成功";	
		return SUCCESS;
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
	public InternationalComplaintsManager getCm() {
		return cm;
	}
	public void setCm(InternationalComplaintsManager cm) {
		this.cm = cm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Long getMerchantno() {
		return merchantno;
	}
	public void setMerchantno(Long merchantno) {
		this.merchantno = merchantno;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
