package com.storeInf.model;

import java.io.Serializable;

public class StoreInfVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer memId;
	private Integer uniformNum;
	private String principal;
	private String storCall;
	private String storAdd;
	private Integer postalCode;
	private Integer category;

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public Integer getUniformNum() {
		return uniformNum;
	}

	public void setUniformNum(Integer uniformNum) {
		this.uniformNum = uniformNum;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getStorCall() {
		return storCall;
	}

	public void setStorCall(String storCall) {
		this.storCall = storCall;
	}

	public String getStorAdd() {
		return storAdd;
	}

	public void setStorAdd(String storAdd) {
		this.storAdd = storAdd;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
