<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>网站订单号异常统计</title>
</head>
<div align="center" >
	<h3>网站订单号异常统计</h3>
</div>
<s:form id="formu" action="queryDifWeb" method="post" theme="simple">
<table align="center">
		<tr class=TR_Title>
	 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >
				</td>
	 		</tr>
	</table>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="80%">
	<tr align="center" bgColor=#cccccc>
		<td width="5%"></td>
		<td width="7%">商户号</td>
		<td width="30%">网站</td>
		<td width="17%">商户订单号1</td>
		<td width="17%">商户订单号2</td>
		<td width="12%">交易时间1</td>
		<td width="12%">交易时间2</td>
	</tr>
	<%int i=0; %>
	<s:iterator id="dif" value="difList">
	<%i++; %>
	<tr align="center">
		<td><%=i %></td>
		<td><s:property value="#dif[0]" />&nbsp;</td>
		<td><s:property value="#dif[1]" />&nbsp;</td>
		<td><s:property value="#dif[2]" />&nbsp;</td>
		<td><s:property value="#dif[3]" />&nbsp;</td>
		<td><s:property value="#dif[4]" />&nbsp;</td>
		<td><s:property value="#dif[5]" />&nbsp;</td>
	</tr>
	</s:iterator>
</table>
<script language="javascript" type="text/javascript">
       loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "en");
       loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "en");
         function cleanDate(vid){
            document.getElementById(vid).value="";
        }
</script>
</s:form>

