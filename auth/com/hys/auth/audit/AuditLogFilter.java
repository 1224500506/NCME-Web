package com.hys.auth.audit;

import com.hys.auth.util.RequestUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.SystemUser;
import com.hys.exam.model.system.SystemLog;
import com.hys.exam.model.system.SystemMenu;
import com.hys.exam.service.local.SystemLogManage;
import com.hys.exam.service.local.SystemMenuManage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AuditLogFilter implements Filter {
    private static SystemLogManage systemLogManage;

    static {
        BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:application*.xml");
        systemLogManage = (SystemLogManage) factory.getBean("systemLogManage");
    }

    public static final Logger logger = Logger.getLogger(AuditLogFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            HttpServletRequest req = (HttpServletRequest) request;
//
//            String uri = req.getRequestURI();
//            String ip = req.getRemoteAddr();
//            String operatePlatform = "qiantai";//平台
//            String mode = RequestUtil.getParameter(req, "mode");
//
//            SystemLog systemLog = new SystemLog();
//            SystemUser user = (SystemUser) req.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
//            if (null != user) {
//                systemLog.setOperate_login_name(user.getRealName());
//                systemLog.setUser_id(String.valueOf(user.getUserId()));
//            } else {
//                systemLog.setOperate_login_name("");
//            }
//            systemLog.setOperate_platform(operatePlatform);
//            systemLog.setRequest_url(uri);
//            systemLog.setVisit_ip(ip);
//            systemLog.setOperate_time(sdf.format(new Date()));
//            systemLog.setCreate_time(sdf.format(new Date()));
//            //登录、退出
//            if (uri.contains("logout.do")) {
//                systemLog.setModule_name("退出");
//                systemLog.setOperate_type("exit");
//                systemLog.setOperate_context("退出中国继续医学教育网成功！");
//                systemLogManage.addSystemLog(systemLog);
//            } else if (uri.contains("loginAJAX.do")&&null != user) {
//                systemLog.setModule_name("登录");
//                systemLog.setOperate_type("login");
//                systemLog.setOperate_context("登录中国继续医学教育网成功！");
//                systemLogManage.addSystemLog(systemLog);
//            }
//            logger.debug("AuditLogFilter >>>>>>>>>操作平台=" + operatePlatform + ",请求web路径：" + uri + ";mode=" + mode + ",登录账号=" + systemLog.getOperate_login_name() + ",操作时间==" + sdf.format(new Date()) + ",访问IP=" + ip);
//        } catch (RuntimeException e) {
//            logger.error("添加审计日志期间发生异常:" + e.getMessage());
//            e.printStackTrace();
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
