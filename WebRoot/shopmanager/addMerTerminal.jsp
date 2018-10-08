<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html>
  <head>
  	
    <title>添加/修改-终端</title>
  </head>
      <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/addMerTerminal");
	}
</script> 
  <body>
  	<center>
    <H3>添加商户独立终端</H3>
	    <s:form action="addMerTerminal" theme="simple">
        <div style="margin:0 0 0 50px">
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
		    	<tr>
		    		<td>商户号</td>
		    		<td>
		    			<input type="hidden" name="merchantTerminal.merchantId" value="<s:property value="merchant.id"/>"/>
		    			<input type="hidden" name="merchantTerminal.id" value="<s:property value="merchantTerminal.id"/>"/>
		    			<s:property value="merchant.merno"/> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>通道</td>
		    		<td>
		    			<s:select name="merchantTerminal.channelId" list="channelList" listKey="id" listValue="channelName"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>终端号</td>
		    		<td>
		    			<s:textfield name="terminalNo"></s:textfield>

		    		</td>
		    	</tr>
		    	
		    </table>
		</div>
            <input type="button" onClick="addCourse(this.form);" value="添加终端" class="windows_icon1"/>
	    </s:form>
    </center>
    
  </body>
</html>
