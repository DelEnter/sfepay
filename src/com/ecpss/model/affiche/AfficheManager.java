package com.ecpss.model.affiche;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



import org.hibernate.annotations.Proxy;

import com.ecpss.model.PriEntity;

/**
 * 公告管理
 * 
 * @author huhongguang
 * 
 */
@Entity
@Table(name = "international_affichemanager")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class AfficheManager extends PriEntity implements Serializable {
	private static final long	serialVersionUID	= 6348641281693287064L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_AfficheManager", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id; // 数据表id 自增

	@Column(name = "afficheContext", nullable = true)
	private String afficheContext; // 公告内容

	@Column(name = "url", nullable = true)
	private String url; // 公告链接

	@Column(name = "affichedate")
	private Date affichedate; // 公告时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAfficheContext() {
		return afficheContext;
	}

	public void setAfficheContext(String afficheContext) {
		this.afficheContext = afficheContext;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getAffichedate() {
		return affichedate;
	}

	public void setAffichedate(Date affichedate) {
		this.affichedate = affichedate;
	}

}
