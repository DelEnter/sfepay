<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<html>
	<head>
		<title>交易条件设置</title>
		<script type="text/javascript" src="..js/util.js"></script>
		 <LINK href="../css/head.css" type=text/css rel=stylesheet>
		<script type="text/javascript">
			//修改条件设置
			function updateConditionSeting(f) {
				//goFormWindow(f,"../PaySystem/updateConditionSet");
				document.getElementById("myForm123").submit();
			}
			
			//查询条件设置
			function findCondition() {
				var merno = document.getElementById("mno").value;
				//alert(merno);
				window.location.href="../PaySystem/findCondition.action?merno="+merno;
			}
			
		</script>
	</head>
	<body>
		<H3 align=center>
			交易条件设置
		</H3>
		<s:form action="updateConditionSet" id="myForm123" theme="simple" namespace="/PaySystem">
		<table align="center">
		<tr>
				<td><s:iterator id="bt" value="internationalTradeconditionList">
						<s:set name="merno" value="#bt[1].merno"/>
					</s:iterator>
					商户号：<input type="text" id="mno" value="<s:property value="#merno"/>"/>
				</td>
				<td>
					<input type="button" value="查询" onClick="findCondition();"/>
				</td>
			</tr>
			</table>
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="400" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
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
				<input type="hidden" name="merno" value="<s:property value="#bt[1].merno" />" />
 				<input type="hidden" name="merid"
					value="<s:property value="#bt[1].id"/>" />
			</table>
				<table align="center">
					<tr>
						<td>
					<s:if test="internationalTradeconditionList.size==5">
							<input type="button" onClick="updateConditionSeting(this.form);" value="修改" class="windows_icon1" />
					</s:if>
						</td>
					</tr>
		</table>
			</s:form>
			
			
			<!--尾部begin-->
	<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("交易条件修改成功");
		}else if(temflag==2){
			alert("交易条件修改失败");
		}		
	</script>	
		
	</body>
</html>
