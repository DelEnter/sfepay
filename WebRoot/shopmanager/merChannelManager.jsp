<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>通道列表</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
			var merid = document.getElementById('merid').value;
	        window.location="../PaySystem/toMerChannel.action?merid="+merid;
	}
	function add(){ 
	   var merid = document.getElementById('merid').value;
	   openWindow('../PaySystem/toAddMerChannel.action?merid='+merid,'12');
	}
	function update(channelid){
		var merid = document.getElementById('merid').value;
		openWindow('../PaySystem/toUpdateMerChannel.action?merChannelId='+channelid+'&merid='+merid,'12')
	}
	function openorclose(channelid,onoff){
		openWindow('../PaySystem/openorclose.action?merChannelId='+channelid+'&onoff='+onoff,'12')
	}
</script>
<div align="center">
	<h3>商户通道</h3>
</div>
<s:form name="toMerChannel">
<div align="center" style="border-bottom:1px solid #aca899; font-size:14px" >
			商户号：<input type="text" name="merchantno" size="15" value="<s:property value="merchantno"/>"/><input type="submit"  value="查询" />&nbsp;&nbsp;&nbsp;<input type="button" value="新增通道" onclick="add();">
	</div>

<input type="hidden" name="merid" id="merid" value="<s:property value="merid" />" />
   <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
    		<tr>
    			<td bgColor=#cccccc>商户号</td>
    			<td bgColor=#cccccc>通道</td>
    			<td bgColor=#cccccc>风控分值lv1</td>
    			<td bgColor=#cccccc>风控分值lv2</td>
    			<td bgColor=#cccccc>结算周期</td>
    			<td bgColor=#cccccc>结算费率</td>
    			<td bgColor=#cccccc>保证金周期</td>
    			<td bgColor=#cccccc>保证金费率</td>
    			<td bgColor=#cccccc>执行时间</td>
    			<td bgColor=#cccccc>状态</td>
    			<td bgColor=#cccccc>操作</td>
    		</tr>
    		<s:iterator id="m" value="merChannelList" >
	    		<tr>
	    			<td><s:property value="#m[0]" />&nbsp;</td>
	    			<td><s:property value="#m[1]"/>&nbsp;</td>
	    			<td><s:property value="#m[2].maxmind_lv1"/>&nbsp;</td>
	    			<td><s:property value="#m[2].maxmind_lv2"/>&nbsp;</td>
	    			<td><s:property value="#m[2].balanceCycle"/>天&nbsp;</td>
	    			<td><s:property value="#m[2].showbalanceCharge"/>&nbsp;</td>
	    			<td><s:property value="#m[2].bailCycle"/>天&nbsp;</td>
	    			<td><s:property value="#m[2].showbailCharge"/>&nbsp;</td>
	    			<td><s:property value="#m[2].executeTime"/>&nbsp;</td>
	    			<td>
	    			<s:if test="#m[2].onoff==1">
	    				已开通
	    			</s:if>
	    			<s:else>
						已关闭
	    			</s:else>
	    			</td>
	    			<td><input type="button" name="p" value="<s:if test="#m[2].onoff==1">关闭</s:if><s:else>开通</s:else>" onclick="openorclose(<s:property value="#m[2].id" />,<s:property value="#m[2].onoff" />);"/>
	    			<input type="button" name="p" value="update" onclick="update(<s:property value="#m[2].id" />);"/>&nbsp;</td>
	    			
	    			
	    		</tr>
    		</s:iterator>
    	</table>
  </s:form>