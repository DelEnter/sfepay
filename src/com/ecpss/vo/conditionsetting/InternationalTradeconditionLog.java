package com.ecpss.vo.conditionsetting;

import com.ecpss.model.log.SystemLog;

/**
 * 
 * 交易条件日志
 * 
 * @author huhongguang
 * 
 */
public class InternationalTradeconditionLog {

	private Long id;
	private Long merchantId;
	private Long itemno;
	private Long tradenumber;
	private Long cycle;
	private String remark;
	private String itemName;
	private String merno;

	private Long merchantIdafter;
	private Long itemnoafter;
	private Long tradenumberafter;
	private Long cycleafter;
	private String remarkafter;
	private String itemNameafter;

	public String getUpdateInternationalTradecondit() {
		StringBuffer sb = new StringBuffer("配置的商户为：" + this.merno + "\r\n");
		if (this.tradenumber != null && !this.tradenumber.equals(this.tradenumberafter)) {
			sb.append("可交易笔数:" + this.tradenumber + "------------>"
					+ this.tradenumberafter + "\r\n");
		}
		if (this.cycle != null && !this.cycle.equals(this.cycleafter)) {
			sb.append("周期:" + this.cycle + "------------>" + this.cycleafter
					+ "\r\n");
		}
		if (this.remark != null && !this.remark.equals(this.remarkafter)) {
			sb.append("备注:" + this.remark + "------------>" + this.remarkafter
					+ "\r\n");
		}
		System.out.println(sb);
		return sb.toString();
	}
	
	/**
	 * 创建交易条件设置
	 * @return
	 */
	public String addInternationalTradecondit() {
		StringBuffer sb = new StringBuffer("配置的商户为：" + this.merno + "\r\n");
		if (tradenumber == null || "".equals(tradenumber)) {
			if(tradenumber == null) {
				tradenumber = Long.parseLong("");
			}
			sb.append("可交易笔数:" +tradenumber + "------------>"
					+ tradenumberafter + "\r\n");
		}
		if (cycle == null || "".equals(cycle)) {
			if(cycle == null) {
				cycle = Long.parseLong("");
			}
			sb.append("周期:" + cycle + "------------>" +cycleafter
					+ "\r\n");
		}
		if (remark == null ||  "".equals(remark)) {
			if(remark == null) {
				remark = "";
			}
			sb.append("备注:" + remark + "------------>" + remarkafter
					+ "\r\n");
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

	public Long getItemno() {
		return itemno;
	}

	public void setItemno(Long itemno) {
		this.itemno = itemno;
	}

	public Long getTradenumber() {
		return tradenumber;
	}

	public void setTradenumber(Long tradenumber) {
		this.tradenumber = tradenumber;
	}

	public Long getCycle() {
		return cycle;
	}

	public void setCycle(Long cycle) {
		this.cycle = cycle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getMerno() {
		return merno;
	}

	public void setMerno(String merno) {
		this.merno = merno;
	}

	public Long getMerchantIdafter() {
		return merchantIdafter;
	}

	public void setMerchantIdafter(Long merchantIdafter) {
		this.merchantIdafter = merchantIdafter;
	}

	public Long getItemnoafter() {
		return itemnoafter;
	}

	public void setItemnoafter(Long itemnoafter) {
		this.itemnoafter = itemnoafter;
	}

	public Long getTradenumberafter() {
		return tradenumberafter;
	}

	public void setTradenumberafter(Long tradenumberafter) {
		this.tradenumberafter = tradenumberafter;
	}

	public Long getCycleafter() {
		return cycleafter;
	}

	public void setCycleafter(Long cycleafter) {
		this.cycleafter = cycleafter;
	}

	public String getRemarkafter() {
		return remarkafter;
	}

	public void setRemarkafter(String remarkafter) {
		this.remarkafter = remarkafter;
	}

	public String getItemNameafter() {
		return itemNameafter;
	}

	public void setItemNameafter(String itemNameafter) {
		this.itemNameafter = itemNameafter;
	}

}
