<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<head>
	<title>网址列表</title>
<script language="JavaScript" src="../js/util.js"></script>	
	<script language="JavaScript" type="text/JavaScript">
	//跳转到添加网址
	function add(){ 
		var merid = document.getElementById("merid").value;
	   openWindow('../PaySystem/toAddMerchantWebSite1.action?merid='+merid,'12')
	}
	
	//修改网址
	function update(id){
		openWindow('../PaySystem/toUpdateMerchantWebSite1.action?iid='+id,'12')
	}
	</script>
</head>
<h3 align="center" >商户网址设置</h3>
<input type="button" value="新增网址" onclick="add();">
<input type="hidden" name="merid" id="merid" value="<s:property value="merid" />" />
	<input type="hidden" name="merno" value="<s:property value="merno"/>">
	
   <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
    		<tr>
    			<td bgColor=#cccccc>交易网址</td>
    			<td bgColor=#cccccc>返回网址</td>
    			<td bgColor=#cccccc>绑定日期</td>
    			<td bgColor=#cccccc>网址状态</td>
    			<td bgColor=#cccccc>网站类型</td>
    			<td bgColor=#cccccc>操作员</td>
    			<td bgColor=#cccccc>操作</td>
    		</tr>
    		<s:iterator id="merchantWebSiteList" value="merchantWebSiteList" >
	    		<tr>
	    			<td><s:property value="#merchantWebSiteList[0].tradeWebsite"/>&nbsp;</td>
	    			<td><s:property value="#merchantWebSiteList[0].website" />&nbsp;</td>
	    			<td><s:property value="#merchantWebSiteList[0].executetime"/>&nbsp;</td>
	    			<td><s:if test="#merchantWebSiteList[0].isblack==0">
	    			正常交易&nbsp;
	    			</s:if>
	    			<s:if test="#merchantWebSiteList[0].isblack==1">
	    			禁止交易&nbsp;
	    			</s:if>
	    			</td>
	    			<td><s:property value="#merchantWebSiteList[0].webSiteType"/>&nbsp;</td>
	    			<td><s:property value="#merchantWebSiteList[0].operator"/>&nbsp;</td>
	    			<td>
	    			<input type="button" name="p" value="update" onclick="update(<s:property value="#merchantWebSiteList[0].Id" />);"/>&nbsp;
	    			<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteWebSite.action','iid=<s:property value='#merchantWebSiteList[0].Id'/>');" />
	    			</td>
	    		</tr>
    		</s:iterator>
    	</table>