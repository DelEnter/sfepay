<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <title>操作提示</title>
  
   <style type="text/css">
<!--
li { list-style:none}
-->
   </style>
</head>
  <body>
  <div>
    <script language="JavaScript" type="text/JavaScript">
 function closeWindowCheck(){
      closeWindow();
      <s:if test="loaction.reload==true">
        
         if(windowCount()>0){
           reloadWindow();
         }else{
         <s:if test="loaction.loaction!=''">
           window.location='<ww:property value="loaction.loaction"/>'
         </s:if>
         <s:else>
            if((typeof reloadPage)=="function"){
              reloadPage();
            }else{
              window.location.reload();
            }
         </s:else>
         }
      </s:if>
     
    }
   </script>
   </div>
   
     <div id="title" value="操作提示"/>
     <div id="resizetable" width="400" height="100" style=" width:400px; height:100px; text-align:center; ">
      <ul style="text-align:center;">
        <li style="font-size:14px;"><s:property value="messageAction"/></li>
        <br class="clear" />
        <li>
           <input type="button" value="关闭" onClick="closeWindowCheck();" class="windows_icon" />
        </li>
      </ul>    
     </div>
     
  </body>
</html>