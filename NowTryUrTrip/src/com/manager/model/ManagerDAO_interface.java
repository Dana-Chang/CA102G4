package com.manager.model;

import java.util.List;

public interface ManagerDAO_interface {
	public void insert(ManagerVO managerVO);
	public void update(ManagerVO managerVO);
	public void delete(Integer menId);
	public ManagerVO findByPrimaryKey(Integer menId);
    public List<ManagerVO> getAll();
    public ManagerVO findManagerAccount(String mgrAccount);
}
