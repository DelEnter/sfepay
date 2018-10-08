<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  	<%@ include file="../include/dialog.jsp"%>
	<script language="JavaScript" src="../js/util.js"></script>
  	<%@ include file="../util/calendar.jsp" %>
    <title>添加结算汇率</title>
  </head>
     <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
		goFormWindow(f,"saveBalanceRateAction");
	}
</script>  
  
  <body>
    <div id="title" value="添加结算汇率" />
     <div id="resizetable" width="300" height="200" style=" width:300px; height:300px; text-align:center">
    	
    	<s:form action="saveBalanceRateAction" method="post" theme="simple">
	    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
				<tr>
					
					<td>币种</td>
					<td>
						<s:select name="rate.moneykindno" list="list" listKey="id" listValue="moneykindname"/>
		   			</td>
				</tr>
				<tr>
					<td>结算汇率</td>
					<td><s:textfield name="rate.rate"/></td>
				</tr>
				<tr>
					<td>结算汇率执行时间</td>
					<td><s:textfield name="excuteDate" id="rate.executetime" onfocus="calendar()"/></td>
				</tr>
			</table>
			<input type="button" onClick="addCourse(this.form);" value="添加结算汇率" />
		</s:form>
		<a href="findBalanceRateAction.action">返回</a>
     </div>
  </body>
</html>
