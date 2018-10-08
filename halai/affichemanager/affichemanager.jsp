<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<html>
<head>
		<title>公告管理主页面</title>
        <link href="../css/other.css" rel="stylesheet" type="text/css" />
        <style>
         td,tr,table{ font-size:12px;}
        </style>
		<script language="JavaScript" src="../js/util.js"></script>
		<script type="text/javascript">
	//跳转到添加公告
	function add(){ 
	    
	     openWindow('../PaySystem/toAddAfficheManager.action','12');
	}
	
	//修改公告
	function update(id){
		//alert(id);
		openWindow('../PaySystem/toUpdateAffiche.action?iid='+id,'12');
	}
	</script>
	</head>

	<body>
		<input type="button" value="新增公告" onClick="add();">
			<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#666666">
<tr>
					<td height="30" align="center" bgcolor="#CCCCCC" style="font-size:14px"><strong>
					  公告内容					</strong></td>
  <td height="30" align="center" bgcolor="#CCCCCC" style="font-size:14px"><strong>
						公告时间					</strong></td>
  <td height="30" align="center" bgcolor="#CCCCCC" style="font-size:14px"><strong>
						链接地址					</strong></td>
  <td height="30" align="center" bgcolor="#CCCCCC" style="font-size:14px"><strong>
						操作					</strong></td>
			  </tr>
				<s:iterator id="affichemanager" value="amList">
					<tr>
						<td height="30" align="center" bgcolor="#FFFFFF">
							<s:property value="#affichemanager.afficheContext" />
&nbsp;						</td>
						<td height="30" align="center" bgcolor="#FFFFFF">
							<s:property value="#affichemanager.affichedate" />
&nbsp;						</td>
						<td height="30" align="center" bgcolor="#FFFFFF">
							<a href="#"><s:property value="#affichemanager.url" /> </a>
&nbsp;						</td>
						<td height="30" align="center" bgcolor="#FFFFFF">
							<input type="button" name="pt" value="update"
								onclick="update(<s:property value="#affichemanager.id" />);" />
							&nbsp;

							<input type="button" value="删除"
								onclick="openWindow('../PaySystem/deleteAfficheManager.action','iid=<s:property value='#affichemanager.id'/>');" />						</td>
					</tr>
				<input type="hidden" name="iid"
					value="<s:property value="#affichemanager.id" />" />				</s:iterator>
			</table>

 </body>
</html>
