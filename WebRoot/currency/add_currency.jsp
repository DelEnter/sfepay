<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>


<html>
  <head>
    <title>添加币种</title>
    <%@ include file="../util/calendar.jsp" %>
    <script language="JavaScript" src="../js/util.js"></script>
    <%@ include file="../include/dialog.jsp"%>
     <script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {		
			goFormWindow(f,"addCurrencyAction");
		}
	</script>  
  </head>
  
  <body>
      <div id="title" value="添加币种" />
     <div id="resizetable" width="300" height="200" style=" width:300px; height:300px; text-align:center">
	    <s:form action="addCurrencyAction" theme="simple">
     
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
		    	<tr>
		    		<td>币种编号</td>
		    		<td>
		    			<!--<s:select name="moneykind.moneykindno" list="#{'1':'USD','2':'EUR','3':'CNY','4':'GBP','5':'HKD','6':'JPY','7':'AUD','8':'NOK','10':'Dollar','11':'CAD','12':'SEK','13':'DKK'}" listKey="key" listValue="value"/>
		    		-->
		    		<s:textfield name="moneykind.moneykindno"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>币种名称</td>
		    		<td>
		    			<s:textfield name="moneykind.moneykindname"></s:textfield>
		    		</td>
		    	</tr>
		    </table>
		
            <input type="button" onClick="addCourse(this.form);" value="添加汇率" />
	    </s:form>
	    <div>
    		<a href="findCurrencyAction.action">返回</a>
    	</div>
      </div>
  </body>
</html>
