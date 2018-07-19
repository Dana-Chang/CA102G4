package com.proprietorArea.model;
import java.sql.Date;
import java.sql.Timestamp;

public class PprVO implements java.io.Serializable{
	
	private Integer pprMsgeNo;//Not Null(PK)
	private Integer pprSpotNo;//Not Null(PK)(FK)
	private Timestamp pprCheckIn;
	private Timestamp pprCheckOut;
	private String pprMsgeCtx;
	private byte[] pprMsgeImg;
	
	public Integer getPprMsgeNo() {
		return pprMsgeNo;
	}
	public void setPprMsgeNo(Integer pprMsgeNo) {
		this.pprMsgeNo = pprMsgeNo;
	}
	public Integer getPprSpotNo() {
		return pprSpotNo;
	}
	public void setPprSpotNo(Integer pprSpotNo) {
		this.pprSpotNo = pprSpotNo;
	}
	public Timestamp getPprCheckIn() {
		return pprCheckIn;
	}
	public void setPprCheckIn(Timestamp pprCheckIn) {
		this.pprCheckIn = pprCheckIn;
	}
	public Timestamp getPprCheckOut() {
		return pprCheckOut;
	}
	public void setPprCheckOut(Timestamp pprCheckOut) {
		this.pprCheckOut = pprCheckOut;
	}
	public String getPprMsgeCtx() {
		return pprMsgeCtx;
	}
	public void setPprMsgeCtx(String pprMsgeCtx) {
		this.pprMsgeCtx = pprMsgeCtx;
	}
	public byte[] getPprMsgeImg() {
		return pprMsgeImg;
	}
	public void setPprMsgeImg(byte[] pprMsgeImg) {
		this.pprMsgeImg = pprMsgeImg;
	}
}
