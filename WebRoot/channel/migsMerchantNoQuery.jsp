<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>migs通道列表</title>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){

        window.location="../PaySystem/toMigsMerchantList.action";
}
function add(){ 
   openWindow('../PaySystem/toAddMigsMerchantNo.action','12');
}
function update(channelid){
	openWindow('../PaySystem/toUpdateMigs.action?migsId='+channelid,'12')
}
function onoff(channelid){
	openWindow('../PaySystem/migsonoff.action?migsId='+channelid,'12')
}
</script>

<table width="80%" border="0" cellspacing="0" cellpadding="0">
   <tr class="talbe_list">
    <td height="25" colspan="2"><table width="80%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30" align="center"><img src="../images/other/icon4.gif" alt="" /></td>
        <td class="font14px"><b>通道列表</b>&nbsp;</td>
      </tr>
    </table></td>
    <td height="25" align="right"><input type="button" class="button_add_01" onclick="add();" value="" title="新增通道">
     &nbsp;&nbsp;</td>
  </tr>
  <tr class="talbe_list_01" style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>商户号</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>卡种</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>是否开通</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>操作</strong></td>
  </tr>
  <s:iterator id="channelList" value="migsMerchantNoList" >
  <tr style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#channelList.bankMerchantId" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#channelList.cardtype" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px">
    
    <s:if test="#channelList.onoff==1">
    	已开通
    </s:if>
    <s:else>
    	已关闭
    </s:else>
    &nbsp;
    </td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px">
    	<input type="button" name="a" value="开通关闭" onclick="onoff(<s:property value="#channelList.id" />)" />
    	<input type="button" name="p" value="修改" title="update"  onclick="update(<s:property value="#channelList.id" />);"/>
    </td>
  </tr>
  </s:iterator>
</table>

