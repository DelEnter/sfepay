<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>查看导入的银行退款</title>
</head>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
        window.location="../PaySystem/refundByBankQuery";
	}	
	function shanchuantuikuan(){
			//window.open ('../refund/importRefundHistory.jsp','newwindow','menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=1,resizable=1,height=300,width=600') 
			window.showModalDialog('../refund/importRefundHistory.jsp', window,'dialogHeight:325px;dialogWidth:650px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
		}
	function shanchu(rhid){
		if(confirm("确定要删除吗?")){
			window.location="../PaySystem/deleteDouble.action?rhid="+rhid;
		}
	}
</script>
<div align="center">
	<h3>查看退款历史导入记录</h3>
</div>
<s:form name="formu" id="formu" action="refundByBankQuery" method="post" theme="simple">
<div align="left">
	<a href="#" onclick="shanchuantuikuan()">上传退款Excel</a>
</div>
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
				<tr class=TR_Title>
					<td>
						卡号
					</td>
					<td>
						<s:textfield name="cardNo" />
					</td>
					<td>
					  批次号
					</td>
					<td>
					<s:select name="batchnos" list="batchnoList"  headerKey="" headerValue="----" />

					</td>
				</tr>
				<tr>
		 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
		 		</td>
	 		</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="搜索"/>
					</td>
				</tr>	
			</TABLE>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr bgcolor="#cccccc">
		<td>序列</td>
		<td>卡号</td>
		<td>交易金额</td>
		<td>退款金额</td>
		<td>交易日期</td>
		<td>终端号</td>
		<td>授权号</td>
		<td>操作</td>
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr>
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p.cardNo" /></td>
		<td><s:property value="#p.tradeAmount" /></td>
		<td><s:property value="#p.refundAmount" /></td>
		<td><s:property value="#p.tradeTime" /></td>
		<td><s:property value="#p.terminalNo" /></td>
		<td><s:property value="#p.authorizationNo" /></td>
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="forms(0)" />
</div>
<div align="center">
重复的数据
</div>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr bgcolor="#cccccc">
		<td>序列</td>
		<td>卡号</td>
		<td>交易金额</td>
		<td>退款金额</td>
		<td>交易日期</td>
		<td>终端号</td>
		<td>授权号</td>
		<td>操作</td>
	</tr>
	<s:iterator id="c" value="rhList" status="i">
	<tr>
		<td><s:property value="#i.index+1" /></td>
		<td><s:property value="#c.cardNo" /></td>
		<td><s:property value="#c.tradeAmount" /></td>
		<td><s:property value="#c.refundAmount" /></td>
		<td><s:property value="#c.tradeTime" /></td>
		<td><s:property value="#c.terminalNo" /></td>
		<td><s:property value="#c.authorizationNo" /></td>
		<td><a href="#" onclick="shanchu(<s:property value="#c.id"/>)">干掉</a></td>
	</tr>
	</s:iterator>
</table>
 <!-- 下面这段script代码必须放在form体的最后  
             loadcalendar方法的五个参数分别解释如下：
             1、日期显示文本框的ID号
             2、触发日历控件显示的控件ID号
             3、要显示的日期格式，%Y表示年，%m表示月，%d表示日
             4、是否带周显示，默认是不带
             5、是否带时间显示，默认是不带
             6、日历显示文字的语言，默认是中文 -->
        <script language="javascript" type="text/javascript">

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>











