<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<form action="<s:property value='icbcpayurl'/>" method="post">
<input type="hidden" name="interfaceName" value="<s:property value='interfaceName'/>" />
<input type="hidden" name="interfaceVersion" value="<s:property value='interfaceVersion'/>" />
<input type="hidden" name="tranData" value="<s:property value='tranData'/>" />
<input type="hidden" name="merSignMsg" value="<s:property value='merSignMsg'/>" />
<input type="hidden" name="merCert" value="<s:property value='merCert'/>" />
<input type="submit">
</form>