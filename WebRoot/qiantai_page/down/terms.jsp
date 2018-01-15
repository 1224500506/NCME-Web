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
<body>
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="${ctx}/qiantai_page/down/terms.jsp">服务条款</a></li>
    </ul>
    <div class="content no_padding">
        <div class="left_nav about_nav">
            <ul>
                <li><a href="${ctx}/qiantai_page/down/about_us.jsp">关于我们</a></li>
                <li><a href="${ctx}/qiantai_page/down/contact_us.jsp">联系我们</a></li>
                <li class="active"><a href="${ctx}/qiantai_page/down/terms.jsp">服务条款</a></li>
                <li><a href="${ctx}/qiantai_page/down/privacy.jsp">隐私声明</a></li>
            </ul>
        </div>
        <div class="main_cont right">
        	<div class="main_cont right about_cont">
        	<div><h2></h2></div>
            <div class="cont has_border">
                <h2>版权信息</h2>
                <p>1、中国继续医学教育网单独拥有或与相关内容提供者共同拥有本网站内所有内容（包括但不限于文字、图片、音频、视频资料及页面设计、编排、软件等）或通过本网站提供产品或服务所涉及到的版权和/或其他相关知识产权。与本网站相关的标识为中国继续医学教育网的注册商标，受中国法律保护。</p>
				<p>2、除非中国法律另有规定，未经中国继续医学教育网书面许可，对于中国继续医学教育网拥有版权和/或其他相关知识产权的任何内容，任何公司及个人不得复制或在非中国继续医学教育网所属的服务器上做镜像或以其他任何方式进行使用（包含但不限于抄录、编辑、修改、传播及销售）。对于本网站标识等中国继续医学教育网的注册商标，任何人不得擅自使用。</p>
				<p>3、已获得书面授权，可以使用中国继续医学教育网拥有版权和/或其他相关知识产权的任何内容及商标标识的公司及个人使用上述内容时必须注明来源。</p>
				<p>4、本网站注明来源的稿件、图片及其他作品的内容均属转载，本网站的转载行为是基于信息共享和传播，并未进行权属、真实性等问题的核实，亦并不对其观点持任何立场；其他公司或个人若从本网站下载使用所转内容，须保留本网站注明的“稿件来源”，并自负版权等法律责任。</p>
				<p>5、本网站的内容或转载内容若涉及版权及其他任何侵权问题，请版权人或其他权利人以书面形式向中国继续医学教育网反映，并提供相应身份证明、权属证明及详细的侵权情况证明。中国继续医学教育网提醒您注意：您须保证您的主张和陈述内容的真实性，并对此承担法律责任。</p>
            </div>
            <div class="cont has_border">
                <h2>学习账号使用申明</h2>
                <p>任何学员对其学习账号的使用以及账号安全负全部责任；每个学员的学习账号仅限学员个人私自使用，学员以任何方式与任何第三方共享学习账号或公开学习课程（包括但不限于向任何第三方透露学习课程、与他人共享学习账号、将自己的学习账号提供给第三方使用、将学习课程公开播放或以任何方式供多人同时使用）都是严格禁止的；上述情况一旦发生，我司将立即停止违规账号的学习权限，同时我司会进一步追究违规人员的法律责任，包含不限于追偿损失、司法追责等。</p>
            </div>
            <div class="cont has_border">
                <h2>免责条款</h2>
                <p>用户在接受中国继续医学教育网服务之前，请务必仔细阅读本条款并同意本声明。</p>
				<p>用户直接或通过各类方式间接使用中国继续医学教育网服务和数据的行为，都将被视作已无条件接受本声明所涉全部内容；若用户对本声明的任何条款有异议，请停止使用中国继续医学教育网所提供的全部服务。</p>
				<p>1、若中国继续医学教育网已经明示其网络服务提供方式发生变更并提醒用户应当注意事项，用户未按要求操作所产生的一切后果由用户自行承担。</p>
				<p>2、中国继续医学教育网严格遵守中华人民共和国有关的各项法律法规，用户应对其所提供作品因形式、内容及授权的不完善、不合法所造成的一切后果承担完全责任，中国继续医学教育网对用户上传的文章、课件及其他资源不承担任何法律责任。中国继续医学教育网对会员上传的医学相关著作权归属无法做出独立判断；上传人应当在确信自己没有侵害他人著作权的前提之下，向中国继续医学教育网上传文章及其他资源，否则应当自行承担由此引起的一切法律责任。</p>
				<p>3、中国继续医学教育网所刊载的内容，包括文章、图片及其他资源，并不代表同意其说法或描述，仅供参考使用，也不构成任何学术建议。</p>
				<p>4、中国继续医学教育网可能（但在法律上不负任何责任）会监控或审查用户在本站上发送或邮寄的信息或相互之间单独交流的任何领域，包括但不限于交谈室、公告牌或其他用户论坛以及任何交流内容。中国继续医学教育网对有关任何这类交流的内容不承担任何责任，无论它们是否会因版权法引起诽谤、隐私、淫秽或其它方面的问题。中国继续医学教育网保留删除包含被视为侮辱、诽谤、淫秽或其它不良内容的消息的权利。</p>
				<p>5、本站到第三方网站的链接仅作为一种方便服务提供给您。如果使用这些链接，您将离开本站。中国继续医学教育网没有审查过任何第三方网站，对这些网站及其内容不进行控制，也不负任何责任。因此，中国继续医学教育网对这些网站及其任何信息、软件或其他产品、材料、或因使用它们而可能获得的任何结果不予认可，也不作任何陈述。如果您决定访问任何与本站链接的第三方网站，其风险全部由您自己承担。</p>
				<p>6、中国继续医学教育网及其供应商或本站中提到的第三方不担保网络服务一定能满足用户的要求，也不担保网络服务不会中断，对网络服务的及时性、安全性、准确性、以及不可抗力导致的任何后果，也都不作担保，无论这些后果是否由于使用、或不能使本站的结果、与本站链接的任何网站或者任何这类网站中包含的信息所引起，也不管它们是否有保证、合同、侵权行为或任何其它法律根据以及事前已得到这类损害可能发生的忠告。</p>
            </div>
        </div>





     
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>

</body>
</html>