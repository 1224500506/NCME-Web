<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<form action="">
<div class="container">
	<%@include file="/qiantai_page/top2.jsp"%>
    <div class="content no_padding"  style="padding-top:61px">
        <div class="tabs">
            <ul class="tab_list">
                <li id="tab1" class="tab">专委会职责</li>
                <!-- 
                <li id="tab2" class="tab">管理办法</li>
                 -->
                <li id="tab3" class="tab active">专委会列表</li>
            </ul>
            <div class="tab1 tab_cont" style="display:none;min-height:420px;">
                <ul>
                    <li>（一）建设本专业的卫生计生专业技术人员岗位胜任力模型、培训指标体系、评价指标体系，并形成完善更新机制；</li>
                    <li>（二）组织编写本专业《中国继续医学教育指南》；</li>
                    <li>（三）组织编写本专业能力建设专用教材、继续教育项目专用教材；</li>
                    <li>（四）设计、征集、组织、推荐、审核继教培训项目与专业能力建设培训项目；</li>
                    <li>（五）结合本专业能力建设和继续医学教育开展课题研究、学术交流活动，推广新知识、新理论、新技术和新方法。</li>
                </ul>
            </div>
            <div class="tab2 tab_cont" style="display:none;width:100%;">
	            <div class="main_cont right about_cont" style="width:100%;margin-top:30px;">
	            <h2 style="width:100%;text-align:center;">国家卫生计生委能力建设和继续教育中心</h2>
	            <h2 style="width:100%;text-align:center;">专家委员会管理办法（草案）</h2>
	  
	            <h3 style="width:100%;text-align:center;">第一章  总  则</h3>
	            <div class="cont" style="width:100%;">
	                <p style="text-indent:2em;width:100%;">第一条  为贯彻落实《中共中央 国务院关于深化医药卫生体制改革的意见》、《卫生部关于加强“十三五”期间继续医学教育工作的指导意见》、《医药卫生中长期人才发展规划（2011-2020年）》的文件精神，尽快建立与发展相适应的卫生计生专业技术人才队伍专业能力建设与继续医学教育机制，本中心结合工作实际，特制定《国家卫生计生委能力建设和继续教育中心专家委员会管理办法》（以下简称《管理办法》）。</p>
	                <p style="text-indent:2em;width:100%;">第二条 本中心设立继续教育信息化项目管理办公室（以下简称项目办公室），负责专家委员会的管理与协调工作。</p>
	                <p style="text-indent:2em;width:100%;">第三条  专家委员会的名命名方式为：“国家卫生计生委能力建设和继续教育 × × 科专家委员会”，开展活动时，应使用全称；其英文译名应与中文名称一致，并报项目办公室审核备案。</p>
	                <p style="text-indent:2em;width:100%;">第四条 专家委员会负责人职务规范名称为：主任委员、副主任委员、秘书长、委员。专家委员会受项目办公室的领导和管理，在授权范围内开展活动，对项目办公室负责。</p>
	                <p style="text-indent:2em;width:100%;">第五条  专家委员会开展活动应遵循以下原则：</p>
	                <p style="text-indent:40px;">（一）遵守国家法律、法规，贯彻执行党和国家的方针、政策，坚持正确的政治方向。</p>
					<p style="text-indent:40px;">（二）自觉接受本中心的领导、管理和监督，重要事项应及时向项目办公室请示、报告。未经请示、报告或未获批准的，不得实施。</p>
					<p style="text-indent:40px;">（三）紧密结合中心实际工作，组织开展健康、有益的活动，做到自律守信，扎实工作，热情服务，务实高效。</p>
					<p style="text-indent:40px;">（四）不得以营利为目的开展活动。</p>
					<p style="text-indent:2em;width:100%;">第六条   专家委员会须紧密围绕卫生计生各专科能力建设和继续教育的工作，在专业范围内承接项目办公室委托与授权的各项工作，具体如下：</p>
					<p style="text-indent:40px;">（一）建设本专业的卫生计生专业技术人员岗位胜任力模型、培训指标体系、评价指标体系，并形成完善更新机制。</p>
					<p style="text-indent:40px;">（二）组织编写本专业《中国继续医学教育指南》。</p>
					<p style="text-indent:40px;">（三）组织编写本专业能力建设专用教材、继续教育项目专用教材。</p>
					<p style="text-indent:40px;">（四）设计、征集、组织、推荐、审核继教培训项目与专业能力建设培训项目。</p>
					<p style="text-indent:40px;">（五）结合本专业能力建设和继续医学教育开展课题研究、学术交流活动，推广新知识、新理论、新技术和新方法。</p>
					<p style="text-indent:40px;">（六）承担项目办公室交办的其他相关工作。</p>
	            </div>
	            <h3 style="width:100%;text-align:center;">第二章 设立、变更与撤销</h3>
	            <div class="cont">
	            	<p style="text-indent:2em;width:100%;">第七条  专家委员会的设立条件：</p>
	            	<p style="text-indent:40px;">（一）具有明确的专科咨询需求和明晰的工作任务与业务范围，已形成相对独立的专业领域或学科体系，与本中心其他既有专家委员会不存在交叉重复；</p>
					<p style="text-indent:40px;">（二）拥有造诣较深的行业或学科带头人以及热心医学工作的骨干和委员队伍；</p>
					<p style="text-indent:40px;">（三）具备独立开展某领域学术活动的条件和能力。</p>
					<p style="text-indent:2em;width:100%;">第八条  申请设立专家委员会的程序：</p>
					<p style="text-indent:40px;">（一）申请设立专家委员会，应由该领域或该学科在全国范围内有广泛影响力的机构负责人或学术带头人提议发起，向项目管理办公室提交成立该专家委员会的申请，并填报新设专家委员会申请表、拟任负责人登记表、主任委员、常务副主任委员、副主任委员、委员候选人名单。</p>
					<p style="text-indent:40px;">（二）项目管理办公室将对申报材料的完整性和负责人任职资格进行审核。对不符合要求的，予以退回和重新申报；符合要求的，由本中心批复后成立。</p>
					<p style="text-indent:40px;">（三）新设专家委员会获准成立后，由本中心对外发布通知，公布专家委员会名单。</p>
	            </div>
	            <h3 style="width:100%;text-align:center;">第三章  组织与职能</h3>
	            <div class="cont">
	            	<p style="text-indent:2em;width:100%;">第九条  专家委员会设主任委员、副主任委员、秘书长，每届任期三年。专家委员会组成应根据学科发展现状，同时兼顾专业代表性和地域性，合理分配委员名额。</p>
	            	<p style="text-indent:2em;width:100%;">第九条  专家委员会委员应具备下列基本条件：</p>
	            	<p style="text-indent:40px;">（一）坚持党的路线、方针和政策，具有完全民事行为能力。</p>
					<p style="text-indent:40px;">（二）从事本领域管理或专业技术工作15年以上，具有副主任医师、副教授、研究员及以上职称，原则上应为所在单位在职人员。</p>
					<p style="text-indent:40px;">（三）工作认真，作风正派，组织协调能力强，热心能力建设与继续教育工作。</p>
					<p style="text-indent:40px;">（四）年龄不超过65岁，身体健康，能坚持日常工作。</p>
					<p style="text-indent:2em;width:100%;">第十条  专家委员会副主任委员、和主任委员除符合第十二条的要求外，还应具备下列条件：</p>
					<p>（一）有较高的管理或专业水平，在卫生计生专业领域中有较高的学术威望。</p>
					<p>（二）所在单位在本行业或本专业内具有较高知名度和影响力，愿意支持专家委员会的工作。</p>
					<p style="text-indent:2em;width:100%;">第十一条  专家委员会设主任委员1名，秘书长１名，副主任委员原则上不超过9名；原则上 ，一人只允许在一个专家委员会任主任委员、副主任委员或秘书长。</p>
					<p style="text-indent:2em;width:100%;">第十二条  原则上，专家委员会主任委员、副主任委员、秘书长、连任不超过两届。专家委员会委员连任不超过三届。</p>
					<p style="text-indent:2em;width:100%;">第十三条  专家委员会实行主任委员负责制。主任委员主持专家委员会工作，副主任委员协助主任委员负责分管工作，秘书长负责协调沟通工作，专家委员会原则上不聘用专职工作人员。</p>
					<p style="text-indent:2em;width:100%;">第十四条　各专家委员会根据本专家委员会的工作任务下设各学科专家小组，小组内自行任命小组组长、副组长和成员，成立后报项目管理办公室审核备案。</p>
					<p style="text-indent:2em;width:100%;">第十五条  专家委员会全体会议每年至少召开一次，会议的时间、地点和内容须预先上报到项目管理办公室，获得批准后召开，会议纪要须及时上报和下发。</p>
					<p style="text-indent:2em;width:100%;">第十六条  专家委员会应在届满前至少半年开始筹备换届事宜，制定换届工作方案，并上报项目管理办公室审批。经项目管理办公室批准后，方可组织开展新一届委员会的提名推荐工作。</p>
					<p style="text-indent:2em;width:100%;">第十七条  专家委员会换届时，委员更新比例不低于三分之一，不超过三分之二。所有委员候选人均应征求其所在单位和项目管理办公室的意见。</p>
					<p style="text-indent:2em;width:100%;">第十八条  专家委员会换届工作小组负责委员候选人的资格审查，并提出新一届委员、秘书长、副主任委员、主任委员候选人名单，报项目管理办公室审批。</p>
					<p style="text-indent:2em;width:100%;">第十九条  专家委员会候选人名单经本中心书面批准后生效。</p>
					<p style="text-indent:2em;width:100%;">第二十条  专家委员会可聘任名誉主任委员和顾问。卸任的前任主任委员可受聘为新一届委员会名誉主任委员，卸任的前任副主任委员，对本专家委员会的工作有突出贡献的，可受聘为新一届委员会顾问。名誉主任委员、顾问任期一届。</p>
					<p style="text-indent:2em;width:100%;">第二十一条  当选的专业委员会名誉主任委员、顾问、主任委员、副主任委员、秘书长 、委员由本中心统一颁发证书。</p>
					<p style="text-indent:2em;width:100%;">第二十二条  专家委员会届满因故不能按期换届的，由专家委员会全体会议讨论通过后，向项目管理办公室提出延期换届申请，报项目管理办公室审批。延期换届最长不超过一年。</p>
					<p style="text-indent:2em;width:100%;">第二十三条  专家委员会主任委员在任期内因工作变动、身体等原因不宜继续担任主任委员职务的，由本人提出，报项目管理办公室通过后，可中止其职务，并由本中心颁发感谢状。接任的主任委员人选由项目管理办公室与专家委员会共同研究决定，由本中心颁发主任委员证书。</p>
					<p style="text-indent:2em;width:100%;">第二十四条  如确有工作需要，专家委员会可增补委员。但须由拟增补的委员所在单位提名，由专家委员会向项目管理办公室提交增补委员的书面请示。项目办公室审核同意后方可增补为委员。委员增补每年可进行一次。</p>
					<p style="text-indent:2em;width:100%;">第二十五条  专家委员会换届调整时，应及时做好工作文件、档案的移交，确保本专家委员会工作的连续性。</p>
	            </div>
	            <h3 style="width:100%;text-align:center;">第四章  业务活动</h3>
	            <div class="cont">
	            	<p style="text-indent:2em">第二十六条  专家委员会开展业务活动，应遵守《管理办法》所规定的范围，应有利于我国卫生计生事业的发展，符合本学科专家委员会的性质和特点，积极、规范、有效地进行，严禁开展任何以营利为目的的活动。</p>
					<p style="text-indent:2em">第二十七条  专家委员会的业务活动受本中心的管理、指导与监督。</p>
					<p style="text-indent:2em">第二十八条  专家委员会开展活动时，如需签订协议书或合同书等法律性文件，应对其内容条款进行认真审核，并向项目管理办公室提出申请。协议或合同涉及到经济往来的，须由本中心法定代表人或法定代表人授权代表审核签订。</p>
					<p style="text-indent:2em">第二十九条  专家委员会与其他行业组织开展合作活动必须经项目管理办公室授权或批准。凡涉及使用专家委员会名称或有经济往来的，除上报项目管理办公室同意外，还应参照《管理办法》的规定，与合作方签订协议，明确各方权利、义务和法律责任。</p>
					<p style="text-indent:2em">第三十条  专家委员会开展合作活动时，应对合作方的资质、能力、信用等进行甄别考察，对合作内容做好风险评估，对合作项目全程监督，切实履行相关职责，不得以挂名方式参与合作。 </p>
					<p style="text-indent:2em">第三十一条  专家委员会将自身业务活动委托其他组织承办或协办的，应当加强对所开展活动的主导和监督。严禁将专家委员会的业务活动转包或者委托与专家委员会负责人有直接利益关系的个人或者组织实施。</p>
					<p style="text-indent:2em">第三十二条  专家委员会与境外组织或者个人进行合作，应当遵守有关法律法规和外事管理规定。</p>
					<p style="text-indent:2em">第三十三条  本中心对专家委员会实行重大活动申报制度，凡属参与、组织或联合组织重大业务活动的，均应在该活动的筹备工作启动前至少一个月报项目管理办公室审核同意。活动结束后，应及时将书面总结上报项目管理办公室备案。重大活动包括：</p>
					<p style="text-indent:40px;">（一）全国性或行业性的、规模在100人以上的学术会议活动；</p>
					<p style="text-indent:40px;">（二）涉外及涉港、澳、台的活动；</p>
					<p style="text-indent:2em">对于专家委员会违反上述规定，擅自开展重大活动的，项目管理办公室有权令其停止，产生的后果由专家委员会承担。</p>
					<p style="text-indent:2em">第三十四条  专家委员会应保质保量完成项目管理办公室交办的继续医学教育、专业能力建设、专项调研、业务咨询与建议等工作和任务。</p>
					<p style="text-indent:2em">第三十五条  专家委员会应积极履行义务，支持和参与本中心活动，认真完成项目管理办公室交办的工作。并应按照本中心的统一部署，支持和参与本中心举办的大型综合性活动。</p>
	            </div>
	            <h3 style="width:100%;text-align:right;">国家卫生计生委能力建设和继续教育中心</h3>
	            <h3 style="width:100%;text-align:right;">2016年5月</h3>
	        </div>
         </div>
            <div class="tab3 tab_cont">
                <ul class="card_list">
                    <c:forEach items = "${info}" var = "p">
                    <li >
                        <a href="${ctx}/ExpertGroup.do?mode=detail&id=${p.group.id}" target="_blank" id="groupList" title="${p.group.name}">
			                	<div class="top_cont" >
	                                <h2 id = "${p.group.id}">
											<c:choose>
												<c:when test="${fn:length(p.group.name)<=9}">
													${p.group.name}
												</c:when>
												<c:otherwise>
													${fn:substring(p.group.name,0,9)}...
												</c:otherwise>
											</c:choose>
									</h2>
	                            </div>
	                            <div class="user_cont" style = "height:205px">
	                                <span class="title">主任委员</span>
	                                <c:if test = "${p.expertList != null && p.expertList.size()!=0 }">
		                                <span class="avatar"><img src="${p.expertList.get(0).photo}"></span>
		                                <h2>${p.expertList.get(0).name}</h2>
		                                <h3 class="topvebanner-p" title='${p.expertList.get(0).workUnit}'>
											<c:if test="${fn:length(p.expertList.get(0).workUnit)>15}">
					                          ${fn:substring(p.expertList.get(0).workUnit,0,15)}...
					                        </c:if>
					                        <c:if test="${fn:length(p.expertList.get(0).workUnit)<=15}">
					                          ${p.expertList.get(0).workUnit}
					                        </c:if>
										</h3>
	                                </c:if>
	                            </div>
                        </a>
                        </li>
                        </c:forEach>
                </ul>
                <%@include file="/commons/page.jsp"%>
            </div>
        </div>
    </div>
    <div style="margin-top:70px;">
    <%@include file="/qiantai_page/bottom.jsp"%>
    </div>
</div>
</form>

</body>
</html>