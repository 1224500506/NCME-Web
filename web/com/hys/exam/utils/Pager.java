package com.hys.exam.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.springframework.web.util.WebUtils;

/**
 * 
 * 标题：
 * 
 * 作者：
 * 
 * 描述：
 * 
 * 说明:
 */
public class Pager<T> implements java.io.Serializable {

    /**
	 * 
	 */

    private static final long serialVersionUID = -8154405986796333298L;

    private static final String key[] = { "pageSize=", "pager.offset=",
            "pageOffset=" };

    private static final String key1[] = { "pageSize", "pager.offset",
            "pageOffset" };

    private int pageOffset = 1;

    private int count;

    private int pageSize = 20;

    private String queryString;

    private String url;

    private SortOrderEnum sortDirection;

    public SortOrderEnum getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private List<T> list;

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static String stringToArrayList(String str, String reg) {
        String m = "";
        if (str == null || str.equals(""))
            return m;

        String[] strArr = StringUtils.split(str, reg);

        for (int i = 0; i < strArr.length; ++i) {
            if (strArr[i].indexOf(key[0]) == -1
                    && strArr[i].indexOf(key[1]) == -1
                    && strArr[i].indexOf(key[2]) == -1
                    && strArr[i].indexOf(".") == -1
                    && strArr[i].indexOf("[") == -1
                    && strArr[i].indexOf("]") == -1
                    && strArr[i].indexOf(key1[0]) == -1
                    && strArr[i].indexOf(key1[1]) == -1
                    && strArr[i].indexOf(key1[2]) == -1) {
                m += "&" + strArr[i];
            }
        }

        if (!m.equals("")) {
            return m;
        } else {
            return "";
        }
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(HttpServletRequest req) {

        String queryString = req.getQueryString();

        List<String> list = new ArrayList<String>();
        stringToList(queryString, "&", list);
        queryString = "";
        if (list.size() > 0) {
            Iterator<String> it = list.iterator();
            String entry = null;
            while (it.hasNext()) {
                entry = (String) it.next();
                queryString += "&" + entry;
            }
        }
        this.queryString = getWebToList(req, queryString);
    }

    public void setQueryString(HttpServletRequest req, String searchWord) {

        String queryString = req.getQueryString();

        List<String> list = new ArrayList<String>();
        stringToList(queryString, "&", list);
        queryString = "";
        if (list.size() > 0) {
            Iterator<String> it = list.iterator();
            String entry = null;
            while (it.hasNext()) {
                entry = (String) it.next();
                if (entry.indexOf("searchWord") > -1) {
                    entry = "searchWord=" + searchWord;
                }
                queryString += "&" + entry;
            }
        }
        this.queryString = queryString;

    }

    // 一个带有List数组
    public static void stringToList(String str, String reg, List<String> list) {
        if (str != null && !str.equals("")) {
            String[] strArr = StringUtils.split(str, reg);
            for (int i = 0; i < strArr.length; ++i) {
                if (strArr[i].indexOf(key[0]) == -1
                        && strArr[i].indexOf(key[1]) == -1
                        && strArr[i].indexOf(key[2]) == -1
                        && strArr[i].indexOf(".") == -1
                        && strArr[i].indexOf("[") == -1
                        && strArr[i].indexOf("]") == -1
                        && strArr[i].indexOf(key1[0]) == -1
                        && strArr[i].indexOf(key1[1]) == -1
                        && strArr[i].indexOf(key1[2]) == -1) {
                    list.add(strArr[i]);
                }
            }
        }
    }

    // 另一种方式的取条件
    public static String getWebToList(HttpServletRequest req, String str) {
        Map params = WebUtils.getParametersStartingWith(req, "");
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            if (pairs.getValue() != null && !"".equals(pairs.getValue())
                    && ((String) pairs.getKey()).indexOf(".") == -1
                    && ((String) pairs.getKey()).indexOf("[") == -1
                    && ((String) pairs.getKey()).indexOf("]") == -1
                    && ((String) pairs.getKey()).indexOf(key1[0]) == -1
                    && ((String) pairs.getKey()).indexOf(key1[1]) == -1
                    && ((String) pairs.getKey()).indexOf(key1[2]) == -1) {
                if (str.indexOf(((String) pairs.getKey()).toString()) == -1) {
                    try {
                        if (pairs.getValue() instanceof Object[]) {
                            Object[] objArray = (Object[]) pairs.getValue();
                            for (Object o : objArray) {
                                str += "&"
                                        + pairs.getKey()
                                        + "="
                                        + URLEncoder.encode(o.toString(),
                                                "UTF-8");
                            }

                        } else {
                            str += "&"
                                    + pairs.getKey()
                                    + "="
                                    + URLEncoder.encode(pairs.getValue()
                                            .toString(), "UTF-8");
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return str;
    }

    // 赋值初始化参数
    public void setDefaultQueryString(Map<String, String> map) {
        Iterator it = map.entrySet().iterator();
        String str = "";
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            str += "&" + pairs.getKey() + "=" + pairs.getValue();
        }
        this.queryString = this.queryString + str;
    }
}
