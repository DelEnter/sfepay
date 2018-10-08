<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>交易查询</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
</style>
<script language="JavaScript" type="text/JavaScript">

	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="1";
		formX.submit();
	}
	
	function chaxun(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="";
		formX.submit();
		document.getElementById('submitse').style.visibility="hidden";
		document.getElementById('process').style.visibility="visible";
	}
	function cancelRefund(refundid){
		window.location="../PaySystem/cancelRefund.action?refundId="+refundid;
	}
	
	function detialinfo(tradeid){

		window.open ('../merchant/viewMerTradeDetail.action?tradeId='+tradeid);

	}
	
	function exp1(orderno){
    	openWindow('../merchant/expressOrder.action?orderNo='+orderno,'12');
    }
	
	function exp2(orderno){
		openWindow('../merchant/expressExpOrder.action?orderNo='+orderno,'12');
    }
	
</script>
</head>
<body>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true" />
<!--头部end-->
<s:form name="formu" id="formu" action="merTradeQuery" method="post" theme="simple">
<input type="hidden" name="isdownload" id="isdownload"/>
    <div class="mainbody">
       <div class="search">
         <ul class="searchtext">
           <li class="name">商户订单号</li>
           <li class="nameinput"><input type="text" name="merchantOrderNo" value="<s:property value='merchantOrderNo'/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">交易流水订单号</li>
           <li class="nameinput"><input type="text" name="orderNo" value="<s:property value='orderNo'/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">金额</li>
           <li class="nameinput">
			<input type="text" name="minamount" size="6" value="<s:property value='minamount'/>" />-
			<input type="text" name="maxamount" size="6" value="<s:property value='maxamount'/>" />
			</li>
         </ul>
         <br class="clear" />
         
         <ul class="searchselect">
           <li class="selectname">支付情况</li>
           <li class="selectinput">
           <s:select name="isresult" list="#@java.util.TreeMap@{'0':'失败','1':'成功','2':'待处理','4':'待确认','5':'未返回'}"  headerKey="" headerValue="----" />
           </li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">退款情况</li>
           <li class="selectinput"><s:select name="istuikuan" list="#@java.util.TreeMap@{'0':'未退款','1':'全额退款','2':'部分退款'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">冻结情况 </li>
           <li class="selectinput"><s:select name="isdongjie" list="#@java.util.TreeMap@{'0':'未冻结','1':'冻结','2':'解冻'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">拒付情况</li>
           <li class="selectinput"><s:select name="isjufu" list="#@java.util.TreeMap@{'0':'未拒付','1':'已拒付'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">划款情况</li>
           <li class="selectinput"><s:select name="ishuakuan" list="#@java.util.TreeMap@{'0':'未划款','1':'已划款'}"  headerKey="" headerValue="----" /></li>
         </ul>  
         <ul class="searchselect">
           <li class="selectname">跟踪单</li>
           <li class="selectinput"><s:select name="ispiccher" list="#@java.util.TreeMap@{'0':'未上传','1':'已上传'}"  headerKey="" headerValue="----" /></li>
         </ul>  
         <ul class="searchselect">
           <li class="selectname">跟踪号</li>
           <li class="selectinput"><s:select name="istrackno" list="#@java.util.TreeMap@{'0':'未填写','1':'已填写'}"  headerKey="" headerValue="----" /></li>
         </ul>  
         <ul class="searchselect">
           <li class="selectname">勾兑情况</li>
           <li class="selectinput"><s:select name="isgoudui" list="#@java.util.TreeMap@{'0':'未勾兑','1':'已勾兑'}"  headerKey="" headerValue="----" /></li>
         </ul>   
        <ul class="searchselect">
           <li class="selectname">&nbsp;</li>
           <li class="selectinput">&nbsp;</li>
         </ul>
         <ul class="searchtext">
           <li class="name">开始日期</li>
           <li class="nameinput"><input id="start_time" type="text" name="startDate" size="15" value="<s:property value='startDate'/>"/>
        </li>
         </ul>
         <ul class="searchtext">
           <li class="name">结束日期</li>
           <li class="nameinput"><input type="text" id="end_time" name="endDate" size="15" value="<s:property value='endDate'/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">交易网址 </li>
           <li class="nameinput"><input type="text" name="tradeUrl" value="<s:property value="tradeUrl"/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">交易邮箱 </li>
           <li class="nameinput"><input type="text" name="tradeEmail" value="<s:property value="tradeEmail"/>" /></li>
         </ul>
         <ul class="searchbutton">
           <li>
           <div id="submitse" >
           	<input type="image" src="images/search.gif" onclick="chaxun()" /></a>
		   	</div>
		   	<div id="process" style="VISIBILITY: hidden;" >
		   		<img src="https://security.sslepay.com/jsp/ibank/images/loading1.gif" alt="" />
			</div>
           
           </li>
         </ul>
       </div>
       <div class="clear">&nbsp;</div>
         <div><img src="images/division.gif" alt="" /></div>
         
         
         
        
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">商户交易查询</li>
             <s:set name="totala" value="0"/>
             <s:set name="currencyy"/>
             <li class="lilistother">
             		
					总交易金额：&nbsp;
				<s:iterator value="totalAmount" id="total">
				<s:if test="#total[0]!=0">
					<font color="red"><s:property value="#total[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#total[1])"/></font>
				</s:if>
				<s:else>
					0.00
				</s:else>
			</s:iterator>&nbsp;&nbsp;&nbsp;&nbsp;
			成功：&nbsp;<s:iterator value="successfulAmount" id="successful">
				<s:if test="#total[0]!=0">
					<font color="red"><s:property value="#successful[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#successful[1])"/></font>
				</s:if>
				<s:else>
					0.00
				</s:else>
			</s:iterator>&nbsp;&nbsp;&nbsp;&nbsp;
				</li>
             <li class="lilistimg"><input type="image" src="images/download.gif" onclick="exportInfo()" /></li>
           </ul>
           <ul class="bottom">
             <li class="li_03">金额</li>  
             <li class="li_01">币种</li>  
             <li class="li_01">通道</li> 
             <li class="li_01">状态</li> 
             <li class="li_01">退款</li>  
             <li class="li_01">拒付</li>  
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
             <li class="lil_01"><s:property value="states.getCurrencyTypeByNo(#m[5])"/></li>    
             <li class="lil_01"><s:property value="#m[7]"/></li>   
             <li class="lil_01"><s:property value="states.getStateName(#m[8],1)" escape="false" /></li>   
             <li class="lil_01"><s:property value="states.getStateName(#m[8],2)" escape="false"/></li>    
             <li class="lil_01"><s:property value="states.getStateName(#m[8],3)" escape="false"/></li>    
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


             <li class="lil_06">
             	<img src="images/more_icon.gif"  alt="查看详情"  onclick="detialinfo(<s:property value="#m[13]"/>)"/>
             </li>
       		 <li>          	
	       		 	<s:if test="result == 1">
	       		 		<s:if test="states.getStateName(#m[8],1).substring(25,27) == '成功'">
	       		 			<s:if test="states.getStateName(#m[8],2).substring(0,3) == '未退款'">	
	       		 				<s:if test="states.getStateName(#m[8],3).substring(0,3) == '未拒付'">
	       		 					<s:if test="states.getStateName(#m[8],8).substring(0,3) == '未划款'">
		       		 					<s:if test="#m[21] != 0">	       		 			
					               				<img style="padding-top: 5px" src="../images/expresss.png" onclick="exp1('<s:property value="#m[0]"/>')" alt="下单"/>
					               			<s:if test="#m[20] != null">
					               			    <%-- <a href="../merchant/expressExpOrder.action?orderNo=<s:property value="#m[0]"/>">
					               					<img style="padding-left: 10px" src="../images/expordError.png" alt="失败原因"/>
					               				</a> --%>	
					               				<img style="padding-left: 10px" src="../images/expordError.png" onclick="exp2('<s:property value="#m[0]"/>')" alt="失败原因"/>			               				
					               			</s:if>		
				               			</s:if>	
				               		</s:if>
			               			<s:if test="#m[21] == 0">
			               				<img src="../images/sym.png" alt="下单成功"/>		               			
			               			</s:if>           						               			
		               			</s:if>
		               		</s:if>
		               		<s:elseif test="states.getStateName(#m[8],2).substring(26,30) == '部分退款'">
	       		 				<s:if test="states.getStateName(#m[8],3).substring(0,3) == '未拒付'">	  
	       		 							<s:if test="states.getStateName(#m[8],8).substring(0,3) == '未划款'"> 			
					               				<s:if test="#m[21] != 0">	       		 		
							               				<img style="padding-top: 5px" src="../images/expresss.png" onclick="exp1('<s:property value="#m[0]"/>')" alt="下单"/>
							               			<s:if test="#m[20] != null">
							               				<%-- <a href="../merchant/expressExpOrder.action?orderNo=<s:property value="#m[0]"/>">
							               					<img style="padding-left: 10px" src="../images/expordError.png" alt="失败原因"/>
							               				</a> --%>
							               				<img style="padding-left: 10px" src="../images/expordError.png" onclick="exp2('<s:property value="#m[0]"/>')" alt="失败原因"/>
							               			</s:if>		
						               			</s:if>	
						               			<s:if test="#m[21] == 0">
						               				<img src="../images/sym.png" alt="下单成功"/>			               			
						               			</s:if> 	
						               		</s:if>		               		              	
		               			</s:if>
		               		</s:elseif>
	               		</s:if>
	               	</s:if>            
       		 </li>
           </ul>
           </s:if>
            </s:iterator> 
            <ul class="listlistpage">
             <li><br><pages:pages value="info" beanName="info" formName="getElementById('formu')" /></li>
           </ul>
         </div>
         
       </div>
         
     </div>
   </div>
   
   <!-- 下面这段script代码必须放在form体的最后  
             loadcalendar方法的五个参数分别解释如下：
             1、日期显示文本框的ID号
             2、触发日历控件显示的控件ID号
             3、要显示的日期格式，%Y表示年，%m表示月，%d表示日
             4、是否带周显示，默认是不带
             5、是否带时间显示，默认是不带
             6、日历显示文字的语言，默认是中文 -->
        <script language="javascript" type="text/javascript">

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
   </s:form>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->
   
   
   