package com.peeinn.domain;

import java.util.Date;

public class MemberVO {
	private String mId;
	private String mPw;
	private String mName;
	private String mMail;
	private String mTel;
	private Date mRegdate;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String mId, String mPw, String mName, String mMail, String mTel, Date mRegdate) {
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mMail = mMail;
		this.mTel = mTel;
		this.mRegdate = mRegdate;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmMail() {
		return mMail;
	}

	public void setmMail(String mMail) {
		this.mMail = mMail;
	}

	public String getmTel() {
		return mTel;
	}

	public void setmTel(String mTel) {
		this.mTel = mTel;
	}

	public Date getmRegdate() {
		return mRegdate;
	}

	public void setmRegdate(Date mRegdate) {
		this.mRegdate = mRegdate;
	}

	@Override
	public String toString() {
		return "MemberVO [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mMail=" + mMail + ", mTel=" + mTel
				+ ", mRegdate=" + mRegdate + "]";
	}

}// MemberVO
