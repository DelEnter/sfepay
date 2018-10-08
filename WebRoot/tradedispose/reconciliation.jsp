<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<html>
  <head>
    <title>上传银联对账数据</title>
 	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  </head>
  
  <body>
    <center>
    	<h3>上传银联对账数据</h3>
    	<s:form action="uploadDuiZhang" id="form1" method="post" theme="simple" enctype="multipart/form-data">
    	通道：<s:select name="duiType" list="#{'0':'银联对账','1':'其他通道对账'}" listKey="key" listValue="value"/><br/>
    	开始日期：<input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/><br/>
		结束日期：<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/><br/>
	              文件：<input type="file" name="fileName" id="fileName">
	    	<input type="button" value="上传" onclick="toUpload()">
	
	</s:form><br/>
	<span><s:if test="%{errMsg!=null}">差异的订单：<s:property value="errMsg" /></s:if></span>
    </center>
  </body>
  <script language="javascript" type="text/javascript">
	loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "en");
	loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "en");
	function cleanDate(vid){
	    document.getElementById(vid).value="";
	   }
	function toUpload(){
		var startTime=document.getElementById("start_time").value;
		var end_time=document.getElementById("end_time").value;
		var fileName=document.getElementById("fileName").value;
		if(startTime==""||end_time==""||fileName==""){
			alert("有为空的项！");
			return false;
		}
		document.getElementById("form1").submit();
	}
	</script>
</html>
