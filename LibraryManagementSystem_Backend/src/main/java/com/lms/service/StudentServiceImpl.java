package com.lms.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.bean.Book;
import com.lms.bean.BookInfo;
import com.lms.bean.Student;
import com.lms.dao.IStudentDao;

@Service
public class StudentServiceImpl implements IStudentService{
	
	@Autowired
	IStudentDao studentDao;

	@Override
	public String registerStudent(Student student) {
		student.setPassword(toEncode(student.getPassword()));
		return studentDao.registerStudent(student);
	}
	public static String toEncode(String message) {
		 return Base64.getEncoder().encodeToString(message.getBytes());
		}
	@Override
	public Student checkLoginDetails(String id, String password) {
		Student student=studentDao.checkLoginDetails(id,password);
		if(student==null) {
			return null;
		}
		String studentPassword=toDecode(student.getPassword());
		if(studentPassword.matches(password)) {
			return student;	
		}
		 return null;
	}
	public static String toDecode(String message) {
		byte[] decodedBytes = Base64.getDecoder().decode(message);
		return new String(decodedBytes);
	}
	
	@Override
	public List<Book> getAllBooks() {
		return studentDao.getAllBooks();
	}
	
	@Override
	public Boolean borrowBook(String bookId, String studentId) {
		return studentDao.borrowBook(bookId,studentId);
	}
	
	@Override
	public List<BookInfo> returnBook(Integer issueId, String studentId) {
		return studentDao.returnBook(issueId,studentId);
	}
	@Override
	public List<BookInfo> getCart(String studentId) {
		return studentDao.getCart(studentId);
	}
	@Override
	public List<BookInfo> getCard(String studentId) {
		return studentDao.getCard(studentId);
	}
	@Override
	public Integer getTotalFine(String studentId) {
		return studentDao.getTotalFine(studentId);
	}
}
