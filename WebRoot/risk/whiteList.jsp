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
	<title>白名单</title>
</head>
<div align="center" >
	<h3>白名单</h3>
</div>
<script language="JavaScript" type="text/JavaScript">
function addWhiteList(){
	if(document.getElementById("whitename").value==""||document.getElementById("whitetype").value==""||document.getElementById("risktype").value==""){
		alert("前3项不能为空！");
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
	window.location="../PaySystem/delWhiteList.action?riskIds="+ids;
	}
}
</script>
<s:form id="addformu" action="addWhiteList" method="post" theme="simple">
<table align="center">
		<tr class=TR_Title>
	 		<td align="right">白名单内容：</td>
	 		<td><input type="text" id="whitename" name="whitelist.whitename" maxlength="20"></td>
	 		<td align="right">所属类型：</td>
	 		<td>
	 		<s:select id="whitetype" name="whitelist.whitetype" list="#{'':'--请选择--','1':'商户号','2':'卡号','3':'IP','4':'EMAIL','5':'网址','6':'国家'}" listKey="key" listValue="value" />
	 		</td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">风控等级：</td>
	 		<td>
	 		<s:select id="risktype" name="whitelist.risktype" list="#{'':'--请选择--','1':'第一等级','2':'第二等级','3':'第三等级','4':'第四等级','5':'第五等级','6':'第六等级','7':'第七等级'}" listKey="key" listValue="value"/>
	 		</td>
	 		<td align="right">备注：</td>
	 		<td colspan="3"><input type="text" id="remark" name="whitelist.remark"></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="addWhiteList()" value="添加" >
				</td>
	 		</tr>
	</table>
	</s:form>
	<table cellSpacing="0" cellPadding="0" align="center" >
	<tr><td olspan="3"><span style="font-size: 14px;color:blue">*风险等级解析：</td></tr>
	<tr>
	<td>&nbsp;&nbsp;*第一等级：不走风控。</td>
	<td>*第四等级：高优先级+不同网站(03)。</td>

	</tr>
	<tr>
	<td>&nbsp;&nbsp;*第二等级：走01or04。</td>
	<td>*第五等级：高优先级+敏感字(00)。</td>
	</tr>
	<tr>
	<td>&nbsp;&nbsp;*第三等级：高优先级+新卡(02)。</td>
	<td>&nbsp;&nbsp;*第六等级：交易失败不待处理。</td>
	</tr>
	</table>
	<hr/>
	<s:form id="formu" action="getWhiteList" method="post" theme="simple">
	<table align="center" >
		<tr class=TR_Title>
	 		<td align="right">白名单内容：</td>
	 		<td><input type="text" id="qwhiteName" name="qwhiteName" value="<s:property value="qwhiteName" />" maxlength="20"></td>
	 		<td align="right">所属类型：</td>
	 		<td>
	 		<s:select name="qwhiteType" list="#{'':'--请选择--','1':'商户号','2':'卡号','3':'IP','4':'EMAIL','5':'网址','6':'国家'}" listKey="key" listValue="value"/>
	 		</td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">风控等级：</td>
	 		<td>
	 		<s:select name="qriskType" list="#{'':'--请选择--','1':'第一等级','2':'第二等级','3':'第三等级','4':'第四等级','5':'第五等级','6':'第六等级','7':'第七等级'}" listKey="key" listValue="value"/>
	 		</td>
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
		<td width="5%" bgcolor="#FFFFFF" align="center">
  			<input type="checkbox" onclick='chkall("formu",this)' name=chk>
  		</td>
  		<td width="25%">白名单内容</td>
		<td width="10%">所属类型</td>
		<td width="14%">风控等级</td>
		<td width="15%">添加时间</td>
		<td width="10%">操作员</td>
		<td width="21%">备注</td>
	</tr>
	<s:iterator id="items" value="info.result">
	<tr align="center">
	 	<td bgcolor="#FFFFFF" align="center"><input type="checkbox" name="orderIds" value="<s:property value="#items.id"/>"/></td>
	 	<td style="font-size: 10px;"><s:property value="#items.whitename" /></td>
		<td style="font-size: 10px;"><s:if test="%{#items.whitetype==1}">
		商户号
		</s:if>
		<s:elseif test="%{#items.whitetype==2}">
		卡号
		</s:elseif>
		<s:elseif test="%{#items.whitetype==3}">
		IP
		</s:elseif>
		<s:elseif test="%{#items.whitetype==4}">
		EMAIL
		</s:elseif>
		<s:elseif test="%{#items.whitetype==5}">
		网址
		</s:elseif>
		<s:elseif test="%{#items.whitetype==6}">
		国家
		</s:elseif>
		</td>
		<td style="font-size: 10px;">
		<s:if test="%{#items.risktype==1}">
		第一等级
		</s:if>
		<s:elseif test="%{#items.risktype==2}">
		第二等级
		</s:elseif>
		<s:elseif test="%{#items.risktype==3}">
		第三等级
		</s:elseif>
		<s:elseif test="%{#items.risktype==4}">
		第四等级
		</s:elseif>
		<s:elseif test="%{#items.risktype==5}">
		第五等级
		</s:elseif>
		<s:elseif test="%{#items.risktype==6}">
		第六等级
		</s:elseif>
		<s:elseif test="%{#items.risktype==7}">
		第七等级
		</s:elseif>
		</td>
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

