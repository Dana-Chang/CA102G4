package com.purchasingOrder.model;

import java.sql.Timestamp;
import java.util.List;

public class PurchasingOrderService {

	private PurchasingOrderDAO_interface dao;

	public PurchasingOrderService() {
		dao = new PurchasingOrderDAO();
	}

	public List<PurchasingOrderVO> getAll() {
		return dao.getAll();
	}
	public List<PurchasingOrderVO> getOneForAll(Integer memIdReq) {		//取得會員的代買請求列表
		return dao.getOneForAll(memIdReq);
		
	}
	public List<PurchasingOrderVO> getOneForAllRes(Integer memIdReq) { //取得回覆會員的代買列表
		return dao.getOneForAllRes(memIdReq);
	}
	public List<PurchasingOrderVO> getOneForAllReq(Integer memIdReq) { //取得回覆會員的代買列表
		return dao.getOneForAllReq(memIdReq);
	}
	
	
}
