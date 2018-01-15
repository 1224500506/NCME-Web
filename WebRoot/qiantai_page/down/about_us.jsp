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
        <li><a href="${ctx}/qiantai_page/down/about_us.jsp">关于我们</a></li>
    </ul>
    <div class="content no_padding">
        <div class="left_nav about_nav">
            <ul>
                <li class="active"><a href="${ctx}/qiantai_page/down/about_us.jsp">关于我们</a></li>
                <li><a href="${ctx}/qiantai_page/down/contact_us.jsp">联系我们</a></li>
                <li><a href="${ctx}/qiantai_page/down/terms.jsp">服务条款</a></li>
                <li><a href="${ctx}/qiantai_page/down/privacy.jsp">隐私声明</a></li>
            </ul>
        </div>
        <div class="main_cont right about_cont">
            <img src="${ctx}/qiantai_page/img/about_1.png">
            <h2><i class="fa fa-caret-right"></i> 中国继续医学教育网</h2>
            <div class="cont">
                <p style="text-indent:2em">“中国继续医学教育网”作为国家卫生计生委重点督办项目，是在《中共中央国务院关于深化医药卫生体制改革的意见》、《医药卫生中长期人才发展规划（2011-2020年）》、国务院办公厅《关于促进和规范健康医疗大数据应用发展的指导意见》等文件精神的指引下、在科教司业务指导下，集合健康医疗领域各项优质继续教育教学资源，运用开放性办学模式构建的国家级继续医学教育服务云平台，是“国家健康医疗开放大学”的重要组成部分。</p>
                <p style="text-indent:2em">“中国继续医学教育网”紧跟国家健康2030规划各发展阶段对人才队伍的现实需求，坚持科技支撑、创新驱动、需求导向、稳步推进的指导原则，以岗位胜任力为核心提供分专业、分层、分级的继续医学教育。</p>
            </div>
            <div class="cont bg_gray">
                <div class="float_right">
                    <img src="${ctx}/qiantai_page/img/about_2.png">
                </div>
                <h2>远程+面授，基地+项目</h2>
                <p>充分发挥“课程资源数字化、学习目标规范化、实训基地网络化、学习成效可视化、职业能力认证管理规范化”五位一体的教学优势，以教学目标为出发点选择教学方式，注重“项目与基地相结合，理论与操作相结合”。</p>
            </div>
            <div class="cont has_border">
                <div class="float_left">
                    <img src="${ctx}/qiantai_page/img/about_3.png">
                </div>
                <h2>慕课+VR，任务+互动</h2>
                <p>探索新型互联网教学模式和方法，组织优质慕课，通过远程VR手术直播、病例研讨、视频演示、三维动画等不同形式的融合完成线上教学。</p>
            </div>
            <h2><i class="fa fa-caret-right"></i> 国家卫生计生委能力建设和继续教育中心</h2>
            <div class="cont">
                <p style="text-indent:2em">国家卫生计生委能力建设和继续教育中心（以下简称“中心”）是国家卫生计生委直属事业单位（http://www.rkrc.cn），负责卫生计生系统继续教育组织、管理与实施，具体承担卫生计生系统继续教育信息化建设；负责卫生计生系统队伍职业化建设，具体承担公立医院院长任职资格管理，开展行业职业技能鉴定；负责卫生计生行业规范化能力建设，组织开展职业化培训；承担卫生计生行业职业化体系建设，开展职业技能鉴定；实施能力认证社会化服务。</p>
                <p style="text-indent:2em">中心接受科教司委托，建立《中国继续医学教育指南》课题研究组，并设立了《卫生计生专业技术人员岗位胜任力模型》子课题，致力构建符合中国国情的卫生计生专业技术人员的岗位胜任力通用模型，并形成各学科的培训、评价指标体系，从而不断提高继续医学教育水平，提高和改善卫生计生专业技术人员的职业素质和服务能力。前期经过对美国ACGME、加拿大RCPSC和中国医科大学相关研究成果的研究借鉴、全国各省市医院抽样调查、专家研讨论证，课题组现已初步建立面向医生、护理、药师、公卫、医技、中医等六类人员的《卫生计生专业技术人员岗位胜任力模型》。在此基础上，各学科专家委员会将进一步深入的调查研究和系统实证， 逐步形成岗位卫生计生专业技术人员胜任力模型指标体系的更新机制，为中国继续医学教育提供理论指导。</p>
            </div>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>

</body>
</html>