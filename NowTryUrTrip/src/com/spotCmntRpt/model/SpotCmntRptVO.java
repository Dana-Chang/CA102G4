package com.spotCmntRpt.model;

import java.sql.Timestamp;

public class SpotCmntRptVO implements java.io.Serializable {
	private Integer rptNo;// (PK)1
	private Integer memId;// (FK)2
	private Integer spotCmntNo;// (FK)3
	private String rptReason;// 4
	private Timestamp rptTime;// 5

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

	public Integer getSpotCmntNo() {
		return spotCmntNo;
	}

	public void setSpotCmntNo(Integer spotCmntNo) {
		this.spotCmntNo = spotCmntNo;
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
