var playerVid="";
var playerObjectId="";
var customRecodLastTimeState = 0;//seek()方法重复触发事件recodLastTime()状态码（重复触发原因：限制滚动条只能向后拖的功能代码）——yxt

/***
	    程宏业  2017-03-24  
	  设置内置播放器属性
	  详情见 http://doc.bokecc.com/vod/dev/PlayerAPI/player01/
	  官方文档
	 该方法单独存在不可被其他方法调用!  
	   **/
	   /**** 方法开始***/
	   function on_cc_player_init( vid, objectID ){
		   var config = {};
	    //config.fullscreen_enable = 1; //启用自定义全屏
	   // config.fullscreen_function = "customFullScreen"; //设置自定义全屏函数的名称
	   // config.on_player_pause = "onPlayPaused"; //设置当暂停播放时的回调函数的名称
	    config.control_enable= 1;
	    config.on_player_start="on_spark_player_start";
	    config.on_player_seek="recodLastTime";
	    this.playerVid=vid;
	    this.playerObjectId=objectID;
	    //config.progressbar_enable = 1;
	    var player = getSWF( objectID );
	    player.setConfig( config );
	  }
     //拖动滚动条时事件
     function recodLastTime(from,to){
    	 /*if(to>from){//限制滚动条只能向后拖的代码片段（lifei.version）
        	 var player = getSWF( playerObjectId );
        	 player.seek(from)
    		 return false;
    	 }*/
    	 if(typeof(vPlayBegin)!='undefined'&&vPlayBegin){
    		 vPlayBegin = false;
    	 }else{
    		 //限制只能在观看过的时间段内向前拖动
    		 if(typeof(currVideoPlayLengthMax)!='undefined'){
        		 if(from>currVideoPlayLengthMax){
        			 currVideoPlayLengthMax = from;
        		 }
        		 if(to>currVideoPlayLengthMax){
        			 customRecodLastTimeState = 1;//避免重复调用实际学习时间统计方法recodLastTimeCallbackCustom()
        			 var player = getSWF( playerObjectId );
        			 player.seek(from)
        			 return false;
        		 }else{
        			 /*** 避免影响脚本功能,调用引用页面自定义方法.yxt ***/
        			 if(typeof(recodLastTimeCallbackCustom)=="function"){ 
        				 if(customRecodLastTimeState==0){
        					 recodLastTimeCallbackCustom(from,to);
        				 }else if(customRecodLastTimeState==1){
        					 customRecodLastTimeState = 0;
        				 }
        			 }
        			 /*** 避免影响脚本功能,调用引用页面自定义方法.yxt ***/
        		 }
        	 }
    	 }
     }
     
	function getSWF( swfID ) {
	      if (window.document[ swfID ]) {
		return window.document[ swfID ];
	    } else if (navigator.appName.indexOf("Microsoft") == -1) {
			if (document.embeds && document.embeds[ swfID ]) {
		return document.embeds[ swfID ];
	      }
	    } else {
		return document.getElementById( swfID );
	    }
	  }
	
	/*****方法结束****/
	
	//jquery  ajax 封装  
	function doAjax(url,data,callBack){  
	    $.ajax({   
	        type:"POST",  
	        url: url,  
	        data: data+"&iddd="+Math.random(),  
	        dataType: "html",  
	        success: function(data){   
	            callBack(data);   
	        }  
	    });  
	}  
	  
	function doPostAjax(url,callBack){  
	    $.post(url, function(data) {  
	        callBack(data);   
	    });  
	}  
	  
	function doJsonAjax(url,data,callBack,async){  
	    $.ajax({   
	        type:"POST",  
	        url: url,  
	        data: data+"&iddd="+Math.random(),  
	        dataType: "json",
	        async:async,
	        success: function(data){  
	            callBack(data);   
	        },  
	        error: function (data){  
	              
	        }  
	    });  
	}  
	  	