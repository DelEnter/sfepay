<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>上传发货单</title>
    
	<link rel="stylesheet" type="text/css" href="../css/head.css">

  </head>
  <base target="_self">
  <body>
    <center>
    	<h3>上传发货单</h3>
    	<s:form action="uploadPicture" method="post" theme="simple" enctype="multipart/form-data">
	    	<table>
	    		( 注意选择图片只能是这些格式: *.bmp ， *.png ， *.gif ， *.jpeg )
					  	( 图片的大小不要超过1M!)
					  	上传文件名不可以是汉字.
	    		<tr>
	    			<td>
	    				选择图片：
	    				<input type="file" name="upload">
	    				<s:hidden name="trade.id"/>
		    		</td>
	    		</tr>
	    		<tr>
    				<td>
    				<s:submit value="上传"/>
    			</td>
	    		</tr>
	    	</table>
	    	
    	</s:form>
    </center>

  </body>
</html>
