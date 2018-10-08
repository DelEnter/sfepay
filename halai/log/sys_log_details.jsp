<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统日志详细</title>
		<script language="JavaScript" src="../js/util.js"></script>
		<link href="../css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>


		<div class="mrbbtit">
			<h2>
				系统日志
			</h2>
		</div>

		<div class="mrbox-a">
			<table class="" align="left">
			<tr>
					<td>
						商户号：
					</td>
					<td>
						<s:textfield name="systemLog.merno"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						记录时间：
					</td>
					<td>
						<s:textfield name="systemLog.operTime"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>
						操作员用户名：
					</td>
					<td>
						<s:textfield name="systemLog.userName"></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						日志详细：
					</td>
					<td colspan="2">
						<textarea rows="8" cols="100" disabled>
							<s:property value="systemLog.remarks" />
						</textarea>
					</td>
				</tr>
			</table>

			<div class="clear"></div>
			<ul class="btu-box">
				<li>
					<button type="submit" class="mrboxbtu" onClick="window.close()">
						返回
					</button>
				</li>
			</ul>
		</div>


	</body>
</html>