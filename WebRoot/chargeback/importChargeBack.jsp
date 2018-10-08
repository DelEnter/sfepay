<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html>
  <head>
    <title>导入银行拒付数据</title>
  </head>
  <body>
  	<center>
	    <s:form action="importChargebackTrade" namespace="/PaySystem" theme="simple" method ="POST" enctype ="multipart/form-data">
		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="600" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
		    	<tr>
					<td width="30%" align="right" >
						<div align="right">请选择文件：</div>
					</td>
					<td align="right" >
						<div align="left"><s:file name="fileName"/></div>
					</td>
				</tr>
				<tr >
				<td width="30%" align="right" colspan="2">
					<s:property value="messageAction"/>
					</td>
				</tr>
				<tr >
				<td width="30%" align="right" >
						<input type="submit" value="上传"/>
					</td>
					<td align="right" >
					</td>
				</tr>
		    </table>
	    </s:form>
    </center>
    
  </body>
</html>
