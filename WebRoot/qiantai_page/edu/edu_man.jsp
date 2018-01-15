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
    <style>
    .fa_text{ margin-top:25px; }
    </style>
</head>
<body>
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <ul class="bread_crumbs">
        <li>您的位置：</li>
        <li><a href="${ctx}/first.do">首页</a> <i class="fa fa-angle-right"></i></li>
        <li><a href="${ctx}/qiantai_page/edu/edu_man.jsp">继教管理</a></li>
    </ul>
    <div class="content no_padding">
        <div class="cont">
            <h2 class="main_title_2" style="font-size:25px;font-weight:bold">平台规划</h2>
            <p style="text-indent:2em">“中国继续医学教育网”作为国家卫生计生委重点督办项目，是在《医药卫生中长期人才发展规划（2011-2020年）》，国务院办公厅《关于促进和规范健康医疗大数据应用发展的指导意见》（国办发〔2016〕47号）等文件精神的指引下，在科教司业务指导下，根据《中国继续医学教育指南》建立的全国继续医学教育综合管理系统，服务于继续医学教育实施的全过程，为实现全国继续医学教育数据互通互联，实施个性化，精准化继续教育提供大数据支撑。</p>
            <img src="${ctx}/qiantai_page/img/map.png" style="margin-bottom:30px">
        </div>

        <div class="cont">
            <h2 class="title"><span>基础数据管理系统</span></h2>
            <div class="cont_half">
                <span><i class="fa fa-cubes fa_text"></i></span>
                <h2>教学资源库</h2>
                <p>整合国家卫生计生系统现有资源，凝聚国内各学科一流专家之力，建立包含视音频、图文、试题、病例等多种素材的国家级继续医学教育资源库</p>
            </div>
            <div class="cont_half">
                <span><i class="fa fa-hospital-o fa_text"></i></span>
                <h2>基地管理</h2>
                <p>通过信息化的基地管理流程，充分发挥继教基地的优势，为形成以能力建设为核心，以“远程+面授，基地+项目”为特色的新型继教模式提供服务</p>
            </div>

            <div class="cont_half">
                <span><i class="fa fa-user-md fa_text"></i></span>
                <h2>师资管理</h2>
                <p>对继教师队伍进行统一管理，促进全国继续医学教育资源配置均衡，教师队伍质量稳步提升</p>
            </div>
            <div class="cont_half">
                <span><i class="fa fa-graduation-cap fa_text"></i></span>
                <h2>学员管理</h2>
                <p>建立健全全国卫生计生专业技术人员个人终身学习档案，解决目前继续教育数据无法互通互联的问题，为继教政策和指南的规划奠定数据基础</p>
            </div>

        </div>
        <div class="clearfix"></div>
        <div class="cont" style="margin-top:30px">
            <h2 class="title"><span>项目流程管理系统</span></h2>
            <div class="cont_half">
                <span><i class="fa fa-file-text-o fa_text"></i></span>
                <h2>需求管理</h2>
                <p>作为继续教育规划阶段的重要组成部分，通过调研评估，形成年度CME大纲，指导每年度的继续医学教育工作</p>
            </div>
            <div class="cont_half">
                <span><i class="fa fa-list-alt fa_text"></i></span>
                <h2>项目管理</h2>
                <p>进一步推进继续教育项目信息化管理，建立国家、省市联动的继教项目库，解决继续医学教育项目信息孤岛问题</p>
            </div>
            <div class="cont_half">
                <span><i class="fa fa-key fa_text"></i></span>
                <h2>学习档案</h2>
                <p>以岗位胜任力模型为参照，为全国卫生计生技术人员建立个人终身学习档案，通过对培训记录进行大数据分析，为各学科人才培养提供有力支撑</p>
            </div>
            <div class="cont_half">
                <span><i class="fa fa-shirtsinbulk fa_text"></i></span>
                <h2>监督质控</h2>
                <p>建立权威的医学教育负面清单和推优表彰制度，<br>为继教教育的健康发展提供保障</p>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>
</body>
</html>