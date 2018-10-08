<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <!--头部begin-->
 <head><title>Ecpss - Merchant Transaction Administration</title></head>
<s:action name="indexMenuEn" executeResult="true" />   

<div class="mainbody">
	   <div class="welcome">
          <div class="welcome_hua">
            <ul>
              <li>Welcome，<span class="font_color_01"><s:property value="merchantBean.merchantUserName"/></span><span class="font_color_02"></span></li>
              <li>Ecpss Merchant ID: <span class="font_color_02"><s:property value="merchantBean.merChantNo"/></span></li>
            </ul>
            <ul>
            	<li>Last login time：<span class="font_color_02"><s:property value="oldLoginTime"/></span></li>
            </ul>
          </div>
          <div class="border_box">
             <ul>
               <li>You have&nbsp;<span class="font_color_01">
					<s:property value="charbackCount"/>
					</span>&nbsp;transactions which have been chargebacked.</li>
             </ul>
             <ul>
               <li>You have <span class="font_color_01"><s:property value="compliantCount"/></span> disputed transactions which haven't been handling.</li>
               <li>&nbsp;</li>
             </ul>
             <ul style="border:none">
               <li> &nbsp;</li>
             </ul>
          </div>
          <div class="clear">&nbsp;</div>
          <div><img src="images/banner.gif" alt="" /></div>
        <div>
     </div>
   </div>
 </div>
<br class="clear" />

     
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	