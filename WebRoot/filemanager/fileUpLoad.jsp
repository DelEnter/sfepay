<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>文件上传</title>
</head>
<body style="margin: 0px;">
<s:form name="fileupload" id="filed" action ="fileUpLoad" namespace="/merchant" method ="POST" theme="simple" enctype ="multipart/form-data" > 
<table width="100%" border="0" cellspacing="1" cellpadding="1"
	class="bg_white" align="center">
	<tr>
		<td align="center">
		<table align="center" border=0 cellpadding=1 cellspacing=1
			width="100%">
			<tr>
				<td colspan="3" align="right" class="bg_table01" height="0.5"><img
					src="./../images/temp.gif" width="1" height="3"></td>
			</tr>
			<td colspan="3" align="right" class="bg_table03">&nbsp;</td>
			<tr class="bg_table02">
				<td width="17%" align="right" class="bg_table02">
				<div align="right"><font color="red">* </font>请选择文件：</div>
				</td>
				<td align="right" class="bg_table02">
				<div align="left"><s:file name="myFile" onchange="fillFileName(this.value)"/></div>
				</td>
			</tr>
			<tr class="bg_table02">
				<td align="right"><font color="red">* </font>文件名：</td>
				<td align="left"><s:textfield size="30" maxlength="50" id="filename" name="fm.filename"/>
				</td>
			</tr>
			<tr align="center">
				<td colspan="3" class="bg_table02">&nbsp;</td>
			</tr>
			<tr align="center">
				<td colspan="3" class="bg_table03">
				<input type="button" value="上传" class="button01" onclick="check();"/>
				</td>
			</tr>
		</table>
</table>
<s:hidden name="fid"/>
<table width="70%" border="1" cellspacing="1" cellpadding="1" class="bg_white" align="center">
	<s:iterator id="p" value="fileList" status="s">
	<tr>
		<td align="center"><s:property value="#p.filename" /></td>
		<td align="center"><s:property value="#p.fileroute" /></td>
		<td align="right"><s:property value="#p.filedate" /></td>
		<td>
		<input type="button" name="SaveBtn" onclick="downloadfile(<s:property value='#p.id'/>)" value="　下 载　" class="button02">
		</td>
	</tr>
	</s:iterator>
</table>
<script language="javascript">
function check(){	
		document.forms(0).submit();
}
function downloadfile(fid){
	document.getElementById("fid").value=fid;
	document.getElementById("filed").action="downloadFile";
	document.getElementById("filed").submit();
}
function fillFileName(filePath){
	if(filePath != null){
		var lastNameIndex = filePath.lastIndexOf('\\');
		if(lastNameIndex == -1){
			lastNameIndex = filePath.lastIndexOf('/');
		}
		if(lastNameIndex != -1){
			document.getElementById('filename').value = filePath.substring(lastNameIndex+1);
		}
	}
}
</script>
</s:form>
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
</html>