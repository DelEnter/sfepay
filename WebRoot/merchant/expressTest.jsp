<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%String ref = request.getHeader("REFERER");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>下单信息反馈</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<script language="JavaScript" type="text/JavaScript">
</script>
</head>
<body>
	    下单请求已发送！<br/>
      <input type="button" id="backBtn" name="button" class="button_return" value="返回"
      onclick="javascript:window.location='<%=ref%>'" />
</body>
   
   
   