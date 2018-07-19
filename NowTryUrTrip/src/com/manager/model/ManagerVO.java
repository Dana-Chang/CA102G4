package com.manager.model;

import java.io.Serializable;

public class ManagerVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer mgrId;
	private String mgrName;
	private String mgrAccount;
	private String mgrPsw;
	private String mgrIdentity;
	private Integer mgrStatus;

	public Integer getMgrId() {
		return mgrId;
	}

	public void setMgrId(Integer mgrId) {
		this.mgrId = mgrId;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	public String getMgrAccount() {
		return mgrAccount;
	}

	public void setMgrAccount(String mgrAccount) {
		this.mgrAccount = mgrAccount;
	}

	public String getMgrPsw() {
		return mgrPsw;
	}

	public void setMgrPsw(String mgrPsw) {
		this.mgrPsw = mgrPsw;
	}

	public String getMgrIdentity() {
		return mgrIdentity;
	}

	public void setMgrIdentity(String mgrIdentity) {
		this.mgrIdentity = mgrIdentity;
	}

	public Integer getMgrStatus() {
		return mgrStatus;
	}

	public void setMgrStatus(Integer mgrStatus) {
		this.mgrStatus = mgrStatus;
	}
}
