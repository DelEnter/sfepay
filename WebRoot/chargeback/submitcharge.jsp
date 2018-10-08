<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<head>
	<title>拒付</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function xiugai(f) {		
		goFormWindow(f,"../PaySystem/singleChargeBack");
	}
</script>  
<s:form action="singleChargeBack" id="form1" namespace="/PaySystem" theme="simple">
<input type="hidden" name="tradeId" value="<s:property value="ti.id"/>">
<div id="title" value="交易拒付"/>
<div id="resizetable" width="600" height="360">
 <div style="margin:0 0 0 50px">
  <div class="windows">
     <div class="right">流水订单号：</div>
     <div class="right"><s:textfield name="ti.orderNo" size="35" ></s:textfield></div>
     <div class="left">是否列为黑卡：</div>
     <div class="right"><s:checkbox name="isBackCard"/></div>
     <div class="left">是否列为高风险卡：</div>
     <div class="right"><s:checkbox name="isRickCard"/></div>
     <div class="left">拒付原因：</div>
     <div class="right"><s:textarea name="cb.remark" rows="10" cols="40"></s:textarea></div>
     <div class="clear">&nbsp;</div>
     <div class="left">&nbsp;</div>
     <div class="right">
      <input type="button" onClick="xiugai(this.form);" class="windows_icon1" value="提交拒付" />
     </div>
 </div>
  </div>
</div>
</s:form>