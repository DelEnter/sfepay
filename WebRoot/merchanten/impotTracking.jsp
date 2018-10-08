<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>   
    <title> Upload Tracking NO.</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
    <link href="css/css.css" rel="stylesheet" type="text/css">
  </head>
    <base target="_self">
  <body>
	<s:form name="form1" action="confirmImportEn" namespace="/merchant" theme="simple" method="post" enctype="multipart/form-data">
    		<table width=700 border=0 align=center cellPadding=0 cellSpacing=1 bgcolor="#CB7610">
<tr align="center" bgcolor="#cccccc">
    				<td bgcolor="#EACE96">
	  <div class="right">
          <div class="right_top" align=center >  &nbsp;&nbsp;&nbsp;<font  size=3 color="">Upload Tracking NO</font> </div>
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
						<div align="right">Please select document：</div>
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
					<font color="red">ips:ECPSS will send the tracking No. which you upload to the cardholder .</font>
				</div>
					</td>
				</tr>	
				<tr bgcolor="#EACE96">
					<td colspan="2">
					Notice: The format of the document which you will upload should be the same as the format <br/>
					of the Excel document which you downloaded.
					     
					    The type of the shipping company and the shipping invoice should both be filled in. <br/>
					    The types include: EMS,DHL,UPS,USPS,TNT,FedEx,DMS, and you should choose one type of them,<br/>
					     or your operation will be not allowed.:   <font color="red"> EMS,DHL,UPS,TNT,FedEx,DMS </font>
					</td>
				</tr>	
				
				</TABLE>
          </div>
    </div>
   			  </tr>
   		  </table>
          <br />
    		<center> 
    			<input type="submit" class="input_button_01" value="Upload"/> 
    			<input type="button" class="input_button_01" onclick="closewin()" value="Close"/> 
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
