<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
 
<html>
<head>
	<title>添加公告</title>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
	<script language="JavaScript" src="../js/util.js"></script>
</head> 
    <script language="JavaScript" type="text/JavaScript">
	function addAffiche(f) {		
			//document.getElementById("myForms").submit();
			goFormWindow(f,"../PaySystem/addAfficheManager");
	}
</script> 
  <body>
<s:form action="addAfficheManager" id="myForms" namespace="/PaySystem" theme="simple">

<div id="title" value="新增公告"/>
<div id="resizetable" width="400" height="200">
<div class="windows">
   <div class="left">公告内容：</div>
   <div class="right3"><!--<s:textfield name="am.afficheContext" id="afficheContext"></s:textfield> -->
   <textarea name="am.afficheContext" id="afficheContext" class="input_text" ></textarea>
   </div>
   <div class="left">链接地址：</div>
   <div class="right"><s:textfield name="am.url" id="url" ></s:textfield></div>
   <div class="clear">&nbsp;</div>
   
   <div class="left">&nbsp;</div>
   <div class="right">
      <input type="button" onClick="addAffiche(this.form);" value="添加公告" class="windows_icon1" />
   </div>

</div>
	
</s:form>
</body>
</html>