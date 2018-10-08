<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>


<html>
  <head>
    <title>添加备注</title>
    <%@ include file="../util/calendar.jsp" %>
    <script language="JavaScript" src="../js/util.js"></script>
    <%@ include file="../include/dialog.jsp"%>
     <script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {		
			goFormWindow(f,"addorUpdateMerSucRate");
		}
	</script>  
  </head>
  
  <body>
      <div id="title" value="备注" />
     <div id="resizetable" width="300" height="200" style=" width:300px; height:300px; text-align:center">
	    <s:form action="addorUpdateMerSucRate" theme="simple">
	    <input type="hidden" name="merSucRate.id" value="<s:property value="merSucRate.id"/>">
     
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
		    	<tr>
		    		<td>使用状态</td>
		    		<td>
		    			<s:select name="merSucRate.status" list="#{'1':'使 用','0':'未使用'}" listKey="key" listValue="value"/>
		    		
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>备注信息</td>
		    		<td>
		    			<s:textfield name="merSucRate.tradeRemark"></s:textfield>
		    		</td>
		    	</tr>
		    </table>
		<br/>
            <input type="button" onClick="addCourse(this.form);" value="提 交" />
	    </s:form>
	    <div>
    		<a href="toMerSucRateInfo.action">返回</a>
    	</div>
      </div>
  </body>
</html>
