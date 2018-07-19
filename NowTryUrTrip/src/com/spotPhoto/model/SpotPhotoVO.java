package com.spotPhoto.model;

public class SpotPhotoVO {
	private int spotPhotoNo;
	private int spotNo;
	private int memId;
	private byte[] spotPhoto;
	private String photoTitle;
	public int getSpotPhotoNo() {
		return spotPhotoNo;
	}
	public void setSpotPhotoNo(int spotPhotoNo) {
		this.spotPhotoNo = spotPhotoNo;
	}
	public int getSpotNo() {
		return spotNo;
	}
	public void setSpotNo(int spotNo) {
		this.spotNo = spotNo;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public byte[] getSpotPhoto() {
		return spotPhoto;
	}
	public void setSpotPhoto(byte[] spotPhoto) {
		this.spotPhoto = spotPhoto;
	}
	public String getPhotoTitle() {
		return photoTitle;
	}
	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
	}
	
}
