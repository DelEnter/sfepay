<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="../util/checkAll.jsp" %>
    <LINK href="../css/head.css" type=text/css rel=stylesheet>
    <%@ taglib prefix="pages" uri="/xs-pages"%>
    <%@ include file="../util/calendar.jsp" %>
    
    <title>拒付处理</title>
   <SCRIPT language=JavaScript>
	<!--判断开始是否大于结束时间-->
    function checkTime(){
    	var startTime= document.getElementById("startTime").value;
    	var endTime= document.getElementById("endTime").value;
    	
   		if(startTime>endTime){
   			alert("开始时间大于结束时间!");
   		}else{
   			document.getElementById("form1").submit();
   		}
    }
    
    <!-- 检验是否选上需要处理的选项 -->
		<!-- 检验是否选上需要处理的选项 -->
	function check(){
		var workorderObjectNos = document.getElementsByName('disposeId');
		var gets = new Array();
		var k = 0;
		var result = 0;
		for(var i=0; i<workorderObjectNos.length; i++){
			if(workorderObjectNos[i].checked){
			    gets[k] = workorderObjectNos[i].value;
			    result =  gets[k];
   				k++;
			}
 		}
 		if(result==0){
			alert("请选上拒付的选项！");
		}else{
			form1.action="protestDispose";
			form1.submit();
		}
	}
	</SCRIPT>
  </head>
 
  <body>
  	<center>
  		<a href="findProtestDispose.action">查询拒付数据</a>
    	<h3>拒付处理</h3>
    	<s:form id="form1" action="findNoProtest" theme="simple" method="post">
   		
   		<div align=center>
   			<font color="red">
				<s:fielderror/>
			</font>
   		</div>
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
	   				<input type="button" value="确定" onclick="checkTime()">
	   			</td>
	   			<td>
	   				<s:reset value="取消"/>
	   			</td>
	   		</tr>
   		</table>
   	
	   	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width=80% align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	   		<tr bgColor=#cccccc>
	   			<th><input type="checkbox" onclick='chkall("form2",this)' name=chk()> </th>
				<th>商户号</th>
	   			<th>流水号</th>
	   			<th>订单号</th>
	   			<th>交易金额</th>
	   			<th>交易日期</th>
	   			<th>是否退款</th>
	   			<th>是否拒付</th>
	   			<th>是否冻结</th>
	   			<th>备注</th>
	   		</tr>
   		<s:iterator id="rejectId" value="info.result">
	   		<tr align=center>
	   			<td>
					<input type="checkbox"  name="disposeId" id="disposeId" value="<s:property value="#rejectId[0].id"/>">
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[1].merno"/>&nbsp;
	   				
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].orderNo"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].merchantOrderNo"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].tradeAmount"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].tradeTime"/>&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="states.getStateName(#rejectId[0].tradeState,2)" />&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="states.getStateName(#rejectId[0].tradeState,3)" />&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="states.getStateName(#rejectId[0].tradeState,4)" />&nbsp;
	   			</td>
	   			<td>
	   				<s:property value="#rejectId[0].remark"/>&nbsp;
	   			</td>
	   		</tr>
   		</s:iterator>
   		</table>
   		<table align="center">
	   		<tr>
	   			<td>
	   				<input type="button" value="确定拒付" onclick="check()">
	   			</td>
	   			<td>
	   				<s:reset value="取消"/>
	   			</td>
	   		</tr>
	   		<tr bgColor=#ffffff>
				<td colspan="30">
					<pages:pages value="info" beanName="info"
						formName="getElementById('form1')" />
				</td>
			</tr>
	   	</table>
	 </s:form>
    </center>
  </body>
</html>
