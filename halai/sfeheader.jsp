 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
 <%@ page import="java.util.*"%>
<!--begin header -->
     <div class="header">
        <div id="hd-m">
         <div id="logo">
         <a href="http://www.sfepay.com" title="迁易通 SFEPAY" >
         <img src="images/logo-sfepay.png" alt="迁易通 SFEPAY" /></a>
         </div>
         <div id="topm">
         <img src="images/topm.png" alt="迁易通安全快捷方便" />
         </div>
         <div id="topjoin">
           <ul>
            <li class="topone">
            <a href="https://www.sfepay.com/newReg.jsp" target="_blank" title="在线申请 迁易通支付" >在线申请</a>
            </li>
            <li class="toptwo">
            <a href="#" title="SFEPAY English Site" >EN</a>
            </li>
           </ul>
         </div>
		</div>
        <div id="nav">
         <ul>
            <li <% if ( pageid == 0 ) { %>class="pnav1" <%}else{%>class="nav1"<%}%> >
            <a href="http://www.sfepay.com" title="首页 SFEPAY" >首页</a>
            </li>
            <li <% if ( pageid == 1 ) { %>class="pnav2" <%}else{%>class="nav2"<%}%> >
            <a href="about-us.jsp" title="关于我们" >关于我们</a>
            </li>
            <li <% if ( pageid == 2 ) { %>class="pnav3" <%}else{%>class="nav3"<%}%> >
            <a href="download.jsp" title="选择我们" >技术支持</a>
            </li>
            <li <% if ( pageid == 3 ) { %>class="pnav4" <%}else{%>class="nav4"<%}%> >
            <a href="join.jsp" title="加入我们" >加入我们</a>
            </li>
            <li <% if ( pageid == 4 ) { %>class="pnav5" <%}else{%>class="nav5"<%}%> >
            <a href="products.jsp" title="产品选择" >产品选择</a>
            </li>
            <li <% if ( pageid == 5 ) { %>class="pnav6" <%}else{%>class="nav6"<%}%> >
            <a href="faq.jsp" title="常见问题" >常见问题</a>
            </li>
            <li <% if ( pageid == 6 ) { %>class="pnav7" <%}else{%>class="nav7"<%}%> >
            <a href="contact.jsp" title="联系我们" >联系我们</a>
            </li>
           </ul>
        </div>
     </div>
<!--END header -->