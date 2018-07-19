package com.groupMem.model;

public class GroupMemService {
	private GroupMemDAO_interface dao;
	
	public GroupMemService(){
		dao = new GroupMemJDBCDAO();
	}
	
}
