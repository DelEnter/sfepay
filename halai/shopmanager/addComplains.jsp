<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<head>
	<title>新增客人投诉</title>
</head>

  <script language="JavaScript" type="text/JavaScript">
	function tianjia(f) {	
			goFormWindow(f,'../PaySystem/addComplains','');
	}
	function getCarNo(){
	
	}

</script>
<body>
<s:form action="addComplains" id="form1" namespace="/PaySystem" theme="simple">
<table width="800" border="0" cellspacing="1" cellpadding="1" align="center">
	<tr>
		<td align="center">
			<table align="center" border=0 cellpadding=1 cellspacing=1
				width="100%">
				<tr>
					<td align="right">
						E-mail ：
					</td>
					<td>
						<s:textfield name="email" onblur="getTradeNo()" size="35" value=""></s:textfield>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						商户号 ：
					</td>
					<td>
						<s:textfield name="cm.merchantNo" size="10" value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">
						流水订单号 ：
					</td>
					<td>
						<s:textfield name="cm.orderNo" size="35" value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">
						商户订单号 ：
					</td>
					<td>
						<s:textfield name="cm.merchantOrderNo" size="35" value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">
						类型 ：
					</td>
					<td>
						<s:select name="istuikuan" list="#@java.util.TreeMap@{'0':'未退款','1':'全额退款','2':'部分退款'}"  headerKey="" headerValue="----" />
					</td>
				</tr>
				<tr>
					<td align="right">
						内容 ：
					</td>
					<td>
						<s:textarea name="content" rows="10" cols="40" value=""></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" onClick="tianjia(this.form);" class="windows_icon1" value="S1ubmit" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</s:form>
  
</body>
</html>