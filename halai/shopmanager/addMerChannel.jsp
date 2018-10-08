<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html>
  <head>
  	
    <title>添加通道</title>
  </head>
      <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/addMerChannel");
	}
</script> 
  <body>
  	<center>
    <H3>添加商户通道</H3>
	    <s:form action="addMerChannel" theme="simple">
        
        
        <div style="margin:0 0 0 50px">
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
		    	<tr>
		    		<td>商户号</td>
		    		<td>
		    			<input type="hidden" name="merchannel.merchantId" value="<s:property value="merchant.id"/>"/>
		    			<s:property value="merchant.merno"/> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>通道</td>
		    		<td>
		    			<s:select name="merchannel.channelId" list="channelList" listKey="id" listValue="channelName"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>结算周期</td>
		    		<td>
		    			<s:textfield name="merchannel.balanceCycle"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>结算费率</td>
		    		<td>
		    			<s:textfield name="merchannel.balanceCharge"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>保证金结算周期</td>
		    		<td>
		    			<s:textfield name="merchannel.bailCycle"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>保证金结算费率</td>
		    		<td>
		    			<s:textfield name="merchannel.bailCharge"></s:textfield>
		    		</td>
		    	</tr>
		    	<tr>
		    	<td>风控分值lv1</td>
		    	<td>
		    		<s:textfield name="merchannel.maxmind_lv1"></s:textfield>
		    	</td>
		    	</tr>
		    	<tr>
		    	<td>风控分值lv2</td>
		    	<td>
		    		<s:textfield name="merchannel.maxmind_lv2"></s:textfield>
		    	</td>
		    	</tr>
		    	
		    </table>
		</div>
            <input type="button" onClick="addCourse(this.form);" value="添加通道" class="windows_icon1"/>
	    </s:form>
    </center>
    
  </body>
</html>
