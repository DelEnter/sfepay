<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>敏感信息管理</title>
</head>
<div align="center" >
	<h3>敏感信息管理</h3>
</div>
<script language="JavaScript" type="text/JavaScript">
function addSensitive(){
	
	if(document.getElementById("billNo").value==""&&document.getElementById("email").value==""&&document.getElementById("tradeUrl").value==""&&document.getElementById("products").value==""&&document.getElementById("ip").value==""){
		alert("至少有一项不能为空！");
		return false;
	}
	if(confirm("确认添加吗？")){
	document.getElementById("addformu").submit();
	}
}
function query(){
	document.getElementById("formu").submit();
}
function delSen(id){
	if(confirm("确认删除吗？")){
	window.location="../PaySystem/delSensitive.action?senId="+id;
	}
}
</script>
<s:form id="addformu" action="addSensitive" method="post" theme="simple">
<table align="center">
		<tr class=TR_Title>
	 		<td align="right">敏感订单号：</td>
	 		<td><input type="text" id="billNo" name="senInfo.billNo"></td>
	 		<td align="right">敏感email：</td>
	 		<td><input type="text" id="email" name="senInfo.email"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">敏感交易网址：</td>
	 		<td><input type="text" id="tradeUrl" name="senInfo.tradeUrl"></td>
	 		<td align="right">敏感产品：</td>
	 		<td><input type="text" id="products" name="senInfo.products"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">Ip：</td>
	 		<td><input type="text" id="ip" name="ip"></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="addSensitive()" value="添加" >
				</td>
	 		</tr>
	</table>
	</s:form>
	<hr/>
	<s:form id="formu" action="sensitive" method="post" theme="simple">
	<table align="center">
		<tr class=TR_Title>
	 		<td align="right">敏感订单号：</td>
	 		<td><input type="text" id="qbillNo" name="qbillNo" value="<s:property value='qbillNo' />"></td>
	 		<td align="right">敏感email：</td>
	 		<td><input type="text" id="qemail" name="qemail" value="<s:property value='qemail' />"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">敏感交易网址：</td>
	 		<td><input type="text" id="qtradeUrl" name="qtradeUrl" value="<s:property value='qtradeUrl' />"></td>
	 		<td align="right">敏感产品：</td>
	 		<td><input type="text" id="qproducts" name="qproducts" value="<s:property value='qproducts' />"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">Ip：</td>
	 		<td><input type="text" id="qip" name="qip" value="<s:property value='qip' />"></td>
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
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="query()" value="查询" >
				</td>
				
			</tr>
	</table>
	<br/>
	<br/>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="80%">
	<tr align="center" bgColor=#cccccc>
		<td width="10%">订单号</td>
		<td width="15%">email</td>
		<td width="13%">IP</td>
		<td width="20%">交易网站</td>
		<td width="15%">商品信息</td>
		<td width="12%">添加时间</td>
		<td width="10%">添加人</td>
		<td width="5%">操作</td>
	</tr>
	<s:iterator id="sen" value="info.result">
	<tr align="center">
		<td><s:property value="#sen.billNo" />&nbsp;</td>
		<td><s:property value="#sen.email" />&nbsp;</td>
		<td><s:property value="#sen.ip1" />.<s:property value="#sen.ip2" />.<s:property value="#sen.ip3" />.<s:property value="#sen.ip4" />&nbsp;</td>
		<td><s:property value="#sen.tradeUrl" />&nbsp;</td>
		<td><s:property value="#sen.products" />&nbsp;</td>
		<td><s:property value="#sen.createTime" />&nbsp;</td>
		<td><s:property value="#sen.lastMan" />&nbsp;</td>
		<td><a href="#" onclick="delSen('<s:property value="#sen.id" />')">删除</a></td>
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

