package com.lms.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Request {
	@Id
	private Integer issueId;
	private String studentId;
	private String studentName;
	private String bookName;
	private String bookUrl;
	private String bookAuthor;
	private Integer noOfBooksAvailable;
	public Request() {
		super();
	}
	public Request(Integer issueId,String bookUrl, String studentId, String studentName, String bookName, String bookAuthor,
			Integer noOfBooksAvailable) {
		super();
		this.issueId = issueId;
		this.studentId = studentId;
		this.studentName = studentName;
		this.bookName = bookName;
		this.bookUrl=bookUrl;
		this.bookAuthor = bookAuthor;
		this.noOfBooksAvailable = noOfBooksAvailable;
	}

	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	public Integer getNoOfBooksAvailable() {
		return noOfBooksAvailable;
	}
	public void setNoOfBooksAvailable(Integer noOfBooksAvailable) {
		this.noOfBooksAvailable = noOfBooksAvailable;
	}
	public String getBookUrl() {
		return bookUrl;
	}
	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}
	
}
