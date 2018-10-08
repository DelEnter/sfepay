<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<head>
	<title>系统菜单</title>
</head>

权限管理

<input type="button" value="权限管理"  onclick="toShowUrl('login.jsp');" />
</p>
<s:iterator id="resourcelist" value="resourcelist">
<s:if test="#resourcelist[0]=='000001'">
<a href="<s:property value='#resourcelist[1]' />"><s:property value="#resourcelist[2]" /> </a>
</s:if>
</p>
</s:iterator>
商户管理
</p>
<s:iterator id="resourcelist2" value="resourcelist">
<s:if test="#resourcelist2.[0]=='000002'">
<a href="<s:property value='#resourcelist2.[1]' />"><s:property value="#resourcelist2.[3]" /> </a>
</s:if>
</p>
</s:iterator>
交易管理
<s:iterator id="resourcelist3" value="resourcelist">
<s:if test="#resourcelist3.[0]=='000003'">
<a href="<s:property value='#resourcelist3.[1]' />"><s:property value="#resourcelist3.[3]" /> </a>
</s:if>
</p>
</s:iterator>

结算管理
<s:iterator id="resourcelist4" value="resourcelist">
<s:if test="#resourcelist4.[0]=='000004'">
<a href="<s:property value='#resourcelist4.[1]' />"><s:property value="#resourcelist4.[3]" /> </a>
</s:if>
</p>
</s:iterator>
对账管理
<s:iterator id="resourcelist5" value="resourcelist">
<s:if test="#resourcelist5.[0]=='000005'">
<a href="<s:property value='#resourcelist5.[1]' />"><s:property value="#resourcelist5.[3]" /> </a>
</s:if>
</p>
</s:iterator>
系统设置
<s:iterator id="resourcelist6" value="resourcelist">
<s:if test="#resourcelist.[0]=='000006'">
<a href="<s:property value='#resourcelist6.[1]' />"><s:property value="#resourcelist6.[3]" /> </a>
</s:if>
</p>
</s:iterator>
客服系统
<s:iterator id="resourcelist7" value="resourcelist">
<s:if test="#resourcelist7.[0]=='000007'">
<a href="<s:property value='#resourcelist7.[1]' />"><s:property value="#resourcelist7.[3]" /> </a>
</s:if>
</p>
</s:iterator>
风控系统
<s:iterator id="resourcelist8" value="resourcelist">
<s:if test="#resourcelist8.[0]=='000008'">
<a href="<s:property value='#resourcelist8.[1]' />"><s:property value="#resourcelist8.[3]" /> </a>
</s:if>
</p>
</s:iterator>
报表系统(统计)
<s:iterator id="resourcelist9" value="resourcelist">
<s:if test="#resourcelist9.[0]=='000009'">
<a href="<s:property value='#resourcelist9.[1]' />"><s:property value="#resourcelist9.[3]" /> </a>
</s:if>
</p>
</s:iterator>
风控系统
<s:iterator id="resourcelist10" value="resourcelist">
<s:if test="#resourcelist10.[0]=='000010'">
<a href="<s:property value='#resourcelist10.[1]' />"><s:property value="#resourcelist10.[3]" /> </a>
</s:if>
</p>
</s:iterator>
系统日志管理
<s:iterator id="resourcelist11" value="resourcelist">
<s:if test="#resourcelist11.[0]=='000011'">
<a href="<s:property value='#resourcelist11.[1]' />"><s:property value="#resourcelist11.[3]" /> </a>
</s:if>
</p>
</s:iterator>
持卡人信息
<s:iterator id="resourcelist12" value="resourcelist">
<s:if test="#resourcelist12.[0]=='0000012'">
<a href="<s:property value='#resourcelist12.[1]' />"><s:property value="#resourcelist12.[3]" /> </a>
</s:if>
</p>
</s:iterator>