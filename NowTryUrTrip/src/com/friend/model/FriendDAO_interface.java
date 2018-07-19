package com.friend.model;

import java.util.List;

public interface FriendDAO_interface {
	
	public void insert(FriendVO friendVO);
	public void update(FriendVO friendVO);
	public void delete(Integer memid,Integer friendId);
	public List<FriendVO> findByPrimaryKey(Integer memid);
    public List<FriendVO> getAll();
	

}
