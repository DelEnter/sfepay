<?php
//me5c@hotmail.com
$pid="cho";
include("includes/atop.php");
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择我们 - 迁易通支付</title>
<?php include("includes/head.php"); ?>
<script type="text/javascript">
 $(document).ready(function(){
document.getElementById("num").focus();
});

</script>
</head>
<body>
<div id="sfe">
<?php 
include("includes/header.php");
echo '<div class="page">';
include("includes/left.php");
include("includes/choose.php");
echo '</div>';
include("includes/footer.php");
?>
</div>
</body>
</html>