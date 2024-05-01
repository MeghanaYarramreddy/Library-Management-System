package com.lms.dao;

import java.util.List;

import com.lms.bean.Book;
import com.lms.bean.BookInfo;
import com.lms.bean.Student;

public interface IStudentDao {
	
	String registerStudent(Student student);
	Student checkLoginDetails(String studentId, String password);
	List<Book> getAllBooks();
	Boolean borrowBook(String bookId, String studentId);
	List<BookInfo> returnBook(Integer issueId, String studentId);
	List<BookInfo> getCart(String studentId);
	List<BookInfo> getCard(String studentId);
	Integer getTotalFine(String studentId);

}
