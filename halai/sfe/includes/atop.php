<?php
//me5c@hotmail.com
if(extension_loaded("zlib") && strstr($_SERVER["HTTP_ACCEPT_ENCODING"],"gzip")){
    ob_start("ob_gzhandler");
}else {
    echo "php zlib使用失败，请联系管理员";//确认是否已加载zlib扩展
    exit();
}
if (strtolower( $_SERVER['SERVER_NAME'])=='sfepay.com'){
Header( "HTTP/1.1 301 Moved Permanently" ) ;
Header( "Location: http://www.sfepay.com".$_SERVER['REQUEST_URI']."" );
}
?>