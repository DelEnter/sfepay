<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<title>无标题文档</title> 
	<script language="javascript"> 
	/** * 打开对话框，从服务器上选择图片 */ 
	function selectProductPic(){
		var type = document.getElementById("type").value; 
		if (type == null || type == "") {
			type = "Image"; 
		} 
		var url = "fileManager1?type=" + type; 
		//alert(url); 
		var str = window.showModalDialog(url,"","dialogWidth=800px;dialogHeight=530px;center=yes;help=no;status=0"); 
		if (str != null) {
			document.getElementById("filename").innerHTML = "文件名：" + str.fileName + "<br/>" + "文件夹：" + str.folderPath + "<br/>" + "完整路径：" + str.filePath ; 
			//document.getElementById("img1").src=str.path; 
			//document.getElementById("image").value=str.path; 
		} 
	} 
	</script> 
	</head> 
	<body> 
	<select name="type" id="type"> 
	<option value="File">文件</option> 
	<option value="Image">图片</option> 
	<option value="Flash">Flash</option> 
	<option value="Media">Media</option> 
	</select> 
	<input type="button" value="选择" title="选择" onclick="javascript:selectProductPic()"/> 
	<div id="filename"></div> 
	</body> 
	</html>