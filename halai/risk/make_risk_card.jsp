<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>列为高风险卡</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
  </head>
  
  <body>
  	<center>
  		<h3>列为高风险卡</h3>
  		<s:form action="riskCardMake" method="post" theme="simple" name="form1">
	  		<table>
	  			<tr>
	  				<td>ip</td>
	  				<td>
	  					<s:textfield name="risk.ip"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>邮箱</td>
	  				<td>
	  					<s:textfield name="risk.email"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>卡号</td>
	  				<td>
	  					<s:textfield name="risk.cardno"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>cookie</td>
	  				<td>
	  					<s:textfield name="risk.cookie"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>备注</td>
	  				<td>
	  					<s:textfield name="risk.remark"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>
	  					<s:submit value="列为高风险卡"></s:submit>
	  				</td>
	  			</tr>
	  		</table>
  		</s:form>
  		<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("成功列为高风险卡!");
		}	
	</script>	
  	</center>&nbsp;
  </body>
</html>
