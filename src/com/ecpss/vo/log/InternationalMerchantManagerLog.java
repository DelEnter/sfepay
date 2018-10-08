package com.ecpss.vo.log;

/**
 * 商户管理值设定日志
 * @author huhongguang
 *
 */
public class InternationalMerchantManagerLog {
	private Long id;
	private Long merchantId;
	private Long markValue;
	private Long penQuotaLower;
	private Long penQuota;
	private Long dayQuota;
	private Long monthQuota;
    private String merno;
	private Long merchantIdafter;
	private Long markValueafter;
	private Long penQuotaLowerafter;
	private Long penQuotaafter;
	private Long dayQuotaafter;
	private Long monthQuotaafter;

	public String getUpdateLog() {
		StringBuffer sb = null;
		if(merno != null && !("".equals(merno.trim()))) {
			sb = new StringBuffer("配置的商户为："+this.merno+"\r\n");
		}
		if (!this.dayQuota.equals(this.dayQuotaafter)) {
			sb.append("单笔交易限额:" + this.dayQuota + "------------>"
					+ this.dayQuotaafter+"\r\n");
		}
		if (!this.markValue.equals(this.markValueafter)) {
			sb.append("分值:" + this.markValue + "------------>"
					+ this.markValueafter+"\r\n");
		}
		if (!this.monthQuota.equals(this.monthQuotaafter)) {
			sb.append("月交易限额:" + this.monthQuota + "------------>"
					+ this.monthQuotaafter+"\r\n");
		}
		if (!this.penQuota.equals(this.penQuotaafter)) {
			sb.append("单笔交易限额(上限):" + this.penQuota + "------------>"
					+ this.penQuotaafter+"\r\n");
		}
//		if (!this.penQuotaLowerafter.equals(this.penQuotaLowerafter)) {
//			sb.append("单笔交易限额(下限):" + this.penQuota + "------------>"
//					+ this.penQuotaafter+"\r\n");
//		}
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

	public Long getMarkValue() {
		return markValue;
	}

	public void setMarkValue(Long markValue) {
		this.markValue = markValue;
	}


	public Long getPenQuota() {
		return penQuota;
	}

	public void setPenQuota(Long penQuota) {
		this.penQuota = penQuota;
	}

	public Long getDayQuota() {
		return dayQuota;
	}

	public void setDayQuota(Long dayQuota) {
		this.dayQuota = dayQuota;
	}

	public Long getMonthQuota() {
		return monthQuota;
	}

	public void setMonthQuota(Long monthQuota) {
		this.monthQuota = monthQuota;
	}

	public Long getMerchantIdafter() {
		return merchantIdafter;
	}

	public void setMerchantIdafter(Long merchantIdafter) {
		this.merchantIdafter = merchantIdafter;
	}

	public Long getMarkValueafter() {
		return markValueafter;
	}

	public void setMarkValueafter(Long markValueafter) {
		this.markValueafter = markValueafter;
	}


	public Long getPenQuotaafter() {
		return penQuotaafter;
	}

	public void setPenQuotaafter(Long penQuotaafter) {
		this.penQuotaafter = penQuotaafter;
	}

	public Long getDayQuotaafter() {
		return dayQuotaafter;
	}

	public void setDayQuotaafter(Long dayQuotaafter) {
		this.dayQuotaafter = dayQuotaafter;
	}

	public Long getMonthQuotaafter() {
		return monthQuotaafter;
	}

	public void setMonthQuotaafter(Long monthQuotaafter) {
		this.monthQuotaafter = monthQuotaafter;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public Long getPenQuotaLower() {
		return penQuotaLower;
	}

	public void setPenQuotaLower(Long penQuotaLower) {
		this.penQuotaLower = penQuotaLower;
	}

	public Long getPenQuotaLowerafter() {
		return penQuotaLowerafter;
	}

	public void setPenQuotaLowerafter(Long penQuotaLowerafter) {
		this.penQuotaLowerafter = penQuotaLowerafter;
	}

}
