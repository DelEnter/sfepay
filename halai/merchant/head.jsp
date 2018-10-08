<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery.js"></script>
<script type="text/javascript" src="css/jquery-latest.js"></script> 
<script language="JavaScript">

function showlistiframe(obj){
    function toShowUrl(url){
    document.location.href=url;
    }
	}	
	
function showmenu(targetid){

       var k=1;
       for(k;k<10;k++){
     var f='menu'+k;
     if(targetid!=f){
  document.getElementById(f).style.display="none";   
     }
     }

    if (document.getElementById){
        var target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="block";
            }
    }
}	

</script>		
<script type="text/javascript"> 
$(document).ready(function(){

	$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled - Adds empty span tag after ul.subnav
	
	$("ul.topnav li span").click(function() { //When trigger is clicked...
		
		//Following events are applied to the subnav itself (moving subnav up and down)
		$(this).parent().find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click

		$(this).parent().hover(function() {
		}, function(){	
			$(this).parent().find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up
		});

		//Following events are applied to the trigger (Hover events for the trigger)
		}).hover(function() { 
			$(this).addClass("subhover"); //On hover over, add class "subhover"
		}, function(){	//On Hover Out
			$(this).removeClass("subhover"); //On hover out, remove class "subhover"
	});

});
</script>


<div class="container">
   <div class="header2">
     <div class="logo"><img src="images/logo.gif" alt=""/></div>
     <s:if test="#session.merchantBean.merChantNo == '3604'" >
      	<div class="slogan"><img src="images/tongyong.gif" alt="" /></div>   
     </s:if>
    <s:else>
    	<div class="slogan"><img src="images/slogan02.gif" alt="" /></div>
    </s:else>
		    
     <div class="welcome">
       <div class="help">
         <ul>
           <li><img src="images/icon_01.gif" alt="帮助中心" />&nbsp;<a href="#">帮助中心</a></li>
		   <li>
日常客服：<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=2248884917&amp;site=qq&amp;menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:2248884917:41 &amp;r=0.8826520327154458" alt="迁易通日常客服" title="迁易通日常客服"></a>

&nbsp;<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=2205723289&amp;site=qq&amp;menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:2205723289:41 &amp;r=0.8826520327154458" alt="迁易通日常客服" title="迁易通日常客服"></a>
&nbsp;&nbsp;结算客服：<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=2410806472&amp;site=qq&amp;menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:2410806472:41 &amp;r=0.8826520327154458" alt="结算客服" title="结算客服"></a>		   
		   </li>
		   </ul>
       </div>
       <div class="tips"><span>欢迎您回来 <span class="font_color_01"><s:property value="#session.merchantBean.merChantNo"/></span> | <span class="font_color_01"> <a href="merLogout.action">退出</a></span></span></div>
       
     </div>
     <div class="bordder"></div>
     <!--<div class="affiche">
       <span><b>公告：</b><font class="font_color_01">总经理邮箱:cs@sfepay.com.广大商户如有任何建议可发送邮件到此邮箱.</font></span>
     </div>-->
     
     <div class="container1">
        <div id="header1m">
        <ul class="topnav">
         
            <li>
                <a href="#">交易管理</a>
                <ul class="subnav">
                	<s:iterator id="resourcelist" value="resourcelist">
                		<s:if test="#resourcelist[0].substring(5)==1"> 
			     		<li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     			<s:property value='#resourcelist[2]'/>
			     			</a></li>         
			           </s:if>
                	</s:iterator>
                </ul>
            </li>
            <li>
                <a href="#">划款管理</a>
                <ul class="subnav">
                    <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==2"> 
			     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     		<s:property value='#resourcelist[2]'/></a></li>         
			           </s:if>
			          </s:iterator>
                </ul>
            </li>
            <li>
                <a href="#">退款管理</a>
                <ul class="subnav">
                    <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==3"> 
			     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     		<s:property value='#resourcelist[2]'/></a></li>         
			           </s:if>
			          </s:iterator>
                </ul>
            </li>
            <li>
                <a href="#">跟踪管理</a>
                <ul class="subnav">
                    <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==4">  
			     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     		<s:property value='#resourcelist[2]'/></a></li>         
			           </s:if>
			          </s:iterator>
                </ul>
            </li>
            <li>
                <a href="#">信息管理</a>
                <ul class="subnav">
                    <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==5">  
			     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     		<s:property value='#resourcelist[2]'/></a></li>         
			           </s:if>
			          </s:iterator>
                </ul>
            </li>
            <li>
                <a href="#">客服管理</a>
                <ul class="subnav">
                    <s:iterator id="resourcelist" value="resourcelist">         
			         <s:if test="#resourcelist[0].substring(5)==6">   
			     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     		<s:property value='#resourcelist[2]'/></a></li>         
			           </s:if>
			          </s:iterator>
                </ul>
            </li>
             <li>
                <a href="#">拒付处理</a>
                <ul class="subnav">
                    <s:iterator id="resourcelist" value="resourcelist">         
			         <s:if test="#resourcelist[0].substring(5)==8">   
			     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     		<s:property value='#resourcelist[2]'/></a></li>         
			           </s:if>
			          </s:iterator>
                </ul>
            </li>
            <li>
                <a href="#">权限管理</a>
                <ul class="subnav">
                    <s:iterator id="resourcelist" value="resourcelist">         
			         <s:if test="#resourcelist[0].substring(5)==7">   
			     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
			     		<s:property value='#resourcelist[2]'/></a></li>         
			           </s:if>
			          </s:iterator>
                </ul>
            </li>
            <li>
            <a href="#">风险管理</a>
            <ul class="subnav">
                <s:iterator id="resourcelist" value="resourcelist">         
		         <s:if test="#resourcelist[0].substring(5)==9">   
		     <li><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>">
		     		<s:property value='#resourcelist[2]'/></a></li>         
		           </s:if>
		          </s:iterator>
            </ul>
        </li>
        </ul>
    </div>
     </div>
     
     <div class="url"><span><a href="#">首页</a> >> <s:property value='#session.resource.menuName'/></span></div>
   </div>
   <div class="pagebody">
     <div class="sidber">
       <div class="button_title">
         <ul class="ul_bg" onclick="showmenu('menu1');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">交易管理</li>
         </ul>
         <ul class="ul_list">
           <div id="menu1" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">       
           <s:if test="#resourcelist[0].substring(5)==1"> 
     <li class="listli"><a href="<s:property value='#resourcelist[1]' />?resource.id=<s:property value='#resourcelist[3]'/>&query=0">
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
           <li class="listfont">划款管理</li>          
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
           <li class="listfont">退款管理</li>
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
           <li class="listfont">跟踪管理</li>
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
           <li class="listfont">信息管理</li>
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
           <li class="listfont">客服管理</li>
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
           <li class="listfont">权限管理</li>
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
           <li class="listfont">拒付处理</li>
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
       <div class="button_title" >
         <ul class="ul_bg" onclick="showmenu('menu9');">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">风控管理</li>
         </ul>
         <ul class="ul_list">
           <div id="menu9" style="display:none;">  
         <s:iterator id="resourcelist" value="resourcelist">         
           <s:if test="#resourcelist[0].substring(5)==9"> 
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
           <li class="tiaojian"><img src="images/icon_03.gif" alt="" /> 7*24小时客服电话</li>
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
 
