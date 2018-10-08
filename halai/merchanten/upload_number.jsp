<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>   
    <title>Upload tracking NO</title>

	<link rel="stylesheet" type="text/css" href="../css/head.css">
    

    <link href="css/css.css" rel="stylesheet" type="text/css">
  </head>
  <base target="_self">
  <body>
  <!--	<s:action name="indexMenu" executeResult="true"/>  -->
  <s:form action="uploadNumberEn" method="post" theme="simple">
  	<table width=700 border=0 align=center cellPadding=0 cellSpacing=1 bgcolor="#CB7610">
		<tr align="center" bgcolor="#cccccc">
	      <td height="30" bgcolor="#EACE96">Ecpss Order NO</td>
		  <td bgcolor="#EACE96">Merchant Order NO</td>
		  <td bgcolor="#EACE96">Transaction Date</td>
		  <td bgcolor="#EACE96">Transaction Amount</td>
		  <td bgcolor="#EACE96">Payment Status</td>
		  <td bgcolor="#EACE96">Fill in</td>
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
			<s:property value="states.getStateNameEn(trade.tradeState,1)" escape="false"/>&nbsp;
	   				</td>
			<td bgcolor="#FFF6E4">
			<s:select name="mail" list="#{'':'','EMS':'EMS','DHL':'DHL','UPS':'UPS','TNT':'TNT','FedEx':'FedEx','DMS':'DMS','NNN':'其他'}" listKey="key" listValue="value"></s:select>
	   		<s:textfield name="trade.isTrackNo"></s:textfield>
  			</td>
 			  </tr>
 		  </table>
        <br />
  		<center> <s:submit cssClass="input_button_01" value="Upload"/> </center>
  	</s:form>

    <!--尾部begin-->

  </body>
</html>
