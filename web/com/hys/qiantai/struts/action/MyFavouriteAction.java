package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CVSetFavorites;
import com.hys.qiantai.model.LogStudyStatistics;

/**
 * 
 * @author Tiger
 * @time 2017-1-10
 * 
 * Detail:My favorite action.
 *
 */
public class MyFavouriteAction extends BaseAction {

	private CVSetEntityManage localCVSetEntity;
	
	private ExamPropValFacade localExamPropValFacade;
	
	private CVSetManage localCVSetManage;
	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}
	
	public CVSetEntityManage getLocalCVSetEntity() {
		return localCVSetEntity;
	}

	public void setLocalCVSetEntity(CVSetEntityManage localCVSetEntity) {
		this.localCVSetEntity = localCVSetEntity;
	}
	
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		SystemUser user = LogicUtil.getSystemUser(request);
		
		//When user didn't login.
		if (user == null)
		{
			return "fail";
		}
		
		String method=request.getParameter("method");

		//cv_set_id
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		
		//Delete my favorite cv_set.
		if (method != null && method.equals("delFav"))
		{	
			localCVSetManage.delFav(id, userId);
			StrutsUtil.renderText(response, "success");
			return null;
		}
		else
		{
			//Get my favorite cv_set list.
			List<CVSet> list = new ArrayList<CVSet>();			
			String sign = request.getParameter("sign");
			CVSetFavorites queryCVSetFav = new CVSetFavorites();
			queryCVSetFav.setSystem_user_id(user.getUserId());
			
			PageList page = new PageList();
			int currentPageIndex = PageUtil.getPageIndex(request);
			int pageSize = PageUtil.getPageSize(request);
			
			page.setObjectsPerPage(pageSize);
			page.setPageNumber(currentPageIndex);
		
			if (sign != null && sign.equals("0"))
			{
				queryCVSetFav.setSign("指南");
			}
			else if (sign != null && sign.equals("1"))
			{
				queryCVSetFav.setSign("公需科目");
			}
			
			if(user.getUserConfig()!=null){
				queryCVSetFav.setUserProvinceCode(user.getUserConfig().getUserProvinceId());
			}
			
			//Get size of cv_set list
			int fullInfoSize = localCVSetManage.getFavoriteCVSetListFromUserSize(queryCVSetFav);
			page.setFullListSize(fullInfoSize);
			//Get list of cv_set
			list = localCVSetManage.getFavoriteCVSetListFromUser(queryCVSetFav, page);
			
			for (CVSet cvSet : list)
			{
				//get statistics data
				LogStudyStatistics sts = new LogStudyStatistics();
				sts.setUserId(user.getUserId());
				sts.setCvSetId(cvSet.getId());
				localCVSetEntity.getLogStudyStatistics(sts);
				
				//Get the study percent of user.
				double pers = 0;
				if (sts.getUnits() != 0)
				{
					pers = Math.round(sts.getCompletedLogUnits() * 1000 / sts.getUnits()) /10;
				}				
				cvSet.setStudyProgress(pers);
				
				//Get the name of last study unit.
				if (sts.getLastUnits() != null && sts.getLastUnits().size() > 0)
				{
					cvSet.setLastUnitName(sts.getLastUnits().get(0).getName());
				}
				//项目负责人
	            cvSet.setManagerList(localCVSetManage.getManagerList(cvSet.getId(), 1));
			}
			

			page.setList(list);
			
			request.setAttribute("pageCVSet", page);
			request.setAttribute("userInfo", user);
			request.setAttribute("sign", sign);
			request.setAttribute("CVSet", list);
			return "list";
		}
	}		
}
