<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>新增资源</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addResource(f) {
			goFormWindow(f,"../PaySystem/addResouce");
	}
</script>

<div id="title" value="新增资源"/>
<div id="resizetable" width="400" height="200">
<div class="windows">
<s:form action="addResouce" id="form1" namespace="/PaySystem">
   <div class="left">所属模块：</div>
   <div class="right">
      <select name="resouce.fatherNumber" >
                <option value="000001">权限管理</option>
                <option value="000002">商户管理</option>
                <option value="000003">交易管理</option>
                <option value="000004">结算管理</option>
                <option value="000005">对账管理</option>
                <option value="000006">系统设置</option>
                <option value="000007">客服系统</option>
                <option value="000008">风控系统</option>
                <option value="000009">报表系统(统计)</option>
                <option value="000010">风控系统</option>
                <option value="000011">系统日志管理</option>
                <option value="000012">持卡人信息</option>
       </select>
    </div>
    <div class="clear">&nbsp;</div>
    <div class="left">资源名称：</div>
    <div class="right"><input type="text" name="resouce.menuName"/></div>
    <div class="clear">&nbsp;</div>
    <div class="left">资源URL：</div>
    <div class="right"><input type="text" name="resouce.resourceUrl"/></div>
    <div class="clear">&nbsp;</div>
    <div class="left">&nbsp;</div>
    <div class="right"><input type="button" onClick="addResource(this.form);" value="新增资源"/></div>
</s:form>
</div>