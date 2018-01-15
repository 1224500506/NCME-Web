package com.hys.auth.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;

import com.hys.auth.model.NewsModel;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.LogStudyCVSetManage;
import com.hys.exam.service.local.SystemMessageManage;
import com.hys.qiantai.model.SystemMessageModel;

/**
 * 
 * 
 * NewsUtil
 * 创建人:用户消息通知
 * 时间：2017-3-31-下午1:23:48 
 * @version 1.0.0
 *
 */
public class NewsUtil {

	

	
	/***
	 * 
	 * 消息通知
	 * 方法名：CheckStudyTime
	 * 创建人：xuchengfei 
	 * 时间：2017-3-31-下午1:17:08 
	 * @param request void
	 * @exception 
	 * @since  1.0.0
	 */
	
	public  void CheckStudyTime(HttpServletRequest request,NewsModel model){
		try {
			
			SystemUser user = LogicUtil.getSystemUser(request);
			if(user!=null){
				List<LogStudyCVUnit> searchLogStudyCVUnit = model.getLocalLogStudyCVSetManage().searchLogStudyCVUnit(null, null, user.getUserId());
				
				for (LogStudyCVUnit logStudyCVUnit : searchLogStudyCVUnit) {
					
					CVSet cvset = new CVSet();
					cvset.setId(logStudyCVUnit.getLogStudyCvSetId());
					CVSet cvset2 = model.getLocalCVSetManage().viewCVSetById(cvset);
					
					if(cvset2 == null)continue;
						String startDateStr = DateUtils.DateToString(new Date());
						
						//YHQ,2017-08-16
						Date cvSetEndDate = cvset2.getEnd_date() ;
						if(cvSetEndDate == null) continue;
						
						String endDateStr = DateUtils.DateToString(cvset2.getEnd_date());	
						int  time = DateUtils.compareDate(startDateStr, endDateStr, 0);
					    if(time <=30){
					    	SystemMessageModel systeMess = new SystemMessageModel();
					    	systeMess.setSystem_user_id(user.getUserId());
					    	systeMess.setMessage_content("Hi,亲爱的 "+user.getRealName()+"，您正在学习的"+cvset2.getName()+"马上就要过期啦，为避免您的损失，请在"+DateUtils.DateToString(cvset2.getEnd_date())+"前完成该项目的学习！");
					    	systeMess.setMessage_date(new Date());
					    	systeMess.setCv_set_id(cvset2.getId());
					    	if(model.getLocalSystemMesageManage().findMessage(systeMess)){
					    		 model.getLocalSystemMesageManage().SaveMessage(systeMess);
					    	}
					    }
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	
}
