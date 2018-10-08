<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<html>
<head>
    <title>代理商户管理</title>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  	<script language="JavaScript" src="../js/util.js"></script>
  </head>
      <script type="text/javascript">
		function addCourse(f) {	
			//alert(f.action);
			//goFormWindow(f,"../PaySystem/addAgentsMerchant");
			document.getElementById("myForm").submit();
			//alert(f);	
		}
		
		//删除代理商户
	function deleteAgentMerchant(agentId) {
		//alert(agentId);
		window.location.href="../PaySystem/deleteAgentMerchant.action?agentId="+agentId;
		
		//goFormWindow(f,"../PaySystem/deleteAgentMerchant?agentId="+agentId);
	}
	
	</script> 
  <body>
    <H3>代理商户管理</H3>
	    <s:form  action="addAgentsMerchant" theme="simple" id="myForm">
	      <table width="500" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="20%" align="right">代理商户号: </td>
              <td><input type="text" name="proMerno" />&nbsp;</td>
            </tr>
            <tr>
              <td align="right">子商户号:</td>
              <td><input type="text" name="merno" /></td>
            </tr>
            <tr>
              <td align="right">密码:</td>
              <td><input type="password" name="pwd" /> </td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td><input type="button" onClick="addCourse(this.form);" value="添加" class="windows_icon1"/></td>
            </tr>
          </table>
	    </s:form>
<hr />
		代理商户号：
		<s:if test="mernolist != null && mernolist.size>0">
			<s:select name="agentsMerNo" list="mernolist" headerKey=""
				headerValue="----" id="agenid"
				onchange="findAgentsMerchantManager();" />
		</s:if>
		<br />
		
        
        <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#666666">
  <tr>
    <td align="center" bgColor=#cccccc><span style="font-weight: bold">序号	</span></td>
			<td align="center" bgColor=#cccccc><span style="font-weight: bold">代理商户号			</span></td>
			<td align="center" bgColor=#cccccc><span style="font-weight: bold">商户号			</span></td>
			<td align="center" bgColor=#cccccc><span style="font-weight: bold"> 操作			</span></td>
  </tr>
<s:iterator id="a" value="agentsMerList" status="s">
			<tr>
				<td align="center" bgcolor="#FFFFFF">
				  <s:property value="#s.index+1" />				</td>
		  <td align="center" bgcolor="#FFFFFF">
	<s:property value="#a[0].agentsMerchantNo" />
					<!-- 
					<s:select list="mernolist" listKey="id"/> -->
			</td>
		  <td align="center" bgcolor="#FFFFFF">
	<s:property value="#a[1].merno" />
			  </td>
		  <td align="center" bgcolor="#FFFFFF">
	<input type="button" value="delete"
						onclick="deleteAgentMerchant('<s:property value="#a[0].id" />');" />
	</td>
			  <br />
			</tr>
		</s:iterator>
</table>

</body>
</html>
