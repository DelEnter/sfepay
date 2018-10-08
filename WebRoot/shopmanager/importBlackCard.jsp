<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <title>导入黑卡</title>
  </head>
      <script language="JavaScript" type="text/JavaScript">
</script> 
  <body>
  	<center>
    <H3>导入卡号到黑卡库</H3>
    <div align="center">
    	<s:property value="messageAction" />
    </div>
	    <s:form action="importBlackCard" theme="simple" method ="POST" enctype ="multipart/form-data">
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
		    	<tr>
					<td width="30%" align="right" >
						<div align="right">卡号存在的位置：</div>
					</td>
					<td align="right" >
						<div align="left"><input type="text" name="importType"/></div>
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
