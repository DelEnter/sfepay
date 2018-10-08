<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<head>
	<title>新增页面</title>
	<script language="JavaScript" src="../js/util.js"></script>
	<LINK href="../css/head.css" type=text/css rel=stylesheet>
</head>
<script language="JavaScript" type="text/JavaScript">

	//跳转到添加网址管理
	function addSite(){ 
		var merno = document.getElementById("merno").value;
		//alert(merno);
	   openWindow('../PaySystem/toAddWebSiteManager.action?merchant.merno='+merno,'12');
	}
	
	//修改网址管理
	function update(id){
		openWindow('../PaySystem/toUpdateWebSiteManager.action?website.id='+id,'12')
	}
</script>
<html>
<body>
		<center>
			<h3>商户网址管理</h3>
			<s:form action="webSiteManager" id="myForm" namespace="/PaySystem">
				<table align="center">
					<tr>
						<td>商户号:<s:textfield name="merchant.merno" id="merno" /></td>
						<td> 交易网址：<s:textfield name="website.tradeWebsite" id="tradeWebsite" /></td>
						<td> 返回网址：<s:textfield name="website.website" id="website"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="搜索" /> &nbsp; &nbsp; </td>
					<td><input type="button" value="新增网址" onClick="addSite();"></td>
					</tr>
				</table>
			
			   <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
			    		<tr>
			    			<td bgColor=#cccccc>商户号</td>
			    			<td bgColor=#cccccc>交易网址</td>
			    			<td bgColor=#cccccc>返回网址</td>
			    			<td bgColor=#cccccc>绑定日期</td>
			    			<td bgColor=#cccccc>操作员</td>
			    			<td bgColor=#cccccc>操作</td>
			    		</tr>
			    		<s:iterator id="merchantWebSite" value="info.result" >
				    		<tr>
				    			<td><s:property value="#merchantWebSite[1].merno" />&nbsp;</td>
				    			<td><s:property value="#merchantWebSite[0].tradeWebsite"/>&nbsp;</td>
				    			<td><s:property value="#merchantWebSite[0].website" />&nbsp;</td>
				    			<td><s:property value="#merchantWebSite[0].executetime"/>&nbsp;</td>
				    			<td><s:property value="#merchantWebSite[0].operator"/>&nbsp;</td>
				    			<td>
				    			<input type="button" name="p" value="update" onclick="update(<s:property value="#merchantWebSite[0].Id" />);"/>&nbsp;
				    			<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteWebSiteManager.action','website.id=<s:property value='#merchantWebSite[0].Id'/>');" />
				    			</td>
				    		</tr>
			    		</s:iterator>
			    	</table>
			    	<pages:pages value="info" beanName="info" formName="getElementById('myForm')" />
			</s:form>
		</center>
	</body>
</html>




