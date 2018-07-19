package com.blogArticle.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;

public class BlogArticleVO implements Serializable {
	private Integer articleNo;
	private Integer authorNo;
	private Timestamp articleTime;
	private String viewable;
	private String reported;
	private String reviewed;
	private String isBlocked;
	private String blockedReason;
	private String articleContent;
	private String articleTitle;
	
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public Integer getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Integer authorNo) {
		this.authorNo = authorNo;
	}
	public Timestamp getArticleTime() {
		return articleTime;
	}
	public void setArticleTime(Timestamp articleTime) {
		this.articleTime = articleTime;
	}
	public String getViewable() {
		return viewable;
	}
	public void setViewable(String viewable) {
		this.viewable = viewable;
	}
	public String getReported() {
		return reported;
	}
	public void setReported(String reported) {
		this.reported = reported;
	}
	public String getReviewed() {
		return reviewed;
	}
	public void setReviewed(String reviewed) {
		this.reviewed = reviewed;
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
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}


}
