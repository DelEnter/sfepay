<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>列为黑卡</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<%@ include file="../include/dialog.jsp"%>
	<script language="JavaScript" src="../js/util.js"></script>
	 <script language="JavaScript" type="text/JavaScript">
		function addBackCard() {		
			document.getElementById("form1").action="backCardMake";
			document.getElementById("form1").submit();
		}
		function delBackCard(id) {	
			if(confirm("确认删除吗？")){
			window.location="../PaySystem/delBackCardMake.action?back.id="+id;
			}
		}
	</script> 
  </head>
  
  <body>
  	<center>
  		<h3>列为黑卡</h3>
  		<s:form action="toBackCardMake" method="post" theme="simple" name="form1" id="form1">
	  	<table align="center">
			<tr class=TR_Title>
	  				<td>ip</td>
	  				<td colspan="2">
	  					<s:textfield name="back.ip"/>
	  				</td>
	  				<td>邮箱</td>
	  				<td colspan="2">
	  					<s:textfield name="back.email"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>卡号</td>
	  				<td colspan="2">
	  					<s:textfield name="back.cardno" maxlength="16"/>
	  				</td>
	  				<td>cookie</td>
	  				<td colspan="2">
	  					<s:textfield name="back.cookie"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>备注</td>
	  				<td colspan="3">
	  					<s:textfield name="back.remark"/>
	  				</td>
	  				<td>是否模糊</td>
	  				<td>
	  					<input type="checkbox" name="yesOrNo" value="0" 
	  					<s:if test="%{yesOrNo==0}">
               			 checked="checked"
            				</s:if>
	  					/>
	  				</td>
	  			</tr>
	  			<tr class=font-align>
				<td colspan="3" align="center"><br>
					<input type="button" value="列为黑卡" onclick="addBackCard()" >
				</td>
				<td align="center"><br>
					<input type="submit" value="搜  索" >
				</td>
			</tr>
	  		</table>
	  <TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="80%">
		<tr align="center" bgColor=#cccccc>
			<td width="20%">卡号</td>
			<td width="20%">email</td>
			<td width="20%">Ip</td>
			<td width="15%">cookie</td>
			<td width="15%">备注</td>
			<td width="10%">操作</td>
		</tr>
		<s:iterator id="backList" value="info.result">
		<tr align="center">
		<td><s:property value="#backList[0].cardno" /></td>
		<td><s:property value="#backList[0].email" /></td>
		<td><s:property value="#backList[0].ip" /></td>
		<td><s:property value="#backList[0].cookie" /></td>
		<td><s:property value="#backList[0].remark" /></td>
		<td><a href="#" onclick="delBackCard('<s:property value="#backList[0].id" />')">删除</a></td>
		</tr>
		</s:iterator>
	</table>
	<div align="center">
	<pages:pages value="info" beanName="info" formName="getElementById('form1')" />
	</div>
  		</s:form>
  	</center>&nbsp;
  	<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("成功列为黑卡!");
		}
		if(temflag==2){
			alert("成功删除黑卡!");
		}
	</script>	
  </body>
</html>
