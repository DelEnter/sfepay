<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      <title>执行业务出错</title>
	  <link href="css/body2.css" rel="stylesheet" type="text/css" />
	  <link href="css/input2.css" rel="stylesheet" type="text/css" />
  </head>


  <body onload="resizeWindow();">
   <script language="JavaScript" type="text/JavaScript">
    	function backWindow(){
    	  if(window.opener!=null){
    	   window.close();
    	  }else{
    	   window.history.back();
    	  }
        }
        </script>
  <div id="title" value="成功" />
   <div class="small_tishi_windows" id="resizetable" width="400" height="200">
      <ul class="smail_tishi_windows_border">
        <li class="smail_tishi_windows_border">
          系统错误,请重试！
        </li>
        <br class="clear" />
        <li class="smail_tishi_windows_border">
           <input type="button" value="返回" onclick="backWindow();" class="windows_icon1">
        </li>
      </ul>    
   </div>

  </body>
</html>
