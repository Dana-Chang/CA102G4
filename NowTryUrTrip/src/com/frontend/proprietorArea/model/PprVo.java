package com.frontend.proprietorArea.model;
import java.sql.Date;

public class PprVo implements java.io.Serializable{
	
	private Integer pprMsgeNo;//Not Null(PK)
	private Integer pprSpotNo;//Not Null(PK)(FK)
	private Date pprCheckIn;
	private Date pprCheckOut;
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
	public Date getPprCheckIn() {
		return pprCheckIn;
	}
	public void setPprCheckIn(Date pprCheckIn) {
		this.pprCheckIn = pprCheckIn;
	}
	public Date getPprCheckOut() {
		return pprCheckOut;
	}
	public void setPprCheckOut(Date pprCheckOut) {
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
