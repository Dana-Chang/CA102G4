package com.spotRpt.model;

import java.sql.Date;
import java.sql.Timestamp;

public class SpotRptVO implements java.io.Serializable{
	private Integer rptNo;
	private Integer memId;
	private Integer spotNo;	
	private String rptReason;
	private Timestamp rptTime;
	
	public Integer getRptNo() {
		return rptNo;
	}
	public void setRptNo(Integer rptNo) {
		this.rptNo = rptNo;
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
