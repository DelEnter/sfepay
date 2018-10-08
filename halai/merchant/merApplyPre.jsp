<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="../include/dialog.jsp"%>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/util.js"></script>
<head>
<title>预授权完成</title>
</head>
<script language="JavaScript" type="text/JavaScript">
	
	function checkSelect(){
		var select = document.formu.checkAll;
		if(select.checked==true){
			selectAll();
		}else{
			noselectAll();
		}
	}
	function selectAll() { 
		var o=document.formu.refundIds;
		//单条记录
		if(o.value!=null){
			o.checked=true;
		}
		//多条记录
	    for(var i=0;i<o.length;i++){
			if(o[i].name == "refundIds") {
				o[i].checked=true;
		    }
		}
	}
	function noselectAll() {
	    var o=document.formu.refundIds;
	    if(o.value!=null){
			o.checked=false;
		}
		 for(var i=0;i<o.length;i++){
				if(o[i].name == "refundIds")
			    {
   					o[i].checked=false;
			    }
			 }
	}
	
	function submitRefund(){
	   var flag=0;
	   var chck=document.getElementsByName("refundIds");
	   for(i=0;i<chck.length;i++){
	       if(chck[i].checked==true){
	           flag++;
	       }
	   }
	   if(flag>0){
	   	  if(confirm("确认提交预授权信息吗?")){
	   		document.getElementById("formu").action="submitRefund";
			document.getElementById("formu").submit();
	   	  }
       }else{
           alert("请选择一个!");
       }
	}
	
	function reloadPage(){
	        window.location="../PaySystem/toCreditCard.action";
	}
	function applyRefund(tradeamount){
		var refundAmount = document.getElementById("refundAmount").value;
		if((refundAmount<=tradeamount)&&(refundAmount>(tradeamount*0.85))){
			var v = confirm("确认信息:\n交易金额:"+tradeamount+"\n完成金额:"+refundAmount);
			if(v==true){
				document.getElementById("form1").action="toApplyPre";
				document.getElementById("form1").submit();
			}
		}else{
			alert("完成金额不能大于交易金额.且不小于原始交易金额的85%")
		}
		
	}
	function cancelRefund(refundid){
		window.location="../PaySystem/cancelRefund.action?refundId="+refundid;
	}
	function chaxun(){
		var formX = document.getElementById("formu");
		formX.submit();
	}	
	function doPRE(fid){
	
	document.getElementById("subtype").value='1';
	document.getElementById("dopre").value=fid;
		var formX = document.getElementById("form1");
		formX.submit();
	
	}
	function nodoPRE(fid){
	document.getElementById("tradeidforop").value=fid;
	document.getElementById("dopre").value=fid;
	document.getElementById("pretype").value='1';
		var formX = document.getElementById("form1");
		
		formX.submit();	
	}
	
</script>
<s:form name="toApplyPre" action="toApplyPre" id="form1" theme="simple">
<input type="hidden" id="tradeidforop" name="tradeId" value="<s:property value='applyRefund.id'/>" /> 
<input type="hidden" name="pretype" id="pretype" value="0" />
<input type="hidden" name="subtype" id="subtype" value="0" />
<!--头部begin-->
<s:action name="indexMenu" executeResult="true" />
<!--头部end-->
<div class="mainbody">


        <input type="hidden" name="orderNo" id="dopre" value="<s:property value='orderNo'/>"/>
  <div><img src="images/division.gif" alt="" /></div>
  <div class="list">
    <div class="listtitle">
      <ul class="top">
        <li class="lifonttitle">提交完成预授权</li>
        <li class="lilistother"><font color="red"> 提醒：根据Sfepay交易流水号查询出该笔交易，填写金额进行完成操作</font></li>
      </ul>
      <ul class="bottom">
        <li class="li_08">流水号</li>
        <li class="li_05">商户订单号</li>
        <li class="li_05">交易日期</li>
        <li class="li_04">金额</li>
        <li class="li_03">支付状态</li>
        <li class="li_04">退款状态</li>
        <li class="li_03">完成金额</li>
      </ul>
    </div>
    <div class="listlist">
      <ul class="listlistbottom">
         <li class="lil_08">
          <s:property value="applyRefund.OrderNo" />
        </li>
        <li class="lil_05">
          <s:property value="applyRefund.merchantOrderNo" />
        </li>
        <li class="lil_05">
          <s:property value="applyRefund.tradeTime" />
        </li>
        <li class="lil_04">
          <s:property value="applyRefund.tradeAmount" />
        </li>
        <li class="lil_03">
          <s:property value="states.getStateName(applyRefund.tradeState,1)" escape="false"/>
        </li>
        <li class="lil_04">
          <s:property value="states.getStateName(applyRefund.tradeState,2)" escape="false"/>
        </li>
        <li class="lil_03">
          <input type="input" name="refundAmount" id="refundAmount" class="text_input_01" style="color:red" title="Enter Refund Amount" value="<s:property value='applyRefund.tradeAmount' />"/></li>
      </ul>
      <ul class="listlistpage">
        <li><font color="red">
          <s:property value="messageAction"/>
          <br/>
          </font>
          <input type="button" name="s" value="提交完成" class="input_button_01" onclick="applyRefund(<s:property value="applyRefund.tradeAmount" />
          )" /></li>
      </ul>
     
      
    </div>
  </div>

</s:form>

<hr />

<s:form name="formu" id="formu" action="toApplyPre" method="post" theme="simple">
  <s:hidden name="exportX" id="exportX"/>

    <div class="search">
      <ul class="searchtext">
        <li class="name">商户订单号</li>
        <li class="nameinput">
          <input type="input" name="merchantOrderNo" value="<s:property value='merchantOrderNo'/>" />
        </li>
      </ul>
      <ul class="searchtext">
        <li class="name">交易流水订单号</li>
        <li class="nameinput">
          <input type="input" name="orderNo2" value="<s:property value='orderNo2'/>"/>
        </li>
      </ul>
      <br class="clear" />
      <ul class="searchtext">
        <li class="name">开始日期</li>
        <li class="nameinput">
          <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
        </li>
      </ul>
      <ul class="searchtext">
        <li class="name">结束日期</li>
        <li class="nameinput">
          <input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
        </li>
      </ul>
      <ul class="searchselect" >
        <li><a href="#" onclick="chaxun()"><img src="images/search.gif" alt="开始搜索" /></a></li>
      </ul>
    </div>
    <div><img src="images/division.gif" alt="" /></div>
    <div class="list">
      <div class="listtitle">
        <ul class="top">
          <li class="lifonttitle">预授权完成查询</li>

        </ul>
        <ul class="bottom">
          <li class="li_01">序列</li>
          <li class="li_08">流水号</li>
          <li class="li_10">商户订单号</li>
          <li class="li_12">交易日期</li>
          <li class="li_03">交易金额</li>
          <li class="li_03">完成金额</li>
          <li class="li_03">授权状态</li>
          <li class="li_01">操作</li>
        </ul>
      </div>
      <div class="listlist">
        <s:iterator id="p" value="info.result" status="s">
          <ul class="listlistbottom">
            <li class="lil_01">
              <s:property value="#s.index+1" />
            </li>
            <li class="lil_08">
              <s:property value="#p.orderNo" />
            </li>
            <li class="lil_10">
              <s:property value="#p.merchantOrderNo" />
            </li>
            <li class="lil_12">
              <s:property value="#p.tradeTime" />
            </li>

            <li class="lil_03">
              <s:property value="#p.tradeAmount" />
            </li>
            <li class="lil_03">
              <s:property value="#p.pre_money" />
            </li>
            <li class="lil_03">
              <s:property value="states.getStateNameByClass(#p.tradeState,14)" escape="false" />
            </li>
            <li class="lil_01">
              <s:if test="states.getStateNameByClass(#p.tradeState,14)=='等待完成'"> <a href="#" onclick="doPRE(<s:property value="#p.id" />)">完成</a> </s:if>
              <s:if test="states.getStateNameByClass(#p.tradeState,14)=='已申请'"> <a href="#" onclick="nodoPRE(<s:property value="#p.id" />)">取消完成</a> </s:if>
            </li>
          </ul>
        </s:iterator>
        <ul class="listlistpage">
          <li>
            <pages:pages value="info" beanName="info" formName="forms(1)" />
          </li>
        </ul>
      </div>
    </div>
    
  </div>
</s:form>
<!-- 下面这段script代码必须放在form体的最后  
loadcalendar方法的五个参数分别解释如下：
1、日期显示文本框的ID号
2、触发日历控件显示的控件ID号
3、要显示的日期格式，%Y表示年，%m表示月，%d表示日
4、是否带周显示，默认是不带
5、是否带时间显示，默认是不带
6、日历显示文字的语言，默认是中文 --> 
<script language="javascript" type="text/javascript">

loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "en");
loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "en");

function cleanDate(vid){
	document.getElementById(vid).value="";
}
</script> 
<!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end--> 

