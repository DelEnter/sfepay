<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>终端管理</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
        window.location="../PaySystem/toTerminalManager.action";
	}
	function add(){ 
	   openWindow('../PaySystem/toTerminalManagerForm','12');
	}
	function update(terminalid){
		openWindow('../PaySystem/toTerminalManagerForm.action?terminalId='+terminalid,'12')
	}
	function openorclose(channelid,onoff){
		openWindow('../PaySystem/terminalonoff.action?terminalId='+channelid+'&onoff='+onoff,'12')
	}
	function openorclosepro(channelid,onoff){
		openWindow('../PaySystem/processonoff.action?terminalId='+channelid+'&onoff='+onoff,'12')
	}
</script>
<s:form name="formu" id="formu" action="toTerminalManager" method="post" theme="simple">
<input type="button" value="新增终端" onclick="add();">
<div align="center">
通道
<s:select name="channelId" list="channelList" listKey="id" listValue="channelName" headerKey="" headerValue="----" />
卡种
<s:select name="creditCardId" list="creditCardList" listKey="id" listValue="cardName" headerKey="" headerValue="----" />
终端号
<input type="input" name="terminalNo" size="15" value="<s:property value='terminalNo'/>"/>
商户号
<input type="input" name="merchantno" size="15" value="<s:property value='merchantno'/>"/>
<input type="submit" value="查询" >
</div>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center">序列</td>
		<td align="center">通道名称</td>
		<td align="center">卡种</td>
		<td align="center">终端号</td>
		<td align="center">商户号</td>
		<td align="center">关联终端号</td>
		<td align="center">账单地址</td>
		<td align="center">是否开通</td>
		<td align="center">操作</td>
		<td align="center">是否自动处理</td>
		<td align="center">操作</td>
		<td align="center">月交易限制笔数</td>
		<td align="center">网关</td>
		<td align="center">银行</td>
		<td align="center">HashCode</td>
		<td align="center">交易接入码</td>
		
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr>
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[0]" /></td>
		<td><s:property value="#p[1]" /></td>
		<td><s:property value="#p[2].terminalNo" /></td>
		<td><s:property value="#p[2].merchantNo" />&nbsp;</td>
		<td><s:property value="#p[2].andterminalNo" />&nbsp;</td>
		<td title="<s:property value="#p[2].billingAddress" />"><s:property value="#p[2].billingAddress" /></td>
		<td align="center">
			<s:if test="#p[2].onoff==1">正在使用</s:if><s:else>停用</s:else>
		</td>
		<td align="center">
			<a href="#" onclick="openorclose(<s:property value="#p[2].id" />,<s:property value="#p[2].onoff" />);" >
				<s:if test="#p[2].onoff==1">停用</s:if><s:else>开通</s:else>
			</a>
			&nbsp;/&nbsp;			<a href="#" onclick="update(<s:property value="#p[2].id" />)">修改</a></td>

			<td align="center">
			<s:if test="#p[2].isauto==1">自动处理</s:if><s:else>停用</s:else>
			</td>
			<td align="center">
			<a href="#" onclick="openorclosepro(<s:property value="#p[2].id" />,<s:property value="#p[2].isauto" />);" >
			<s:if test="#p[2].isauto==1">自动处理</s:if><s:else>开通</s:else>
			</a>
		
		<td><s:property value="#p[2].tradeTimes"/>&nbsp;</td>
		<td><s:property value="#p[2].netstate"/>&nbsp;</td>
		<td><s:property value="#p[2].banktype"/>&nbsp;</td>
		<td><s:property value="#p[2].hashcode"/>&nbsp;</td>
		<td><s:property value="#p[2].authcode"/>&nbsp;</td>
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="forms(0)" />
</div>
<div align="right">
<select name="pagesize" onchange="this.form.submit()">
	<option value="20"
		<s:if test="pagesize==20">
			selected
		</s:if>
	>每页20条</option>
	<option value="30"
		<s:if test="pagesize==30">
			selected
		</s:if>
		 >每页30条</option>
	<option value="40"
	<s:if test="pagesize==40">
			selected
		</s:if>
	>每页40条</option>
	<option value="50"
	<s:if test="pagesize==50">
			selected
		</s:if>
	>每页50条</option>
</select>
</div>
<p align="center">
</p>
</s:form>



<s:form action="importTerminalNo" theme="simple" method ="POST" enctype ="multipart/form-data">
<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="600" align=center 
bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
    	<tr>
			<td width="30%" align="right" >
				<div align="right">请选择文件：</div>
			</td>
			<td align="right" >
				<div align="left"><s:file name="fileName"/></div>
			</td>
		</tr>
		<tr >
		<td width="30%" align="right" >
				<input type="submit" value="上传"/>
			</td>
			<td align="right" >
			</td>
		</tr>
    	
    </table>
</s:form>
