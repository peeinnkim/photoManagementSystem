package com.peeinn.domain;

import java.util.Date;

public class PhotoVO {
	private int pNo;
	private String pName;
	private String pOriginName;
	private String pWriter;
	private Date pRegdate;

	public PhotoVO() {
		// TODO Auto-generated constructor stub
	}

	public PhotoVO(int pNo, String pName, String pOriginName, String pWriter, Date pRegdate) {
		this.pNo = pNo;
		this.pName = pName;
		this.pOriginName = pOriginName;
		this.pWriter = pWriter;
		this.pRegdate = pRegdate;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpOriginName() {
		return pOriginName;
	}

	public void setpOriginName(String pOriginName) {
		this.pOriginName = pOriginName;
	}

	public String getpWriter() {
		return pWriter;
	}

	public void setpWriter(String pWriter) {
		this.pWriter = pWriter;
	}

	public Date getpRegdate() {
		return pRegdate;
	}

	public void setpRegdate(Date pRegdate) {
		this.pRegdate = pRegdate;
	}

	@Override
	public String toString() {
		return "PhotoVO [pNo=" + pNo + ", pName=" + pName + ", pOriginName=" + pOriginName + ", pWriter=" + pWriter
				+ ", pRegdate=" + pRegdate + "]";
	}

}// PhotoVO
