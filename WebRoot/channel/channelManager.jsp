<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>通道列表</title>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){

        window.location="../PaySystem/toChannel.action";
}
function add(){ 
   openWindow('../PaySystem/toAddChannel.action','12');
}
function update(channelid){
	openWindow('../PaySystem/toUpdateChannel.action?channelId='+channelid,'12')
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
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>通道id</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>通道名称</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>操作</strong></td>
  </tr>
  <s:iterator id="channelList" value="channelList" >
  <tr style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#channelList.id" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#channelList.channelName" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><input type="button" name="p" value="" title="update" class="button_update_01" onclick="update(<s:property value="#channelList.id" />);"/></td>
  </tr>
  </s:iterator>
</table>
