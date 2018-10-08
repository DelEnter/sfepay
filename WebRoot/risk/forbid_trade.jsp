<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../include/dialog.jsp"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script language="JavaScript" src="../js/util.js"></script>
		<script language="JavaScript" type="text/JavaScript">
		function reloadPage(){
			window.location="toForbidTrade.action";
		}
	</script>
		<title>重复交易风险分析</title>
		<SCRIPT language=JavaScript>
		<!--判断开始是否大于结束时间-->
	     function checkTime(){
	    	var startTime= document.getElementById("startTime").value;
	    	var endTime= document.getElementById("endTime").value;
	    	
	   		if(startTime>endTime){
	   			alert("开始时间大于结束时间!");
	   		}else{
	   			form1.action="toForbidTrade.action";
	   			form1.submit();
	   		}
	    }
	  
	</SCRIPT>
		<link rel="stylesheet" type="text/css" href="../css/head.css">
		<%@ include file="../util/calendar.jsp"%>
	</head>

	<body>
		<center>
			<h3>
				重复交易风险分析
			</h3>
			<s:form id="form1" action="toForbidTrade" theme="simple" method="post">
				<table align="center">
					<!--  <tr>
						<td>
							开始日期
						</td>
						<td>
							<s:textfield name="startTime" id="startTime" onfocus="calendar()" />
						</td>
						<td>
							结束日期
						</td>
						<td>
							<s:textfield name="endTime" id="endTime" onfocus="calendar()" />
						</td>
					</tr>-->
					<tr>
						<td>
							IP 
						</td>
						<td>
							<s:textfield name="risk.ip"/>
						</td>
						<td>
							Email
						</td>
						<td>
							<s:textfield name="risk.email"/>
						</td>
					</tr>
					<tr>
						<td>
							卡号
						</td>
						<td>
							<s:textfield name="risk.cardno"/>
						</td>
						<td>
							cookie
						</td>
						<td>
							<s:textfield name="risk.cookie"/>
						</td>
					</tr>
					<tr>
						<td>
							IP
						</td>
						<td>
							<s:checkbox name="card.ip"/>
						</td>
						<td>
							卡号
						</td>
						<td>
							<s:checkbox name="card.cardNo"/>
						</td>
						<td>
							邮箱
						</td>
						<td>
							<s:checkbox name="card.email"/>
						</td>
						<td>
							cookie
						</td>
						<td>
							<s:checkbox name="card.cookie"/>
						</td>
					</tr>
					
					<tr>
						<td>
							<!--  <input type="button" value="确定" onclick="checkTime()">-->
							<s:submit value="查询"/>
						</td>
						<td>
							<s:reset value="取消"></s:reset>
						</td>
					</tr>
				</table>
				<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width=80%
					align=center bgColor=#ffffff borderColorLight=#000000 border=1
					height="10">
					<tr bgColor=#cccccc align="center">
						<td>
							IP
						</td>
						<td>
							卡号
						</td>
						<td>
							邮箱
						</td>
						<td>
							cookie
						</td>
						<td>
							重复次数
						</td>
	
						<td>
							处理方式
						</td>
					</tr>
					<s:iterator id="listId" value="info.result">
						<tr align=center>
							<td>
								<s:property value="#listId[0]" />
								&nbsp;
							</td>
							<td>
								<s:property value="#listId[1]" />
								&nbsp;
							</td>
							<td>
								<s:property value="#listId[2]" />
								&nbsp;
							</td>
							<td>
								<s:property value="#listId[3]" />
								&nbsp;
							</td>
							<td>
								<a
									href="forbidTradeList.action?card.ip=<s:property value="#listId[0]"/>&card.cardNo=<s:property value="#listId[1]"/>&card.email=<s:property value="#listId[2]"/>&card.cookie=<s:property value="#listId[3]"/>"><s:property
										value="#listId[4]" />&nbsp;</a>
							</td>
							<td>
								<input type="button" value="列为黑卡"
									onclick="openWindow('backCardMake.action?card.ip=<s:property value="#listId[0]"/>&card.cardno=<s:property value="#listId[1]"/>&card.email=<s:property value="#listId[2]"/>&card.cookie=<s:property value="#listId[3]"/>')" />
								<input type="button" value="列为高风险卡"
									onclick="openWindow('riskCardMake.action?card.ip=<s:property value="#listId[0]"/>&card.cardno=<s:property value="#listId[1]"/>&card.email=<s:property value="#listId[2]"/>&card.cookie=<s:property value="#listId[3]"/>')" />
							</td>
	
						</tr>
					</s:iterator>
				</table>
				<table width=100%>
					<tr>
						<td>
							<pages:pages value="info" beanName="info"
								formName="getElementById('form1')" />
						</td>
					</tr>
				</table>
			</s:form>
		</center>
	</body>
</html>
			