package com.ecpss.model.shop;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "INTERNATIONAL_Exchangerate")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalExchangerate implements java.io.Serializable{
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_exchangerate", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long Id;
	
	@Column(name = "moneykindno", nullable = true, length = 2)
	private Long moneykindno;			//交易币种
	
	@Column(name = "type", nullable = true, length = 100)
	private String type;   // 1:交易汇率  2:结算汇率
	
	@Column(name = "rate", nullable = true)
	private Double rate;		//汇率
	
	//private String exchangeRate;
	
	@Column(name="executetime") 
	private Date executetime;		//执行时间
	
	@Column(name="settime") 
	private Date settime;		//执行时间
	
	@Column(name = "creater", nullable = true, length = 20)
	private String creater;			//制单人
        
	private String showRate;//显示
	
	public String getShowRate() {
		return this.getRate().toString();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	/**4447962129857912
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the trate
	 */
	

	

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * @return the moneykindno
	 */
	public Long getMoneykindno() {
		return moneykindno;
	}

	/**
	 * @param moneykindno the moneykindno to set
	 */
	public void setMoneykindno(Long moneykindno) {
		this.moneykindno = moneykindno;
	}


	/**
	 * @return the executetime
	 */
	public Date getExecutetime() {
		return executetime;
	}

	/**
	 * @param executetime the executetime to set
	 */
	public void setExecutetime(Date executetime) {
		this.executetime = executetime;
	}

	/**
	 * @return the settime
	 */
	public Date getSettime() {
		return settime;
	}

	/**
	 * @param settime the settime to set
	 */
	public void setSettime(Date settime) {
		this.settime = settime;
	}

	/**
	 * @return the rate
	 */
	public Double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}

	/**
	 * @return the rate
	 */
	

	/**
	 * @return the exchangeRate
	 */
	/*public String getExchangeRate() {
		return exchangeRate;
	}

	*//**
	 * @param exchangeRate the exchangeRate to set
	 *//*
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
*/
	

}
