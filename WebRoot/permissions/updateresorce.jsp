<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>修改资源</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function updateResource(f) {		
			goFormWindow(f,"../PaySystem/updateResouce");
	}
</script>
<s:form action="addResouce" id="form1" namespace="/PaySystem">
<div id="title" value="修改资源"/>
<div id="resizetable" width="400" height="200">
  <div class="windows">
  <input type="hidden" name="showresouce.id" value="<s:property value='showresouce.id'/>" />
     <div class="left">所属模块：</div>
     <div class="right"><select name="showresouce.fatherNumber" value="<s:property value='showresouce.fatherNumber'/>" >
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
       </select></div>
     <div class="clear">&nbsp;</div>
     <div class="left">资源名称：</div>
     <div class="right"><input type="text" name="showresouce.menuName" value="<s:property value='showresouce.menuName'/>" /></div>
     <div class="clear">&nbsp;</div>
     <div class="left">资源URL：</div>
     <div class="right"><input type="text" name="showresouce.resourceUrl" value="<s:property value='showresouce.resourceUrl'/>"/></div>
     <div class="clear">&nbsp;</div>
     <div class="left">&nbsp;</div>
     <div class="right"><input type="button" onClick="updateResource(this.form);" value="修改资源" class="windows_icon1"/></div>
  </div>
</div>
</s:form>