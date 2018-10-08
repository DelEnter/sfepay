<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pages" uri="/xs-pages" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>划款表审核</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	 <%@ include file="../include/dialog.jsp"%>
    <style>
      td,tr,table{ font-size:12px;}
    </style>
	<h3 align="center">划款表审核</h3>
	<!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>

	<SCRIPT language=JavaScript>
	
    <!-- 检验是否选上需要处理的选项 -->
	function check(){
		var workorderObjectNos = document.getElementsByName('freezeId');
		var gets = new Array();
		var k = 0;
		var result = 0;
		for(var i=0; i<workorderObjectNos.length; i++){
			if(workorderObjectNos[i].checked){
			  // alert("第" + i + "个值：" + workorderObjectNos[i].value);
			    gets[k] = workorderObjectNos[i].value;
			    result =  gets[k];
   				k++;
			}
 		}
 		if(result==0){
			alert("请选上勾兑的选项！");
		}else{
			form2.submit();
		}
	}
   
  	function openDetail(merchId){
     
	 var str ='showMingxi?batchNo='+merchId ;

	 window.open (str) ;
	}
	
	function openView(beatno,merchid){
	 var str ='showAuditDetail?batchNo='+beatno+'&merchantId='+merchid;

	 window.showModalDialog (str, window,'dialogHeight:780px;dialogWidth:1200px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
	
	}
	</script>   
   
   
</SCRIPT>
  </head>
  
  <body>
  	<s:form id="form1" action="toAuditList" theme="simple" method="post">
  		<table align="center">
  			<tr>
  				<td>商户号</td>
  				<td>
  				<input type="text" value="<s:property value='tradeinfo.merno'/>" name="tradeinfo.merno"/>
  				</td>
  				<td>批次号</td>
  				<td>
  					<s:textfield name="orderdno" value=""/>
  				</td>
  			</tr>
  			<tr>
  				<td>开始日期
  				</td> 		
		 		<td>
		 		<input id="start_time" type="text" name="startDate" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 	<input id="end_time" type="text" name="endDate" size="15" value="<s:property value='endDate'/>"/>	
		 		</td>
  			</tr>
  		</table>
  		<table align="center">
  			<tr>
  				<td>
  					<s:submit value="确定"/>
  				</td>
  				<td>
  					<s:reset value="取消"/>
  				</td>
  			</tr>
  		</table>
  		
  	</s:form>	
  	<s:form name="form2" id="form2" action="toAuditList" theme="simple" method="post">
  	  <input type="hidden" value="<s:property value='tradeinfo.merno'/>" name="tradeinfo.merno"/>
  		<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=1>
  			<tr bgColor=#cccccc>
  				<th>商户号</th>
  				<th>批次号</th>
  				<th>交易金额</th>
  				<th>交易币种</th>
  				<th>结算金额</th>
  				<th>结算RMB金额</th>
 				<th>生成划款表日期</th>
  				<th>审核</th>
  				<th>明细</th>
  				<th>备注</th>
  				<th>操作人</th>
  				<th>操作日期</th>
  			</tr>
  			<s:iterator id="huaKuanId" value="info.result">
	  			<tr align="center">
	  				<td>
	  					<s:property value="#huaKuanId[1].merno"/>&nbsp;
	  				</td>
	  				<td>
	  					<s:property value="#huaKuanId[0].batchno"/>&nbsp;
	  				</td>
	  				<td>
	  					<s:property value="#huaKuanId[0].ordercount"/>&nbsp;
	  				</td>
	  				<td>
	  					<s:property value="#huaKuanId[0].trademoneyname"/>&nbsp;
	  				</td>
	  				<td>
	  					<s:property value="#huaKuanId[0].refundmentmoney"/>&nbsp;
	  				</td>
	  				<td>
	  					<s:property value="#huaKuanId[0].freezecount"/>&nbsp;
	  				</td>
	  				<td>
	  					<s:property value="#huaKuanId[0].createtabletime"/>&nbsp;
	  				</td>
	  				<td>
	  					<a onClick="openView('<s:property value='#huaKuanId[0].batchno'/>','<s:property value='#huaKuanId[0].merchantno'/>');" href="#">审核&nbsp;</a>
	  				&nbsp;</td>
	  				<td>
                    <a onClick="openDetail('<s:property value='#huaKuanId[0].batchno'/>')" href="#">明细</a>	&nbsp;
                     </td>
                        <td align="center" onmouseover="this.style.backgroundColor=''" 
						style="FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif" 
						onmouseout="this.style.backgroundColor=''" align=right><A 
						title="<s:property value="#huaKuanId[0].remark"/>" 
						><s:property value="#huaKuanId[0].remark"/></A>
						</td>
						<td >
							<s:property value="#huaKuanId[0].disposeman"/>
						</td>
						<td >
						<s:property value="#huaKuanId[0].disposedate"/>
						</td>
	  			</tr>
  			</s:iterator>		
  			<pages:pages value="info" beanName="info" formName="getElementById('form2')" />

  		</table>
  		
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

    
  </body>
</html>
