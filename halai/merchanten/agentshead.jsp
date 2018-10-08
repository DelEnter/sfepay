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
           <li><img src="images/icon_01.gif" alt="Help" />&nbsp;<a href="#">Help</a></li>
           <li><img src="images/icon_02.gif" alt="Customer Service" />&nbsp;<a href="#">Customer Service</a></li>
         </ul>
       </div>
       <div class="tips"><span>Welcome Back  <span class="font_color_01"><s:property value="merchantBean.merChantNo"/></span>   | <span class="font_color_01"><a href="merLogout.action">Sign out</a></span>| <span class="font_color_01"><a href="toMerModifyPwden.action">Change Password</a></span></span></div>
     </div>
     <div class="affiche">
       <span><b>Notice：</b><font class="font_color_01">According to some customers' advice and suggestions, ECPSS made optimization and  adjustment.</font></span>
     </div>
   </div>
   <div class="pagebody">
     <div class="sidber">
       <div class="button_title">
         <ul class="ul_bg">
           <li class="liststyle"><img src="images/icon.gif" alt="" /></li>
           <li class="listfont">Transactions</li>
         </ul>
         <ul class="ul_list">
           <div id="menu1" style="display:block;">  
     <li class="listli"><a href="../merchanten/agentsTradeQuery" >TradeQuery</a></li>         
           </div>
         </ul>
       </div>
     </div>
 