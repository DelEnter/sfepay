<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ include file="../util/checkAll.jsp" %>
    <LINK href="../css/head.css" type=text/css rel=stylesheet>
    <%@ taglib prefix="pages" uri="/xs-pages"%>
    <%@ include file="../util/calendar.jsp" %>
    <title>解冻处理</title>
    
	<SCRIPT language=JavaScript>
	
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
			alert("请选上冻结的选项！");
		}else{
			form1.action="thaw"
			form1.submit();
		}
	}
</SCRIPT>
  </head>
  
  <body>
  	<center>
  		<h3>解冻处理</h3>
  		<s:form id="form1" action="findThawAuditing.action" theme="simple" method="post">
	   		<table>
		   		<tr>
		   			<td>流水号</td>
		   			<td>
		   				<s:textfield name="tradeinfo.orderNo" id="tradeinfo.orderNo"/>
		   			</td>
		   			<td>商户号</td>
		   			<td>
		   				<s:textfield name="merchant.merno" id="tradeinfo.merchantNo"/>
		   			</td>
		   			<td>
		   				<s:submit value="查询"></s:submit>
		   			</td>
		   		</tr>	   		
		   	</table>
		   	<br>
		   	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
		   		<tr bgColor=#cccccc>
		   			<th><input type="checkbox" onclick='chkall("form1",this)' name=chk> </th>
					<th>商户号</th>
		   			<th>流水号</th>
		   			<th>商户订单号</th>
		   			<th>交易金额</th>
		   			<th>交易日期</th>
		   			<th>备注</th>
		   		</tr>
	   		<s:iterator id="freezeId" value="info.result">
		   		<tr align=center>
		   			<td>
						<input type="checkbox" name="disposeId" id="disposeId" value="<s:property value="#freezeId[0].id"/>">
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[1].merno"/>&nbsp;
		   				
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].orderNo"/>&nbsp;
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].merchantOrderNo"/>&nbsp;
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].tradeAmount"/>&nbsp;
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].tradeTime"/>&nbsp;
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].remark"/>&nbsp;
		   			</td>
		   		</tr>
	   		</s:iterator>
		   	</table>
		  	<table align="center">
		   		<tr>
		   			<td>
		   				<input type="button" value="解冻" onclick="check()">
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
  	<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("解冻成功!");
		}else if(temflag==2){
			alert("解冻失败!");
		}		
	</script>	
  </body>
</html>
