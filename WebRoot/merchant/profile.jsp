<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的资料</title>
    <meta name="pragma" content="no-cache">
    <meta name="cache-control" content="no-cache">
    <meta name="expires" content="0">    
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="This is my page">    	

  </head>
  
  <body>
    <h1>profile</h1>
  </body>
</html>
