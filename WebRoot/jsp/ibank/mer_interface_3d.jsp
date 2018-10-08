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
String ReturnURL = request.getParameter("Send_Url");
String Hash = request.getParameter("HASH");
String tokenid = request.getParameter("token_id");

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

<body onload="setTimeout('submit()',20000)">
<form name="form1" action="<%=ReturnURL%>" method="post"> 
		<table align="center">
			
			<tr align="center">
				<td width="60%">
					<h4><b>Processing page</b></h4>
				</td>
			</tr>
			<tr class="shade" align="center">
				<td width="60%"><br>
				</td>
			</tr>
			
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
		        <input type="hidden" name="HASH" value="<%=Hash%>">
		        	<input type="hidden" name="token_id" value="<%=tokenid%>">

		        </td>
		     </tr>
		     
		</table>
	</form>
</body>

</html>

