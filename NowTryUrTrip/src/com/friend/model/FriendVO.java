package com.friend.model;

import java.io.Serializable;

public class FriendVO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private Integer memId;
	private Integer friendId;
	private Integer friendRp;
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	public Integer getFriendRp() {
		return friendRp;
	}
	public void setFriendRp(Integer friendRp) {
		this.friendRp = friendRp;
	}
	
}
