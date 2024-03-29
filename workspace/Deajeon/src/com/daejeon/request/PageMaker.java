package com.daejeon.request;

public class PageMaker {
	private int totalCount;
	private int startPage=1;
	private int endPage=1;
	private int realEndPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private SearchCriteria cri;
	
	private void calcData() {
		endPage = (int)(Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		realEndPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		if(startPage < 0) {
			startPage = 1;
		}
		
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		prev = startPage == 1? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false: true;
	}

	public String makeQuery() {
		return makeQuery(cri.getPage());
	}
	
	public String makeQuery(int page) {
		String query="?page=" + page
					+"&perPageNum="+cri.getPerPageNum()
					+"&searchType="+cri.getSearchType()
					+"&keyword="+cri.getKeyword();
		return query;
	}
	
	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
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


	public int getRealEndPage() {
		return realEndPage;
	}


	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
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


	public SearchCriteria getCri() {
		return cri;
	}


	public void setCri(SearchCriteria cri) {
		this.cri = cri;
	}
}
