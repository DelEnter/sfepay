     var currentPlay;
     var plays=new Array();
     function singlePlay(id){
        var url = "../../pages/att/play.action?play.attachmentid="+id;
        $.post(url, function (data){
               $("#playbox").append(data);
        });
     }
     
     
     function play(id){
        currentPlay=id;
        setPlaySelect(id);
        playrange.window.location="../att/play.action?play.attachmentid="+id;
     }
     function init(){
        maxWindow();
        if(plays.length>0){
          previousBtn.disabled=false;
          nextBtn.disabled=false;
          play(plays[0]);
        }else{
          previousBtn.disabled=true;
          nextBtn.disabled=false;
        }
        
     }
     function nextPlay(){
        var p = getPlayNextPostion();
        if(p!=-1){
           play(plays[p]);
        }
     }
     function setPlaySelect(id){
        for(var i=0;i<plays.length;i++){
           document.all[plays[i]].style.display="none";
        }
        document.all[id].style.display="block";
     }
     
      function previousPlay(){
         var p = getPlayPreviousPostion();
        if(p!=-1){
           play(plays[p]);
        }
      }
     
     
     function getPlayCurrentPostion(){
        for(var i=0;i<plays.length;i++){
          if(plays[i]==currentPlay){
            return i;
          }
        }
        return -1;
     }
     
     function getPlayNextPostion(){
        if(currentPlay==null){
          return 0;
        }
        for(var i=0;i<plays.length-1;i++){
         if(plays[i]==currentPlay){
            return i+1;
          }
        }
        return 0;
     }
     function getPlayPreviousPostion(){
        if(currentPlay==null){
          return 0;
        }
      
        for(var i=1;i<plays.length;i++){
          if(plays[i]==currentPlay){
             return i-1;
          }
        }
        return plays.length-1;
     }