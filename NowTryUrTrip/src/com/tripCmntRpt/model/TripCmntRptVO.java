package com.tripCmntRpt.model;

import java.sql.Timestamp;

public class TripCmntRptVO {
	private int tripCmntRptNo;
	private int tripCmntNo;
	private int memId;
	private String rptReason;
	private Timestamp rptTime;
	
	public int getTripCmntRptNo() {
		return tripCmntRptNo;
	}
	public void setTripCmntRptNo(int tripCmntRptNo) {
		this.tripCmntRptNo = tripCmntRptNo;
	}
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
	public String getRptReason() {
		return rptReason;
	}
	public void setRptReason(String rptReason) {
		this.rptReason = rptReason;
	}
	public Timestamp getRptTime() {
		return rptTime;
	}
	public void setRptTime(Timestamp rptTime) {
		this.rptTime = rptTime;
	}

}
