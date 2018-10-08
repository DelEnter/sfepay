package com.ecpss.vo.channel;

import java.util.Date;

import javax.persistence.Column;

import com.ecpss.model.PriEntity;
import com.ecpss.util.DateUtil;

public class InternationalWebchannelsLog extends PriEntity {

	private Long id;

	private Long merchantId; // 商户

	private Long channelId; // 通道

	private Date executeTime; // 执行时间

	private String operator; // 制表人

	private String onoff; // 是否打开 0:关闭 1:打开

	private Long balanceCycle; // 通道结算周期

	private String channelName; // 通道名称

	private Double balanceCharge; // 通道结算手续费率

	private Long bailCycle; // 通道保证金结算周期

	private Double bailCharge; // 通道保证金结算手续费率

	private Long merchantIdafter;

	private Long channelIdafter;

	private Date executeTimeafter;

	private String operatorafter;

	private String onoffafter;

	private Long balanceCycleafter;

	private Double balanceChargeafter;

	private Long bailCycleafter;

	private Double bailChargeafter;

	private String merno; // 商户号

	private String channelNameatter; // 通道名称

	public String getUpdateInternationalWebchannelsLog() {
		StringBuffer sb = new StringBuffer("配置的商户为：" + this.merno + "\r\n");
		if (this.bailCycle != null
				&& !(this.bailCycle.equals(this.bailCycleafter))) {
			sb.append("保证金结算周期:" + this.bailCycle + "------------>"
					+ this.bailCycleafter + "\r\n");
		}
		if(this.balanceCharge != null && !(this.balanceCharge.equals(this.balanceChargeafter))) {
			sb.append("保证金结算费率:" + this.bailCharge + "------------>"
					+ this.bailChargeafter + "\r\n");
			
		}
		
		if (this.balanceCycle != null
				&& !(this.balanceCycle.equals(this.balanceCycleafter))) {
			sb.append("结算周期:" + this.balanceCycle + "------------>"
					+ this.balanceCycleafter + "\r\n");
		}
		// ------------------------------------
		if (this.channelId != null
				&& !(this.channelId.equals(this.channelIdafter))) {
			 sb.append("通道编号:" + this.channelId + "------------>"
			 + this.channelIdafter + "\r\n");
//			sb.append("通道:" + this.channelName + "------------>"
//					+ this.channelNameatter + "\r\n");
		}

		if (this.executeTime != null
				&& !(this.executeTime.equals(this.executeTimeafter))) {

			sb.append("执行时间:" + DateUtil.formatDateTime(this.executeTime) + "------------>"
					+ DateUtil.formatDateTime(this.executeTimeafter) + "\r\n");
		}
		// ----------------------------------------------------
		if (this.merchantId != null
				& !(this.merchantId.equals(this.merchantIdafter))) {
			sb.append("商户:" + this.merchantId + "------------>"
					+ this.merchantIdafter + "\r\n");
		}
		if (this.onoff != null && !(this.onoff.equals(this.onoffafter))) {
			sb.append("是否打开:" + this.onoff + "------------>" + this.onoffafter
					+ "\r\n");
		}
		if (this.operator != null
				&& !(this.operator.equals(this.operatorafter))) {
			sb.append("制表人:" + this.operator + "------------>"
					+ this.operatorafter + "\r\n");
		}
		System.out.println(sb);
		return sb.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public Long getBalanceCycle() {
		return balanceCycle;
	}

	public void setBalanceCycle(Long balanceCycle) {
		this.balanceCycle = balanceCycle;
	}

	public Double getBalanceCharge() {
		return balanceCharge;
	}

	public void setBalanceCharge(Double balanceCharge) {
		this.balanceCharge = balanceCharge;
	}

	public Long getBailCycle() {
		return bailCycle;
	}

	public void setBailCycle(Long bailCycle) {
		this.bailCycle = bailCycle;
	}

	public Double getBailCharge() {
		return bailCharge;
	}

	public void setBailCharge(Double bailCharge) {
		this.bailCharge = bailCharge;
	}

	public Long getMerchantIdafter() {
		return merchantIdafter;
	}

	public void setMerchantIdafter(Long merchantIdafter) {
		this.merchantIdafter = merchantIdafter;
	}

	public Long getChannelIdafter() {
		return channelIdafter;
	}

	public void setChannelIdafter(Long channelIdafter) {
		this.channelIdafter = channelIdafter;
	}

	public Date getExecuteTimeafter() {
		return executeTimeafter;
	}

	public void setExecuteTimeafter(Date executeTimeafter) {
		this.executeTimeafter = executeTimeafter;
	}

	public String getOperatorafter() {
		return operatorafter;
	}

	public void setOperatorafter(String operatorafter) {
		this.operatorafter = operatorafter;
	}

	public String getOnoffafter() {
		return onoffafter;
	}

	public void setOnoffafter(String onoffafter) {
		this.onoffafter = onoffafter;
	}

	public Long getBalanceCycleafter() {
		return balanceCycleafter;
	}

	public void setBalanceCycleafter(Long balanceCycleafter) {
		this.balanceCycleafter = balanceCycleafter;
	}

	public Double getBalanceChargeafter() {
		return balanceChargeafter;
	}

	public void setBalanceChargeafter(Double balanceChargeafter) {
		this.balanceChargeafter = balanceChargeafter;
	}

	public Long getBailCycleafter() {
		return bailCycleafter;
	}

	public void setBailCycleafter(Long bailCycleafter) {
		this.bailCycleafter = bailCycleafter;
	}

	public Double getBailChargeafter() {
		return bailChargeafter;
	}

	public void setBailChargeafter(Double bailChargeafter) {
		this.bailChargeafter = bailChargeafter;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public String getChannelNameatter() {
		return channelNameatter;
	}

	public void setChannelNameatter(String channelNameatter) {
		this.channelNameatter = channelNameatter;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

}
