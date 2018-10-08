<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>列为高风险卡</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<script language="JavaScript" type="text/JavaScript">
		function addRiskCard() {		
			document.getElementById("form1").action="riskCardMake";
			document.getElementById("form1").submit();
		}
		function delRiskList(id) {	
			if(confirm("确认删除吗？")){
			window.location="../PaySystem/delRiskCardMake.action?risk.id="+id;
			}
		}
	</script>
  </head>
  
  <body>
  	<center>
  		<h3>列为高风险卡</h3>
  		<s:form action="toRiskCardMake" method="post" theme="simple" name="form1" id="form1">
	  	<table align="center">
			<tr class=TR_Title>
	  				<td>ip</td>
	  				<td colspan="2">
	  					<s:textfield name="risk.ip"/>
	  				</td>
	  				<td>邮箱</td>
	  				<td colspan="2">
	  					<s:textfield name="risk.email"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>卡号</td>
	  				<td colspan="2">
	  					<s:textfield name="risk.cardno"/>
	  				</td>
	  				<td>cookie</td>
	  				<td colspan="2">
	  					<s:textfield name="risk.cookie"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>备注</td>
	  				<td colspan="2">
	  					<s:textfield name="risk.remark"/>
	  				</td>
	  				<td>交易网站</td>
	  				<td colspan="2">
	  					<s:textfield name="risk.tradeUrl"/>
	  				</td>
	  			</tr>
	  			<tr class=font-align>
				<td colspan="3" align="center"><br>
					<input type="button" value="列为风险卡" onclick="addRiskCard()" >
				</td>
				<td align="center"><br>
					<input type="submit" value="搜  索" >
				</td>
			</tr>
	  		</table>
	  <TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="80%">
		<tr align="center" bgColor=#cccccc>
			<td width="20%">卡号</td>
			<td width="20%">email</td>
			<td width="20%">Ip</td>
			<td width="15%">cookie</td>
			<td width="15%">备注</td>
			<td width="10%">操作</td>
		</tr>
		<s:iterator id="riskList" value="info.result">
		<tr align="center">
		<td><s:property value="#riskList.cardno" /></td>
		<td><s:property value="#riskList.email" /></td>
		<td><s:property value="#riskList.ip" /></td>
		<td><s:property value="#riskList.cookie" /></td>
		<td><s:property value="#riskList.remark" /></td>
		<td><a href="#" onclick="delRiskList('<s:property value="#riskList.id" />')">删除</a></td>
		</tr>
		</s:iterator>
	</table>
	<div align="center">
	<pages:pages value="info" beanName="info" formName="getElementById('form1')" />
	</div>
  		</s:form>
  		<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("成功列为高风险卡!");
		}	
		if(temflag==2){
			alert("成功删除黑卡!");
		}
	</script>	
  	</center>&nbsp;
  </body>
</html>
