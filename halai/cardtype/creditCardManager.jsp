<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>卡种列表</title>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){
        window.location="../PaySystem/toCreditCard.action";
}
function add(){ 
   openWindow('../PaySystem/toAddCreditCard.action','12');
}
function update(carditCardId){
	openWindow('../PaySystem/toUpdateCreditCard.action?carditCardId='+carditCardId,'12')
}
</script>

<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
   <tr class="talbe_list">
    <td height="25" colspan="3"><table width="80%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30" align="center"><img src="../images/other/icon4.gif" alt="" /></td>
        <td class="font14px"><b>卡种列表</b>&nbsp;</td>
      </tr>
    </table></td>
    <td height="25" align="right">
     <input type="button" value="" title="新增卡种" class="button_add_01" onclick="add();">&nbsp;&nbsp;</td>
  </tr>
  <tr class="talbe_list_01" style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>id</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>卡种名称</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>卡种简称</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>操作</strong></td>
  </tr>
  <s:iterator id="credit" value="creditCardList" >
  <tr style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#credit.id" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#credit.cardName" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#credit.shortName" /></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><input type="button" class="button_update_01" name="p" value="" title="update" onclick="update(<s:property value="#credit.id" />);"/></td>
  </tr>
 </s:iterator>
</table>