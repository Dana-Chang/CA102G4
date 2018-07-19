package com.chatRoomMember.model;
import java.sql.Date;

public class ChatRoomMemberVO implements java.io.Serializable{
	private Integer memId;
	private Integer chRoomNo;
	private String status;
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getChRoomNo() {
		return chRoomNo;
	}
	public void setChRoomNo(Integer chRoomNo) {
		this.chRoomNo = chRoomNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
