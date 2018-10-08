package com.ecpss.model.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;

import com.ecpss.model.PriEntity;

@Entity
@Table(name = "international_agentsmerchant")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalAgentsMerchant extends PriEntity implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_agentsmerchant", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	
	@Column(name = "agentsMerchantNo", nullable = true)
	private Long agentsMerchantNo;
	
	@Column(name = "merchantId", nullable = true)
	private Long merchantId;
	
	@Column(name = "password", nullable = true)
	private String password;
	
	@Column(name = "agentsName", nullable = true)
	private String agentsName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgentsMerchantNo() {
		return agentsMerchantNo;
	}

	public void setAgentsMerchantNo(Long agentsMerchantNo) {
		this.agentsMerchantNo = agentsMerchantNo;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getAgentsName() {
		return agentsName;
	}

	public void setAgentsName(String agentsName) {
		this.agentsName = agentsName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}