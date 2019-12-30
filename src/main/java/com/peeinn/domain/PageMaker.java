package com.peeinn.domain;

public class PageMaker {

	private int totalCount; // 게시물 전체 갯수
	private int startPage; // 현재 보이는 페이지 시작 번호
	private int endPage; // 현재 보이는 페이지 끝 번호
	private boolean prev; // 이전 10개 존재여부
	private boolean next; // 이후 10개 존재여부
	private int displayPageNum = 10; // 보이는 페이지 숫자의 갯수

	private Criteria cri;

	public PageMaker() {
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData(); // pageMaker 값 구함
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public int getTotalPager() {
		return (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
	}

	private void calcData() {
		/* -- 화면에 보일 번호 구하기 -- */
		// 현재 페이지의 끝 번호를 구구함. 첫 번호를 구하기 위해 필요함.
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		// 위에서 구한 끝 번호를 사용해 첫 번호를 구함
		startPage = (endPage - displayPageNum) + 1;

		// 전체 게시물의 갯수와 한 페이지에 보여질 게시물 갯수를 사용하여 마지막 페이지 번호를 구함.
		// ex) 전체 게시물: 153개, 한 페이지에 보여질 게시물: 10개 -> 마지막 페이지 번호: 16
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum())); // cri.getPageNum() : 한 페이지에 보여질
																						// 게시물의 갯수
		if (endPage > tempEndPage) { // 만약 이전에 구한 끝 페이지 번호가 실제 끝 페이지 번호보다 크다면 변경해줌.
			endPage = tempEndPage;
		}

		// 이전 페이지 여부
		if (startPage == 1) {
			prev = false;
		} else {
			prev = true;
		}

		// 이후 페이지 여부
		if (endPage * cri.getPerPageNum() >= totalCount) {
			next = false;
		} else {
			next = true;
		}

	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}

}// PageMaker
