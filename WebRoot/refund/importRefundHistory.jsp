<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>   
    <title>上传退款记录</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
    <link href="../merchant/css/css.css" rel="stylesheet" type="text/css">
  </head>
    <base target="_self">
  <body>
	<s:form name="form1" action="importRefundByBank" theme="simple" method="post" enctype="multipart/form-data">
    		<table width=700 border=0 align=center cellPadding=0 cellSpacing=1 bgcolor="#CB7610">
<tr align="center" bgcolor="#cccccc">
    				<td bgcolor="#EACE96">
	  <div class="right">
          <div class="right_top" align=center >  &nbsp;&nbsp;&nbsp;<font  size=3 color="">上传退款记录</font> </div>
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
						<div align="left"><s:file name="fileName" onchange="fillFileName(this.value)" /></div>
					</td>
					<s:hidden name="filesname" id="filesname"/>
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
					<font color="red"></font>
				</div>
					</td>
				</tr>	
				<tr bgcolor="#EACE96">
					<td colspan="2">
					     
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
	function fillFileName(filePath){
		if(filePath != null){
			var lastNameIndex = filePath.lastIndexOf('\\');
			if(lastNameIndex == -1){
				lastNameIndex = filePath.lastIndexOf('/');
			}
			if(lastNameIndex != -1){
				document.getElementById('filesname').value = filePath.substring(lastNameIndex+1);
			}
		}
	}
</script>
  </body>
</html>
