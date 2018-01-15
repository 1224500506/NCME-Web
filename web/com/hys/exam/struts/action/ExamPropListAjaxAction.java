package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.StrutsUtil;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.ExamProp;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.framework.web.action.BaseAction;

public class ExamPropListAjaxAction extends BaseAction {

    private ExamPropValFacade localExamPropValFacade;

    public ExamPropValFacade getLocalExamPropValFacade() {
        return localExamPropValFacade;
    }

    public void setLocalExamPropValFacade(
            ExamPropValFacade localExamPropValFacade) {
        this.localExamPropValFacade = localExamPropValFacade;
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
        String idd = request.getParameter("idd") == null ? "" : request
                .getParameter("idd");
        String area = request.getParameter("area") == null ? "" : request
        		.getParameter("area");
        List<ExamHospital> hospitalList = new ArrayList<ExamHospital>();
        ExamProp prop = new ExamProp();

        if (mode.equals("getICD")) {
            Map<Long, String> resMap = new HashMap<Long, String>();

            String[] idsArray = ids.split(",");

            for (int p = 0; p < idsArray.length; p++) {
                if (idsArray[p].equals("")) {
                    continue;
                }
                List<ExamProp> list;
                ExamPropQuery query = new ExamPropQuery();
                query.setSysPropId(Long.valueOf(idsArray[p]));
                ExamReturnProp rprop = localExamPropValFacade
                        .getNextLevelProp(query);
                list = rprop.getReturnList();

                for (int i = 0; i < list.size(); i++) {
                    List<ExamICD> icdList = list.get(i).getIcdList();
                    for (int j = 0; j < icdList.size(); j++) {
                        resMap.put(icdList.get(j).getId(), icdList.get(j)
                                .getName());
                    }
                }
            }

            List<String> resIds = new ArrayList<String>();
            for (Long pid : resMap.keySet()) {
                resIds.add(resMap.get(pid));
            }
            String resString = resIds.toString();
            resString = resString.substring(1, resString.length() - 1);
            StrutsUtil.renderText(response, resString);
            return null;
        }

        List<ExamProp> list;
        if (id.equals("0")) {
            prop.setType(Integer.valueOf(1));
            //SCP 2017/6/2 只查询为科室的一级学科
            prop.setExt_type(Integer.valueOf(1));
            //暂用 2017/6/23
            prop.setImg_type(Integer.valueOf(idd));
            list = localExamPropValFacade.getPropListByType(prop);

            // List<ExamPropType> tlist =
            // localExamPropValFacade.getExamPropTypeList();

            // request.setAttribute("ctype", tlist);
        } else {

            ExamPropQuery query = new ExamPropQuery();
            query.setSysPropId(Long.valueOf(id));
            if(area!=null&&area.equals("noPage")){
            	query.setPageSize(200);
            }
            //SCP 2017/6/2 只查询为科室的学科
            query.setExt_type(Integer.valueOf(1));
            ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
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

}
