<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>

<div class="popup">
	<form id="loginForm" name="loginForm" action="${ctx}/loginAjax.do"
		method="post">
		<div class="popup">
			<div class="mask"></div>
			<div class="popup_cont clearfix login_form"
				style="margin: -200px 0px 0px -270px;">
				<input type="hidden" id="isLive" value="false" />
				<h2>
					<span class="close"><i class="fa fa-times"></i> </span> 用户登录
				</h2>
				<div class="join_form" id="popup_form">
					<div class="input_div">
						<label>用户名/手机/邮箱</label> <input type="text" name="userData"
							id="userName" />
					</div>
					<div class="input_div">
						<label>密码</label> <input type="password" name="password"
							id="password" />
					</div>
					<div class="input_div">
						<!-- <label>验证码</label>
                    <input type="hidden" id ="yzm" />
                <div id="drag"></div> -->
						<input type="text" id="yzmInput" style="width: 50%"
							placeholder="验证码" /> <input type="button" id="yzmCode"
							onclick="createCode()"
							style="width: 60px; margin-left: 18%; height: 40px; width: 30%; font-size: 30px"
							title='点击更换验证码' /> <input type="hidden" id="yzm" />
					</div>
					<div class="input_div">
						<button type="button" name="submit_btn" id="submit_btn"
							class="btn btn_orange btn_block float_left">确认登录</button>
					</div>
				</div>
			</div>
	</form>
</div>