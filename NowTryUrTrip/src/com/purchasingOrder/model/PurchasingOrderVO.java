package com.purchasingOrder.model;

import java.sql.*;

public class PurchasingOrderVO {
	private String orderNo;
	private String orderReq;
	private String orderStatus;
	private String orderDescription;
	private byte[] orderGoods;
	private Integer tripNo;
	private Integer memIdReq;
	private String deliveryPlaces;
	private Boolean orderSetUp;
	private Timestamp orderSetUpTime = null;
	private Integer quotedPrice;
	private Integer memIdRes;
	private Timestamp deliveryTime = null;
	private byte[] transactionCode;
	private Boolean transactionSuccess;
	private String transFailReqReason;
	private Boolean transFailComplaintReq;
	private String transFailComplaintReqReason;
	private Timestamp transFailComplaintReqTime = null;
	private String transFailResReason;
	private Boolean transFailComplaintRes;
	private String transFailComplaintResReason;
	private Timestamp transFailComplaintResTime = null;
	private Boolean reviewed;
	private Boolean process;
	private Integer wrong;
	private Boolean vlationReq;
	private Boolean vlationRes;
	private String processMethod;
	private Integer evaluateReq;
	private Integer evaluateRes;
	private String commentReq;
	private String commentRes;
	
	
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderReq() {
		return orderReq;
	}
	public void setOrderReq(String orderReq) {
		this.orderReq = orderReq;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public byte[] getOrderGoods() {
		return orderGoods;
	}
	public void setOrderGoods(byte[] orderGoods) {
		this.orderGoods = orderGoods;
	}
	public Integer getTripNo() {
		return tripNo;
	}
	public void setTripNo(Integer tripNo) {
		this.tripNo = tripNo;
	}
	public Integer getMemIdReq() {
		return memIdReq;
	}
	public void setMemIdReq(Integer memIdReq) {
		this.memIdReq = memIdReq;
	}
	public String getDeliveryPlaces() {
		return deliveryPlaces;
	}
	public void setDeliveryPlaces(String deliveryPlaces) {
		this.deliveryPlaces = deliveryPlaces;
	}
	public Boolean getOrderSetUp() {
		return orderSetUp;
	}
	public void setOrderSetUp(Boolean orderSetUp) {
		this.orderSetUp = orderSetUp;
	}
	public Timestamp getOrderSetUpTime() {
		return orderSetUpTime;
	}
	public void setOrderSetUpTime(Timestamp orderSetUpTime) {
		this.orderSetUpTime = orderSetUpTime;
	}
	public Integer getQuotedPrice() {
		return quotedPrice;
	}
	public void setQuotedPrice(Integer quotedPrice) {
		this.quotedPrice = quotedPrice;
	}
	public Integer getMemIdRes() {
		return memIdRes;
	}
	public void setMemIdRes(Integer memIdRes) {
		this.memIdRes = memIdRes;
	}
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public byte[] getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(byte[] transactionCode) {
		this.transactionCode = transactionCode;
	}
	public Boolean getTransactionSuccess() {
		return transactionSuccess;
	}
	public void setTransactionSuccess(Boolean transactionSuccess) {
		this.transactionSuccess = transactionSuccess;
	}
	public String getTransFailReqReason() {
		return transFailReqReason;
	}
	public void setTransFailReqReason(String transFailReqReason) {
		this.transFailReqReason = transFailReqReason;
	}
	public Boolean getTransFailComplaintReq() {
		return transFailComplaintReq;
	}
	public void setTransFailComplaintReq(Boolean transFailComplaintReq) {
		this.transFailComplaintReq = transFailComplaintReq;
	}
	public String getTransFailComplaintReqReason() {
		return transFailComplaintReqReason;
	}
	public void setTransFailComplaintReqReason(String transFailComplaintReqReason) {
		this.transFailComplaintReqReason = transFailComplaintReqReason;
	}
	public Timestamp getTransFailComplaintReqTime() {
		return transFailComplaintReqTime;
	}
	public void setTransFailComplaintReqTime(Timestamp transFailComplaintReqTime) {
		this.transFailComplaintReqTime = transFailComplaintReqTime;
	}
	public String getTransFailResReason() {
		return transFailResReason;
	}
	public void setTransFailResReason(String transFailResReason) {
		this.transFailResReason = transFailResReason;
	}
	public Boolean getTransFailComplaintRes() {
		return transFailComplaintRes;
	}
	public void setTransFailComplaintRes(Boolean transFailComplaintRes) {
		this.transFailComplaintRes = transFailComplaintRes;
	}
	public String getTransFailComplaintResReason() {
		return transFailComplaintResReason;
	}
	public void setTransFailComplaintResReason(String transFailComplaintResReason) {
		this.transFailComplaintResReason = transFailComplaintResReason;
	}
	public Timestamp getTransFailComplaintResTime() {
		return transFailComplaintResTime;
	}
	public void setTransFailComplaintResTime(Timestamp transFailComplaintResTime) {
		this.transFailComplaintResTime = transFailComplaintResTime;
	}
	public Boolean getReviewed() {
		return reviewed;
	}
	public void setReviewed(Boolean reviewed) {
		this.reviewed = reviewed;
	}
	public Boolean getProcess() {
		return process;
	}
	public void setProcess(Boolean process) {
		this.process = process;
	}
	public Integer getWrong() {
		return wrong;
	}
	public void setWrong(Integer wrong) {
		this.wrong = wrong;
	}
	public Boolean getVlationReq() {
		return vlationReq;
	}
	public void setVlationReq(Boolean vlationReq) {
		this.vlationReq = vlationReq;
	}
	public Boolean getVlationRes() {
		return vlationRes;
	}
	public void setVlationRes(Boolean vlationRes) {
		this.vlationRes = vlationRes;
	}
	public String getProcessMethod() {
		return processMethod;
	}
	public void setProcessMethod(String processMethod) {
		this.processMethod = processMethod;
	}
	public Integer getEvaluateReq() {
		return evaluateReq;
	}
	public void setEvaluateReq(Integer evaluateReq) {
		this.evaluateReq = evaluateReq;
	}
	public Integer getEvaluateRes() {
		return evaluateRes;
	}
	public void setEvaluateRes(Integer evaluateRes) {
		this.evaluateRes = evaluateRes;
	}
	public String getCommentReq() {
		return commentReq;
	}
	public void setCommentReq(String commentReq) {
		this.commentReq = commentReq;
	}
	public String getCommentRes() {
		return commentRes;
	}
	public void setCommentRes(String commentRes) {
		this.commentRes = commentRes;
	}
	
	
	
	
	
	
	
		
}
