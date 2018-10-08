<%@page import="java.text.DecimalFormat"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.ecpss.util.MD5" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%--
 Created by IntelliJ IDEA.
 User: Bluewater
 Date: 2005-7-21
 Time: 20:55:45
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//字符编码
String CharacterEncoding = "UTF-8";
request.setCharacterEncoding(CharacterEncoding);
String ReturnURL = request.getParameter("ReturnURL");
String merchantOrderNo = request.getParameter("merchantOrderNo");
String tradeMoneyType = request.getParameter("tradeMoneyType");
String ordercount =request.getParameter("ordercount");
String responseCode = request.getParameter("responseCode");
String message = request.getParameter("message");
String md5Value = request.getParameter("md5Value");
String remark = request.getParameter("remark");
if(StringUtils.isNotBlank(remark)){
String re[]=remark.split("/");
remark=re[1];
}
%>

<html>


<head>
	<style type='text/css'>
    <!--
    .shade   { height:25px; background-color:#E1E1E1 }
    </style>
	<script language="javascript" >
		function submit(){
			form1.submit();
		}
	</script>
</head>

<body onload="setTimeout('submit()',3000)">
<form name="form1" action="<%=ReturnURL%>" method="post"> 
<s:token />
		<table align="center">
		<span><%=ordercount %></span>
			<tr align="center">
				<td width="60%">
					<h4><b>Processing page</b></h4>
				</td>
			</tr>
			<tr class="shade" align="center">
				<td width="60%"><br>
				</td>
			</tr>
			<%if("88".equals(responseCode)){%>
			<tr align="center">
				<td><br>
					<span style="color:blue;font-size:20px">prompt message:Payment Success!
					</span>
					<br>
				</td>
			</tr>
			<% }else if(StringUtils.isNotBlank(message)||StringUtils.isNotBlank(remark)){ %>
			<tr align="center">
				<td><br>
					<span style="color:red;font-size:20px">prompt message:<%if(message!=null){%>
					<%=message %>
					<%}if(remark!=null){%><%=remark %><%} %>
					</span>
					<%if(responseCode!=null){ %>
					<span style="color:red;margin-left:20px ">Error code:<%=responseCode %></span>
					<% }%>
					<br>
				</td>
			</tr>
			<%}%>
			<tr align="center">
				<td><br>
					<b>Hint: Please wait and do not refresh page in the process of submitting your payment
					</b><br>
				</td>
			</tr>
			<tr align="center">
				<td>
					<b>which may lead to double charge.<br><br></b>
				</td>
			</tr>
			<tr align="center">
				<td>
					You will be directed to the Payment Server.
				</td>
			</tr><br>
			<tr align="center">
				<td class="shade" width="90%">
					<b>Thank you for your support</b>
				</td>
			</tr>
		    <tr>	   
		        <td align="left">
		        <input type="hidden" name="BillNo" value="<%=merchantOrderNo%>">
		        	<input type="hidden" name="Currency" value="<%=tradeMoneyType%>">
		        	<input type="hidden" name="Amount" value="<%=ordercount%>">
		        	<input type="hidden" name="Succeed" value="<%=responseCode%>">
		        	<input type="hidden" name="Result" value="<%=message%>">
		        	<input type="hidden" name="ReturnURL" value="<%=ReturnURL%>">
		        	<input type="hidden" name="MD5info" value="<%=md5Value%>"> 
		        </td>
		     </tr>
		     
		</table>
	</form>
</body>

</html>

