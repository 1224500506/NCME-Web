<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html >
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
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">
    <script src="${ctx}/qiantai_page/js/jquery-1.7.2.min.js"></script>
    <script src="${ctx}/qiantai_page/js/jquery.mobile.custom.min.js"></script> 
    <script src="${ctx}/qiantai_page/js/jquery.bxslider.js"></script> 
    <script src="${ctx}/qiantai_page/js/iconfont.js"></script>
    <script src="${ctx}/qiantai_page/js/main.min.js"></script>    
    <script src="${ctx}/qiantai_page/js/global/plugins/SystemMessage.js" type="text/javascript"></script>
    <!-- 联想输入 -->
    
     <script src="${ctx}/qiantai_page/js/jquery.bigautocomplete.js"></script> 
     <link href="${ctx}/qiantai_page/css/jquery.bigautocomplete.css" rel="stylesheet">
    <style>
       .inputTip{
       	   color: #888888;
       	   float: left;
       	   margin-left: 20px;
       	   margin-top: 5px;
       }
    </style>
</head>
<body>
 <form method="post" action="${ctx}/registerUser.do?method=register" id="regForm" name="regForm">
<div class="container">
<%@include file="/qiantai_page/top2.jsp"%>
<div class="content no_padding">
        <div class="join_form inline_form clearfix" style="padding-top:50px;">
            <h2 class="main_title_2"><span class="float_right">已有账号？<a href="${ctx}/login.do" style="color:#12bce1;">去登录</a></span>欢迎注册中国继续医学教育网</h2>
            <p class="info"><i class="fa fa-info-circle"></i> 注意：按照政策、学习及学分审核要求，学习课程的推荐、学分证书的颁发和审核以个人真实姓名和证件号为准，为保证您能够及时准确获得课程推荐、获得学分，请您准确填写个人信息，避免重复注册！</p>
            <h3 class="main_title_2 font_m">填写注册项(<em class="font_red">*</em> 为必填项）</h3>
            <div class="tabs">
                <ul class="tab_list">
                    <li class="tab active" id="tab1">基本信息</li>
                    <li class="tab" id="tab2">工作信息</li>
                </ul>
                <div class="tab1 tab_cont" id="tab_cont1">
                    <div class="input_div">
                        <label><em class="font_red">*</em> 证件类型</label>
                        <select name="certificate_type" id="certificate_type" style="min-width:19em;">
                            <option value="0">请选择</option>
                            <option value="1">身份证</option>
                            <option value="2">军官证</option>
                            <option value="3">港澳/台通行证</option>
                            <option value="4">护照</option>
                        </select>
                    </div>
                    <div class="input_div">
                        <label><em class="font_red">*</em> 证件号码</label>
                        <input type="text" name="certificate_no" id="certificate_no" size="50" style="min-width:19em;width:296px;" onblur="checkIdCard();"/>
                        <p class="cp" style="color: red; display: none;float: left;margin-top: 5px;" >*</p>
                        <p class="inputTip">注册后不可修改</p>
                    </div>
                    <div class="input_div">
                        <label><em class="font_red">*</em> 真实姓名</label>
                        <input type="text" name="real_name" id="real_name"  style="min-width:21.4em;" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/>
                        <p class="inputTip">相关学习证书上都采用此姓名，请填写您的真实姓名，文字间不要使用空格</p>
                    </div>
                     <div class="input_div">
                        <label><em class="font_red">*</em> 用户名</label>
                        <input type="text" name="account_name" id="account_name"  style="min-width:21.4em;" onblur="checkthi(this,'用户名');"/>
                        <p class="cp" style="color: red; display: none;float: left;margin-top: 5px;" >*</p>
                         <p class="inputTip">4-18个字符，仅支持字母和数字</p>
                    </div>
                     <div class="input_div">
                        <label><em class="font_red">*</em> 单位名称</label>
                        <select class="fl att_bianji01" id="region1" name="region1">
							<option value="-1">请选择</option>
							<c:forEach items="${region1list}" var="region">
								<option value="${region.prop_val_id}">${region.name}</option>
							</c:forEach>
						</select>
						
                        <select class="fl att_bianji01" id="region2" name="region2">
							<option value="-1">请选择</option>
						</select>
                        
                        <select class="fl att_bianji01" id="rspropid" name="rspropid">
							<option value="-1">请选择</option>
						</select>
						<div style="clear:both;"></div>
						</br>
						<!--<div style="position:relative;height:30px;margin-left:110px;">
							<input  name="hospital_address" id="hospital_address" style="border-radius:7px;border:1px solid #666;width:23em;height:30px;padding:0 20px 0 10px;" class="bb"/>
							<div style="border-radius:7px;position:absolute;left:0px;padding:10px 20px 10px 10px;min-height:60px;z-index:999;width:25.5em;border:1px solid #666;background:#fff;"  class="aa">
								<i style="font-size:14px;color:#666;line-height:20px;text-algin:left;display:block;">frerfregergegre</i>
								<i style="font-size:14px;color:#666;line-height:20px;text-algin:left;display:block;">frerfregergegre</i>
								<i style="font-size:14px;color:#666;line-height:20px;text-algin:left;display:block;">frerfregergegre</i>
								<i style="font-size:14px;color:#666;line-height:20px;text-algin:left;display:block;">frerfregergegre</i>
								<i style="font-size:14px;color:#666;line-height:20px;text-algin:left;display:block;">frerfregergegre</i>
							</div> 
						</div>-->
						<!--  <select name="hospital_address" id="hospital_address"   style="margin-left:110px;">
	                            <option value="-1">请选择</option>
	                     </select> -->
	                     
	                     <input name="hospital_address" id="hospital_address" type="hidden" />
	                            <!-- 自动补全功能 -->
	                     <input type="text" id="tt" style="width:300px;height:30px;font-size:15px; margin-left: 110px;" autocomplete="off" />
	                     <input name="other_hospital_name" id="other_hospital_name" type="text" style="width:300px;height:30px;font-size:15px; margin-left: 18px;display:none;" />
	                
                    </div>
                      <div class="input_div">
                        <label><em class="font_red">*</em> 职务类型</label>
                        <select name="work_type" id="work_type">
                            <option value="-1">请选择</option>
                            <c:forEach items="${classList}" var="job">
								<option value="${job.code}">${job.name}</option>
							</c:forEach>
                        </select>
                    </div>
                    
                      <div class="input_div">
                        <label><em class="font_red">*</em> 职称</label>
                        <select name="work_unit" id="work_unit">
                            <option value="0">请选择</option>                            
                        </select>
                    </div>
                    
                    <div class="input_div">
                        <label><em class="font_red">*</em>学科</label>
                        <select name="xueke1" id="xueke1">
                            <option value="-1">请选择</option>
                        </select>
                        <select name="xueke2" id="xueke2">
                            <option value="-1">请选择</option>
                        </select>
                        <select name="xueke3" id="xueke3">
                            <option value="-1">请选择</option>
                        </select>
                    </div>
                    
                   <div class="input_div">
                        <label><em class="font_red">*</em> 是否来自基层</label>
                        <select name="grassroot" id="grassroot"  style="min-width:19em;">
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                     <div class="input_div" style="display:none">
                        <label><em class="font_red">*</em> 用户名</label>
                        <input type="text" name="account_name" id="account_name" value="admin" style="min-width:21.4em;" onblur="checkthi(this,'用户名');"/>
                        <p class="cp" style="color: red; display: none;float: left;margin-top: 5px;" >*</p>
                         <p class="inputTip">4-18个字符，仅支持字母和数字</p>
                    </div>
                   
                    
                  <!--   <div class="input_div">
                        <label><em class="font_red">*</em> 性别</label>
                        <select name="sex" id="sex"  style="min-width:19em;">
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </div>
                    <div class="input_div">
                        <label><em class="font_red">*</em> 用户名</label>
                        <input type="text" name="account_name" id="account_name"  style="min-width:21.4em;" onblur="checkthi(this,'用户名');"/>
                        <p class="cp" style="color: red; display: none;float: left;margin-top: 5px;" >*</p>
                         <p class="inputTip">4-18个字符，仅支持字母和数字</p>
                    </div> -->
                   
                    <!-- <div class="input_div">
                        <label>邮箱</label>
                        <input type="text" name="email" id="email" size="30" style="min-width:21.4em;" onblur="checkEmail();"/>
                    </div> -->
                    <div class="input_div">
                        <label>&nbsp;</label>
                        <button type="button" name="tab_btn1" id="tab_btn1" class="btn btn_orange btn_block float_left" onclick="nextValidate();">下一步</button>
                        <button type="button" name="reset_btn" id="tab1_resetbtn" class="btn btn_gray btn_block">重置</button>
                    </div>
                </div>
                <div class="tab2 tab_cont" style="display:none"  id="tab_cont2">
                   <!--  <div class="input_div" id="hst_lvl" style="display:none">
                        <label><em class="font_red">*</em> 医院等级</label>
                        <select name="hospital_level" id="hospital_level">
                            <option value="-1">请选择</option>
                        </select>
                    </div> -->
                    
                    
                    
                    <!-- <div class="input_div">
                        <label><em class="font_red">*</em> 单位地址</label>
                        <input type="text" name="hospital_region" id="hospital_region" placeholder="街道/门牌号" size="50"/>
                    </div> -->
                  
                   <!--  <div class="input_div">
                        <label>执业医师号</label>
                        <input type="text" name="work_id" id="work_id" size="50"/>
                    </div>
                    
                    <div class="input_div">
                        <label>您的学历</label>
                        <select name="grade" id="grade">
                            <option value="0">请选择</option>
                            <option value="1">高中</option>
                            <option value="2">中专</option>
                            <option value="3">大专</option>
                            <option value="4">本科</option>
                            <option value="5">硕士</option>
                            <option value="6">博士</option>
                            <option value="7">博士后</option>
                            <option value="8">其它</option>
                        </select>
                    </div> -->
                    <div class="input_div">
                        <label><em class="font_red">*</em> 手机号</label>
                        <input type="text" name="mobile_phone" id="mobile_phone"  style="min-width:21.4em;" onblur="checkPhone();" />
                        <p class="cp" style="color: red; display: none;" >*</p>
                    </div>
                    <div class="input_div">
                        <label>短信验证码</label>
                        <input type="text" name="verify_code" id="verify_code" style="min-width:11.7em;"/>
                        <button type="button" name="verify_btn" id="verify_btn" onclick="daojishi();" style="border-radius:7px;">获取验证码</button>
                        <input type="hidden" name="yzm" id="yzm"/>
                    </div>
                      <div class="input_div">
                        <label><em class="font_red">*</em> 密码</label>
                        <input type="password" name="account_password" id="account_password" size="40"  style="min-width:19em;" onblur="checkpwd(this);"/>
                         <p class="cp" style="color: red; display: none;float: left;margin-top: 5px;" >*</p>
                         <p class="inputTip">8-20位，须包含大写字母、小写字母、数字，不能使用空格</p>
                    </div>
                    <div class="input_div">
                        <label><em class="font_red">*</em> 确认密码</label>
                        <input type="password" name="confirmpassword" id="confirmpassword" size="40"  style="min-width:19em;"/>
                    </div>
                    
                    <div class="input_div">
                        <label>&nbsp;</label>
                        <input type="checkbox" name="agree_terms" id="agree_terms" checked/> 我已阅读并接受服务条款
                    </div>
                    <div class="input_div">
                        <label>&nbsp;</label>
                        <button type="button" name="register_btn" id="register_btn" class="btn btn_orange  float_left" onclick="haha();">确定注册</button>
                        <button type="button" name="tab2_resetbtn" id="tab2_resetbtn" class="btn btn_gray ">重置</button>
                        <button type="button" name="tab2_backbtn" id="tab2_backbtn" class="btn btn_gray ">返回上一步</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="/qiantai_page/bottom.jsp"%>  
</div>
<div class="popup">
    <div class="mask"></div>
    <div class="popup_cont clearfix login_form">
        <div class="join_form" id="popup_form">
            <p style="font-size:18px;">恭喜你，注册成功 !</p>
            <p style="font-size:14px;"><a href="${ctx}/login.do" id="djs">立即登录[5s]</a></p>
        </div>
    </div>
</div>
</form>
<script type="text/javascript" src="${ctx}/qiantai_page/js/validate.js"></script>
<script language="javascript">
/***
 *
 2017-03-28
    程红叶
   联想输入数据
 */
var AutoData = new Array();
var AutoDataTitle = new Array();


/**
 * 
   2017-03-28
            程宏业
 *  添加数据 
 *
 */
function addData(names,values){
   var obj = { "title": names,"content":values}
    return obj;
}


 
 
/**
 * 程红叶
   2017-03-38
   	联想输入
 */ 
$("#tt").bigAutocomplete(
		{width:300,
		 data:AutoData}
		);
 
 
//验证用户名是否可用
function checkthi(obj,parm){
var	$thi = $(obj); 
var objparm =$('mobile_phone').val();
var filter = /^[a-zA-Z0-9]{4,18}$/;
	if(!$thi.val()){
		$thi.next().show();
		$thi.next().text("*"+parm+"不能为空");
	}else {
		if(!filter.test(objparm)){
			$thi.next().show();
			$thi.next().css("color","red");
			$thi.next().text("*"+parm+"格式不正确");
		}else{
			// 检测用户是否可用
			 $.ajax({
				type:'POST',
				url:'${ctx}/registerUser.do?method=checkUserName&account_name='+objparm,
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message!='success'){
						$thi.next().show();
						$thi.next().css("color","red");
                		$thi.next().text("*"+parm+"已经存在");
                		$thi.focus();
					}else{
						$thi.next().show();
						$thi.next().css("color","green");
                		$thi.next().text("*"+parm+"可用");
					}
				}
			}); 
		}
		
	}
		
}




function checkpwd(obj){
	$thi=$(obj);
	var pattern = /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))[a-zA-Z0-9]{8,20}$/;
	var password = $thi.val();
	if(!pattern.test(password)){
		$thi.next().text("*密码格式不正确");
		$thi.next().css("color","red");
		$thi.next().show();
	}else{
		$thi.next().hide();
	}
	
}


	//验证码倒计时
	var countdown=120;
	
	var error = "${errorMsg}";
	if(error != null && error != "")
	{
		alert(error);
		location.href = "${ctx}/registerUser.do";
	}
	$(function(){
	
		$('#work_id').blur(function(){
			var code = $('#work_id').val();
			var check = /^[0-9]{15}$/.test(code);   
     		if(code!="" && !check){
     			alert("执业医师号为15位数字！");
     			$('#work_id').val("");
     			$('#work_id')[0].focus();
			  	return;
     		}
     		if(code!=""){
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
						  	return;
						}
					}
				});
     		}
		});
		
		$("#tab1_resetbtn").click(function(){
			$("#certificate_type").val(0);
			$("#certificate_no").val("");
			$("#real_name").val("");
			$("#grassroot").val(1);
			$("#account_name").val("");
			$("#account_password").val("");
			$("#confirmpassword").val("");
			$("#mobile_phone").val("");
			$("#verify_code").val("");
			$("#email").val("");

		});
		
		$("#tab2_resetbtn").click(function(){
			$("#region1").val(-1);
			$("#region2").val(-1);
			$("#rspropid").val(-1);
			$("#hospital_address").val(-1);
			$("#tt").val("");
			$("#other_hospital_name").val("");
			$("#other_hospital_name").hide();
			$("#hst_lvl").val(0);
			$("#hst_lvl").hide();
			$("#hospital_level").val(-1);
			$("#hospital_region").val("");
			$("#work_type").val(-1);
			$("#work_unit").val(0);
			$("#work_id").val("");
			$("#xueke1").val(-1);
			$("#xueke2").val(-1);
			$("#xueke3").val(-1);
			$("#grade").val(0);
			$("#mobile_phone").val("");
			$("#verify_code").val("");
			$("#agree_terms").prop("checked", true);
			
			
		});
		$('.aa i').click(function(){
			var str=$(this).text();
			$('.bb').val(str);
			$('.aa').slideUp(50);

			
		})
		
		
		
		
		$("#certificate_no").focusout(function() {
			var strICDCard = $("#certificate_no").val();

			/* if(!isValidateCardNumber(parseInt(strICDCard))) {
				alert("certificate number error");
				return false;
			} */
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
		
		/*
		 *@author  ZJG
		 *@time    2016-12-31
		 *方法说明   用户注册表单提交
		 */
		$("#register_btn").click(function(){
			
				if($("#agree_terms").prop("checked")) {
					//通过AJAX方式提交
					$.ajax({
					    url:'${ctx}/registerUser.do?method=register',
					    type: 'POST',
					    dataType: 'json',
					    data:$("#regForm").serialize(),
					    success: function(data){
					       var result = eval(data);
					      /*  var	aaa= document.getElementById("mobile_phone");
					       result.setAccount_name("aaa"); */
					       if(result.message == 'success'){
					    	   //保存至系统消息
					    	   saveMessage(result.id,"注册成功");
					    	   //弹出 提示层
					    	   $(".popup").show();
					    	   //登录倒计时
					    	   var timer = null;
							   var n=5;
							   timer =setInterval(function(){
									n--;
									$("#djs").text("立即登录["+n+"s]");
									if(n==0){
										clearInterval(timer);
										//执行登录
										location.href = '${ctx}/login.do';
									}
								},1000);
					    	   
					       }else if(result.message == 'errorMsg'){
					    	   alert("此用户名已注册.");
					       }else if(result.message == 'fail'){
					    	   alert("注册失败.");
					       }
					    }
					});
					//$(regForm).submit();
				}
				else{
					alert("请先阅读并接受服务条款!");
					return false; 
				}
		});
		//返回上一步
		$("#tab2_backbtn").on("click",function(){
			$("#tab1").prop("class", "tab active");
			$("#tab2").prop("class", "tab");
			 $("#tab_cont1").show();
			 $("#tab_cont2").hide();
		});
		$('#region1').change(function(){
			$("#region2").val(0);
			$("#rspropid").val(0);
			$("#hospital_address").val();
			$("#hospital_level").val(0);
			var url = "${ctx}/propManage/getPropListAjax.do?area=noPage&type=1&id=" + $(this).val();
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updatePropList($('#region2'), result);
				   		updatePropList2($('#rspropid'), null);
				   		updateHospitalList($('#hospital_address'), result.hospList);
				   };
			    }
			});
		});
			
		$('#region2').change(function(){
			$("#rspropid").val(0);
			$("#hospital_address").val();
			$("#hospital_level").val(0);
			var url = "${ctx}/propManage/getPropListAjax.do?area=noPage&id=" + $(this).val()+"&parentId=" + $("#region1").val();
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
				   		updatePropList2($('#rspropid'), result);
				   		updateHospitalList($('#hospital_address'), result.hospList);
				   };
			    }
			});
		});
		
		$("#rspropid").change(function(){
		
			$("#hospital_address").val();
			$("#hospital_level").val(0);
			var url = "${ctx}/examManage/getHospitalListAjax.do?parentId="+$("#rspropid").val();
			$.ajax({
			    url:url,
			    type: 'POST',
			    dataType: 'json',
			    success: function(result){
				   if(result != ''){
					    //将数据全部追加到
				   		updateHospitalList($('#hospital_address'), result.list);
				   };
			    }
			});
		});
		
		$("#hospital_address").change(function(){
			$('#hospital_level').val(0);
			if($("#hospital_address").val() == 0) {
				$("#other_hospital_name").show();
				$("#hst_lvl").show();
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
			}else {		
				$("#other_hospital_name").hide();
				$("#hst_lvl").hide();
				var url = "${ctx}/examManage/getHospitalListAjax.do?parentId="+$("#rspropid").val();
				$.ajax({
				    url:url,
				    type: 'POST',
				    dataType: 'json',
				    success: function(result){
					   if(result != ''){
					   		updateHospitalAddress($('#hospital_region'), result);
					   };
				    }
				});
			}
		});
		
		
		

		
		
		
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
			var url = "${ctx}/propManage/getPropListAjax.do?id="+$("#xueke2").val()+"&level=2";
			
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
			/*
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
			});	*/
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
	
	function updatePropList2(obj, result, _default){
			
		var str = "<option value='-1' selected>请选择</option>";
		if (result!=null)
		$(result.list).each(function(key, value){
			str += "<option value='"+value.id+"'>"+value.name+"</option>";
		});
		
		$(obj).html(str);
	}
	
	function format(mail) {
		return mail.id + " &lt;" + mail.value + "&gt";
	}
	
	
	
	function updateHospitalList(obj, list, _default){
		if (list!=null){
		AutoData.splice(0);
		AutoDataTitle.splice(0);
		$(list).each(function(key, value){
			AutoData.push(addData(value.name,value.id));
			AutoDataTitle.push(value.name);
		});
		}
		
	}
	
	function updateHospitalLevelList(obj, result, _default){
			
		var str = "<option value='-1' selected>请选择</option>";
		if (result!=null)
		$(result.list).each(function(key, value){
			str += "<option value='"+value.id+"'>"+value.name+"</option>";
		});
		str = "<option value='-1' selected>请选择</option>";
		$(obj).html(str);
	}
	
	function updateHospitalAddress(obj, result, _default) {
		var str="";
		if(result!=null) {
			var id = $("#hospital_address").val();
			$(result.list).each(function(key, value){
				if(id == value.id)
					if(value.hospital_address != null)
						
						str = value.hospital_address;
			});
		}
		$(obj).attr("placeholder",str+"街道/门牌号");
		
	}
	
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
	
	function updateXueke(obj, result, _default) {
		var str = "<option value='-1' selected>请选择</option>";
		
		if (result!=null)
		$(result.list).each(function(key, value){
			str += "<option value='"+value.id+"' img_type="+value.img_type+">"+value.name+"</option>";
		});
		
		$(obj).html(str);
	}
	
	/*
	 * @author  张建国
	 * @time    2017-01-09
	 * 说        明： 验证表单有效性
	 */
	function isFullInputData() {
		if($("#region1").val() == -1) {
			alert("请选择单位所属省！");
			return false;
		}
		if($("#region2").val() == -1) {
			alert("请选择单位所属市！");
			return false;
		}
		if($("#rspropid").val() == -1) {
			alert("请选择单位所属区县！");
			return false;
		}
		/* if($("#hospital_address").val() == 0) {
			alert("请正确填写单位名称或者选择其它选项！");
			return false;
		} */
		//判断单位名称是否是下拉选项框中的值
		var ttValue = $("#tt").val();
		if(ttValue != "" && ttValue != "其它"){
			var flag = $.inArray(ttValue, AutoDataTitle); 
			if(flag<0){
				alert("请正确填写单位名称或者选择其它选项！");
				return false;
			}
		}
		if($("#hospital_address").val() == "其它" && 
		$("#other_hospital_name").val() == "") {
			alert("请输入单位名称！");
			return false;
		}
		if($("#hospital_region").val() == "") {
			alert("请输入单位地址！");		
			$("#hospital_region").focus();
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
		if($("#mobile_phone").val() == "") {
			alert("请输入手机号！");
			$("#mobile_phone").focus();
			return false;
		}
		//判断手机号码是否重复
		if($("#mobile_phone").val()!=""){
			
			var phone = $("#mobile_phone").val();
			//通过ajax检测手机号码是否为空
			$.ajax({
				type:'POST',
				url:'${ctx}/registerUser.do?method=checkMobile&mobile_phone='+phone,
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message!='success'){
						alert('该手机号已经注册，请直接登录.');
						return false;
					}
				}
			});
		}
		//判断验证码是否为空
		 if($("#verify_code").val() == "") {
			alert("请输入验证码！");
			//$("#tab_cont1").show();
			//$("#tab_cont2").hide();
			$("#verify_code").focus();
			return false;
		} 
		//判断是否获取验证码
		 if($("#yzm").val() == "") {
			alert("请先获取验证码！");
			//$("#tab_cont1").show();
			//$("#tab_cont2").hide();
			$("#verify_code").focus();
			return false;
		} 
		//判断验证码是否正确
		if($("#verify_code").val() != "" && $("#yzm").val() != "" && $("#verify_code").val()!= $("#yzm").val()) {
			alert("验证码输入错误！");
			//$("#tab_cont1").show();
			//$("#tab_cont2").hide();
			$("#verify_code").focus();
			return false;
		} 
		return true;
	}
	
	//initXueke();
	
	/*
	 * @author  张建国
	 * @time    2017-01-09
	 * 说         明：获取服务端验证码信息 
	 */
	function geyYZM(){
		//获取用户填写的手机号码
		var phone = $("#mobile_phone").val();
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
	
	$(function () {
	    $(".tab_list li").click(function () {
	        window.location.href = "?tab=" + $(this).attr("id");
	    });
	    $("button[name=reset_btn]").click(function () {
	        $(".input_div input,.input_div select").val("");
	    });
	    $("button[name=submit]").click(function () {
	        $(".tab2").show().siblings("div").hide();
	        $("#tab2").addClass("active").siblings("#tab1").removeClass("active");
	    });
	    $("button[name=submit2]").click(function () {
	        window.localStorage.setItem("isLogin","true");
	        window.location.href = "user_center/my_ability.html";
	        //window.location.href = "index.html";
	    });
	
	    $("select[name=hospital_name]").change(function () {
	        if ($(this).val() == "other"){
	            $("input[name=other_hospital_name]").show();
	        }else{
	            $("input[name=other_hospital_name]").hide();
	        }
	    });
	
	    if ($.getUrlParam("tab") == "tab1"){
	        $(".tab1").show().siblings("div").hide();
	        $("#tab1").addClass("active").siblings("#tab1").removeClass("active");
	    }else if ($.getUrlParam("tab") == "tab2"){
	        $(".tab2").show().siblings("div").hide();
	        $("#tab2").addClass("active").siblings("#tab1").removeClass("active");
	    }
	    $(".tab").unbind("click");
	});
	
	/*
	 * @author 张建国
	 * @time   2017-01-09
	 * 说       明：验证码倒计时
	 */
	function daojishi(){
		var obj = $("#verify_btn");
		
		obj.attr("disabled",true); 

		//验证手机号码是否输入
		var phone = $("#mobile_phone").val();
		if(phone!=null && phone!=''){
			//判断手机号码是否已经注册
			//通过ajax检测手机号码是否为空
			$.ajax({
				type:'POST',
				url:'${ctx}/registerUser.do?method=checkMobile&mobile_phone='+phone,
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message=='success'){
						
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
					}else{
						alert('该手机号已经注册，请直接登录.');
						$("#mobile_phone").focus();
						
					}
				}
			});
		}else{
			alert("请输入手机号码.");
		}
    }
	
	/*
	 * @author 张建国
	 * @time   2017-01-09
	 * 说        明：点击下一步验证
	 */
	 function nextValidate(){
		 checkPhone();
		var yzm = $("#yzm").val();
		if($("#certificate_type").val() == 0) {
			alert("请选择证件类型！");
			$("#tab_cont1").show();
			$("#tab_cont2").hide();			
			return false;
		} 
		if($("#certificate_no").val() == "") {
			alert("请输入证件号码！");
			$("#tab_cont1").show();
			$("#tab_cont2").hide();
			$("#certificate_no").focus();
			return false;
		}
		if(!checkIdCard()){
			alert("证件号码不正确，请重新输入！");
			$("#tab_cont1").show();
			$("#tab_cont2").hide();
			$("#certificate_no").focus();
			return false;
		}
		if($("#real_name").val() == "") {
			alert("请输入真实姓名！");
			$("#tab_cont1").show();
			$("#tab_cont2").hide();
			$("#real_name").focus();
			return false;
		}
		/* if($("#account_name").val() == "") {
			alert("请输入用户名！");
			$("#tab_cont1").show();
			$("#tab_cont2").hide();
			$("#account_name").focus();
			return false;
		} */
		/* var filter = /^[a-zA-Z0-9]{4,18}$/;
		if(!filter.test($("#account_name").val())) {
			alert("用户名格式不正确！");
			$("#tab_cont1").show();
			$("#tab_cont2").hide();
			$("#account_name").focus();
			return false;
		} */
		
		$("#tab1").prop("class", "tab");
		$("#tab2").prop("class", "tab active");
		$("#tab_cont1").hide();
		$("#tab_cont2").show();
	}
	 function haha(){
	 if($("#account_password").val() == "") {
			alert("请输入密码！");
			$("#tab_cont2").show();
			$("#tab_cont1").hide();
			$("#account_password").focus();
			return false;
		}
		var pattern = /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]))[a-zA-Z0-9]{8,20}$/;
		if(!pattern.test($("#account_password").val())) {
			alert("密码格式不正确！");
			$("#tab_cont2").show();
			$("#tab_cont1").hide();
			$("#account_password").focus();
			return false;
		}
		if($("#confirmpassword").val() == "") {
			alert("请确认密码！");
			$("#tab_cont2").show();
			$("#tab_cont1").hide();
			$("#confirmpassword").focus();
			return false;
		}
		if($("#confirmpassword").val() != $("#account_password").val()) {
			alert("请重新确认密码！");
			$("#tab_cont2").show();
			$("#tab_cont1").hide();
			$("#confirmpassword").val('').focus();
			return false;
		}
	 }

	/**
	 * @author 张建国
	 * @time   2017-01-17
	 * 说     明：检查手机号码是否重复
	 **/
	function checkPhone(){
		var $thi = $("#mobile_phone");
		var phone = $("#mobile_phone").val();
		//通过ajax检测手机号码是否为空
		if(phone!=null && phone!=''){
			$.ajax({
				type:'POST',
				url:'${ctx}/registerUser.do?method=checkMobile&mobile_phone='+phone,
				dataType:'json',
				async: false,
				success:function(data){
					var result = eval(data);
					if(result.message!='success'){
						$thi.next().show();
						$thi.next().text("*该手机号已经注册不可用");
						$thi.next().css("color","red");
						$("#mobile_phone").focus();
					}else{
						$thi.next().show();
						$thi.next().css("color","green");
						$thi.next().text("*该手机号可用");
					}
				}
			});
		}
	}
	
	
	/**
	 * @author 张建国
	 * @time   2017-01-17
	 * 说     明：检查证件号码是否重复
	 **/
	function checkIdCard(){
		var idCard = $("#certificate_no").val();
		var $thi = $("#certificate_no");
		var cardType = $("#certificate_type").val();
		var filter_jg = /^[a-zA-Z0-9]{7,21}$/;
		var filter_ga = /^[a-zA-Z0-9]{5,21}$/;
		var filter_hz =  /^[a-zA-Z0-9]{3,21}$/;
		var res = true;
		if(idCard!=null && idCard!=''){
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
			return res;
		}else{
			$thi.next().text("*证件号码不可以为空");
			$thi.next().css("color","red");
			$thi.next().show();
		}
	}

//验证邮箱是否已经存在
function checkEmail(){
	var email = $("#email").val();
	if($("#email").val()!=""){
		var re = /^([a-zA-Z0-9_-])+@([A-Za-z0-9]+[-.])+[A-Za-zd]{2,5}$/;
		if ($('#email').val() !="" && !re.test($('#email').val())){
			alert("邮箱格式不对！");
			$("#email").val("");
			$("#email").focus;
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
					$("#email").val("");
					$("#email").focus;
					return false;
				}
			}
		});
	}
	
}
</script>
</body>
</html>