<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=0,maximum-scale=1.0,user-scalable=yes" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="robots" content="index,follow">
    <meta name="apple-mobile-web-app-title" content="中国继续医学教育网">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="keyword" content="中国继续医学教育网，NCME，CME，继续医学教育，公需科目，基层学院，胜任力，名师，专委会，指南，病例，慕课，直播，VR，三维动画">
    <meta name="description" content="中国继续医学教育网是国家卫生计生委重点督办项目，包含了公需科目，基层学院，胜任力，名师，专委会，指南，病例，慕课，直播，VR，三维动画等栏目">
    <title>中国继续医学教育网_NCME</title>
    <#--<%@include file="/commons/taglibs.jsp"%>-->
    
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/map.js"></script>
    <script type="text/javascript">
        var ctxJS = "${ctx}";
        var basepath ='${ctx}';
        $(function () {
            <!-- 检测用户是否登录-->
            $.ajax({
                //接口地址
                url:'${ctx}/loginAJAX.do?loginCheck=afasdlkfjadls&time=' + new Date().getMilliseconds(),
                //请求方式
                type:'post',
                //返回数据类型
                dataType:'json',
                async:false,
                //请求失败时处理方式
                error:function(){
                	window.localStorage.setItem("isLogin","false");
                },
                //请求成功时处理方式
                success:function(result){
                    //console.log(JSON.stringify(result))
                    if(result.message == 'loginHasLogin'){
                        window.localStorage.setItem("isLogin","true");
                        window.localStorage.setItem("userimg",result.userimge); //用户登录后保存其头像
     		    		window.localStorage.setItem("usersex",result.usersex);  //用户性别
                    }else{
                        window.localStorage.setItem("isLogin","false");
                        window.localStorage.setItem("usersex",result.usersex); 
                    }
                },
            });
            //首先清除下历史
            var storage = window.localStorage.getItem("isLogin");
            var remember_pwd=getCookie("remember_pwd")
            if(storage=="false"&&!remember_pwd=="true"){
                clearCookie();
            }
          
            // 静态页面中优质慕课读取课程数据。
            var map = new Map();
            $.ajax({
                //接口地址
                url:'${ctx}/BasicSubject.do?mode=myMooke&time=' + new Date().getMilliseconds(),
                //请求方式
                type:'post',
                //返回数据类型
                dataType:'json',
                async:false,
                //请求成功时处理方式
                success:function(result){
                    console.log(JSON.stringify(result))
                    console.log(JSON.stringify(result.map))
                    map =result.map;
                    for(var key in map){
                        console.log("key："+key+",value："+map[key]);
                        //修改慕课学习课程
                        $("div#mooke .spanid").each(function(){
                            if(key=$(this).attr ("id")) {
                                $(this).html(map[key]+"次学习")
                            }
                        });
                    }
                },
            });
	    });

        String.prototype.getBytes = function() {
            var cArr = this.match(/[^\x00-\xff]/ig);
            return this.length + (cArr == null ? 0 : cArr.length);
        };
        function getCookie(name)
        {
            var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
            if(arr=document.cookie.match(reg))
                return unescape(arr[2]);
            else
                return null;
        }
        function clearCookie(){
            var keys=document.cookie.match(/[^ =;]+(?=\=)/g);
            if (keys) {
                for (var i = keys.length; i--;)
                    document.cookie=keys[i]+'=0;expires=' + new Date( 0).toUTCString()
            }
        }
    </script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <script src="${ctx}/qiantai_page/js/yzm.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">

    <link rel="shortcut icon" href="/favicon.ico"/>
    <script src="${ctx}/qiantai_page/js/drag.js"></script>
    <link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <style type="text/css">

        .paybox{
            padding: 20px;
            border-radius: 10px;
            background-color: #fff;
            position: fixed;
            top: 50vh;
            left: 50%;
            z-index: 999999;
            width: 500px;
            margin: -200px 0 0 -270px;
            display: none;


        }


        .paybox .joinform #paysubmit{
            border: 0 none;
            padding: 5px 10px;
            display: inline-block;
            box-sizing: border-box;
            font-size: 16px;
            font-weight: 600;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            margin-right: 20px;
            border-radius: 6px;
            background-color: #f90;
            color: #fff;

        }


        .paybox .joinform #closebox2{
            border: 0 none;
            padding: 5px 10px;
            display: inline-block;
            box-sizing: border-box;
            font-size: 16px;
            font-weight: 600;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            margin-right: 20px;
            border-radius: 6px;
            border:1px solid #ccc;
            background-color: #fff;
            color: #615858;

        }


        .paybox .joinform{
            margin: 20px auto;
            width: 80%;
        }

        .paybox h2{
            font-size: 18px;
            border-bottom: 1px solid #12bce1;
            margin-top: 0;
        }



        .joinform .input_div input[type="text"],input[type='password']{
            padding: 5px;
            background: #fff;
            color: #333;
            box-sizing: border-box;
            width: 100%;
            border: 1px solid #666;
            font-size: 14px;
            border-radius: 7px;
        }


        #labcard{
            font-weight: bold;
        }

        #labpwd{
            font-weight: bold;
        }

        .bgc{
            width: 100%;
            height: 100%;
            background-color: rgba(51, 51, 51, 0.5);
            position:absolute;
            top: 0;
            left: 0;
            z-index: 100;
            display: none;

        }

        #yzmCode {
            font-family: Arial, 宋体;
            font-style: italic;
            color: green;
            border: 0;
            padding: 2px 3px;
            letter-spacing: 3px;
            font-weight: bolder;
        }
    </style>

</head>
<body onload='createCode()'>
<div class="bgc"></div>
<form id="fm1" name="fm1" method="post" action="${ctx}/first.do">
	
    <div class="container">
        <link href="${ctx}/qiantai_page/css/iconfont.css" rel="stylesheet">
        <style type="text/css">
            .tank{
                position: fixed;
                bottom: 10px;
                right: 10px;
                z-index:999999;

            }
            #udeskIm,#udeskCall,#erweima2{
                display: block;
            }
           
            .callnum{
                display: none;
                position: absolute;
                right: 95px;
                top: 118px;
                width: 185px;
                padding: 5px 8px;
                text-align: center;
                border-radius: 5px;
                color: rgb(255, 255, 255);
                background-color: rgb(91, 177, 255);
            }
            .callnum a{
                color: #ffffff;
                font-size: 26px;
                display: inline-block;
                text-decoration: none;
                float:right;
                cursor:pointer;
            }
            .callnum img{
                vertical-align: middle;
            } 
            .callnum strong{
            cursor:pointer;
            }
            
            .erweima{
                display: none;
                position: absolute;
                right: 95px;
                top: 118px;
                width: 185px;
                padding: 5px 8px;
                text-align: center;
                border-radius: 5px;
                color: rgb(255, 255, 255);
                background-color: rgb(91, 177, 255);
            }
            .erweima a{
                color: #ffffff;
                font-size: 26px;
                display: inline-block;
                text-decoration: none;
                float:right;
                cursor:pointer;
            }
            .erweima img{
                vertical-align: middle;
            } 
            .erweima strong{
            cursor:pointer;
            }
            
        </style>
        <!-- 头部 -->
        <div class="header index_top">
            <div class="top">
                <div class="top_cont">
                    <div class="left_nav">
                        <a href="${ctx}/first.do">首页</a>
                        <a href="javascript:void(0)" onclick="projectList_sign('公需科目')">公需科目</a>
                        <a href="javascript:void(0)" onclick="projectList_xueke('全科医学')">基层学院</a>
                        <a href="${ctx}/Ability.do">胜任力</a>
                        <a href="${ctx}/teacherManage/teacherManage.do">名师</a>
                        <a href="${ctx}/OrgManage/OrgManage.do">机构</a>
                        <a href="${ctx}/ExpertGroup.do">专委会</a>
                        <a href="${ctx}/qiantai_page/edu/edu_man.jsp">继教管理</a>
                        <a href="${ctx}/qiantai_page/CertificatQeuery/Certif_query.jsp">证书查询</a>
                        <a href="${ctx}/courseManage/courseList.do">学科导航</a>
                        <a href="${ctx}/haiWaiShiYe.do">海外视野</a>
                        <a href="${ctx}/specialAbility.html">专项能力</a>
                    </div>

                    <div class="right_nav">

                        <#--<#if  username??>-->
                            <#--<script>-->
                                <#--window.localStorage.setItem("isLogin","true");-->
                            <#--</script>-->
                        <#--<#else>-->
                            <#--<script>-->
                                <#--window.localStorage.setItem("isLogin","false");-->
                            <#--</script>-->
                            <#--<a href="${ctx}/login.do" class="login_btn">登录</a>-->
                            <#--<a href="${ctx}/registerUser.do">注册</a>-->
                            <#--<a href="${ctx}/help.jsp">帮助</a>-->
                        <#--</#if>-->
                            <a href="${ctx}/login.do" class="login_btn">登录</a>
                            <a href="${ctx}/registerUser.do">注册</a>
                            <a href="${ctx}/help.jsp">帮助</a>
                    </div>
                </div>
            </div>
            <div class="head_cont">
                <h1 class="logo"><a href="${ctx}/first.do" title="中国继续医学教育网_NCME">中国继续医学教育网_NCME</a></h1>
                <div class="menu_bar subject_menu">
                    <ul>
                        <li><a href="javascript:void(0)" onclick="projectList_xueke('全科医学')">全科医学</a></li>
                        <li><a href="javascript:void(0)" onclick="projectList_xueke('妇产科学')">妇产科学</a></li>
                        <li><a href="javascript:void(0)" onclick="projectList_xueke('儿科学')">儿科学</a></li>
                        <li><a href="javascript:void(0)" onclick="projectList_xueke('精神卫生学')">精神卫生学</a></li>
                        <li><a href="javascript:void(0)" onclick="projectList_xueke('康复医学')">康复医学</a></li>
                        <li><a href="${ctx}/ProjectList.do">更多</a> <i class="fa fa-ellipsis-h"></i></li>
                    </ul>
                    <div class="search_form" >
                        <input type="text" name="search_input" id="search_input"  value="" placeholder="请输入搜索关键词" >
                        <button type="button" id="search_button"><i class="icon iconfont">&#xe604;</i></button>
                    </div>
                </div>
            </div>
        </div>
        
     <div class="suspension">
        <div class="suspension-wrap">
            <a href="javascript:;" class="service" id="udeskIm" title="在线客服"><span>在线客服</span></a>
            <a href="javascript:;" class="telephone" id="udeskCall" title="电话咨询"><span>电话咨询</span></a>
            <a href="javascript:;" class="Wechat" id="erweima2" title="微信二维码"><span class="code">微信</br>二维码</span></a>
            <a href="javascript:;" class="feedback" id="feedback" title="意见反馈"><span>意见反馈</span></a>
        </div>
        <div class="callnum">
                <img src="${ctx}/qiantai_page/img/telcall.png" alt="Udesk拨打电话图标">
                <span>400-863-1000</span>
                <strong>×</strong>
            </div>
            
            <div class="erweima">
            <img src="${ctx}/qiantai_page/img/cert_search_QR.png">
                <strong>×</strong>
                </div>
    </div>
        
        
        
        <script>
            var url = $("#url").val();

            function viewSubject(propId) {
                window.location.href = "${ctx}/ProjectList.do?xueke=" + propId;
            }
            function searchProject(mode) {
                if (mode == 1) {
                    window.location.href = "${ctx}/ProjectList.do?sign="
                            + encodeURI("指南解读");
                }
                if (mode == 2) {
                    window.location.href = "${ctx}/caseList.do";
                }
                if (mode == 3) {
                    window.location.href = "${ctx}/liveList.do";
                }
                if (mode == 4) {
                    window.location.href = "${ctx}/vrList.do";
                }
            }
            function postView(mode, id) {
                var e = "${ctx}/postView.do?mode=" + mode + "&id=" + id;
                var c = 800;
                var d = 1100;
                window
                        .open(
                                e,
                                "",
                                "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="
                                + d + ",height=" + c);
            }
            function viewList(mode) {
                var e = "${ctx}/postList.do?mode=" + mode;
                var c = 900;
                var d = 1300;
                window
                        .open(
                                e,
                                "",
                                "top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="
                                + d + ",height=" + c);
            }
            function gotoDetail(id) {
                location.href = "${ctx}/entityManage/entityView.do?id=" + id;
            }
			
            (function (a, h, c, b, f, g) {
                a["UdeskApiObject"] = f;
                a[f] = a[f] || function () {
                    (a[f].d = a[f].d || []).push(arguments)
                };
                g = h.createElement(c);
                g.async = 1;
                g.src = b;
                c = h.getElementsByTagName(c)[0];
                c.parentNode.insertBefore(g, c)
            })(window, document, "script", "http://ncme.udesk.cn/im_client/js/udeskApi.js?1483061109688", "ud");
            ud({
                "code": "1a9dg2j6",
                "link": "http://ncme.udesk.cn/im_client?web_plugin_id=23375",
                "targetSelector": "#udeskIm"
            });

            $(function(){
                $('#udeskCall').click(function(){
                    $('.callnum').css('display','block');
                })
                $('.callnum strong').click(function(){
                    $(this).parent().css('display','none');
                })
                //<a href="${ctx}/qiantai_page/aFeedback/feedback.jsp">
                $('#feedback').click(function(){
                    window.location.href = "${ctx}/qiantai_page/aFeedback/feedback.jsp";
                })
            })
             $(function(){
                $('#erweima2').click(function(){
                    $('.erweima').css('display','block');
                })
                $('.erweima strong').click(function(){
                    $(this).parent().css('display','none');
                })
                
            })
            
        </script>
        <script>
        function projectList_sign(str) {
                window.location.href = "${ctx}/ProjectList.do?sign=" + encodeURI(encodeURI(str));
            }
            function projectList_sign(str) {
                window.location.href = "${ctx}/ProjectList.do?sign=" + encodeURI(encodeURI(str));
            }
            function projectList_xueke(str) {
                window.location.href = "${ctx}/ProjectList.do?xueke=" + encodeURI(str);
            }
            $("#search_button").click(function(){
                window.location.href="${ctx}/searchAction.do?search="+encodeURI(encodeURI($("#search_input").val()));
            })
        </script>
        <#--<slider>-->
        <div class="slider">
   	     	<ul class="bxslider">
       	  		<#list advertList as advert>
                        <li style="background:url() no-repeat center;background-size:100% auto">
	            			
	            				<a style="no-repeat center;background-size:100% auto" href="${ctx}/${advert.target_url}" title="${advert.name}" value="${advert.id?c}"><img src="${advert.url}"/></a>	
	           
	            		</li>
                </#list>
        	</ul>
        </div>
        <input type="hidden" id="dcvid" value="" />
        <input type="hidden" id="zbcvid" value="" />
        <input type="hidden" id="zbcvsetid" value="" />
        <input type="hidden" id="costType" value="" />
        <#--<!-- 直播加入时卡项目入口页 &ndash;&gt;-->
        <div class="paybox" >
            <h2><span class="close" id="closebox" style="float: right;" ><i class="fa fa-times" style="color: rgb(204,204,204);"></i></span> 添加学习卡 </h2>
            <div class="joinform">
                <div class="input_div">
                    <label id="labcard">输入学习卡卡号<input type="text" id="cardnumber" maxlength="20"></label>
                    <label id="labpwd">输入学习卡密码<input type="password" id="cardpwd" maxlength="20"></label>
                    <br/>
                    <input type="button" value="提交" id="paysubmit"/>
                    <input type="button" value="取消" id="closebox2"/>
                </div>
            </div>
        </div>
        <div class="content">
            <ul class="icons">
                <li onclick="javascript:searchProject(1);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-guide-2"></use>
                    </svg>

                </span>
                    <h3>指南</h3>
                </li>
                <li onclick="javascript:searchProject(2);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-bingli"></use>
                    </svg>
                </span>
                    <h3>病例</h3>
                </li>
                <li onclick="javascript:viewSubject('');">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-mooc"></use>
                    </svg>
                </span>
                    <h3>慕课</h3>
                </li>
                <li onclick="javascript:searchProject(3);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-video"></use>
                    </svg>
                </span>
                    <h3>直播</h3>
                </li>
                <li onclick="javascript:searchProject(4);">
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-vr-1"></use>
                    </svg>
                </span>
                    <h3>VR/三维动画</h3>
                </li>
            </ul>
        </div>
        <div id="mooke" class="content content_all has_bg_1 clearfix">
            <h1 class="main_title"><a href="javascript:viewSubject('');" class="more">更多 <i
                    class="fa fa-angle-right"></i></a>优质慕课</h1>
            <ul class="item_list item_img_list">
                    <#list mukeList as muke>
                    <li onclick="javascript:gotoDetail(${muke.id?c});">
                        <span class="item_cover"
                              style="background:url('${muke.file_path}') no-repeat center;background-size:cover"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <div class="info">
                                <span>项目负责人：${muke.managerList[0].name}</span>
                            </div>
                            <p class="desc topvebanner-p">${muke.introduction}</p>
                            <p class="aa">
                                <#if muke.level==1 >国家I类${muke.score}分</#if>
                                <#if muke.level==2 >省级I类${muke.score}分</#if>
                                <#if muke.level==3 >市级I类${muke.score}分</#if>
                                <#if muke.level==4 >省级II类${muke.score}分</#if>
                                <#if muke.level==5 >市级II类${muke.score}分</#if>
                                <#if muke.level==6 >${muke.score}分</#if>
                            </p>
                            <h3 class="foot no_border">
                                <#if muke.cost_type == 0>
                                    <span class="font_green" style="float:left;">免费</span>
                                <#elseif muke.cost_type == 1>
                                    <span class="font_green" style="float:left;">学习卡项目</span>
                                <#elseif muke.cost_type == 2>
                                    <span class="font_red" style="float:left;">￥${muke.cost}</span>
                                <#else>
                                    <span class="font_red" style="float:left;">
                                             ￥${muke.cost}
                                        </span>
                                </#if>
                                <#--${muke.studentNum}次学习-->
                             <span class="spanid" id="${muke.id?c}"></span>
                            </h3>
                        </div>
                    </li>
                </#list>
            </ul>
        </div>
        <div class="content clearfix">
            <h1 class="main_title"><a href="${ctx}/starCourseList.do" class="more">更多 <i class="fa fa-angle-right"></i></a>名师课程</h1>
            <ul class="item_list item_img_list">
                <#list mingshiList as muke>
                    <li onclick="javascript:gotoDetail2(${muke.id?c});" style="height:270px;">
                        <span class="item_cover"
                              style="background:url('${muke.file_path}') no-repeat top center;background-size:100% auto"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <p class="desc topvebanner-p"> ${muke.introduction}</p>
                            <p>授课教师：${muke.teacherList[0].name}</p>
                            <h3 class="foot no_border">
                                <#if muke.cost_type == 0>
                                    <span class="font_green" style="float:left;">免费</span>
                                <#elseif muke.cost_type == 1>
                                    <span class="font_green" style="float:left;">学习卡项目</span>
                                <#elseif muke.cost_type == 2>
                                    <span class="font_red" style="float:left;">￥${muke.cost}</span>
                                <#else>
                                    <span class="font_red" style="float:left;">
                                             ￥${muke.cost}
                                        </span>
                                </#if>
                            </h3>
                        </div>
                    </li>
                </#list>
            </ul>
        </div>
        <div class="content content_all has_bg_1 clearfix">
            <h1 class="main_title"><a href="${ctx}/caseList.do" class="more">更多 <i class="fa fa-angle-right"></i></a>典型病例</h1>
            <ul class="item_list item_img_list">
                <#list bingliList as muke>
                    <li onclick="javascript:gotoDetail2(${muke.id?c});" style="height:265px;">
                        <span class="item_cover"
                              style="background:url('${muke.file_path}') no-repeat center;background-size:cover"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <p class="desc topvebanner-p">${muke.introduction}</p>
                            <p>授课教师：${muke.teacherList[0].name}</p>
                            <h3 class="foot no_border">
                                <#if muke.cost_type == 0>
                                    <span class="font_green" style="float:left;">免费</span>
                                <#elseif muke.cost_type == 1>
                                    <span class="font_green" style="float:left;">学习卡项目</span>
                                <#elseif muke.cost_type == 2>
                                    <span class="font_red" style="float:left;">￥${muke.cost}</span>
                                <#else>
                                    <span class="font_red" style="float:left;">
                                             ￥${muke.cost}
                                        </span>
                                </#if>
                            </h3>
                        </div>
                    </li>
                </#list>
            </ul>
        </div>
        <div class="content clearfix" style="display:none">
            <h1 class="main_title"><a href="${ctx}/liveList.do" class="more">更多 <i class="fa fa-angle-right"></i></a>精彩直播
            </h1>
            <ul class="item_list item_img_list" id="timerFreshUL">
                <#list zhiboList as muke>
                    <li onclick = "javascript:gotoLiveView('${muke.id?c}','${muke.type}','${muke.cost_type}','${muke.startDate}','${muke.endDate}');">
		            <span class="item_cover"
                          style="background:url('${muke.file_path}') no-repeat center;background-size:cover">
                        <div class="item_title">
                        	<input type="hidden" id="cvId${muke_index}" value="${muke.id?c}" />
                    		<span class="ribbon soon" id="soon${muke_index}">...</span>
                    		<input type="hidden" id="timerCvType${muke.id?c}" value="${muke.type}" />
                           
                        	<span>${muke.startDate?string('MM月dd日')}      ${muke.startDate?string('HH:mm')}</span>
                            <span style="color:#fff;">-</span>
                            <span>${muke.endDate?string('MM月dd日')}      ${muke.endDate?string('HH:mm')}</span>
                        </div>
                        <i class="fa fa-play-circle-o"></i>
		            </span>
                        <div class="item_cont">
                            <h2 class="title" title="${muke.name}">${muke.name}</h2>
                            <p class="desc topvebanner-p">${muke.introduction}</p>
                            <div class="foot no_border">
                                <#if muke.cost_type == 0>
                                    <span class="font_green" style="float:left;">免费</span>
                                <#elseif muke.cost_type == 1>
                                    <span class="font_green" style="float:left;">学习卡项目</span>
                                <#elseif muke.cost_type == 2>
                                    <span class="font_red" style="float:left;">￥${muke.cost}</span>
                                <#else>
                                    <span class="font_red" style="float:left;">
                                             ￥${muke.cost}
                                        </span>
                                </#if>
                                <span style="line-height:35px;">授课教师：${muke.teacherList[0].name}</span>
                                <!-- <span style="line-height:35px;">人数：0/1000</span> -->
                            </div>
                            <div class="foot" style="padding:8px 0 0 0;">
                                <span style="display:block;width:100%;text-align:center;">${muke.teacherList[0].workUnit}</span>
                            </div>
                        </div>
                    </li>
                </#list>
            </ul>
        </div>
        
        <div class="content clearfix">
            <h1 class="main_title"><a href="javascript:searchProject(1);" class="more">更多 <i
                    class="fa fa-angle-right"></i></a>指南解读</h1>
            <ul class="item_list item_img_list">
                 <#list zhinanList as zhinan>
                    <li onclick="javascript:gotoDetail(${zhinan.id});">
                        <span class="item_cover"
                              style="background:url('${zhinan.file_path}') no-repeat center;background-size:cover"></span>
                        <div class="item_cont">
                            <h2 class="title" title="${zhinan.name}">${zhinan.name}</h2>
                            <div class="info">
                                <span>项目负责人：${zhinan.managerList[0].name}</span>
                            </div>
                            <p class="desc topvebanner-p">${zhinan.introduction}</p>
                            <p class="aa">
                                <#if zhinan.level==1 >国家I类${zhinan.score}分</#if>
                                <#if zhinan.level==2 >省级I类${zhinan.score}分</#if>
                                <#if zhinan.level==3 >市级I类${zhinan.score}分</#if>
                                <#if zhinan.level==4 >省级II类${zhinan.score}分</#if>
                                <#if zhinan.level==5 >市级II类${zhinan.score}分</#if>
                                <#if zhinan.level==6 >${zhinan.score}分</#if>
                            </p>

                            <h3 class="foot no_border">
                                <#if zhinan.cost_type == 0>
                                    <span class="font_green" style="float:left;">免费</span>
                                <#elseif zhinan.cost_type == 1>
                                    <span class="font_green" style="float:left;">学习卡项目</span>
                                <#elseif zhinan.cost_type == 2>
                                    <span class="font_red" style="float:left;">￥${zhinan.cost}</span>
                                <#else>
                                    <span class="font_red" style="float:left;">
                                             ￥${zhinan.cost}
                                        </span>
                                </#if>
                            </h3>
                        </div>
                    </li>
                 </#list>
            </ul>
        </div>
        
        
       
        
        <div class="content">
            <div class="left_cont" style="min-height:300px;">
                <h1 class="main_title"><a href="javascript:viewList(1);" class="more">更多 <i
                        class="fa fa-angle-right"></i></a><i class="fa fa-bullhorn"></i> 资讯公告</h1>
                <ul class="news_list" style="border-top: 1px solid #ccc;">
                    <#list contentList as sentence>
                        <li><span>${sentence.deli_date?string('yyyy-MM-dd')}</span>
                            <i class="fa fa-angle-right"></i>
                                <a href="javascript:postView(1,${sentence.id});"  title="${sentence.title}">
                            <#if sentence.title?length gt 28>
                                 ${sentence.title?substring(0,28)}...
                            <#else>
                                 ${sentence.title}
                            </#if>
                        </a></li>
                    </#list>
                </ul>
            </div>
            <div class="right_cont" style="min-height:300px;">
                <h1 class="main_title"><a href="javascript:viewList(2);" class="more">更多 <i class="fa fa-angle-right"></i> </a><i class="fa fa-book"></i> 政策法规</h1>
                <ul class="news_list" style="border-top: 1px solid #ccc;">
                        <#list sentenceList as sentence>
                        <li><span>${sentence.deli_date?string('yyyy-MM-dd')}</span>
                            <i class="fa fa-angle-right"></i>
                            <a href="javascript:postView(2,${sentence.id});" title="${sentence.title}">
                                <#if sentence.title?length gt 28>
                                    ${sentence.title?substring(0,28)}...
                                <#else>
                                   ${sentence.title}
                                </#if>
                        </a></li>
                    </#list>
                </ul>
            </div>
        </div>
        <div class="content content_all has_bg_1">
            <h1 class="center_title">学习流程</h1>
            <ul class="icons icons_bg flow">
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-login"></use>
                    </svg>
                </span>
                    <h3>登录</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-signup"></use>
                    </svg>
                </span>
                    <h3>选课</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-learn"></use>
                    </svg>
                </span>
                    <h3>学习</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-application"></use>
                    </svg>
                </span>
                    <h3>申请</h3>
                </li>
                <li><i class="fa fa-long-arrow-right"></i></li>
                <li>
                <span>
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-graduation"></use>
                    </svg>
                </span>
                    <h3>完成</h3>
                </li>
            </ul>
        </div>
            <!-- 尾部 -->
            <div class="footer">
                <div class="foot-top" style="margin:10px auto;">
                    <ul class="aside-ul-modify">
                        <li>
                            <a href="${ctx}/qiantai_page/down/about_us.jsp">关于我们</a>
                        </li>
                        <li>
                            <a href="${ctx}/qiantai_page/down/contact_us.jsp">联系我们</a>
                        </li>
                        <li>
                            <a href="${ctx}/qiantai_page/down/terms.jsp">服务条款</a>
                        </li>
                        <li>
                            <a href="${ctx}/qiantai_page/down/privacy.jsp">隐私声明</a>
                        </li>
                    </ul>
                </div>
                <a target="_blank" href="http://www.rkrc.cn/" class="foot_bottom" style="line-height:20px;width:650px;magin:10px auto;display:block;">
                    <img src="${ctx}/qiantai_page/img/first_logo.png" style="margin-top:5px;"/>
                </a>
                <div class="foot_bottom" style="padding:10px 0px 0px 0px;">
                    <div class="share" style="text-align:center">
                        版权所有 &copy; all rights reserved 京ICP备12023720号-1<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261128151'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1261128151%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
                    </div>
                </div>
                <div style="width:202px;margin:0 auto;">
                    <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11040202450051">
                        <img src="${ctx}/qiantai_page/img/beian.png" />
                    </a>
                </div>
            </div>
    </div>
</form>

<div class="popup">
    <form id="loginForm" name="loginForm" action = "${ctx}/loginAjax.do" method="post">
        <div class="popup">
            <div class="mask"></div>
            <div class="popup_cont clearfix login_form">
                <input type="hidden" id ="isLive" value="false"/>
                <h2><span class="close"><i class="fa fa-times"></i> </span> 用户登录</h2>
                <div class="join_form" id="popup_form">
                    <div class="input_div">
                        <label>用户名/手机/邮箱</label>
                        <input type="text" name="userData" id="userName"/>
                    </div>
                    <div class="input_div">
                        <label>密码</label>
                        <input type="password" name="password" id="password"/>
                    </div>
                    <div class="input_div">
                        <input type="text" id="yzmInput" style="width:50%" placeholder="验证码"/> <input type="button"
                                                                                                      id="yzmCode" onclick="createCode()" style="width:60px;margin-left:18%;height:40px;width:30%;font-size:30px"
                                                                                                      title='点击更换验证码' /> <input type="hidden" id ="yzm" />
                    </div>
                    <div class="input_div">
                        <button type="button" name="submit_btn" id="submit_btn" class="btn btn_orange btn_block float_left">确认登录</button>
                    </div>
                </div>
            </div>
    </form>
</div>
<script src="${ctx}/qiantai_page/js/hide.js"></script>
</body>
</html>

<script>
    //$('#drag').drag();
    var flag = false;
    var  flags = false;
    var url = $("#url").val();

    function viewSubject(propId) {
        window.location.href = "${ctx}/ProjectList.do?xueke=" + propId;
    }

    function searchProject(mode) {
        if (mode == 1) {
            window.location.href = "${ctx}/ProjectList.do?sign="
                    + encodeURI(encodeURI("指南解读"));
        }
        if (mode == 2) {
            window.location.href = "${ctx}/caseList.do";
        }
        if (mode == 3) {
            window.location.href = "${ctx}/liveList.do";
        }
        if (mode == 4) {
            window.location.href = "${ctx}/vrList.do";
        }
    }

    function postView(mode, id) {
        var e = "${ctx}/postView.do?mode=" + mode + "&id=" + id;
        var c = document.body.clientHeight;
        var d = document.body.clientWidth;
        window.location.href = e;
        //window.open(e,"","top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="+ d + ",height=" + c);
    }

    function viewList(mode) {
        var e = "${ctx}/postList.do?mode=" + mode;
        var c = document.body.clientHeight;
        var d = document.body.clientWidth;
        window.location.href = e;
        //window.open(e,"","top=0,left=0,alwaysRaised=yes,scrollbars=yes,resizable=yes,Toolbar=no,Location=no,width="+ d + ",height=" + c);
    }
   

    function gotoDetail(id) {
        location.href = "${ctx}/projectDetail.do?id=" + id;
    }

    function gotoDetail2(id) {
        $("#dcvid").val(id);
        //保存学习记录--项目
        $.ajax({
            type: 'POST',
            url: '${ctx}/study/logstudy.do?mode=queryLogin&id='+id,
            dataType: 'JSON',
            success : function(data){
                var result = eval(data);
                if(result.message == 'noLogin'){
                    //弹出登录窗口
                    $(".popup").show();
                }else{
                    location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class&isCv=isCv";
                };
            }
        });
    }
    function ifLogin(id) {
        //保存学习记录--项目
        $.ajax({
            type: 'POST',
            url: '${ctx}/study/logstudy.do?mode=queryLogin&id='+id,
            dataType: 'JSON',
            success : function(data){
                var result = eval(data);
                location.href = "${ctx}/XiangYiIndex.do?code="+result.code;
            }
        });
    }
    $(".close,button[name=submit]").click(function () {
        $(".popup").hide();
    });
    $("button[name=submit]").click(function () {
        window.localStorage.setItem("isLogin","true");
    });

    //$("#submit_btn").click(function(){
    /* if($('#userName').val() == ""){
        alert("请输入用户名/手机/邮箱！");
        return;
    }
    if($('#password').val() == ""){
        alert("请输入密码！");
        return;
    }
     if(!flag)
        {
            alert("请完成验证码验证！");
            return;
        }
    //通过AJAX实现登录功能
    $.ajax({
        type: 'POST',
        url:  '${ctx}/loginAJAX.do',
			data:{
				userData:$("#userName").val(),
				password:$("#password").val(),
				yzm:$("#yzm").val(),
			},
			dataType: 'JSON',
			success : function(data){
				var result = eval(data);
				if(result.message="success"){
					//刷新页面
					var id=$('#id').val();
					location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class";
				}
			}
        }); */





    //});



    $("#submit_btn").click(function(){
        if($('#userName').val() == ""){
            alert("请输入用户名/手机/邮箱！");
            return;
        }
        if($('#password').val() == ""){
            alert("请输入密码！");
            return;
        }
        if(!validate()){
            return;
        }
        /* if(!flag)
    {
        alert("请完成验证码验证！");
        return;
    }  */
        //$("#submit_btn").attr("disabled","disabled");
        //通过AJAX实现登录功能
        $.ajax({
            type: 'POST',
            url:  '${ctx}/loginAJAX.do',
            data:$("#loginForm").serialize(),
            dataType: 'JSON',
            success : function(data){
                var result = eval(data);
                if(result.message == "success"){
                    $("#submit_btn").removeAttr("disabled");
                    //刷新页面
                    var id=$("#dcvid").val();
                    var isLive = $("#isLive").val();
                    var costType = $("#costType").val()
                    if(isLive == "true"){//此处用于判断用户进入直播的情况
                        var zbcvid = $("#zbcvid").val();
                        var zhiboType = $("#timerCvType"+zbcvid).val();
                        if(zhiboType == 4){
                            checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
                            if(flags ==true){
                                window.location.href = ctxJS + "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="+zbcvid+"&unitId="+0;
                                return;
                            }
                        }
                        $.ajax({//根据课程信息拿到项目信息
                            type: 'POST',
                            url: '${ctx}/BasicSubject.do?mode=queryCVsetByCVId&cvId='+zbcvid,
                            dataType: 'JSON',
                            success : function(data){
                                var result = eval(data);
                                if(result.message=='success'){
                                    $("#zbcvsetid").val(result.cvSetId);
                                    checkbind(result.cvSetId,costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
                                    if(flags ==true){
                                        $("#isLive").val("false");
                                        window.location.href = "${ctx}/viewLiveListInterface.do?mode=getSignk&cvId="+zbcvid;
                                    }
                                }
                            },
                            error: function(e){
                                $(".popup").hide();
                            }
                        });
                    }else{
                        location.href = "${ctx}/entityManage/entityView.do?type=play2&id=" + id + "&paramType=class";
                    }
                }else{
                    if(result.message=="checkNumberNull"){
                        alert("验证码为空！");
                    }
                    else if(result.message=="checkNumberError"){
                        alert("验证码错误！");
                    }
                    else if(result.message=="userNull"){
                        alert("此用户不存在！");
                    }
                    else if(result.message=="passwordError"){
                        alert("密码错误！");
                    }
                    else if(result.message=="userNameNull"){
                        alert("用户名为空！");
                    }
                }
            },
            error: function(e){
                //alert("登录超时......");
            }
        });
    });




    function changeValidateCode(obj) {
        //获取当前的时间作为参数，无具体意义
        var timenow = new Date().getTime();
        //每次请求需要一个不同的参数，否则可能会返回同样的验证码
        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
        obj.src="${ctx}/login/GetRandomPictureAction.do?d="+timenow;
    }

	 
	
    $(function () {
        $('.bxslider').bxSlider({
            auto: true
        });
        //判断banner图的target_url是否存在
       $(".bxslider li a").click(function(){
       		if(this.getAttribute("href")=='${ctx}/'){
       			var id = this.getAttribute("value");
       			$(this).attr("href","${ctx}/bannerManage.do?method=list&id="+id);
       			 $(this).trigger("click");
       			//gotoAdvert(id);
       		}
       })
			    
            
        //生成静态学科导航页面
   /**     	
        	$.ajax({
                //接口地址
                url:'${ctx}/courseManage/courseList.do?logIndex=false',
                //请求方式
                type:'post',
                //返回数据类型
                dataType:'json',
                //请求成功时处理方式
                success:function(result){
                },
            }); **/
    });
	//banner图
	function gotoAdvert(obj) {
		alert("链接地址为空，跳转到内容详情页面"+id);
		alert(document.location.href = "${ctx}/contentManage/bannerManage?method=BannerList&id=" + id);
        //window.location.href = "${ctx}/contentManage/bannerManage?method=list&id=" + id;
    }

    //***************************************
    // 取消
    $("#closebox").click(function(){
        $(".paybox").hide();
        $(".bgc").hide();
    })

    // 取消按钮
    $("#closebox2").click(function(){
        $(".paybox").hide();
        $(".bgc").hide();
    })

    // 提交支付信息
    $("#paysubmit").click(function(){
        var cardnumberval = $("#cardnumber").val();
        var cardpwd = $("#cardpwd").val();
        var zbcvsetid = $("#zbcvsetid").val();
        var zbcvid = $("#zbcvid").val();
        if(!cardnumberval){
            alert("请输入卡号");
            return false;
        }
        if(!cardpwd){
            alert("请输入密码");
            return false;
        }

        $.ajax({
            type: 'POST',
            url: "${ctx}/study/logstudy.do?mode=playSubmit&cvsetId="+zbcvsetid+"&cardNumber="+cardnumberval+"&cardPassword="+cardpwd,
            dataType: 'JSON',
            success : function(data){
                var result = eval(data);
                if(result.message == 'success'){
                    //进入直播
                    var zbcvid = $("#zbcvid").val();
                    window.location.href = "${ctx}/viewLiveListInterface.do?mode=getSignk&cvId="+zbcvid;
                }else if(result.message == 'notbind'){
                    //已评价 YHQ 2017-02-14 1827
                    alert("卡状态不可用或类型错误");
                    return false;
                }else if(result.message == 'time'){
                    alert("您的学习卡已经过期");
                    return false;
                }else if(result.message == 'notfind'){
                    alert("输入的卡号或密码错误");
                    return false;
                }else if(result.message == 'typeno'){
                    alert("卡类型无效");
                    return false;
                }else if(result.message == 'alreadyBind'){//添加改卡已经绑过的情况处理--taoliang
                    alert("此卡已被用户使用！");
                    return false;
                }
            }
        });
    })

    //直播入口操作
    function gotoLiveView(id,zhiboType,costType,startDate,endDate) {
        /* if(zhiboType == 3){
            alert("课程未完成转码，敬请期待！");
            return;
        } */
        zhiboType = $("#timerCvType"+id).val();
        if(zhiboType == 2){
            alert("直播时间为"+startDate.split('.')[0]+" -- "+endDate.split('.')[0]+"，尚未开始！");
            return;
        }else if(zhiboType == 3){
            alert("已结束！");
            return;
        }else if(zhiboType == 4){
            $.ajax({
                type: 'POST',
                url:  ctxJS + '/study/logstudy.do?mode=queryLogin&id='+id,
                dataType: 'JSON',
                success : function(data){
                    var result = eval(data);
                    if(result.message == 'noLogin'){
                        $("#zbcvid").val(result.id);
                        $("#costType").val(costType);//初始化卡类型
                        $("#isLive").val("true");
                        $(".popup").show();
                    }else if(result.message=='success'){
                        $("#zbcvid").val(id);//初始化课程ID
                        checkbind(costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
                        if(flags ==true){
                            window.location.href = ctxJS + "/viewLiveListInterface.do?mode=viewLivePlayback&cvId="+id+"&unitId="+0;
                        }
                    };
                }
            });
            return;
        }else if(zhiboType == 5){
            alert("直播结束，视频转码中，敬请期待！");
            return;
        }
        else{
            $.ajax({
                type: 'POST',
                url: '${ctx}/study/logstudy.do?mode=queryLogin&id='+id,
                dataType: 'JSON',
                success : function(data){
                    var result = eval(data);
                    if(result.message == 'noLogin'){
                        $("#zbcvid").val(result.id);
                        $("#costType").val(costType);//初始化卡类型
                        //弹出登录窗口
                        $("#isLive").val("true");
                        $(".popup").show();
                    }else if(result.message=='success'){
                        $.ajax({//根据课程信息拿到项目信息
                            type: 'POST',
                            url: '${ctx}/BasicSubject.do?mode=queryCVsetByCVId&cvId='+id,
                            dataType: 'JSON',
                            success : function(data){
                                var result = eval(data);
                                if(result.message=='success'){
                                    $("#zbcvid").val(id);
                                    $("#zbcvsetid").val(result.cvSetId);
                                    checkbind(result.cvSetId,costType);//当用户登录后，需验证用户点击的直播课程项目是否为绑卡项目
                                    if(flags ==true){
                                        window.location.href = "${ctx}/viewLiveListInterface.do?mode=getSignk&cvId="+id;
                                    }
                                }
                            }
                        });
                    };
                }
            });
        }
    }

    //验证项目是否需要付费是否绑卡
    function checkbind(cvSetId,costType){
        /* if(costType == 1){
                $(".popup").hide();
             $(".bgc").show();
             $(".paybox").show();
             return false;
           } */
        $.ajax({
            url:"${ctx}/study/logstudy.do?mode=CheckBind&cvsetId="+cvSetId+"&costType="+costType,
            type: 'POST',
            async: false,
            dataType: 'json',
            success: function(data){
                var result = eval(data);
                if(result.card == '0'){
                    flags = true;
                }else{
                    $(".popup").hide();
                    $(".bgc").show();
                    $(".paybox").show();
                    return false;
                }
            }
        });

    }

    $(function () {
        setInterval("timerFun()",1000);
    });
    function timerFun(){
        $("#timerFreshUL li").each(function(index){
            var _this = $("#timerFreshUL li");
            var _cvId = $("#cvId"+index).val();
            //alert(_cvId);starttime=1499989933,endtime=1499990049
            $.ajax({
                type: 'POST',
                url: ctxJS + "/viewLiveListInterface.do?mode=getZBType&cvId="+_cvId,
                dataType: 'JSON',
                success : function(result){
                    //_this.find("#soon"+index).each(function(index){
                    //var soonVal = $(this).text();
                    //if(soonVal != null && soonVal != ""){
                    if(result.typeInt == '1'){
                        $("#timerCvType"+_cvId).val("1");
                        _this.find("#soon"+index).text("正在播放");
                    }
                    if(result.typeInt == '2'){
                        $("#timerCvType"+_cvId).val("2");
                        _this.find("#soon"+index).text("即将开课");
                    }
                    if(result.typeInt == '3'){
                        $("#timerCvType"+_cvId).val("3");
                        _this.find("#soon"+index).text("已结束");
                    }
                    if(result.typeInt == '4'){
                        $("#timerCvType"+_cvId).val("4");
                        _this.find("#soon"+index).text("观看回放");
                    }
                    if(result.typeInt == '5'){
                        $("#timerCvType"+_cvId).val("5");
                        _this.find("#soon"+index).text("已结束");
                    }
                    //}
                    //})
                }
            })
        });
    }
    /* function timerFun(){
        $("#timerFreshUL li").each(function(index){
            var _timerTimeInputStart = $("#timerTimeInputStart"+index).val();
            var _timerTimeInputEnd = $("#timerTimeInputEnd"+index).val();
            var startDate = new Date((_timerTimeInputStart).replace(/-/g,"/"));
            var endDate = new Date((_timerTimeInputEnd).replace(/-/g,"/"));
            var date = new Date();
            $(this).find(".soon").each(function(index){
                var soonVal = $(this).text();
                if(soonVal != "" && soonVal != "已结束"){
                    if(endDate < date){
                        $(this).text("已结束");
                    }
                }
            })
        })
    }  	 */
</script>