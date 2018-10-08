<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
<head>
	<title>商户结算清单</title>
</head>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){
		var merid = document.getElementById('merid').value;
        window.location="../PaySystem/toMerCreditCard.action?merid="+merid;
}
function add(){ 
   var merid = document.getElementById('merid').value;
   openWindow('../PaySystem/toAddMerCreditCard.action?merid='+merid,'12');
}
function update(channelid){
	openWindow('../PaySystem/toUpdateChannel.action?channelId='+channelid,'12')
}
function openorclose(channelid,onoff){
		openWindow('../PaySystem/kazhongkaiguan.action?channelId='+channelid+'&onoff='+onoff,'12')
	}
</script>
<div align="center">
	<h3>商户结算查看</h3>
</div>
<br>
<br>
<s:form name="toMerCreditCard" theme="simple">
<table width="80%" border="0" cellspacing="0" cellpadding="0">
<input type="hidden" name="merid" id="merid" value="<s:property value="merid" />" />
	<tr align="center" style="border-bottom:1px solid #aca899; font-size:14px" >
		<td height="25" colspan="4">
			商户号：<input type="text" name="merno" value="<s:property value="merno"/>"/><input type="submit"  value="查询" />
		</td>
	</tr>
   <tr class="talbe_list">
    <td height="25" colspan="2"><table width="80%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30" align="center"><img src="../images/other/icon4.gif" alt="" /></td>
        <td class="font14px"><b>结算日列表</b>&nbsp;</td>
      </tr>
    </table></td>
    <td height="25" align="right">
     &nbsp;&nbsp;</td>
  </tr>
  <tr class="talbe_list_01" style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25"  align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>日期</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>商户列表</strong></td>
  </tr>
<s:iterator id="m" value="cycleDayList" >
  <tr style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px;
    	<s:if test="#m == today_day">
    		background:#abedff
    	</s:if>
    ">
    		<s:property value="#m" />&nbsp;
    </td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px;
    	<s:if test="#m == today_day">
    		background:#abedff
    	</s:if>
    ">
    	<s:iterator id="s" value="systemManagerService.getMerchantNoByCycle(#m)" >
    		<s:if test="#s!=null">
    			<s:property value="#s"/>,
    		</s:if>
    	</s:iterator>
		&nbsp;
    </td>
  </tr>
  </s:iterator>
</table>
    	
</s:form>
    	
    	
    	