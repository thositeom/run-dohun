/**
 * Copyright (c) 2010 Saltware, Inc.
 * 
 * http://www.saltware.co.kr
 * 
 * Kolon Science Valley Bldg 2th. 901, Guro-dong 811, Guro-gu,
 * Seoul, 152-878, South Korea.
 * All Rights Reserved.
 * 
 * This software is the Java based Enterprise Portal of Saltware, Inc.
 * Making any change or distributing this without permission from us is out of law.
 */
package com.saltware.enface.util;

/**
 * 게시판등 각종 목록조회시 페이지 정보 생성 클래스.
 * 
 * @author <${developer}> (${email})
 * @author Last changed by: ${developer}
 * @version 3.2.2
 * @since 1.0-SNAPSHOT
 */
public class PageUtil {

	// 페이지당 보여줄 갯수(기본값)
	public static final int LIST_COUNT = 10;
	public static final int PAGE_COUNT = 10;
	
	// 전체
	private int total;
	// 현재 페이지
	private int currentPage;
	// 페이지당 보여줄 갯수
	private int listCount;
	// 하단페이지 갯수
	private int pageCount;
	
	public void setTotal(int total) { 
		this.total = total;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getCurrentPage() {
		return this.currentPage;
	}
	
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	public int getListCount() {
		return this.listCount;
	}
	
	public PageUtil(int total) {
		this.total = total;
		this.currentPage = 1;
		this.listCount = LIST_COUNT;
		this.pageCount = PAGE_COUNT;
	}
	
	public PageUtil(int total, int currentPage) {
		this.total = total;
		this.currentPage = currentPage;
		this.listCount = LIST_COUNT;
		this.pageCount = PAGE_COUNT;
	}
	
	public PageUtil(int total, int currentPage, int listCount) {
		this.total = total;
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.pageCount = PAGE_COUNT;
	}
	
	public PageUtil(int total, int currentPage, int listCount, int pageCount) {
		this.total = total;
		this.currentPage = currentPage;
		this.listCount = listCount;
		this.pageCount = pageCount;
	}
	
	public PageUtil() {}
	
	// 총페이지수
	public int getPages() {
		int page = getTotal() / getListCount();
		int mod = getTotal() % getListCount();
		
		if (mod > 0) {
			return page+1;
		}
		
		return page;
	}
	
	// 마지막 페이지수
	private int getLastPageCount() {
		return getTotal() % getListCount();
	}
	
	// 현재 페이지 시작 Row
	public int getCurrentStartRow() {
		return getCurrentPage() * getListCount() - (getListCount()-1);
	}
	
	// 현재 페이지 마지막 Row 
	public int getCurrentEndRow() {
		if (currentPage == getPages()) {
			if (getLastPageCount() != 0)
				return getCurrentStartRow() + getLastPageCount();
			else
				return getTotal();
		}
		
		return currentPage * getListCount();
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public int getCurrentStartPage() {
		int p = Math.abs(getCurrentPage() / getPageCount());
		if (getCurrentPage() % getPageCount() == 0)
			return (p-1) * pageCount + 1;
		
		return p * pageCount + 1;
	}
	
	public int getCurrentEndPage() {
		int lastPage = getCurrentStartPage() + getPageCount() - 1;
		if (lastPage > getPages()) {
			return getPages();
		}
		return lastPage;
	}
	
	public int getCurrentBeforePage() {
		if (getCurrentStartPage() > 1) {
			return getCurrentStartPage() - 1;
		}
		return 1;
	}
	
	public int getCurrentNextPage() {
		if (getCurrentEndPage() == getPages()) {
			return getCurrentEndPage();
		}
		return getCurrentEndPage() + 1;
	}
	
//	public static void main(String[] args) {
//		PageUtil p = new PageUtil(20, 1, 10);
//		System.out.println("currentPage="+p.getCurrentPage());
//		System.out.println("total="+p.getTotal());
//		System.out.println("pages="+p.getPages());
//		System.out.println("startPage="+p.getCurrentStartPage());
//		System.out.println("endPage="+p.getCurrentEndPage());
//		System.out.println("startRow="+p.getCurrentStartRow());
//		System.out.println("endRow="+p.getCurrentEndRow());
//		
//	}
}
