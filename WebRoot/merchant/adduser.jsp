<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>新增操作员</title>
    <link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addOpera(f) {		
			f.submit();
			 window.dialogArguments.location.reload(); 
	}
</script>  
  	<base target="_self"> 
<div id="title" value="新增操作员"/>
<div id="resizetable" width="400" height="150" />
<div class="windows">
  <s:form action="addOperator" id="form8" namespace="/merchant">
  
   <div class="left">用户名：</div>
   <div class="right"><input type="text" name="shopOpera.userName" id="username" class="text_input_02" /> <s:property value="messageAction"/></div>
   <div class="clear"></div>
   
   <div class="left">密码：</div>
   <div class="right"><input type="password" name="shopOpera.password" class="text_input_02" /></div>
   <div class="clear"></div>
   <div class="left">确认密码：</div>
   <div class="right"><input type="password" class="text_input_02" /></div>
   
   <div class="clear"></div>
   <div class="left">&nbsp;</div>
   <div class="right">
      <input type="submit"  value="新增操作员" class="button_01"/>
   </div>
  </s:form>
</div>
</div>