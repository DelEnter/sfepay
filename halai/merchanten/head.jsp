<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery.js"></script>
<script language="JavaScript">

function showlistiframe(obj){
    function toShowUrl(url){
    document.location.href=url;
    }
	}	
	
function showmenu(targetid){

       var k=1;
       for(k;k<9;k++){
     var f='menu'+k;
     if(targetid!=f){
  document.getElementById(f).style.display="none";   
     }
     }

    if (document.getElementById){
        target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="block";
            }
    }
}	

</script>		
<div class="container">
   <div class="header2">
     <div class="logo"><img src="images/logo.gif" alt=""/></div>
     <div class="slogan"><img src="images/slogan02.gif" alt="" /></div>
     <div class="welcome">
       <div class="help">
         <ul>
           <li><img src="images/icon_01.gif" alt="Help" />&nbsp;<a href="#">Help</a></li>
           <li><img src="images/icon_02.gif" alt="Customer Service" />&nbsp;<a href="#">Customer Service</a></li>
         </ul>
       </div>
       <div class="tips"><span>Welcome Back <span class="font_color_01"><s:property value="#session.merchantBean.merChantNo"/></span> | <span class="font_color_01"> <a href="merLogout.action">Sign out</a></span></span></div>
       
     </div>
     <div class="bordder"></div>
     <div class="affiche">
       <span><b>Notice：</b><font class="font_color_01">According to some customers' advice and suggestions, ECPSS made optimization and  adjustment.</font></span>
     </div>
     <div class="url"><span><a href="#">Home</a> >> <s:property value='#session.resource.menuName'/></span></div>
   </div>
   <div class="pagebody">
     <div class="sidber">
       <div class="button_title">
         <ul class="ul_bg" onclick="showmenu('menu1');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Transactions</li>
         </ul>
         <ul class="ul_list">
           <div id="menu1" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">       
           <s:if test="#resourcelist[0].substring(5)==1"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
     			<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font>
     			</a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>
          
       </div>
       
       <div class="button_title" >
         <ul class="ul_bg" onclick="showmenu('menu2');">
           <li class="liststyle"><img src="images/icon_over.gif" alt="" /></li>
           <li class="listfont">Settlements</li>          
         </ul>
         <ul class="ul_list">
           <div id="menu2" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==2"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
     		<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font></a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>        
       </div>
       
       <div class="button_title" >
         <ul class="ul_bg" onclick="showmenu('menu3');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Refunds</li>
         </ul>
         <ul class="ul_list">
           <div id="menu3" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==3"> 
      <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
      		<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font></a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>          
       </div>
       
       <div class="button_title">
         <ul class="ul_bg"  onclick="showmenu('menu4');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Tracking</li>
         </ul>
         <ul class="ul_list">
           <div id="menu4" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==4"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
     		<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font></a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>              
       </div>
       
       <div class="button_title" >
         <ul class="ul_bg" onclick="showmenu('menu5');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Informations</li>
         </ul>
         <ul class="ul_list">
           <div id="menu5" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==5"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
     		<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font></a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>              
         
       </div>
       
       <div class="button_title" >
         <ul class="ul_bg" onclick="showmenu('menu6');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Customer Service</li>
         </ul>
         <ul class="ul_list">
           <div id="menu6" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==6"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
     		<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font></a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>              
       </div>
       
       <div class="button_title" >
         <ul class="ul_bg" onclick="showmenu('menu7');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Preferences</li>
         </ul>
         <ul class="ul_list">
           <div id="menu7" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==7"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
     		<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font></a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>              
       </div>
       <div class="button_title" >
         <ul class="ul_bg" onclick="showmenu('menu8');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Chargebacks</li>
         </ul>
         <ul class="ul_list">
           <div id="menu8" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==8"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
     		<font color="
     			<s:if test="#session.resource.menuName == #resourcelist[2]">
					red
     			</s:if>
				"><s:property value='#resourcelist[2]'/></font></a></li>         
           </s:if>
          </s:iterator>
           </div>
         </ul>             
       </div>      
       <div class="phone">
         <ul>
           <li class="tiaojian"><img src="images/icon_03.gif" alt="" /> 7*24 hours customer service online</li>
           <li class="phonenumber">18930948379 </li>
           <li class="phonenumber">18930272017 </li>
         </ul>
       </div>
     </div>
<script language="JavaScript">
 var f= '<s:property value='#session.resource.numberCode'/>';

 var k;
 if(f!=null){
 k=f.substring(5);
 var show= 'menu'+k;
 showmenu(show);
 }

</script>	     
 