package com.blogMsgReport.model;
import java.sql.Date;
import java.sql.Timestamp;

public class BlogMsgReportVO implements java.io.Serializable{
	private Integer cmtReportNo;
	private Integer msgNo;
	private Integer memId;
	private String rpReason;
	private Timestamp rpTime;
	private String adminChecked;
	private String noRpReason;
	
	public Integer getCmtReportNo() {
		return cmtReportNo;
	}
	public void setCmtReportNo(Integer cmtReportNo) {
		this.cmtReportNo = cmtReportNo;
	}
	public Integer getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(Integer msgNo) {
		this.msgNo = msgNo;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getRpReason() {
		return rpReason;
	}
	public void setRpReason(String rpReason) {
		this.rpReason = rpReason;
	}
	public Timestamp getRpTime() {
		return rpTime;
	}
	public void setRpTime(Timestamp rpTime) {
		this.rpTime = rpTime;
	}
	public String getAdminChecked() {
		return adminChecked;
	}
	public void setAdminChecked(String adminChecked) {
		this.adminChecked = adminChecked;
	}
	public String getNoRpReason() {
		return noRpReason;
	}
	public void setNoRpReason(String noRpReason) {
		this.noRpReason = noRpReason;
	}
	
	

}
