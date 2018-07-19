package com.storeInf.model;

import java.util.List;

public class StoreInfService {
	private StoreInfDAO_interface dao;

	public StoreInfService() {
		dao = new StoreInfDAO();
	}

	public StoreInfVO addStoreInf( Integer uniformNum, String principal, String storCall, String storAdd,
			Integer postalCode, Integer category) {
		StoreInfVO storeInfVO = new StoreInfVO();
		storeInfVO.setUniformNum(uniformNum);
		storeInfVO.setPrincipal(principal);
		storeInfVO.setStorCall(storCall);
		storeInfVO.setStorAdd(storAdd);
		storeInfVO.setPostalCode(postalCode);
		storeInfVO.setCategory(category);
		dao.insert(storeInfVO);
		return storeInfVO;

	}

	public StoreInfVO updateStoreInf(Integer memId, Integer uniformNum, String principal, String storCall,
			String storAdd, Integer postalCode, Integer category) {
		StoreInfVO storeInfVO = new StoreInfVO();
		storeInfVO.setMemId(memId);
		storeInfVO.setUniformNum(uniformNum);
		storeInfVO.setPrincipal(principal);
		storeInfVO.setStorCall(storCall);
		storeInfVO.setStorAdd(storAdd);
		storeInfVO.setPostalCode(postalCode);
		storeInfVO.setCategory(category);
		dao.update(storeInfVO);
		return storeInfVO;
	}

	public void deleteMember(Integer memId) {
		dao.delete(memId);
	}

	public StoreInfVO getOneMember(Integer memId) {
		return dao.findByPrimaryKey(memId);
	}

	public List<StoreInfVO> getAll() {
		return dao.getAll();

	}
	
}
