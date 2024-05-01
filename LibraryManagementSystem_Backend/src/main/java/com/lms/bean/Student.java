package com.lms.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
	@Id
	private String studentId;
	private String studentName;
	private String email;
	private String password;
	private Integer bookLimit;
	private LibraryInfo libraryInfo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LibraryInfo getLibraryInfo() {
		return libraryInfo;
	}
	public void setLibraryInfo(LibraryInfo libraryInfo) {
		this.libraryInfo = libraryInfo;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String studentId,Integer bookLimit, String studentName, String email, String password, LibraryInfo libraryInfo) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.email = email;
		this.bookLimit=bookLimit;
		this.password = password;
		this.libraryInfo = libraryInfo;
	}
	public Integer getBookLimit() {
		return bookLimit;
	}
	public void setBookLimit(Integer bookLimit) {
		this.bookLimit = bookLimit;
	}
}
