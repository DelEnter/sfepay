package com.ecpss.action.channel;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.permissions.Resource;
import com.ecpss.model.permissions.Role;
import com.ecpss.service.iservice.ChannelService;

public class ChannelAction extends BaseAction {
	private List<Role> rolelist;
	public List<Resource> resourcelist;
	@Autowired
	@Qualifier("channelService")
	private ChannelService channelService;
	private Role role;
	public List<InternationalChannels> channelList;
	private InternationalChannels channel;
	private Long channelId;
	private String channelName;

	private String merno; // 商户号

	/**
	 * 通道列表
	 * 
	 * @return
	 */
	public String toChannel() {
		channelList = channelService.getChannelList();
		this.getLoaction().setReload(true);
		return SUCCESS;
	}

	/**
	 * 跳转添加通道
	 * 
	 * @return
	 */
	public String toAddChannel() {

		return SUCCESS;
	}

	/**
	 * 新增通道
	 * 
	 * @return
	 */
	public String addChannel() {
		this.channelService.addChannel(channel);
		this.messageAction = "新增通道成功";
		return this.OPERATE_SUCCESS;
	}

	/**
	 * 跳转到更新页面
	 * 
	 * @return
	 */
	public String toUpdateChannel() {
		channel = (InternationalChannels) this.commonService.load(
				InternationalChannels.class, channelId);
		return SUCCESS;
	}

	/**
	 * 修改通道信息
	 * 
	 * @return
	 */
	public String updateChannel() {
		InternationalChannels c = (InternationalChannels) this.commonService.load(
				InternationalChannels.class, channelId);
		c.setChannelName(channel.getChannelName());
		c.setAccessCode(channel.getAccessCode());
		c.setBankMerchantId(channel.getBankMerchantId());
		c.setBankName(channel.getBankName());
		c.setBankUrl(channel.getBankUrl());
		c.setCheckUrl(channel.getCheckUrl());
		c.setCheckUserName(channel.getCheckUserName());
		c.setCheckUserPwd(channel.getCheckUserPwd());
		c.setMd5(channel.getMd5());
		c.setExecuteTime(new Date());
		c.setLastDate(new Date());
		c.setLastMan(getUserBean().getUserName());
		c.setOperator(getUserBean().getUserName());
		this.commonService.update(c);

		this.messageAction = "修改通道成功";
		return this.OPERATE_SUCCESS;
	}

	public String getSysMenu() {
		this.resourcelist = channelService.getResource();
		return "success";
	}

	// 角色列表
	public String toRole() {
		this.rolelist = this.channelService.getRoleList();
		this.getLoaction().setReload(true);
		return "success";
	}

	// 跳转到新增角色
	public String toaddRole() {

		return "success";
	}

	// 新增角色
	public String addrole() {
		this.channelService.addRole(this.role);
		this.messageAction = "新增角色成功";
		return this.OPERATE_SUCCESS;
	}

	public List<Resource> getResourcelist() {
		return resourcelist;
	}

	public void setResourcelist(List<Resource> resourcelist) {
		this.resourcelist = resourcelist;
	}

	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public List<Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	public List<InternationalChannels> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<InternationalChannels> channelList) {
		this.channelList = channelList;
	}

	public InternationalChannels getChannel() {
		return channel;
	}

	public void setChannel(InternationalChannels channel) {
		this.channel = channel;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}
}
