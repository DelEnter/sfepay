<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="webwork" prefix="ww" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      <title>错误提示</title>
      <link href="css/body2.css" rel="stylesheet" type="text/css" />
	  <link href="css/input2.css" rel="stylesheet" type="text/css" />
   </head>


  <body>
    <div>
     <script language="JavaScript" type="text/JavaScript">
    function backWindow(){
      <ww:if test="loaction.back==true">
         <ww:if test="loaction.loaction!=''">
            goWindow('<ww:property value="loaction.loaction"/>');
         </ww:if>
         <ww:else>
            closeWindow();
         </ww:else>
      </ww:if>
    }

   </script>
    </div>
     
     
     <div id="title" value="错误" />
     <div class="small_tishi_windows" id="resizetable"  width="400" height="300">
      <ul class="smail_tishi_windows_border">
        <li class="smail_tishi_windows_border">
           <ww:if test="actionErrors.size>0">
             <ww:iterator value="actionErrors">
		       <ww:property/>
			 </ww:iterator>
		  </ww:if>
        </li>
        <br class="clear" /><br class="clear" /><br class="clear" />
        <li class="smail_tishi_windows_border">
          <ww:if test="loaction.back==true">
           <input type="button" value="返回" onclick="backWindow();"  class="windows_icon1" />
          </ww:if>  
        </li>
      </ul>    
     </div>
     
     
  <!--<div class="windows_01" id="resizetable" width="775" height="200">
	<div class="pb_center">
	  <div class="clear">&nbsp;</div>
	  <div class="windows_page">
		<ul>
		  <ww:if test="actionErrors.size>0">
             <ww:iterator value="actionErrors">
		        <li class="over"><ww:property/></li>
			 </ww:iterator>
		  </ww:if>
		
		  <li class="clear">&nbsp;</li>
		  <li class="windows_close">
		      <ww:if test="loaction.back==true">
		      <input type="button" value="返回" onclick="backWindow();" class="submit_02">
		      </ww:if>  
		  </li>
		</ul>
	  </div>
	</div>
  </div> -->
  

  </body>
</html>
