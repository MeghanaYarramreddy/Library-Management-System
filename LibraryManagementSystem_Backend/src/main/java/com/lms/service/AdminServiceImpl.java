package com.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.bean.Admin;
import com.lms.bean.Book;
import com.lms.bean.Request;
import com.lms.dao.IAdminDao;


@Service
public class AdminServiceImpl implements IAdminService{
	
	@Autowired
	IAdminDao iAdminDao;

	@Override
	public Admin checkLoginDetails(String adminEmail, String password) {
		return iAdminDao.checkLoginDetails(adminEmail, password);
	}

	@Override
	public Boolean addDetails(Admin admin) {
		return iAdminDao.addDetails(admin);
	}

	@Override
	public Boolean addBook(Book book) {
		return iAdminDao.addBook(book);
	}

	@Override
	public Boolean updateBook(String bookId, int noOfBooks) {
		return iAdminDao.updateBook(bookId,noOfBooks);
	}

	@Override
	public List<Book> deleteBook(String bookId) {
		return iAdminDao.deleteBook(bookId);
	}

	@Override
	public List<Request> issueBook(Integer issueId,String studentId) {
		return iAdminDao.issueBook(issueId,studentId);
	}

	@Override
	public Integer calculateFine(String studentId) {
		return iAdminDao.calculateFine(studentId);
	}

	@Override
	public List<Request> rejectIssue(Integer issueId, String studentId) {
		return iAdminDao.rejectIssue(issueId,studentId);
	}

	@Override
	public List<Request> getAllRequests() {
		return iAdminDao.getAllRequests();
	}

	@Override
	public Boolean addStudent(String studentId) {
		return iAdminDao.addStudent(studentId);
	}

	@Override
	public List<Book> getAllBooks() {
		return iAdminDao.getAllBooks();
	}
}
