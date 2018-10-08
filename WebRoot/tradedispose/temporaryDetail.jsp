<%@ page language="java" 
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>vip交易详情</title>
</head>
<body>
<div align="center"><h2>vip交易详情</h2></div>
<div style="width: 70%;margin-left: 15%">
<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#333333">
<tr>
<td colspan="2" width="50%" align="left" bgcolor="#cccccc" style="font-size: 20px" >持卡人信息:
</td>
<td colspan="2" width="50%" align="left" bgcolor="#cccccc" style="font-size: 20px">MaxMind信息:
</td>
</tr>
<s:iterator value="tempDetail" id="s">
<tr>
<td height="40" width="15%" align="center" bgcolor="#cccccc" >商户号: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[1].substring(0,4)"/></td>
<td height="40" width="15%" align="center" bgcolor="#cccccc" >风险分数: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[22]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >订单号: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[1]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >发卡行: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[23]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >交易时间: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[5]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >发卡国家: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[24]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >金额: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[2]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >发卡电话: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[25]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >币种: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="states.getCurrencyTypeByNo(#s[3])"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >ip所在区号: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[26]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >金额(RMB):</td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[4]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >ip所在地区邮编: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[27]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >交易卡号: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="getCarNo(#s[6]).substring(0,6)"/>******<s:property value="getCarNo(#s[6]).substring(12)"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >距离: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[33]"/>&nbsp;Km</td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >电子邮件: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[7]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >ip所在国家: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[30]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >IP地址: </td>
<td height="40" bgcolor="#cccccc" > <s:property value="#s[8]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >使用代理的几率: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[34]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >姓名: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[9]"/>  <s:property value="#s[10]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >是否使用代理: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[32]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >输入的发卡行: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[11]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >客户电话位置: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[36]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >国家: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[12]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >ip所在国家简码: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[31]"/></td>

</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >城市: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[16]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >ip所在城市: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[39]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >州: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[17]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >ip所在地区(州): </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[28]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >地址: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[13]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >邮编是否一致: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[35]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >物品信息: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="#s[14]"/></td>
<td height="40"  align="center" bgcolor="#cccccc" >所属类型: </td>
<td height="40" bgcolor="#cccccc" ><s:property value="webType"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >电话: </td>
<td colspan="3" height="40" bgcolor="#cccccc" ><s:property value="#s[20]"/></td>

</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >邮政编码: </td>
<td colspan="3" height="40" bgcolor="#cccccc" ><s:property value="#s[15]"/></td>
</tr>
<tr>
<td height="40"  align="center" bgcolor="#cccccc" >交易网站: </td>
<td colspan="3" height="40" bgcolor="#cccccc" ><s:property value="#s[18]"/>&nbsp;&nbsp;&nbsp;
<span style="font-size:14px">（是否风险：<s:if test="%{isRisk==1}"><span style="color:red">是</span></s:if>
		<s:else>无</s:else> |
开通时间：<s:property value="openTime"/> |
交易笔数：第<s:property value="tradeNum"/>）</span>
</td>
</tr>
</s:iterator>
</table>
</div>
</body>
</html>