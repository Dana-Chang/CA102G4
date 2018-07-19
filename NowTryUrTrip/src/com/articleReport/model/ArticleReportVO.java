package com.articleReport.model;
import java.sql.Date;
import java.sql.Timestamp;



public class ArticleReportVO implements java.io.Serializable{
	private Integer reportNo;
	private Integer articleNo;
	private Integer memId;
	private String rpReason;
	private Timestamp rpTime;
	private String adminChecked;
	private String noRpReason;
	public Integer getReportNo() {
		return reportNo;
	}
	public void setReportNo(Integer reportNo) {
		this.reportNo = reportNo;
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
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
