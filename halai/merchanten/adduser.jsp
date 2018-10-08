<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>Create New Operator</title>
    <link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addOpera(f) {		
			f.submit();
			 window.dialogArguments.location.reload(); 
	}
</script>  
  	<base target="_self"> 
<div id="title" value="Create New Operator"/>
<div id="resizetable" width="400" height="150" />
<div class="windows">
  <s:form action="addOperatorEn" id="form8" namespace="/merchant">
  
   <div class="left">Name：</div>
   <div class="right"><input type="text" name="shopOpera.userName" class="text_input_02" /></div>
   <div class="clear"></div>
   
   <div class="left">Password：</div>
   <div class="right"><input type="password" name="shopOpera.password" class="text_input_02" /></div>
   <div class="clear"></div>
   <div class="left">Confirm Password：</div>
   <div class="right"><input type="password" class="text_input_02" /></div>
   
   <div class="clear"></div>
   <div class="left">&nbsp;</div>
   <div class="right">
      <input type="submit"  value="Create New Operator" class="button_01"/>
   </div>
  </s:form>
</div>
</div>