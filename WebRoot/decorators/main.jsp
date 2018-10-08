<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<title><decorator:title default="Mysterious page..." />
		</title>
		<decorator:head />
	</head>
	<body>
		<table width="100%" height="100%">
			<tr>
				<td valign="top">
				</td>
				<td width="100%">
				    <s:if test="#parameters.pageStyle[0] == 'popwin'">
						<table width="100%" height="100%">
							<tr>
								<td id="pageTitle">
										(siteMesh 添加)弹出窗口
								</td>
							</tr>
							<tr>
								<td valign="top" height="100%">
									<decorator:body />
								</td>
							</tr>
						</table>
				    </s:if>
				    <s:else>
						<table width="100%" height="100%">
							<tr>
								<td id="pageTitle">
								(siteMesh 添加)页面header：
									<s:if test="#loginName != null">
										欢迎[<s:property value="#loginName" />]登陆！<br>
									</s:if>
								</td>
							</tr>
							<tr>
								<td valign="top" height="100%">
									<decorator:body />
								</td>
							</tr>
							<tr>
								<td id="footer">
									<b>(siteMesh 添加):</b> 页面的footer
								</td>
							</tr>
						</table>
					</s:else>
				</td>
			</tr>
		</table>
	</body>
</html>