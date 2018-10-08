<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>migs通道统计</title>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
</head>

<table width="33%"  border="1" align="left" bordercolor="#00FF00">
	<tr>
		<td>商户号</td>
		<td>总交易</td>
		
	</tr>	
	<s:iterator id="slist" value="statisticsList" >
		<tr>
		  <td><s:property value="#slist[0]"/></td>
		  <td><s:property value="#slist[1]"/></td>
		</tr>
	</s:iterator>
</table>
<table width="33%"  border="1" align="right" bordercolor="#FF0000">
	<tr>
	<td>商户号</td>
	<td>日交易</td>
	</tr>	
	<s:iterator id="dlist" value="dayStatisticsList" >
		<tr>
		  <td><s:property value="#dlist[0]"/></td>
		  <td><s:property value="#dlist[1]"/></td>
		</tr>
	</s:iterator>
</table>
<table width="33%"  border="1" align="right" bordercolor="#FF0022">
<tr>
<td>商户号</td>
<td>月交易</td>
</tr>	
<s:iterator id="mlist" value="monthStatisticsList" >
	<tr>
	  <td><s:property value="#mlist[0]"/></td>
	  <td><s:property value="#mlist[1]"/></td>
	</tr>
</s:iterator>
</table>

