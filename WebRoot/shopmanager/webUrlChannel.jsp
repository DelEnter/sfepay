<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
 <%@ include file="../include/checkAll.jsp" %>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>网站独立通道设置</title>
</head>
<div align="center" >
	<h3>网站独立通道设置</h3>
</div>
<script language="JavaScript" type="text/JavaScript">
function addUrlChannel(){
	if(document.getElementById("webUrl").value==""||document.getElementById("channelName").value==""||document.getElementById("merchantId").value==""){
		alert("前3项不能为空！");
		return false;
	}
	if(confirm("确认添加吗？")){
	document.getElementById("formu").action="addUrlChannel.action";
	document.getElementById("formu").submit();
	}
}
function delSen(type){
	var str=document.getElementsByName("orderIds");
	var ids="";
	for(var i=0;i<str.length;i++){
		if(str[i].checked==true){
			var value=str[i].value;
			ids+=value+",";
		}
	}
	if(ids==""){
		alert("请选择要操作的项！");
		return  false;
	}
	if(confirm("确认删除吗？")){
	window.location="../PaySystem/delUrlChannel.action?riskIds="+ids;
	}
}
</script>

<s:form id="formu" action="getUrlChannel" method="post" theme="simple">
	<table align="center">
		<tr class=TR_Title>
			<td align="right">网址：</td>
	 		<td><input type="text" id="webUrl" name="urlChannel.webUrl"></td>
	 		<td align="right">通道：</td>
	 		<td><s:select id="channelName" name="urlChannel.channelName" list="channelList" /></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">商户号：</td>
	 		<td><s:select id="merchantId" name="urlChannel.merchantId" list="list" listKey="id" listValue="merno" /></td>
	 		<td align="right">备注：</td>
	 		<td><input type="text" id="remark" name="urlChannel.remark"></td>
	 	
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="addUrlChannel()" value="添加" >
				</td>
	 		</tr>
	</table>
	<br/>
	<br/>
	<div align="center"><s:if test="%{msg!=null}"><span style="color:red"><s:property value="msg" /></span></s:if></div>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr align="center" bgColor=#cccccc>
		<td width="5%" bgcolor="#FFFFFF" align="center">
  			<input type="checkbox" onclick='chkall("formu",this)' name=chk>
  		</td>
  		<td width="25%">网址</td>
		<td width="10%">使用通道</td>
		<td width="15%">所属商户</td>
		<td width="15%">操作时间</td>
		<td width="10%">操作员</td>
		<td width="25%">备注</td>
	</tr>
	<s:iterator id="items" value="info.result">
	<tr align="center">
	 	<td bgcolor="#FFFFFF" align="center"><input type="checkbox" name="orderIds" value="<s:property value="#items[0]"/>"/></td>
	 	<td style="font-size: 10px;"><s:property value="#items[2]" /></td>
		<td style="font-size: 10px;"><s:property value="#items[3]" /></td>
		<td style="font-size: 10px;"><s:property value="#items[1]" /></td>
		<td style="font-size: 10px;"><s:property value="#items[5]" />&nbsp;</td>
		<td style="font-size: 10px;"><s:property value="#items[6]" />&nbsp;</td>
		<td style="font-size: 10px;"><s:property value="#items[4]" />&nbsp;</td>
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="getElementById('formu')" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 20px">
                    <tr>
                      <td align="left">
                      <input type="button" value="删除" onclick="delSen()" />
                      </td>
                    </tr>
                  </table>
</div>
</s:form>
