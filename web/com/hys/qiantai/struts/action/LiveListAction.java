package com.hys.qiantai.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;

import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.Pager;
import com.hys.framework.web.action.BaseAction;

/**
 * @author zheng
 *
 */
public class LiveListAction extends BaseAction {
    private CVManage localCVManage;
    private CvLiveManage localCvLiveManage;
    private CVSetManage localCVSetManage;
    
    
	
	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}

	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}

	public void setLocalCVManage(CVManage localCVManage) {
        this.localCVManage = localCVManage;
    }

    private CVSetEntityManage localCVSetEntityDAO;
    

    public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}

	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}
    @Override
    protected String actionExecute(ActionMapping arg0, ActionForm arg1,
            HttpServletRequest request, HttpServletResponse arg3) throws Exception {
        Pager<CV> pl = new Pager<CV>();

        String xueke = request.getParameter("xueke");
        String type = request.getParameter("type");
        
        int currentPage = PageUtil.getPageIndex2(request);
        pl.setPageOffset(currentPage);
        pl.setSortDirection(SortOrderEnum.DESCENDING);
        pl.setUrl("liveList.do");
        pl.setQueryString(request);

        CV queryCv = new CV();
        queryCv.setCv_type(2);
        queryCv.setServerName(request.getServerName());
        //queryCv.setBrand(2);// 1 病例 2 直播 3 vr
        // 已发布
        queryCv.setStatus(5);

        // 设置学科
        if (xueke != null && !"".equals(xueke) && !"全部".equals(xueke)) {
            queryCv.setPropName(xueke);
        }

        // 设置直播状态
        if (type != null && !"".equals(type) && !"全部".equals(type)) {
            //直播状态 1 正在直播 2 即将开课 3 已经结束 4观看回放
            if ("正在直播".equals(type)) {
            	queryCv.setType(1);
            } else if ("未开始".equals(type)) {
                queryCv.setType(2);
            } else if ("已结束".equals(type)) {
                queryCv.setType(3);
            } else if("直播回放".equals(type)){
            	queryCv.setType(4);
            }
        }

        localCVManage.queryCVLivePageList(pl, queryCv);
        SystemUser user = LogicUtil.getSystemUser(request);
        List<CV> arry = new ArrayList<CV>();
        if(user!=null && user.getUserId()!=0L){
        	pl.setList(localCVManage.cvForProvinceManager(pl.getList(),user));
        }else{
        	pl.setList(localCVManage.cvForProvinceManager(pl.getList(),null));
        }

        for (int i = 0;i< pl.getList().size();i++) {
        	CV cv = pl.getList().get(i);
        	if (cv.getType()==1) {
        		cv.setNumber(localCvLiveManage.cvLiveNumber(cv.getId()));
			}else if (cv.getType()==3) {
				cv.setCode(localCVSetManage.queryCode(cv.getId()));
				 cv.setName2(localCVSetManage.queryNameCode(cv.getId()));
				cv.setNumber3(localCvLiveManage.cvLiveOverNumber(cv.getId()));
			}else if (cv.getType()==4) {
				cv.setNumber4(localCvLiveManage.cvLiveBackNumber(cv.getId()));
			}else if (cv.getType()==2) {
				cv.setCode(localCVSetManage.queryCode(cv.getId()));
				cv.setName2(localCVSetManage.queryNameCode(cv.getId()));
			}
             
        	
        	cv.setTeacherList(localCVManage.getManagerList(cv.getId()));
            //获取项目费用类型情况
            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
            System.out.println(cv);
            if(queryCv.getType() != null){
	            if(queryCv.getType() == 1){
	            	int typeInt = localCvLiveManage.getcvZBTypeForInt(cv.getId());
	            	if(typeInt == 3 || typeInt == 5){
	            		pl.getList().remove(cv);
	            		i--;
	            	}
	            }else if(queryCv.getType() == 3){
	            	int typeInt = localCvLiveManage.getcvZBTypeForInt(cv.getId());
	            }
            }
        }

        request.setAttribute("xueke", xueke);
        request.setAttribute("type", type);
        request.setAttribute("pager", pl);

        return "list";
    }

	public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}

	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}
    
    
}
