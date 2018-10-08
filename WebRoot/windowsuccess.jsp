<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="webwork" prefix="ww" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   <title>操作成功提示1111</title>
   <link href="css/body2.css" rel="stylesheet" type="text/css" />
   <link href="css/input2.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
  <div>
    <script language="JavaScript" type="text/JavaScript">
    function closeWindowCheck(){
      <ww:if test="loaction.reload==true">
         <ww:if test="loaction.loaction!=''">
           window.opener.location='<ww:property value="loaction.loaction"/>'
         </ww:if>
         <ww:else>
            if((typeof window.opener.reloadPage)=="function"){
              window.opener.reloadPage();
            }else{
              window.opener.location.reload();
            }
         </ww:else>
      </ww:if>
       window.close();
    }
   </script>
   </div>
  <div class="windows_01" id="resizetable" width="300" height="200">
	<div class="pb_center">
	  <div class="clear">&nbsp;</div>
	  <div class="windows_page">
		<ul>
		    <ww:if test="actionMessages.size>0">
               <ww:iterator value="actionMessages">
				 <li class="over"><ww:property/></li>
			   </ww:iterator>
		    </ww:if>
		</ul>
		<br class="clear" /><br class="clear" />
		 <li class="windows_close">
		     <input type="button" value="关闭" onclick="closeWindowCheck();"  class="windows_icon1"/>
		  </li>
	  </div>

	</div>
  </div>
  </body>
</html>