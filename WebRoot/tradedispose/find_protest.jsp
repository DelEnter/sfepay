<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <%@ taglib prefix="pages" uri="/xs-pages"%>
	<%@ include file="../util/calendar.jsp" %>
    <title>拒付查询</title>
	
	<SCRIPT language=JavaScript>
	<!--判断开始是否大于结束时间-->
     function checkTime(){
    	var startTime= document.getElementById("startTime").value;
    	var endTime= document.getElementById("endTime").value;
    	
   		if(startTime>endTime){
   			alert("开始时间大于结束时间!");
   		}else{
   			form1.submit();
   		}
    }
  
</SCRIPT>

  </head>
  
  <body>
   	<h3 align="center">拒付查询</h3>
   
   	<s:form id="form1" action="findProtestDispose" theme="simple" method="post">
   		<table align="center">
	   		
	   		<tr>
	   			<td>商户号</td>
	   			<td>
	   				<s:textfield name="merchant.merno"/>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>流水号</td>
	   			<td>
	   				<s:textfield name="tradeinfo.orderNo"/>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>开始日期</td> 		
		 		<td>
		 			<s:textfield name="startTime" id="startTime" onfocus="calendar()"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<s:textfield name="endTime" id="endTime" onfocus="calendar()"/>
		 		</td>
	   		</tr>
	   		</table>
	   		<table align="center">
	   		<tr align="center">
	   			<td>
	   				<input type="button" value="查询" onclick="checkTime()">
	   			</td>
	   			<td>
	   				<s:reset value="取消"/>
	   			</td>
	   		</tr>
   		</table>
   	
	   	<div align=left> 总金额: <font color=red><s:property value="totalAmout"/> </font>RMB</div>
	   	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width=100% align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	   		<tr bgColor=#cccccc>
	   			<th>商户号</th>
	   			<th>流水号</th>
	   			<th>订单号</th>
	   			<th>日期</th>
	   			<th>币种</th>
	   			<th>交易金额</th>
	   			<th>RMB金额</th>
	   			<th>支付状态</th>
	   			<th>拒付状态</th>
	   			<th>拒付处理时间</th>
	   			<th>操作员</th>
	   			<th>备注</th>
	   		</tr>
   		<s:iterator id="rejectId" value="info.result">
	   		<tr align="center">
	   			<td>
	   				<s:property value="#rejectId[2].merno"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].orderNo"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].merchantOrderNo"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].tradeTime"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[1].moneykindname"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].tradeAmount"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].rmbAmount"/>&nbsp;
	   			</td>
	   			
	   			<td>
	   				<s:property value="states.getStateName(#rejectId[0].tradeState,1)"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="states.getStateName(#rejectId[0].tradeState,3)"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].protestTime"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].protestPerson"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].remark"/>&nbsp;
	   			</td>
	   		</tr>
   		</s:iterator>
   		<tr>
			<td colspan="30">
				<pages:pages value="info" beanName="info"
					formName="getElementById('form1')" />
			</td>
		</tr>
	   	</table>
   </s:form>
  </body>
</html>
