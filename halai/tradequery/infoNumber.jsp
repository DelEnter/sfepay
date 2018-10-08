<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>修改交易信息</title>
<script language="JavaScript" src="../js/util.js"></script>

<script>
	function submit1(){
		var form1 = document.getElementById("formu");
		form1.action="updateinfoNumber";
		form1.submit();
	}
	
	function submi2(){
		var form1 = document.getElementById("formu");
		form1.action="selectNumber";
		form1.submit();
	}
</script>

</head>
<div align="center" >
	<h3>修改交易信息</h3>
</div>
<s:form name="formu" id="formu" method="post" theme="simple" action="selectNumber">
<table align="center">
			<tr class=TR_Title>	 		
		 		<td>流水号</td>
		 		<td><s:textfield name="ordNo" /></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="submi2()" value="查询" >				</td>
			</tr>
  </table>
	
	</br></br>
	<table  border="0" cellspacing="5"  cellpadding="0">
	<tr>
		<td>定单号</td>
		<td>
		    <s:hidden name="interr.id"/>
			<s:property value="interr.orderNo"/>
		</td>
	</tr>

	<tr>
		<td class="font_14px">金额</td>
		<td>
			<s:textfield name="interr.tradeAmount"/>
		</td>
	</tr>
	<tr>
	<td class="font_14px">RMB金额</td>
	<td>
	<s:textfield name="interr.rmbAmount"/>
	</td>
	</tr>
	<tr>
		<td class="font_14px">授权号</td>
		<td>
			<s:textfield name="interr.VIPAuthorizationNo"/>
		</td>
	</tr>
	<tr>
		<td class="font_14px">批次号</td>
		<td>
			<s:textfield name="interr.batchNo"/>
		</td>
	</tr>
	<tr>
		<td class="font_14px">状态值</td>
		<td>
			<s:textfield name="interr.tradeState"/>
		</td>
	</tr>
	<tr>
		<td class="font_14px">跟踪单号</td>
		<td>
			<s:textfield name="interr.isTrackNo"/>
		</td>
	</tr>
	<tr>
	<td class="font_14px">备注</td>
	<td>
	<s:textfield name="interr.remark"/>
	</td>
	</tr>

	<tr>
		<td class="font_14px"></td>
		<td>
			<input type="button" onclick="submit1()" value="修改"/>
		</td>
	</tr>
	
	<br/>
	
	<tr>
		<td colspan="2">
		 *名称      占位符     状态  <br/>
		 * 交易状态	 1	       0 失败  1 成功  2 待处理   3 取消  4 待确认 5未返回 <br/>
	     * 退款	     2	       0 未退款  1已退款  2部分退款<br/>
		 * 拒付	     3	       0 未拒付   1已拒付 <br/>
		 * 冻结	     4	       0 未冻结   1 已冻结  2 解冻  3审核解冻<br/>
		 * 勾兑	     5	       0 未勾兑  1 已勾兑<br/>
		 * 申请	     6	       0未申请   1已申请<br/>
		 * 审核	     7	       0 未审核   1已审核 2 问题单<br/>
		 * 是否划款	 8	       0 未划款   1已划款<br/>
		 * 保证金审核	 9	       0 未审核    1 已审核<br/>
		 * 保证金划款	 10	       0 未划款    1 已划款<br/>
		 * 勾兑状态   11         0正常      1 金额不对  2 批次号不对 3 授权号不对<br/>
		 * 返回值状态  12        0 已返回   1 未返回  <br/>
		 * 自动处理交易 13       0未处理   1已处理  <br/>
		 */<br/>
		</td>
	</tr>
</table>
</s:form>