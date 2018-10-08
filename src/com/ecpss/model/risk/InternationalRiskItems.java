package com.ecpss.model.risk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;



/**
 * 风险类型表
 */
import com.ecpss.model.PriEntity;

/**
 * 风控项目表
 * @author jiahui
 *
 */

@Entity
@Table(name = "international_riskitems")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalRiskItems extends PriEntity implements java.io.Serializable{
	private static final long	serialVersionUID	= 6348641281692587064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_RiskItems", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "merchantNo")
	private String merchantNo; // 商户号
	@Column(name = "itemName")
	private String itemName;
	@Column(name = "itemType")
	private String itemType;
	@Column(name = "tradeWeb")
	private String tradeWeb;
	@Column(name = "remark")
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMerchantNo() {
		return merchantNo;
	}
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	public String getTradeWeb() {
		return tradeWeb;
	}
	public void setTradeWeb(String tradeWeb) {
		this.tradeWeb = tradeWeb;
	}

}
