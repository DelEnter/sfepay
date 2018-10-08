function selectResourcePresent(){
 var resourceId=$("#resourceId").val();
 var resourceName=$("#resourceName").val();
 var resourceCount=$("#resourceCount").val();  

opener.modifyReource(resourceId,resourceName,resourceCount,'list2');
//opener.selectpresentResourceIds(resourceId,'1',resourceName);

opener.selectpresentResourcevalue();
//opener.document.all.F3.value=resourceName+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+resourceCount+"&nbsp&nbsp&nbsp个";
//opener.document.all.F4.value=resourceId+":"+resourceCount;

//window.opener.document.all.F3.click();

}
function modifyReource(resourceId,resourceName,resourceCount,list){
var objList = new CList(list, "fck");

var leng=objList.GetLength(); 
var i=0;
if(leng==0){
      
var a=resourceName+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+resourceCount+"&nbsp&nbsp&nbsp个";   
var b=resourceId+":"+resourceCount;
objList.AppendItem(b, a, true); 
}
else{
for(i;i<leng;i++){

      var oldIdString=objList.getItem(i,'value').split(':');
      
      var oldId=oldIdString[0];

         if(resourceId==oldId){

       var  newResourceCount=parseInt(resourceCount)+parseInt(oldIdString[1]);

         objList.DeleteItem(i);
var a=resourceName+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+resourceCount+"&nbsp&nbsp&nbsp个";   
var b=resourceId+":"+resourceCount;
         objList.AppendItem(b, a, true);
         }
         else{
     
var a=resourceName+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+resourceCount+"&nbsp&nbsp&nbsp个";   
var b=resourceId+":"+resourceCount;
objList.AppendItem(b, a, true);       
         }
         
}
}
}
function selectResource(){
 var resourceId=$("#resourceId").val();
 var resourceName=$("#resourceName").val();
 var resourceCount=$("#resourceCount").val();  
opener.modifyReource(resourceId,resourceName,resourceCount,'list'); 
opener.getresource();

 // opener.opener.addtask.value=opener.opener.addtask.value+";"+resourceId+"\'"+resourceName+"\."+resourceCount;
 //  windows.opener.opener.showff('222222222');
//opener.document.all.F1.value=resourceName+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+resourceCount+"&nbsp&nbsp&nbsp个";
//opener.document.all.F2.value=resourceId+":"+resourceCount;

//window.opener.document.all.F1.click();

 //  alert(resourceId+resourceName);
}

function checkResourceName(a){
     if($("#usersname").val()=='')
     {  return 
     }
//	var a = "../mamager/checkResourceName.action?resourceBean.name=" + $("#usersname").val();
	
//	$.post(a, function (data) {
//		$("#checkname").html(data);

//		if(data=='该名称已存在'){

//		document.all.usersname.value="";
//		return 
//		}
//	});
  document.all.checkNameMessage.value=a;
       $("#checkForm").ajaxForm(function(data) {
  	      privateInsertFormData(data);
     });
  
  
   document.all.checkForm.submit();
   
   
     





}
function checkTaskName(){
	var a = "../mamager/checkTaskName.action?taskBean.name=" + $("#usersname").val();
	
	$.post(a, function (data) {
		$("#checkname").html(data);
	});

}

function listPosession(s,c){

    var a="../mamager/listProsession.action?progressionBean.level="+s+"&progressionBean.gameType.id="+c;
   	$.post(a, function (data) {
		$("#listProsession").html(data);
	}); 
 
}

function getresource(){
  var objList = new CList("list", "fck");
    var a="";
  for(var i=0;i<objList.GetLength();i++){
    if(a.length>2){
        a=a+","+objList.getItem(i,'value');
    }
    else{
     a=a+objList.getItem(i,'value');
    }
    document.all.acceptResourceIds.value=a;
  }

}
function goselectResource(a,b,c){

var objList = new CList("list", "fcfs");
      if(b=='1'){
       
           objList.AppendItem(a, c, true);
      }
      if(b=='2'){

    if(objList.GetLength()==1){
    objList.DeleteItem(0);
    document.all.acceptResourceIds.value=""; 
    }
    else{
        objList.DeleteItem(objList.GetLength()-1)
    }
//  objList.DeleteItem(0);
      getresource();
      }
      if(b=='3'){
    var i=0;

while(i<objList.GetLength())
{
if(objList.IsChecked(i)){objList.DeleteItem(i);}

else{i++;}
 getresource(); 
};      
    
if(objList.GetLength()==0){
document.all.acceptResourceIds.value=""; 

}    
      
      }
  

}


function selectpresentResourceIds(a,b,c){

var objList2 = new CList("list2", "fck");
      if(b=='1'){
           objList2.AppendItem(a, c, true);
           
      }
      if(b=='2'){
     objList2.DeleteItem(objList2.GetLength()-1)
      selectpresentResourcevalue();
if(objList2.GetLength()==0){
selectpresentResourcevalue();
document.all.presentResourcevalue.value="";
}      
      }
      if(b=='3'){
    var i=0;

while(i<objList2.GetLength())
{
if(objList2.IsChecked(i)){objList2.DeleteItem(i);}

else{i++;}
selectpresentResourcevalue();
if(objList2.GetLength()==0){
selectpresentResourcevalue();
document.all.presentResourcevalue.value="";
}
}

      
}
      
}
function selectpresentResourcevalue(){

  var objList2 = new CList("list2", "fck");
    var a="";
  for(var i=0;i<objList2.GetLength();i++){
    if(a.length>2){
        a=a+","+objList2.getItem(i,'value');
    }
    else{
     a=a+objList2.getItem(i,'value');
    }
    document.all.presentResourcevalue.value=a;
  }

}

function addSelectItem(){

var g=document.all.selectanswer.value;
 if(document.all.answer.value==''||document.all.answer.value==null){
    alert("bunengweikong");
    return false ;
}
 var a=g+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+document.all.answer.value;
 var valueSelect=g+"\;"+document.all.answer.value;
 //document.all.textContent.value=valueSelect;
 valueName="valueName"+g;
  var cc=objList.GetLength();
  

    var i=0;
    for(i;i<cc;i++){
    if(objList.getItem(i,'value').substring(0,1)==g){
    alert("yijingcunzi");
    return
    
    }

}
    objList.AppendItem(valueSelect, a, true,valueName);
    document.all.answer.value='';
    getSelectItem();

} 

function checkAnswer(){
var answer=document.all.rightAnswer.value;
  var cc=objList.GetLength();
  

    var i=0;
    for(i;i<cc;i++){
    if(!(objList.getItem(i,'value').substring(0,1)==answer)){
    alert("bukeyi");
    return false;
    
    }
    else{
    return true;
    }

}




}



function getSelectItem(){

  var objList = new CList("list", "fck");
    var a="";

    
  for(var i=0;i<objList.GetLength();i++){
    if(a.length>2){

        a=a+";"+objList.getItem(i,'value');
    }
    else{
      
     a=a+objList.getItem(i,'value');

    }
    document.all.textContent.value=a;
  }

}

function addUpdateAcceptResource(){
     var acceptRShow=document.all.acceptResourceShow.value;
     if(!(acceptRShow==''||acceptRShow==null)){
     var initAcceptResource = acceptRShow.split(",");

     var acceptRValue=document.all.acceptResourceIds.value; 

     var initAcceptResourceValue=acceptRValue.split(",");

       for(var i=0;i<initAcceptResource.length;i++){
       
          var va = initAcceptResource[i];

         var  b=initAcceptResourceValue[i];

          var c="1";
        goselectResource(b,c,va);
       }
       }
}
function addUpdatePresentResource(){
     var presentRShow=document.all.presentResourceShow.value;
     
     if(!(presentRShow==''||presentRShow==null)){
     var initPresentResource = presentRShow.split(",");

     var presentRValue=document.all.presentResourcevalue.value; 

     var initPresentResourceValue=presentRValue.split(",");

       for(var i=0;i<initPresentResource.length;i++){
       
          var va = initPresentResource[i];

         var  b=initPresentResourceValue[i];

          var c="1";
      selectpresentResourceIds(b,c,va);      
       // goselectResource(b,c,va);
       
       
       }
       }
   addUpdateAcceptResource();    
        
}

function defaultcheck(){

   
     if(document.getElementById('test1').disabled==true)
{ 

document.getElementById('test1').disabled=false;
document.getElementById('test2').disabled=false;
document.getElementById('test3').disabled=false;
document.getElementById('test4').disabled=false;
document.getElementById('test5').disabled=false;
document.getElementById('test6').disabled=false;
document.getElementById('test7').disabled=false;
document.getElementById('test8').disabled=false;


    return     
 }
if(document.getElementById('test1').disabled==false)
{
document.getElementById('test1').disabled = true;
document.getElementById('test2').disabled = true;
document.getElementById('test3').disabled = true;
document.getElementById('test4').disabled = true;
document.getElementById('test5').disabled = true;
document.getElementById('test6').disabled = true;
document.getElementById('test7').disabled = true;
document.getElementById('test8').disabled = true;
}
}
function checkResult(){

var re=/^(\d{1,2}|100)$/;
var str=document.all.calcuResult.value;
if(re.test(str))
{
goFormPage('../grade/initCalcuReultItem.action');
}
else{
alert("评分范围为0-100");
document.all.calcuResult.focus();
document.all.calcuResult.value="";
}
}
function checkResult2(){

var re=/^(\d{1,2}|100)$/;
var str=document.all.calcuResult.value;
if(re.test(str))
{
goFormPage('../grade/saveCalcuReult.action');
}
else{
alert("评分范围为0-100");
document.all.calcuResult.focus();
document.all.calcuResult.value="";
}
}




