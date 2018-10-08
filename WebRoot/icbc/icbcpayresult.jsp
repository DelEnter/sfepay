<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head><title>Result</title></head>

<body>

<p align="center">=Result</p>
<table align="center" border="1" cellpadding="4">
<s:property value="messageAction"/>
	<s:iterator value="payResultHashMap" >
		<tr><td><s:property value="key"/>=<s:property value="value"/></td></tr>
	</s:iterator>
</table>
</body>

</html>