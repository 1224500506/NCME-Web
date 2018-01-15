package com.hys.exam.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.common.util.DateUtils;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.common.util.LogicUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.filter.SensitivewordFilter;
import com.hys.exam.model.CV;
import com.hys.exam.model.CVSet;
import com.hys.exam.model.CVSetUnitDiscuss;
import com.hys.exam.model.CVSetUnitNote;
import com.hys.exam.model.CVUnit;
import com.hys.exam.model.CaseCase;
import com.hys.exam.model.CaseCasePatient;
import com.hys.exam.model.CaseDisease;
import com.hys.exam.model.CaseDiseaseDiagnosis;
import com.hys.exam.model.CasePatient;
import com.hys.exam.model.CasePatientDiagnosis;
import com.hys.exam.model.CasePatientPropVal;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.model.GroupClassInfo;
import com.hys.exam.model.LogStudy;
import com.hys.exam.model.LogStudyCVUnit;
import com.hys.exam.model.PeixunOrg;
import com.hys.exam.model.SystemUser;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.CVManage;
import com.hys.exam.service.local.CVSetEntityManage;
import com.hys.exam.service.local.CVSetManage;
import com.hys.exam.service.local.CVSetUnitDiscussManage;
import com.hys.exam.service.local.CVSetUnitNoteManage;
import com.hys.exam.service.local.CVUnitManage;
import com.hys.exam.service.local.CaseManage;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.exam.service.local.LogStudyCVUnitManage;
import com.hys.exam.service.local.SystemUserManage;
import com.hys.exam.sessionfacade.local.ExamExaminationFacade;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.util.JsonUtil;
import com.hys.exam.utils.PageList;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;
import com.hys.framework.web.action.BaseAction;
import com.hys.qiantai.model.CVSetEntity;

import net.sf.json.JSONObject;
/**
 * 学科管理
 * @author iU
 *
 */
public class EntityDetailAction extends BaseAction {
	private CVSetEntityManage localCVSetEntityDAO;	
	private SystemUserManage localSystemUserManage;
	private CVSetManage localCVSetManage;		
	private ExamPaperFacade examPaperFacade ;	
	private ExamExaminationFacade examExaminationFacade;	
	private CVUnitManage cvUnitManage;	
	//单元笔记
	private CVSetUnitNoteManage localCVSetUnitNoteManage;	
	//单元讨论
	private CVSetUnitDiscussManage localCVSetUnitDiscussManage;
	//病例信息
	CaseManage localCaseManage;
	//学科   relative with 病例
	private ExamPropValFacade localExamPropValFacade;
	//保存学习记录(单元)Service接口
	private LogStudyCVUnitManage localLogStudyCVUnitManage;		
	private CVSetUnitDiscussManage UnitDiscussManage;	
	private CvLiveManage localCvLiveManage;
	private CVManage localCVManage; 
	

	@SuppressWarnings("rawtypes")
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = ParamUtils.getLongParameter(request, "id", 0L);	
		String method = RequestUtil.getParameter(request, "type");
		String isCv = RequestUtil.getParameter(request, "isCv");//判断是否为课程链接请求
		SystemUser user = LogicUtil.getSystemUser(request);
		//获取单元信息,YHQ,2017-07-24
		if(method.equals("queryUnitInfo")){
			return queryUnitInfo(mapping,form,request,response);
		}
		//走进播放页面
		if(method.equals("play")){
			return play(mapping,form,request,response);
		}
		//走进播放页面2
		if(method.equals("play2")){
			if(user == null){
				return "login";
			}
			
			//获取登录用户的省份id,userProviceId=0表示用户没有省份信息
			int userProviceId = localSystemUserManage.getUserProviceId(user.getUserId());
			Long cvSetId = id;
			if(isCv!=null&&isCv.equals("isCv")){
				//根据课程id获取项目id 因为查不到数据的时候会返回异常，此种情况就是直接打开地址，其实就是违法访问；如果是正常访问能查到，课程必须归属于项目才能被显示出来
				//cvSetId 初始化为0，如果查询到，会被重新赋值，如果查询不到，就是异常，直接跳转到首页
				try {
					cvSetId = Long.valueOf(localSystemUserManage.getcvSetIdFromcvId(id));
				} catch (Exception e) {
					return "first";
				}
			}
			//根据用户的省份id和项目id查询项目id，查询关联表，如果关联表有数据，则该用户所归属省份包含在项目开放的省份中
			List cvids = localCVSetEntityDAO.getCVId(cvSetId, userProviceId);
			/*****判断该项目是否属于授权全国的项目，如果是授权全国的项目，即使用户没有省份信息也可以播放begin****/
			//查询省份List
			//ExamProp prop = new ExamProp();
			//prop.setType(20);
			//List<ExamProp> regionList = localExamPropValFacade.getPropListByType(prop);
			//if(cvids.size()==regionList.size()){
			if(cvids!=null&&cvids.size()>0){
				return play2(mapping,form,request,response);
			}else{
				return "first";
			}
			/*****end****/
			/*if(cvids != null && cvids.size() == 1){
				return play2(mapping,form,request,response);
			}else{
				return "first";
			}*/			
		}
		//走进播放页面3
		if(method.equals("play3")){
			//YHQ,2017-07-30,应该与play2一致，照搬play2
			if(user == null){
				return "login";
			}
			//获取登录用户的省份id,userProviceId=0表示用户没有省份信息
			int userProviceId = localSystemUserManage.getUserProviceId(user.getUserId());
			Long cvSetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
			if(isCv!=null&&isCv.equals("isCv")){
				//根据课程id获取项目id 因为查不到数据的时候会返回异常，此种情况就是直接打开地址，其实就是违法访问；如果是正常访问能查到，课程必须归属于项目才能被显示出来
				//cvSetId 初始化为0，如果查询到，会被重新赋值，如果查询不到，就是异常，直接跳转到首页
				try {
					cvSetId = Long.valueOf(localSystemUserManage.getcvSetIdFromcvId(id));
				} catch (Exception e) {
					return "first";
				}
			}
			//根据用户的省份id和项目id查询项目id，查询关联表，如果关联表有数据，则该用户所归属省份包含在项目开放的省份中
			List cvids = localCVSetEntityDAO.getCVId(cvSetId, userProviceId);
			/*****判断该项目是否属于授权全国的项目，如果是授权全国的项目，即使用户没有省份信息也可以播放begin****/
			//查询省份List
			ExamProp prop = new ExamProp();
			prop.setType(20);
			List<ExamProp> regionList = localExamPropValFacade.getPropListByType(prop);
			if(cvids.size()==regionList.size()){
				return play3(mapping,form,request,response);
			}
			/*****end****/
			if(cvids != null && cvids.size() == 1){
				return play3(mapping,form,request,response);
			}else{
				return "first";
			}			
		}
		//拓展阅读 开始
		if(method.equals("reading")){
			//YHQ,2017-07-30,应该与play2一致，照搬play2
			if(user == null){
				return "login";
			}
			//获取登录用户的省份id,userProviceId=0表示用户没有省份信息
			int userProviceId = localSystemUserManage.getUserProviceId(user.getUserId());
			Long cvSetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
			if(isCv!=null&&isCv.equals("isCv")){
				//根据课程id获取项目id 因为查不到数据的时候会返回异常，此种情况就是直接打开地址，其实就是违法访问；如果是正常访问能查到，课程必须归属于项目才能被显示出来
				//cvSetId 初始化为0，如果查询到，会被重新赋值，如果查询不到，就是异常，直接跳转到首页
				try {
					cvSetId = Long.valueOf(localSystemUserManage.getcvSetIdFromcvId(id));
				} catch (Exception e) {
					return "first";
				}
			}
			//根据用户的省份id和项目id查询项目id，查询关联表，如果关联表有数据，则该用户所归属省份包含在项目开放的省份中
			List cvids = localCVSetEntityDAO.getCVId(cvSetId, userProviceId);
			/*****判断该项目是否属于授权全国的项目，如果是授权全国的项目，即使用户没有省份信息也可以播放begin****/
			//查询省份List
			ExamProp prop = new ExamProp();
			prop.setType(20);
			List<ExamProp> regionList = localExamPropValFacade.getPropListByType(prop);
			if(cvids.size()==regionList.size()){
				return reading(mapping,form,request,response);
			}
			/*****end****/
			if(cvids != null && cvids.size() == 1){
				return reading(mapping,form,request,response);
			}else{
				return "first";
			}			
		}
		
		//拓展阅读 结束
		
		//YHQ,2017-07-31,课程预览————后台发送
		if(method.equals("play4View")){
			return play4View(mapping,form,request,response);
		}
		
		//走进播放页面2--预览
		if(method.equals("play2View")){
			return play2View(mapping,form,request,response);
		}
		//走进播放页面3--预览
		if(method.equals("play3View")){
			  return play3View(mapping,form,request,response);
		}
		//根据课程id查询课程主讲教师
		if(method.equals("queryTeacherByCVId")){
			return queryTeacherByCVId(mapping,form,request,response);
		}
		//保存单元笔记
		if(method.equals("addUnitNote")){
			return addUnitNote(mapping,form,request,response);
		}
		//保存扩展阅读
	/*	if (method.equals("saveConten")) {
			return saveConten
		}*/
		//查询发表的笔记信息
		if(method.equals("queryUnitNote")){
			return queryUnitNote(mapping,form,request,response);
		}
		if(method.equals("addUnitTalk")){
			return addUnitTalk(mapping,form,request,response);
		}
		//根据单元id查询组课内容
		if(method.equals("getAJAXContent")){
			return getAJAXContent(mapping,form,request,response);
		}
		//查询发表的讨论信息
		if(method.equals("queryUnitTalk")){
			return queryUnitTalk(mapping,form,request,response);
		}
		
		// 2017.01.26 Add By IE
		//This method's function is that to show CourseTest
		if (method.equals("courseTest")) {
			return courseTest(mapping,form,request,response);
		}
				
		//Add By IE To Do Marking
		if (method.equals("doMarking")) {
			return doMarking(mapping,form,request,response);
		}
		if(method.equals("CaseAnalysis")){
			return CaseAnalysis(mapping,form,request,response);
		}
		if(method.equals("subjectDiscuss")){
			return subjectDiscuss(mapping,form,request,response);
		}
		
		List<CVSet> list = new ArrayList<CVSet>();
		CVSet queryCVSet = new CVSet();	
		queryCVSet.setId(id);
		list = localCVSetManage.getCVSetList(queryCVSet);
		//屏蔽写死的路径
		/*for (CVSet cvSet:list) {
			cvSet.setFile_path("/" + Constants.UPLOAD_FILE_PATH_CVS + "/" + cvSet.getId());
			for(int i=0; i<cvSet.getManagerList().size(); i++) {
				cvSet.getManagerList().get(i).setPhoto("/" + Constants.UPLOAD_FILE_PATH_EXPERT + "/" + cvSet.getManagerList().get(i).getPhoto());
			}
			for(int i=0; i<cvSet.getTeacherList().size(); i++) {
				cvSet.getTeacherList().get(i).setPhoto("/"+Constants.UPLOAD_FILE_PATH_EXPERT+"/" + cvSet.getTeacherList().get(i).getPhoto());
			}
		}*/
		CVSetEntity cvSetEntity1 = new CVSetEntity();
		cvSetEntity1.setCvSetId(id);
		PageList pl = new PageList();
		int currentPage = PageUtil.getPageIndex(request);
		int pageSize = PageUtil.getPageSize(request);
		pl.setObjectsPerPage(pageSize);
		pl.setPageNumber(currentPage);
		pl.setFullListSize(localCVSetEntityDAO.getCVSetEntitySize(cvSetEntity1));
		List<CVSetEntity> entityList = localCVSetEntityDAO.getCVSetEntityInfo(cvSetEntity1,pl);
		for (CVSetEntity cvSetEntity: entityList) {
				SystemUser tempUser = localSystemUserManage.getItemById(cvSetEntity.getUserId());				
				cvSetEntity.setName(tempUser.getRealName());
				//屏蔽写死的路径
				//cvSetEntity.setPhoto_url("/" + Constants.UPLOAD_FILE_PATH_USER + "/"+tempUser.getUser_image());
		}
		if(user != null) {
			CVSetEntity cvSetEntity = new CVSetEntity();
			
			cvSetEntity.setCvSetId(id);
			cvSetEntity.setUserId(user.getUserId());
			
			List<CVSetEntity> cvSetEntityList = localCVSetEntityDAO.getCVSetEntityInfo(cvSetEntity);
			if(cvSetEntityList != null && cvSetEntityList.size() > 0)
				cvSetEntity = cvSetEntityList.get(0);
			request.setAttribute("CVSetEntity", cvSetEntity);			
		}
		
		if(list != null && list.size() >0)
		{
			CVSet cvSet = list.get(0);
			request.setAttribute("CVSet", cvSet);
		}
		request.setAttribute("id", id);
		
		request.setAttribute("user", user);
		request.setAttribute("entityList", entityList);
		return "SUCCESS";
	}
	//拓展阅读
	private String reading(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//与play2完全一致(jsp完全复制play2。jsp)————play2与reading唯一的区别就是：play2是按上次学习的历史记录排序开始学习的，reading是按当前传的单元id开始学习的
		
				//保存查询结果
				List<CV> result = new ArrayList<CV>();
				//保存查询到的课程信息
				List<CV> cvList = new ArrayList<CV>();
				//保存项目信息
				List<CVSet> cvset = new ArrayList<CVSet>();
				CVSet cvs = new CVSet();
				//保存项目负责人信息
				List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
				//保存项目单位信息
				List<PeixunOrg> org = new ArrayList<PeixunOrg>();
				//获取前台参数
				//项目Id
				Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);	
				//课程Id
				Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
				//单元id
				Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
				
				String paramType = request.getParameter("paramType");
				//获取当前登录用户信息
				SystemUser user = LogicUtil.getSystemUser(request);
				 //保存书籍资源
				List<ExamSource>  examSource = cvUnitManage.getSourceVal(unitId);
			    request.setAttribute("sourceVal", examSource);
			    request.setAttribute("cvId", cvId);
			    request.setAttribute("cvsetId", cvsetId);
				//判断是项目跳转还是课程跳转
				if(paramType!=null && paramType.equals("class")){
					//通过课程id查询项目信息
					CVSet cv = localCVSetEntityDAO.queryCVSetListByCvId(cvId);
					if(cv!=null){
						cvList = localCVSetEntityDAO.getCvListByProId(cv.getId());
						//查询项目信息
						cvset = localCVSetEntityDAO.queryCVUnitById(cv.getId());
						if(cvset!=null && cvset.size()>0){
							cvs = cvset.get(0);
						}
						//查询项目负责人信息
						expert = localCVSetEntityDAO.queryProjectFZR(cv.getId());
						//查询项目单位信息
						org = localCVSetEntityDAO.queryProjectOrg(cv.getId());
						if(cvs!=null && !"".equals(cvs)){
							//封面图片
							request.setAttribute("fengmian", cvs.getFile_path());
						}
					}
				}else if(paramType!=null && paramType.equals("project")){
					cvList = localCVSetEntityDAO.getCvListByProId(cvsetId);
					//查询项目信息
					cvset = localCVSetEntityDAO.queryCVUnitById(cvsetId);
					if(cvset!=null && cvset.size()>0){
						cvs = cvset.get(0);
					}
					//查询项目负责人信息
					expert = localCVSetEntityDAO.queryProjectFZR(cvsetId);
					//查询项目单位信息
					org = localCVSetEntityDAO.queryProjectOrg(cvsetId);
					if(cvs!=null && !"".equals(cvs)){
						//封面图片
						request.setAttribute("fengmian", cvs.getFile_path());
					}
				}
				
				//合并查询课程与单元信息
				if(cvList!=null && cvList.size()>0){
					for(int i=0;i<cvList.size();i++){
						CV cv =  cvList.get(i);
						//根据课程信息查询单元信息
						List<CVUnit> unitList = null;
						if(cv.getCv_type() == 2){
							List<CvLive> livelist = localCvLiveManage.queryCvLiveList(cv.getId());
							if(livelist != null && livelist.size() > 0){
								cv.setStartDate(livelist.get(0).getStart_date());
								cv.setEndDate(livelist.get(0).getInvalid_date());
							}
							unitList = localCvLiveManage.getCvUnitForLive(cv.getId());
							if(unitList.size() > 0){
								for(CVUnit unit : unitList){
									//添加直播课程单元为学习单元过滤
									LogStudyCVUnit cvStudyLog = new LogStudyCVUnit();
									cvStudyLog.setCvUnitId(unit.getId());
									cvStudyLog.setSystemUserId(user.getUserId());
									List<LogStudyCVUnit> logUnitListForLive = localLogStudyCVUnitManage.queryLogStudyCVUnitForLiveFilter(cvStudyLog);
									if(logUnitListForLive.size() > 0){
										unit.setLogStatus(null);
									}else{
										LogStudyCVUnit logunit = new LogStudyCVUnit();
										logunit.setCvUnitId(unit.getId());
										logunit.setSystemUserId(user.getUserId());
										List<LogStudyCVUnit> logunitList = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(logunit);
										if(logunitList.size() > 0){
											if(logunitList.get(0).getStatus() == 1){
												unit.setLogStatus(1);
											}else if(logunitList.get(0).getStatus() == 2){
												unit.setLogStatus(2);
											}else{
												unit.setLogStatus(null);
											}
										}
									}
								}
							}
						}else{
							//YHQ,2017-07-30
							unitList = localCVSetEntityDAO.queryUnitListByClassIdAndUserId(cv.getId(), user.getUserId());	
						}
						
						cv.setUnitList(unitList);
						
						//根据课程判断该课程是否为直播课程---taoliang
			            List<CvLive> liveList = localCvLiveManage.queryCvLiveList(cv.getId());
			            if(liveList != null && liveList.size() > 0){
		            		Date now = new Date();
		            		Date startDate = liveList.get(0).getStart_date();
		            		Date endDate = liveList.get(0).getInvalid_date();
		            		if(startDate.getTime() > now.getTime()){
		            			cv.setType(2);
		            		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
		            			cv.setType(1);
		            		}else if(endDate.getTime() < now.getTime()){
		            			cv.setType(3);
		            		}
			            }
			            
			            //获取项目费用类型情况
			            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
			            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
			            
						result.add(cv);
					}
				}
				
				//将查询集合保存到request中
				request.setAttribute("list", result);
				request.setAttribute("project", cvs);
				
				if(user!=null){
					request.setAttribute("userId", user.getUserId());
				}else{
					request.setAttribute("userId", "");
				}
				
				request.setAttribute("currStudyUnitId", unitId);//当前该学习的单元id
				
				if(expert!=null && expert.size()>0){
					request.setAttribute("expert", expert.get(0));
				}
				if(org!=null && org.size()>0){
					request.setAttribute("org", org.get(0));
				}
				
				request.setAttribute("paramType", paramType);						
				
			    return "reading"; 
	}

	/**
	 * @author   杨红强
	 * @time     2017-07-31
	 * @方法说明          课程预览————后台调用
	 */
	protected String play4View(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//保存项目讨论信息
		//获取前台参数
		//课获取前台传递的id
		Long id = ParamUtils.getLongParameter(request, "id", 0L);	
		String paramType = request.getParameter("paramType");
		
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		Long cvId 	= ParamUtils.getLongParameter(request, "cvId", 0L);
		
		//判断是项目跳转还是课程跳转
		if(paramType!=null && paramType.equals("class")){
			/*通过课程id查询项目信息
			CVSet cv = localCVSetEntityDAO.queryCVSetListByCvId(id);
			if(cv != null){
				cvList = localCVSetEntityDAO.getCvListByProId(cv.getId());
				//查询项目信息
				cvset = localCVSetEntityDAO.queryCVUnitById(cv.getId());
				if(cvset!=null && cvset.size()>0){
					cvs = cvset.get(0);
				}
				//查询项目负责人信息
				expert = localCVSetEntityDAO.queryProjectFZR(cv.getId());
				//查询项目单位信息
				org = localCVSetEntityDAO.queryProjectOrg(cv.getId());
				if(cvs!=null && !"".equals(cvs)){
					//封面图片
					request.setAttribute("fengmian", cvs.getFile_path());
				}
			} else {//孤立的课程与项目无关联
				cvList = localCVSetEntityDAO.getCvListByClassId(id) ;
			}*/
			
			cvList = localCVSetEntityDAO.getCvListByClassId(id) ;			
		}else if(paramType!=null && paramType.equals("project")){
			cvList = localCVSetEntityDAO.getCvListByProId(id);
			//查询项目信息
			cvset = localCVSetEntityDAO.queryCVUnitById(id);
			if(cvset!=null && cvset.size()>0){
				cvs = cvset.get(0);
			}
			//查询项目负责人信息
			expert = localCVSetEntityDAO.queryProjectFZR(id);
			//查询项目单位信息
			org = localCVSetEntityDAO.queryProjectOrg(id);
			if(cvs!=null && !"".equals(cvs)){
				//封面图片
				request.setAttribute("fengmian", cvs.getFile_path());
			}
		}		
		
        //合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//根据课程信息查询单元信息																	
				List<CVUnit> unitList = null;
				if(cv.getCv_type() == 2){
					List<CvLive> livelist = localCvLiveManage.queryCvLiveList(cv.getId());
					if(livelist != null && livelist.size() > 0){
						cv.setStartDate(livelist.get(0).getStart_date());
						cv.setEndDate(livelist.get(0).getInvalid_date());
					}
					unitList = localCvLiveManage.getCvUnitForLive(cv.getId());					
				}else{
					//YHQ,2017-07-30
					unitList = localCVSetEntityDAO.queryUnitListByClassIdAndUserId(cv.getId(), null);			
				}
				
				cv.setUnitList(unitList);
				
				//根据课程判断该课程是否为直播课程---taoliang
	            List<CvLive> liveList = localCvLiveManage.queryCvLiveList(cv.getId());
	            if(liveList != null && liveList.size() > 0){
            		Date now = new Date();
            		Date startDate = liveList.get(0).getStart_date();
            		Date endDate = liveList.get(0).getInvalid_date();
            		if(startDate.getTime() > now.getTime()){
            			cv.setType(2);
            		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
            			cv.setType(1);
            		}else if(endDate.getTime() < now.getTime()){
            			cv.setType(3);
            		}
	            }
	            
				result.add(cv);
			}
		}
		
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);		
		
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
		request.setAttribute("paramType", paramType);
		request.setAttribute("unitId", unitId);
		request.setAttribute("cvId", cvId);		
		
	    return "play4View";
	}
	
	/**
	 * @author 杨红强
	 * @time   2017-07-24
	 * 方法说明   根据单元Id和userId查询单元的学习记录信息、单元信息	 
	 */	
	private String queryUnitInfo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		String mode = request.getParameter("mode");
		SystemUser user = LogicUtil.getSystemUser(request);
		//返回信息
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		if (mode != null && mode.equals("view")) {//预览而已
			CVUnit unitObj = localCVSetEntityDAO.queryCVUnitLogStudyByUnitIdAndUserId(unitId, null) ;			
			resultMap.put("unitObj", unitObj);
			resultMap.put("message", "success");
		} else {
			if (user != null) {
				CVUnit unitObj = localCVSetEntityDAO.queryCVUnitLogStudyByUnitIdAndUserId(unitId, user.getId()) ;			
				resultMap.put("unitObj", unitObj);
				resultMap.put("message", "success");
			} else {
				resultMap.put("message", "noLogin");
			}
		}
		
		String resultJsonStr = JsonUtil.map2json(resultMap) ;
		StrutsUtil.renderText(response, resultJsonStr);
		return null;
	}
	
	/**
	 * @author IE
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return	String
	 */
	
	private String doMarking(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//Get the answer result of paper.
		String inputTestResult = request.getParameter("testResult");
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		String testResult[] = inputTestResult.split("/");
		
		JSONObject json = new JSONObject();
		LogStudy logStudy = new LogStudy();
		LogStudyCVUnit logStudyCVUnit = new LogStudyCVUnit();
		
		logStudy.setCv_id(cvId);
		
		logStudy.setCv_unit_id(unitId);
	/*
		logStudyCVUnit.setCvId(cvId);
		logStudyCVUnit.setCvUnitId(unitId);
		logStudyCVUnit.setLogStudyCvSetId(cvsetId);*/
		//Get the current SystemUserId.
		//String assignName = request.getSession().getAttribute("SPRING_SECURITY_LAST_USERNAME").toString();
		SystemUser assignUser = LogicUtil.getSystemUser(request);
		Long currentSystemId = localCVSetEntityDAO.getCurrentSystemUserIDByName(assignUser.getRealName());
		
		logStudy.setSystem_user_id(currentSystemId);
		/*logStudyCVUnit.setSystemUserId(currentSystemId);
		localLogStudyCVUnitManage.addLogStudyCVUnit(logStudyCVUnit);*/
		//Change By IE 2017.02.05
//		Long paperId = localCVSetEntityDAO.getPaperIdByUnitId(unitId);
		Long paperId = null;
		
		logStudy.setTestPaper_id(paperId);
		logStudy.setLog_study_cv_unit_id(unitId);
		Map<String, ExamQuestion> questionMap = new HashMap <String, ExamQuestion>();
		ExamPaper paper = new ExamPaper();
			
		paper = examPaperFacade.getExamPaperById(paperId);
		if (paper.getExamQuestionList() != null) {
			for (ExamQuestion quest : paper.getExamQuestionList()) {
				 questionMap.put(quest.getId().toString(), quest);
			 }
		}
			
		
		//Mark examination from answer.
		int score=0;
		String question_id ="";
		int correctCount =0;
		List<JSONObject> questInfo = new ArrayList<JSONObject>();
		for (int i = 0 ; i < testResult.length ; i++) {
			
			String tempResult[] = testResult[i].split(",");
			logStudy.setSelect_result(testResult[i]);
			
			if (tempResult.length > 1 && !StringUtils.checkNull(tempResult[0]) && !StringUtils.checkNull(tempResult[1])) {
				ExamQuestion curQuestion = questionMap.get(tempResult[0]);
				logStudy.setQuestionLabel_id(curQuestion.getQuestion_label_id());
				logStudy.setSeq(curQuestion.getSeq());
				//Convert answers to list. 
				List<String> answerList = new ArrayList<String>();
				if (!StringUtils.checkNull(tempResult[1])) {
					answerList = StringUtils.stringToArrayList(tempResult[1], "_");
				}
				
				//Check answers.
				boolean answerFlag = true;
				String answerStr = "";
				int charCode = 0;
				for (ExamQuestionKey key : curQuestion.getQuestionKeyList()){
					
					logStudy.setQuestion_id(key.getQuestion_id());
					if (key.getIsnot_true() != 0) {
						answerStr += Constants.RESULT[charCode] + ",";
						if (!answerList.contains(key.getId().toString())) {
							answerFlag = false;
						}
					}
					charCode ++;
				}
				if (answerFlag == true) {
					correctCount ++;
					score += curQuestion.getQuestion_score();
					logStudy.setScore(curQuestion.getQuestion_score());
					logStudy.setIsNot_Right(1);
				} else {
					logStudy.setIsNot_Right(0);
					logStudy.setScore(0);
				}
				
				if (answerStr.length() > 0){
					answerStr = answerStr.substring(0, answerStr.length() - 1);
				}
				
				//Set the correct answers and analysis of question.
				JSONObject jsonQues = new JSONObject();
				jsonQues.put("questionId", curQuestion.getId());
				jsonQues.put("correctSolution", answerStr);
				jsonQues.put("analysis", curQuestion.getAnalyse());
				
				logStudy.setQuestion_Result(answerStr);
				
			
				logStudy.setRight_count(String.valueOf(correctCount));
				
				localCVSetEntityDAO.setLogStudyCVUnitExam(logStudy);
				questInfo.add(jsonQues);
			}
		}
		
		json.put("score", score);
		json.put("info", questInfo);
		StrutsUtil.renderText(response, json.toString());
		return null;
	}
	
	/**
	 * 
	 * @param Actionmapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String
	 * @author IE
	 * @time 2017-01-26
	 * @Explain View CourseTestShow
	 */
	@SuppressWarnings("unused")
	private String courseTest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Long id = ParamUtils.getLongParameter(request, "id", 0L);
		
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//获取前台参数
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);	
		//课程Id
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		//单元id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		//查询课程信息
		cvList = localCVSetEntityDAO.getCvListByProId(cvsetId);
		//查询项目信息
		cvset = localCVSetEntityDAO.queryCVUnitById(cvsetId);
		if(cvset!=null && cvset.size()>0){
			cvs = cvset.get(0);
		}
		//查询项目负责人信息
		expert = localCVSetEntityDAO.queryProjectFZR(cvsetId);
		//查询项目单位信息
		org = localCVSetEntityDAO.queryProjectOrg(cvsetId);
		if(cvs!=null && !"".equals(cvs)){
			//封面图片
			request.setAttribute("fengmian", cvs.getFile_path());
		}
        //合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//Get current cv.
				if (cv.getId().equals(cvId)){
					request.setAttribute("curCV", cv);
				}
				//根据课程信息查询单元信息
				List<CVUnit> unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
				cv.setUnitList(unitList);
				result.add(cv);
			}
		}
		
		//Get the current unit.
		CVUnit curUnit = localCVSetEntityDAO.getCVUnitByUnitId(unitId);
		
		//Get the test paper and question list.Add by Tiger.
//		Long paperId = localCVSetEntityDAO.getPaperIdByUnitId(unitId);
		Long paperId= null;
		
//		ExamPaper paper = examPaperFacade.getExamPaperById(paperId);
//		request.setAttribute("paper", paper);
//					
//		if(paper.getExamQuestionList() != null){
//			List<ExamQuestion> quesList = paper.getExamQuestionList();
//			request.setAttribute("quesList", quesList);
//		}
		
		
		
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);
		//项目Id
		request.setAttribute("cvsetId", cvsetId);
		//课程Id
		request.setAttribute("cvId", cvId);
		//单元Id
		request.setAttribute("unitId", unitId);
		
		//Current Unit
		request.setAttribute("curUnit", curUnit);
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
		return "courseTest";
	}

	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   ZJG
	 * @time     2016-12-22
	 * @方法说明  跳转播放页面
	 */
	@SuppressWarnings("unused")
	protected String play(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//获取前台参数
		//课程id
		Long id = ParamUtils.getLongParameter(request, "id", 0L);	
		//根据课程id查询课程详情
	    return "play";
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   ZJG
	 * @time     2016-12-22
	 * @方法说明  跳转播放页面
	 */
	protected String play2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){		
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//保存项目讨论信息
		//获取前台参数
		//课获取前台传递的id
		Long id = ParamUtils.getLongParameter(request, "id", 0L);	
		String paramType = request.getParameter("paramType");
		//获取当前登录用户信息
		SystemUser user = LogicUtil.getSystemUser(request);
		
		//判断是项目跳转还是课程跳转
		if(paramType!=null && paramType.equals("class")){
			//通过课程id查询项目信息
			CVSet cv = localCVSetEntityDAO.queryCVSetListByCvId(id);
			if(cv!=null){
				cvList = localCVSetEntityDAO.getCvListByProId(cv.getId());
				//查询项目信息
				cvset = localCVSetEntityDAO.queryCVUnitById(cv.getId());
				if(cvset!=null && cvset.size()>0){
					cvs = cvset.get(0);
				}
				//查询项目负责人信息
				expert = localCVSetEntityDAO.queryProjectFZR(cv.getId());
				//查询项目单位信息
				org = localCVSetEntityDAO.queryProjectOrg(cv.getId());
				if(cvs!=null && !"".equals(cvs)){
					//封面图片
					request.setAttribute("fengmian", cvs.getFile_path());
				}
			}
		}else if(paramType!=null && paramType.equals("project")){
			cvList = localCVSetEntityDAO.getCvListByProId(id);
			//查询项目信息
			cvset = localCVSetEntityDAO.queryCVUnitById(id);
			if(cvset!=null && cvset.size()>0){
				cvs = cvset.get(0);
			}
			//查询项目负责人信息
			expert = localCVSetEntityDAO.queryProjectFZR(id);
			//查询项目单位信息
			org = localCVSetEntityDAO.queryProjectOrg(id);
			if(cvs!=null && !"".equals(cvs)){
				//封面图片
				request.setAttribute("fengmian", cvs.getFile_path());
			}
		}
		/*else if(paramType!=null && paramType.equals("class")){
			cvList = localCVSetEntityDAO.getCvListByClassId(id);
			if(cvList!=null && cvList.size()>0){
				//封面图片
				request.setAttribute("fengmian", cvList.get(0).getFile_path());
			}
		}*/
		
        //合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//根据课程信息查询单元信息																	
				List<CVUnit> unitList = null;
				if(cv.getCv_type() == 2){
					List<CvLive> livelist = localCvLiveManage.queryCvLiveList(cv.getId());
					if(livelist != null && livelist.size() > 0){
						cv.setStartDate(livelist.get(0).getStart_date());
						cv.setEndDate(livelist.get(0).getInvalid_date());
					}
					unitList = localCvLiveManage.getCvUnitForLive(cv.getId());
					if(unitList.size() > 0){
						for(CVUnit unit : unitList){
							//添加直播课程单元为学习单元过滤
							LogStudyCVUnit cvStudyLog = new LogStudyCVUnit();
							cvStudyLog.setCvUnitId(unit.getId());
							cvStudyLog.setSystemUserId(user.getUserId());
							List<LogStudyCVUnit> logUnitListForLive = localLogStudyCVUnitManage.queryLogStudyCVUnitForLiveFilter(cvStudyLog);
							if(logUnitListForLive.size() > 0){
								unit.setLogStatus(null);
							}else{
								LogStudyCVUnit logunit = new LogStudyCVUnit();
								logunit.setCvUnitId(unit.getId());
								logunit.setSystemUserId(user.getUserId());
								List<LogStudyCVUnit> logunitList = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(logunit);
								if(logunitList.size() > 0){
									if(logunitList.get(0).getStatus() == 1){
										unit.setLogStatus(1);
									}else if(logunitList.get(0).getStatus() == 2){
										unit.setLogStatus(2);
									}else{
										unit.setLogStatus(null);
									}
								}
							}
						}
					}
				}else{
					//YHQ,2017-07-30
					unitList = localCVSetEntityDAO.queryUnitListByClassIdAndUserId(cv.getId(), user.getUserId());			
				}
				
				cv.setUnitList(unitList);
				
				//根据课程判断该课程是否为直播课程---taoliang
	            List<CvLive> liveList = localCvLiveManage.queryCvLiveList(cv.getId());
	            if(liveList != null && liveList.size() > 0){
            		Date now = new Date();
            		Date startDate = liveList.get(0).getStart_date();
            		Date endDate = liveList.get(0).getInvalid_date();
            		if(startDate.getTime() > now.getTime()){
            			cv.setType(2);
            		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
            			cv.setType(1);
            		}else if(endDate.getTime() < now.getTime()){
            			cv.setType(3);
            		}
	            }
	            
				result.add(cv);
			}
		}
		
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);
		if(user!=null){
			request.setAttribute("userId", user.getUserId());
						
			//YHQ,2017-07-24
			List<CVUnit> unitLogStudyList = null ;
			
			if(paramType != null && paramType.equals("class")){
				unitLogStudyList = localCVSetEntityDAO.queryUnitLogStudyByCvIdAndUserId(id, user.getUserId());
			}else if(paramType != null && paramType.equals("project")){
				boolean trueOrfalse = localCVManage.isExistOtherCVForLiveProject1(id);
				if(trueOrfalse == true){
					unitLogStudyList = localCVSetEntityDAO.queryUnitLogStudyByCvSetIdAndUserIdForLive(id, user.getUserId());
				}else{
					unitLogStudyList = localCVSetEntityDAO.queryUnitLogStudyByCvSetIdAndUserId(id, user.getUserId());
				}
			}
			
			Long cuUnitIdCurrStudy = null ;//当前应该学习的课程单元id
			Long cuUnitIdlogStatus1 = null ;//学习log状态为1的课程单元id
			Long cuUnitIdNull = null ;//学习log状态为null的课程单元id
			Long cuUnitId0 = null ;//第一个课程单元id
			Integer iStatus1Tmp = null ;
			
			if (unitLogStudyList != null) {
				for (int cuItmp = 0 ; cuItmp < unitLogStudyList.size() ; cuItmp++) {
					if (cuItmp == 0) cuUnitId0 = unitLogStudyList.get(cuItmp).getId() ;
					
					Integer tmpUnitLogStudyStatus = unitLogStudyList.get(cuItmp).getLogStatus() ;
					if (tmpUnitLogStudyStatus != null && tmpUnitLogStudyStatus == 1) {
						cuUnitIdlogStatus1 = unitLogStudyList.get(cuItmp).getId() ;
						break ;
					}
					if (tmpUnitLogStudyStatus != null && tmpUnitLogStudyStatus == 2 && iStatus1Tmp == null) {
						iStatus1Tmp = cuItmp ;
					}
					if (iStatus1Tmp != null && cuItmp > iStatus1Tmp && tmpUnitLogStudyStatus == null && cuUnitIdNull == null) {
						cuUnitIdNull = unitLogStudyList.get(cuItmp).getId() ;
					}
				}
			}						
			if (cuUnitIdlogStatus1 != null) {
				cuUnitIdCurrStudy = cuUnitIdlogStatus1 ;
			} else {
				cuUnitIdCurrStudy = cuUnitIdNull ;
			}
			if (cuUnitIdCurrStudy == null) cuUnitIdCurrStudy = cuUnitId0 ;
			
			request.setAttribute("currStudyUnitId", cuUnitIdCurrStudy);
		}else{
			request.setAttribute("userId", "");
		}
		
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
		request.setAttribute("paramType", paramType);
	    return "play2";
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   ZJG
	 * @time     2016-12-22
	 * @方法说明  跳转播放页面
	 */
	protected String play3(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//YHQ,2017-07-30,重写，与play2完全一致(jsp完全复制play2。jsp)————play2与play3唯一的区别就是：play2是按上次学习的历史记录排序开始学习的，play3是按当前传的单元id开始学习的
		
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//获取前台参数
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);	
		//课程Id
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		//单元id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		
		
		String paramType = request.getParameter("paramType");
		//获取当前登录用户信息
		SystemUser user = LogicUtil.getSystemUser(request);
		
		//判断是项目跳转还是课程跳转
		if(paramType!=null && paramType.equals("class")){
			//通过课程id查询项目信息
			CVSet cv = localCVSetEntityDAO.queryCVSetListByCvId(cvId);
			if(cv!=null){
				cvList = localCVSetEntityDAO.getCvListByProId(cv.getId());
				//查询项目信息
				cvset = localCVSetEntityDAO.queryCVUnitById(cv.getId());
				if(cvset!=null && cvset.size()>0){
					cvs = cvset.get(0);
				}
				//查询项目负责人信息
				expert = localCVSetEntityDAO.queryProjectFZR(cv.getId());
				//查询项目单位信息
				org = localCVSetEntityDAO.queryProjectOrg(cv.getId());
				if(cvs!=null && !"".equals(cvs)){
					//封面图片
					request.setAttribute("fengmian", cvs.getFile_path());
				}
			}
		}else if(paramType!=null && paramType.equals("project")){
			cvList = localCVSetEntityDAO.getCvListByProId(cvsetId);
			//查询项目信息
			cvset = localCVSetEntityDAO.queryCVUnitById(cvsetId);
			if(cvset!=null && cvset.size()>0){
				cvs = cvset.get(0);
			}
			//查询项目负责人信息
			expert = localCVSetEntityDAO.queryProjectFZR(cvsetId);
			//查询项目单位信息
			org = localCVSetEntityDAO.queryProjectOrg(cvsetId);
			if(cvs!=null && !"".equals(cvs)){
				//封面图片
				request.setAttribute("fengmian", cvs.getFile_path());
			}
		}
		
		//合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//根据课程信息查询单元信息
				List<CVUnit> unitList = null;
				if(cv.getCv_type() == 2){
					List<CvLive> livelist = localCvLiveManage.queryCvLiveList(cv.getId());
					if(livelist != null && livelist.size() > 0){
						cv.setStartDate(livelist.get(0).getStart_date());
						cv.setEndDate(livelist.get(0).getInvalid_date());
					}
					unitList = localCvLiveManage.getCvUnitForLive(cv.getId());
					if(unitList.size() > 0){
						for(CVUnit unit : unitList){
							//添加直播课程单元为学习单元过滤
							LogStudyCVUnit cvStudyLog = new LogStudyCVUnit();
							cvStudyLog.setCvUnitId(unit.getId());
							cvStudyLog.setSystemUserId(user.getUserId());
							List<LogStudyCVUnit> logUnitListForLive = localLogStudyCVUnitManage.queryLogStudyCVUnitForLiveFilter(cvStudyLog);
							if(logUnitListForLive.size() > 0){
								unit.setLogStatus(null);
							}else{
								LogStudyCVUnit logunit = new LogStudyCVUnit();
								logunit.setCvUnitId(unit.getId());
								logunit.setSystemUserId(user.getUserId());
								List<LogStudyCVUnit> logunitList = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(logunit);
								if(logunitList.size() > 0){
									if(logunitList.get(0).getStatus() == 1){
										unit.setLogStatus(1);
									}else if(logunitList.get(0).getStatus() == 2){
										unit.setLogStatus(2);
									}else{
										unit.setLogStatus(null);
									}
								}
							}
						}
					}
				}else{
					//YHQ,2017-07-30
					unitList = localCVSetEntityDAO.queryUnitListByClassIdAndUserId(cv.getId(), user.getUserId());	
				}
				
				cv.setUnitList(unitList);
				
				//根据课程判断该课程是否为直播课程---taoliang
	            List<CvLive> liveList = localCvLiveManage.queryCvLiveList(cv.getId());
	            if(liveList != null && liveList.size() > 0){
            		Date now = new Date();
            		Date startDate = liveList.get(0).getStart_date();
            		Date endDate = liveList.get(0).getInvalid_date();
            		if(startDate.getTime() > now.getTime()){
            			cv.setType(2);
            		}else if(startDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()){
            			cv.setType(1);
            		}else if(endDate.getTime() < now.getTime()){
            			cv.setType(3);
            		}
	            }
	            
	            //获取项目费用类型情况
	            cv.setCost_type(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost_type());
	            cv.setCost(localCVSetEntityDAO.queryCVSetListByCvId(cv.getId()).getCost());
	            
				result.add(cv);
			}
		}
		
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);
		
		if(user!=null){
			request.setAttribute("userId", user.getUserId());
		}else{
			request.setAttribute("userId", "");
		}
		
		request.setAttribute("currStudyUnitId", unitId);//当前该学习的单元id
		
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
		request.setAttribute("paramType", paramType);						
		
	    return "play2"; //"play3";统一转向play2.jsp，统一维护，YHQ，2017-08-07
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   ZJG
	 * @time     2017-02-15
	 * @方法说明  根据课程Id查询课程主讲教师
	 */
	protected String queryTeacherByCVId(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//返回数据
		Map<String,Object> map = new HashMap<String,Object>();
		//课程Id
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);	
		//通过课程Id查询教师信息
		ExpertInfo expert = localCVSetEntityDAO.queryTeacherByCVId(cvId);
		if(expert!=null && expert.getName()!=null){
			map.put("message", "success");
			map.put("teacherName", expert.getName());
		}else{
			map.put("message", "fail");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
	    return null;
	}
	
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明      添加单元笔记功能
	 */
	protected String addUnitNote(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//单元Id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		//note type
		Long note_type = ParamUtils.getLongParameter(request, "note_type", 0L);
		//笔记内容
		String content = request.getParameter("content");
		//当前登录人
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化单元笔记对象
		CVSetUnitNote note = new CVSetUnitNote();
		note.setCvSetId(cvsetId);
		note.setCvUnitId(unitId);
		note.setSystemUserId(user.getUserId());
		note.setNoteContent(content);
		note.setNote_type(note_type);
	    //执行保存笔记
		localCVSetUnitNoteManage.addCVSetUnitNote(note);
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明      添加单元讨论功能————课程单元的讨论为自由讨论与任务点考核无关
	 */
	protected String addUnitTalk(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//单元Id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		//1.普通讨论 2.话题讨论 3.病例讨论
		Long discuss_type = ParamUtils.getLongParameter(request, "discussType", 0L);
		//笔记内容
		String content = request.getParameter("content");
		//当前登录人
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化单元讨论对象
		CVSetUnitDiscuss talk = new CVSetUnitDiscuss();
		talk.setCvsetId(cvsetId);
		talk.setCvUnitId(unitId);
		talk.setSystemUserId(user.getUserId());
		talk.setDiscuss_type(discuss_type);
		talk.setDiscussContent(content);
	    //执行保存讨论
		localCVSetUnitDiscussManage.addCVSetUnitDiscuss(talk);
	    
		CVUnit cv = new CVUnit();
		cv.setId(unitId);
		CVUnit cv2 = cvUnitManage.findCvunit(cv);
		
		/*YHQ,2017-08-01,掉坑里了,课程单元的讨论为自由讨论与任务点考核无关艹艹艹艹艹,2017-07-27
		boolean unitPassFlag = false ;
		if (cv2 != null) {
			Integer unitPoint = cv2.getPoint() ;			
			if (unitPoint != null && unitPoint == 1) {
				Double unitQuota = cv2.getQuota() ;
				if (unitQuota != null) {
					CVSetUnitDiscuss discuss = new CVSetUnitDiscuss();					
					discuss.setCvUnitId(unitId);
					discuss.setSystemUserId(user.getUserId());
					long count = UnitDiscussManage.CountCVSetUnitDiscuss(discuss);
					if (count >= unitQuota) {
						unitPassFlag = true ;
					}
				} else {
					unitPassFlag = true ;
				}
			} else {
				unitPassFlag = true ;
			}
		}		
		if (unitPassFlag) {
			LogStudyCVUnit cvs = new LogStudyCVUnit();
			cvs.setSystemUserId(user.getId());
			cvs.setCvUnitId(unitId);
			cvs.setStatus(2);
			localLogStudyCVUnitManage.updLogStudyCVUnitById(cvs);
			map.put("isPass", "1");
		} else {
			map.put("isPass", "0");
		}
		*/
				
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明      查询发表的单元笔记信息
	 */
	protected String queryUnitNote(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();		
		//笔记集合
		List<CVSetUnitNote> list = new ArrayList<CVSetUnitNote>();
		//单元Id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//note type
		Long note_type = ParamUtils.getLongParameter(request, "note_type", 0L);
		//当前登录人
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化单元笔记对象
		CVSetUnitNote note = new CVSetUnitNote();
		note.setCvUnitId(unitId);
		note.setSystemUserId(user.getUserId());	
		note.setNote_type(note_type);
		note.setCvSetId(cvsetId);
		//执行查询
		list = localCVSetUnitNoteManage.queryUnitNote(note);
		//对笔记进行敏感词过滤
		SensitivewordFilter filter = new SensitivewordFilter();
		for (CVSetUnitNote unitNote:list) {
			String content = unitNote.getNoteContent();
			String noteContent = filter.replaceSensitiveWord(content,1,"*");
			unitNote.setNoteContent(noteContent);
		}
		//----过滤结束----
		map.put("message", "success");
		map.put("list", list);
		//当前登录人员姓名
		map.put("userName", user.getRealName());
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   张建国
	 * @time     2017-01-17
	 * 方法说明      查询发表的单元讨论信息
	 */
	protected String queryUnitTalk(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		//讨论集合
		List<CVSetUnitDiscuss> list = new ArrayList<CVSetUnitDiscuss>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//单元Id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		//1.普通讨论 2.话题讨论 3.病例讨论
		Long discuss_type = ParamUtils.getLongParameter(request, "discussType", 0L);
		//当前登录人
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化单元讨论对象
		CVSetUnitDiscuss talk = new CVSetUnitDiscuss();
		talk.setCvUnitId(unitId);
		talk.setSystemUserId(user.getUserId());
		talk.setDiscuss_type(discuss_type);
		talk.setCvsetId(cvsetId);
		//执行查询
		list = localCVSetUnitDiscussManage.queryUnitTalk(talk);
		//对讨论进行敏感词过滤
		SensitivewordFilter filter = new SensitivewordFilter();
		for (CVSetUnitDiscuss discuss:list) {
			String content = discuss.getDiscussContent();
			String discussContent = filter.replaceSensitiveWord(content,1,"*");
			discuss.setDiscussContent(discussContent);
		}
		//----过滤结束----
		map.put("message", "success");
		map.put("list", list);
		//当前登录人员姓名
		map.put("userName", user.getRealName());
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   ZJG
	 * @time     2016-12-22
	 * @方法说明  根据单元id查询组课信息级课程信息
	 */
	protected String getAJAXContent(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);	
		//查询组课信息
		List<GroupClassInfo> list = localCVSetEntityDAO.queryGroupByUnitId(unitId);
		List<CV> cvList = new ArrayList<CV>();
		Map<String,Object> map = new HashMap<String,Object>();
		if(list!=null && list.size()>0){
			map.put("message", "success");
			map.put("list", list);
			//查询课程信息
			cvList = localCVSetEntityDAO.queryCVById(list.get(0).getClassParentId());
			if(cvList!=null && cvList.size()>0){
				map.put("cv", cvList.get(0));
			}else{
				map.put("cv", null);
			}
		}else{
			map.put("message", "fail");
		}
		
		//YHQ，查询学习记录id，2017-03-10
		map.put("videoLogStudyId", 0L);
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user != null && user.getUserId() > 0L){			
			LogStudyCVUnit cvsObj = new LogStudyCVUnit();		
			cvsObj.setCvUnitId(unitId);
			cvsObj.setSystemUserId(user.getUserId());
			List<LogStudyCVUnit> lscuList = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvsObj);
	        if(lscuList != null && lscuList.size()>0){           
	        	map.put("videoLogStudyId", lscuList.get(0).getId());
	        }
		}
		
		
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	/**
	 * @author M
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String
	 */
	private String CaseAnalysis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//
		CVSetUnitDiscuss discuss = new CVSetUnitDiscuss();	
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);	
		//课程Id
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		//单元id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);		
		//查询课程信息		
		cvList = localCVSetEntityDAO.getCvListByProId(cvsetId);
		//查询项目信息
		cvset = localCVSetEntityDAO.queryCVUnitById(cvsetId);
		if(cvset!=null && cvset.size()>0){
			cvs = cvset.get(0);
		}
		//Get the current unit.
		CVUnit curUnit = localCVSetEntityDAO.getCVUnitByUnitId(unitId);
		
		//查询项目负责人信息
		expert = localCVSetEntityDAO.queryProjectFZR(cvsetId);
		//查询项目单位信息
		org = localCVSetEntityDAO.queryProjectOrg(cvsetId);
		if(cvs!=null && !"".equals(cvs)){
			//封面图片
			request.setAttribute("fengmian", cvs.getFile_path());
		}
        //合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//Get current cv.
				if (cv.getId().equals(cvId)){
					request.setAttribute("curCV", cv);
				}
				//根据课程信息查询单元信息
				List<CVUnit> unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
				cv.setUnitList(unitList);
				result.add(cv);
			}
		}
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);
		//项目Id
		request.setAttribute("cvsetId", cvsetId);
		//课程Id
		request.setAttribute("cvId", cvId);
		//单元Id
		request.setAttribute("unitId", unitId);
		//current unit
		request.setAttribute("curUnit", curUnit);
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
		//caseInfoString	
		discuss.setCvsetId(cvsetId);
		discuss.setCvUnitId(unitId);
//		Long caseId = localCVSetUnitDiscussManage.getCaseId(discuss);
		Long caseId = null;
		String courseString = "";
		String courseIds = "";
		String subjectString = "";
		String subjectids = "";
		String ICD10_names = "";
		CaseCasePatient newCasePatient = new CaseCasePatient();
		if(caseId != null && !caseId.equals(""))
		{
			CaseCase curCase= localCaseManage.getCaseById(Long.valueOf(caseId));
			CasePatient patient = localCaseManage.getPatientById(curCase.getpId());
			CaseDisease disease = localCaseManage.getDiseaseById(curCase.getDiseaseId());
			
			String pCertificate = patient.getCertificate();
			Date birthday = null;
			if(pCertificate != null && pCertificate.length() == 18)
			{
				String Certificate = pCertificate.substring(6, 14);
				birthday = DateUtil.parse(Certificate, "yyyyMMdd");
			}
			
			if(birthday != null)
			{
				patient.setpAge(getAge(birthday));
				patient.setBirthday(DateUtils.formatDate(birthday, "yyyy-MM-dd"));
	
			}
			Date onlineDate = DateUtil.parse(curCase.getOnlineDate());
			curCase.setOnlineDate(DateUtils.formatDate(onlineDate, "yyyy-MM-dd"));	

			Date createDate = DateUtil.parse(curCase.getCreateDate(),"yyyy-MM-dd");
			curCase.setCreateDate(DateUtils.formatDate(createDate, "yyyy-MM-dd"));
			Date diagDate = DateUtil.parse(patient.getDiagDate());
			patient.setDiagDate(DateUtils.formatDate(diagDate, "yyyy-MM-dd"));
			patient.setpAge(getAge(birthday));
			newCasePatient.setCaseObject(curCase);
			newCasePatient.setPatientObject(patient);
			newCasePatient.setCaseDiseaseObject(disease);
			newCasePatient.setPatientDiagnosisObject(getPatientDiagnosis(patient.getId()));
			newCasePatient.setDiseaseDiagnosisObject(getDiseaseDiagnosis(disease.getId()));
			newCasePatient.setDiseaseDiagnosis(localCaseManage.getDiseaseDiagnosisById(disease.getId()));
			newCasePatient.setPatientDiagnosis(localCaseManage.getPatientDiagnosisById(patient.getId()));
			newCasePatient.setDiseaseDiscuss(localCaseManage.getDiseaseDiscussById(disease.getId()));
			List<CasePatientPropVal> patientProp = new ArrayList<CasePatientPropVal>();
			patientProp = localCaseManage.getCasePropByPatientId(patient.getId());
			for (int i = 0 ; i < patientProp.size() ; i ++)
			{
				CasePatientPropVal curProp = patientProp.get(i);
				if (curProp.getType() <= 5){
					courseString += curProp.getPropName() + ",";
					courseIds += curProp.getPropId() + ",";
				}
				else {
					subjectString += curProp.getPropName() + ",";
					subjectids += curProp.getPropId() + ",";
				}
				ExamPropQuery query  = new ExamPropQuery();
				
				try{
					query.setSysPropId(curProp.getPropId());
					ExamReturnProp rprop = localExamPropValFacade.getNextLevelProp(query);
					
					if (rprop != null)
						for (int k=0; k<rprop.getReturnList().size(); k++)
							for (int g=0; g<rprop.getReturnList().get(k).getIcdList().size(); g++)
								ICD10_names += " " + rprop.getReturnList().get(k).getIcdList().get(g).getName();
				} catch(Exception e){}
			}
		}
		if (courseString != "")
		{
			courseString = courseString.substring(0,courseString.length() - 1);	
		}
		if (courseIds != "")
		{
			courseIds = courseIds.substring(0,courseIds.length() - 1);	
		}
		if (subjectString != "")
		{
			subjectString = subjectString.substring(0,subjectString.length() - 1);	
		}
		if (subjectids != "")
		{
			subjectids = subjectids.substring(0,subjectids.length() - 1);	
		}
		if(newCasePatient.getPatientObject() != null){
			if(newCasePatient.getPatientObject().getNumber1Type() == null){
				newCasePatient.getPatientObject().setNumber1Type(-1);
			}
		}
		if(newCasePatient.getPatientObject() != null){
			if(newCasePatient.getPatientObject().getNumber2Type() == null){
				newCasePatient.getPatientObject().setNumber2Type(-1);
			}
		}
		if(newCasePatient.getCaseDiseaseObject() != null){
			newCasePatient.getCaseDiseaseObject().setDiseaseType(-1);
		}
//		研发中心
//		if(newCasePatient.getCaseDiseaseObject() == null){
//			newCasePatient.getCaseDiseaseObject().setDiseaseType(-1);
//		}
		//caseInfo End
		
		//send caseInfo to 'caseAnalysis.jsp'		
		request.setAttribute("caseData", newCasePatient);
		if(newCasePatient.getDiseaseDiscuss() != null){
			request.setAttribute("diseaseDiscuss", newCasePatient.getDiseaseDiscuss().size());		
		}
		request.setAttribute("courseStr", courseString);
		request.setAttribute("courseIds", courseIds);
		request.setAttribute("subjectStr", subjectString);
		request.setAttribute("subjectids", subjectids);
		request.setAttribute("ICD", ICD10_names);		
		
		return "caseAnalysis";
	}
	/**
	 * @author M
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return String Constant
	 */
	private String subjectDiscuss(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//保存查询结果
				List<CV> result = new ArrayList<CV>();
				//保存查询到的课程信息
				List<CV> cvList = new ArrayList<CV>();
				//保存项目信息
				List<CVSet> cvset = new ArrayList<CVSet>();
				CVSet cvs = new CVSet();
				//保存项目负责人信息
				List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
				//保存项目单位信息
				List<PeixunOrg> org = new ArrayList<PeixunOrg>();
				//
				CVSetUnitDiscuss discuss = new CVSetUnitDiscuss();	
				//项目Id
				Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);	
				//课程Id
				Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
				//单元id
				Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
				//查询课程信息
				cvList = localCVSetEntityDAO.getCvListByProId(cvsetId);
				//Get the current unit.
				CVUnit curUnit = localCVSetEntityDAO.getCVUnitByUnitId(unitId);
				//查询项目信息
				cvset = localCVSetEntityDAO.queryCVUnitById(cvsetId);
				if(cvset!=null && cvset.size()>0){
					cvs = cvset.get(0);
				}
				//查询项目负责人信息
				expert = localCVSetEntityDAO.queryProjectFZR(cvsetId);
				//查询项目单位信息
				org = localCVSetEntityDAO.queryProjectOrg(cvsetId);
				if(cvs!=null && !"".equals(cvs)){
					//封面图片
					request.setAttribute("fengmian", cvs.getFile_path());
				}
		        //合并查询课程与单元信息
				if(cvList!=null && cvList.size()>0){
					for(int i=0;i<cvList.size();i++){
						CV cv =  cvList.get(i);
						//Get current cv.
						if (cv.getId().equals(cvId)){
							request.setAttribute("curCV", cv);
						}
						//根据课程信息查询单元信息
						List<CVUnit> unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
						cv.setUnitList(unitList);
						result.add(cv);
					}
				}
				//将查询集合保存到request中
				request.setAttribute("list", result);
				request.setAttribute("project", cvs);
				//项目Id
				request.setAttribute("cvsetId", cvsetId);
				//课程Id
				request.setAttribute("cvId", cvId);
				//单元Id
				request.setAttribute("unitId", unitId);
				//current unit
				request.setAttribute("curUnit", curUnit);
				if(expert!=null && expert.size()>0){
					request.setAttribute("expert", expert.get(0));
				}
				if(org!=null && org.size()>0){
					request.setAttribute("org", org.get(0));
				}
		return "subjectDiscuss";
	}

	@SuppressWarnings("deprecation")
	public Integer getAge(Date Birthday){
		Date now = new Date();
		if (Birthday == null)
			return 0;
		int age = now.getYear() - Birthday.getYear();
		return age;
	}
	public String getPatientDiagnosis(Long patientId){
		String result = "";
		List<CasePatientDiagnosis> curPatientDiagnosis = localCaseManage.getPatientDiagnosisById(patientId);
		for(int i = 0 ; i < curPatientDiagnosis.size(); i++){
			result += curPatientDiagnosis.get(i).getDiagnosis();
			if(i != curPatientDiagnosis.size() - 1){
				result += ",";
			}
		}
		return result;
	}
	public String getDiseaseDiagnosis(Long diseaseId){
		String result = "";
		List<CaseDiseaseDiagnosis> curDiseaseDiagnosis = localCaseManage.getDiseaseDiagnosisById(diseaseId);
		for(int i = 0 ; i < curDiseaseDiagnosis.size(); i++){
			result += curDiseaseDiagnosis.get(i).getDiagnosis();
			if(i != curDiseaseDiagnosis.size() - 1){
				result += ",";
			}
		}
		return result;
	}

	public LogStudyCVUnitManage getLocalLogStudyCVUnitManage() {
		return localLogStudyCVUnitManage;
	}

	public void setLocalLogStudyCVUnitManage(LogStudyCVUnitManage localLogStudyCVUnitManage) {
		this.localLogStudyCVUnitManage = localLogStudyCVUnitManage;
	}

	
	// 重写play2方法
	
	public String play2s(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		
		
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//保存项目讨论信息
		//获取前台参数
		//课获取前台传递的id
		Long id = ParamUtils.getLongParameter(request, "id", 0L);	
		String paramType = request.getParameter("paramType");
		//判断是项目跳转还是课程跳转
		if(paramType!=null && paramType.equals("class")){
			//通过课程id查询项目信息
			CVSet cv = localCVSetEntityDAO.queryCVSetListByCvId(id);
			if(cv!=null){
				cvList = localCVSetEntityDAO.getCvListByProId(cv.getId());
				//查询项目信息
				cvset = localCVSetEntityDAO.queryCVUnitById(cv.getId());
				if(cvset!=null && cvset.size()>0){
					cvs = cvset.get(0);
				}
				//查询项目负责人信息
				expert = localCVSetEntityDAO.queryProjectFZR(cv.getId());
				//查询项目单位信息
				org = localCVSetEntityDAO.queryProjectOrg(cv.getId());
				if(cvs!=null && !"".equals(cvs)){
					//封面图片
					request.setAttribute("fengmian", cvs.getFile_path());
				}
			}
		}else if(paramType!=null && paramType.equals("project")){
			cvList = localCVSetEntityDAO.getCvListByProId(id);
			//查询项目信息
			cvset = localCVSetEntityDAO.queryCVUnitById(id);
			if(cvset!=null && cvset.size()>0){
				cvs = cvset.get(0);
			}
			//查询项目负责人信息
			expert = localCVSetEntityDAO.queryProjectFZR(id);
			//查询项目单位信息
			org = localCVSetEntityDAO.queryProjectOrg(id);
			if(cvs!=null && !"".equals(cvs)){
				//封面图片
				request.setAttribute("fengmian", cvs.getFile_path());
			}
		}
		/*else if(paramType!=null && paramType.equals("class")){
			cvList = localCVSetEntityDAO.getCvListByClassId(id);
			if(cvList!=null && cvList.size()>0){
				//封面图片
				request.setAttribute("fengmian", cvList.get(0).getFile_path());
			}
		}*/
        //合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//根据课程信息查询单元信息
				List<CVUnit> unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
				cv.setUnitList(unitList);
				result.add(cv);
			}
		}
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
		    play3(mapping,form,request,response);
		    
		    return null;
		
	}

	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   SCP
	 * @time     2017
	 * @方法说明	   课程预览
	 */
	protected String play2View(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){

		
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//保存项目讨论信息
		//获取前台参数
		//课获取前台传递的id
		Long id = ParamUtils.getLongParameter(request, "id", 0L);	
		String paramType = request.getParameter("paramType");
		String yulan = RequestUtil.getParameter(request, "yulan");//后台课程预览参数，前台不带次参数，如果为空就验证用户登录状态，不为空不验证
		
		//判断是项目跳转还是课程跳转
		if(paramType!=null && paramType.equals("class")){
			//通过课程id查询项目信息
			CVSet cv = localCVSetEntityDAO.queryCVSetListByCvId(id);
			if(cv!=null){
				cvList = localCVSetEntityDAO.getCvListByProId(cv.getId());
				//查询项目信息
				cvset = localCVSetEntityDAO.queryCVUnitById(cv.getId());
				if(cvset!=null && cvset.size()>0){
					cvs = cvset.get(0);
				}
				//查询项目负责人信息
				expert = localCVSetEntityDAO.queryProjectFZR(cv.getId());
				//查询项目单位信息
				org = localCVSetEntityDAO.queryProjectOrg(cv.getId());
				if(cvs!=null && !"".equals(cvs)){
					//封面图片
					request.setAttribute("fengmian", cvs.getFile_path());
				}
			}else{
				//后台“课程管理-查看”课程预览的时候,"yulan"为后台预览传递过来的标识参数
				if(yulan!=null && !yulan.equals("")){
					CV cvClass = new CV();
					cvClass.setId(id);
					cvClass.setName(yulan);
					cvList.add(cvClass);
				}
			}
		}else if(paramType!=null && paramType.equals("project")){
			cvList = localCVSetEntityDAO.getCvListByProId(id);
			//查询项目信息
			cvset = localCVSetEntityDAO.queryCVUnitById(id);
			if(cvset!=null && cvset.size()>0){
				cvs = cvset.get(0);
			}
			//查询项目负责人信息
			expert = localCVSetEntityDAO.queryProjectFZR(id);
			//查询项目单位信息
			org = localCVSetEntityDAO.queryProjectOrg(id);
			if(cvs!=null && !"".equals(cvs)){
				//封面图片
				request.setAttribute("fengmian", cvs.getFile_path());
			}
		}
		/*else if(paramType!=null && paramType.equals("class")){
			cvList = localCVSetEntityDAO.getCvListByClassId(id);
			if(cvList!=null && cvList.size()>0){
				//封面图片
				request.setAttribute("fengmian", cvList.get(0).getFile_path());
			}
		}*/
        //合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//根据课程信息查询单元信息
				List<CVUnit> unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
				//查询素材信息SCP
				List<GroupClassInfo> groupClassInfo = localCVSetEntityDAO.queryGroupClassInfo(cv.getId());
				//截取地址中的类型循环存入
				for(int j=0;j<groupClassInfo.size();j++){
					String html = groupClassInfo.get(j).getClassContent();
					Document doc = Jsoup.parse(html);
					Elements allImg = doc.getElementsByTag("img") ;
					for (Element imgElt : allImg) {
						Attributes allProp = imgElt.attributes() ;
						String altAttr = allProp.get("alt") ;
						unitList.get(j).setContent(altAttr);
					}
				}
				cv.setUnitList(unitList);
				result.add(cv);
			}
		}
		//获取当前登录用户信息
		SystemUser user = LogicUtil.getSystemUser(request);
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);
		if(user!=null){
			request.setAttribute("userId", user.getUserId());
		}else{
			request.setAttribute("userId", "");
		}
		
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
	    return "play2View";
	}
	
	/**
	 * @param    ActionMapping
	 * @return   String
	 * @author   SCP
	 * @time     2017
	 * @方法说明	   课程预览
	 */
	protected String play3View(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		//保存查询结果
		List<CV> result = new ArrayList<CV>();
		//保存查询到的课程信息
		List<CV> cvList = new ArrayList<CV>();
		//保存项目信息
		List<CVSet> cvset = new ArrayList<CVSet>();
		CVSet cvs = new CVSet();
		//保存项目负责人信息
		List<ExpertInfo> expert = new ArrayList<ExpertInfo>();
		//保存项目单位信息
		List<PeixunOrg> org = new ArrayList<PeixunOrg>();
		//获取前台参数
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);	
		//课程Id
		Long cvId = ParamUtils.getLongParameter(request, "cvId", 0L);
		//单元id
		Long unitId = ParamUtils.getLongParameter(request, "unitId", 0L);
		//content
		String content = request.getParameter("content");
		//quota
		Double  quota = 0.0;
		if(StringUtils.isNotEmpty(ParamUtils.getParameter(request, "quota"))){
			quota = Double.parseDouble(ParamUtils.getParameter(request, "quota"));
		}
		//查询课程信息
		cvList = localCVSetEntityDAO.getCvListByProId(cvsetId);
		//查询项目信息
		cvset = localCVSetEntityDAO.queryCVUnitById(cvsetId);
		if(cvset!=null && cvset.size()>0){
			cvs = cvset.get(0);
		}
		//Get the current unit.
		CVUnit curUnit = localCVSetEntityDAO.getCVUnitByUnitId(unitId);
		
		//查询项目负责人信息
		expert = localCVSetEntityDAO.queryProjectFZR(cvsetId);
		//查询项目单位信息
		org = localCVSetEntityDAO.queryProjectOrg(cvsetId);
		if(cvs!=null && !"".equals(cvs)){
			//封面图片
			request.setAttribute("fengmian", cvs.getFile_path());
		}
        //合并查询课程与单元信息
		if(cvList!=null && cvList.size()>0){
			for(int i=0;i<cvList.size();i++){
				CV cv =  cvList.get(i);
				//根据课程信息查询单元信息
				List<CVUnit> unitList = localCVSetEntityDAO.queryUnitListByClassId(cv.getId());
				//查询素材信息SCP
				List<GroupClassInfo> groupClassInfo = localCVSetEntityDAO.queryGroupClassInfo(cv.getId());
				//截取地址中的类型循环存入
				for(int j=0;j<groupClassInfo.size();j++){
					String html = groupClassInfo.get(j).getClassContent();
					Document doc = Jsoup.parse(html);
					Elements allImg = doc.getElementsByTag("img") ;
					for (Element imgElt : allImg) {
						Attributes allProp = imgElt.attributes() ;
						String altAttr = allProp.get("alt") ;
						unitList.get(j).setContent(altAttr);
					}
				}
				cv.setUnitList(unitList);
				result.add(cv);
			}
		}
		//获取当前登录用户信息
		SystemUser user = LogicUtil.getSystemUser(request);
		if(user!=null){
			request.setAttribute("userId", user.getUserId());
		}else{
			request.setAttribute("userId", "");
		}
		
		//将查询集合保存到request中
		request.setAttribute("list", result);
		request.setAttribute("project", cvs);
		//项目Id
		request.setAttribute("cvsetId", cvsetId);
		//课程Id
		request.setAttribute("cvId", cvId);
		//单元Id
		request.setAttribute("unitId", unitId);
		//content
		request.setAttribute("content", content);
		//quota
		
		request.setAttribute("quota", quota);
		if(expert!=null && expert.size()>0){
			request.setAttribute("expert", expert.get(0));
		}
		if(org!=null && org.size()>0){
			request.setAttribute("org", org.get(0));
		}
		
		//YHQ，查询学习记录id，2017-03-10
		request.setAttribute("videoLogStudyId", 0L);
		LogStudyCVUnit cvsObj = new LogStudyCVUnit();
		cvsObj.setLogStudyCvSetId(cvsetId);
		cvsObj.setCvId(cvId);
		cvsObj.setCvUnitId(unitId);
		if(user!=null){
			cvsObj.setSystemUserId(user.getUserId());
		}
		List<LogStudyCVUnit> list = localLogStudyCVUnitManage.queryLogStudyCVUnitByUnitId(cvsObj);
        if(list!=null && list.size()>0){           
            request.setAttribute("videoLogStudyId", list.get(0).getId());
        }
		
	    return "play3View";
	}
	
	public CvLiveManage getLocalCvLiveManage() {
		return localCvLiveManage;
	}
	public void setLocalCvLiveManage(CvLiveManage localCvLiveManage) {
		this.localCvLiveManage = localCvLiveManage;
	}
	public CVSetManage getLocalCVSetManage() {
		return localCVSetManage;
	}
	public void setLocalCVSetManage(CVSetManage localCVSetManage) {
		this.localCVSetManage = localCVSetManage;
	}	
	public CVSetEntityManage getLocalCVSetEntityDAO() {
		return localCVSetEntityDAO;
	}
	public void setLocalCVSetEntityDAO(CVSetEntityManage localCVSetEntityDAO) {
		this.localCVSetEntityDAO = localCVSetEntityDAO;
	}
	public SystemUserManage getLocalSystemUserManage() {
		return localSystemUserManage;
	}
	public void setLocalSystemUserManage(SystemUserManage localSystemUserManage) {
		this.localSystemUserManage = localSystemUserManage;
	}
	public CVSetUnitNoteManage getLocalCVSetUnitNoteManage() {
		return localCVSetUnitNoteManage;
	}
	public void setLocalCVSetUnitNoteManage(CVSetUnitNoteManage localCVSetUnitNoteManage) {
		this.localCVSetUnitNoteManage = localCVSetUnitNoteManage;
	}
	public CVSetUnitDiscussManage getLocalCVSetUnitDiscussManage() {
		return localCVSetUnitDiscussManage;
	}
	public void setLocalCVSetUnitDiscussManage(CVSetUnitDiscussManage localCVSetUnitDiscussManage) {
		this.localCVSetUnitDiscussManage = localCVSetUnitDiscussManage;
	}
	public ExamPaperFacade getExamPaperFacade() {
		return examPaperFacade;
	}
	public void setExamPaperFacade(ExamPaperFacade examPaperFacade) {
		this.examPaperFacade = examPaperFacade;
	}
	public ExamExaminationFacade getExamExaminationFacade() {
		return examExaminationFacade;
	}
	public void setExamExaminationFacade(ExamExaminationFacade examExaminationFacade) {
		this.examExaminationFacade = examExaminationFacade;
	}	
	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}
	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}
	public CaseManage getLocalCaseManage() {
		return localCaseManage;
	}
	public void setLocalCaseManage(CaseManage localCaseManage) {
		this.localCaseManage = localCaseManage;
	}
	public CVUnitManage getCvUnitManage() {
		return cvUnitManage;
	}
	public void setCvUnitManage(CVUnitManage cvUnitManage) {
		this.cvUnitManage = cvUnitManage;
	}
	public CVSetUnitDiscussManage getUnitDiscussManage() {
		return UnitDiscussManage;
	}
	public void setUnitDiscussManage(CVSetUnitDiscussManage unitDiscussManage) {
		UnitDiscussManage = unitDiscussManage;
	}

	public CVManage getLocalCVManage() {
		return localCVManage;
	}

	public void setLocalCVManage(CVManage localCVManage) {
		this.localCVManage = localCVManage;
	}
	
}
