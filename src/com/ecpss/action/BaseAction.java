package com.ecpss.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.ecpss.action.common.MethodDispatchAction;
import com.ecpss.service.common.CommonService;
import com.ecpss.util.AES;
import com.ecpss.util.LoactionBean;
import com.ecpss.util.StateUtils;
import com.ecpss.vo.MerchantBean;
import com.ecpss.vo.UserBean;
import com.opensymphony.xwork2.ActionContext;

public class BaseAction extends MethodDispatchAction{
    public LoactionBean loaction;
	@Autowired
	@Qualifier("commonService")
	protected CommonService commonService; 
	
	private StateUtils states = new StateUtils();
	protected String http_server = "https://www.sfepay.com";
	public String query="";
	
	public UserBean getUserBean(){
		
		return (UserBean)ActionContext.getContext().getSession().get("user");
	}	
	public MerchantBean getMerchantBean(){
		
		return (MerchantBean)ActionContext.getContext().getSession().get("merchantBean");
	}	
	
	public String messageAction;
	public String messageEnAction;
	/**
	 * 返回到成功页面
	 */
    public static final String OPERATE_SUCCESS ="operatesuccess"; 
    /**
     * 返回到错误页面
     */
    public static final String OPERATE_ERROR ="operateerror"; 

 /**
  * 窗口方式到成功页面
  * 
  */   
    public static final String WINDOWOPERATE_SUCCESS="windowoperatesuccess";
  /**
   * 窗口方式到错误页面
   * 
   */  
    public static final String WINDOWOPERATE_ERROR="windowoperateerror";
	public String getMessageAction() {
		return messageAction;
	}
	public void setMessageAction(String messageAction) {
		this.messageAction = messageAction;
	}
	public LoactionBean getLoaction() {
		if(this.loaction==null){
			this.loaction=new LoactionBean();
		}
		return loaction;
	}
	public void setLoaction(LoactionBean loaction) {
		this.loaction = loaction;
	}
	public StateUtils getStates() {
		return states;
	}
	public void setStates(StateUtils states) {
		this.states = states;
	}
	public String getMessageEnAction() {
		return messageEnAction;
	}
	public void setMessageEnAction(String messageEnAction) {
		this.messageEnAction = messageEnAction;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	/*
	 * 获取加密卡号
	 *
	 */
	public String  getCarNo(String message){
		
		if(message.length()>16){
			return AES.getCarNo(message);
		}else{
			return message;
		}
		
		
	}
	/*
	 * 将卡号加密
	 *
	 */
	public String  setCarNo(String message){
		
		return AES.setCarNo(message);
		
		
	}
	public String getHttp_server() {
		return http_server;
	}
	public void setHttp_server(String http_server) {
		this.http_server = http_server;
	}
}
