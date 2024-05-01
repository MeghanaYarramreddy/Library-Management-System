package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.bean.Book;
import com.lms.bean.BookInfo;
import com.lms.bean.Student;
import com.lms.service.IStudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200") 
public class StudentController {
	
	@Autowired
	IStudentService studentService;
	
	@PostMapping("/register")
	public Boolean registerStudent(@RequestBody Student student) {
		if(studentService.registerStudent(student) != null)
			return true;
		return false;
	}
	
	@GetMapping("/login")
	public Student checkLoginDetails(@RequestParam String id, String password){
		Student student=studentService.checkLoginDetails(id,password);
		if( student!= null)
		{
			return student;
		}
		return null;
	}
	
	@GetMapping("/getallbooks")
	public List<Book> getAllBooks(){
		return studentService.getAllBooks();
	}
	
	@PostMapping("/borrowbook")
	public Boolean borrowBook(@RequestParam String bookId,String studentId) {
		return studentService.borrowBook(bookId,studentId);
	}
	
	@PostMapping("/returnbook")
	public List<BookInfo> returnBook(@RequestParam Integer issueId,String studentId) {
		return studentService.returnBook(issueId,studentId);
	}
	
	@GetMapping("/getcart")
	public List<BookInfo> getCart(@RequestParam String studentId){
		return studentService.getCart(studentId);
	}
	
	@GetMapping("/getcard")
	public List<BookInfo> getCard(@RequestParam String studentId){
		if(studentService.getCard(studentId).size()==0)
		{
			return null;
		}
		return studentService.getCard(studentId);		
	}
	
	@GetMapping("/gettotalfine")
	public Integer getTotalFine(@RequestParam String studentId) {
		System.out.println("here we go");
		return studentService.getTotalFine(studentId);
	}
}
