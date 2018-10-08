<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <title>添加网址管理</title>
  </head>
      <script language="JavaScript" type="text/JavaScript">
      
	function addCourse(f) {
		goFormWindow(f,"../PaySystem/addWebSiteManager");
		//document.getElementById("myForm").submit();
	}
</script> 
  <body>
  	<center>
    <H3>添加网址管理</H3>
	    <s:form action="webSiteManager" theme="simple" id="myForm123">
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
			<tr>
		    		<td>商户号</td>
		    		<td>
							<input type="hidden" name="website.merchanid" id="merchanid"
								value="<s:property value="merchant.id"/>" />
							<input type="text" name="merchant.merno" id="merno" value="<s:property value="merchant.merno" />" />
						</td>
		    	</tr>
		    	<tr>
		    		<td>交易网址</td>

		    		<td>
		    		<s:textfield name="website.tradeWebsite" id="website" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>返回网址</td>
		    		<td>
		    			<s:textfield name="website.website" id="tradeWebsite" />
		    		</td>
		    	</tr>
		    </table>
		    <input type="button" onClick="addCourse(this.form);" value="添加网址" class="windows_icon1"/>
	    </s:form> 
    </center>
    
  </body>
</html>
