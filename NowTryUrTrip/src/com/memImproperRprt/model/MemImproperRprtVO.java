package com.memImproperRprt.model;

import java.io.Serializable;

public class MemImproperRprtVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public Integer irsnumber;
	public Integer violationId;
	public Integer reportNumber;
	
	public Integer getIrsnumber() {
		return irsnumber;
	}
	public void setIrsnumber(Integer irsnumber) {
		this.irsnumber = irsnumber;
	}
	public Integer getViolationId() {
		return violationId;
	}
	public void setViolationId(Integer violationId) {
		this.violationId = violationId;
	}
	public Integer getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(Integer reportNumber) {
		this.reportNumber = reportNumber;
	}
	

}
