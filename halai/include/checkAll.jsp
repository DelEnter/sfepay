<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script language="JavaScript">
		function chkall(input1,input2)
		{
		    var objForm = document.forms[input1];
		    var objLen = objForm.length;
		    for (var iCount = 0; iCount < objLen; iCount++)
		    {
		        if (input2.checked == true)
		        {
		            if (objForm.elements[iCount].type == "checkbox")
		            {
		                objForm.elements[iCount].checked = true;
		            }
		        }
		        else
		        {
		            if (objForm.elements[iCount].type == "checkbox")
		            {
		                objForm.elements[iCount].checked = false;
		            }
		        }
		    }
		}
	</script>
    
  </head>
  
  <body>
    
  </body>
</html>
