<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>交易数据分析</title>
</head>
<div align="center" >
	<h3>交易数据分析</h3>
</div>
<script language="JavaScript" type="text/JavaScript">
window.onload=function(){
	var checkMer=document.getElementById("checkMer").value;
	if(checkMer=="1"){
		document.getElementById("noMerchant").checked=true;
	}
}
function viewDetails(type,cardNo,startDate,endDate){
	window.open("../PaySystem/viewDetails.action?cardNo="+cardNo+"&startDate="+startDate+"&endDate="+endDate+"&webtype="+webtype+"&detailType="+type);
}
function addBackList(type,cardNo){
	document.getElementById("formu").action="addBackList.action?cardNo="+cardNo+"&detailType="+type;
	document.getElementById("formu").submit();
}
</script>

	<s:form id="formu" action="tradeMonitor" method="post" theme="simple">
	<table align="center">
		<tr class=TR_Title>
	 		<td align="right">查询项：</td>
	 		<td>
	 		<s:select name="queryType" list="#{'1':'全卡号','2':'卡号前6','3':'卡号前9','4':'email','5':'Ip'}" listKey="key" listValue="value"/></td>
	 		<td align="right">错误次数：</td>
	 		<td>
	 		<s:select name="failNum" list="#{'2':'2','3':'3','4':'4','5':'5'}" listKey="key" listValue="value"/>
	 		</td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
		 		</td>
		 		<td>网站分类</td>
		 		<td>
                     <s:select name="webtype" theme="simple" list="webTypeList" headerKey="" headerValue="--请选择--" />
		 		</td>
	 		</tr>
	 		<tr class=TR_Title>
	 			<td>不同商户号：</td>
	 			<td colspan="3">
	 				<input type="checkbox" id="noMerchant" name="noMerchant" value="1">
	 				<input type="hidden" id="checkMer" name="checkMer" value="<s:property value='noMerchant'/>">
	 			</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >
				</td>
			</tr>
		</table>
		<div align="center"><s:if test="%{msg!=null}"><s:property value="msg" /></s:if></div>
	<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="80%">
		<tr align="center" bgColor=#cccccc>
			<td width="50%">查询项</td>
			<td width="10%">总交易次数</td>
			<td width="10%">失败次数</td>
			<td width="20%">操作</td>
		</tr>
		<s:iterator id="risk" value="info.result">
		<tr align="center">
			<s:if test="%{queryType==1||queryType==2||queryType==3}">
			<s:if test="{#risk[3]}==1">
			<td><span style="color:red"><a href="#" style="color: red" onclick="viewDetails('<s:property value="queryType" />','<s:property value="#risk[0]" />','<s:property value="startDate" />','<s:property value="endDate" />')"><s:property value="getCarNo(#risk[0])" /></a></span></td>
			</s:if>
			<s:else>
			<a href="#" onclick="viewDetails('<s:property value="queryType" />','<s:property value="#risk[0]" />','<s:property value="startDate" />','<s:property value="endDate" />')"><s:property value="getCarNo(#risk[0])" /></a>
			</s:else>
		</s:if>
		<s:else>
		<td><a href="#" onclick="viewDetails('<s:property value="queryType" />','<s:property value="#risk[0]" />','<s:property value="startDate" />','<s:property value="endDate" />')"><s:property value="#risk[0]" /></a></td>
		</s:else>
		<td><s:property value="#risk[1]" /></td>
		<td><s:property value="#risk[2]" /></td>
		<s:if test="%{queryType==1||queryType==2||queryType==3}">
		<td><a href="#" onclick="addBackList('<s:property value="queryType" />','<s:property value="getCarNo(#risk[0])" />')">添黑名单</a></td>
		</s:if>
		<s:if test="%{queryType==4}">
		<td><a href="#" onclick="addBackList('<s:property value="queryType" />','<s:property value="#risk[0]" />')">添黑名单</a></td>
		</s:if>
		<s:if test="%{queryType==5}">
		<td><a href="#" onclick="addBackList('<s:property value="queryType" />','<s:property value="#risk[0]" />')">添黑名单</a></td>
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

