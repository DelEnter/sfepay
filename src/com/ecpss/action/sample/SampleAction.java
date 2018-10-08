package com.ecpss.action.sample;

import java.util.List;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.user.User;
import com.ecpss.service.sample.SampleService;
import com.ecpss.web.PageInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author 
 */
@ParentPackage("template")
@Results( { @Result(name = "success", value = "/pages/login.jsp")})
public class SampleAction extends BaseAction {
	@Autowired
	@Qualifier("sampleService")
	private SampleService sampleService;
	private User loginUser;
	private List<User> userList;
	
	private PageInfo info;
	
	public String doDefault(){
		if(info==null){
			info = new PageInfo();
		}
		//loginUser = (User) commonService.load(User.class, 1L);
		String hql="from User";
		info.setPageSize(2);
		info = commonService.listQueryResultByHql(hql, info);
		return "success";
	}
	public String login(){
		ActionContext.getContext().put("loginName", loginUser.getUserName());
		commonService.save(loginUser);
		String hql="from User";
		userList = commonService.list(hql);
		
		//userList = sampleService.getUserList();
		return "success";
	}
	/**
	 * @return the loginUser
	 */
	public User getLoginUser() {
		return loginUser;
	}
	/**
	 * @param loginUser the loginUser to set
	 */
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public PageInfo getInfo() {
		return info;
	}
	public void setInfo(PageInfo info) {
		this.info = info;
	}
}
