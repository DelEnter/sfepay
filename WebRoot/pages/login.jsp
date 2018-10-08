<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<head>
	<title>登陆页面</title>
</head>
<s:form action="sample" id="form1" namespace="/sample" theme="simple">
<s:hidden name="method" value="login"/>
用户名：<input type="text" name="loginUser.userName"/><br>
密码：<input type="text" name="loginUser.password"/><br>
<s:submit value="提交"></s:submit><br>
<input type="button" value="弹出窗口" onclick="window.open('<%=request.getContextPath()%>/index.jsp?pageStyle=popwin')">
<br>用户列表<br>

</s:form>
<s:form action="channel" id="form2" namespace="/template" theme="template">
<s:hidden name="method" value="getSysMenu"/>
<s:submit value="提交"></s:submit>
</s:form>