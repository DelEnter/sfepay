<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>终端商户两号信息</title>
<script language="JavaScript" src="../js/util.js"></script>
<script language="JavaScript" type="text/JavaScript">

function checkSelect(){
	var select = document.formu.checkAll;
	if(select.checked==true){
		selectAll();
	}else{
		noselectAll();
	}
}
function selectAll() { 
	var o=document.formu.termanalids;
	//单条记录
	if(o.value!=null){
		o.checked=true;
	}
	//多条记录
    for(var i=0;i<o.length;i++){
		if(o[i].name == "termanalids") {
			o[i].checked=true;
	    }
	}
}
function noselectAll() {
    var o=document.formu.termanalids;
    if(o.value!=null){
		o.checked=false;
	}
	 for(var i=0;i<o.length;i++){
			if(o[i].name == "termanalids")
		    {
					o[i].checked=false;
		    }
		 }
}
	
	function chaxun(){
		var f = document.getElementById("formu");
		f.action="twoNumber";
		f.submit();
		
	}
	function tijiao1(){
		var f = document.getElementById("formu");
		f.action="batchUpdateTer";
		f.submit();
	}
	
</script>
</head>
<div align="center" >
	<h3>终端商户两号信息</h3>
</div>
<s:form name="formu" id="formu" method="post" theme="simple" action="twoNumber">
<table align="center">
			<tr class=TR_Title>	 		
		 		<td>终端号</td>
		 		<td><s:textfield name="terminalno" /></td>
		 		<td>通道</td>
		 		<td><s:select name="channelId" list="chennelList" listKey="id" listValue="channelName" headerKey="" headerValue="----" /></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="chaxun()" value="查询" >				</td>
			</tr>
			<tr class=TR_Title>	 		
			<td>更换成终端号</td>
			<td><s:textfield name="addTerNo" /></td>
			</tr>
			<tr class=font-align>
			<td colSpan="8" align="center"><br>
			<input type="button" onclick="tijiao1()" value="提交新终端号" >				</td>
			</tr>
			
  </table>
	
	</br></br>
	<table cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
		<tr bgColor=#cccccc>
			<td><input type="checkbox" name="checkAll" onclick="checkSelect();" /></td>
			<td>商户号</td>
			<td>通道</td>
			<td>终端号</td>
		</tr>
		<s:iterator id="tradeSecond" value="twoNumber" status="s">
		<tr>
			<td>
				<s:property value="#s.index+1"/>
				<input type="checkbox" name="termanalids" value="<s:property value='#tradeSecond[2]'/>"/>
			</td>
			<td><s:property value="#tradeSecond[0]" />&nbsp;</td>
			<td><s:property value="#tradeSecond[3]" />&nbsp;</td>
			<td><s:property value="#tradeSecond[1]" />&nbsp;</td>
			
		</tr>
		</s:iterator>
</table>

</s:form>