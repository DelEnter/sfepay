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
@Table(name = "international_twodispose")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalTwoDispose extends PriEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_twodispose", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	private Long id;
	
	@Column(name = "tradeId")
	private Long tradeId;      //交易ID
	
	@Column(name = "shijikoukuan")
	private Double shijikoukuan;         //实际扣款

	@Column(name = "yingkoukuan")
	private Double yingkoukuan;         //应扣款
	
	@Column(name = "chae")
	private Double chae;   //差额
	
	@Column(name = "batchno", length = 20)
	private String batchno;           //批次号
	
	@Column(name = "authno", length = 20)
	private String authno;        //授权号
	
	@Column(name = "tworesult", length = 20)
	private String tworesult;      //二次结果
	
	@Column(name = "remark", length = 20)
	private String remark;     //上传的授权号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Double getShijikoukuan() {
		return shijikoukuan;
	}

	public void setShijikoukuan(Double shijikoukuan) {
		this.shijikoukuan = shijikoukuan;
	}

	public Double getYingkoukuan() {
		return yingkoukuan;
	}

	public void setYingkoukuan(Double yingkoukuan) {
		this.yingkoukuan = yingkoukuan;
	}

	public Double getChae() {
		return chae;
	}

	public void setChae(Double chae) {
		this.chae = chae;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getAuthno() {
		return authno;
	}

	public void setAuthno(String authno) {
		this.authno = authno;
	}

	public String getTworesult() {
		return tworesult;
	}

	public void setTworesult(String tworesult) {
		this.tworesult = tworesult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	
	
	
}