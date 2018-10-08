<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="webwork" prefix="ww" %>

<!-- 带有复选框(CheckBox)的tree -->
<script  src="../../js/dhtmlXCommon.js"></script>
<script  src="../../js/dhtmlXTree.js"></script>	
<link href="../../css/treestyle/dhtmlXTree.css" rel="stylesheet" type="text/css" >


<script>
       function closeAllRoots(){
			var rootsAr = tree.getSubItems(0).split(",")
			for(var i=0;i<rootsAr.length;i++){
				tree.closeAllItems(rootsAr[i])
			}
		}
	   var tree=new dhtmlXTreeObject("treeBox","100%","100%",0);
        tree.setImagePath("../../images/tree/");
       	tree.setOnClickHandler(clickEvent);
       	tree.enableCheckBoxes(1);
      
        <ww:iterator value="tree">
                tree.insertNewChild("<ww:property value="parent" />","<ww:property value="path" />","<ww:property value="name" />",0,0,0,0,"SELECT");
        </ww:iterator>
        closeAllRoots();
</script>
	