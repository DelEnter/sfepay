<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>上传3D勾兑数据</title>
 	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  </head>
  
  <body>
    <center>
    	<h3>上传3D勾兑数据</h3>
    	<s:form action="uploadGoudui" method="post" theme="simple" enctype="multipart/form-data">
	    	<input type="hidden" name="uploadType" value="2">
	    	文件：<input type="file" name="upload">
	    	<s:submit value="上传"/>
		</s:form>
    </center>
  </body>
</html>
