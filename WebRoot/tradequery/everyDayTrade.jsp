<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>每日详情</title>
<script language="JavaScript" src="../js/util.js"></script>
</head>
<div align="center" >
	<h3>每日详情</h3>
</div>
<s:form name="formu" id="formu" method="post" theme="simple" action="everyDayTradeinfo">
<table align="center">
			<tr class=TR_Title>	 		
		 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>		 		</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >				</td>
			</tr>
  </table>
  
<table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#666666">	
<s:property value="showTable" escape="false"/>

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

            loadcalendar('start_time', 'start_time', '%Y-%m', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m', false, true, "cn");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>
