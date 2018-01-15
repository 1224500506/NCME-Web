<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
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
    <meta name="keyword" content="">
    <meta name="description" content="">
    <title>中国继续医学教育网</title>
    <%@include file="/commons/taglibs.jsp"%>
    <script src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script>
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
</head>
<style>
    .tabs{
        overflow: hidden;
        width: 100%;
    }
    .tab{
        display: block;
        padding: 5px 8px;
    }
    .active{
    	border:none;
        border-left: 3px solid #12bce1;
    }
    .tab_list{
        float: left;
    }
    .tab_cont{
        float: right;
        width: 87%;
        border:1px solid #cdcdcd;
        border-radius: 5px;
    }
    .ul{
         display: block;
        width: 100px;
    }
    
</style>

<body>
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="help.jsp">帮助中心</a></li>
    </ul>
    <div class="content no_padding">
        <div class="tabs">
            <ul class="tab_list">
                <li class="tab active" id="tab1"  >登录与注册</li>
                <li class="tab" id="tab2" >项目学习</li>
                <li class="tab" id="tab3" >使用学习卡</li>
                <li class="tab" id="tab4" >申请学习</li>
                <li class="tab" id="tab5" >专项能力</li>
            </ul>
            <div class="tab_cont tab1">
                <div class="cont">
                <ol>
                    <li>如何注册？
                        <p>初次访问中国继续医学教育网-NCME平台的浏览者，可以点击页面右上角的“注册”或登录页面内的“去注册”按钮，进入注册页面，填写相应信息后，提交完成注册。</p>
                    </li>
                    <li>为什么注册时要填写真实个人信息？
                        <p>1.根据国家相关政策规定，继续医学教育学分证书必须与个人真实信息相关联（姓名、身份证号、年龄、单位、科室等），若您注册时填写的信息不真实，您获得的学分将无法获得主管部门的认可；2.为保障您的权益，您需要查询用户名和密码时，需要核实您的身份信息。</p>
                    </li>
                    <li>手机绑定了平台其他账户怎么办？
                        <p>目前平台仅支持一个手机号绑定一个账户，若您已经使用手机号注册过一个用户，则不会成功进行二次注册。</p>
                    </li>
                    <li>为什么我收不到手机验证码？
                        <p>1.请检查您的手机是否因为欠费等原因被停机；2.请检查手机短信收件箱是否已满；3.一些防骚扰应用可能会将验证码误判断为骚扰短信而进行拦截，请您检查引用的拦截记录；如您确认以上三点没问题，请您联系在线客服或拨打客服电话400-863-1000，我们帮您排查问题。</p>
                    </li>
                    <li>登录时，输入验证码，没反应怎么办？
                        <p>1.清除当前浏览器缓存，重新登录；2.检查网络设置，确认无误后重新登录。</p>
                    </li>
                    <li>如何查看或修改个人信息？
                        <p>登录中国继续医学教育网-NCME平台，进入“个人中心”，点击“账号管理”，可以查看并修改个人部分基本信息；身份证号本人不可随意修改；若要修改，则需要联系客服，经过严格验证及审核后，方能修改。</p>
                    </li>
                    <li>如何修改密码？
                        <p>登录中国继续医学教育网-NCME平台，进入“个人中心”，点击“账号管理--账号安全”，可修改登录密码。</p>
                    </li>
                    <li>忘记密码怎么办？
                        <p>进入登录页面，点击登录框下方的“找回密码”，输入您注册时填写的手机号码，即可重新设置您的密码；或通过在线客服、客服电话400-863-1000验证身份信息后，我们会帮助您查找。 </p>
                    </li>
                </ol>
                </div>
            </div>
            <div class="tab_cont tab2" style="display:none">
                <div class="cont">
                    <ol>
                        <li>学习项目，是否需要登录？
                            <p>学习项目需要登录；中国继续医学教育网采用胜任力模型，为学员智能推荐学习项目，为保证学员具有良好的使用体验，需要学员先登录系统，再进行项目的学习。</p>
                        </li>
                        <li>是否必须学习本学科/本专业的项目？
                            <p>若您工作单位所在地区的卫生行政部门要求您必须获得本专业的相关学分，请遵守相关规定。除此之外，您也可以选择您感兴趣的其他学科/专业的项目进行学习。</p>
                        </li>
                        <li>如何选择合适的项目？
                            <p>1.您可以通过平台首页的“学科导航”、“搜索”等功能查找合适的项目进行学习 ；2.您登录后，系统会根据您注册时填写的职称、学科、及所在的单位信息为您自动推荐合适的项目，您可以在您所在学科页面、胜任力、及个人中心-我的胜任力页面，查看系统推荐的项目。</p>
                        </li>
                        <li>学习过程中的任务点是什么？
                            <p>中国继续医学教育网注重学员的过程性学习，任务点是学员在学习过程中需要完成的任务，以便检测学员是否已经掌握学习内容，只有完成项目任务点，系统才认为学员已经完成该项目的学习。</p>
                        </li>
                        <li>任务点是否都必须完成？
                            <p>任务点是系统对您学习过程的检验，若您需要申请该项目的学分，则必须完成所有的任务点；若您不需要申请该项目的学分，则可以根据您个人的学习情况，自行决定是否完成。</p>
                        </li>
                        <li>项目学习是否有时间限制？
                            <p>不同项目，针对不同适用地区、人群等，有不同的学习时限，学员可通过项目介绍中的“起止时间”查看项目的学习时限。</p>
                        </li>
                        <li>直播为何不能查看？
                            <p>直播不能观看的可能原因如下：1.直播参与人数上限为1000人，超过1000人后则无法进入查看直播；2.直播已经结束，视频转码尚未完成，则不可查看回放，等待转码完成后，可点击查看回放；3.网络问题，您可以重启您家的路由器，检查网络，如还是不能观看，请拨打客服电话400-863-1000。</p>
                        </li>
                        <li>已经错过的直播，还能否观看？
                            <p>已经结束的直播，可等待直播视频转码完成后，观看直播回放；转码完成的直播，“观看回放”字样为可点击状态。</p>
                        </li>
                        <li>视频播放过程音频和画面不同步
                            <p>可能原因及解决方法如下：1.首先，请确定您正确使用课件播放器，注意播放、暂停、前后翻页等按钮。2.如果手动翻页过快，可能造成视频画面或声音无法及时同步，遇到此种情况，请耐心等待。建议按照教师讲解速率正常收看课程。3.您所在某些地区的网络条件比较差，影响视频播放。您可以尝试重新连接网络或者切换至网速更快的网络以实现流畅的视频播放。4.如果经上述方式，仍发现视频播放有问题，请您联系在线客服或拨打客服电话400-863-1000，我们将及时检查解决。</p>
                        </li>
                        <li>如何退出一个正在学习的项目？
                            <p>登录后，进入个人中心-我的学习，将鼠标移到要退出的项目上，项目右上角将出现删除按钮，点击、确认即可退出该项目。</p>
                        </li>
                        <li>项目中的各个章节都学习了，为什么进度不是100%
                            <p>进度不是百分百，可能是项目中的某个学习任务点尚未完成。点开该项目，逐个章节打开检查，如果某个章节的任务点图标仍为灰色，则表示该章节的任务点尚未完成，需要按照该章节的任务点要求完成学习。</p>
                        </li>
                    </ol>
                </div>
            </div>
            
             <div class="tab_cont tab3" style="display:none">
                <div class="cont">
                    <ol>
                       <li>有困难找警察叔叔</li>
                        <p>学习项目需要登录；中国继续医学教育网采用胜任力模型，为学员智能推荐学习项目，为保证学员具有良好的使用体验，需要学员先登录系统，再进行项目的学习。</p>
                    </ol>
                </div>
            </div>
            
             <div class="tab_cont tab4" style="display:none">
                <div class="cont">
                    <ol>
                       <li>有困难找爸爸</li>
                        <p>学习项目需要登录；中国继续医学教育网采用胜任力模型，为学员智能推荐学习项目，为保证学员具有良好的使用体验，需要学员先登录系统，再进行项目的学习。</p>
                    </ol>
                </div>
            </div>
            
             <div class="tab_cont tab5" style="display:none">
                <div class="cont">
                    <ol>
                       <li>有困难找妈妈</li>
                        <p>学习项目需要登录；中国继续医学教育网采用胜任力模型，为学员智能推荐学习项目，为保证学员具有良好的使用体验，需要学员先登录系统，再进行项目的学习。</p>
                    </ol>
                </div>
            </div>
            
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>

</body>
</html>