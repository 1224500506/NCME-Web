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
    <script src="${ctx}/qiantai_page/js/checkimg.js"></script>
    <script type="text/javascript" src="${ctx}/peixun_page/js/lib/layer/layer.js"></script>
    <link href="${ctx}/qiantai_page/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/jquery.bxslider.css" rel="stylesheet">
    <link href="${ctx}/qiantai_page/css/global.css" rel="stylesheet">




<style>

#cardview{
position: absolute;
height: 320px;
width: 800px;
top:200px;
background-color:#fff;
left: 20%;
z-index: 100;
text-align: center;
display: none;
border-radius: 5px;
border: 1px solid #ccc;

}





.paybox{
position: absolute;
height: 200px;
width: 400px;
top:200px;
background-color:#ccc;
left: 40%;
z-index: 100;
text-align: center;
display: none;
}

.paybox span{
padding: 0px 4px 0px 4px;
background: rgb(221,221,221);
float: right;
color:white;
border-radius: 6px;
border: solid 1px white;
cursor: pointer;

}

.paybox #paysubmit{
    margin-top: 20px;
	border: 0 none;
    padding: 5px 10px;
    display: inline-block;
    box-sizing: border-box;
    text-align: center;
    cursor: pointer;
    text-decoration: none;
    margin-right: 20px;
    border-radius: 6px;

}

.paybox lable{

margin-top: 30px;

}


 #close_btn{
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

.poptop{
margin: -280px 0 0 -270px;

}


#cardtitle ul li{
list-style: none;
float: left;
width:123px;
text-align:center;
padding:5px;
}


.cardviewinner #tbody ul li{
list-style: none;
float: left;
padding: 4px;

}



.cardviewinner{
height: 300px;
width: 800px;
overflow: auto;

}

#cardtitle #closet{
position: absolute;
left: 782px;
cursor: pointer;

}
#tbody tr td{
	width:120px;
	padding:5px 3px 5px 2px;
	
}
.join_form .input_div label { 
	display: block; 
}
</style>

</head>




<div id="cardview">
<div id="cardtitle">
<span id="closet" title="关闭">X</span>
<ul>
	<li>卡类型</li>
	<li>卡号</li>
	<li>适用的项目</li>
	<li>面值</li>
	<li>有效期</li>
	<li>使用状态</li>
</ul>
</div>
<div class="cardviewinner">

<table style="margin: 0px 0">
<tbody id ="tbody">

</tbody>
</table>



</div>
</div>

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
	               		<img src="${userInfo.user_avatar}" onerror="imgonload(this,'${userInfo.sex}');" id="userImage" name="userImage" />
	               	</c:if>
	               	<c:if test="${userInfo.user_avatar == null}">
		               <c:if test="${userInfo.sex == 1}">
		               <img src="${ctx}/qiantai_page/img/user_avatar.jpg"  id="userImage" name="userImage" />
		               </c:if>
		               <c:if test="${userInfo.sex == 2}">
		               <img src="${ctx}/qiantai_page/img/user_avatar1.png"  id="userImage" name="userImage" />
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
                <li  class="active"><a href="${ctx}/userInfo/myAccountManage.do">我的学习卡</a></li> 
                <li><a href="${ctx}/userInfo/MyCourse.do?mode=personal">我的学科</a></li>
                <li><a href="${ctx}/userInfo/myMessageManage.do">我的消息</a></li>
                <li><a href="${ctx}/userInfo/studyRecordManage.do">学习档案</a></li>
                <li><a href="${ctx}/userInfo/userAccount.do">账号管理</a></li>                
                
            </ul>
        </div>
        <div class="main_cont right">
            <div class="tabs">
                <ul class="tab_list">
                    <li class="float_right add_card font_s"><i class="fa fa-plus"></i><a href="javascript:void(0);" id="addcard">添加学习卡</a></li>
                    <li class="tab active" id="tab1">可使用</li>
                    <!-- <li class="tab" id="tab2">已用完</li> -->
                    <li class="tab" id="tab3">已过期</li>
                </ul>
                <div class="tab_cont tab1">
                    <table>
                        <thead>
                        <tr>
                            <th>卡类型</th>
                            <th>卡号</th>
                            <th>有效期</th>
                            <th>适用项目</th>
                            <th>已用项目</th>
                             <th>操作</th>
                            
                        </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${cardlist}" var="cards" >
                        <tr>
                            <td>${cards.cardTypeName}</td>
                            <td>${cards.CARD_NUMBER}</td>
                            <td><fmt:formatDate value="${cards.USEFUL_DATE}"  pattern="yyyy-MM-dd 23:59:59"/></td>
                            <td>${cards.cvSetTotal}</td>
                            <td>${cards.cvSetUseTotal}</td>
                             <td class="ctrl">
                             <a href="javascript:infoView('${cards.cardTypeName}','${cards.CARD_TYPE_ID}','${cards.CARD_NUMBER}','${cards.CV_SET_ID}','${cards.USEFUL_DATE}',1);" title="${cards.CARD_NUMBER}" class="btn btn_blue btn_s QueryView">查看明细</a>
                            </td> 
                            
                        </tr>
                         </c:forEach>
                        </tbody>
                    </table>
            <!--         <ul class="paginate">
                        <li class="first_page">首页</li>
                        <li class="prev">上一页</li>
                        <li class="active">1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li>……</li>
                        <li class="next">下一页</li>
                        <li class="last_page">尾页</li>
                    </ul> -->
                </div>
   <%--              <div class="tab_cont tab2" style="display:none">
                             <table>
                        <thead>
                        <tr>
                            <th>卡类型</th>
                            <th>卡号</th>
                            <th>适用项目</th>
                            <th>面值</th>
                            <th>余额</th>
                            <th>有效期</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${cardlist}" var="cards" >
                        <tr>
                            <td>学习卡</td>
                            <td>${cards.CARD_NUMBER}</td>
                            <td>${cards.name}</td>
                            <td>￥${cards.FACE_VALUE}</td>
                            <td>￥00.00</td>
                            <td>${cards.USEFUL_DATE}</td>
                            <td class="ctrl">
                                <button type="button" name="view_detail" class="btn btn_blue btn_s">查看明细</button>
                                <button type="button" name="use_it" class="btn btn_orange btn_s">立即使用</button>
                            </td>
                        
                        </tr>
                         </c:forEach>
                        </tbody>
                    </table>
                    <ul class="paginate">
                        <li class="first_page">首页</li>
                        <li class="prev">上一页</li>
                        <li class="active">1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li>……</li>
                        <li class="next">下一页</li>
                        <li class="last_page">尾页</li>
                    </ul>
                </div> --%>
                <div class="tab_cont tab3" style="display:none">
                             <table>
                        <thead>
                        <tr>
                            <th>卡类型</th>
                            <th>卡号</th>
                            <th>有效期</th>
                            <th>适用项目</th>
                            <th>已用项目</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${cardlist2}" var="cards2" >
                        <tr>
                             <td>${cards2.cardTypeName}</td>
                            <td>${cards2.CARD_NUMBER}</td>
                            <td><fmt:formatDate value="${cards2.USEFUL_DATE}"  pattern="yyyy-MM-dd 23:59:59"/></td>
                            <td>${cards2.cvSetTotal}</td>
                            <td>${cards2.cvSetUseTotal}</td>
                             <td class="ctrl">
                             <a href="javascript:infoView('${cards2.cardTypeName}','${cards2.CARD_TYPE_ID}','${cards2.CARD_NUMBER}','${cards2.CV_SET_ID}','${cards2.USEFUL_DATE}',3);" title="${cards2.CARD_NUMBER}" class="btn btn_blue btn_s QueryView">查看明细</a>
                            </td> 
                        </tr>
                         </c:forEach>
                        </tbody>
                    </table>
             <!--        <ul class="paginate">
                        <li class="first_page">首页</li>
                        <li class="prev">上一页</li>
                        <li class="active">1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li>……</li>
                        <li class="next">下一页</li>
                        <li class="last_page">尾页</li>
                    </ul> -->
                </div>
            </div>
        </div>
        </div>
        <%@include file="/qiantai_page/bottom.jsp"%>
    </div>
    
    <div class="popup ">
	<form id="loginForm" name="loginForm">
		<div class="popup">
		    <div class="mask"></div>
		    <div class="popup_cont clearfix login_form" style="margin: -220px 0 0 -270px;">
		        <h2><span class="close"><i class="fa fa-times"></i></span> 添加学习卡 </h2>
		        <div class="join_form" id="popup_form">
		            <div class="input_div">
		                <label>输入学习卡卡号: <input type="text" name="cardnumber" id="cardnumber"></label>
		               
		                <label id="labpwd">输入学习卡密码:<input type="password" id="cardpwd" maxlength="20" style="display:bolck;height:31px;width:400px;"></label>
		                
		            </div>
		         
		     <div class="input_div">
		            <div class="input_div">
		               <button type="button" name="submit_btn" id="submit_btn" class="btn btn_orange btn_block float_left">提交</button>
		               <button type="button" name="submit_btn" id="close_btn">取消</button>
		            </div>
		        </div>
		    </div>
		</div>
	</form>
</div>

<!-- layer内容 -->
<div id="layercontent" style="display:none;">
<div class="center">
<form name="editfrm"  method="post">
	<input type="hidden" id="userId" name="model.userId" value="0" />
	<div class="tiaojian" style="min-height:40px;">
		<p class="fl" style="margin-bottom:20px;margin-left:20px;">
			<span style="width:8em;text-align:right;">卡号：</span>
			<span id="cNumber"></span>
		</p>
		<p class="fl" style="margin-bottom:20px;margin-left:20px;">
			<span style="width:8em;text-align:right;">卡类型：</span>
			<span id="cType"></span>
		</p>
		<p class="fl" style="margin-bottom:20px;margin-left:20px;">
			<span style="width:8em;text-align:right;">有效期至：</span>
			<span id="cUserfullDate"></span>
		</p>
		<table>
             <thead>
             <tr>
                 <th>序号</th>
                 <th>项目名称</th>
                 <th>学分</th>
                 <th>使用时间</th>
                  <th>使用状态</th>
             </tr>
             </thead>
             <tbody id="tbodyX">
               <%-- <c:forEach items="${cardlist2}" var="cards2" >
             	<tr>
                  <td>${cards2.cardTypeName}</td>
                 <td>${cards2.CARD_NUMBER}</td>
              
                 <td>￥${cards2.price}</td>
                  <td><fmt:formatDate value="${cards2.USEFUL_DATE}"  pattern="yyyy-MM-dd 23:59:59"/></td>
                  <td class="ctrl">
                  <a href="javascript:void(0);" title="${cards2.CARD_NUMBER}" class="btn btn_blue btn_s QueryView ">查看明细</a>
                 </td> 
             	</tr>
              </c:forEach> --%>
             </tbody>
         </table>
	</div>
</form>

    <div class="popup ">
	<form id="loginForm" name="loginForm">
		<div class="popup">
		    <div class="mask"></div>
		    <div class="popup_cont clearfix login_form" style="margin: -220px 0 0 -270px;">
		        <h2><span class="close"><i class="fa fa-times"></i></span> 添加学习卡 </h2>
		        <div class="join_form" id="popup_form">
		            <div class="input_div">
		                <label>输入学习卡卡号: <input type="text" name="cardnumber" id="cardnumber"></label>
		               
		                <label id="labpwd">输入学习卡密码:<input type="password" id="cardpwd" maxlength="20" style="display:bolck;height:31px;width:400px;"></label>
		                
		            </div>
		         
		     <div class="input_div">
		            <div class="input_div">
		               <button type="button" name="submit_btn" id="submit_btn" class="btn btn_orange btn_block float_left">提交</button>
		               <button type="button" name="submit_btn" id="close_btn">取消</button>
		            </div>
		        </div>
		    </div>
		</div>
	</form>
</div>
	

</div>
</div>


    
<script>
    $(function () {
        $(".btn_apply").click(function(){
            $(".popup.credit_tips").show();
        });
        $(".btn_cert,.btn_credit").click(function(){
            $(".popup.cert").show();
        });
        $("button[name=yes],button[name=no],.close").click(function () {
            $(".popup").hide();
        });
        
        /** CHY 2017-04-19
                                         添加学习卡
                                       弹出框                            
                                        
        **
        
        **/
        $("#addcard").click(function(){
        	$("#cardnumber").val("");
        	$("#cardpwd").val("");
        	$(".popup").show();
        	
        })
        
        /**
          	添加学习卡提交
        */
        $("#submit_btn").click(function(){
        	var cardnumberval = $("#cardnumber").val();
        	var cardpwd = $("#cardpwd").val();
        	
        	if(!cardnumberval){
        		alert("请输入卡号");
        		return false;
        	}
        	if(!cardpwd){
        		alert("请输入密码");
        		return false;
        	}
         	$.ajax({
				type: 'POST',
				url: "${ctx}/userInfo/myAccountManage.do?mode=addCard&cardNumber="+cardnumberval+"&cardPassword="+cardpwd,
				dataType: 'JSON',
				success : function(data){
					var result = eval(data);
					if(result.message == 'success'){
						//暂时播放
			        	alert("添加成功");
			        	$(".popup").hide();
			        	window.location.href="${ctx}/userInfo/myAccountManage.do";
					}else if(result.message == 'noenable'){						
						alert("该卡已被使用，无法重复添加"); //卡状态不可用
						return false;
					}else if(result.message == 'time'){
						alert("您的学习卡已经过期");
						return false;
					}else if(result.message == 'notfind') {
						alert("输入的卡号或密码错误");
						return false;
					}else if(result.message == 'typeno') {
						alert("该卡未绑定项目，无法添加成功"); //卡类型无效
						return false;
					}else if(result.message == 'typedisable') {
						alert("该学习卡处于禁用状态，不可绑定用户"); //卡类型无效
						return false;
					}
				},
				error:function(data2){
					alert("数据异常");
				}
	    });
        	
        	
        })
        
        
        $("#closet").click(function(){
        	$("#cardview").hide();
        	
        	
        	
        })
        
        //#####################################
        var win_w = "800px";
		var win_h = "500px";
		var add_cont = $('#layercontent').html();
		$('#layercontent').remove();
		$(".QueryView").click(function(){
			layer.open({
				type: 1,
				title: "学习卡使用明细",
				skin: 'layui-layer-rim', //加上边框
				area: [win_w, win_h], //宽高
				content: add_cont,
				closeBtn: 0,
				btn: ['返回'],
				yes: function (index, layero) {
					//缩写保存功能
					layer.close(index);
				},
				btn2: function (index, layero) {
					layer.close(index);
				},
				success: function(layerb, index){
					$(".btn1").click(function(){
						layer.close(index);
					});
				}
			});
		});
        //#####################################
        
        /* $(".QueryView").click(function(){
        	
        	$("#cardview").show(500);
        	$("#tbody").empty();
        	var cardNumber = $(this).attr("title");
         	 $.ajax({
				type: 'POST',
				url: "${ctx}/userInfo/myAccountManage.do?mode=view&cardNumber="+cardNumber,
				dataType: 'JSON',
				success : function(data){
				
		          	 var obj = data;
		          	 
	                 var html ="";
	               	 $.each( obj.list, function(index, o){
	               	 	//添加学习卡使用状态--taoliang{
	               	 	var useStatusStr = "";
	               	 	if(o.use_status == 1){
	               	 		useStatusStr = "<td>已使用</td>";
	               		}else if(o.use_status == 0){
	               			useStatusStr = "<td><a href='${ctx}/projectDetail.do?id="+o.CV_SET_ID+"'>未使用</td>";
	               		}
	               		//########end}    
	               		html+="<tr>"+
	       			 	"<td>"+o.cardTypeName+"</td>"+
	        			"<td>"+o.CARD_NUMBER+"</td>"+
	        			"<td>"+o.ITEM_NAME+"</td>"+
	       				"<td>￥"+o.price+"</td>"+
	        		    "<td>"+o.PAY_DATE+"</td>"+
	        		    useStatusStr
	       				"</tr>";
	       				
	       			});
	               			 
	            		 $("#tbody").append(  
	            				 html
	            		);
	            		  
	                },
				error:function(data2){
					alert("数据异常");
				}
	    }); 
        	
        	
        	
        	
        	
        }) */
        
        
        
           // 取消
        $("#closebox").click(function(){
        	$(".paybox").hide();
        }) 
        
        // 取消按钮
         $("#close_btn").click(function(){
        	 $(".popup").hide();
        }) 
        
        

    });
    
    function infoView(cardTypeName,cardTypeId,cardNumber,cvSetId,usefullDate,type){
         	 $.ajax({
				type: 'POST',
				url: "${ctx}/userInfo/myAccountManage.do?mode=view&cardNumber="+cardNumber+"&cardTypeId="+cardTypeId+"&cvSetId="+cvSetId,
				dataType: 'JSON',
				success : function(data){
					$("#cNumber").html(cardNumber);
		          	$("#cType").html(cardTypeName);
	            	$("#cUserfullDate").html(usefullDate.split(' ')[0]+" 23:59:59");
	            	var obj = data;
	                var html ="";
	               	 $.each( obj.list, function(index, o){
	               	 	//添加学习卡使用状态--taoliang{12bce1
	               	 	var useStatusStr = "";
	               	 	var levelStr = "";
	               	 	if(o.use_status == 1){
	               	 		if(type==1){
	               	 			useStatusStr = "<td><a href='${ctx}/projectDetail.do?id="+o.cvSetId+"'>已使用</td>";
	               	 		}else{
	               	 			useStatusStr = "<td>已使用</td>";
	               	 		}
	               		}else if(o.use_status == 0){
	               			if(type==1){
	               				useStatusStr = "<td><a style='color:#0B92E8;font-weight: 900' href='${ctx}/projectDetail.do?id="+o.cvSetId+"'>去使用</td>";
	               			}else{
	               				useStatusStr = "<td>去使用</td>";
	               			}
	               			
	               		}
	               		if(o.level == 1){
	               			levelStr = "国家I类";
	               		}else if(o.level == 2){
	               			levelStr = "省级I类";
	               		}else if(o.level == 3){
	               			levelStr = "市级I类";
	               		}else if(o.level == 4){
	               			levelStr = "省级II类";
	               		}else if(o.level == 5){
	               			levelStr = "市级II类";
	               		}
	               		//########end}    
	               		html+="<tr>"+
	       			 	"<td>"+(index+1)+"</td>"+
	        			"<td>"+o.name+"</td>"+
	        			"<td>"+levelStr+o.score+"</td>"+
	       				"<td>"+o.payDate+"</td>"+
	        		    useStatusStr
	       				"</tr>";
	       				
	       			});
	               			 
            		$("#tbodyX").append(html);
	            		  
	            },
				error:function(data2){
					alert("数据异常");
				}	  
	    }); 
    }
</script>
</html>