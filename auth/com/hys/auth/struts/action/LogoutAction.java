package com.hys.auth.struts.action;


import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.service.local.SystemLogManage;
import com.hys.exam.utils.CusAccessObjectUtil;
import com.hys.framework.web.action.BaseAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * 标题：用户登出
 * <p>
 * 作者：zdz，2009 7 24
 * <p>
 * 描述：
 * <p>
 * 说明:
 */
public class LogoutAction extends BaseAction {
    private SystemLogManage systemLogManage;

    @SuppressWarnings("unchecked")
    @Override
    protected String actionExecute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
        if (null != user) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SystemLog systemLog = new SystemLog();
            systemLog.setUser_id(String.valueOf(user.getUserId()));
            systemLog.setOperate_login_name(user.getRealName());
            systemLog.setOperate_platform("qiantai");
            systemLog.setRequest_url(request.getRequestURI());
            systemLog.setVisit_ip(CusAccessObjectUtil.getIpAddress(request));
            systemLog.setOperate_time(sdf.format(new Date()));
            systemLog.setCreate_time(sdf.format(new Date()));
            systemLog.setModule_name("退出");
            systemLog.setOperate_type("exit");
            systemLog.setOperate_context("退出中国继续医学教育网成功！");
            try {
                systemLogManage.addSystemLog(systemLog);
            } catch (Exception e){
                logger.error("添加审计日志期间发生异常:" + e.getMessage());
            }
        }
        // 清空session中的值
        Enumeration<String> e = request.getSession().getAttributeNames();

        request.getSession().invalidate();

        request.getSession().removeAttribute(Constants.SESSION_TRAIN_ADMIN_USER);

//		String key;
    /*	while (e.hasMoreElements()) {
            key = (String) e.nextElement();
			request.getSession().removeAttribute(key);
			
			request.getSession().invalidate();
		}*/
        /*String url = request.getParameter("url");*/
        return Constants.SUCCESS;
    }

    public SystemLogManage getSystemLogManage() {
        return systemLogManage;
    }

    public void setSystemLogManage(SystemLogManage systemLogManage) {
        this.systemLogManage = systemLogManage;
    }
}
