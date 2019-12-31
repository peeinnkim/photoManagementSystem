package com.peeinn.domain;

public class Criteria {
	private int page; // 선택한 페이지 번호
	private int perPageNum; // 한 페이지당 보일 게시물 번호

	/*
	  	(page-1) * perPageNum -> DB row index
	 
	 */
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}

	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public int getPageStart() {
		return (this.page - 1) * this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}

}// Criteria
