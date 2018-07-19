package com.spot.model;

import java.io.Serializable;

public class SpotVO implements Serializable {

	private Integer spotNo;
	private String spotName;
	private String spotType;
	private String spotAddr;
	private String spotLng;
	private String spotLat;
	private double spotRate;
	private String spotOverview;
	private byte[] spotPhoto;
	private Integer spotOwner;
	private String spotChk;
	private String spotHdl;
	private String spotIsBlocked;
	private String spotBlockedReason;
	public Integer getSpotNo() {
		return spotNo;
	}
	public void setSpotNo(Integer spotNo) {
		this.spotNo = spotNo;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getSpotType() {
		return spotType;
	}
	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}
	public String getSpotAddr() {
		return spotAddr;
	}
	public void setSpotAddr(String spotAddr) {
		this.spotAddr = spotAddr;
	}
	public String getSpotLng() {
		return spotLng;
	}
	public void setSpotLng(String spotLng) {
		this.spotLng = spotLng;
	}
	public String getSpotLat() {
		return spotLat;
	}
	public void setSpotLat(String spotLat) {
		this.spotLat = spotLat;
	}
	public double getSpotRate() {
		return spotRate;
	}
	public void setSpotRate(double spotRate) {
		this.spotRate = spotRate;
	}
	public String getSpotOverview() {
		return spotOverview;
	}
	public void setSpotOverview(String spotOverview) {
		this.spotOverview = spotOverview;
	}
	public byte[] getSpotPhoto() {
		return spotPhoto;
	}
	public void setSpotPhoto(byte[] spotPhoto) {
		this.spotPhoto = spotPhoto;
	}
	public Integer getSpotOwner() {
		return spotOwner;
	}
	public void setSpotOwner(Integer spotOwner) {
		this.spotOwner = spotOwner;
	}
	public String getSpotChk() {
		return spotChk;
	}
	public void setSpotChk(String spotChk) {
		this.spotChk = spotChk;
	}
	public String getSpotHdl() {
		return spotHdl;
	}
	public void setSpotHdl(String spotHdl) {
		this.spotHdl = spotHdl;
	}
	public String getSpotIsBlocked() {
		return spotIsBlocked;
	}
	public void setSpotIsBlocked(String spotIsBlocked) {
		this.spotIsBlocked = spotIsBlocked;
	}
	public String getSpotBlockedReason() {
		return spotBlockedReason;
	}
	public void setSpotBlockedReason(String spotBlockedReason) {
		this.spotBlockedReason = spotBlockedReason;
	}
}
