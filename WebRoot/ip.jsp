<%@ page language="java"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<%
String ip1 = request.getHeader("X-Forwarded-For");
String ip2 = request.getHeader("Proxy-Client-IP");
String ip3 = request.getHeader("WL-Proxy-Client-IP");
String ip4 = request.getHeader("HTTP_CLIENT_IP");
String ip5 = request.getHeader("HTTP_X_FORWARDED_FOR");
String ip6 = request.getRemoteAddr();
%>
<body>
<div><span style="font-size: 20px">ip1:<%=ip1 %></span><br/>
<span style="font-size: 20px">ip2:<%=ip2 %></span><br/>
<span style="font-size: 20px">ip3:<%=ip3 %></span><br/>
<span style="font-size: 20px">ip4:<%=ip4 %></span><br/>
<span style="font-size: 20px">ip5:<%=ip5 %></span><br/>
<span style="font-size: 20px">ip6:<%=ip6 %></span><br/>
</div>
</body>
</html>