<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <title>修改通道</title>
  </head>
      <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/updateMerChannel");
	}
</script> 
  <body>
  	<center>
    <H3>修改商户通道sss</H3>
	    <s:form action="updateMerChannel" theme="simple">
	    <input type="hidden" name="merChannelId" value="<s:property value="merchannel.id"/>">
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
		    			<input type="text" value="<s:property value='merchannel.showbalanceCharge'/>" name="merchannel.balanceCharge" /> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>保证金结算周期</td>
		    		<td>
		    	<input type="text" value="<s:property value='merchannel.bailCycle'/>" name="merchannel.bailCycle" /> 
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>保证金结算费率</td>
		    		<td>
		    		<input type="text" value="<s:property value='merchannel.showbailCharge'/>" name="merchannel.bailCharge" /> 

		    		</td>
		    	</tr>
		    	<tr>
		    		<td>风控分值lv1</td>
		    	<td>
		    		<input type="text" value="<s:property value='merchannel.maxmind_lv1'/>" name="merchannel.maxmind_lv1" /> 
		    	
		    	</td>
		    	</tr>
		    	<tr>
		    		<td>风控分值lv2</td>
		    	<td>
		    		<input type="text" value="<s:property value='merchannel.maxmind_lv2'/>" name="merchannel.maxmind_lv2" /> 
		    	
		    	</td>
		    	</tr>
		    	<tr>
		    		<td>优先级</td>
		    	<td>
		    		<input type="text" value="<s:property value='merchannel.priority'/>" name="merchannel.priority" /> 
		    	
		    	</td>
		    	</tr>
		    	<tr>
		    	<td>通道附加费</td>
		    	<td>
		    		<s:textfield name="merchannel.channelFee"></s:textfield>
		    	</td>
		    	</tr>
		    </table>
		    <input type="button" onClick="addCourse(this.form);" value="修改通道" class="windows_icon1"/>
	    </s:form>
    </center>
    
  </body>
</html>
