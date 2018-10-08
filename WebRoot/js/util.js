var windowDiv;

function privateHideProgressbar()
{ 
        var loader = document.getElementById("load"); 
        document.onmousemove=null;
        loader.style.display = "none";  
       
}
function privateShowProgressbar()
{      
        var loader = document.getElementById("load"); 
        document.onmousemove=privateProgressbarMove;
        loader.style.display = "block";  
        
}
function privateProgressbarMove(e)
{
    var curStyle;
    var loader = document.getElementById("load"); 
    curStyle=loader.style;
    curStyle.visibility="visible";
    var height;
    var width;
    height=curStyle.pixelHeight;
    width=curStyle.pixelWidth;
    width=parseInt(width);
    height=parseInt(height);
    var posx;
    posx=event.clientX;
    var newleft;
    if (posx>=(document.body.clientWidth - 5 - width))
    {
      newleft=document.body.clientWidth + document.body.scrollLeft - 5 - width;
    }
    else{
      newleft=document.body.scrollLeft + posx;
    }
    curStyle.pixelLeft=newleft;
    var posy;
    posy=event.clientY;
    var newtop;
    if (posy > (document.body.clientHeight - 5 - height))
    {
      newtop=document.body.clientHeight + document.body.scrollTop - 5 - height;
    }
    else{
      newtop=document.body.scrollTop + posy;
    }
    curStyle.pixelTop=newtop;
}

function privateCreateWindow(w,h,t){
   var idd = "#"+windowDiv[windowDiv.length-1].id;
   $(idd).dialog({width:w,height:h,title:t,hide:"true",show:"true",close:privateCloseWindowDiv,modal:true,overlay:{opacity:0.5,background:"black"} });
}

function privateCloseWindowDiv(){
     var vv = windowDiv.pop();
     vv.parentNode.removeChild(vv);
}
function privateCreateWindowDiv(){
    if(windowDiv==null){
       windowDiv= new Array();
    }
    var t = "t"+new Date().getTime();
    var winDiv = document.createElement('div');
    winDiv.id=t;
    winDiv.className="flora";
    var tit = $("#title").val();
    if(tit==undefined){
          tit="对话框";
    }
    winDiv.title=tit;
    
    windowDiv.push(winDiv);
    document.body.appendChild(winDiv);
    return t;
}
function privateInsertFormData(data){
        var idd = "#"+windowDiv[windowDiv.length-1].id;
        $(idd).dialog("close");
        privateCreateWindowDiv();
        idd = "#"+windowDiv[windowDiv.length-1].id;
        $(idd).html(data);
       
        var t = $("#title").val();
        if(t==undefined){
          t="对话框";
        }
        var w = 600;
        if($("#resizetable").attr("width")!=undefined){
           w = Number($("#resizetable").attr("width"))+35;
        }
        var h =550;
        if($("#resizetable").attr("height")!=undefined){
           h = Number($("#resizetable").attr("height"))+55;
        }
        privateCreateWindow(w,h,t);
}
function privateMergeUrl(url,arg,f,a,postarg){
  if(a==undefined){
     a=".action"; 
  }
  var pos = url.indexOf(a);
  if(pos==-1){
        url=url+a;
  }
  if(f!=undefined){
    if(arg!=undefined){
       var arr = arg.split("&");
       for(var i=0;i<arr.length;i++){
          var par = arr[i].split("=");
          var hid = document.createElement('input');
          hid.type="hidden";
          hid.name=par[0];
          hid.value=par[1];
          f.appendChild(hid);
       }
    }
    f.action=url;
  }else{
    if(arg!=undefined){
      if(postarg!=undefined){
        var arr = arg.split("&");
        for(var i=0;i<arr.length;i++){
           var par = arr[i].split("=");
           postarg[par[0]]=par[1];
        }     
      }else{
        url=url+"?"+arg;
      }
    }
  }
  return url;
}

//dialog
function openServletWindow(url,arg) {
     var postarg=new Object();
     url = privateMergeUrl(url,arg,undefined,".do",postarg);
     privateShowProgressbar();
     $.post(url,postarg, function (data) {
       privateHideProgressbar();
       
       var idd = "#"+windowDiv[windowDiv.length-1].id;
       $(idd).html(data);
       
        var t = $("#title").val();
        if(t==undefined){
          t="对话框";
        }
        var w = 600;
        if($("#resizetable").attr("width")!=undefined){
           w = Number($("#resizetable").attr("width"))+35;
        }
        var h =550;
        if($("#resizetable").attr("height")!=undefined){
           h = Number($("#resizetable").attr("height"))+55;
        }
        privateCreateWindow(w,h,t);
     });
}
function openSelectWindow(url,arg) {
     if(url.substring(url.length-".action".length)!=".action"){
        url=url+".action";
     }
     if(arg!=undefined){
      url=url+"?"+arg;  
    }
    var w=1;
    var h=1;
   
	var t = (screen.height - h) / 2;
	var l = (screen.width - w) / 2;
	var win = window.open(url, "_blank", "height=" + h + ",width=" + w + ",top=" + t + ",left=" + l + ",toolbar=no," + "menubar=no,scrollbars=yes,resizable=yes,location=no,status=yes");
	//showModalDialog(url,"","dialogWidth=430px;dialogHeight=115px;center:yes;status:no;help:no;scroll:no ")	;
	// var win = window.showModalDialog(url,"","dialogWidth="+w+"pt;dialogHeight="+h+"pt;center:yes;status:no;help:no;scroll:no");
	if (win.opener == null) win.opener = window;
	win.focus();
}
function openWindow(url,arg) {
     var postarg=new Object();
     url = privateMergeUrl(url,arg,undefined,".action",postarg);
     
     privateShowProgressbar();
     privateCreateWindowDiv();
     $.post(url,postarg, function (data) {
       privateHideProgressbar();
       var idd = "#"+windowDiv[windowDiv.length-1].id;
        $(idd).html(data);
        var t = $("#title").val();
        if(t==undefined){
          t="对话框";
        }
        var w = 600;
        if($("#resizetable").attr("width")!=undefined){
           w = Number($("#resizetable").attr("width"))+35;
        }
        var h =550;
        if($("#resizetable").attr("height")!=undefined){
           h = Number($("#resizetable").attr("height"))+55;
        }
        
        
        privateCreateWindow(w,h,t);
       
        windowDiv[windowDiv.length-1].url=url;
     });
}
function closeWindow() {
    if(windowDiv.length>0){
       var idd = "#"+windowDiv[windowDiv.length-1].id;
       $(idd).dialog("close");
    }
}

function reloadWindow(){
    if(windowDiv.length>0){
       var url = windowDiv[windowDiv.length-1].url;
       closeWindow();
       openWindow(url);
    }
}
function windowCount(){
    return windowDiv.length;
}

function goFormWindow(f,url,arg){
     url = privateMergeUrl(url,arg,f);
     $('#'+f.id).ajaxForm(function(data) {
  	      privateInsertFormData(data);
     });
     $('#'+f.id).submit();
}
function goWindow(url,arg){
     var postarg=new Object();
     url = privateMergeUrl(url,arg,undefined,".action",postarg);
     $.post(url,postarg, function (data) {
       privateInsertFormData(data);
     });
}
function goPage(url,arg) {
     window.location=privateMergeUrl(url,arg);
}
function goFormPage(url,arg) {
    var f = document.forms[0];
    if(f!=undefined){
       privateMergeUrl(url,arg,f);
       f.submit();
    }else{
       goPage(url,arg);
    }
}

//window
function operateWindow(url,arg,tip){
  if(tip==undefined){
    tip="";
  }
  if(window.confirm(tip)){
     openWindow(url,arg);
  }
}

function resizeWindow() {
    window.setTimeout(resizeWindowEvent,1); 
}
function resizeWindowEvent() {
    var width = Number(window.document.all['resizetable'].width)+30 ;
    var height = Number(window.document.all['resizetable'].height)+80;
	window.resizeTo(width, height);
	var t = (screen.height - height) / 2;
	var l = (screen.width - width) / 2;
	window.moveTo(t, l);
}

function maxWindow() {
    window.setTimeout(maxWindowEvent,1); 
}
function maxWindowEvent() {
    var width = window.screen.availWidth;
    var height = window.screen.availHeight;
	window.resizeTo(width, height);
	window.moveTo(0, 0);
}
//pagination
function proiPage(f,c){
	f.currentPage.value=c-1;
	f.submit();
}
function nextPage(f,c)
{    f.currentPage.value=c+1;
	f.submit();
}
function checkCurPage(f){
	var v = f.curPage.value;
    if(v==""){
		alert("请输入转向页号");
		return false;
	}
	v=parseInt(v);
	if(isNaN(v)){
		alert("页号必须是数字");
		return false;
	}
	if(v>parseInt(f.pageNumber.value)){
		alert("页号必须小于"+f.pageNumber.value);
		return false;
	}
	if(v<=0){
		alert("页号必须大于0");
		return false;
	}
	f.currentPage.value=v;
	return true; 
}
function seachPage(f)
{   if(checkCurPage(f)){
	   f.submit();
    }
}
//other
function choiceCategory() {
	var url="../category/selectCategories.action";
	$.post(url,function(data) {	
		$("#categoryTree").html(data);
	});
}