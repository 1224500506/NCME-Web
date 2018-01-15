package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.SystemUser;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.ExpertGroupModel;
import com.hys.qiantai.struts.action.ExpertGroupAction;

public class GetSubjectListAction extends BaseAction {

    private ExamPropValFacade localExamPropValFacade;

	private ExpertGroupManage localExpertgroupManage;

	private ExpertManageManage localExpertManageManage;

	private CVSetManage localCVSetManage;

    private CVManage localCVManage;

    public ExamPropValFacade getLocalExamPropValFacade() {
        return localExamPropValFacade;
    }

    public void setLocalExamPropValFacade(
            ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
    }

    public ExpertGroupManage getLocalExpertgroupManage() {
		return localExpertgroupManage;
	}

	public void setLocalExpertgroupManage(ExpertGroupManage localExpertgroupManage) {
		this.localExpertgroupManage = localExpertgroupManage;
	}

	public ExpertManageManage getLocalExpertManageManage() {
		return localExpertManageManage;
	}

	public void setLocalExpertManageManage(
			ExpertManageManage localExpertManageManage) {
		this.localExpertManageManage = localExpertManageManage;
	}

	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public CVManage getLocalCVManage() {
		return localCVManage;
	}

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}

	protected String actionExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // PageList pl = new PageList();

        String mode = request.getParameter("mode") == null ? "" : request
                .getParameter("mode");
        String ids = request.getParameter("ids") == null ? "" : request
                .getParameter("ids");
        String id = request.getParameter("id") == null ? "0" : request
                .getParameter("id");
        String parentId = request.getParameter("parentId") == null ? "0"
                : request.getParameter("parentId");
        String type = request.getParameter("type") == null ? "0" : request
                .getParameter("type");
        
        if (mode.equals("xkxm")){
        	return xuekedexiangmu(mapping, form, request, response);
        }
        
        List<ExamHospital> hospitalList = new ArrayList<ExamHospital>();
        ExamProp prop = new ExamProp();

        List<ExamProp> list;
        {

            ExamPropQuery query = new ExamPropQuery();
            query.setSysPropId(Long.valueOf(id));
            ExamReturnProp rprop = localExamPropValFacade.getNextLevelPropExam(query);
            list = rprop.getReturnList();

            if (type.equals("1") && list != null && list.size() > 0) {
                for (ExamProp examProp : list) {
                    ExamPropQuery query1 = new ExamPropQuery();
                    query1.setSysPropId(Long.valueOf(examProp.getId()));
                    // ExamReturnProp rprop1=
                    // localExamPropValFacade.getNextLevelProp(query1);

                    // List<ExamProp> listTwoExamProp = rprop1.getReturnList();
                    //
                    // for(ExamProp eProp : listTwoExamProp)
                    // {
                    // eProp.setType(20);
                    // ExamHospital host = new ExamHospital();
                    // {
                    // host.setPropId(Long.valueOf(eProp.getId()));
                    // List<ExamHospital> hospList=
                    // localExamPropValFacade.getHospitalList(host);
                    // hospitalList.addAll(0, hospList);
                    // }
                    // }
                }
            } else {
                if (id.equals("-1") && !parentId.equals("0")) {
                    ExamPropQuery queryParent = new ExamPropQuery();
                    queryParent.setSysPropId(Long.valueOf(parentId));
                    ExamReturnProp rpropParent = localExamPropValFacade
                            .getNextLevelProp(queryParent);

                    list = rpropParent.getReturnList();
                    for (ExamProp examProp : list) {
                        ExamPropQuery query1 = new ExamPropQuery();
                        query1.setSysPropId(Long.valueOf(examProp.getId()));
                        ExamReturnProp rprop1 = localExamPropValFacade
                                .getNextLevelProp(query1);

                        List<ExamProp> listTwoExamProp = rprop1.getReturnList();

                        for (ExamProp eProp : listTwoExamProp) {
                            eProp.setType(20);
                            ExamHospital host = new ExamHospital();
                            {
                                host.setPropId(Long.valueOf(eProp.getId()));
                                List<ExamHospital> hospList = localExamPropValFacade
                                        .getHospitalList(host);
                                hospitalList.addAll(0, hospList);
                            }
                        }
                    }
                }
                // for(ExamProp examProp: list) {
                // examProp.setType(20);
                // ExamHospital host = new ExamHospital();
                // {
                // host.setPropId(Long.valueOf(examProp.getId()));
                // List<ExamHospital> hospList=
                // localExamPropValFacade.getHospitalList(host);
                // hospitalList.addAll(0, hospList);
                // }
                // }
                else if (list != null && list.size() > 0) {
	            	// get related number of xianmu. add by han 2017-01-25
	                for (ExamProp examProp : list) {
	                	if (examProp.getType() != 3){
	                		break;
	                	}
	            		// 优质慕课
	                    List<CVSet> mukeList = getCVSetList(request, 3, examProp.getName());
	                    
	            		//Get the 
	            		List<CVSet> zhinanList = getCVSetList(request, 7, examProp.getName());
	            		
	            		//Get the bingli list.Brand = 1 : bingli.
	            		List<CV> bingliList = getCVList(request, "1", examProp.getName());
	            		
	            		long xiangmus = mukeList.size()+zhinanList.size();//+bingliList.size();
	            		
	            		examProp.setProp_val1(xiangmus);
	            	}
                }
            }
            // List<ExamPropType> tlist =
            // localExamPropValFacade.getExamPropTypeList();
            /*
             * pl.setList(rprop.getReturnList());
             * pl.setObjectsPerPage(pageSize); pl.setPageNumber(currentPage);
             * pl.setFullListSize(rprop.getTotal_count());
             */// request.setAttribute("ctype", tlist);
        }
        JSONObject result = new JSONObject();
//        List<ExamProp> resultList = list;//new ArrayList<ExamProp>();
//        if (list != null && list.size() > 0) {
//            //for (int i = list.size() - 1; i >= 0; i--) {
//            for (int i = 0; i < list.size(); i++) {
//                resultList.add(list.get(i));
//            }
//        } else {
//            resultList = list;
//        }

        result.put("list", list);
        result.put("hospList", hospitalList);
        StrutsUtil.renderText(response, result.toString());
        // request.setAttribute("type", type);
        // request.setAttribute("prop_val1", id);
        // request.setAttribute("propList", list);

        return null;
    }

    
    //查询课程列表
    public List<CV> getCVList(HttpServletRequest request, String brand, String propName) throws Exception {
        PageList<CV> pl = new PageList<CV>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CV queryCv = new CV();
        queryCv.setBrand(brand);
        queryCv.setServerName(request.getServerName());
        
       	queryCv.setPropName(propName);
        //已发布
        queryCv.setStatus(5);

        localCVManage.queryCVPageList2(pl, queryCv);

        for (CV cv : pl.getList()) {
            cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
        }
        //localCVManage.updateCVUnit(cvUnit);
        return pl.getList();
    }
    
    /**
     * @author Tiger.
     * @time 2017-1-13
     * @param request
     * @param edtionId
     * @return
     * @throws Exception
     * 
     * Get the 'zhinan' CV_Set list from user's information.
     */
    // 查询项目列表
    public List<CVSet> getCVSetList(HttpServletRequest request, Integer edtionId, String propName)
            throws Exception {
    	
        PageList<CVSet> pl = new PageList<CVSet>();

        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);

        CVSet query = new CVSet();
        query.setEdtionId(edtionId);
        query.setServerName(request.getServerName());
        //已发布
        query.setStatus(5);
        
       	query.setPropName(propName);

       	localCVSetManage.queryCVSetPageList3(pl, query);

        return pl.getList();
    }
    /**
     * @author Tiger.
     * @time 2017-1-11
     * @param request
     * @param user
     * @return
     * @throws Exception
     * 
     * Detail : Get the bind cv_set list.
     */
    public List<CVSet> getBindList(HttpServletRequest request, SystemUser user) throws Exception
    {			
        PageList<CVSet> pl = new PageList<CVSet>();

        CVSet query = new CVSet();
        query.setServerName(request.getServerName());
        //已发布
        query.setStatus(5);
        query.setStart_date(new Date());
        
        int currentPage = PageUtil.getPageIndex(request);

        pl.setPageNumber(currentPage);
        pl.setObjectsPerPage(4);
        pl.setSortDirection(SortOrderEnum.ASCENDING);
        
        localCVSetManage.getBindList(pl, query, user);
    	return pl.getList();
    }

    /**
	 * @author Han
	 * @time	2017-01-25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * Description get xueke's xiangmu info
	 */
	private String xuekedexiangmu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		String xueke = ParamUtils.getParameter(request, "xueke", null);
		
		ExamProp prop; 
		if(xueke != null && !xueke.equals(""))
		{
			prop = localExamPropValFacade.getSysPropVal(Long.valueOf(xueke));
			request.setAttribute("prop", prop);
		}
		else {
			return "fail";
		}
	
		ExpertGroup group = new ExpertGroup();
		group.setPropIds(xueke);

		List<ExpertGroup> list =  localExpertgroupManage.getExpertGroupList(group);
		for (ExpertGroup e: list){

			Long id = e.getId();
	
			ExpertGroupModel groupModel = new ExpertGroupModel();
			List<ExpertGroupModel> subGroupModel = new ArrayList<ExpertGroupModel>();
			
			// get group include user
			ExpertGroup curGroup =  localExpertgroupManage.getExpertGroup(Long.valueOf(id));	
			if(curGroup==null){
				break;
			}
			groupModel.setGroup(curGroup);
			groupModel.setExpertList(new ArrayList<ExpertInfo>());
			
			ExpertGroup temp = new ExpertGroup();
			
			temp.setParent(curGroup.getId());
			
			// get subgroup which group include
			List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
			for(ExpertGroup s : sublist){
				ExpertGroupModel sub = new ExpertGroupModel();
				
				sub.setGroup(s);
				sub.setExpertList(new ArrayList<ExpertInfo>());
				
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setGroupIds(s.getId().toString());
				//取得专家目录
				List<ExpertInfo> expertlist =  localExpertManageManage.getExpertList(expertInfo);
				for (ExpertInfo ex: expertlist){
//					ex.setPhoto("/" + Constants.UPLOAD_FILE_PATH_EXPERT + "/" + ex.getPhoto());
					if(ex.getOffice().equals(1) || ex.getOffice().equals(2) || ex.getOffice().equals(3)){
						groupModel.getExpertList().add(ex);
					}
					else 
					{	
						sub.getExpertList().add(ex);
					}
				}
				if(sub.getGroup().getLockState() == 1) //启用
				{
					subGroupModel.add(sub);	
				}				
			}
			request.setAttribute("groupInfo", groupModel);
			request.setAttribute("subGroupInfo", subGroupModel);
			//In now condition, pick only 1 data because exist duplicate dates in database
			break;
		}

		// 优质慕课
        List<CVSet> mukeList = getCVSetList(request, 3, prop.getName());
        
		//Get the bind list.
        SystemUser user = new SystemUser();
        user.setUserId(0L);
		List<CVSet> bindList = getBindList(request, user);
		
		//Get the 
		List<CVSet> zhinanList = getCVSetList(request,7, prop.getName());
		
		//Get the bingli list.Brand = 1 : bingli.
		List<CV> bingliList = getCVList(request,"1", prop.getName());
		
		request.setAttribute("mukeList", mukeList);
		request.setAttribute("bindList", bindList);
		request.setAttribute("zhinanList", zhinanList);
		request.setAttribute("bingliList", bingliList);
		return "personal";
	}

}
