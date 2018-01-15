package com.hys.qiantai.struts.action;

import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.constants.Constants;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.utils.VerificationCodeUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 标题：病历管理平台
 * 
 * 作者：Tiger 2016-09-17
 * 
 * 描述：
 * 
 * 说明:删除病例
 */
public class GetRandomPictureAction extends BaseAction {
	@SuppressWarnings("unchecked")
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    VerificationCodeUtil vcu = VerificationCodeUtil.Instance();
	    response.setContentType("image/jpeg");
	    response.setHeader("inputName", "inputStream");
	    //response.getWriter().write(vcu.getImage());
	    OutputStream os = response.getOutputStream();
        ImageOutputStream imageOut = ImageIO  
                .createImageOutputStream(os);
        ImageIO.write(vcu.getImg(), "JPEG", imageOut);  
        imageOut.close();
	    //response.getOutputStream().write(imageData);
        request.getSession().setAttribute("random", vcu.getVerificationCodeValue());// 取得随机字符串放入HttpSession
        response.flushBuffer();
        os.close();
	      
	    return null;
	}
}
