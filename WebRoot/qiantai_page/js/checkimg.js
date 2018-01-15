/***
 * 程宏业
 2017-2-23
 判断图片加载是否成功
 */ 

function imgonload(obj,sex){
	if(sex==1){
	$(obj).attr("src",basepath+"/qiantai_page/img/user_avatar.jpg");
	}else{
		$(obj).attr("src",basepath+"/qiantai_page/img/user_avatar1.png");
	}
}
