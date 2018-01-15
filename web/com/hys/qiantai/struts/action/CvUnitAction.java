package com.hys.qiantai.struts.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.CVUnit;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.exam.util.JsonUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * 
 * cvUnitAction
 * 创建人:程宏业 时间：2017-3-20-下午3:21:40 
 * @version 1.0.0
 *
 */
public class CvUnitAction extends BaseAction{

	 private CVUnitManage cvUnitManage;

	
	 
	 
		@Override
		protected String actionExecute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			// TODO Auto-generated method stub
			
			String mode = RequestUtil.getParameter(request, "mode");
			
			try {
				long unitid= Long.parseLong(request.getParameter("unitid"));
				
				// 查询任务指标大小
				if(mode.equals("Query")){
					return QueryQuota(mapping,form, request,response, unitid);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		
		
			
			return null;
		}
	 
		
		
		/**
		 * 
		 * 查询达标指标情况
		 * 方法名：QueryQuota
		 * 创建人：程宏业
		 * 时间：2017-3-20-下午4:03:55 
		 * 手机:15210211487
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param unitid
		 * @return String
		 * @exception 
		 * @since  1.0.0
		 */
		public String QueryQuota(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,Long unitid){
		
			Map<String ,String > map = new HashMap<String, String>();
			CVUnit cvu = new CVUnit();
			cvu.setId(unitid);
			CVUnit cvu1 = cvUnitManage.findCvunit(cvu);
			if(cvu1!=null && (cvu1.getType()==1 || cvu1.getType()== 4) && cvu1.getQuota()!=null && cvu1.getPoint().equals(1)){
				// 视频
				map.put("quota", cvu1.getQuota().toString());
				map.put("code", "1");
			}else if(cvu1!=null && (cvu1.getType()==2 || cvu1.getType()== 6) && cvu1.getQuota()!=null && cvu1.getPoint().equals(1)){
				// 讨论、病例
				map.put("quota", cvu1.getQuota().toString());
				map.put("code", "2");
			}else if(cvu1!=null && cvu1.getQuota()!=null && cvu1.getPoint().equals(1) && cvu1.getType()==3){
				// 测验
				map.put("quota", cvu1.getQuota().toString());
				map.put("code", "3");
			}else{
				// 其他
				map.put("quota", cvu1.getQuota().toString());
				map.put("code", "4");
			}
			
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 // Getting and Setting 
	 public CVUnitManage getCvUnitManage() {
			return cvUnitManage;
		}

		public void setCvUnitManage(CVUnitManage cvUnitManage) {
			this.cvUnitManage = cvUnitManage;
		}

	
		 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
