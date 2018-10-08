<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>交易信息</title>
<script language="JavaScript" src="../js/util.js"></script>
</head>
<div align="center" >
	<h3>交易信息</h3>
</div>
<s:form name="formu" id="formu" method="post" theme="simple" action="tradeinfo">
<table align="center">
			<tr class=TR_Title>
				<td>商户号</td>
		 		<td><s:textfield name="merchant.merno"/></td>
	 		
		 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
		 		</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >
				</td>
				
			</tr>
	</table>
	</br></br>
	<table cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
		<tr bgColor=#cccccc>
			<td>商户号</td>
			<td>币种</td>
			<td>失败金额</td>
			<td>失败笔数</td>
			<td>成功金额</td>
			<td>成功笔数</td>
			<td>成功比率</td>
			<td>待处理金额</td>
			<td>待处理笔数</td>
			<td>取消金额</td>
			<td>取消笔数</td>
			<td>待确认金额</td>
			<td>待确认笔数</td>
			<td>未返回金额</td>
			<td>未返回笔数</td>
			<td>退款金额</td>
			<td>退款笔数</td>
			<td>退款金额(退款日期)</td>
			<td>退款笔数(退款日期)</td>
			<td>退款比率(退款日期)</td>
			<td>拒付金额</td>
			<td>拒付笔数</td>
			<td>拒付比率</td>
			<td>拒付金额(拒付日期)</td>
			<td>拒付笔数(拒付日期)</td>
			<td>拒付比率(拒付日期)</td>
			<td>冻结金额</td>
			<td>冻结笔数</td>
		</tr>
	<s:iterator id="trade" value="tradeList">
		<tr>
			<td><s:property value="#trade[0]" />&nbsp;</td>
			<td><s:property value="states.getCurrencyTypeByNo(#trade[24])" />&nbsp;</td>
			<td><s:property value="#trade[4]" />&nbsp;</td>
			<td><s:property value="#trade[5]" />&nbsp;</td>
			<td><s:property value="#trade[6]" />&nbsp;</td>
			<td><font color="green" size="3"><s:property value="#trade[7]" />&nbsp;</font></td>
			<td>
				<font color="red" size="3">
					<s:property value="caclulate.getValue(#trade[7],#trade[5])"/>&nbsp;
				</font>
			</td>
			
			<td><s:property value="#trade[8]" />&nbsp;</td>
			<td><s:property value="#trade[9]" />&nbsp;</td>
			
			<td><s:property value="#trade[10]" />&nbsp;</td>
			<td><s:property value="#trade[11]" />&nbsp;</td>
			
			<td><s:property value="#trade[12]" />&nbsp;</td>
			<td><s:property value="#trade[13]" />&nbsp;</td>
			
			<td><s:property value="#trade[14]" />&nbsp;</td>
			<td><s:property value="#trade[15]" />&nbsp;</td>
			
			<td><s:property value="#trade[16]" />&nbsp;</td>
			<td><s:property value="#trade[17]" />&nbsp;</td>
			
			
			
			<td><s:property value="#trade[25]" />&nbsp;</td>
			<td><s:property value="#trade[26]" />&nbsp;</td>
			<td>
			<font color="red" size="3">
			<s:property value="caclulate.getValue2(#trade[26],#trade[7])"/>&nbsp;
			</font>
			</td>

			<td><s:property value="#trade[18]" />&nbsp;</td>
			<td><s:property value="#trade[19]" />&nbsp;</td>
			<td>
				<font color="red" size="3">
					<s:property value="caclulate.getValue2(#trade[19],#trade[7])"/>&nbsp;
				</font>
			</td>
			
			<td><s:property value="#trade[22]" />&nbsp;</td>
			<td><s:property value="#trade[23]" />&nbsp;</td>
			<td>
			<font color="red" size="3">
			<s:property value="caclulate.getValue2(#trade[23],#trade[7])"/>&nbsp;
			</font>
			</td>
			
			<td><s:property value="#trade[20]" />&nbsp;</td>
			<td><s:property value="#trade[21]" />&nbsp;</td>
			
		</tr>
		</s:iterator>
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
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>











