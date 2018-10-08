<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>   
    <title>上传跟踪单号</title>

	<link rel="stylesheet" type="text/css" href="../css/head.css">
    

    <link href="css/css.css" rel="stylesheet" type="text/css">
  </head>
  <base target="_self">
  <body>

  <s:form action="uploadNumber" method="post" theme="simple" onsubmit="return checkTrackNo();">
  	<table width=700 border=0 align=center cellPadding=0 cellSpacing=1 bgcolor="#CB7610">
		<tr align="center" bgcolor="#cccccc">
	      <td height="30" bgcolor="#EACE96">流水号</td>
		  <td bgcolor="#EACE96">商户订单号</td>
		  <td bgcolor="#EACE96">交易日期</td>
		  <td bgcolor="#EACE96">交易金额</td>
		  <td bgcolor="#EACE96">支付状态</td>
		  <td bgcolor="#EACE96">填写单号</td>
	  	</tr>
		<tr align="center">
			<td bgcolor="#FFF6E4">
	  			<s:property value="trade.orderNo"/>
				<s:hidden name="trade.id"></s:hidden>
			</td>
			  <td bgcolor="#FFF6E4">
			<s:property value="trade.merchantOrderNo"/>
	   				</td>
			  <td bgcolor="#FFF6E4">
			<s:property value="trade.tradeTime"/>
	   				</td>
			  <td bgcolor="#FFF6E4">
			<s:property value="trade.tradeAmount"/>
	   				</td>
			  <td bgcolor="#FFF6E4">
			<s:property value="states.getStateName(trade.tradeState,1)" escape="false"/>&nbsp;
	   				</td>
			<td bgcolor="#FFF6E4">
			<s:select name="mail" list="#{'':'','EMS':'EMS','DHL':'DHL','UPS':'UPS','TNT':'TNT','FedEx':'FedEx','DMS':'DMS','ChinaPost':'ChinaPost','HkPost':'HkPost','NNN':'其他'}" listKey="key" listValue="value"></s:select>
	   		<s:textfield name="trade.isTrackNo" id="trackNo"></s:textfield>
  			</td>
 			  </tr>
 		  </table>
        <br />
  		<center> <s:submit cssClass="input_button_01" value="上传"/> </center>
  	</s:form>

    <!--尾部begin-->

  </body>
</html>
<script type="text/javascript">
function checkTrackNo(){
	var banjiao=/[\uFF00-\uFFFF]/g;
	var trackNo=document.getElementById("trackNo").value
	if(banjiao.test(trackNo)){
		alert("请输入半角字符！");
	return false;
	}
	
}
</script>
