package com.spotCmnt.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SpotCmntVO implements Serializable {

	private Integer spotCmntNo;
	private Integer memId;
	private Integer spotNo;
	private String cmnt;
	private Timestamp cmntTime;
	private Integer rate;
	private String isChecked;
	private String isBlocked;
	private String blockedReason;
	public Integer getSpotCmntNo() {
		return spotCmntNo;
	}
	public void setSpotCmntNo(Integer spotCmntNo) {
		this.spotCmntNo = spotCmntNo;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getSpotNo() {
		return spotNo;
	}
	public void setSpotNo(Integer spotNo) {
		this.spotNo = spotNo;
	}
	public String getCmnt() {
		return cmnt;
	}
	public void setCmnt(String cmnt) {
		this.cmnt = cmnt;
	}
	public Timestamp getCmntTime() {
		return cmntTime;
	}
	public void setCmntTime(Timestamp cmntTime) {
		this.cmntTime = cmntTime;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	public String getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(String isBlocked) {
		this.isBlocked = isBlocked;
	}
	public String getBlockedReason() {
		return blockedReason;
	}
	public void setBlockedReason(String blockedReason) {
		this.blockedReason = blockedReason;
	}
}
