<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<style>
.windows{ width:400px; height:500px; margin:0 0 0 25px;}
.left{ width:100px; height:30px; float:left;padding:3px 0 0 0}
.right{ width:250px; height:30px; float:left;  }
</style>
      <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/terminalManager");
	}
</script> 
    <s:form action="terminalManager" theme="simple">
<input type="hidden" name="terminalId" value="<s:property value="terminalId"/>"/>
<div id="title" value="终端管理"/>
<div id="resizetable" width="400px" height="500px">
  <div class="windows">
     <div class="left" >通道：</div>
     <div class="right"><s:select name="tm.channelId" list="channelList" listKey="id" listValue="channelName"  /></div>
     <div style="clear:both">&nbsp;</div> 
     <div class="left">卡种：</div>
     <div class="right"><s:select name="tm.creditCardId" list="creditCardList" listKey="id" listValue="cardName" /></div>
     <div style="clear:both">&nbsp;</div> 
     
     <div class="left">商户号：</div>
     <div class="right"><s:textfield name="tm.merchantNo"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
     
     <div class="left">终端号：</div>
     <div class="right"><s:textfield name="tm.terminalNo"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
      <div class="left">关联终端号：</div>
     <div class="right"><s:textfield name="tm.andterminalNo"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
     <div class="left">账单地址：</div>
     <div class="right"><s:textfield name="tm.billingAddress"></s:textfield></div>
     <div style="clear:both">&nbsp;</div>
      <div class="left">月交易笔数：</div>
     <div class="right"><s:textfield name="tm.tradeTimes"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
      <div class="left">网关类型：</div>
      <div class="right"><s:textfield name="tm.netstate"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
      <div class="left">银行：</div>
      <div class="right"><s:textfield name="tm.banktype"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
      <div class="left">HashCode：</div>
      <div class="right"><s:textfield name="tm.hashcode"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
      <div class="left">交易接入码：</div>
      <div class="right"><s:textfield name="tm.authcode"></s:textfield></div>
      <div style="clear:both">&nbsp;</div>
      <div class="left">银行返回备注：</div>
      <div class="right"><s:textfield name="tm.bankBackRemark"></s:textfield></div>
      <div style="clear:both">&nbsp;</div> 
     <div class="left">&nbsp;</div>
     <div class="right">
       <input type="button" onClick="addCourse(this.form);" value="提交" class="windows_icon1"/>
     </div>
 
  </div>
</div>
</s:form>


