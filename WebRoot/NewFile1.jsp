<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
     <%@ include file="../include/dialog.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <title>Animated Menu Hover 1</title>
 <script type="text/javascript" src="js/jquery.js"></script>
  <style type="text/css">
         #blockCity{ position:absolute;font-size:9pt;  background-color:#FFFFCC; padding:5px; border:1px solid #F5C66B;line-height:160%; width:300px; display:none;}
         #blockCity a
          {
              color:#000000;
              font-size:9pt;
              text-decoration:none;
          }
          #blockCity a:hover
         {
             font-size:10pt;
             border-bottom:dashed 1px #000000; 
         }
         #blockCity table
         {
         border:1px dotted #F5C66B;
         }
         #blockCity table td
         {
           width:40px;
            text-algin:center;
         }
     </style> 
     <script type="text/javascript"> 
      function selStation(locationid){
          document.getElementById("blockCity").innerHTML = "";
        
                      document.getElementById("blockCity").innerHTML +="<tr><td>dsadsada</td></tr><tr><td>ndfisadfniasnfids</td></tr><br />";
                      document.getElementById("blockCity").style.left = event.x-20;
                     document.getElementById("blockCity").style.top = event.y + document.documentElement.scrollTop-30 ;
                     document.getElementById("blockCity").style.display = "block";
          
     }
 
     function cshow(){
         document.getElementById("blockCity").style.display="block";
     }
     function chide(){
         document.getElementById("blockCity").style.display="none";
     }
     </script>
 </head>
 <body>
 
 <div>fdsafsafsa</div>
<div>
<table>
<tr>
<td><a href="#" onmouseover="selStation('1');" onmouseout="chide();" >查看我隐藏的DIV</a><table id="blockCity"  border="1" cellpadding="0" cellspacing="1" bgcolor="#333333" style="margin-top: 20px"></table></td>
</tr>
<tr>
<td><a href="#" onmouseover="selStation('1');" onmouseout="chide();" >查看我隐藏的DIV</a></td>
</tr>
</table>
</div>
 </body>
 </html>
