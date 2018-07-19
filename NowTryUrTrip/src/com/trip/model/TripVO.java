package com.trip.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

public class TripVO implements Serializable {
	private int tripNo;
	private int memId;
	private String tripName;
	private Timestamp departTime;
	private String tripType;
	private Timestamp tripAddTime;
	private Timestamp tripRmvTime;
	private Double tripRate;
	private String tripIsPublic;
	private int tripPrice;
	private byte[] tripAdImg;
	private String tripDesc;
	private String tripContent;
	public int getTripNo() {
		return tripNo;
	}
	public void setTripNo(int tripNo) {
		this.tripNo = tripNo;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public Timestamp getDepartTime() {
		return departTime;
	}
	public void setDepartTime(Timestamp departTime) {
		this.departTime = departTime;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public Timestamp getTripAddTime() {
		return tripAddTime;
	}
	public void setTripAddTime(Timestamp tripAddTime) {
		this.tripAddTime = tripAddTime;
	}
	public Timestamp getTripRmvTime() {
		return tripRmvTime;
	}
	public void setTripRmvTime(Timestamp tripRmvTime) {
		this.tripRmvTime = tripRmvTime;
	}
	public Double getTripRate() {
		return tripRate;
	}
	public void setTripRate(Double tripRate) {
		this.tripRate = tripRate;
	}
	public String getTripIsPublic() {
		return tripIsPublic;
	}
	public void setTripIsPublic(String tripIsPublic) {
		this.tripIsPublic = tripIsPublic;
	}
	public int getTripPrice() {
		return tripPrice;
	}
	public void setTripPrice(int tripPrice) {
		this.tripPrice = tripPrice;
	}
	public byte[] getTripAdImg() {
		return tripAdImg;
	}
	public void setTripAdImg(byte[] tripAdImg) {
		this.tripAdImg = tripAdImg;
	}
	public String getTripDesc() {
		return tripDesc;
	}
	public void setTripDesc(String tripDesc) {
		this.tripDesc = tripDesc;
	}
	public String getTripContent() {
		return tripContent;
	}
	public void setTripContent(String tripContent) {
		this.tripContent = tripContent;
	}


}
