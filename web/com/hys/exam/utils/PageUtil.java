package com.hys.exam.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.GenericValidator;

import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkException;

/**
 * 
 * 标题：
 * 
 * 作者：zdz，2009 7 13
 * 
 * 描述：
 * 
 * 说明:
 */
public class PageUtil {
	/**
	 * 根据每页大小 当前页 生成sql(mysql数据库)
	 * 
	 * @param sql
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */

	public static String getPageSql(String sql, int currentPage,int pageSize) {
/*		StringBuffer sb = new StringBuffer();
		sb.append("select * from(select row_.*, rownum rownum_ from(");
		sb.append(sql);
		sb.append(") row_ where rownum<=");
		sb.append(pageSize * currentPage);
		sb.append(") where rownum_>");
		sb.append(pageSize * (currentPage - 1));
		return sb.toString();
*/
		StringBuilder sb = new StringBuilder();
		sb.append(sql);
		sb.append(" limit ");
		sb.append(pageSize * (currentPage-1));
		sb.append(", ");
		sb.append(pageSize);
		return sb.toString();
	}

	/**
	 * 根据id取得当前页
	 * 
	 * @param id
	 * @return
	 */
	public static Integer getPageIndex(String id, HttpServletRequest request)
			throws FrameworkException {
		String pageIndexName = new org.displaytag.util.ParamEncoder(id)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		try {
			//return GenericValidator.isBlankOrNull(request
			//		.getParameter(pageIndexName)) ? 0 : (Integer
			//		.parseInt(request.getParameter(pageIndexName)) - 1);
			return GenericValidator.isBlankOrNull(request
					.getParameter(pageIndexName)) ? 1 : (Integer
					.parseInt(request.getParameter(pageIndexName)));
		} catch (NumberFormatException ne) {
			throw new FrameworkException(ErrorCode.ec01528, ne);
		}
	}

	/**
	 * 取page index
	 * 
	 * @param request
	 * @return
	 * @throws FrameworkException
	 */
	public static Integer getPageIndex(HttpServletRequest request)
			throws FrameworkException {
		try {
			return GenericValidator.isBlankOrNull(request.getParameter("page")) ? 1
					: (Integer.parseInt(request.getParameter("page")));
		} catch (NumberFormatException ne) {
			throw new FrameworkException(ErrorCode.ec01528, ne);
		}
	}

    /**
     * 取page index
     * 
     * @param request
     * @return
     * @throws FrameworkException
     */
    public static Integer getPageIndex2(HttpServletRequest request)
            throws FrameworkException {
        try {
            return GenericValidator.isBlankOrNull(request
                    .getParameter("pageOffset")) ? 1 : (Integer
                    .parseInt(request.getParameter("pageOffset")));
        } catch (NumberFormatException ne) {
            throw new FrameworkException(ErrorCode.ec01528, ne);
        }
    }
	
	
	/**
	 * 取页面显示数据条数
	 * 
	 * @param request
	 * @return
	 * @throws FrameworkException
	 */
	public static Integer getPageSize(HttpServletRequest request)
			throws FrameworkException {
		try {
			return GenericValidator.isBlankOrNull(request
					.getParameter("pagesize")) ? 10 : (Integer.parseInt(request
					.getParameter("pagesize")));

		} catch (NumberFormatException ne) {
			throw new FrameworkException(ErrorCode.ec01528, ne);
		}
	}
	
	/**
	 * 
	 * @param sql
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */

	public static String getCountSql(String sql) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from (");
		sb.append(sql);
		sb.append(") tempcount");
		return sb.toString();
	}
}
