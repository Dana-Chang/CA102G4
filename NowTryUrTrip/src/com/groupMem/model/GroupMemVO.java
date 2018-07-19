package com.groupMem.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

public class GroupMemVO implements Serializable {
	private Integer tripNo;
	private Integer memId;
	private String isLeader;

	public Integer getTripNo() {
		return tripNo;
	}

	public void setTripNo(Integer tripNo) {
		this.tripNo = tripNo;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

}
