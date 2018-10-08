<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<SCRIPT LANGUAGE="javascript">
　 function openVin(src){
     var tem='twoNumber?terminalno='+src;
　　  window.open(tem);
   }　　
　　</SCRIPT>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>终端交易情况</title>
<script language="JavaScript" src="../js/util.js"></script>
</head>
<div align="center" >
	<h3>终端交易情况</h3>
</div>
<s:form name="formu" id="formu" method="post" theme="simple" action="viptemManger">
<table align="center">
			<tr class=TR_Title>		 		
		 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>		 		</td>
	 		</tr> 		
		 		<td>终端号</td>
		 		<td><s:textfield name="vipterminalno" />
		 		通道<s:select name="channelId" list="chennelList" listKey="id" listValue="channelName" headerKey="" headerValue="----" />

		 		</td>
		 		<td>统计类型</td>
		 		<td><s:select name="cbtype" list="#{'':'所有','1':'拒付日期','0':'交易日期'}" listKey="key" listValue="value"/></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >				</td>
			</tr>
  </table>
	
	</br></br>
	<table cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
		<tr bgColor=#cccccc>
			<td>终端号</td>
			<td>成功笔数</td>
			<td>成功金额</td>
			<td>拒付笔数</td>
			<td>拒付金额</td>
			<td>拒付率</td>
		</tr>
		<s:set name="counts" value="0"/>
		<s:set name="countc" value="0"/>
		<s:iterator id="tradeSecond" value="temManger">
		<tr>
			<td><s:property value="#tradeSecond[2]" />&nbsp;</td>
			<td><s:property value="#tradeSecond[0]" /><s:set name="counts" value="#counts+#tradeSecond[0]"/>&nbsp;</td>
			<td><s:property value="#tradeSecond[3]" />&nbsp;</td>
			<td><s:property value="#tradeSecond[1]" />
				<s:if test="#tradeSecond[1]!=null ">
				<s:set name="countc" value="#countc+#tradeSecond[1]"/>
				</s:if>
			&nbsp;</td>
			<td>&nbsp;<s:property value="#tradeSecond[4]" />&nbsp;</td>
			<td><s:property value="caclulate.getValue2(#tradeSecond[1],#tradeSecond[0])"/>&nbsp;</td>
			<!--<td><a href="twoNumber.action?terminalno=<s:property value="#tradeSecond[2]" />">查看商户号</a>&nbsp;</td>-->
			<td><a onclick="openVin('<s:property value="#tradeSecond[2]" />')" href="#">查看商户号</a>&nbsp;</td>
		</tr>
		</s:iterator>
		<tr>
			<td>小计:</td>
			<td><s:property value="#counts"/></td>
			<td>&nbsp;</td>
			<td><s:property value="#countc"/></td>
			<td>&nbsp;</td>
			<td><s:property value="caclulate.getValue2(#countc,counts)"/>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
</table>
  <script language="javascript" type="text/javascript">

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
</s:form>