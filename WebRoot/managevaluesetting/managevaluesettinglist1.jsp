<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<head>
	<title>商户管理值设定</title>
	<script type="text/javascript" src="..js/util.js"></script>
</head>
<script type="text/JavaScript">
	function saveManagevalue(f) {	
	//alert(f);	
	//document.getElementById("myForm").submit();
		goFormWindow(f,"../PaySystem/saveManageValueSetting1");
	}
</script>

<s:form id="myForm" namespace="/PaySystem" theme="simple">
	
<input type="hidden" name="merid"
		value="<s:property value="merchant.id"/>" />
	<input type="hidden" name="merno"
		value="<s:property value="merchant.merno" />" />
        <div id="resizetable" width="400" height="300">
        <div class="windows">
  			 <div class="left">商户号：</div>
  			 <div class="right2"><input type="hidden" name="imm.merchanid"
				value="<s:property value="merchant.merno"/>" />
			<s:property value="merchant.merno" />
             <div class="clear">&nbsp;</div>
  			 <div class="left">分值：</div>
  			 <div class="right"><s:textfield name="imm.markValue" id="markValue"></s:textfield></div>
             
             <div class="clear">&nbsp;</div>
  			 <div class="left">单笔交易限额：</div>
  			 <div class="right"><s:textfield name="imm.penQuota" id="penQuota"></s:textfield></div>
             
             <div class="clear">&nbsp;</div>
  			 <div class="left">天交易限额：</div>
  			 <div class="right"><s:textfield name="imm.dayQuota" id="dayQuota"></s:textfield></div>
             
             <div class="clear">&nbsp;</div>
  			 <div class="left">月笔交易限额：</div>
  			 <div class="right"><s:textfield name="imm.monthQuota" id="monthQuota"></s:textfield></div>
  			 
  			 <div class="clear">&nbsp;</div>
  			 <div class="left">风控管理费/笔：</div>
  			 <div class="right"><s:textfield name="imm.riskMoney" id="riskMoney"></s:textfield></div>
  			 
  			 <div class="clear">&nbsp;</div>
  			 <div class="left">通道使用费/月</div>
  			 <div class="right"><s:textfield name="imm.channelMoney" id="channelMoney"></s:textfield></div>
		</td>
	</div>
	<br>
	<input type="button" value="保存" onClick="saveManagevalue(this.form);"
		class="windows_icon1" />
             <div class="clear">&nbsp;</div>
  			 <div class="left">&nbsp;</div>
  			 <div class="right">
  			 </div>
		</div>
</s:form>


