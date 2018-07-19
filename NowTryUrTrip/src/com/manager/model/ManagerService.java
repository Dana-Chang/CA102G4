package com.manager.model;

import java.util.List;

public class ManagerService {

	private ManagerDAO_interface dao;

	public ManagerService() {
		dao = new ManagerDAO();
	}

	public ManagerVO addManager(String mgrName, String mgrAccount, String mgrPsw, String mgrIdentity,
			Integer mgrStatus) {

		ManagerVO managerVO = new ManagerVO();
		managerVO.setMgrName(mgrName);
		managerVO.setMgrAccount(mgrAccount);
		managerVO.setMgrPsw(mgrPsw);
		managerVO.setMgrIdentity(mgrIdentity);
		managerVO.setMgrStatus(1);
		dao.insert(managerVO);
		return managerVO;
	}

	public ManagerVO updateManager(String mgrName, String mgrAccount, String mgrPsw, String mgrIdentity,
			Integer mgrStatus, Integer mgrId) {

		ManagerVO managerVO = new ManagerVO();
		managerVO.setMgrName(mgrName);
		managerVO.setMgrAccount(mgrAccount);
		managerVO.setMgrPsw(mgrPsw);
		managerVO.setMgrIdentity(mgrIdentity);
		managerVO.setMgrStatus(mgrStatus);
		managerVO.setMgrId(mgrId);
		dao.update(managerVO);
		return managerVO;
	}

	public void deleteManager(Integer mgrId) {
		dao.delete(mgrId);
	}

	public ManagerVO getOneManager(Integer mgrId) {
		return dao.findByPrimaryKey(mgrId);
	}

	public List<ManagerVO> getAll() {
		return dao.getAll();
	}
	public ManagerVO getOneManagerAccount(String mgrAccount){
		return dao.findManagerAccount(mgrAccount);
		
	}
	
}