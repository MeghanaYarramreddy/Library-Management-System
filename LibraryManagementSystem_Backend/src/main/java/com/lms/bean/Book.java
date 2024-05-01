package com.lms.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {
	@Id
	private String bookId;
	private String bookName;
	private String bookUrl;
	private String bookAuthor;
	private Integer rackNumber;
	private Integer noOfBooks;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public Integer getRackNumber() {
		return rackNumber;
	}
	public void setRackNumber(Integer rackNumber) {
		this.rackNumber = rackNumber;
	}
	public Integer getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(Integer noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookId,String bookUrl, String bookName, String bookAuthor, Integer rackNumber, Integer noOfBooks) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.rackNumber = rackNumber;
		this.noOfBooks = noOfBooks;
	}
	public String getBookUrl() {
		return bookUrl;
	}
	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}
}
