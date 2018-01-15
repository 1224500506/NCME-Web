<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=0,maximum-scale=1,user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="robots" content="index,follow">
<meta name="apple-mobile-web-app-title" content="中国继续医学教育网">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="keyword" content="">
<meta name="description" content="">
<title>中国继续医学教育网</title>


<%@include file="/commons/taglibs.jsp"%>
<script
	src="${ctx}/qiantai_page/js/jquery-1.11.1.min.js?rev=8101d596b2b8fa35fe3a634ea342d7c3"></script>
<script
	src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js?rev=704152c7bb95698f12cad09ac0f2ff62"></script>
<script
	src="${ctx}/qiantai_page/js/jquery.bxslider.js?rev=b257e0969014adf4d5836b545fd6ccfc"></script>
<script
	src="${ctx}/qiantai_page/js/iconfont.js?rev=4944cef39d9362ec54350ae8e04692b5"></script>
<script
	src="${ctx}/qiantai_page/js/jquery.qrcode.min.js?rev=ddb79e5abac8e281bbdd3cc48d8462cb"></script>
<script
	src="${ctx}/qiantai_page/js/main.min.js?rev=21c2f98ef43f0a8b456ab54440b323bc"></script>
<script src="${ctx}/qiantai_page/js/validate.js"></script>
<link
	href="${ctx}/qiantai_page/css/animate.min.css?rev=178b651958ceff556cbc5f355e08bbf1"
	rel="stylesheet">
<link
	href="${ctx}/qiantai_page/css/jquery.bxslider.css?rev=77dc860468f9d4798bc28bd38b285561"
	rel="stylesheet">
 <link href="${ctx}/qiantai_page/css/themes/global.css" rel="stylesheet">
<link
	href="${ctx}/qiantai_page/css/global.css?rev=d0180079bf5750e145db7c5b30055926"
	rel="stylesheet">
<%-- <link href="${ctx}/qiantai_page/css/iconfont.css" rel="stylesheet"> --%>
<link href="${ctx}/qiantai_page/css/rating.css" rel="stylesheet">
<script src="${ctx}/qiantai_page/js/qrcode.js"></script>
<link href="${ctx}/qiantai_page/css/chakanzhengshu.css" rel="stylesheet"
	id="css">

<style type="text/css">
.tank {
	position: fixed;
	bottom: 10px;
	right: 10px;
	z-index: 999999;
}

.callnum a {
	color: #ffffff;
	font-size: 26px;
	display: inline-block;
	text-decoration: none;
	float: right;
	cursor: pointer;
}

.callnum img {
	vertical-align: middle;
}

#udeskIm, #udeskCall {
	display: block;
}

.callnum {
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

.zhi {
	padding: 5;
	position: absolute;
	margin-left: 4px;
	padding-top: 8px;
}

/* #box span {
	border-left: 3px solid #12bce1;
    margin-left: 20px;
    padding-left: 16px;
}  */
#qrcode {
	margin-left: 71px;
	position: relative;
	top: 6px;
	left: -99px;
}
</style>
</head>


<body>
	<div class="container">
		<%@include file="/qiantai_page/top2.jsp"%>
		<ul class="bread_crumbs">
			<li>您的位置：</li>
			<li><a href="${ctx}/first.do">首页</a> <i
				class="fa fa-angle-right"></i></li>
			<li><a href="#">证书查询</a></li>
		</ul>

		<div class="chaxun">
			<div class="xuefen fl">
				<h3 class="main_title">
					<span>远程继教学分查询</span>
				</h3>
				<!--   <div class="content cx1"> -->
				<div class="main_cont cx1" style="margin-bottom: 20px;">
					<div class="xf_form">
						<div class="input_div">
							<p style="margin: 20px 0;">
								<label>身份证号:</label> <input type="text" name="user_id"
									id="idcard" class="max_w user_id" size="50"
									style="padding: 1px 0 1px 6px;" placeholder="请输入身份证号">
							</p>
							<div class="cp" style="color: red; display: none;">*</div>
						</div>

						<div class="input_div">
							<p style="margin: 20px 0;">
								<label>学习时间:</label> <select name="start_year" id="begintime"
									style="background: #fff; height: 30px; text-align: center; padding: 0 40px;">
									<option value="2017" selected="selected">2017</option>
									<option value="2016">2016</option>
									<option value="2015">2015</option>
								</select> &nbsp;至&nbsp; <select name="start_year" id="endtime"
									style="background: #fff; height: 30px; text-align: center; padding: 0 40px;">
									<option value="2017" selected="selected">2017</option>
									<option value="2016">2016</option>
									<option value="2015">2015</option>
								</select>
							</p>
						</div>

					</div>
					<div class="input_div xf_cx" style="margin-top: 50px;">
						<label>&nbsp;</label>
						<button type="button" name="comment_submit"
							class="btn btn_blue btn_cert_search">查询</button>
					</div>
					<div class="tips_cont"></div>

				</div>
				<div class="jieguobiao">
					<div class="jieguo cert_result" style="display: none">
						<h3>
							<span>远程继教学分查询结果</span>
						</h3>
						<table style="margin-top: 0">
							<caption id="box"
								style="text-align: left; margin-left: 12px; padding-bottom: 20px;">

							</caption>
							<thead>
								<tr id="tr">
									<td>序号</td>
									<td>年度</td>
									<td>真实姓名</td>
									<td>项目名称</td>
									<td>学分类型</td>
									<td>授予学分</td>
									<td>申请时间</td>
									<td>查看证书</td>
								</tr>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
					</div>
				</div>

				<!-- </div> -->
			</div>
			<div class="zhengshu fl">
				<p>
					<span>专项能力证书查询</span>
				</p>
				<a href=""> <img src="${ctx}/qiantai_page/img/sous.png">
				</a>
			</div>
			<div class="clearfix"></div>

		</div>
		<div style="margin-top: 300px;">
			<%@include file="/qiantai_page/bottom.jsp"%>
		</div>
		<input type="hidden" id="lastUpdateDate" value="" />
	</div>
	<div class="popup cert">
		<div class="mask"></div>
		<div class="popup_cont cert"
			style="width: 800px; position: relative; top: 450px;">

			<div class="cont text_center">

				<div id="createImg"></div>



			</div>
			<div class="foot">
				<button name="no" type="button" class="btn btn_blue">返回</button>
			</div>
		</div>
	</div>

	<script>
		$(function() {

			$(".btn_cert_search").click(function() {
				if ($("#begintime").val() > $("#endtime").val()) {
					alert("开始时间大于结束时间！");
					return false;
				} else if ($(".user_id").val() == "") {
					// $(".tips_cont").show().html("<p class='warning'>未查询到此学员学分证书的相关记录！</p>");
					$(".cert_result").hide();
					alert("请输入身份证号！");
					return false;
				} else if (!checkIdCard()) {
					alert("身份证号不正确，请重新输入！");
					return false;
				} else {
					searchinfo();
					$(".cert_result").show();
					$(".tips_cont").hide();
				}
			});

			function searchinfo() {
				$("#tbody").empty();
				$("#box").empty();
				var url = "${ctx}/myStudy/DiplomaAction.do?method=QueryDiplomaByIdCard&idCard="
						+ $(".user_id").val()
						+ "&begintime="
						+ $("#begintime").val()
						+ "&endtime="
						+ $("#endtime").val()
						+ "&ITEM_NUMBER="
						+ $("#ITEM_NUMBER").val();
				$
						.ajax({
							url : url,
							type : 'POST',
							dataType : 'text',
							async : false,
							success : function(data) {
								var obj = eval('(' + data + ')');

								if (obj.code == 1) {
									$("#box").text("未查询到此学员学分证书的相关记录！");
									$("#tr").hide();

									return false;
								}
								var html = "";
								$
										.each(
												obj.list,
												function(index, o) {
													index = index + 1;
													html += "<tr>" + "<td>"
															+ index
															+ "</td>"
															+ "<td>"
															+ o.apply_time
																	.substr(0,
																			4)
															+ "</td>"
															+ "<td>"
															+ o.system_user_name
															+ "</td>"
															+ "<td>"
															+ o.item_name
															+ "</td>"
															+ "<td>"
															+ o.item_grade
															+ "</td>"
															+ "<td>"
															+ o.item_score
															+ "</td>"
															+ "<td>"
															+ o.apply_time
															+ "</td>"
															+
															// "<td>" +"<button name=no type=button class=btn btn_blue btn_credit>返回</button>"+"</td>"+
															"<td>"
															+ "<button style='background-color:#fff;border:none;' class=text_right onclick=findscore('"
															+ o.system_user_id
															+ "','"
															+ o.diploma_number
															+ "','"
															+ o.item_score
															+ "','"
															+ o.item_number
															+ "','1') ><a href=javascript:void(0); class=btn_credit>查看</a></button>"
															+ "</td>" + "</tr>"
												});
								$("#tbody").append(html);

								$("#box")
										.append(
												"<div class=jieg_info style='padding-left:0;'>"
														+ "<p style='overflow:hidden;padding-left:0;line-height:36px;'>"
														+ "<span class= jgwd>姓名："
														+ obj.user.realName
														+ "</span>"
														+ "<span>身份证号："
														+ obj.user.certificateNo
														+ "</span>"
														+ "</p>"
														+ "<p style='overflow:hidden;padding-left:0;line-height:36px;'>"
														+ "<span class=jgwd>职务类型："
														+ obj.user.workUnit
														+ "</span>"
														+ "<span>职称："
														+ obj.item.profTitle
														+ "</span>"
														+ "</p>"
														+ "<p style='overflow:hidden;padding-left:0;line-height:36px;'>"
														+ "<span class=jgwd>已获得总学分：<em style='font-size:16px;color: #ff7800;font-style: normal;'>"
														+ obj.user.email
														+ "分</em></span>"
														+ "</p>" + "</div>"

										);
							},
						});
			}

		});

		//格式化日期
		function toDate(str) {
			var arr = new Array();
			if (str.length > 0) {
				arr = str.split("-");
				return arr[0] + "&nbsp;年" + arr[1] + "&nbsp;月"
						+ arr[2].substring(0, 2) + "&nbsp;日";
			} else {
				return "&nbsp;年&nbsp;月&nbsp;日";
			}
		}

		//查看学分及证书
		function findscore(userid, code, score, cvSetId, title) {
			if (title == 1) {
				$("#sctitle").html("欢迎查看学分");
			}

			$("button[name=no]").click(function() {
				$(".popup").css({
					display : "none"
				})
			});

			$(".btn_credit").on("click", function() {
				$(".popup").css({
					display : "block"
				})
			})

			$("#cvSetId_hidden").val(cvSetId);
			$("#createImg").empty();
			var url = "${ctx}/myStudy/DiplomaAction.do?method=find&system_user_id="
					+ userid
					+ "+&diploma_number="
					+ code
					+ "&item_number="
					+ cvSetId;
			$
					.ajax({
						url : url,
						type : 'POST',
						dataType : 'text',
						async : false,
						success : function(data) {
							var obj = eval('(' + data + ')');
							if (obj.item_grade == "undefined") {
								obj.item_grade = "全部";
							}
							var lastUpdateDate = $("#lastUpdateDate").val();
							$("#createImg")
									.append(
											"<div class='center'>"
													+ "<div class='center1'>"
													+ " <h1 class='h1'>学分证书</h1>"
													+ " <p class='p'>"
													+ " 姓名<span style='padding:0px 10px 0px 10px;'>"
													+ obj.system_user_name
													+ "</span>身份证号<span style='padding:0px 10px 0px 10px;'>"
													+ obj.id_number
													+ "</span>于<span style='padding:0px 10px 0px 10px;'>"
													+ toDate(obj.begin_time)
													+ "</span>"
													+ "至<span style='padding:0px 10px 0px 10px;'>"
													+ toDate(obj.end_time)
													+ "</span>参加继续医学教育项目（面授&nbsp;<input type='checkbox' id='ms' />，"
													+ "远程教育&nbsp;<input type='checkbox' id='yc'  />，混合&nbsp; <input type='checkbox' id='hh'  />）[项目编号：<span style='padding:0px 10px 0px 10px;'>"
													+ obj.item_number
													+ "</span>][项目名称：<span style='padding:0px 10px 0px 10px;'>"
													+ obj.item_name
													+ "</span>]。经考核合格，特授予&nbsp;"
													+ obj.item_grade
													+ ""
													+ "<span style='padding:0px 10px 0px 10px;'></span> 继续医学教育学分<span style='padding:0px 10px 0px 10px'>"
													+ score
													+ "</span>分。"
													+ "</p>"
													+ "<div class='zhengshu'>"
													+ "<p style='float:left;'>证书编号：<span>"
													+ obj.diploma_number
													+ "</span></p>"
													+ "<p style='float:right;'>国家卫生计生委能力建设和继续教育中心</p>"
													+ "</div>"
													+ "<div style='clear:both;'></div>"
													+ "<div class='zhengshu'>"
													+ "	<p style='float:left;width:70px;height:70px;margin-left:50px;'></p>"
													+ "	<p style='float:right;padding-top:20px;' ><span style='padding:0px 20px 0px 20px;'>"
													+ toDate(obj.apply_time)
													+ "</span></p>"
													+ "<div id ='qrcode'></div>"
													+ "<span class='bj'></span>"
													+ "</div>"
													+ "</div>"
													+ "</div>"

									);
							printURL = obj.diploma_number;
							var qrcode = new QRCode(document
									.getElementById("qrcode"), {

								width : 90,//设置宽高
								height : 90
							});

							qrcode
									.makeCode(basepath
											+ "/myStudy/DiplomaAction.do?method=findT&system_user_id="
											+ userid + "&diploma_number="
											+ obj.diploma_number
											+ "&lastUpdateDate="
											+ lastUpdateDate + "&cvSetId="
											+ cvSetId);

							if (obj.item_type = "远程") {
								$("#yc").attr("checked", "checked");

							} else if (obj.item_type = "面授") {
								$("#ms").attr("checked", "checked");
							} else {
								$("#hh").attr("checked", "checked");
							}
						},
					});
		}

		$(".btn_cert_search2").click(
				function() {
					if ($("#begintime").val() > $("#endtime").val()) {
						alert("开始时间大于结束时间！");
						return false;
					} else if ($(".user_id").val() == "") {
						// $(".tips_cont").show().html("<p class='warning'>未查询到此学员学分证书的相关记录！</p>");
						$(".cert_result").hide();
						alert("请输入身份证号！");
						return false;
					} else if (!checkIdCard()) {
						alert("身份证号不正确，请重新输入！");
						return false;
					} else {
						window.location.href = "list.jsp?CERTIFICATE_NO="
								+ $(".user_id").val()
					}
				});

		var url = $("#url").val();

		function viewSubject(propId) {
			window.location.href = "http://www.ncme.org.cn/ProjectList.do?xueke="
					+ propId;
		}
		function searchProject(mode) {
			if (mode == 1) {
				window.location.href = "http://www.ncme.org.cn/ProjectList.do?sign="
						+ encodeURI("指南解读");
			}
			if (mode == 2) {
				window.location.href = "http://www.ncme.org.cn/caseList.do";
			}
			if (mode == 3) {
				window.location.href = "http://www.ncme.org.cn/liveList.do";
			}
			if (mode == 4) {
				window.location.href = "http://www.ncme.org.cn/vrList.do";
			}
		}
		function postView(mode, id) {
			var e = "http://www.ncme.org.cn/postView.do?mode=" + mode + "&id="
					+ id;
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
			var e = "http://www.ncme.org.cn/postList.do?mode=" + mode;
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
			location.href = "http://www.ncme.org.cn/entityManage/entityView.do?id="
					+ id;
		}

		(function(a, h, c, b, f, g) {
			a["UdeskApiObject"] = f;
			a[f] = a[f] || function() {
				(a[f].d = a[f].d || []).push(arguments)
			};
			g = h.createElement(c);
			g.async = 1;
			g.src = b;
			c = h.getElementsByTagName(c)[0];
			c.parentNode.insertBefore(g, c)
		})(window, document, "script",
				"http://ncme.udesk.cn/im_client/js/udeskApi.js?1483061109688",
				"ud");
		ud({
			"code" : "1a9dg2j6",
			"link" : "http://ncme.udesk.cn/im_client?web_plugin_id=23375",
			"targetSelector" : "#udeskIm"
		});
		$(function() {
			$('#udeskCall').click(function() {
				$('.callnum').css('display', 'block');
			});
			$('.callnum a').click(function() {
				$(this).parent().css('display', 'none');
			});
		});
	</script>
	<script>
		function projectList_sign(str) {
			window.location.href = "http://www.ncme.org.cn/ProjectList.do?sign="
					+ encodeURI(str);
		}
		function projectList_xueke(str) {
			window.location.href = "http://www.ncme.org.cn/ProjectList.do?xueke="
					+ encodeURI(str);
		}

		var url = window.location.href;

		if (url.indexOf("%E5%85%AC%E9%9C%80%E7%A7%91%E7%9B%AE") > -1) {
			$("#menu2").addClass("active");
		}
		if (url.indexOf("%E5%85%A8%E7%A7%91%E5%8C%BB%E5%AD%A6") > -1) {
			$("#menu3").addClass("active");
		}
		if (url.indexOf("Ability") > -1) {
			$("#menu4").addClass("active");
		}
		if (url.indexOf("teacherManage") > -1) {
			$("#menu5").addClass("active");
		}
		if (url.indexOf("Org") > -1) {
			$("#menu6").addClass("active");
		}
		if (url.indexOf("ExpertGroup") > -1) {
			$("#menu7").addClass("active");
		}
		if (url.indexOf("edu_man") > -1) {
			$("#menu8").addClass("active");
		}
		if (url.indexOf("CertificatQeuery") > -1) {
			$("#menu9").addClass("active");
		}
		if (url.indexOf("courseList") > -1) {
			$("#menu10").addClass("active");
		}
		if (url.indexOf("haiWaiShiYe") > -1) {
			$("#menu11").addClass("active");
		}

		$(".login_btn").click(function() {

			location.reload();

		});

		function barHeight() {
			$(".header,header_main").css("height", "61px");
			$(".head_cont").css("height", "40px");
			$(".menu_bar").css("position", "absolute");
			$(".menu_bar").css("margin-left", "85px");
		};

		// 单击查询按钮
		$("#search_button").click(
				function() {

					window.location.href = "${ctx}/searchAction.do?search="
							+ encodeURI(encodeURI($("#search_input").val()));
				})

		function checkIdCard() {
			var idCard = $("#idcard").val();
			var $thi = $("#idcard");
			if ($thi.val().length != 18) {
				$thi.next().text("*身份证号必须为18位的");
				$thi.next().css("color", "red");
				$thi.next().show();
				return false;
			} else if (IdentityCodeValidRegister($("#idcard").val()) == false) {
				//校验身份证号码合法性
				$thi.next().text("*身份证号不正确");
				$thi.next().css("color", "red");
				$thi.next().show();
				return false;
			} else {
				$thi.next().text("*该证件号可以用");
				$thi.next().css("color", "green");
				$thi.next().show();
				return true;
			}
		}
	</script>
</body>
</html>
