package com.storeInf.model;

import java.sql.Connection;
import java.util.List;


public interface StoreInfDAO_interface {
	public void insert(StoreInfVO storeInfVO);
	public void update(StoreInfVO storeInfVO);
	public void delete(Integer memId);
	public StoreInfVO findByPrimaryKey(Integer memId);
    public List<StoreInfVO> getAll();

    
    public void insert2 (StoreInfVO storeInfVO , Connection con);
}
