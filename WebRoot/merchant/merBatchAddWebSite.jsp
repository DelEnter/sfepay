<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<html>
<head>
	<title>批量上传</title>
	<script language="JavaScript" src="../js/util.js"></script>
</head> 
<script language="JavaScript" type="text/JavaScript">

function batchWebUpload(){
	var file=document.getElementById("batchWebFileId").value;
	if(file==""){
		alert("请选择文件！");
		return false;
	}
	var xls=file.split(".");
	if(xls[xls.length-1]!="xls"&&xls[xls.length-1]!="xlsx"){
		alert("请选择正确的格式文件,如(**.xls)!");
		return false;
	}
	document.getElementById("myForm").submit();
}
</script>
  <body>
<s:form action="batchWebUpload" id="myForm" namespace="/merchant" theme="simple" enctype="multipart/form-data" >
<table style="margin-left: 50px">
	<tr>
		<td>文件名：</td>
		<td>
			<s:file name="batchWebFile" id="batchWebFileId"></s:file>
		</td>
	</tr>
	</table>
	<br />
	<input style="margin-left: 50px;" type="button" value="上  传" onclick="batchWebUpload()"
		class="windows_icon1" />
		<br/>
		<br/>
		<span style="margin-left: 80px;">*上传模版格式，请点<a href="../template/batchWebsite.xls"><span style="color:red;">下载</span></a></span>
		<br/>
		<br/>
		<span style="margin-left: 80px;color:red">*添加的网址将在网址审核列表里面等待审核才能生效。</span>
		<br/>
		<br/>
		<span style="margin-left: 50px;color:blue">*温馨提示：</span>
		<br/>
		<span style="margin-left: 60px;">* 模版文件第一行是标题，请不要修改或删除.</span>
		<br/>
		<span style="margin-left: 60px;">* 添加网址内容从第二行开始添加，第一列tradeWebsite(交易网址)，第二列webSite(返回网址)</span>
</s:form>
</body>
</html>