package com.ecpss.action.mersetting;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ecpss.action.BaseAction;
import com.ecpss.model.channel.InternationalChannels;
import com.ecpss.model.channel.InternationalMerchantChannels;
import com.ecpss.model.log.SystemLog;
import com.ecpss.model.shop.InternationalMerchant;
import com.ecpss.service.iservice.ChannelService;
import com.ecpss.vo.channel.InternationalWebchannelsLog;

public class MerChannelSettingAction extends BaseAction {
	
	@Autowired
	@Qualifier("channelService")
	private ChannelService channelService;
	
	private Long merid;            //商户id
	private List merChannelList;
	private Long merChannelId;
	private String onoff;
	private InternationalMerchant merchant;
	private List<InternationalChannels> channelList;
	
	private InternationalMerchantChannels merchannel;
	
	private String merno;  //商户号
	private Long merchantno;
	private String channelName;		//通道名称
	/**
	 * 跳转到商户通道管理页面
	 * @return
	 */
	public String toMerChannel(){
		if(merchantno!=null){
			String hlq="select m.id from InternationalMerchant m where m.merno="+merchantno;
			merid = (Long) this.commonService.uniqueResult(hlq);
			merChannelList = channelService.getMerChannelList(merid);
		}else{
			merChannelList = channelService.getMerChannelList(merid);
			String h="select m.merno from InternationalMerchant m where m.id="+merid;
			merchantno = (Long) this.commonService.uniqueResult(h);
		}
		this.getLoaction().setReload(true);
		return SUCCESS;
	}
	/**
	 * 跳转到添加商户通道,根据商户号ID
	 * @return
	 */
	public String toAddMerChannel(){
		merchant = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, merid);
		channelList = channelService.getChannelList();
		return SUCCESS;
	}
	/**
	 * 添加商户通道信息.包括结算信息
	 * @return
	 */
	public String addMerChannel(){
		merchannel.setExecuteTime(new Date());
		merchannel.setLastDate(new Date());
		merchannel.setLastMan(getUserBean().getUserName());
		merchannel.setOnoff("1");
		this.commonService.save(merchannel);
		this.messageAction="新增通道成功";		
		return this.OPERATE_SUCCESS;
	}
	/**
	 * 跳转到商户通道修改页面
	 * @return
	 */
	public String toUpdateMerChannel(){
		merchant = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, merid);
		channelList = channelService.getChannelList();
		
		merchannel = (InternationalMerchantChannels) this.commonService.load(InternationalMerchantChannels.class, merChannelId);
		
		return SUCCESS; 
	}
	/**
	 * 修改商户通道信息
	 * @return
	 */
	public String updateMerChannel(){
		InternationalMerchantChannels mc = (InternationalMerchantChannels) this.commonService.load(InternationalMerchantChannels.class, merChannelId);
		InternationalMerchant m = (InternationalMerchant) this.commonService.load(InternationalMerchant.class, mc.getMerchantId());
		// 创建日志实体对象
		SystemLog sl = new SystemLog();
		
		// 创建商户通道vo 记录操作前的状态
		InternationalWebchannelsLog iwl = new InternationalWebchannelsLog();
		//1.记录更新前的状态
		iwl.setLastDate(mc.getLastDate());
		iwl.setLastMan(mc.getLastMan());
		iwl.setBailCharge(mc.getBailCharge());
		iwl.setBailCycle(mc.getBailCycle());
		iwl.setBalanceCharge(mc.getBalanceCharge());
		iwl.setBalanceCycle(mc.getBalanceCycle());
		iwl.setChannelId(mc.getChannelId());
//		//设置通道名称
//		iwl.setChannelName(channelName);
		iwl.setExecuteTime(mc.getExecuteTime());
		iwl.setMerchantId(mc.getMerchantId());
		iwl.setOnoff(mc.getOnoff());
		iwl.setOperator(mc.getOperator());
		iwl.setMerno(m.getMerno()+"");
		
		mc.setBailCycle(merchannel.getBailCycle());
		mc.setBailCharge(merchannel.getBailCharge());
		mc.setBalanceCharge(merchannel.getBalanceCharge());
		mc.setBalanceCycle(merchannel.getBalanceCycle());
		mc.setChannelId(merchannel.getChannelId());
		mc.setMaxmind_lv1(merchannel.getMaxmind_lv1());
		mc.setMaxmind_lv2(merchannel.getMaxmind_lv2());
		mc.setPriority(merchannel.getPriority());
		mc.setChannelFee(merchannel.getChannelFee());
		mc.setExecuteTime(new Date());
		this.commonService.update(mc);
		
		//2.记录操作后的状态 
		iwl.setBailChargeafter(mc.getBailCharge());
		iwl.setBailCycleafter(mc.getBailCycle());
		iwl.setBalanceChargeafter(mc.getBalanceCharge());
		iwl.setBalanceCycleafter(mc.getBalanceCycle());
		iwl.setChannelIdafter(mc.getChannelId());
		//iwl.setChannelNameatter(channelName);
		iwl.setExecuteTimeafter(mc.getExecuteTime());
		iwl.setMerchantIdafter(mc.getMerchantId());
		iwl.setOnoffafter(mc.getOnoff());
		iwl.setOperatorafter(mc.getOperator());
		
		
		
		// 设置日志属性值
		sl.setUserName(this.getUserBean().getUserName());
		sl.setOperTime(new Date());
		sl.setOperType("2");
		sl.setMerno(iwl.getMerno());
		sl.setRemarks(iwl.getUpdateInternationalWebchannelsLog());
		sl.setRescReow("修改商户通道");
		this.commonService.save(sl);
		this.messageAction="修改成功";		
		return this.OPERATE_SUCCESS;
	}
	/**
	 * 商户通道开关
	 * @return
	 */
	public String openorclose(){
		System.out.println(onoff);
		if(onoff.equals("1")){
			onoff="0";
			this.messageAction="通道已关闭";
		}else if(onoff.equals("0")){
			onoff="1";
			this.messageAction="通道已开通";
		}
		InternationalMerchantChannels mc = (InternationalMerchantChannels) this.commonService.load(InternationalMerchantChannels.class, merChannelId);
		mc.setOnoff(onoff);
		mc.setExecuteTime(new Date());
		this.commonService.update(mc);
		return this.OPERATE_SUCCESS;
	}
	
	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public List getMerChannelList() {
		return merChannelList;
	}

	public void setMerChannelList(List merChannelList) {
		this.merChannelList = merChannelList;
	}

	public Long getMerid() {
		return merid;
	}

	public void setMerid(Long merid) {
		this.merid = merid;
	}
	public InternationalMerchant getMerchant() {
		return merchant;
	}
	public void setMerchant(InternationalMerchant merchant) {
		this.merchant = merchant;
	}
	public List<InternationalChannels> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<InternationalChannels> channelList) {
		this.channelList = channelList;
	}
	public InternationalMerchantChannels getMerchannel() {
		return merchannel;
	}
	public void setMerchannel(InternationalMerchantChannels merchannel) {
		this.merchannel = merchannel;
	}
	public Long getMerChannelId() {
		return merChannelId;
	}
	public void setMerChannelId(Long merChannelId) {
		this.merChannelId = merChannelId;
	}
	public String getOnoff() {
		return onoff;
	}
	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}
	public String getMerno() {
		return merno;
	}
	public void setMerno(String merno) {
		this.merno = merno;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Long getMerchantno() {
		return merchantno;
	}
	public void setMerchantno(Long merchantno) {
		this.merchantno = merchantno;
	}
	
}
