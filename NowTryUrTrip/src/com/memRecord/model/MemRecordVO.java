package com.memRecord.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MemRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer violationId;
	private Integer memId;
	private Integer isSuspended;
	private String suspCat;
	private String suspReason;
	private Timestamp suspStart;
	private Integer suspDays;
	private Integer rprtSrc;
	private Integer mgrId;
	public Integer getViolationId() {
		return violationId;
	}
	public void setViolationId(Integer violationId) {
		this.violationId = violationId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getIsSuspended() {
		return isSuspended;
	}
	public void setIsSuspended(Integer isSuspended) {
		this.isSuspended = isSuspended;
	}
	public String getSuspCat() {
		return suspCat;
	}
	public void setSuspCat(String suspCat) {
		this.suspCat = suspCat;
	}
	public String getSuspReason() {
		return suspReason;
	}
	public void setSuspReason(String suspReason) {
		this.suspReason = suspReason;
	}
	public Timestamp getSuspStart() {
		return suspStart;
	}
	public void setSuspStart(Timestamp suspStart) {
		this.suspStart = suspStart;
	}
	public Integer getSuspDays() {
		return suspDays;
	}
	public void setSuspDays(Integer suspDays) {
		this.suspDays = suspDays;
	}
	public Integer getRprtSrc() {
		return rprtSrc;
	}
	public void setRprtSrc(Integer rprtSrc) {
		this.rprtSrc = rprtSrc;
	}
	public Integer getMgrId() {
		return mgrId;
	}
	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}

	
	
}
