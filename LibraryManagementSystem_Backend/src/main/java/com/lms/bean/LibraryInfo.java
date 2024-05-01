package com.lms.bean;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LibraryInfo {
	List<BookInfo> bookList;
	Integer totalFine;
	public List<BookInfo> getBookList() {
		return bookList;
	}
	public void setBookList(List<BookInfo> bookList) {
		this.bookList = bookList;
	}
	public Integer getTotalFine() {
		return totalFine;
	}
	public void setTotalFine(Integer fine) {
		this.totalFine = fine;
	}
	public LibraryInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LibraryInfo(List<BookInfo> bookList, Integer totalFine) {
		super();
		this.bookList = bookList;
		this.totalFine = totalFine;
	}
}
