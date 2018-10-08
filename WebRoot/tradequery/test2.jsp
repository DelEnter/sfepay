<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>终端统计</title>
<script language="JavaScript" src="../js/util.js"></script>
</head>
<div align="center" >
	<h3>终端统计</h3>
</div>
<s:form name="formu" action="tradeinfothird" id="formu" method="post" theme="simple">
<table align="center">
			<tr class=TR_Title>
		 		<td>终端号</td>
	 			<td>
		 			<s:select name="tradeinfo.VIPTerminalNo" list="terminalList" headerKey="" headerValue="----"  />
		 		</td>
	 		
		 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
		 		</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >
				</td>
				
			</tr>
	</table>
	</br></br>
	<table cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
		<tr bgColor=#cccccc>
			<td>交易日期</td>
			<td>失败金额</td>
			<td>失败笔数</td>
			<td>成功金额</td>
			<td>成功笔数</td>
			<td>成功比率</td>
			<td>拒付金额</td>
			<td>拒付笔数</td>
			<td>拒付比率</td>
		</tr>
		<s:iterator id="tradeThird" value="tradeThirdList">
		<tr>
			<td><s:property value="#tradeThird[0]" />&nbsp;</td>
		
			<td><s:property value="#tradeThird[3]" />&nbsp;</td>
			<td><s:property value="#tradeThird[4]" />&nbsp;</td>
			<td><s:property value="#tradeThird[5]" />&nbsp;</td>
			<td><font color="green" size="3"><s:property value="#tradeThird[6]" />&nbsp;</font></td>
			<td>
				<font color="red" size="3">
					<s:if test="#tradeThird[6] == null">0%</s:if>&nbsp;
					<s:property value="caclulate.getValue(#tradeThird[6],#tradeThird[4])"/>&nbsp;
				</font>
			</td>
			
			<td><s:property value="#tradeThird[7]" />&nbsp;</td>
			<td><s:property value="#tradeThird[8]" />&nbsp;</td>
			<td>
				<font color="red" size="3">
					<s:if test="#tradeThird[8] == null">0%</s:if>&nbsp;
					<s:property value="caclulate.getValue(#tradeThird[8],#tradeThird[6])"/>&nbsp;
				</font>
			</td>
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
        <script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1 && temflag != 0){
			
		}else if(temflag==2){
			alert("商户号不存在");
		}		
	</script>	
</s:form>











