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
              <li>欢迎您，<span class="font_color_01"><s:property value="merchantBean.merchantUserName"/></span><span class="font_color_02"></span></li>
              <li>商户号：<span class="font_color_02"><s:property value="merchantBean.merChantNo"/></span></li>
            </ul>
            <ul>
            	<li>上次登陆：<span class="font_color_02"><s:property value="oldLoginTime"/></span></li>
            </ul>
          </div>
          <div class="border_box">
             <ul>
               <li>您有&nbsp;<span class="font_color_01">
					<s:property value="charbackCount"/>
					</span>&nbsp;笔拒付交易</li>
             </ul>
             <ul>
               <li>您还有 <span class="font_color_01"><s:property value="compliantCount"/></span> 笔持卡人投诉交易未处理.</li>
               <li>&nbsp;</li>
             </ul>
             <ul style="border:none">
               <li> &nbsp;</li>
             </ul>
          </div>

          </div>
		  <div class="clear" style="font-size: 14px;">
		  <font color="black">公告：2013年08月<b>09</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;background:#f9f9f9;border-left:5px solid red;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  今年通道开通以来，拒付比例不断攀升，虽然我们不断提醒商户，并采取相应处罚措施，但是很遗憾拒付和GBBP比例，还是超过卡组织规定的标准！
银行要求我们10号以后停止交易，重新评估商户的风险，本次评估时间预计需要一个月时间。 
暂停收款的商户会和以前通道暂停期间一样分批结算剩余款项，如有最新情况我们会及时通知！
<br><br>

</p>
<div align="right">迁易通 （SFEPAY）<br/></div>   
		  <div class="clear" style="font-size: 14px;">
		  <font color="black">公告：2013年08月<b>09</b>日<br/>
		   <br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <font color="black">公告：2013年05月<b>17</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;background:#f9f9f9;border-left:5px solid red;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最近统计发现拒付比例还在不断上升，再次提醒请各位商户能够重视这个问题.
<br>检查发现仍有很多商户没有及时上传快递单号。鉴于目前情况我司决定暂停受理新商户开通，<br>
已经开通使用的商户配合做好降低拒付的工作，共同维护通道使用。及时上传快递单号，<br><b>对拒付超标的商户我司将采取进一步的限制措施。</b>
<br><br>
</p>
<div align="right">迁易通 （SFEPAY）<br/></div>   
		  <div class="clear" style="font-size: 14px;">
		  <font color="black">公告：2013年05月<b>17</b>日<br/>
		  
          <div class="clear" style="font-size: 14px;">
		  <font color="black">公告：2013年05月<b>13</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;background:#f9f9f9;border-left:5px solid red;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新通道上线以后，最近发现拒付比例在不断上升，请各位商户能够重视这个问题，想办法降低拒付，共同维护通道正常使用。
<br>特别是有一半的拒付原因是未收到货引起的，风控部门要求商户在订单成功一周内要上传快递单号，<br>
不能及时上传快递单号的请及时联系我们客服做出说明。如果不及时说明情况的话，我司将采取强制退款措施！！
<br><br>
</p>
<div align="right">迁易通 （SFEPAY）<br/></div>   
		  <div class="clear" style="font-size: 14px;">
		  <font color="black">公告：2013年05月<b>13</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;background:#f9f9f9;border-left:5px solid green;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目前通道已完全恢复（成功率也已恢复）商户网站联系业务员，添加网站恢复收单.
<br><br>
</p>
<div align="right">迁易通 （SFEPAY）<br/></div>            
          <div align="right">2013年04月<b>10</b>日<br/></div><br/><br/>
		  <font color="black">公告：2013年02月<b>03</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;background:#f9f9f9;border-left:5px solid red;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;从2月份起对于暂时停止收款的商户结算周期改为每月一次为每月的一号和二号，保证金结算为每月的16号17号。<br>祝: 新春快乐  生意兴隆。
<br><br>
</p>
<div align="right">迁易通 （SFEPAY）<br/></div>            
          <div align="right">2013年02月<b>03</b>日<br/></div><br/><br/>
		  <font color="black">公告：2012年12月<b>29</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;background:#f9f9f9;border-left:5px solid red;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收单银行从2013起将严格控制拒付率和GBPP，从1月1日起商户将自行承担每笔不低于5000美金的GBPP罚金。老商户余额低于5000美金或拒付比例超过1%的商户本公司将暂停收款通道，整顿时间预期2个月。
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其他商户请做好售后服务工作，控制拒付比例，对visa组织严查的品牌网站停止收款。<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;暂停收款的商户，将分批结算余款。<br><br>
</p>
<div align="right">迁易通 （SFEPAY）<br/></div>            
          <div align="right">2012年12月<b>29</b>日<br/></div><br/><br/>
		  
		  <font color="black">公告：2012年12月<b>06</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;background:#f1f1f1;border-left:3px solid #FF7171;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由于银行和品牌商的限制,请做加拿大羽绒服( candagoose )的商户网站删除并卸载我们的支付。
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一经发现<font color="red"><b>罚款一万美元</b></font>.<br><br>
</p>                                                                                                                       
			<div align="right">迁易通 （SFEPAY）<br/></div>            
          <div align="right">2012年12月<b>06</b>日<br/></div><br/><br/>
		  
		  <font color="black">公告：2012年9月<b>14</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 16px;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 通道为实时通道。 <br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们新的vip实时通道上线。<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 现在针对部分老商户开通。 
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新通道拒付要求较高，希望大家做好客服售后工作，共同维护通道长期使用。
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2%以下拒付的商户可以联系客服和业务代表申请开通！<br><br>
</p>                                                                                                                       
			<div align="right">迁易通 （SFEPAY）<br/></div>            
          <div align="right">2012年9月<b>14</b>日<br/></div><br/><br/>
			  
		  <font color="black">公告：2012年7月<b>13</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p>尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交易暂停的商户保证金结算日改为每月16号，17号。<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">货款结算日统一改为每月1号2号。</font>
<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请商户于结算日前上传快递单号，提交划款申请。
<br><br>
</p>                                                                                                                       
			<div align="right">迁易通 （SFEPAY）<br/></div>            
          <div align="right">2012年7月<b>13</b>日<br/></div><br/><br/>
		  
			  <font color="black">公告：2012年5月<b>16</b>日<br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<p style="padding-left: 30px; color: black; line-height: 23px; font-size: 15px;">尊敬的商户：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的前期满足180天的订单，保证金可以划款。我们会在每月18号，给您结算保证金，请注意查收。<br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">另外：<b>保证金没有满1000元的，划款时会收取20元的转账费用。</b></font><br>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">如果您的保证金金额不满1000元，您可以联系客服，要求下次一起结算，否则我们会默认您同意给您划款。谢谢合作！</font><br><br>另祝：生意兴隆！<br>
</p>

                                                              
                                                                 
			<div align="right">迁易通 （SFEPAY）<br/></div>            
          <div align="right">2012年5月<b>16</b>日<br/></div><br/>
          <br/>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;各位商户你们好：
          <br/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;做退款处理时在“退款管理”中：先在“申请退款”中提交申请（至已申请状态）→再“确定提交退款”中确认提交（至已提交状态），经过审核的退款在20-30工作日后到达客户的账号，请商户退款处理后给客户去邮件说明情况，以免客户长时间收不到退款而拒付。
</font>
          <br/>
          </div>
          <div align="right"> SFEPAY<br/></div><br/>              
          <div align="right"> 2011年11月03日 <br/></div><br/> 

         


          
        
          </font>
          <div class="clear">&nbsp;</div>
          <div></div>
        <div>
     </div>
   </div>
 </div>
<br class="clear" />

     
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	
