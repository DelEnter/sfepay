<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <!--头部begin--><head><title>Sfepay - 商户后台首页</title></head>
<s:action name="indexMenu" executeResult="true" />   


 <div class="mainbody">
 
    <div class="welcome">
          <div class="welcome_hua">
           &nbsp;
          </div> 
          <div class="border_box">
             <ul>
               <li><b>未结算金额：</b><span class="font_color_01"><s:property value='nosettlementmoney'/></span></li>
             </ul>
             <ul style="border-bottom:none">
               <li><b>保证金余额：</b><span class="font_color_01"><s:property value='nobanlancesMoney'/></span></li>
             </ul>
          </div>
          <div class="clear">&nbsp;</div>
        <div>
     </div>
   </div>
 </div>
<br class="clear" />

 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	