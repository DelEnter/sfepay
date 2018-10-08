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
	
	function chaxun(){
		var formX = document.getElementById("formu");
		formX.submit();
	}
	function shengcheng(refundType){
		document.getElementById('refundType').value=refundType
		var formX = document.getElementById("formu");
		formX.action="batchRefund";
		formX.submit();
	}
	function xiazaishensu(fileId){
		window.location="../PaySystem/downbatchrefund.action?fileId="+fileId;
	}
	function shangchuan(){
		var formX = document.getElementById("formu");
		formX.action="daorudcctuikuan.action";
		formX.submit();
	}
	function shengchengshenqingtuikuan(){
		var formX = document.getElementById("formu");
		formX.action="createExcelApplyRefund.action";
		formX.submit();
	}
</script>
<div align="center">
	<h3>下载批量退款文件</h3>
</div>
<s:form name="formu" id="formu" action="todownbatchrefund"  enctype ="multipart/form-data"  method="post" theme="simple">
<s:hidden name="refundType" id="refundType"/>
<table align="center">
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
	 		<tr class=TR_Title>
	 		 	<td colspan="6"><font color="red"><s:property value="messageAction"/></font></td>
		 		
	 		</tr>
	 		
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >
					<input type="button" onclick="shengcheng('VIP')" value="vip生成" >
					<input type="button" onclick="shengcheng('MIGS')" value="MIGS生成" >
				</td>
				
			</tr>
			<tr class=font-align>
				<td colSpan="8" align="center"><br>
					生成拒付退款文件<s:file name="fileName"/>
					<input type="button" onclick="shangchuan()" value="上传"/>
				</td>
				
			</tr>
			<tr class=font-align>
				<td colSpan="8" align="center"><br>
					生成申请退款excel<s:file name="fileNameBytxt"/>
					<input type="button" onclick="shengchengshenqingtuikuan()" value="上传"/>
				</td>
				
			</tr>
	</table>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center">序列</td>
		<td align="center">文件名</td>
		<td align="center">文件长度</td>
		<td align="center">时间</td>
		<td align="center">下载次数</td>
		<td align="center">最后下载时间</td>
		<td align="center">操作</td>
	</tr>
	<s:iterator id="p" value="fileList" status="s">
	<tr>
		<td><s:property value="#s.index+1" />&nbsp;</td>
		<td><s:property value="#p.filename" />&nbsp;</td>
		<td><s:property value="#p.filesize" />&nbsp;</td>
		<td align="center"><s:property value="#p.filedate" />&nbsp;</td>
		<td align="center"><s:property value="#p.downloadcount" />&nbsp;</td>
		<td align="center"><s:property value="#p.downloadDate" />&nbsp;</td>
		<td align="center"><a href="#" onclick="xiazaishensu(<s:property value="#p.id" />)">下载</a></td>
	</tr>
	</s:iterator>
    </table>
<div align="center">
</div>
<div align="right">
</div>
</s:form>
