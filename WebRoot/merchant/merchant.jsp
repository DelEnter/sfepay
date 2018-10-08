<%@page import="com.ecpss.model.affiche.AfficheManager"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <!--头部begin-->
 <head><title>SFEPAY- 商户后台首页</title></head>
<s:action name="indexMenu" executeResult="true" />   

<div class="mainbody">
	   <div class="welcome">
          <div class="welcome_hua">
            <ul>
            <li>欢迎您，<span class="font_color_01"><s:property value="#session.merchantBean.merchantUserName"/></span><span class="font_color_02"></span></li>
            <li>商户号：<span class="font_color_02"><s:property value="#session.merchantBean.merChantNo"/></span></li>
              <!--<li>欢迎您，<span class="font_color_01"><s:property value="merchantBean.merchantUserName"/></span><span class="font_color_02"></span></li>-->
              <!-- <li>商户号：<span class="font_color_02"><s:property value="merchantBean.merChantNo"/></span></li> -->
            </ul>
            <ul>
            	<!--<li>上次登陆：<span class="font_color_02"><s:property value="oldLoginTime"/></span></li> -->
            	<li>上次登陆：<span class="font_color_02"><s:property value="#session.oldLoginTime"/></span></li>
            </ul>
          </div>
          <div class="border_box">
             <ul>
               <li>您当月有&nbsp;<span class="font_color_01">
               		
					<!--<s:property value="charbackCount"/>-->
					<s:property value="#session.charbackCount"/>
					</span>&nbsp;笔拒付交易</li>
             </ul>
             <ul>
               <!--  <li>您还有 <span class="font_color_01"><s:property value="compliantCount"/></span> 笔持卡人投诉交易未处理.</li>-->
               <li>您还有 <span class="font_color_01"><s:property value="#session.compliantCount"/></span> 笔持卡人投诉交易未处理.</li>
             </ul>
             <ul>
                <li>直连接口下载：<a href="../template/(sfepay)formagentoV1.3.1.rar"><span style="color:red">magento系统接口</span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="../template/(sfepay)forzencartV1.3.1.rar"><span style="color:red">zencart系统接口</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:blue;font-size:12px;">*请自己下载对应自己系统的接口，有疑问的地方请联系客服！</span></li>
             </ul>
			 <!--<ul>
			 <li>zencart接口补丁下载：<a href="../template/payment_online.rar"><span style="color:red">payment_online.php</span></a>&nbsp;&nbsp;&nbsp;&nbsp;替换路径：includes\modules\payment\payment_online.php（其他系统请忽略此消息！）</li>
			 </ul>-->
          </div>
          <div class="clear"><font color="red" size="3">公告：<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲爱的各位商户:
          <br/>
          <%
          AfficheManager afficheManager=(AfficheManager)request.getSession().getAttribute("afficheManager");
          if(afficheManager!=null){
          	  //String[] message=afficheManager.getAfficheContext().split("/");
	          //for(int i=0;i<message.length;i++){
	          %>
	          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%//message[i] %><br/>
	          <%//}%>
          <%}%>
	<br/><br/>
<div align="right">迁易通<BR/><s:property value="#session.afficheManager.affichedate"/></div>

          </font>
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