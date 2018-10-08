<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <title>添加网址</title>
  </head>
      <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {
		goFormWindow(f,"../PaySystem/addMerchantWebSite.action");
	}
</script> 
  <body>
  	<center>
    <H3>添加网址</H3>
	    <s:form action="addMerchantWebSite" theme="simple">
	    <input type="hidden" name="merid" value="<s:property value="merchant.id"/>" />
	    <input type="hidden" name="merno" value="<s:property value="merchant.merno" />">
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
			<tr>
		    		<td>商户号</td>
		    		<td>
							<s:property value="merchant.merno" />
						</td>
		    	</tr>
		    	<tr>
		    		<td>交易网址</td>
		    		<td>
		    			<s:textfield name="website.tradeWebsite" id="tradeWebsite"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>返回网址</td>
		    		<td>
		    		<s:textfield name="website.website" id="website"></s:textfield>
		    		</td>
		    	</tr>
		    </table>
		    <input type="button" onClick="addCourse(this.form);" value="添加网址" class="windows_icon1"/>
	    </s:form> 
    </center>
    
  </body>
</html>
