<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>交易查询</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
</head>
<body>
<div class="mainbody">
<table width="100%" >
<tr>
<td class="jiaoyi" width="24%" >
<div class="fangkuang" style="overflow: auto;">* 总金额：
		<s:if test="todayAmout.size>0">
		  <s:iterator value="todayAmout" id="today">
			<font>&nbsp;<s:property value="#today[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#today[1])"/></font>
		  </s:iterator>
		</s:if>
		<s:else>
			0.00
		</s:else>
		<br/>
		* 成功金额：
		<s:if test="todaySuccessAmout.size>0">
		  <s:iterator value="todaySuccessAmout" id="todaysuc">
			<font>·&nbsp;<s:property value="#todaysuc[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#todaysuc[1])"/></font>
		  </s:iterator>
		</s:if>
		<s:else>
			0.00
		</s:else>
		<br/>
		* 保证金：
		<s:if test="bailAmout.size>0">
		  <s:iterator value="bailAmout" id="bailAmout">
			<font>·&nbsp;<s:property value="#bailAmout[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#bailAmout[1])"/></font>
		  </s:iterator>
		</s:if>
		<s:else>
			0.00
		</s:else>
</div>
</td>
<td class="chuli" width="24%">
<div class="fangkuang">*您有&nbsp;<span class="font_color_01"><s:property value="#session.charbackCount"/></span>&nbsp;笔拒付交易.<br/>
*您还有 <span class="font_color_01"><s:property value="#session.compliantCount"/></span> 笔持卡人投诉交易未处理.
</div>
</td>
<td class="gonggao" width="24%">
<div class="fangkuang"><span style="color:red">亲爱的各位商户:</span><br/><marquee direction=up height=100px scrollamount="2">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#session.afficheManager.afficheContext"/>
<div align="right">星支付<BR/><s:property value="#session.afficheManager.affichedate"/></div>
</marquee></div>
</td>
<td width="14%" >
<div style="width: 100px;height:120px "><br/><span style="font-size:16px">今日成功率:</span><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:blue;font-size:30px"><s:property value="successRate"/>%</span></div>
</td>
</tr>
</table>
<br/>
<s:form name="formu" id="formu" action="getTranInfo" method="post" theme="simple">
<div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">近期交易详情</li>
             <s:set name="totala" value="0"/>
             <s:set name="currencyy"/>
             <li class="lilistother">
					
			成功：&nbsp;<s:if test="successfulAmount.size>0">
			<s:iterator value="successfulAmount" id="successful">
					<font color="red"><s:property value="#successful[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#successful[1])"/></font>
				</s:iterator>
				</s:if>
				<s:else>
					0.00
				</s:else>
			&nbsp;&nbsp;&nbsp;&nbsp;
				</li>
           </ul>
           <ul class="bottom">
              <li class="li_03">金额</li>
             <li class="li_04">金额(RMB)</li>  
             <li class="li_01">币种</li>  
             <li class="li_01">通道</li> 
             <li class="li_03">状态</li> 
             <li class="li_03">退款</li>  
             <li class="li_03">拒付</li>  
             <li class="li_03">划款</li>
             <li class="li_03">传号</li>         
             <li class="li_05">交易网址</li>         
             <li class="li_04">备注</li>  
             <li class="li_06">操作</li>
           </ul>
         </div>
         <div class="listlist">
        <s:iterator id="m" value="info.result" status="s">
        <s:if test="%{#m[12].indexOf('1093high risk') || #m[8].substring(0,1)!=\"0\"}">
           <ul class="listlisttop">
             <li>流水订单号：<s:property value="#m[0]" /></li>
             <li>订单号：<s:property value="#m[1]"/> </li>
             <li>交易日期 ：<s:property value="#m[3]"/></li>
             <li>交易卡种：<s:property value="#m[17]"/></li>
             <li>EMAIL：<s:property value="#m[18]"/></li>
             <li>IP：<s:property value="#m[19]"/></li>
           </ul>
           <ul class="listlistbottom">
             <li class="lil_03"><s:property value="#m[4]"/></li> 
             <li class="lil_04"><s:property value="#m[6]"/></li> 
             <li class="lil_01"><s:property value="states.getCurrencyTypeByNo(#m[5])"/></li>    
             <li class="lil_01"><s:property value="#m[7]"/></li>   
             <li class="lil_03"><s:property value="states.getStateName(#m[8],1)" escape="false" /></li>   
             <li class="lil_03"><s:property value="states.getStateName(#m[8],2)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateName(#m[8],3)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateName(#m[8],8)" escape="false"/></li>    
             <li class="lil_03">
             	<s:if test="#m[11]!=null">
             		是
             	</s:if>
             	<s:else>
             		否
             	</s:else>
             </li>     
             <li class="lil_05"><s:property value="#m[16]"/></li>
             <li class="lil_04">&nbsp;<s:property value="#m[12]" escape="false"/></li>    

             <li class="lil_06"><a href="../merchant/viewMerTradeDetail.action?tradeId=<s:property value='#m[13]'/>" target="mainFrame"><img src="images/more_icon.gif"  alt="查看详情"  /></a></li>
       
           </ul>
           </s:if>
            </s:iterator> 
            <ul class="listlistpage">
             <li><br/><pages:pages value="info" beanName="info" formName="getElementById('formu')" /></li>
           </ul>
         </div>
         
       </div>
       </s:form>
</div>
</body>
</html>