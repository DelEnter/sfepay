package com.ecpss.action.tradedispose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;

import com.ecpss.action.BaseAction;

public class ReconciliationAction extends BaseAction {
	private String startDate;
	private String endDate;
	private String fileName;
	private String errMsg="";
	private String duiType;
	public String toUploadDuiZhang(){
		errMsg=null;
		return SUCCESS;
	}
	public String uploadDuiZhang(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GBK"));
			String line;
			br.readLine();
			while ( (line = br.readLine()) != null ) {
				String[] info = line.split(",");
				if("0".equals(duiType)){
					if(info.length>10){
					String posNo = info[6].trim().substring(1);
					String hql="select ti from InternationalTradeinfo ti where substr(ti.tradeState,1,1)='1' and ti.VIPBatchNo='"+posNo+"'";
					if(StringUtils.isNotBlank(startDate)){
						hql=hql+" and ti.tradeTime>=to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss')";
					}
					if(StringUtils.isNotBlank(endDate)){
						hql=hql+" and ti.tradeTime<=to_date('"+endDate+" 23:59:59"+"','yyyy-MM-dd hh24:mi:ss')";
					}
					List list=commonService.list(hql);
					if(list==null||list.size()==0){
						errMsg=errMsg+posNo+"¡¢";
					}
					}
				}if("1".equals(duiType)){
					String oraderNo = info[1].trim().toString();
					String hql="select ti from InternationalTradeinfo ti where substr(ti.tradeState,1,1)='1' and ti.orderNo='"+oraderNo+"'";
					if(StringUtils.isNotBlank(startDate)){
						hql=hql+" and ti.tradeTime>=to_date('"+startDate+"','yyyy-MM-dd hh24:mi:ss')";
					}
					if(StringUtils.isNotBlank(endDate)){
						hql=hql+" and ti.tradeTime<=to_date('"+endDate+" 23:59:59"+"','yyyy-MM-dd hh24:mi:ss')";
					}
					List list=commonService.list(hql);
					if(list==null||list.size()==0){
						errMsg=errMsg+oraderNo+"¡¢";
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(StringUtils.isBlank(errMsg)){
			errMsg="ÎÞ²î´í½»Ò×£¡";
		}
		return SUCCESS;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getDuiType() {
		return duiType;
	}
	public void setDuiType(String duiType) {
		this.duiType = duiType;
	}

}
