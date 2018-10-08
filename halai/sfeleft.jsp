<!--begin left -->
    
    <div class="left">
      <div class="llogin">
        <form name="form2" id="form2" action="" method="post" onsubmit="return checkHolderInfo()">
          <p>
            <label for="num">商户号:</label>
            <input type="text"  name="merno" id="merno"  size="18" class="inbord" autocomplete="off" />
          </p>
          <p>
            <label for="user">用户名:</label>
            <input type="text"  name="username" id="username"  size="18" class="inbord" autocomplete="off" />
          </p>
          <p id="mimal"> 密&nbsp;&nbsp;&nbsp;码:
            <input name="password" id="password" type="password" size="18" class="inbord" autocomplete="off" />
          </p>
          <p>
            <label for="code">验证码:</label>
            <input type="text" name="vercode" id="vercode" size="9" class="lgom" autocomplete="off" />
            						<a href="javascript:refreshimg();">
             <img style="line-height:23px; margin-left:9px;" src="http://www.sfepay.com/authImg.do" id="authImg" alt="点击刷新" /></a>
            </p>
          <p>
            <input type="button" name="sub" id="sub" value="登录" class="insub" onclick="submitInfo('http://www.sfepay.com/merchant/merLogin.action','login')" />
          <a href="https://www.sfepay.com/newReg.jsp" target="_blank" id="butjoin"> <img src="images/join-but.jpg" style="line-height:24px; margin-left:37px;margin-top:5px;float:left;height:21px;width:53px;" /></a> </p>
        </form>
      </div>
      <div class="le_help">
        <ul>
          <li> <a href="faq.jsp#faqc" >迁易通的优势有哪些?</a> </li>
          <li> <a href="faq.jsp#faqd" >手续费怎么收取?</a> </li>
          <li> <a href="faq.jsp#faqd" >支持部分退款吗?</a> </li>
          <li> <a href="faq.jsp#faqd" >开通需要哪些资料?</a> </li>
          <li> <a href="faq.jsp#faqd"  >一个账户可以接几个网站?</a> </li>
          <li> <a href="faq.jsp#faqd"  >拒付怎么处理?</a> </li>
          <li> <a href="faq.jsp#faqd"  >客服服务的时间是怎么样的?</a> </li>
        </ul>
      </div>
      <div class="le_news">
        <ul>
          <li> <a href="faq.jsp#faqd"  >迁易通的优势有哪些?</a> </li>
          <li> <a href="faq.jsp#faqd" >手续费怎么收取?</a> </li>
          <li> <a href="faq.jsp#faqd"  >支持部分退款吗?</a> </li>
          <li> <a href="faq.jsp#faqd"  >开通需要哪些资料?</a> </li>
        </ul>
      </div>
      <img src="images/commer.gif" /> <img src="images/vmcard.png" align="right" alt="visa master card" /> </div>
    
    <!--END left -->