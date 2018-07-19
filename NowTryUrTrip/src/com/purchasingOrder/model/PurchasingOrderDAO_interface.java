package com.purchasingOrder.model;

import java.util.List;



public interface PurchasingOrderDAO_interface {
	
	 public void insert(PurchasingOrderVO purchasingOrderVO);
     public void update(PurchasingOrderVO purchasingOrderVO);
     public void delete(String orderNo);
     public PurchasingOrderVO findByPrimaryKey(String orderNo);
     public List<PurchasingOrderVO> getAll();
     public List<PurchasingOrderVO> getOneForAll(Integer memIdReq);
	 public List<PurchasingOrderVO> getOneForAllRes(Integer memIdReq);
	 public List<PurchasingOrderVO> getOneForAllReq(Integer memIdReq);
	 
//   public List<PointTransactionVO> getAll(Map<String, String[]> map); 

	
}
