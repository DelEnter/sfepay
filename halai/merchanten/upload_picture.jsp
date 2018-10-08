<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>Upload invoice picture</title>
    
	<link rel="stylesheet" type="text/css" href="../css/head.css">

  </head>
  <base target="_self">
  <body>
    <center>
    	<h3>Upload invoice picture</h3>
    	<s:form action="uploadPicture" method="post" theme="simple" enctype="multipart/form-data">
	    	<table>
	    		(Please pay attention to that the format of the picture <br/>
	    		could only be :*.bmp , *.png , *.gif , *.jpeg)
					  	( The size of the picture couldn't be over 1M!)
					  	The name of document couldn't be chinese.
	    		<tr>
	    			<td>
	    				Select Picture：
	    				<input type="file" name="upload" value="Browse">
	    				<s:hidden name="trade.id"/>
		    		</td>
	    		</tr>
	    		<tr>
    				<td>
    				<s:submit value="Upload"/>
    			</td>
	    		</tr>
	    	</table>
	    	
    	</s:form>
    </center>

  </body>
</html>
