<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
<head>
	<title>商户卡种列表</title>
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
var merid = document.getElementById('merid').value;
	openWindow('../PaySystem/toxiugakazhong.action?mercreditcardId='+channelid+'&merid='+merid,'12')
}
function openorclose(channelid,onoff){
		openWindow('../PaySystem/kazhongkaiguan.action?channelId='+channelid+'&onoff='+onoff,'12')
	}
</script>
<div align="center">
	<h3>卡种通道管理</h3>
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
    <td height="25" colspan="4"><table width="80%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="30" align="center"><img src="../images/other/icon4.gif" alt="" /></td>
        <td class="font14px"><b>通道列表</b>&nbsp;</td>
      </tr>
    </table></td>
    <td height="25" align="right"><input type="button" class="button_add_01" onclick="add();" value="" title="新增卡种">
     &nbsp;&nbsp;</td>
  </tr>
  <tr class="talbe_list_01" style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>商户号</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>通道</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>卡种</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>状态</strong></td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px" ><strong>操作</strong></td>
  </tr>
<s:iterator id="m" value="merCreditCardList" >
  <tr style="border-bottom:1px solid #aca899; background:#f6f6f6">
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#m[0]" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#m[1]" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:property value="#m[2]" />&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px"><s:if test="#m[3].onoff==1">
	    				已开通
	    			</s:if>
	    			<s:else>
						已关闭
	    			</s:else>&nbsp;</td>
    <td height="25" align="center" style="border-bottom:1px solid #aca899; font-size:14px">
		<input type="button" name="p" value="<s:if test="#m[3].onoff==1">关闭</s:if><s:else>开通</s:else>" onclick="openorclose(<s:property value="#m[3].id" />,<s:property value="#m[3].onoff" />);"/>
		<input type="button" name="modifyi" value="修改" onclick="update(<s:property value="#m[3].id"/>)"/>
	</td>
  </tr>
  </s:iterator>
</table>
    	
</s:form>
    	
    	
    	