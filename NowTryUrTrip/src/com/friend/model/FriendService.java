package com.friend.model;

import java.util.List;

public class FriendService {

	private FriendDAO_interface dao;

	public FriendService() {
		dao = new FriendDAO();
	}

	public FriendVO addFriend(Integer memId, Integer friendId, Integer friendRp) {
		FriendVO friendVO = new FriendVO();

		friendVO.setMemId(memId);
		friendVO.setFriendId(friendId);
		friendVO.setFriendRp(0);

		dao.insert(friendVO);
		return friendVO;
	}

	public FriendVO deleteFriend(Integer memId, Integer friendId) {
		FriendVO friendVO = new FriendVO();

		friendVO.setMemId(memId);
		friendVO.setFriendId(friendId);
		dao.delete(memId, friendId);
		return friendVO;
	}

	public FriendVO befriend(Integer memId, Integer friendId,Integer friendRp) {
		FriendVO friendVO = new FriendVO();

		friendVO.setMemId(memId);
		friendVO.setFriendId(friendId);
		friendVO.setFriendRp(1);
		dao.update(friendVO);
		return friendVO;
	}
	public List<FriendVO> getFriend(Integer memId){		
		return dao.findByPrimaryKey(memId);		
	}	
}
