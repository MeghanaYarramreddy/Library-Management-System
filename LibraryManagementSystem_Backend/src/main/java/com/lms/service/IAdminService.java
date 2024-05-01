package com.lms.service;

import java.util.List;

import com.lms.bean.Admin;
import com.lms.bean.Book;
import com.lms.bean.Request;

public interface IAdminService {
	Admin checkLoginDetails(String adminEmail, String password);
	Boolean addDetails(Admin admin);
	Boolean addBook(Book book);
	Boolean updateBook(String bookId, int noOfBooks);
	List<Book> deleteBook(String bookId);
	List<Request> issueBook(Integer issueId, String studentId);
	Integer calculateFine(String studentId);
	List<Request> rejectIssue(Integer issueId, String studentId);
	List<Request> getAllRequests();
	Boolean addStudent(String studentId);
	List<Book> getAllBooks();
}
