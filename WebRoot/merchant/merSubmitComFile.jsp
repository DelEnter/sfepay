<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>上传拒付申诉文件</title>
</head>
<script language="javascript">
	function submitonefile(f){	
		goFormWindow(f,"../merchant/merSubmitCom");
	}
	function fillFileName(filePath){
		if(filePath != null){
			var lastNameIndex = filePath.lastIndexOf('\\');
			if(lastNameIndex == -1){
				lastNameIndex = filePath.lastIndexOf('/');
			}
			if(lastNameIndex != -1){
				document.getElementById('fileName').value = filePath.substring(lastNameIndex+1);
			}
		}
	}
	function closeby(){
		opener.reloadPage();
		this.window.close();   
	}
</script>
<body style="margin: 0px;">
<s:if test="messageAction==null">
	<s:form action="merSubmitCom" id="form1" namespace="/merchant" method="POST" theme="simple" enctype="multipart/form-data">
	<input type="hidden" name="chargeBackId" value="<s:property value="cb.id"/>"/>
	<table width="60%" border="0" cellspacing="1" cellpadding="1"
		class="bg_white" align="center">
		<tr>
			<td align="center">
			<table align="center" border=0 cellpadding=1 cellspacing=1
				width="100%">
							<td colspan="3" align="right" class="bg_table03">&nbsp;</td>
				<tr class="bg_table02">
					<td width="30%" align="right" class="bg_table02">
					<div align="right"><font color="red">* </font>请选择文件：</div>
					</td>
					<td align="right" class="bg_table02">
					<div align="left"><s:file name="myFile" onchange="fillFileName(this.value)"/></div>
					</td>
				</tr>
				<tr class="bg_table02">
					<td align="right"><font color="red">* </font>文件名：</td>
					<td align="left"><s:textfield size="30" maxlength="50" id="filename" name="cb.complaintsFileName"/>
					</td>
				</tr>
				<tr align="center">
					<td colspan="3" class="bg_table02">&nbsp;</td>
				</tr>
				<tr align="center">
					<td colspan="3" class="bg_table03">
					<input type="submit" value="上传" />
					</td>
				</tr>
			</table>
	</table>
	</s:form>
</s:if>
<s:else>
<div align="center">
	<font color="red">
		<s:property value="messageAction"/>
	</font>
	<input type="button" name="colseit" value="Close" onclick="closeby()"/>
<div>b
</s:else>
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
</html>