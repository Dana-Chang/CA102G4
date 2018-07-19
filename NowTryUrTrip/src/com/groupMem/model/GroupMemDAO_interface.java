package com.groupMem.model;

import java.util.List;

public interface GroupMemDAO_interface {
	public void insert(GroupMemVO groupMemVO);

	public void update(GroupMemVO groupMemVO);

	public void delete(Integer tripNo, Integer memId);

	public GroupMemVO findByPrimaryKey(Integer tripNo, Integer memId);

	public List<GroupMemVO> getAll();
}
