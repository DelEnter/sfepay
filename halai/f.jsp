<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript">
    	function forward(){
    		document.getElementById("f2").submit();
    		//var url = document.getElementById("url").value;
			//window.open(url,"ACS",
			//			"height=400,width=660");

			
    	}
    </script>
    <title>跳转提示</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body onload="forward()">
    页面已经跳转向发卡行3D验证页面<br>
    <form action="forward" id="f1" method="post">
    	<input type="hidden" name="transInfo.id" value="${transInfo.id}"/>
    	<input type="hidden" name="transInfo.time" value="2" />
    </form>
    <input type="hidden" id="url" value="${transInfo.acs_url}"/>
    
    <form action="<%= session.getAttribute("url")%>" id="f2" method="post">
 
    	<input type="hidden" name="PaReq" value="<%= session.getAttribute("PaReq")%>"/>
    	<input type="hidden" name="TermUrl" value="<%= session.getAttribute("turl")%>"/>
    	<input type="hidden" name="MD" value="<%= session.getAttribute("xid")%>"/>
    </form>
  </body>
  <% session.invalidate(); %>
</html>
