<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>

<html>
	<head>
		<title>修改公告</title>
        <link href="../css/other.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="../js/util.js"></script>
	</head>
	<script language="JavaScript" type="text/JavaScript">
	function updateAffiche(f) {		
			//document.getElementById("myForm").submit();
			goFormWindow(f,"../PaySystem/updateAfficheManager");
	}
	
</script>
	<body>
		<s:form action="updateAfficheManager" id="myForm" namespace="/PaySystem"
			theme="simple">
			<input type="hidden" name="iid" value="<s:property value="iid" />">
            <div id="title" value="修改公告"/>
			<div id="resizetable" width="400" height="200">
            <div class="windows">
  			 <div class="left">公告内容：</div>
   		     <div class="right"><s:textfield name="am.afficheContext" id="afficheContext"></s:textfield></div>
  			 <div class="clear">&nbsp;</div>
             
             <div class="left">公告时间:</div>
   		     <div class="right"><!-- 
					<s:textfield name="am.affichedate"></s:textfield> -->
					<s:date name="am.affichedate"/></div>
  			 <div class="clear">&nbsp;</div>
             
             <div class="left">链接地址：</div>
   		     <div class="right"><s:textfield name="am.url" id="url"></s:textfield></div>
  			 <div class="clear">&nbsp;</div>
  			 
             <div class="left">&nbsp;</div>
   			<div class="right">
               <input type="button" onClick="updateAffiche(this.form);" value="修改公告" class="windows_icon1" />
  			 </div>

</div>
			
		</s:form>
	</body>
</html>