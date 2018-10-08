<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>   
    <title>上传跟踪单号</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
    <link href="css/css.css" rel="stylesheet" type="text/css">
  </head>
    <base target="_self">
  <body>
	<s:form name="form1" action="confirmImport" namespace="/merchant" theme="simple" method="post" enctype="multipart/form-data">
    		<table width=700 border=0 align=center cellPadding=0 cellSpacing=1 bgcolor="#CB7610">
<tr align="center" bgcolor="#cccccc">
    				<td bgcolor="#EACE96">
	  <div class="right">
          <div class="right_top" align=center >  &nbsp;&nbsp;&nbsp;<font  size=3 color="">上传跟踪号</font> </div>
          <div class="right_text">
				<TABLE borderColor=#ffffff cellSpacing=0 cellPadding=0 width="70%"
						align=center bgColor=#ffffff borderColorLight=#000000 border=0>
				<tr bgcolor="#EACE96">
					<td width="30%" align="left" colspan="2">
						<font color="red"><s:property value="messageAction"/></font>
					</td>
				</tr>
				<tr bgcolor="#EACE96">
					<td width="30%" align="right" >
						<div align="right">请选择文件：</div>
					</td>
					<td align="right" >
						<div align="left"><s:file name="trackingName"/></div>
					</td>
				</tr bgcolor="#EACE96">
				
				<tr bgcolor="#EACE96">
				<td width="30%" align="right" bgcolor="#EACE96">
						&nbsp;
					</td>
					<td align="right" >
					</td>
				</tr>
				<tr bgcolor="#EACE96">
					<td colspan="2">
					<div align="center">
					<font color="red">Tips: 上传的发货跟踪单号,将发送该跟踪号给持卡人.</font>
				</div>
					</td>
				</tr>	
				<tr bgcolor="#EACE96">
					<td colspan="2">
					提醒: 	上传的的文件类型应该和您下载的Excel文件格式一样.<br/>
					     发货单公司类型和发货单都必须填写.
					     
					     类型填写:   <font color="red"> EMS,DHL,UPS,TNT,FedEx,DMS,ChinaPost,HkPost </font> 必须填写这其中一种,否则不给添加
					</td>
				</tr>	
				
				</TABLE>
          </div>
    </div>
   			  </tr>
   		  </table>
          <br />
    		<center> 
    			<input type="submit" class="input_button_01" value="上传"/> 
    			<input type="button" class="input_button_01" onclick="closewin()" value="关闭"/> 
    		</center>
				</s:form>
 
    <!--尾部begin-->
<script language="javascript">
	function closewin(){
		reloadPage();
		window.close();
	}
</script>
  </body>
</html>
