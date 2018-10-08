<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ include file="../util/checkAll.jsp" %>
    <%@ taglib prefix="pages" uri="/xs-pages"%>
    <%@ include file="../util/calendar.jsp" %>
    <title>解冻审核</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<SCRIPT language=JavaScript>

    
    <!-- 检验是否选上需要处理的选项 -->
		<!-- 检验是否选上需要处理的选项 -->
	function check(){
		var workorderObjectNos = document.getElementsByName('disposeId');
		var gets = new Array();
		var k = 0;
		var result = 0;
		for(var i=0; i<workorderObjectNos.length; i++){
			if(workorderObjectNos[i].checked){
			    gets[k] = workorderObjectNos[i].value;
			    result =  gets[k];
   				k++;
			}
 		}
 		if(result==0){
			alert("请选上冻结的选项！");
		}else{
			form1.action="auditingThaw"
			form1.submit();
		}
	}
</SCRIPT>

  </head>
  
  <body>
    <center>
    	<h3>解冻审核</h3>
	   	<s:form action="findFreeze" method="post" theme="simple" name="form1">
	   		<table>
		   		<tr>
		   			<td>流水号</td>
		   			<td>
		   				<s:textfield name="tradeinfo.orderNo" id="tradeinfo.orderNo"/>
		   			</td>
		   			<td>商户号</td>
		   			<td>
		   				<s:textfield name="merchant.merno" id="tradeinfo.merchantNo"/>
		   			</td>
		   			<td>
		   				<s:submit value="查询"></s:submit>
		   			</td>
		   		</tr>
		   	</table> <br>
	    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	    		<tr bgColor=#cccccc align="center">
	    			<td><input type="checkbox" onclick='chkall("form1",this)' name=chk> </td>
	    			<td>商户号</td>
	    			<td>流水号</td>
	    			<td>商户订单号</td>
	    			<td>交易金额</td>
	    			<td>交易日期</td>
	    			<td>备注</td>
	    		</tr>
	    		<s:iterator id="freezeId" value="info.result">
		    		<tr align="center">
		    			<td> 
							<input type="checkbox" name="disposeId" id="disposeId" value="<s:property value="#freezeId[0].id"/>">
			   			</td>
		    			<td>
		   				<s:property value="#freezeId[1].merno"/>&nbsp;	
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].orderNo"/>&nbsp;
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].merchantOrderNo"/>&nbsp;
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].tradeAmount"/>&nbsp;
		   			</td>
		   			<td>
		   				<s:property value="#freezeId[0].tradeTime"/>&nbsp;
		   			</td>
		   		
		   			<td>
		   				<s:property value="#freezeId[0].remark"/>&nbsp;
		   			</td>
		    		</tr>
	    		</s:iterator>
	    	</table>
	   		<table>
		   		<tr>
		   			<td>
		   				<input type="button" value="解冻审核" onclick="check()">
		   			</td>
		   		</tr>
		   		<tr bgColor=#ffffff>
					<td>
						<pages:pages value="info" beanName="info"
							formName="getElementById('form1')" />
					</td>
				</tr>
		   	</table>
	   	</s:form>
    </center>
    <script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("解冻审核成功!");
		}else if(temflag==2){
			alert("解冻审核失败!");
		}		
	</script>	
  </body>
</html>
