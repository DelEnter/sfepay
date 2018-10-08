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
	<title>风控项控制</title>
</head>
<div align="center" >
	<h3>风控项控制</h3>
</div>
<script language="JavaScript" type="text/JavaScript">
function addRiskItems(){
	
	if(document.getElementById("itemName").value==""&&document.getElementById("remark").value==""){
		alert("至少有一项不能为空！");
		return false;
	}
	if(confirm("确认添加吗？")){
	document.getElementById("addformu").submit();
	}
}
function query(){
	document.getElementById("formu").submit();
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
	window.location="../PaySystem/delRiskItems.action?riskIds="+ids;
	}
}
</script>
<s:form id="addformu" action="addRiskItems" method="post" theme="simple">
<table align="center">
		<tr class=TR_Title>
	 		<td align="right">项目名称：</td>
	 		<td><input type="text" id="itemName" name="riskItemsInfo.itemName" maxlength="20"></td>
	 		<td align="right">项目类型：</td>
	 		<td>
	 		<s:select name="riskItemsInfo.itemType" list="#{'':'--请选择--','1':'国家','2':'区域','3':'网站分类'}" listKey="key" listValue="value"/>
	 		</td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">商户号：</td>
	 		<td><input type="text" id="itemName" name="riskItemsInfo.merchantNo" maxlength="20"></td>
	 		<td align="right">交易网站：</td>
	 		<td><input type="text" id="itemName" name="riskItemsInfo.tradeWeb" maxlength="30"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">备注：</td>
	 		<td colspan="3"><input type="text" id="remark" name="riskItemsInfo.remark"></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="addRiskItems()" value="添加" >
				</td>
	 		</tr>
	</table>
	</s:form>
	<hr/>
	<s:form id="formu" action="getRiskItems" method="post" theme="simple">
	<table align="center">
		<tr class=TR_Title>
	 		<td align="right">项目内容：</td>
	 		<td><input type="text" id="qitemName" name="qitemName" value="<s:property value="qitemName" />" maxlength="20"></td>
	 		<td align="right">项目类型：</td>
	 		<td>
	 		<s:select name="qitemType" list="#{'':'--请选择--','1':'国家','2':'区域','3':'网站分类'}" listKey="key" listValue="value"/>
	 		</td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">商户号：</td>
	 		<td><input type="text" id="qmerchantNo" name="qmerchantNo" value="<s:property value="qmerchantNo" />" maxlength="20"></td>
	 		<td align="right">交易网站：</td>
	 		<td><input type="text" id="qtradeWeb" name="qtradeWeb" value="<s:property value="qtradeWeb" />" maxlength="30"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">备注：</td>
	 		<td colspan="3"><input type="text" id="qremark"  name="qremark" value="<s:property value="qremark" />"></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="query()" value="查询" >
				</td>
				
			</tr>
	</table>
	<br/>
	<br/>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr align="center" bgColor=#cccccc>
		<td width="4%" bgcolor="#FFFFFF" align="center">
  			<input type="checkbox" onclick='chkall("formu",this)' name=chk>
  		</td>
  		<td width="8%">商户号</td>
		<td width="18%">项目内容</td>
		<td width="10%">项目类型</td>
		<td width="18%">网站</td>
		<td width="15%">添加时间</td>
		<td width="9%">操作员</td>
		<td width="22%">备注</td>
	</tr>
	<s:iterator id="items" value="info.result">
	<tr align="center">
	 	<td bgcolor="#FFFFFF" align="center"><input type="checkbox" name="orderIds" value="<s:property value="#items.id"/>"/></td>
	 	<td style="font-size: 10px;"><s:property value="#items.merchantNo" /></td>
		<td style="font-size: 10px;"><s:property value="#items.itemName" />&nbsp;</td>
		<s:if test="%{#items.itemType==1}">
		<td style="font-size: 10px;">国家</td>
		</s:if>
		<s:elseif test="%{#items.itemType==2}">
		<td style="font-size: 10px;">区域</td>
		</s:elseif>
		<s:elseif test="%{#items.itemType==3}">
		<td style="font-size: 10px;">网站分类</td>
		</s:elseif>
		<td style="font-size: 10px;"><s:property value="#items.tradeWeb" /></td>
		<td style="font-size: 10px;"><s:property value="#items.lastDate" />&nbsp;</td>
		<td style="font-size: 10px;"><s:property value="#items.lastMan" />&nbsp;</td>
		<td style="font-size: 10px;"><s:property value="#items.remark" />&nbsp;</td>
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

