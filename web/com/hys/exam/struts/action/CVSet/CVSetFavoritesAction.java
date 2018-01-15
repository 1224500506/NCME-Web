package com.hys.exam.struts.action.CVSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.hys.auth.util.ParamUtils;
import com.hys.auth.util.RequestUtil;
import com.hys.auth.util.StrutsUtil;
import com.hys.exam.constants.Constants;
import com.hys.exam.model.CVSetFavorutes;
import com.hys.exam.model.SystemUser;
import com.hys.exam.service.local.CVSetFavoritesManage;
import com.hys.exam.util.JsonUtil;
import com.hys.framework.web.action.BaseAction;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   CVSetFavoritesAction.java
 *     
 *     Description       :   项目收藏Action处理类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class CVSetFavoritesAction extends BaseAction {

	//项目收藏Service接口
	private CVSetFavoritesManage localCVSetFavoritesManage;

	public CVSetFavoritesManage getLocalCVSetFavoritesManage() {
		return localCVSetFavoritesManage;
	}

	public void setLocalCVSetFavoritesManage(CVSetFavoritesManage localCVSetFavoritesManage) {
		this.localCVSetFavoritesManage = localCVSetFavoritesManage;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 项目收藏主控制方法
	 */
	@Override
	protected String actionExecute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		//获取操作类别
		String mode = RequestUtil.getParameter(request, "mode");
		if(mode.equals("add")){
			return add(mapping, form, request, response);
		}else if(mode.equals("delete")){
			return delete(mapping, form, request, response);
		}else if(mode.equals("search")){
			return search(mapping, form, request, response);
		}
		return "list";
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 项目收藏方法
	 */
	private String add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化项目收藏对象
		CVSetFavorutes cvs = new CVSetFavorutes();
		cvs.setCvSetId(cvsetId);
		cvs.setSystemUserId(user.getUserId());
		//执行收藏
		localCVSetFavoritesManage.addCVSetFavorites(cvs);
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 项目取消收藏方法
	 */
	private String delete(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "noLogin");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化项目收藏对象
		CVSetFavorutes cvs = new CVSetFavorutes();
		cvs.setCvSetId(cvsetId);
		cvs.setSystemUserId(user.getUserId());
		//执行收藏
		localCVSetFavoritesManage.deleteCVSetFavorites(cvs);
		map.put("message", "success");
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
	
	/**
	 * @author   张建国
	 * @time     2017-01-10
	 * @param    ActionMapping
	 * @return   String
	 * 方法说明： 查询用户是否收藏过指定项目
	 */
	private String search(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//返回信息
		Map<String,String> map = new HashMap<String,String>();
		//项目Id
		Long cvsetId = ParamUtils.getLongParameter(request, "cvsetId", 0L);
		//获取Session中的用户
		SystemUser user = (SystemUser) request.getSession().getAttribute(Constants.SESSION_TRAIN_ADMIN_USER);
		if(user==null || user.getUserId()==0L){
			map.put("message", "no");
			StrutsUtil.renderText(response, JsonUtil.map2json(map));
			return null;
		}
		//实例化项目收藏对象
		CVSetFavorutes cvs = new CVSetFavorutes();
		cvs.setCvSetId(cvsetId);
		cvs.setSystemUserId(user.getUserId());
		//查询数据
		List<CVSetFavorutes> list = localCVSetFavoritesManage.searchCVSetFavorites(cvs);
		if(list!=null && list.size()>0){
			map.put("message", "yes");
		}else{
			map.put("message", "no");
		}
		StrutsUtil.renderText(response, JsonUtil.map2json(map));
		return null;
	}
		
}///*~
