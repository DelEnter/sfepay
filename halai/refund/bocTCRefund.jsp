<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>TC通道退款</title>
</head>
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
	   		document.getElementById("formu").action="auditingRefund";
			document.getElementById("formu").submit();
       }else{
           alert("请选择一个!");
       }
	}
	
	function Refund(refundid){
		   if(confirm("确定要退款吗?")){
			   document.getElementById("refundid").value=refundid;
			   document.getElementById("formu").action="bocrefund";
			   document.getElementById("formu").submit();
			   
			   
		   }
	      
		}
</script>
<div align="center">
	<h3>TC通道退款操作</h3>
</div>
<s:form name="formu" id="formu" action="bocrefundlist" method="post" theme="simple">
<s:hidden name="refundid" />
<div><p align="center">

<s:property value="messageAction"/></p>
</div>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td></td>
		<td>序列</td>
		<td>流水号</td>
		<td>商户订单号</td>
		<td>授权号</td>
		<td>凭证号</td>
		<td>RRN</td>
		<td>交易日期</td>
		<td>申请日期</td>
		<td>交易金额</td>
		<td>退款金额(外币)</td>
		<td>退款金额(RMB)</td>
		<td>交易状态</td>
		<td>退款状态</td>
		<td>终端号</td>
		<td>操作</td>
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		<td></td>
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[1].orderNo" /></td>
		<td><s:property value="#p[1].merchantOrderNo" /></td>
		<td><s:property value="#p[1].VIPAuthorizationNo" /></td>
		<td><s:property value="#p[1].VIPBatchNo" /></td>
		<td><s:property value="#p[1].boc_rrn" /></td>
		<td><s:property value="#p[1].tradeTime" /></td>
		<td><s:property value="#p[0].applyDate" /></td>
		<td><s:property value="#p[1].tradeAmount" /></td>
		<td><s:property value="#p[0].refundAmount" /></td>
		<td><font color="red"><s:property value="#p[0].refundRMBAmount" /></font></td>
		<td><s:property value="states.getStateName(#p[1].tradeState,1)" escape="false" /></td>
		<td align="center"><s:property value="states.getRefundDetailState(#p[0].refundState)" escape="false"/></td>
		<td align="center"><s:property value="#p[1].VIPTerminalNo" /></td>
		<td><a href="#" onclick="Refund(<s:property value="#p[0].id" />)">退款</a></td>
		
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="forms(0)" />
</div>
<p align="center">
	
</p>
</s:form>











