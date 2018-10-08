package com.ecpss.action.permissions;

import org.apache.struts2.ServletActionContext;

import com.ecpss.action.BaseAction;

public class LogoutAction extends BaseAction {
	
	/**
	 * 用户退出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
		
	}

	
}
