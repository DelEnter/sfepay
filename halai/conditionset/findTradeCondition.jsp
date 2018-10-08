<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>交易条件设置</title>
		<script type="text/javascript" src="..js/util.js"></script>
		<script type="text/javascript">
			function updateConditionSetting(f) {	
			goFormWindow(f,"../PaySystem/updateConditionQueryAction1");
		}
		</script>
	</head>
	<body>
		<H3 align=center>
			交易条件设置
		</H3>

			<tr>
				<td>
					商户号：
					<s:iterator id="bt" value="internationalTradeconditionList">
						<s:set name="merno" value="#bt[1].merno"/>
					</s:iterator>
					<s:property value="#merno"/>
				</td>
			</tr>
		<table >
			<tr align="center">
				<td bgColor=#cccccc>
					项目
				</td>
				<td bgColor=#cccccc>
					可交易笔数
				</td>
				<td bgColor=#cccccc>
					周期(小时)
				</td>
				<td bgColor=#cccccc>
					备注
				</td>
			</tr>
			<s:form action="updateConditionQueryAction" id="myForm2" theme="simple">
				<s:iterator id="bt" value="internationalTradeconditionList">
				
					<tr align="center">
					<td align="left">
							<s:property value="#bt[0].itemName"/>
						</td>
						<td>
							<input type="text" name="tradenumber" value="<s:property value="#bt[0].tradenumber"/>" />
						</td>
					
						<td>
							<input type="text" name="cycle" value="<s:property value="#bt[0].cycle"/>" />
						</td>
						<td>
							<input type="text" name="remark" value="<s:property value="#bt[0].remark"/>" />
						</td>
						
						
					</tr>
				<input type="hidden" name="iid" value="<s:property value='#bt[0].id'/>" />
			    <input type="hidden" name="itemno" value="<s:property value='#bt[0].itemno'/>" />
			   <input type="hidden" name="itemName" value="<s:property value="#bt[0].itemName"/>" />
				</s:iterator>
					<tr>
						<td>
							<input type="button" onClick="updateConditionSetting(this.form);" value="修改" class="windows_icon1" />
						</td>
					</tr>
				<input type="hidden" name="merno" value="<s:property value="#bt[1].merno" />" />
 				<input type="hidden" name="merid"
					value="<s:property value="#bt[1].id"/>" />
			</s:form>
		</table>
	</body>
</html>
