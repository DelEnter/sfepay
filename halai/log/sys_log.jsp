<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统日志</title>
		<script type="text/javascript">
			 function getLogDetails(id){
		    	var iTop = (window.screen.availHeight- 30 - 750) / 2;
		    	var iLeft = (window.screen.availWidth- 10 - 700) / 2;
				window.showModalDialog("../PaySystem/toSystemLogDetails.action?id="+id,window,"dialogHeight:400px;dialogWidth:1000px;status:no;scroll:no;center:yes;help:no");	
		  	 }
		  	 
		  	 
		  	 function findSystemLog() {
		  	 	document.getElementById("myForm").submit();
		  	 }
		</script>
	</head>
	<body>
		<s:form action="systemLogManager" id="myForm" namespace="/PaySystem">
		<s:iterator id="systemLogList" value="info.result">
			<s:set name="merno" value="#systemLogList.merno"/>
		</s:iterator>
			商户号:<input name="merno" type="text" value="<s:property value='merno'/>"><br/>
								记录时间:
							<input name="operTime" type="text" >
							至
							<input name="recordtime" type="text" ><br/>
								操作员用户名：
							<input name="systemLog.userName" type="text" ><br/>
							<input type="submit" value="查询" />
							<div class="ctable">
			<table class="listab" border="0" cellspacing="0" cellpadding="0">
				<tr class="listabtit">
					<td width="25%">
						记录时间
					</td>
					<td width="25%">
						操作用户名
					</td>&nbsp;
					
					<td width="25%">
						功能模块
					</td>
					<td width="25%">
						修改内容
					</td>
				</tr>
				<!-- 集合不为空
				
				 -->
				<s:iterator id="systemLogList" value="info.result">
					<tr>
						<td>
							<s:property value="#systemLogList.operTime" />
							&nbsp;
						</td>
						<td>
							<s:property value="#systemLogList.userName" />
							&nbsp;
						</td>
						<td>
							<s:property value="#systemLogList.rescReow" />
							&nbsp;
						</td>
						<td>
								<input type="button" value="查看详细"
								onclick="getLogDetails(<s:property value='#systemLogList.id'/>)";/>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
					<pages:pages value="info" beanName="info"
				formName="getElementById('myForm')" />	

		
				</s:form>
	</body>
</html>