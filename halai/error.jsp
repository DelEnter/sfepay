<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <title>错误提示</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   <link href="../css/body2.css" rel="stylesheet" type="text/css" />
   <link href="../css/input2.css" rel="stylesheet" type="text/css" />

</head>
 <body>
   <div>
	 <script language="JavaScript" type="text/JavaScript">
      function backWindow(){
      <s:if test="loaction.back==true">
         <s:if test="loaction.loaction!=''">
            goWindow('<s:property value="loaction.loaction"/>');
         </s:if>
         <s:else>
            closeWindow();
         </s:else>
      </s:if>
       }
	  
   </script>
   </div>
	<div id="title" value="操作错误" />
   <div class="small_tishi_windows" id="resizetable" width="400" height="100">
      <ul class="smail_tishi_windows_border">
        <li class="smail_tishi_windows_border">

		        <s:property   value='messageAction'/>

        </li>
        <br class="clear" />
        <li class="smail_tishi_windows_border">
           <s:if test="loaction.back==true">
		      <input type="button" value="关闭" onclick="backWindow();" class="windows_icon" />
		   </s:if> 
        </li>
      </ul>    
   </div>
  </body>
</html>
