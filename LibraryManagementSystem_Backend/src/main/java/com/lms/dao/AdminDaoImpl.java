package com.lms.dao;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.lms.bean.Admin;
import com.lms.bean.Book;
import com.lms.bean.BookInfo;
import com.lms.bean.LibraryInfo;
import com.lms.bean.Request;
import com.lms.bean.Student;


@Repository
public class AdminDaoImpl implements IAdminDao{
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Admin checkLoginDetails(String adminEmail, String password) {
		Query query=new Query();
		query.addCriteria(Criteria.where("adminEmail").is(adminEmail));
		Admin admin=mongoTemplate.findOne(query, Admin.class);
		if(admin != null) {
			if(admin.getPassword().matches(password))
				return admin;
		}
		return null;
	}

	@Override
	public Boolean addDetails(Admin admin) {
		Query query=new Query();
		query.addCriteria(Criteria.where("adminEmail").is(admin.getAdminEmail()));
		if(mongoTemplate.findOne(query, Admin.class)==null)
		{
			mongoTemplate.insert(admin);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean addBook(Book book) {
		Query query =new Query();
		query.addCriteria(Criteria.where("bookId").is(book.getBookId()));
		if(mongoTemplate.insert(book)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateBook(String bookId, int noOfBooks) {
		Query query =new Query();
		query.addCriteria(Criteria.where("bookId").is(bookId));
		Book book=mongoTemplate.findOne(query, Book.class);
		if(book!=null) {
			book.setNoOfBooks(book.getNoOfBooks()+noOfBooks);
			mongoTemplate.save(book);
			return true;
		}
		return false;
	}

	@Override
	public List<Book> deleteBook(String bookId) {
		Query query=new Query();
		query.addCriteria(Criteria.where("bookId").is(bookId));
		Book book=mongoTemplate.findOne(query, Book.class);
		if(book!=null) {
			List<Student> students= mongoTemplate.findAll(Student.class);
			Iterator<Student> studentIterator=students.iterator();
			while(studentIterator.hasNext()) {
				Student student=studentIterator.next();
				if(student!=null) {
					if(student.getLibraryInfo().getBookList()!=null) {
						List<BookInfo> bookInfos=student.getLibraryInfo().getBookList();
						Iterator<BookInfo> iter = bookInfos.iterator();
						while(iter.hasNext()) {
							BookInfo bookInfo=iter.next();
							String status=bookInfo.getStatus();
							System.out.println(book.getBookId()+" "+bookInfo.getBookId());
							if((bookInfo.getBookId().contentEquals(book.getBookId()))&&(status.contains("pending"))) {
								bookInfo.setStatus("Book Not Available");
								Query requestQuery=new Query();
								requestQuery.addCriteria(Criteria.where("issueId").is(bookInfo.getIssueId()));
								mongoTemplate.remove(requestQuery, Request.class);
							}
						}
						student.getLibraryInfo().setBookList(bookInfos);
					}
					mongoTemplate.save(student);
				}
			}
			mongoTemplate.remove(query, Book.class);
			return mongoTemplate.findAll(Book.class);
		}
		return null;
	}

	@Override
	public List<Request> issueBook(Integer issueId,String studentId) {
		Query studentQuery=new Query();
		studentQuery.addCriteria(Criteria.where("studentId").is(studentId));
		Student student=mongoTemplate.findOne(studentQuery, Student.class);
		if(student.getLibraryInfo()==null) {
			return null;
		}
		LibraryInfo libraryInfos=student.getLibraryInfo();
		if(libraryInfos.getBookList()==null) {
			return null;
		}
		List<BookInfo> bookInfos=libraryInfos.getBookList();
		Iterator<BookInfo> iter = bookInfos.iterator();
		while(iter.hasNext()) {
			BookInfo bookInfo=iter.next();
			if((bookInfo.getIssueId()==issueId)&&(student.getBookLimit()>0)) {
				Query bookQuery=new Query();
				System.out.println(bookInfo.getBookId());
				bookQuery.addCriteria(Criteria.where("bookId").is(bookInfo.getBookId()));
				Book book=mongoTemplate.findOne(bookQuery, Book.class);
				Query removeRequest=new Query();
				removeRequest.addCriteria(Criteria.where("issueId").is(issueId));
				mongoTemplate.findAndRemove(removeRequest, Request.class);
				LocalDate date=LocalDate.now();
				bookInfo.setIssueDate(date);
				bookInfo.setStatus("issued");
				bookInfo.setDueDate(date.plusDays(15));
				student.setBookLimit(student.getBookLimit()-1);
				book.setNoOfBooks(book.getNoOfBooks()-1);
				mongoTemplate.save(book);
			}
		}
		libraryInfos.setBookList(bookInfos);
		student.setLibraryInfo(libraryInfos);
		mongoTemplate.save(student);
		return mongoTemplate.findAll(Request.class);
	}

	@Override
	public Integer calculateFine(String studentId) {
		Query studentQuery=new Query();
		studentQuery.addCriteria(Criteria.where("studentId").is(studentId));
		Student student=mongoTemplate.findOne(studentQuery, Student.class);
		if(student.getLibraryInfo()!=null) {
			return student.getLibraryInfo().getTotalFine();
		}
		return -1;
	}

	@Override
	public List<Request> rejectIssue(Integer issueId, String studentId) {
		System.out.println("came");
		Query studentQuery=new Query();
		studentQuery.addCriteria(Criteria.where("studentId").is(studentId));
		Student student=mongoTemplate.findOne(studentQuery, Student.class);
		if(student.getLibraryInfo()==null) {
			return null;
		}
		LibraryInfo libraryInfos=student.getLibraryInfo();
		if(libraryInfos.getBookList()==null) {
			return null;
		}
		List<BookInfo> bookInfos=libraryInfos.getBookList();
		Iterator<BookInfo> iter = bookInfos.iterator();
		while(iter.hasNext()) {
			BookInfo bookInfo=iter.next();
			if(bookInfo.getIssueId()==issueId) {
				Query removeRequest=new Query();
				removeRequest.addCriteria(Criteria.where("issueId").is(issueId));
				mongoTemplate.findAndRemove(removeRequest, Request.class);
				bookInfo.setStatus("rejected");
				mongoTemplate.save(student);
				return mongoTemplate.findAll(Request.class);
			}
		}
		return null;
	}

	@Override
	public List<Request> getAllRequests() {
		return mongoTemplate.findAll(Request.class);
	}

	@Override
	public Boolean addStudent(String studentId) {
		if(checkUser(studentId)!=null)
			return false;
		Student student=new Student();
		student.setStudentId(studentId);
		mongoTemplate.save(student);
		return true;
		}
		
		public Student checkUser(String id) {
			Query query=new Query();
			query.addCriteria(Criteria.where("id").is(id));
			return mongoTemplate.findOne(query,Student.class);
		}

		@Override
		public List<Book> getAllBooks() {
			return mongoTemplate.findAll(Book.class);
		}
}
