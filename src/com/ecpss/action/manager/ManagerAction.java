package com.ecpss.action.manager;

import com.ecpss.action.BaseAction;
import com.ecpss.model.user.User;

public class ManagerAction extends BaseAction {
	private User user;

	// 跳转到人员管理页面
	public String toManager() {
		return SUCCESS;
	}

	// 新增管理员
	public String toAddManager() {
		return SUCCESS;
	}

	// 新增管理员
	public String addManager() {
		this.commonService.save(user);
		return this.OPERATE_SUCCESS;
	}
	// 

}
