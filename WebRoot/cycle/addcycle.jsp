<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>新增操作员</title>
    <link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCycle(f) {		
			f.submit();
	}
</script>  
  	<base target="_self"> 
<div id="title" value="新增划款日"/>
<div id="resizetable" width="400" height="150" />
<div class="windows">
  <s:form action="addCycle" id="form8" namespace="/PaySystem">
  
   <div class="left">商户号：</div>
   <div class="right"><input type="text" name="settlementCycleTime.merchant" class="text_input_02" /></div>
   <div class="clear"></div>
   
   <div class="left">划款日：</div>
   <div class="right"><input type="text" name="settlementCycleTime.cycleTime" class="text_input_02" /></div>
   <div class="clear"></div>
   
   <div class="clear"></div>
   <div class="left">&nbsp;</div>
   <div class="right">
      <input type="submit"  value="新增划款日" class="button_01"/>
   </div>
  </s:form>
</div>
</div>