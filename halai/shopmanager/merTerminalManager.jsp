<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>商户独立终端列表</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
			var merid = document.getElementById('merid').value;
	        window.location="../PaySystem/showMerchantTerminalQuery.action?merid="+merid;
	}
	function add(){ 
	   var merid = document.getElementById('merid').value;
	   openWindow('../PaySystem/toAddMerTerminal.action?merid='+merid,'12');
	}
	function update(merTerId){
		var merid = document.getElementById('merid').value;
		openWindow('../PaySystem/toAddMerTerminal.action?merTerId='+merTerId+'&merid='+merid,'12')
	}
	function openorclose(merTerId,onoff){
		openWindow('../PaySystem/terisopen.action?merTerId='+merTerId+'&onoff='+onoff,'12')
	}
</script>
<div align="center">
	<h3>商户独立终端</h3>
</div>
<s:form name="showMerchantTerminalQuery">
<div align="center" style="border-bottom:1px solid #aca899; font-size:14px" >
			商户号：<input type="text" name="merchantno" size="15" value="<s:property value="merchantno"/>"/><input type="submit"  value="查询" />&nbsp;&nbsp;&nbsp;<input type="button" value="新增终端" onclick="add();">
	</div>

<input type="hidden" name="merid" id="merid" value="<s:property value="merid" />" />
   <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
    		<tr>
    			<td bgColor=#cccccc>通道</td>
    			<td bgColor=#cccccc>终端号</td>
    			<td bgColor=#cccccc>状态</td>
    			<td bgColor=#cccccc>操作</td>
    		</tr>
    		<s:iterator id="m" value="merchantTerminalList" >
	    		<tr>
	    			<td><s:property value="#m[2].channelName" />&nbsp;</td>
	    			<td><s:property value="#m[1].terminalNo"/>&nbsp;</td>
	    			<td>
	    			<s:if test="#m[0].isopen==1">
	    				已开通
	    			</s:if>
	    			<s:else>
						已关闭
	    			</s:else>
	    			</td>
	    			<td><input type="button" name="p" value="<s:if test="#m[0].isopen==1">关闭</s:if><s:else>开通</s:else>" onclick="openorclose(<s:property value="#m[0].id" />,<s:property value="#m[0].isopen" />);"/>
	    			<input type="button" name="p" value="修改" onclick="update(<s:property value="#m[0].id" />);"/>&nbsp;</td>
	    			
	    			
	    		</tr>
    		</s:iterator>
    		<s:iterator id="m1" value="merchantNoList" >
    		<tr>
    		<td><s:property value="#m1[2].channelName" />&nbsp;</td>
    		<td><s:property value="#m1[1].bankMerchantId"/>&nbsp;</td>
    		<td>
    		<s:if test="#m1[0].isopen==1">
    		已开通
    		</s:if>
    		<s:else>
    		已关闭
    		</s:else>
    		</td>
    		<td><input type="button" name="p" value="<s:if test="#m1[0].isopen==1">关闭</s:if><s:else>开通</s:else>" onclick="openorclose(<s:property value="#m1[0].id" />,<s:property value="#m1[0].isopen" />);"/>
    		<input type="button" name="p" value="修改" onclick="update(<s:property value="#m1[0].id" />);"/>&nbsp;</td>
    		
    		
    		</tr>
    		</s:iterator>
    	</table>
  </s:form>