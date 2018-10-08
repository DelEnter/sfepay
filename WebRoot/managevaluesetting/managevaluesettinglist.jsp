<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<html>
  <head>
    <title>商户管理值</title>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  	<script src="../js/util.js"></script>
  </head>
      <script type="text/javascript">
      
      //保存商户管理值
		function addCourse(f) {	
			//alert(f.action);
			//goFormWindow(f,"../PaySystem/saveManageValueSetting");
			document.getElementById("myForm").submit();
		}
		
		//根据商户号查询商户管理值
		function findCondition() {
			var merchantNo = document.getElementById("merchantNo").value;
			window.location.href="../PaySystem/findManageValueSetting.action?merchantNo="+merchantNo;
		}
	
	
	</script> 
  <body>
	   <h3 align="center">商户风控值设置</h3>
<s:form action="saveManageValueSetting" id="myForm" namespace="/PaySystem" theme="simple">
	<table align="center">
		<tr>   
			<td>
				商户号：<input type="text" name="merchantNo" id="merchantNo" value='<s:property value='merchantNo'/>' />   
			</td>
			<td>
				<input type="button" value="查询" onClick="findCondition();"/>
			</td>
		</tr>
		</table>
        <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="400" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
			<tr align="center">
			<s:if test="flag != 1">
			<td bgColor=#cccccc>
					商户号
				</td>
			</s:if>
				<td bgColor=#cccccc>
					分值(正常)
				</td>
					<td bgColor=#cccccc>
					分值(待确认)
					</td>
				<td bgColor=#cccccc>
					单笔交易限额(上限)
				</td>
				<td bgColor=#cccccc>
					单笔交易限额(下限)
				</td>
				<td bgColor=#cccccc>
					天交易限额
				</td>
				<td bgColor=#cccccc>
					月笔交易限额
				</td>
					<td bgColor=#cccccc>
					网站个数
					</td>
			</tr>
				<s:iterator id="imm" value="internationalMerchantManagerList">
					<tr align="center">
					<s:if test="flag != 1">
					<td align="left">
							<s:property value="#imm[1].merno"/>
						</td>
					</s:if>
						<td align="left">
							<input type="text" name="markValue" value="<s:property value="#imm[0].markValue"/>" />
						</td>
						<td align="left">
						<input type="text" name="confirmValue" value="<s:property value="#imm[0].confirmValue"/>" />
						</td>
						<td>
							<input type="text" name="penQuota" value="<s:property value="#imm[0].penQuota"/>" />
						</td>
						<td>
							<input type="text" name="penQuotaLower" value="<s:property value="#imm[0].penQuotaLower"/>" />
						</td>
						<td>
							<input type="text" name="dayQuota" value="<s:property value="#imm[0].dayQuota"/>" />
						</td>
						
						<td>
							<input type="text" name="monthQuota" value="<s:property value="#imm[0].monthQuota"/>" />
						</td>
						<td>
						<input type="text" name="urlCount" value="<s:property value="#imm[0].urlCount"/>" />
						</td>
					</tr>
				<input type="hidden" name="merid" value="<s:property value="#imm[1].id"/>" />
		
				<input type="hidden" name="iid" value="<s:property value="#imm[0].id"/>" />
		
				<input type="hidden" name="merno" id="merno" value="<s:property value="#imm[1].merno"/>" />
				</s:iterator>
				</table>
				<table align="center">
				<s:if test="internationalMerchantManagerList.size>0">
					<tr>
						<td>
							<input type="button" value="保存" onClick="addCourse(this.form);" class="windows_icon1" />
						</td>
					</tr>
				</s:if>
		</table>
	<br>
</s:form>
  <!--尾部begin-->
	<script language="javascript">	
		var temflag='<s:property value='sign'/>';
		if(temflag==1){
			alert("商户管理值保存成功");
		}else if(temflag==2){
			alert("商户管理值保存失败");
		}		
	</script>	
	</body>
