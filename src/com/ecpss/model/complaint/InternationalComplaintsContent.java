package com.ecpss.model.complaint;

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

@Entity
@Table(name = "international_comcontent")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class InternationalComplaintsContent extends PriEntity implements Serializable {
	private static final long serialVersionUID = 1000000000000000454L;
	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_complaintscontent", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	private Long id;
	@Column(name = "complaintId",nullable = true)
	private Long complaintId;      //投诉表ID
	@Column(name = "content",nullable = true,length=1000)
	private String content;        //投诉 / 回复内容
	@Column(name = "newdate",nullable = true)
	private Date newdate;             //添加时间
	@Column(name = "contenttype",nullable = true,length=2)
	private String contenttype;           //类型  1:客服投诉  2:商户回复
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getNewdate() {
		return newdate;
	}
	public void setNewdate(Date newdate) {
		this.newdate = newdate;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
}
