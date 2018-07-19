package com.blogMsg.model;

import java.sql.Timestamp;

public class BlogMsgVO implements java.io.Serializable {
	private Integer msgNo;
	private Integer articleNo;
	private Integer msgMemId;
	private String msgContent;
	private Timestamp msgTime;
	private String reported;
	private String reviewed;
	private String isBlocked;
	private String blockedReason;
	
	public Integer getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(Integer msgNo) {
		this.msgNo = msgNo;
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public Integer getMsgMemId() {
		return msgMemId;
	}
	public void setMsgMemId(Integer msgMemId) {
		this.msgMemId = msgMemId;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Timestamp getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
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



}
