<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>Apply Refund</title>
</head>

<div align="center">
	<h3>商户交易成功率查询</h3>
</div>
<s:form name="formu" id="formu" action="tradeQueryfindAll" method="post">
<table align="center">
	<tr class=TR_Title>
		 		<td>商户号</td>
		 		<td>
		 			<input type="text" name="merchantNo" value="<s:property value='merchantNo'/>"/>
		 		</td>
	 		</tr>
	 		 <tr class=TR_Title>
	 		 	<td>交易日期</td>
		 		<td>
		 			 <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>---
		 		</td>
		 		<td colspan=2>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
	 			</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >
				</td>
				
			</tr>
	</table>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td>序列</td>
		<td>商户号</td>
		<td>总交易笔数</td>
		<td>成功交易笔数</td>
		<td>成功率</td>
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[0]" /></td>
		<td><s:property value="#p[1]" /></td>
		<td><s:property value="#p[2]" /></td>
		<td>&nbsp;<span style="color:red"><s:property value="#p[3]" />%</span></td>
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

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>











