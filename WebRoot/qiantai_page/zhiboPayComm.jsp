<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<script src="${ctx}/qiantai_page/js/zhibodrag.js"></script>
<link href="${ctx}/qiantai_page/css/drag.css" rel="stylesheet">
<style type="text/css">

.paybox{
    padding: 20px;
    border-radius: 10px;
    background-color: #fff;
    position: fixed;
    top: 50vh;
    left: 50%;
    z-index: 999999;
    width: 500px;
    margin: -200px 0 0 -270px;
    display: none; 


}


.paybox .joinform #paysubmit{
    border: 0 none;
    padding: 5px 10px;
    display: inline-block;
    box-sizing: border-box;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
    margin-right: 20px;
    border-radius: 6px;
    background-color: #f90;
    color: #fff;

}


.paybox .joinform #closebox2{
     border: 0 none;
    padding: 5px 10px;
    display: inline-block;
    box-sizing: border-box;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
    margin-right: 20px;
    border-radius: 6px;
    border:1px solid #ccc;
    background-color: #fff;
    color: #615858;

}




.paybox .joinform{
    margin: 20px auto;
    width: 80%;
}

.paybox h2{
	font-size: 18px;
    border-bottom: 1px solid #12bce1;
    margin-top: 0;
}



.joinform .input_div input[type="text"],input[type='password']{
    padding: 5px;
    background: #fff;
    color: #333;
    box-sizing: border-box;
    width: 100%;
    border: 1px solid #666;
    font-size: 14px;
    border-radius: 7px;
}


#labcard{
font-weight: bold;
}

#labpwd{
font-weight: bold;
}

.bgc{
    width: 100%;
    height: 100%;
    background-color: rgba(51, 51, 51, 0.5);
    position:absolute;
    top: 0;
    left: 0;
    z-index: 100;
    display: none;
    
}
</style>
		<input type="hidden" id="zbcvid" value="" />
        <input type="hidden" id="zbcvsetid" value="" />
        <input type="hidden" id="costType" value="" />
<!-- 直播加入时卡项目入口页 -->
       <div class="paybox" >
		 <h2><span class="close" id="closebox" style="float: right;" ><i class="fa fa-times" style="color: rgb(204,204,204);"></i></span> 添加学习卡 </h2>
		 <div class="joinform">
		<div class="input_div">
		 <label id="labcard">输入学习卡卡号<input type="text" id="cardnumber" maxlength="20"></label>
		 <label id="labpwd">输入学习卡密码<input type="password" id="cardpwd" maxlength="20"></label>
		<br/>
		<input type="button" value="提交" id="paysubmit"/>
		<input type="button" value="取消" id="closebox2"/>
		</div> 
		 </div>
	</div>
