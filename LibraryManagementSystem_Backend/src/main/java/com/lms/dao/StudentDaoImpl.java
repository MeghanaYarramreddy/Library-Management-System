package com.lms.dao;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.lms.bean.Book;
import com.lms.bean.BookInfo;
import com.lms.bean.LibraryInfo;
import com.lms.bean.Request;
import com.lms.bean.Student;

@Repository
public class StudentDaoImpl implements IStudentDao {
 
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String registerStudent(Student student) {
		if(checkUser(student.getStudentId())==null)
		return null;
		student.setBookLimit(3);
		if(student.getLibraryInfo()==null) {
			student.setLibraryInfo(new LibraryInfo());
		}
		LibraryInfo libraryInfos=student.getLibraryInfo();
		libraryInfos.setTotalFine(0);
		mongoTemplate.save(student);
		return "saved";
	}
	
	public Student checkUser(String id) {
		Query query=new Query();
		query.addCriteria(Criteria.where("studentId").is(id));
		return mongoTemplate.findOne(query,Student.class);
	}
	
	@Override
	public Student checkLoginDetails(String id, String password) {
		Student student=checkUser(id);
		if(student==null)
		{
			return null;
		}
		return student;
	}

	@Override
	public List<Book> getAllBooks() {
		return mongoTemplate.findAll(Book.class);
	}

	@Override
	public Boolean borrowBook(String bookId, String studentId) {
		Query bookQuery=new Query();
		int issueId=1;
		bookQuery.addCriteria(Criteria.where("bookId").is(bookId));
		Book book=mongoTemplate.findOne(bookQuery, Book.class);
		Query studentQuery=new Query();
		studentQuery.addCriteria(Criteria.where("studentId").is(studentId));
		Student student=mongoTemplate.findOne(studentQuery, Student.class);
		if((book.getNoOfBooks()>0)&&(student.getBookLimit()>0)) {
			LibraryInfo libraryInfos=student.getLibraryInfo();
			if(libraryInfos.getBookList()==null) {
				libraryInfos.setBookList(new ArrayList<BookInfo>()); 
				}
			List<BookInfo> bookInfos=libraryInfos.getBookList();
			if(!bookInfos.isEmpty()) {
				Iterator<BookInfo> bookIterator=bookInfos.iterator();
				while(bookIterator.hasNext()) {
					BookInfo bookInfo2=bookIterator.next();
					if(bookInfo2.getIssueId()==issueId) {
						issueId++;
					}
				}
			}
			BookInfo bookInfo=new BookInfo();
			bookInfo.setIssueId(issueId);
			bookInfo.setBookId(book.getBookId());
			bookInfo.setBookName(book.getBookName());
			bookInfo.setStatus("pending");
			bookInfo.setBookAuthor(book.getBookAuthor());
			bookInfo.setBookUrl(book.getBookUrl());
			bookInfo.setRackNumber(book.getRackNumber());
			bookInfos.add(bookInfo);
			libraryInfos.setBookList(bookInfos);
			student.setLibraryInfo(libraryInfos);
			Request request=new Request();
			request.setIssueId(issueId); 
			request.setBookAuthor(book.getBookAuthor());
			request.setBookName(book.getBookName());
			request.setStudentId(studentId);
			request.setStudentName(student.getStudentName());
			request.setBookUrl(book.getBookUrl());
			request.setNoOfBooksAvailable(book.getNoOfBooks());
			mongoTemplate.save(student);
			mongoTemplate.save(request);
			return true;
			}
		return false;
	}

	@Override
	public List<BookInfo> returnBook(Integer issueId, String studentId) {
		  Query query =new Query();
		  query.addCriteria(Criteria.where("studentId").is(studentId));
		  Student student=mongoTemplate.findOne(query, Student.class);
		  if(student!=null) {
			  LibraryInfo libraryInfo=student.getLibraryInfo();
			  if(libraryInfo.getBookList()!=null) {
				  Iterator<BookInfo> bookIterator=libraryInfo.getBookList().iterator();
				  while(bookIterator.hasNext()) {
					  BookInfo bookInfo=bookIterator.next();
					  if(bookInfo.getIssueId()==issueId) {
						  Query bookQuery =new Query();
						  bookQuery.addCriteria(Criteria.where("bookId").is(bookInfo.getBookId()));
						  Book book=mongoTemplate.findOne(bookQuery, Book.class);
						  if(book==null) {
							  book=new Book();
							  book.setNoOfBooks(1);
							  book.setBookId(bookInfo.getBookId());
							  book.setBookName(bookInfo.getBookName());
							  book.setBookUrl(bookInfo.getBookUrl());
							  book.setBookAuthor(bookInfo.getBookAuthor());
							  book.setRackNumber(bookInfo.getRackNumber());
						  }
						  else {
							  book.setNoOfBooks(book.getNoOfBooks()+1);
						  }
						  bookInfo.setReturnDate(LocalDate.now());
						  bookInfo.setStatus("returned");
						  int fine=0;
						  if(bookInfo.getReturnDate().isAfter(bookInfo.getDueDate())) {
							  int days=Period.between(bookInfo.getDueDate(), bookInfo.getReturnDate()).getDays();
							  fine=1*days;
							  bookInfo.setFine(Math.abs(fine));
							  libraryInfo.setTotalFine(fine+bookInfo.getFine());
						  }
						  else {
							  bookInfo.setFine(0);
						  }
						  student.setBookLimit(student.getBookLimit()+1);
						  mongoTemplate.save(book);
					  }
				  }
				  mongoTemplate.save(student);
				  return getCard(studentId);
			  }
		  }
		return null;
	}

	@Override
	public List<BookInfo> getCart(String studentId) {
		Query query=new Query();
		query.addCriteria(Criteria.where("studentId").is(studentId));
		Student student=mongoTemplate.findOne(query,Student.class);
		if(student!=null) {
			if(student.getLibraryInfo().getBookList()!=null) {
				List<BookInfo> bookInfo=student.getLibraryInfo().getBookList();
				Iterator<BookInfo> bookIterator=bookInfo.iterator();
				List<BookInfo> cartList=new ArrayList<BookInfo>();
				while(bookIterator.hasNext()) {
					BookInfo bookInf=bookIterator.next();
					if((bookInf.getStatus().contains("Book Not Available"))||(bookInf.getStatus().contains("issued"))||(bookInf.getStatus().contains("rejected"))||(bookInf.getStatus().contains("returned"))) {
						
					}
					else {
						cartList.add(bookInf);
					}
				}
				if(cartList.isEmpty()) {
					return null;
				}
				return cartList;
			}
		}
		return null;
	}

	@Override
	public List<BookInfo> getCard(String studentId) {
		Query query =new Query();
		query.addCriteria(Criteria.where("studentId").is(studentId));
		Student student=mongoTemplate.findOne(query,Student.class);
		List<BookInfo> bookList=new ArrayList<BookInfo>();
		if(student!=null) {
			if(student.getLibraryInfo().getBookList()!=null) {
				List<BookInfo> bookInfos=student.getLibraryInfo().getBookList();
				Iterator<BookInfo> bookIterator=bookInfos.iterator();
				while(bookIterator.hasNext()) {
					BookInfo bookInfo=bookIterator.next();
					if((bookInfo.getStatus().contains("issued"))||(bookInfo.getStatus().contains("rejected"))||(bookInfo.getStatus().contains("Book Not Available"))||(bookInfo.getStatus().contains("returned"))) {
						bookList.add(bookInfo);
					}
				}
			}
		}
		if(bookList.isEmpty()) {
			return null;
		}
		return bookList;
	}

	@Override
	public Integer getTotalFine(String studentId) {
		Query query =new Query();
		query.addCriteria(Criteria.where("studentId").is(studentId));
		Student student=mongoTemplate.findOne(query,Student.class);
		int totalFine=student.getLibraryInfo().getTotalFine();
		System.out.println("fine: "+totalFine);
		if(student.getLibraryInfo()!=null) {
			return student.getLibraryInfo().getTotalFine();
		}
		return totalFine;
	}
}