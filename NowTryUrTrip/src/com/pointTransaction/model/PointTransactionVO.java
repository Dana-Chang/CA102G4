package com.pointTransaction.model;
import java.sql.*;

public class PointTransactionVO implements java.io.Serializable{
	private Integer transactionNo;
	private Integer memId;
	private Integer startPoint;
	private Integer startCash;
	private String transactionDescription;
	private Integer changeCash;
	private Timestamp transactionTime;
	private Integer changePoint;
	
	
	
	public Integer getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(Integer transactionNo) {
		this.transactionNo = transactionNo;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	
	public Integer getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Integer startPoint) {
		this.startPoint = startPoint;
	}
	public Integer getStartCash() {
		return startCash;
	}
	public void setStartCash(Integer startCash) {
		this.startCash = startCash;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public Integer getChangeCash() {
		return changeCash;
	}
	public void setChangeCash(Integer changeCash) {
		this.changeCash = changeCash;
	}
	public Timestamp getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}
	public Integer getChangePoint() {
		return changePoint;
	}
	public void setChangePoint(Integer changePoint) {
		this.changePoint = changePoint;
	}
	
	
	
}
