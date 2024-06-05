package com.tjoeun.beans;

import lombok.Data;

@Data
public class PageBean {
	
	private int min;
	private int max;
	private int prevPage;
	private int nextPage;
	private int pageCount;
	private int currentPage;
  /*
    int contentCount : (각 게시판 별)전체 게시글 개수
    int currentPage  : 현재 페이지 번호
    int contentPageCount : 페이지 당 게시글 개수 
    int paginationCount  : 페이지 버튼 개수
  */
	public PageBean(int contentCount, int currentPage, 
			            int contentPageCount, int paginationCount) {

		this.currentPage = currentPage;
		
		this.pageCount = contentCount / contentPageCount;
		
		if(contentCount % contentPageCount > 0) {
			this.pageCount++;
		}
	
		this.min = ((currentPage - 1) / contentPageCount) * contentPageCount + 1;

		this.max = this.min + paginationCount - 1;
	
		if(this.max > this.pageCount) {
			this.max = this.pageCount;
		}

		this.prevPage = this.min - 1;

		this.nextPage = this.max + 1;

		if(this.nextPage > this.pageCount) {
			this.nextPage = this.pageCount;
		}
		
	}
	
	
	
	
	
	
}
