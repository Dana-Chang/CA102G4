package com.tripCmnt.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TripCmntVO implements Serializable {

	private int tripCmntNo;
	private int memId;
	private int tripNo;
	private String tripCmntContent;
	private Timestamp tripCmntTime;
	private int tripRate;
	private String tripCmntIsHdl;
	private String tripCmntIsBlocked;
	private String tripCmntBlockedReason;
	public int getTripCmntNo() {
		return tripCmntNo;
	}
	public void setTripCmntNo(int tripCmntNo) {
		this.tripCmntNo = tripCmntNo;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public int getTripNo() {
		return tripNo;
	}
	public void setTripNo(int tripNo) {
		this.tripNo = tripNo;
	}
	public String getTripCmntContent() {
		return tripCmntContent;
	}
	public void setTripCmntContent(String tripCmntContent) {
		this.tripCmntContent = tripCmntContent;
	}
	public Timestamp getTripCmntTime() {
		return tripCmntTime;
	}
	public void setTripCmntTime(Timestamp tripCmntTime) {
		this.tripCmntTime = tripCmntTime;
	}
	public int getTripRate() {
		return tripRate;
	}
	public void setTripRate(int tripRate) {
		this.tripRate = tripRate;
	}
	public String getTripCmntIsHdl() {
		return tripCmntIsHdl;
	}
	public void setTripCmntIsHdl(String tripCmntIsHdl) {
		this.tripCmntIsHdl = tripCmntIsHdl;
	}
	public String getTripCmntIsBlocked() {
		return tripCmntIsBlocked;
	}
	public void setTripCmntIsBlocked(String tripCmntIsBlocked) {
		this.tripCmntIsBlocked = tripCmntIsBlocked;
	}
	public String getTripCmntBlockedReason() {
		return tripCmntBlockedReason;
	}
	public void setTripCmntBlockedReason(String tripCmntBlockedReason) {
		this.tripCmntBlockedReason = tripCmntBlockedReason;
	}

}
