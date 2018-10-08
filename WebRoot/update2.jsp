<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>新增通道</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/addChannel");
	}
</script>  
<s:form action="addfast2" id="form1" namespace="/PaySystem" theme="simple">

<div id="title" value="新增通道"/>
<div id="resizetable" width="400" height="360">
  <div class="windows">
  
     <div class="left">开始1</div>
     <div class="right"><input type="text" name="beginno"/></div>
     <div class="left">结束100</div>
     <div class="right"><input type="text" name="endno"/></div>
 
        <input type="submit"   value="执行"  />
     </div>
 
  </div>
</div>

</s:form>