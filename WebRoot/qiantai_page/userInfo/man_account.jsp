<!-- 
	@auther: Alisa
	@date: 2017-01-11
 -->
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
    	input:-webkit-autofill,
    	autofill{
    	 	background-color:white;
    	}
    </style>
</head>
<script src="${ctx}/qiantai_page/js/checkimg.js"></script>
<script src="${ctx}/qiantai_page/js/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/qiantai_page/js/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/qiantai_page/js/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
<script src="${ctx}/qiantai_page/js/global/plugins/SystemMessage.js" type="text/javascript"></script>
<script src="${ctx}/qiantai_page/js/jquery.bigautocomplete.js"></script> 
<link href="${ctx}/qiantai_page/css/jquery.bigautocomplete.css" rel="stylesheet">

<body>

 <form method="post" action="${ctx}/userInfo/userAccount.do?method=update" id="regForm" name="regForm" enctype="multipart/form-data">
<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
    <div class="user_cover">
        <div class="content no_padding">
            <div class="user_ctrl" style="display:none"><!-- YHQ 2017-02-15 功能以后实现 -->
                <a href="javascript:void(0)">
                    <span>1</span>关注
                </a>
                <a href="javascript:void(0)">
                    <span>3</span>粉丝
                </a>
            </div>
            <div class="user_big_avatar">
            <c:if test="${userInfo.user_avatar !=  null}">
						               		<img src="${userInfo.user_avatar}" onerror="imgonload(this,'${userInfo.sex}');" id="userImage" name="userImage" >
						               	</c:if>
						               	<c:if test="${userInfo.user_avatar == null}">
						               <c:if test="${userInfo.sex == 1}">
						               <img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" >
						               </c:if>
						               <c:if test="${userInfo.sex == 2}">
						               <img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" >
						               </c:if>
						               </c:if>
               		
            </div>
            <div class="user_info_list">
                <h2>${userInfo.realName }</h2>
                <h3>
                	${userInfo.profTitle}
                </h3>
                <h3>
                	${userInfo.workUnit}
           	    </h3>
           	</div>
        </div>
    </div>
    <div class="content">
        <div class="left_nav">
           <ul>
                <li><a href="${ctx}/userInfo/userAbility.do">我的胜任力</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do">我的学习</a></li>
                <li><a href="${ctx}/userInfo/MyStudyManage.do?mode=tab2">我的学分</a></li>
                <li><a href="${ctx}/userInfo/userFav.do">我的收藏</a></li>
                <li><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li> 
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal&xueke=99031">我的学科</a></li>
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li class="active"><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>                
                
            </ul>
        </div>
        <div class="main_cont right">
            <div class="tabs">
                <ul class="tab_list">
                    <li class="tab active" id="tab1">基本资料</li>
                    <li class="tab" id="tab2">账号安全</li>
                </ul>
                <div class="tab_cont tab1">
                    <div class="info">修改个人资料（<em class="font_red">*</em> 为必填项）</div>
                    <div class="join_form inline_form all_width">
                        <div class="input_div">
                            <input type="hidden" name="certificate_type"  id="certificate_type"  value="${userInfo.certificateType}"/>
                            <input type="hidden" name="certificate_no_old"  id="certificate_no_old"  value="${userInfo.certificateNo}"/>
                            <label><em class="font_red">*</em> 身份证号</label>
                            <%-- <input type="text" name=""  id=""  size="50" value="${userInfo.certificateNo}" disabled> --%>
                            <input type="text" name="certificate_no"  id="certificate_no"  size="50" value="${userInfo.certificateNo}" onblur="checkIdCard();" <c:if test="${userInfo.certificateNo !=  null}">readOnly="true"</c:if>/>
                            <p class="cp" style="color: red; display: none;" >*</p>
                        </div>
                        <div class="input_div">
                            <label><em class="font_red">*</em> 真实姓名</label>
                            <input type="text" name="real_name" id="real_name"  value="${userInfo.realName}" <c:if test="${userInfo.realName !=  null}">readOnly="true"</c:if>/>
                        </div>
                        <div class="input_div">
                            <label>头像</label>
                            	<div class=" fileinput fileinput-new" data-provides="fileinput">
                            		<div class="avatar fileinput-preview fileinput-exists thumbnail">
		                            	<c:if test="${userInfo.user_avatar !=  null}">
						               		<img src="${userInfo.user_avatar}" onerror="imgonload(this,'${userInfo.sex}');" id="userImage" name="userImage" >
						               	</c:if>
						               	<c:if test="${userInfo.user_avatar == null}">
						               <c:if test="${userInfo.sex == 1}">
						               <img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" >
						               </c:if>
						               <c:if test="${userInfo.sex == 2}">
						               <img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" >
						               </c:if>
						               </c:if>
						           </div>
						           <input type="file"  id="selImage" name="selImage" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
				               	</div>
                            <!-- <input type="file" id="selImage" name="selImage"> -->
                        </div>
                        <div class="input_div">
                            <label><em class="font_red">*</em> 用户名</label>
                            <input type="text" name="account_name" id="account_name"  value="${userInfo.accountName}">
                        </div>
                        <div class="input_div">
                            <label><em class="font_red">*</em> 性别</label>
                            <select name="sex" id="sex">
                            	<option value="1" <c:if test="${userInfo.sex == 1 }">selected="selected"</c:if>>男</option>
                            	<option value="2" <c:if test="${userInfo.sex == 2 }">selected="selected"</c:if>>女</option>
                            </select>
                        </div>
                        <div class="input_div">
                            <label><em class="font_red">*</em> 单位名称</label>
                            <select id="region1" name="region1">
                                <c:forEach items="${region1list}" var="region">                                	
										<option value="${region.prop_val_id}" <c:if test="${region.prop_val_id == userConfig.userProvinceId}"> selected</c:if>>${region.name}</option>
							</c:forEach>
                            </select>
                            <select id="region2" name="region2">
                            	<c:forEach items="${region2}" var="region2">   
                            		<option value="${region2.prop_val_id}" <c:if test="${region2.prop_val_id == userConfig.userCityId}"> selected</c:if>>${region2.name}</option>
                               </c:forEach>
                            </select>
                            <select id="rspropid" name="rspropid">
                                <c:forEach items="${region3}" var="region3">   
                            		<option value="${region3.id}" <c:if test="${region3.prop_val_id == userConfig.userCountiesId}"> selected</c:if>>${region3.name}</option>
                                </c:forEach>
                            </select>
                            <input name="hospital_address" id="hospital_address" type="hidden" value="-9"/>
                            
                            <c:if test="${hospital != null}">
	                            <c:forEach items="${hospital}" var="hospit">
		                            <c:if test="${hospit.id == userInfo.work_unit_id}">
			                     		<input type="text" id="tt" style="margin: 10px 108px 0px;width:300px;" value= "${hospit.name}"  autocomplete="off"/>
			                     	</c:if>
		                     	</c:forEach>
	                     	</c:if>
	                     	<c:if test="${hospital == null}">
			                    <input type="text" id="tt" style="margin: 10px 108px 0px;width:300px;" value= "${userInfo.otherHospitalName}" autocomplete="off" />
	                     	</c:if>
	                     	<input name="other_hospital_name" id="other_hospital_name" type="text" style="margin: 10px -90px 0px;width:300px;display:none;" />
                            <!-- ####################################################################### -->
                            <%-- <select style="margin: 10px 108px 0px" name="hospital_address" id="hospital_address">
                                <c:forEach items="${hospital}" var="hospit">   
                            		<option value="${hospit.id}" <c:if test="${hospit.id == userInfo.work_unit_id}"> selected</c:if>>
                   						<c:if test="${fn:length(hospit.name)>15}">
					                          ${fn:substring(hospit.name,0,15)}...
					                    </c:if>
					                    <c:if test="${fn:length(hospit.name)<=15}">
					                         ${hospit.name}
					                    </c:if>
                            		</option>
                                </c:forEach>
                            	<option value="-2">其它</option>
                            </select> --%>
                        </div>
                        <!-- <div class="input_div"  id="hst_lvl" style="display:none">
                            <label><em class="font_red">*</em> 医院等级</label>
                            <select name="hospital_level" id="hospital_level">
                                <option value="-1">请选择</option>
                            </select>
                        </div> -->
                        <!-- <div class="input_div"  id="other_hospital" style="display:none">
                       		<label><em class="font_red">*</em> 医院名称</label>
                       		<input type="text" name="other_hospital_name" id="other_hospital_name" value=""/>
                       	</div> -->
                        <div class="input_div">
                            <label><em class="font_red">*</em> 医院地址</label>
                            <c:if test="${hosAddress != null}">
                            	<c:if test="${hosAddress != ''}">
                            		<input type="text" name="hospital_region" id="hospital_region"  placeholder="街道/门牌号" size="50" value=" ${hosAddress}"/>
                            	</c:if>
                            </c:if>
                            <c:if test="${hosAddress == null || hosAddress == ''}">
                            	<c:if test="${hospitalAddress != null && hospitalAddress != ''}">
                            		<input type="text" name="hospital_region" id="hospital_region"  placeholder="街道/门牌号" size="50" value=" ${hospitalAddress}"/>
                            	</c:if>
                            </c:if>
                            <c:if test="${hosAddress == null and hospitalAddress == null}">
                            	<input type="text" name="hospital_region" id="hospital_region"  placeholder="街道/门牌号" size="50" value=" ${hospitalAddress}"/>
                            </c:if>
                        </div>
                        <div class="input_div">
                            <label><em class="font_red">*</em> 职称类型</label>
                            <select name="work_type" id="work_type">
                                <c:forEach items="${jobList}" var="job">   
                            		<option value="${job.code}" <c:if test="${job.code == workExtType}">  selected</c:if>>${job.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input_div">
                            <label><em class="font_red">*</em> 您的职称</label>
                            <select name="work_unit" id="work_unit"><option value="-1">其它</option>
                                <c:forEach items="${myJobList}" var="myjob">   
                            		<option value="${myjob.id}" <c:if test="${myjob.id == userInfo.job_Id}">  selected</c:if>>${myjob.name}</option>
                                </c:forEach>  
                            </select>
                        </div>
                        <div class="input_div">
                            <label>执业医师号</label>
                            <input type="text" name="work_id" id="work_id"  size="50" value="${userInfo.health_certificate}">
                            <input type="hidden" id="oldwork_id" value="${userInfo.health_certificate}"/>
                        </div>
                        <div class="input_div">
                            <label><em class="font_red">*</em> 您的学科</label>
                            <select name="xueke1" id="xueke1">
                            	<c:forEach items="${xueke1}" var="xue">   
                            		<c:if test="${xue.id == levelOne}"><option value="${xue.id}" selected>${xue.name}</option></c:if>
                                </c:forEach>                                
	                        </select>
	                        <select name="xueke2" id="xueke2">
	                            <c:forEach items="${xueke2}" var="xue2">   
                            		<option value="${xue2.id}" <c:if test="${xue2.id == levelTwo}">selected</c:if>>${xue2.name}</option>
                                </c:forEach>
	                        </select>
	                        <select name="xueke3" id="xueke3">
	                            <c:forEach items="${xueke3}" var="xue3">   
                            		<option value="${xue3.id}" <c:if test="${xue3.id == userInfo.prop_Id}">selected</c:if>>${xue3.name}</option>
                                </c:forEach>
	                        </select>
                        </div>
                        <div class="input_div">
                            <label>您的学历</label>
                            <select name="grade" id="grade">
                                <option value="0" >请选择</option>
                                <option value="1" <c:if test="${userInfo.education eq 1}"> selected</c:if>>高中</option>
                                <option value="2" <c:if test="${userInfo.education eq 2}"> selected</c:if>>中专</option>
                                <option value="3" <c:if test="${userInfo.education eq 3}"> selected</c:if>>大专</option>
                                <option value="4" <c:if test="${userInfo.education eq 4}"> selected</c:if>>本科</option>
                                <option value="5" <c:if test="${userInfo.education eq 5}"> selected</c:if>>硕士</option>
                                <option value="6" <c:if test="${userInfo.education eq 6}"> selected</c:if>>博士</option>
                                <option value="7" <c:if test="${userInfo.education eq 7}"> selected</c:if>>博士后</option>
                                <option value="8" <c:if test="${userInfo.education eq 8}"> selected</c:if>>其它</option>
                            </select>
                        </div>
                        <div class="input_div">
                            <label>&nbsp;</label>
                            <button type="button" name="submit2" class="btn btn_orange btn_block float_left" onclick="javascript:Change();">确定保存</button>
                        </div>
                    </div>
                </div>
                <div class="tab_cont tab2" style="display: none">
                    <ul class="info_list">
                        <li><span style="cursor:pointer" class="float_right btn_edit_pwd"><i class="fa fa-edit font_s"></i> 修改</span><i class="fa fa-key"></i> <strong>登录密码：</strong>******</li>
                        <li><span style="cursor:pointer" class="float_right btn_edit_mobile"><i class="fa fa-edit font_s"></i> 修改</span><i class="fa fa-mobile"></i> <strong>手机号码：</strong><span id = "myMobilePhone">${userInfo.mobilPhone}</span></li>
                        <li><span style="cursor:pointer" class="float_right btn_edit_email"><i class="fa fa-edit font_s"></i> 修改</span><i class="fa fa-envelope"></i> <strong>邮箱：</strong><span id = "myEmail">${userInfo.email}</span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="popup edit_pwd">
	    <div class="mask"></div>
	    <div class="popup_cont normal">
	        <h2><span class="float_right close"><i class="fa fa-times"></i> </span> 修改密码</h2>
	        <div class="join_form">
	            <div class="input_div">
	                <label>原密码</label>
	                <input type="password" id="curpassword"/>
	            </div>
	            <div class="input_div">
	                <label>新密码</label>
	                <input type="password" id="newpassword"/>
	            </div>
	            <div class="input_div">
	                <label>确认密码</label>
	                <input type="password" id="retypassword"/>
	            </div>
	        </div>
	        <div class="foot">
	            <button class="btn btn_blue btn_password_save" type="button">保存</button>
	        </div>
	    </div>
	</div>
	<div class="popup edit_mobile">
	    <div class="mask"></div>
	    <div class="popup_cont normal">
	        <h2><span class="float_right close"><i class="fa fa-times"></i> </span> 修改手机号码</h2>
	        <div class="join_form">
	            <div class="input_div">
	            	<label>手机号码</label>
	            	<input type="text" id="newmobile" value = "${userInfo.mobilPhone}"  onblur="checkPhone();"/>
	            	<p class="infor" style="display: none;"></p>
	            	
	            </div>
	            <div class="input_div">
                        <label>短信验证码</label>
                        <input type="text" name="verify_code" id="verify_code" style="min-width:11.7em;"/>
                        <button type="button" name="verify_btn" id="verify_btn" onclick="daojishi01();" style="border-radius:7px;">获取验证码</button>
                        <input type="hidden" name="yzm" id="yzm"/>
                    </div>
	            
	        </div>
	        <div class="foot">
	            <button class="btn btn_blue btn_mobile_save" type="button">保存</button>
	        </div>
	    </div>
	</div>
	

	
	
    <div class="popup edit_email">
	    <div class="mask"></div>
	    <div class="popup_cont normal">
	        <h2><span class="float_right close"><i class="fa fa-times"></i> </span> 修改邮箱</h2>
	        <div class="join_form">
	            <div class="input_div">
	            	<label>邮箱</label>
	            	<input type="text" id="newemail" value = "${userInfo.email}" onblur="checkEmail();"/>
	            	<input type="hidden" id="oldemail"  value="${userInfo.email}" />
	            </div>
	        </div>
	        <div class="foot">
	            <button class="btn btn_blue btn_email_save" type="button">保存</button>
	        </div>
	    </div>
	   
	</div>
<%@include file="/qiantai_page/bottom.jsp"%>
</div>
</form>

	
	
</body>
<script type="text/javascript" src="${ctx}/qiantai_page/js/validate.js"></script>
<script language="javascript">

	var AutoData = new Array();
	$("#tt").bigAutocomplete(
		{width:300,
		 data:AutoData}
		);
	function addData(names,values){
   		var obj = { "title": names,"content":values}
    	return obj;
	}
	function updateHospitalList(obj, list, _default){
		if (list!=null){
			AutoData.splice(0);
			$(list).each(function(key, value){
				AutoData.push(addData(value.name,value.id));
			});
		}
	}
//#########################################
var userId = "${userInfo.userId}";
var error = "${errorMsg}";
if(error != null && error != "")
{
	alert(error);
}
$(function(){

	//根据证件类型显示出正确的证件类型标签
	var certificate_type = $("#certificate_type").val();
	if(certificate_type == 2){
		$(".input_div:eq(0) > label").html("<em class='font_red'>*</em>军官证号");
	}else if(certificate_type == 3){
		$(".input_div:eq(0) > label").html("<em class='font_red'>*</em>港澳/台通行证号");
	}else if(certificate_type == 4){
		$(".input_div:eq(0) > label").html("<em class='font_red'>*</em>护照号");
	}else{
		$(".input_div:eq(0) > label").html("<em class='font_red'>*</em>身份证号");
	}
	//######################################
	//自动根据地址装载单位列表
	var reg1 = $("#region1").val();
	var reg2 = $("#region2").val();
	var reg3 = $("#rspropid").val();
	//alert(reg1);
	//alert(reg2);
	//alert(reg3);
	if(reg1 != null && reg1 != ""){
		var url = "${ctx}/propManage/getPropListAjax.do?type=1&id=" + reg1;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateHospitalList($('#hospital_address'), result.hospList);
			   };
		    }
		});
	}
	if(reg2 != null && reg2 != ""){
		var url = "${ctx}/propManage/getPropListAjax.do?id=" + reg2+"&parentId=" + $("#region1").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateHospitalList($('#hospital_address'), result.hospList);
			   };
		    }
		});
	}
	if(reg3 != null && reg3 != ""){
		var url = "${ctx}/examManage/getHospitalListAjax.do?parentId="+$("#rspropid").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updateHospitalList($('#hospital_address'), result.list);
			   };
		    }
		});
	}
	//######################################
	$("#selImage").change(function(){
		var path = location.host;
	});
	$(".btn_edit_pwd").click(function () {
            $(".edit_pwd").show();
        });
        $(".btn_edit_mobile").click(function () {
            $(".edit_mobile").show();
        });
        $(".btn_edit_email").click(function () {
            $(".edit_email").show();
        });
        $(".btn_save, .close").click(function () {
            $(".popup").hide();
        });
        
	$("#tab_btn1").click(function(){
		$("#tab1").prop("class", "tab");
		$("#tab2").prop("class", "tab active");
		
		$("#tab_cont1").hide();
		$("#tab_cont2").show();
	});
	
	
	$("#certificate_no").focusout(function() {
		/*var strICDCard = $("#certificate_no").val();
		if(!isValidateCardNumber(parseInt(strICDCard))) {
			alert("身份证号码不正确");
			return false;
		}*/
	});
	
	$("#mobile_phone").focusout(function(){
		/* var strMobile = $("#mobile_phone").val();
		if(!isValidateMobile(strMobile))
			alert("Mobile Error."); */
	});
	
	$("#mobile_phone").focusout(function(){
		/* var strMobile = $("#mobile_phone").val();
		if(!isValidateMobile(strMobile))
			alert("Mobile Error."); */
	});
		
	$('#region1').change(function(){
		$("#region2").val(0);
		$("#rspropid").val(0);
		$("#hospital_address").val(0);
		$("#hospital_level").val(0);
		$('#tt').val("");
		
		$("#other_hospital_name").val("");
		var url = "${ctx}/propManage/getPropListAjax.do?area=noPage&type=1&id=" + $(this).val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList($('#region2'), result);
			   		updatePropList($('#rspropid'), null);
			   		updateHospitalList($('#hospital_address'), result.hospList);
			   };
		    }
		});
	});
		
	$('#region2').change(function(){
		$("#rspropid").val(0);
		$("#hospital_address").val(0);
		$("#hospital_level").val(0);
		$("#other_hospital_name").val("");
		$('#tt').val("");
		var url = "${ctx}/propManage/getPropListAjax.do?area=noPage&id=" + $(this).val()+"&parentId=" + $("#region1").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
			   if(result != ''){
			   		updatePropList($('#rspropid'), result);
			   		updateHospitalList($('#hospital_address'), result.hospList);
			   };
		    }
		});
	});
	
	$("#rspropid").change(function(){
		$("#hospital_address").val(0);
		$("#hospital_level").val(0);
		$("#other_hospital_name").val("");
		$('#tt').val("");
		var url = "${ctx}/examManage/getHospitalListAjax.do?parentId="+$("#rspropid").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){
		   
			   if(result != ''){
			   		updateHospitalList($('#hospital_address'), result.list);
			   };
		    }
		});
	});
	$('#tt').change(function(){
		var ttval = $('#tt').val();
		$("#hospital_address").val(0);
		$("#other_hospital_name").val(ttval);
	});
	
	$("#hospital_address").change(function(){
		$('#hospital_level').val(0);
		$("#other_hospital_name").val("");
		if($("#hospital_address").val() == -2) {
			$("#other_hospital").show();
			$("#tt").val("");
			//$("#hst_lvl").show();
			$("#hospital_region").val("");
			
			var url = "${ctx}/examManage/getHospitalLevelAjax.do?type=23";
		
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updateHospitalLevelList($('#hospital_level'), result);
				   };
			    }
			});
		} 
		else if ($("#hospital_address").val() != -1) {	
			$("#other_hospital").hide();
			$("#hst_lvl").hide();	
			var url = "${ctx}/examManage/getHospitalListAjax.do?method=getHospitalAddress&hospId="+$("#hospital_address").val();
		
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'text',
			    success: function(result){
				   $("#hospital_region").attr("placeholder",result+"街道门牌号");
			    }
			});
		} else {
			$("#other_hospital").hide();
			$("#hst_lvl").hide();
		}
	});
	
	function initXueke(idd) {
		var dd=idd;
		var url = "${ctx}/propManage/getPropListAjax.do?idd="+dd;
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateXueke($('#xueke1'), result);
			   		//修改联动一级学科
			   		var img_type=$("#work_type").val();
					$("#xueke1 option").attr("selected",false);
					$("#xueke1 option[img_type="+img_type+"]").attr("selected",true)
					
					$("#xueke2").val(0);
					$("#xueke3").val(0);
					var url = "${ctx}/propManage/getPropListAjax.do?id="+$("#xueke1").val();
					$.ajax({
					    url:url,
					    type: 'POST',
					    dataType: 'json',
					    success: function(result){   
						   if(result != ''){ 
						   		updateXueke($('#xueke2'), result);
						   		updateXueke($('#xueke3'), null);
						   };
					    }
					});	
			   };
		    }
		});	
	}
	
	$("#xueke1").change(function(){
		$("#xueke2").val(0);
		$("#xueke3").val(0);
		var url = "${ctx}/propManage/getPropListAjax.do?id="+$("#xueke1").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateXueke($('#xueke2'), result);
			   		updateXueke($('#xueke3'), null);
			   };
		    }
		});	
	});
	
	$("#xueke2").change(function(){
		$("#xueke3").val(0);
		var url = "${ctx}/propManage/getPropListAjax.do?id="+$("#xueke2").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateXueke($('#xueke3'), result);
			   };
		    }
		});	
	});
	
	$("#work_type").change(function(){
		
		//work_type改变后，先给xueke1赋值
		initXueke($("#work_type").val());
		
		$("#work_unit").val(0);
		var url = "${ctx}/propManage/propWorkUnit.do?ext_type="+$("#work_type").val();
		
		$.ajax({
		    url:url,
		    type: 'POST',
		    dataType: 'json',
		    success: function(result){   
			   if(result != ''){ 
			   		updateWorkUnit($('#work_unit'), result);
			   };
		    }
		});	
	});
});
function updateWorkUnit(obj, result, _default){
	var str="<option value='-1' selected>请选择</option>";
	if (result!=null)
	$(result.list).each(function(key, value){
		str += "<option value='"+value.id+"'>"+value.name+"</option>";
	});
	
	$(obj).html(str);
}
function updatePropList(obj, result, _default){
		
	var str = "<option value='-1' selected>请选择</option>";
	if (result!=null)
	$(result.list).each(function(key, value){
		str += "<option value='"+value.prop_val_id+"'>"+value.name+"</option>";
	});
	
	$(obj).html(str);
}

/* function updateHospitalList(obj, list, _default){
		
	var str = "<option value='-1' selected>请选择</option>";
	if (list!=null)
	$(list).each(function(key, value){
		str += "<option value='"+value.id+"'>"+value.name+"</option>";
	});
	str += "<option value='-2'>其它</option>";
	
	$(obj).html(str);
} */

function updateHospitalLevelList(obj, result, _default){
		
	var str = "<option value='-1' selected>请选择</option>";
	if (result!=null)
	$(result.list).each(function(key, value){
		str += "<option value='"+value.id+"'>"+value.name+"</option>";
	});
	
	$(obj).html(str);
}

function updateHospitalAddress(obj, result, _default) {
	var str="";
	if(result!=null) {
		var id = $("#hospital_address").val();
		$(result.list).each(function(key, value){
			if(id == value.propId)
				str = value.hospital_address;
		});
	}
	$(obj).val(str);
}

function updateXueke(obj, result, _default) {
	var str = "<option value='-1' selected>请选择</option>";
	
	if (result!=null)
	$(result.list).each(function(key, value){
		str += "<option value='"+value.id+"'>"+value.name+"</option>";
	});
	
	$(obj).html(str);
}

function isFullInputData() {
	if($("#certificate_type").val() == 0) {
		alert("请选择证件类型！");
		return false;
	}
	if($("#certificate_no").val() == "") {
		alert("请输入证件号码！");
		return false;
	}
	if(!checkIdCard()){
		alert("证件号码不正确，请重新输入！");
		return false;
	}
	if($("#real_name").val() == "") {
		alert("请输入真实姓名！");
		return false;
	}
	//判断上传文件类型
	var imageFile = $('#selImage').val();
	if (imageFile != '') {
		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|bmp)$/.test(imageFile)) {
			 alert("头像必须是.gif,jpeg,jpg,png,bmp中的一种");
			 return false;
		}
		 //判断上传文件大小
	    var size = $("#selImage").get(0).files[0].size / 1024;  
	    if(size > 10240){  
	        alert('头像大小不能超过10M');  
	        return false;  
	    }
	}
	
	if($("#account_name").val() == "") {
		alert("请输入用户名！");
		return false;
	}
	if($("#account_password").val() == "") {
		alert("请输入密码！");
		return false;
	}
	if($("#confirmpassword").val() == "") {
		alert("请确认密码！");
		return false;
	}
	if($("#confirmpassword").val() != $("#account_password").val()) {
		alert("请重新确认密码！");
		return false;
	}
	if($("#mobile_phone").val() == "") {
		alert("请输入手机号！");
		return false;
	}
/* 	if($("#email").val() == "") {
		alert("请输入邮箱地址！");
		return false;
	}
	var re = /^[_a-z0-9-]+([_a-z0-9-]+)*@/i;
	if ($('#email').val() !="" && !re.test($('#email').val())){
		alert("邮箱格式不对！");
		return false;
	} 	 */
	if($("#region1").val() == -1) {
		alert("请选择单位所属省！");
		return false;
	}
	if($("#region2").val() == -1) {
		alert("请选择单位所属市！");
		return false;
	}
	/* if($("#hospital_address").val() == -2){
		if($("#hospital_level").val() < 0){
			alert("请选择医院级别！");
			return false;
		}
		if($("#other_hospital_name").val() == ""){
			alert("请输入单位名称！");
			return false;
		}
	} */
	if($("#rspropid").val() == -1) {
		alert("请选择单位所属区县！");
		return false;
	}
	/* if($("#hospital_address").val() == -1) {
		alert("请选择医院！");
		return false;
	} */
	
	if($('#tt').val() == ""){
		alert("请输入单位名称！");
		return false;
	}
	/* if($("#hospital_address").val() == 0) {
		//var ttflag = true;
		var ttStr = $('#tt').val();
		var url = '${ctx}/userInfo/userAccount.do?method=checkHospitalName';
		$.ajax({
			url:url,
			data:{"tt":ttStr},
			type:'POST',
			dataType:'json',
			success:function(result){
				if(result != null){
					if(result.msg == "no"){
						$("#other_hospital_name").hide();
						alert("请正确填写单位名称或者选择其它选项！");
						return false;
					}
				}
			}
		});
		//return ttflag;
	} */
	if($("#hospital_address").val() == "其它" && 
		$("#other_hospital_name").val() == "") {
			alert("请输入单位名称！");
			return false;
	}
	if($("#hospital_region").val() == "") {
		alert("请输入医院地址！");
		return false;
	}
	if($("#work_type").val() == -1) {
		alert("请选择职称类型！");
		return false;
	}
	if($("#work_unit").val() == -1) {
		alert("请选择您的职称！");
		return false;
	}
	if($("#xueke1").val() == -1) {
		alert("请选择一级学科！");
		return false;
	}
	if($("#xueke2").val() == -1) {
		alert("请选择二级学科！");
		return false;
	}
	if($("#xueke3").val() == -1) {
		alert("请选择三级学科！");
		return false;
	}
	return true;
}

	$(".btn_password_save").click(function(){
		if($("#curpassword").val() != ""){
			if($("#newpassword").val() == ""){
				alert("请输入新的密码！");
				return false;
			}
			else{
				//判断密码格式
				var pattern = /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))[a-zA-Z0-9]{8,20}$/;
				if(!pattern.test($("#newpassword").val())){
					alert("密码为8-20位，须包含大写字母、小写字母、数字，不能使用空格！");
					return false;
				}
				if($("#newpassword").val() != $("#retypassword").val()){
					alert("您输入的密码不匹配,请重新输入！");
					return false;
				}
				else {
					if(confirm("您确定要修改密码吗？")){
						var url = action="${ctx}/userInfo/userAccount.do?method=updatePass";
						$.ajax({
							url:url,
							data:"curPassword="+$("#curpassword").val()+"&newPassword="+$("#newpassword").val(),
							type:'POST',
							dataType:'json',
							success:function(result){
								if(result != null){
									if(result.msg != null &&result.msg != "")
									{
										alert(result.msg);
									}
									else if(result.success_msg != null || result.success_msg != ""){
										
										alert(result.success_msg);
										//保存系统信息
										var userid ='${userInfo.userId}';  
										saveMessage(userid,"修改密码成功");
										$('.edit_pwd').hide();
										$('.edit_pwd').find('input').val('');
									}
								}
							},
						});
					}
					else return false;
				}
			}
		}
		else
		{
			alert("请输入原来的密码！");
			return false;
		}
	});
	$(".btn_email_save").click(function(){
		var email = $("#newemail").val();
		if(email != ""){
			if(confirm("您确定要修改邮箱地址吗？")){
				if($("#newemail").val() == "") {
					alert("请输入邮箱地址！");
					return false;
				}
				var re = /^([a-zA-Z0-9_-])+@([A-Za-z0-9]+[-.])+[A-Za-zd]{2,5}$/;
				if ($('#newemail').val() !="" && !re.test($('#newemail').val())){
					alert("邮箱格式不对！");
					return false;
				}
				var url = action="${ctx}/userInfo/userAccount.do?method=updateEmail";
				$.ajax({
					url:url,
					data:"newEmail="+$("#newemail").val(),
					type:'POST',
					dataType:'json',
					success:function(result){
						if(result != null){
							if(result.msg != null && result.msg != "")
							{
								alert(result.msg);
								var userid ='${userInfo.userId}';  
								saveMessage(userid,"修改邮箱成功");
								$("#myEmail").text($("#newemail").val());
								$('.edit_email').hide();
							}
						}
					},
				});
			}
		}
	});
	$(".btn_mobile_save").click(function(){
		checkPhone();
		// 验证验证码
	var flag = CheckVerify_code();
		
		if(flag){
			if(confirm("您确定要修改手机号码吗？")){
				if($("#newmobile").val() == "") {
					alert("请输入手机号码！");
					return false;
				}
				var re = /^[0-9]{11}$/;
				if ($('#newmobile').val() !="" && !re.test($('#newmobile').val())){
					alert("手机号必须为11位数字！");
					return false;
				}
				var url = action="${ctx}/userInfo/userAccount.do?method=updateMobile";
				$.ajax({
					url:url,
					data:"newMobile="+$("#newmobile").val(),
					type:'POST',
					dataType:'json',
					success:function(result){
						if(result != null){
							if(result.msg != null && result.msg != "")
							{
								alert(result.msg);
								// 保存系统消息
								var userid ='${userInfo.userId}';  
								saveMessage(userid,"手机号码修改成功");
								$("#myMobilePhone").text($("#newmobile").val());
								$('.edit_mobile').hide();
							}
						}
					},
				});
			}
		}
	
		
		

	});
	
	//Check the account name is existing and update user information.
	function isDuplicateAccount()
	{
		var url = action="${ctx}/userInfo/userAccount.do?method=isDuplicate";
		$.ajax({
			url:url,
			data:"userId = " + userId + "&accountName="+$("#account_name").val(),
			type:'POST',
			dataType:'text',
			success:function(result){
				if(result != null && result == "exist"){
					alert("姓名重复，请重新输入！");
					$("#account_name").focus();
					return true;
				}
				else
				{
					//update information.
					$(regForm).submit();
					return false;
				}
			},
		});
	}
	function Change(){
		if(isFullInputData())
		{
			//update user information.
			isDuplicateAccount();
		}
		else
		{
			return;
		}
		
	}
	

	
	/*
	 * @author  张建国
	 * @time    2017-01-09
	 * 说         明：获取服务端验证码信息 
	 */
	function geyYZM(){
		//获取用户填写的手机号码
		var phone = $("#newmobile").val();
		if(phone!=null && phone!=''){
			if(phone.length==11){
				$.ajax({
					type:'POST',
					url:'${ctx}/registerUser.do?method=getVerificationCode&mobile_phone='+phone,
					dataType:'json',
					success:function(data){
						var result = eval(data);
						if(result.message=='success'){
							//alert('验证码已发送，请耐心等待');
							$("#yzm").val(result.yzm);
						}else{
							alert('验证码发送失败.');
						}
					}
				});
			}else{
				alert("请填写正确的手机号码!");
			}
		}else{
			alert("请填写手机号码!");
		}
	}
	
	// 验证验证码是否正确

	function CheckVerify_code(){
		
		if($("#verify_code").val() == "") {
			alert("请输入验证码！");
			return false;
		}
		//判断是否获取验证码
		else if($("#yzm").val() == "") {
			alert("请先获取验证码！");
			return false;
		}
		//判断验证码是否正确
		else if($("#verify_code").val() != "" && $("#yzm").val() != "" && $("#verify_code").val()!= $("#yzm").val()) {
			alert("验证码输入错误！");
			return false;
		}else{
			
			return true;
		}
	}
	
	// 倒计时01
	function daojishi01(){
		
		var obj = $("#verify_btn");
		var timer = null;
		var n=60;
		if(n!=0){
			//发送验证码
			geyYZM();
		}
		timer =setInterval(function(){
			n--;
			obj.text(n+'s重新获取');
			obj.attr("disabled",true); 
			
			if(n==0){
				clearInterval(timer);
				obj.attr("disabled",false); 
				obj.text('获取验证码');
			}
		},1000);
	
		
	}
	

// 验证手机是否已经存在

function checkPhone(){
	if($("#newmobile").val()!=""){
		
		var phone = $("#newmobile").val();
		var re = /^[0-9]{11}$/;
		if ($('#newmobile').val() !="" && !re.test($('#newmobile').val())){
			$(".infor").css("color","red");
			$(".infor").show();
			$(".infor").text("手机号必须为11位数字！");
			return false;
		}
		//通过ajax检测手机号码是否为空
		$.ajax({
			type:'POST',
			url:'${ctx}/registerUser.do?method=checkMobile&mobile_phone='+phone,
			dataType:'json',
			async: false,
			success:function(data){
				var result = eval(data);
				if(result.message!='success'){
					$(".infor").css("color","red");
					$(".infor").show();
					$(".infor").text("该手机号已经存在");
					return false;
				}else{
					$(".infor").css("color","green");
					$(".infor").show();
					$(".infor").text("该手机号可以用");
				}
			}
		});
	}
}
/**
 * 检查证件号码是否重复以及是否合法
 */
function checkIdCard(){
	var idCard = $("#certificate_no").val();
	var $thi = $("#certificate_no");
	var cardType = $("#certificate_type").val();
	var idCard_old = $("#certificate_no_old").val();
	var filter_jg = /^[a-zA-Z0-9]{7,21}$/;
	var filter_ga = /^[a-zA-Z0-9]{5,21}$/;
	var filter_hz =  /^[a-zA-Z0-9]{3,21}$/;
	var res;
	//通过ajax检测证件号码是否为空
	if(idCard!=null && idCard!=''){
		if(idCard!=idCard_old){
			$.ajax({
				type:'POST',
				url:'${ctx}/registerUser.do?method=checkIdCard&idCard='+idCard,
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message!='success'){
						$thi.next().text("*该证件号码已被注册");
						$thi.next().css("color","red");
						$thi.next().show();
						$("#certificate_no").focus();
						res = false;
					}else if($("#certificate_type").val()==1 && $thi.val().length !=18){
						$thi.next().text("*身份证号必须为18位的");
						$thi.next().css("color","red");
						$thi.next().show();
						res = false;
					}else if($("#certificate_type").val() ==1  && IdentityCodeValidRegister($("#certificate_no").val())==false){
						//校验身份证号码合法性
						$thi.next().text("*身份证号不正确");
						$thi.next().css("color","red");
						$thi.next().show();
						res = false;
					}else if(cardType == 2 && !filter_jg.test(idCard)){//校验军官证号码合法性
						$thi.next().text("*军官证号码不正确");
						$thi.next().css("color","red");
						$thi.next().show();
						res = false;
					}else if(cardType == 3 && !filter_ga.test(idCard)){//校验港澳/台通行证号码合法性
						$thi.next().text("*港澳/台通行证号码不正确");
						$thi.next().css("color","red");
						$thi.next().show();
						res = false;
					}else if(cardType == 4 && !filter_hz.test(idCard)){//校验护照号码合法性
						$thi.next().text("*护照号码不正确");
						$thi.next().css("color","red");
						$thi.next().show();
						res = false;
					}else{
						$thi.next().text("*该证件号可以用");
						$thi.next().css("color","green");
						$thi.next().show();
						res = true;
					}
				}
			});
		}else{
			res = true;
		}
		return res;
	}else{
		$thi.next().text("*证件号码不可以为空");
		$thi.next().css("color","red");
		$thi.next().show();
	}
}

//验证邮箱是否已经存在
function checkEmail(){
	var email = $("#newemail").val();
	var oldemail = $("#oldemail").val();
	if($("#newemail").val()!=""){
		if(email != oldemail){
			var re = /^([a-zA-Z0-9_-])+@([A-Za-z0-9]+[-.])+[A-Za-zd]{2,5}$/;
			if ($('#newemail').val() !="" && !re.test($('#newemail').val())){
				alert("邮箱格式不对！");
				$("#newemail").val("");
				$("#newemail").focus();
				return false;
			}
			//通过ajax检测邮箱是否已经存在
			$.ajax({
				type:'POST',
				url:'${ctx}/registerUser.do?method=checkIsUnique&email='+email,
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message!='success'){
						alert("该邮箱已经存在，请重新输入！");
						$("#newemail").val("");
						$("#newemail").focus();
						return false;
					}
				}
			});
		}
		//return res;
	}
	
}
//验证执业医师号不能重复
$('#work_id').blur(function(){
	var code = $('#work_id').val();
	var oldCode = $('#oldwork_id').val();
	var check = /^[0-9]{15}$/.test(code);   
		if(code!="" && !check){
			alert("执业医师号为15位数字！");
			$('#work_id').val("");
			$('#work_id')[0].focus();
	  	return false;
		}
		if(code!="" && code!=oldCode){
			$.ajax({
			type:'POST',
			url:'${ctx}/registerUser.do?method=code&code='+code,
			dataType:'json',
			success:function(data){
				var result = eval(data);
				if(result.message=='yes'){
					alert("执业医师号已存在！");
					$('#work_id').val("");
	     			$('#work_id')[0].focus();
				  	return false;
				}
			}
		});
		}
});
</script>
</html>