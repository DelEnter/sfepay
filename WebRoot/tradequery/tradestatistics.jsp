<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
	 <%@ include file="../include/dialog.jsp"%>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>交易统计</title>
<script language="JavaScript" src="../js/util.js"></script>
</head>
<div align="center" >
	<h3>交易统计</h3>
</div>
<s:form name="formu" id="formu" action="tradestatistics" method="post" theme="simple">
<table align="center">
			<tr class=TR_Title>
				<td>商户号</td>
		 		<td><s:textfield name="merchant.merno"/></td>
		 		<td>流水订单号</td>
		 		<td><s:textfield name="tradeinfo.orderNo"/></td>
		 		<td>商户订单号</td>
		 		<td><s:textfield name="tradeinfo.merchantOrderNo"/></td>
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
	<div>
	<table cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
		<tr>
			<td colspan="9" align="center"><font color="red" size="3">交易状态</font></td>
		</tr>
		<tr>
			<td>币种</td>
			<td>成功</td>
			<td>待处理</td>
			<td>取消</td>&nbsp;
			<td>待确认</td>
			<td>未返回</td>
			<td>失败</td>
			<td>比数</td>
		</tr>
		<s:iterator value="tradestateAmount" id="tradestate">
			<tr>
				<td>
					<s:if test="#tradestate[2] == 1">USD</s:if>
					<s:if test="#tradestate[2] == 2">EUR</s:if>
					<s:if test="#tradestate[2] == 4">GBP</s:if>
					<s:if test="#tradestate[2] == 3">CNY</s:if>
				</td>
				<td>
					<s:if test="#tradestate[0] == 1"><s:property value="#tradestate[1]" />&nbsp;</s:if>
					&nbsp;
				</td>
				<td>
					<s:if test="#tradestate[0] == 2"><s:property value="#tradestate[1]" />&nbsp;</s:if>
					&nbsp;
				</td>
				<td>
					<s:if test="#tradestate[0] == 3"><s:property value="#tradestate[1]" />&nbsp;</s:if>
					&nbsp;
				</td>
				<td>
					<s:if test="#tradestate[0] == 4"><s:property value="#tradestate[1]" />&nbsp;</s:if>
					&nbsp;
				</td>
				<td>
					<s:if test="#tradestate[0] == 5"><s:property value="#tradestate[1]" />&nbsp;</s:if>
					&nbsp;
				</td>
				<td>
					<s:if test="#tradestate[0] == 0"><s:property value="#tradestate[1]" />&nbsp;</s:if>
					&nbsp;
				</td>
				<td>
					<s:if test="#tradestate[0] == 0"><s:property value="#tradestate[3]" />&nbsp;</s:if>
					&nbsp;
					<s:if test="#tradestate[0] == 1"><s:property value="#tradestate[3]" />&nbsp;</s:if>
					&nbsp;
					<s:if test="#tradestate[0] == 2"><s:property value="#tradestate[3]" />&nbsp;</s:if>
					&nbsp;
					<s:if test="#tradestate[0] == 3"><s:property value="#tradestate[3]" />&nbsp;</s:if>
					&nbsp;
					<s:if test="#tradestate[0] == 4"><s:property value="#tradestate[3]" />&nbsp;</s:if>
					&nbsp;
					<s:if test="#tradestate[0] == 5"><s:property value="#tradestate[3]" />&nbsp;</s:if>
					&nbsp;
				</td>
			</tr>
		</s:iterator>
	</table>
	</br></br>
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="5" align="center"><font color="red" size="3">退款</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未退款总金额</td>
				<td>部分退款金额</td>
				<td>全部退款金额</td>
				<td>比数</td>
			</tr>
			<s:iterator value="drawbackAmount" id="drawback">
				<tr>
					<td>
					<s:if test="#drawback[3] == 1">USD</s:if>
					<s:if test="#drawback[3] == 2">EUR</s:if>
					<s:if test="#drawback[3] == 4">GBP</s:if>
					<s:if test="#drawback[3] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#drawback[0] == 0"><s:property value="#drawback[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#drawback[0] == 2"><s:property value="#drawback[2]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#drawback[0] == 1"><s:property value="#drawback[2]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#drawback[0] == 0"><s:property value="#drawback[4]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#drawback[0] == 1"><s:property value="#drawback[4]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#drawback[0] == 2"><s:property value="#drawback[4]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="4" align="center"><font color="red" size="3">拒付</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未拒付</td>
				<td>已拒付</td>
				<td>比数</td>
			</tr>
			<s:iterator value="protestAmount" id="protest">
				<tr>
					<td>
						<s:if test="#protest[2] == 1">USD</s:if>
						<s:if test="#protest[2] == 2">EUR</s:if>
						<s:if test="#protest[2] == 4">GBP</s:if>
						<s:if test="#protest[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#protest[0] == 0"><s:property value="#protest[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#protest[0] == 1"><s:property value="#protest[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#protest[0] == 0"><s:property value="#protest[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#protest[0] == 1"><s:property value="#protest[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="6" align="center"><font color="red" size="3">冻结</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未冻结</td>
				<td>已冻结</td>
				<td>解冻</td>
				<td>审核解冻</td>
				<td>比数</td>
			</tr>
			<s:iterator value="freezeAmount" id="freeze">
				<tr>
					<td>
						<s:if test="#freeze[2] == 1">USD</s:if>
						<s:if test="#freeze[2] == 2">EUR</s:if>
						<s:if test="#freeze[2] == 4">GBP</s:if>
						<s:if test="#freeze[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#freeze[0] == 0"><s:property value="#freeze[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#freeze[0] == 1"><s:property value="#freeze[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#freeze[0] == 2"><s:property value="#freeze[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#freeze[0] == 3"><s:property value="#freeze[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#freeze[0] == 0"><s:property value="#freeze[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#freeze[0] == 1"><s:property value="#freeze[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#freeze[0] == 2"><s:property value="#freeze[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#freeze[0] == 3"><s:property value="#freeze[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="4" align="center"><font color="red" size="3">勾兑</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未勾兑</td>
				<td>已勾兑</td>
				<td>比数</td>
			</tr>
			<s:iterator value="blendAmount" id="blend">
				<tr>
					<td>
						<s:if test="#blend[2] == 1">USD</s:if>
						<s:if test="#blend[2] == 2">EUR</s:if>
						<s:if test="#blend[2] == 4">GBP</s:if>
						<s:if test="#blend[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#blend[0] == 0"><s:property value="#blend[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#blend[0] == 1"><s:property value="#blend[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#blend[0] == 0"><s:property value="#blend[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#blend[0] == 1"><s:property value="#blend[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="3" align="center"><font color="red" size="3">申请</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未申请</td>
				<td>已申请</td>
				<td>比数</td>
			</tr>
			<s:iterator value="applyAmount" id="apply">
				<tr>
					<td>
						<s:if test="#apply[2] == 1">USD</s:if>
						<s:if test="#apply[2] == 2">EUR</s:if>
						<s:if test="#apply[2] == 4">GBP</s:if>
						<s:if test="#apply[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#apply[0] == 0"><s:property value="#apply[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#apply[0] == 1"><s:property value="#apply[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#apply[0] == 1"><s:property value="#apply[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#apply[0] == 1"><s:property value="#apply[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="5" align="center"><font color="red" size="3">审核</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未审核</td>
				<td>已审核</td>
				<td>问题单</td>
				<td>比数</td>
			</tr>
			<s:iterator value="auditAmount" id="audit">
				<tr>
					<td>
						<s:if test="#audit[2] == 1">USD</s:if>
						<s:if test="#audit[2] == 2">EUR</s:if>
						<s:if test="#audit[2] == 4">GBP</s:if>
						<s:if test="#audit[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#audit[0] == 0"><s:property value="#audit[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#audit[0] == 1"><s:property value="#audit[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#audit[0] == 2"><s:property value="#audit[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#audit[0] == 0"><s:property value="#audit[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#audit[0] == 1"><s:property value="#audit[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#audit[0] == 2"><s:property value="#audit[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="4" align="center"><font color="red" size="3">是否划款</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未划款</td>
				<td>已划款</td>
				<td>比数</td>
			</tr>
			<s:iterator value="transfermoneyAmount" id="transfermoney">
				<tr>
					<td>
						<s:if test="#transfermoney[2] == 1">USD</s:if>
						<s:if test="#transfermoney[2] == 2">EUR</s:if>
						<s:if test="#transfermoney[2] == 4">GBP</s:if>
						<s:if test="#transfermoney[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#transfermoney[0] == 0"><s:property value="#transfermoney[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#transfermoney[0] == 1"><s:property value="#transfermoney[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#transfermoney[0] == 0"><s:property value="#transfermoney[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#transfermoney[0] == 1"><s:property value="#transfermoney[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="4" align="center"><font color="red" size="3">保证金审核</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未审核</td>
				<td>已审核</td>
				<td>比数</td>
			</tr>
			<s:iterator value="bailauditAmount" id="bailaudit">
				<tr>
					<td>
						<s:if test="#bailaudit[2] == 1">USD</s:if>
						<s:if test="#bailaudit[2] == 2">EUR</s:if>
						<s:if test="#bailaudit[2] == 4">GBP</s:if>
						<s:if test="#bailaudit[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#bailaudit[0] == 0"><s:property value="#bailaudit[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#bailaudit[0] == 1"><s:property value="#bailaudit[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#bailaudit[0] == 0"><s:property value="#bailaudit[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#bailaudit[0] == 1"><s:property value="#bailaudit[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
		
		
		<table cllSpacing="0" cellPadding="0" align="center" border=1 width="100%">
			<tr>
				<td colspan="4" align="center"><font color="red" size="3">保证金划款</font></td>
			</tr>
			<tr>
				<td>币种</td>
				<td>未划款</td>
				<td>已划款</td>
				<td>比数</td>
			</tr>
			<s:iterator value="bailtransfermoneyAmount" id="bailtransfermoney">
				<tr>
					<td>
						<s:if test="#bailtransfermoney[2] == 1">USD</s:if>
						<s:if test="#bailtransfermoney[2] == 2">EUR</s:if>
						<s:if test="#bailtransfermoney[2] == 4">GBP</s:if>
						<s:if test="#bailtransfermoney[2] == 3">CNY</s:if>
					</td>
					<td>
						<s:if test="#bailtransfermoney[0] == 0"><s:property value="#bailtransfermoney[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#bailtransfermoney[0] == 1"><s:property value="#bailtransfermoney[1]" />&nbsp;</s:if>
						&nbsp;
					</td>
					<td>
						<s:if test="#bailtransfermoney[0] == 0"><s:property value="#bailtransfermoney[3]" />&nbsp;</s:if>
						&nbsp;
						<s:if test="#bailtransfermoney[0] == 1"><s:property value="#bailtransfermoney[3]" />&nbsp;</s:if>
						&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
		</br></br>
	
	</div>
	</br></br>
<div align="center">
<pages:pages value="info" beanName="info" formName="forms(0)" />
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
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>











