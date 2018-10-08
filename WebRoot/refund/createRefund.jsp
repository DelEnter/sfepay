<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>Apply Refund</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
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
		var o=document.formu.refundIds;
		//单条记录
		if(o.value!=null){
			o.checked=true;
		}
		//多条记录
	    for(var i=0;i<o.length;i++){
			if(o[i].name == "refundIds") {
				o[i].checked=true;
		    }
		}
	}
	function noselectAll() {
	    var o=document.formu.refundIds;
	    if(o.value!=null){
			o.checked=false;
		}
		 for(var i=0;i<o.length;i++){
				if(o[i].name == "refundIds")
			    {
   					o[i].checked=false;
			    }
			 }
	}
	
	function submitAuditing(){
	   var flag=0;
	   var chck=document.getElementsByName("refundIds");
	   for(i=0;i<chck.length;i++){
	       if(chck[i].checked==true){
	           flag++;
	       }
	   }
	   if(flag>0){
	   		document.getElementById("formu").action="createRefund";
			document.getElementById("formu").submit();
       }else{
           alert("请选择一个!");
       }
	}
	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="1";
		formX.submit();
	}
	function batchrefund(){
		var formX = document.getElementById("formu");
		formX.action = "batchRefund";
		formX.submit();
	}
	function chaxun(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="";
		formX.submit();
	}
	
	function buchongxinxi(tradeid){
		window.showModalDialog ("../PaySystem/toinputFulInfo.action?tradeId="+tradeid, window,'dialogHeight:630px;dialogWidth:593px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') ;
	}
</script>
<div align="center">
	<h3>生成退款交易</h3>
</div>
<s:form name="formu" id="formu" action="toCreateRefund" method="post" theme="simple">
<input type="hidden" name="exportX" id="exportX" value=""/>
<table align="center">
<tr class=TR_Title>
		 		<td>商户号</td>
		 		<td>
		 			<s:select name="merchantNo" list="merchantList" listKey="merno" listValue="merno" headerKey="" headerValue="----" />
		 		</td>
	 		</tr>
	 		 <tr class=TR_Title>
	 		 	<td>流水订单号</td>
		 		<td>
		 			<input type="input" name="orderNo" value="<s:property value='orderNo'/>"/>
		 		</td>
		 		<td>卡号</td>
		 		<td>
		 			<input type="input" name="cardNo" value="<s:property value="cardNo"/>"/>
	 			</td>
		 		<td></td>
		 		<td>
	 			</td>
	 		</tr>
	 		 <tr class=TR_Title>
	 		 	<td>授权号</td>
		 		<td>
		 			<input type="input" name="authorizationNo" value="<s:property value='authorizationNo'/>"/>
		 		</td>
		 		<td></td>
		 		<td>
	 			</td>
		 		<td></td>
		 		<td>
	 			</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >
				</td>
				
			</tr>
	</table>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center"><input type="checkbox" name="checkAll" onclick="checkSelect();" /></td>
		<td align="center">序列</td>
		<td align="center">流水号</td>
		<td align="center">商户订单号</td>
		<td align="center">卡号</td>
		<td align="center">通道</td>
		<td align="center">交易日期</td>
		<td align="center">审核日期</td>
		<td align="center">交易金额</td>
		<td align="center">退款金额(外币)</td>
		<td align="center">退款金额(RMB)</td>
		<td align="center">交易状态</td>
		<td align="center">退款状态</td>
		<td align="center">授权号</td>
		<td align="center">终端号</td>
		<td align="center">补充信息</td>
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr>
		<td align="center"><input type="checkbox" name="refundIds" value="<s:property value="#p[0].id" />" /></td>
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[1].orderNo" /></td>
		<td>
			<s:property value="#p[1].merchantOrderNo" />
		</td>
		<td align="center"><s:property value="#p[2].cardNo" /></td>
		<td align="center"><s:property value="#p[3]" /></td>
		<td align="center"><s:property value="#p[1].tradeTime" /></td>
		<td align="center"><s:property value="#p[0].auditingDate" /></td>
		<td align="right"><s:property value="#p[1].tradeAmount" /></td>
		<td align="right"><s:property value="#p[0].refundAmount" /></td>
		<td align="right"><font color="red"><s:property value="#p[0].refundRMBAmount" /></font></td>
		<td align="center"><s:property value="states.getStateName(#p[1].tradeState,1)"  escape="false"/></td>
		<td align="center"><s:property value="states.getRefundDetailState(#p[0].refundState)"  escape="false"/></td>
		<td align="center"><s:property value="#p[1].VIPAuthorizationNo" /></td>
		<td align="center"><s:property value="#p[1].VIPTerminalNo" /></td>
		
		<td align="center">
			<s:if test="#p[1].DCCTradeAmount==null">
				<a href="#" onclick="buchongxinxi(<s:property value="#p[1].id"/>)">补充信息</a>
			</s:if>&nbsp;
		</td>
		
	</tr>
	</s:iterator>
    
    

</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="forms(0)" />
</div>
<div align="right">
<select name="pagesize" onchange="this.form.submit()">
	<option value="20"
		<s:if test="pagesize==20">
			selected
		</s:if>
	>每页20条</option>
	<option value="30"
		<s:if test="pagesize==30">
			selected
		</s:if>
		 >每页30条</option>
	<option value="40"
	<s:if test="pagesize==40">
			selected
		</s:if>
	>每页40条</option>
	<option value="50"
	<s:if test="pagesize==50">
			selected
		</s:if>
	>每页50条</option>
</select>
</div>
<p align="center">
	<input type="button" name="s" value="生成退款" onclick="submitAuditing()" />
	<input type="button" name="s" value="下载" onclick="exportInfo()" />
	<input type="button" name="s" value="批量" onclick="batchrefund()" />
</p>
</s:form>
