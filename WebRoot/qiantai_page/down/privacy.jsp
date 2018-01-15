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
        <li><a href="${ctx}/qiantai_page/down/privacy.jsp">隐私声明</a></li>
    </ul>
    <div class="content no_padding">
        <div class="left_nav about_nav">
            <ul>
                <li><a href="${ctx}/qiantai_page/down/about_us.jsp">关于我们</a></li>
                <li><a href="${ctx}/qiantai_page/down/contact_us.jsp">联系我们</a></li>
                <li><a href="${ctx}/qiantai_page/down/terms.jsp">服务条款</a></li>
                <li class="active"><a href="${ctx}/qiantai_page/down/privacy.jsp">隐私声明</a></li>
            </ul>
        </div>
        <div class="main_cont right">
        	<div class="main_cont right about_cont">
        	<div><h2></h2></div>
            <div class="cont">
            	欢迎您访问中国继续医学教育网网站 ！<br/><br/>
            	本隐私声明是我们对访问者隐私保护的许诺。网站访问者（以下也称“用户”或“您”）的信息对我们非常重要，并且我们非常重视对您的隐私信息的保护,因此我们特对隐私信息的收集、使用和许可等作如下声明： 
            </div>
            <div class="cont has_border">
                <h2>一、用户信息的收集范围</h2>
				<p> 我们获取信息的主要目的在于向用户提供一个顺畅、高效的学习流程，并致力于不断完善和提升学习体验。我们可能会收集和使用的信息包括您主动提供给我们、我们主动向您收集以及第三方向我们提供的信息，具体包括：</p>
				<p>（1）浏览信息：包括您的网页浏览记录、搜索记录等信息；</p>
				<p>（2）注册信息：包括您在注册时设置的用户名、用户昵称，以及在注册时填写及后期补充的姓名、公司名称、证件号码、地址、邮箱、联系电话、传真等个人或公司信息。</p>
				<p>（3）支付信息：包括付款人、付款方式、付款金额、银行账号等信息。</p>
				<p>（4）设备信息：包括您的浏览器和计算机上的信息，如IP地址、浏览器的类型、语言、访问时间、软硬件特征等数据。当您下载或使用我们的客户端软件，或访问移动WAP站点时，我们会读取与您的位置和移动设备相关的信息，如设备型号、设备识别码、操作系统、分辨率、电信运营商等数据。</p>
				<p>（5）其他在客服咨询、投诉或电话回访中获得的信息。</p>
            </div>
            <div class="cont has_border">
                <h2>二、用户信息的用途</h2>
				<p>为了向您提供服务，并不断提升我们的服务体验，我们将不可避免地使用您的信息，信息的使用方式和用途主要包括：</p>
				<p>（1）向您提供你需要或可能感兴趣的课程或服务信息；</p>
				<p>（2）就您的要求向您提供咨询服务，或向您做出回应或与您沟通；</p>
				<p>（3）根据您的申请，向您提供会员帐号，并授予您在中国继续医学教育网网站购买商品及参加各种服务的相应会员资格，如促销活动、购买优惠资格；</p>
				<p>（4）收取、处理或退还款项；</p>
				<p>（5）为评估和完善我们的服务，联系您进行调研；</p>
				<p>（6）为评估和完善我们的服务，进行数据分析，如购买行为或趋势分析、市场或顾客调查、财务分析；</p>
				<p>（7）在事先获得您同意的情况下，向您指定的联系方式发送产品和服务信息；</p>
				<p>（8）预防和追究各种违法、犯罪活动或违反我们政策规则的法律责任；</p>
				<p>（9）经您许可的其他用途。</p>
            </div>
            <div class="cont has_border">
                <h2>三、用户信息的共享、披露与转让</h2>
                <p>1、用户的信息是我们业务的重要组成部分，我们不会出售或以其他方式共享您的个人信息，但如下情况除外：</p>
				<p>（1）与我们的关联公司、入驻机构共享相关信息；</p>
				<p>（2）与为我们提供配送服务、收付款服务或为我们发送信函的合作伙伴共享相关信息；</p>
				<p>（3）根据法律、法规及法律程序的规定；</p>
				<p>（4）根据政府部门（如行政机构、司法机构）的要求；</p>
				<p>（5）事先获得您的许可。</p>
				<p>2、为了给您提供更好、更优、更加个性化的服务，或共同为您提供服务，或为了预防互联网犯罪（如诈骗）的目的，我们的关联方、合作伙伴（如银行）会依据法律的规定或与您的约定或征得您同意的前提下，向我们分享您的个人信息。</p>
            </div>
            <div class="cont has_border">
                <h2>四、其他网站链接</h2>
				<p>本网站可能会包含至其他网站的链接，此类网站一般不是我们运营。因此，我们建议您在访问链接网站时谨慎浏览和使用；您在使用此类网站服务时造成的任何后果，我们概不负责。</p>
            </div>
            <div class="cont has_border">
                <h2>五、未成年人的保护</h2>
				<p>我们非常重视对未成年人个人信息的保护。如果您是未成年人，请与您监护人一起或在监护人的监督下使用本网站的产品或服务。</p>
            </div>
            <div class="cont has_border">
                <h2>六、《隐私声明》的更新</h2>
				<p>我们可能会不时更新本《隐私声明》，并在更新时在本网站上醒目位置发布公告，我们欢迎您随时查看本声明。</p>
            </div>
			<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您对本隐私声明或中国继续医学教育网的隐私保护措施以及您在使用中的问题有任何意见和建议请直接联系客服咨询。</h5>
        </div>
        
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>
</div>

</body>
</html>