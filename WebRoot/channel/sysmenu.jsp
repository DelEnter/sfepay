<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<head>
	<title>登陆页面</title>
</head>

<s:iterator id="resourcelist" value="resourcelist" >
<s:property value="#resourcelist.menuName" />
</p>
</s:iterator>
