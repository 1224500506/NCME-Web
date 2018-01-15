<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <%@include file="/commons/taglibs.jsp"%>
        <style>
            html,body{
                height:100%;
                width:100%;
                padding:0;
                margin:0;
            }
            #preview{
                width:100%;
                height:100%;
                padding:0;
                margin:0;
            }
            #preview *{font-family:sans-serif;font-size:16px;}
        </style>
        <link rel="stylesheet" type="text/css" href="${ctx}/play/css/video.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/play/css/core.css">
        <link rel="stylesheet" type="text/css" href="${ctx}/play/css/pt_pages_course_courseLearn.css">
        <script type="text/javascript" src="${ctx}/play/js/internal.js"></script>
        <script type="text/javascript" src="${ctx}/play/js/ueditor.parse.js"></script>
        <script type="text/javascript" src="${ctx}/play/js/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="${ctx}/play/js/swfobject.js"></script>
        <title></title>
    </head>
    <body class="view">
        <div id="preview" style="margin:8px;width:80%;height:650px;">
            <p><span style="color:#c0504d;display:none;" class="template_video">
            </span><p class="display:none" class="template_video"></p><input type="hidden" class="template" value="video">
            <span style="margin-left:50px;font-size:18px;">医学基础导论课程--测试单元</span><br/><br/>
            <span style="margin-left:5px;font-size:13px;margin-top:50px;">类型：视频课程 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;授课教师：李明 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;达标要求：标准 </span><br/><br/>
            <img width="80%" height="500px" _url="51071753DF02D8F29C33DC5901307461" src="${ctxPeixunURL}/udeitor/themes/default/images/spacer.gif" style="background:url(${ctxPeixunURL}/udeitor/themes/default/images/videologo.gif) no-repeat center center; border:1px solid gray;" onclick="play(this);"/></p>
        </div>
        <div id="course-toolbar-box" class="g-sd2"> 
   <div class="m-ctb"> 
    <div class="courseintro"> 
     <div class="f-fl courseTitle j-courseintro"> 
      <div class="u-coursetitle f-fl"> 
       <a href="#" target="_blank" class="j-info f-thide">年龄相关性白内障</a> 
       <div class="f-cb"> 
        <span class="j-info starall"> 
         <div class="u-rating"> 
          <div class="star on"></div> 
          <div class="star on"></div> 
          <div class="star on"></div> 
          <div class="star on"></div> 
          <div class="star on"></div> 
          <span class="j-tip"></span> 
         </div></span> 
        <span class="cmt j-cmt">(18份评价)</span> 
       </div> 
       <p class="j-info f-thide">发布者：北京协和医院</p> 
      </div> 
     </div> 
     <a class="j-courseimg-link courseImgCover" target="_blank" href="#"> 
      <div class="courseImg-link"></div> <img src="${ctx}/play/img/tu12.png" class="f-fr courseImg j-courseimg" /> </a> 
    </div> 
    <div class="tabs j-tab-tabwrap"> 
     <a class="tabitem j-tab-tabitem tab-forum tabitem-pos" id="auto-id-1476253936321"> <span class="f-fl tabicon tabicon-pos0"></span> <span class="f-fl">讨论</span> </a> 
     <a class="tabitem j-tab-tabitem tab-talk" style="display:none;" id="auto-id-1476253936323"> <span class="f-fl tabicon tabicon-1"></span> <span class="f-fl">说说</span> </a> 
     <a class="tabitem j-tab-tabitem" id="auto-id-1476253936325"> <span class="f-fl tabicon tabicon-2"></span> <span class="f-fl">目录</span> </a> 
     <a class="tabitem j-tab-tabitem" id="auto-id-1476253936327"> <span class="f-fl tabicon tabicon-3"></span> <span class="f-fl">笔记</span> </a> 
     <a class="tabitem j-tab-tabitem" style="display:none;" id="auto-id-1476253936329"> <span class="f-fl tabicon tabicon-4"></span> <span class="f-fl">问答</span> </a> 
    </div> 
    <div id="toolbar-box" class="tabarea j-tabarea"> 
     <div class="tabbox j-tabbox" id="forum-tabbox" style="display: block;"> 
      <div class="m-toolbarForum"> 
       <div class="edit f-cb"> 
        <div class="j-forumSel forumSel"> 
         <div class="u-select"> 
          <div class="up j-up f-thide" id="auto-id-1476253936378">
            综合讨论区 
          </div> 
          <div class="down f-bg j-list" style="display: none;"> 
           <div class="f-thide list" title="老师答疑区" id="auto-id-1476253936374">
             老师答疑区 
           </div> 
           <div class="f-thide list" title="综合讨论区" id="auto-id-1476253936376">
             综合讨论区 
           </div> 
          </div> 
         </div> 
        </div> 
        <div class="j-ipt"> 
         <div class="u-cmtwrp"> 
          <div class="u-cmtedtip2"> 
           <div class="f-fs0 s-fc2 j-ic f-fl" style="display:none"> 
            <div>
              还可以输入 
             <b class="s-fc1">100</b>字 
            </div> 
           </div> 
          </div> 
          <div class="u-cmtedit f-cb" style="z-index:102;"> 
           <div class="wrap f-cb"> 
            <textarea name="notetxt" class="j-ic mtxt" id="auto-id-1476253936341"></textarea> 
           </div> 
           <label class="j-ic hint" for="notetxt" id="auto-id-1476253936345" onfocus="aa();">欢迎分享，鼓励原创，无论是你的学习体验、想法、工作生活经验，还是好书、好文章、好资源，都可发在这里。</label> 
          </div> 
         </div> 
        </div> 
        <a class="j-post postBtn f-fr" id="auto-id-1476253936300"> <span>发起讨论</span> </a> 
       </div> 
      </div> 
     </div> 
     <div class="tabbox j-tabbox" id="talk-tabbox"> 
     </div> 
    </div> 
   </div> 
</div> 
    <div id="swfDiv" style="display:none;margin-left:12px;width:80%;height:500px;margin-top:-520px;"></div> 
</body>
    
    <script type="text/javascript">
    
        $('#preview').css('color','#FFFFFF');
    
       // document.getElementById('preview').innerHTML = editor.getContent();
      
        var swfobj=new SWFObject('http://union.bokecc.com/flash/player.swf?userid=078E396B1332FD8E&videoid=034282A1F1B06B299C33DC5901307461', 'playerswf', '80%', '500', '8');
    	swfobj.addVariable( "userid" , "078E396B1332FD8E");	//	partnerID,用户id
    	swfobj.addVariable( "videoid" , "034282A1F1B06B299C33DC5901307461");	//	spark_videoid,视频所拥有的 api id
    	swfobj.addVariable( "mode" , "api");	//	mode, 注意：必须填写，否则无法播放
    	swfobj.addVariable( "autostart" , "true");	//	开始自动播放，true/false
    	swfobj.addVariable( "jscontrol" , "true");	//	开启js控制播放器，true/false
    	swfobj.addParam('allowFullscreen','true');
    	swfobj.addParam('allowScriptAccess','always');
    	swfobj.addParam('wmode','transparent');
    	swfobj.write('swfDiv');
    	
    	var gobalObj;
        
        //点击播放
        function play(obj){
           $("#swfDiv").css("display","block");
           $(obj).css("display","none");
           gobalObj = obj;
           document.getElementById("playerswf").spark_player_start();
        }
        
        //播放完毕
        function on_spark_player_stop() {
        	 $("#swfDiv").css("display","none");
             $(gobalObj).css("display","block");
    	}
        
        //暂停播放
        function on_spark_player_pause() {
        	$("#swfDiv").css("display","none");
            $(gobalObj).css("display","block");
    	}
    </script>
</html>