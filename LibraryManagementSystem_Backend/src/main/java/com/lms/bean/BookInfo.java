package com.lms.bean;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

public class BookInfo {
	
	@Id
	private Integer issueId;
	private String bookId;
	private String bookName;
	private String bookAuthor;
	private String bookUrl;
	private String status;
	private Integer rackNumber;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private Integer fine;
		
	public BookInfo(Integer issueId, String bookId,String bookUrl, String bookName, String bookAuthor, String status,
			Integer rackNumber, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate, Integer fine) {
		super();
		this.issueId = issueId;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.status = status;
		this.rackNumber = rackNumber;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.fine = fine;
	}
	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public Integer getFine() {
		return fine;
	}
	public void setFine(Integer fine) {
		this.fine = fine;
	}
	public Integer getRackNumber() {
		return rackNumber;
	}
	public void setRackNumber(Integer rackNumber) {
		this.rackNumber = rackNumber;
	}
	public String getBookUrl() {
		return bookUrl;
	}
	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

}
