<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery.js"></script>
<div class="container">
   <div class="header2">
     <div class="logo"><img src="images/logo.gif" alt=""/></div>
     <div class="slogan"><img src="images/slogan02.gif" alt="" /></div>
     <div class="welcome">
       <div class="help">
         <ul>
           <li><img src="images/icon_01.gif" alt="帮助中心" />&nbsp;<a href="#">帮助中心</a></li>
           <li>&nbsp;<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2248884917&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:2248884917:41 &r=0.8826520327154458" alt="迁易通客服" title="迁易通客服">迁易通客服</a></li>
		   <li>&nbsp;<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2426354956&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:2426354956:41 &r=0.8826520327154458" alt="技术" title="技术">技术</a></li>
         </ul>
       </div>
       <div class="tips"><span>欢迎您回来 <span class="font_color_01"><s:property value="merchantBean.merChantNo"/></span>   | <span class="font_color_01"> 退出</span></span></div>
     </div>
     <div class="affiche">
       <span><b>公告：</b><font class="font_color_01">根据大家的意见和建议，SFEPAY进行了优化和调整。</font></span>
     </div>
   </div>
   <div class="pagebody">
     <div class="sidber">
       <div class="button_title">
         <ul class="ul_bg">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">交易管理</li>
         </ul>
         <ul class="ul_list">
           <div id="menu1" style="display:block;">  
     <li class="listli"><a href="../merchant/agentsTradeQuery" >交易查询</a></li>         
           </div>
         </ul>
       </div>
     </div>
 