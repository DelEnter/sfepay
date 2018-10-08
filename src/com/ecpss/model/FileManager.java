package com.ecpss.model;

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

@Entity
@Table(name = "File_Manager")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true, dynamicInsert = true, dynamicUpdate = true)
@Proxy(lazy = false)
public class FileManager implements Serializable {
	private static final long	serialVersionUID	= 6348641281692587064L;

	@Id
	@TableGenerator(name = "sequenceTable", table = "application_sequence", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "seq_filemanager", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceTable")
	@Column(length = 20)
	private Long id;
	@Column(name = "filename", nullable = true, length = 100)
	private String				filename;
	@Column(name = "fileroute", nullable = true, length = 100)
	private String				fileroute;
	@Column(name = "filetype", nullable = true, length = 20)
	private String				filetype;
	@Column(name = "remarks", nullable = true, length = 500)
	private String				remarks;
	@Column(name = "filesize", nullable = true, length = 10 )
	private Long			    filesize;
	@Column(name = "filedate", nullable = true, length = 50)
	private Date				filedate;
	@Column(name = "downloadcount")
	private Long				downloadcount;
	@Column(name = "downloadDate")
	private Date				downloadDate;
	/**
	 * 存储类型
	 * 0 :  DCC退款批量下载
	 * 
	 */
	@Column(name = "usetype", nullable = true, length = 50)
	private String				usetype;  //存储类型
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileroute() {
		return fileroute;
	}
	public void setFileroute(String fileroute) {
		this.fileroute = fileroute;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getFilesize() {
		return filesize;
	}
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	public Date getFiledate() {
		return filedate;
	}
	public void setFiledate(Date filedate) {
		this.filedate = filedate;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getUsetype() {
		return usetype;
	}
	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}
	public Long getDownloadcount() {
		return downloadcount;
	}
	public void setDownloadcount(Long downloadcount) {
		this.downloadcount = downloadcount;
	}
	public Date getDownloadDate() {
		return downloadDate;
	}
	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}
	
}
