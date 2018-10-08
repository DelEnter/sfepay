<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <title>修改币种</title>

  </head>
     <script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {		
			goFormWindow(f,"updateCurrencyAction");
		}
	</script>  
  <body>
  	<center>
     <div id="title" value="修改币种" />
     <div id="resizetable" width="300" height="200" style=" width:300px; height:300px; text-align:center">
	    <s:form action="updateCurrencyAction" theme="simple">
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
		    	<tr>
		    		<td>币种编号</td>
		    		<td>
		    			<s:hidden name="moneykind.id"></s:hidden>
		    			<s:textfield name="moneykind.moneykindno" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>币种名称</td> 
		    		<td>
		    			<s:textfield name="moneykind.moneykindname" />
		    		</td>
		    	</tr>
		    </table>
		    <input type="button" onClick="addCourse(this.form);" value="修改" />
	    </s:form>
	    <div>
    		<a href="findCurrencyAction.action">返回</a>
    	</div>
    </div>
    
  </body>
</html>
