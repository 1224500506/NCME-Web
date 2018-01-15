package com.hys.auth.util;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import com.hys.auth.dao.ResourceDAO;

import com.hys.auth.model.HysResources;
import com.hys.exam.dao.local.CvLiveDAO;
import com.hys.exam.model.CvLive;


public class TestUtil {


	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[] { "model/applicationContext_jndi.xml",
						"auth/applicationContext_dao_auth.xml" });
		CvLiveDAO dao = (CvLiveDAO) ctx.getBean("CvLiveDAO");
		
		CvLive cv = new CvLive();
		/*System.out.println(dao.queryCvAllLiveList());*/
	
		
		
//		List list = new ArrayList();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		System.out.println(org.apache.commons.lang.StringUtils.join(list.iterator(), ","));
		
		

	}

}
