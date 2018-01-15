package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.SystemCardOrderDao;
import com.hys.exam.model.SystemCardOrder;
import com.hys.exam.service.local.SystemCardOrderManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class SystemCardOrderManageImpl extends BaseMangerImpl implements SystemCardOrderManage{

	private SystemCardOrderDao systemCardOrderDao;
	
	
	// Getting  and Setting 
	
			public SystemCardOrderDao getSystemCardOrderDao() {
				return systemCardOrderDao;
			}

			public void setSystemCardOrderDao(SystemCardOrderDao systemCardOrderDao) {
				this.systemCardOrderDao = systemCardOrderDao;
			}

	
	@Override
	public List<SystemCardOrder> findByUidProid(Long uid, Long proid,String cardNumber) {
		// TODO Auto-generated method stub
		return systemCardOrderDao.find(uid, proid,cardNumber);
		
	}
	
	@Override
	public Boolean findByUidProid2(Long uid, Long proid) {
		return systemCardOrderDao.find2(uid, proid);
		
	}


	@Override
	public void AddCardOrder(SystemCardOrder systemCardOrder) {
		// TODO Auto-generated method stub
		systemCardOrderDao.AddCardOrder(systemCardOrder);
	}
	
	
	

		@Override
		public List<SystemCardOrder> findListByUid(Long uid) {
			// TODO Auto-generated method stub
			return systemCardOrderDao.findListByUid(uid);
		}


		@Override
		public List<SystemCardOrder> findAllList(SystemCardOrder T) {
			// TODO Auto-generated method stub
			return systemCardOrderDao.findAllList(T);
		}

		@Override
		public List<SystemCardOrder> findListByCardNumber(SystemCardOrder T) {
			// TODO Auto-generated method stub
			return systemCardOrderDao.findListByCardNumber(T);
		}

		@Override
		public List<SystemCardOrder> getListForUseCvSet(
				SystemCardOrder queryParam) {
			return systemCardOrderDao.getListForUseCvSet(queryParam);
		}
	
}
