package com.hys.exam.struts.action.expert;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.hys.auth.util.LongUtil;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.common.util.DateUtil;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.service.local.ExpertManageManage;
import com.hys.exam.struts.form.ExpertGroupForm;
import com.hys.exam.struts.form.ExpertInfoForm;
import com.hys.framework.web.action.BaseAction;

/**
 * 
 * @author Han
 * 专家委员会管理
 */
public class ExpertGroupAction extends BaseAction {

	private ExpertGroupManage localExpertgroupManage;
	private ExpertManageManage localExpertManageManage;
	
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
	protected String actionExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String method = RequestUtil.getParameter(request, "mode");
		if (method.equals("add")){
			return add(mapping, form, request, response);
		}
		else if (method.equals("edit")){
			return edit(mapping, form, request, response);
		}
		else if (method.equals("save")){
			return update(mapping, form, request, response);
		}
		else if (method.equals("del")){
			return del(mapping, form, request, response);
		}
		else if (method.equals("lock")){
			return lock(mapping, form, request, response);
		}
		else if (method.equals("detail")){
			return detail(mapping, form, request, response);
		}
		else
			return list(mapping, form, request, response);
	}
	/**
	 * 显示添加专委会页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroup group = new ExpertGroup();
		group.setState(1);
		request.setAttribute("info", group);
		return "edit";
	}
	
	/**
	 * 显示修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		group = localExpertgroupManage.getExpertGroup(eform.getId());
		request.setAttribute("info", group);
		return "edit";
	}
	/**
	 * 保存委员会信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		
		ExpertGroup oldGroup = new ExpertGroup();
		
		String name = request.getParameter("rsName");
		String propIds = request.getParameter("propIds");
		String summary = request.getParameter("summary");
		String email = request.getParameter("email");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");
		String note = request.getParameter("note");
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		String organizeDate = request.getParameter("organizeDate");
		//设置查询条件
		if(id != null && !id.equals(""))
		{
			oldGroup.setId(Long.valueOf(id));
		}
		oldGroup.setName(name);
		List<ExpertGroup> nameList = localExpertgroupManage.getExpertGroupfromName(oldGroup);
		if(nameList.size() != 0)
		{
			StrutsUtil.renderText(response, "repeatname");
			return null;
		}
		ExpertGroup group = new ExpertGroup();
		
		//如果是修改设置ID
		if (id != null && !id.equals(""))
			group.setId(LongUtil.parseLong(id));
		
		//设置信息
		group.setAddress(address);
//		group.setBreakDate(DateUtil.parse(eform.getBreakDate(), "yyyy-MM-dd"));
		group.setContact(contact);
		group.setEmail(email);
//		group.setMaster(eform.getMaster());
		group.setName(name);
		group.setNote(note);
		group.setOrganizeDate(DateUtil.parse(organizeDate, "yyyy-MM-dd"));
		group.setParent(0L);
		group.setPhone1(phone1);
		group.setPhone2(phone2);
		if(state != null && state !="")
			group.setState(Integer.valueOf(state));
		group.setSummary(summary);
		group.setPropIds(propIds);
		
		if (group.getId() == null)	//添加
			localExpertgroupManage.addExpertGroup(group);
		else	//修改
			localExpertgroupManage.updateExpertGroup(group);
		
		StrutsUtil.renderText(response, "success");
		return null;
	}
	
	/**
	 * 删除委员会
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		group.setId(eform.getId());
		
		boolean flag = localExpertgroupManage.deleteExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 禁用委员会
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String lock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置禁用状态为2
		group.setId(eform.getId());
		group = localExpertgroupManage.getExpertGroup(group);
		if(group.getLockState() != null && group.getLockState() == 1)
			group.setLockState(2);
		else
			group.setLockState(1);
		
		boolean flag = localExpertgroupManage.updateExpertGroup(group);
		if(flag){
			StrutsUtil.renderText(response, "success");
		}else {
			StrutsUtil.renderText(response, "error");
		}
			
		return null;
	}
	
	/**
	 * 取得符合查询条件的委员会数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ExpertGroupForm eform = (ExpertGroupForm)form;
		ExpertGroup group = new ExpertGroup();
		
		//设置查询条件
		group.setName(eform.getName());
		group.setLockState(eform.getState());
		group.setPropIds(eform.getPropIds());
		group.setPropNames(eform.getPropNames());
		group.setParent(0L);
		group.setStartDate(eform.getStartDate());
		group.setEndDate(eform.getEndDate());
		
		List<ExpertGroup> list =  localExpertgroupManage.getExpertGroupList(group);
		ExpertGroup temp = new ExpertGroup();
		for (ExpertGroup e: list){
			
			
			temp.setName(e.getName());
			temp.setId(e.getId());


			/*//取得关联学科数据
			ExpertGroup old = localExpertgroupManage.getExpertGroup(e.getId());
			e.setProp(old.getProp());
			
			ExpertGroup temp = new ExpertGroup();
			temp.setParent(e.getId());
			
			List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
			String master = "";
			for(ExpertGroup s : sublist){
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setGroupId(e.getId());
				expertInfo.setSubGroupId(s.getId());
				//取得专家目录
				List<ExpertInfo> expertlist =  localExpertManageManage.getExpertList(expertInfo);
				
				for (ExpertInfo ex: expertlist){
					if(ex.getOffice().equals(1)){
						master += ex.getName() + ",";
					}
				}
			}
			if(master != "")
			{
				master = master.substring(0,master.length() - 1);
			}
			e.setMaster(master);
			//取得学组数
			e.setNumSubGroup(sublist.size());
			
			//取得届期数据
			List<ExpertGroupTerm> termlist = localExpertgroupManage.getTermList(e);
			String termStr = "";
			
			for(ExpertGroupTerm t: termlist){
				termStr += t.getStartDateStr() + " ; " + t.getEndDateStr() + "<br>";
			}
			e.setTermStr(termStr);*/
		}
		request.setAttribute("groupList", list);
		request.setAttribute("info", group);
		
		return "list";
	}
	/**
	 * 显示添加专委会页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected String detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		if(id != null && !id.equals(""))
		{
			ExpertGroup group = new ExpertGroup();
			
			group.setParent(0L);
			group.setId(Long.valueOf(id));
			
			ExpertGroup curGroup =  localExpertgroupManage.getExpertGroup(Long.valueOf(id));	

			ExpertGroup temp = new ExpertGroup();
			temp.setParent(curGroup.getId());
			
			List<ExpertGroup> sublist = localExpertgroupManage.getExpertGroupList(temp);
			List<ExpertGroup> sublist1 = new ArrayList<ExpertGroup>();
			List<ExpertGroup> sublist2 = new ArrayList<ExpertGroup>();
			String master = "";
			String bumaster = "";
			String secretary = "";
			int subCount = 0;
			for(ExpertGroup s : sublist){
				ExpertInfo expertInfo = new ExpertInfo();
				expertInfo.setGroupIds(s.getId().toString());
				//取得专家目录
				List<ExpertInfo> expertlist =  localExpertManageManage.getExpertList(expertInfo);
				String teamManager = "";
				String teamMember = "";
				for (ExpertInfo ex: expertlist){
					if(ex.getOffice().equals(1)){
						master += ex.getName() + ",";
					}
					else if(ex.getOffice().equals(2))
					{
						bumaster += ex.getName() + ",";
					}
					else if(ex.getOffice().equals(3))
					{
						secretary += ex.getName() + " , ";
					}
					else if(ex.getOffice().equals(4))
					{
						teamManager += ex.getName() + ",";
					}
					else
					{
						teamMember += ex.getName() + ",";
					}
				}
				if(teamManager != "")
				{
					teamManager = teamManager.substring(0,teamManager.length() - 1);
				}
				if(teamMember != "")
				{
					teamMember = teamMember.substring(0,teamMember.length() - 1);
				}
				s.setMaster(teamManager);
				s.setTeamMember(teamMember);
				if(subCount % 2 ==0)
				{
					sublist1.add(s);
				}
				else
				{
					sublist2.add(s);
				}
				subCount ++;
			}
			if(master != "")
			{
				master = master.substring(0,master.length() - 1);
			}
			if(bumaster != "")
			{
				bumaster = bumaster.substring(0,bumaster.length() - 1);
			}
			if(secretary != "")
			{
				secretary = secretary.substring(0,secretary.length() - 3);
			}
			curGroup.setMaster(master);
			//取得学组数
			curGroup.setNumSubGroup(sublist.size());
			
			request.setAttribute("group", curGroup);
			request.setAttribute("bumaster", bumaster);
			request.setAttribute("secretary", secretary);
			request.setAttribute("subgroup1", sublist1);
			request.setAttribute("subgroup2", sublist2);
			if(sublist1.size() >= sublist2.size())
			{
				request.setAttribute("subcount", sublist1.size());
			}
			else
			{
				request.setAttribute("subcount", sublist2.size());
			}
		}
		return "detail";
	}
}
