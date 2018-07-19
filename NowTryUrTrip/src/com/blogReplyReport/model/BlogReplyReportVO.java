package com.blogReplyReport.model;
import java.sql.Date;
import java.sql.Timestamp;

public class BlogReplyReportVO implements java.io.Serializable{
	private Integer replyReportNo;
	private Integer replyNo;
	private Integer memId;
	private String rpReason;
	private Timestamp rpTime;
	private String adminChecked;
	private String noRpReason;

	
	public Integer getReplyReportNo() {
		return replyReportNo;
	}
	public void setReplyReportNo(Integer replyReportNo) {
		this.replyReportNo = replyReportNo;
	}
	public Integer getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(Integer replyNo) {
		this.replyNo = replyNo;
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
