<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>交易查询</title>
</head>
<div align="center" >
	<h3>交易详情查询</h3>
</div>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){

        window.location="../PaySystem/tradeQuery.action";
}
function bocsales(orderNo){
	openWindow('../PaySystem/bocSales.action?tradeid='+orderNo,'12')
}
</script>
<s:form name="formu" id="formu" action="tradeQuery" method="post" theme="simple">

<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr bgColor=#cccccc>
		<td>流水号</td>
		<td>商户订单号</td>
		<td>商户号</td>
		<td>交易日期</td>
		<td>交易金额</td>
		<td>交易币种</td>
		<td>人民币</td>
		<td>通道</td>
		<td>支付情况</td>
		<td>是否勾兑</td>
		<td>是否退款</td>
		<td>退款金额</td>
		<td>是否冻结</td>
		<td>是否划款</td>
		<td>是否拒付</td>
		<td>是否传图</td>
		<td>是否传号</td>
		<td>处理时间</td>
		<td>处理人</td>
		<td>授权号</td>
		<td>批次号</td>
		<td>终端号</td>
		<td>备注</td>
		<td>划款申请时间</td>
		<td>原始金额</td>
		<td>原始RMB</td>	
		<td>交易网址</td>	
		<s:if test="#session.user.userName == 'timzhang'  || #session.user.userName == 'yepeng'  || #session.user.userName == 'smith'  ">
		<td>TC处理</td>	
		</s:if>
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr>
		<td><s:property value="#p[0]" />&nbsp;</td>
		<td><s:property value="#p[1]" />&nbsp;</td>
		<td><s:property value="#p[2]" />&nbsp;</td>
		<td><s:property value="#p[3]" />&nbsp;</td>
		<td><s:property value="#p[4]" />&nbsp;</td>
		<td><s:property value="states.getCurrencyTypeByNo(#p[5])" />&nbsp;</td>
		<td><s:property value="#p[6]" />&nbsp;</td>
		<td><s:property value="#p[7]" />&nbsp;</td>
		<td><s:property value="states.getStateName(#p[8],1)" escape="false" />&nbsp;</td>
		<td><s:property value="states.getStateName(#p[8],5)" escape="false"/>&nbsp;</td>
		<td><s:property value="states.getStateName(#p[8],2)" escape="false"/>&nbsp;</td>
		<td><s:property value="#p[9]" />&nbsp;</td>
		<td><s:property value="states.getStateName(#p[8],4)" escape="false"/>&nbsp;</td>
		<td><s:property value="states.getStateName(#p[8],8)" escape="false"/>&nbsp;</td>
		<td><s:property value="states.getStateName(#p[8],3)" escape="false"/>&nbsp;</td>
		<td><s:if test="#p[10]!=null">
				是
			</s:if>
			<s:else>
				否
			</s:else>&nbsp;
		</td>
		<td><s:property value="#p[11]" />&nbsp;</td>
		<td><s:property value="#p[12]" />&nbsp;</td>
		<td><s:property value="#p[13]" />&nbsp;</td>
		<td><s:property value="#p[14]" />&nbsp;</td>
		<td><s:property value="#p[15]" />&nbsp;</td>
		<td><s:property value="#p[17]" />&nbsp;</td>
		<td><s:property value="#p[16]" />&nbsp;<s:property value="#p[19]" /></td>
		<td><s:property value="#p[18]" />&nbsp;</td>
		<td><s:property value="#p[20]" />&nbsp;</td>	
		<td><s:property value="#p[21]" />&nbsp;</td>
		<td><s:property value="#p[23]" />&nbsp;</td>							
		<s:if test=" #session.user.userName == 'yepeng' || #session.user.userName == 'timzhang'  || #session.user.userName == 'smith' ">
		<td>
			<s:if test="#p[8].substring(0,1)==2">
				<a href="#" onclick="bocsales(<s:property value="#p[22]" />)">SALES</a> 
			</s:if>&nbsp;
		</td>
		</s:if>
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="getElementById('formu')" />
</div>
   <!-- 下面这段script代码必须放在form体的最后  
             loadcalendar方法的五个参数分别解释如下：
             1、日期显示文本框的ID号
             2、触发日历控件显示的控件ID号
             3、要显示的日期格式，%Y表示年，%m表示月，%d表示日
             4、是否带周显示，默认是不带
             5、是否带时间显示，默认是不带
             6、日历显示文字的语言，默认是中文 -->
        <script language="javascript" type="text/javascript">

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "en");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "en");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>











