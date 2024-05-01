package com.lms.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lms.bean.Admin;
import com.lms.bean.Book;
import com.lms.bean.Request;
import com.lms.service.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	IAdminService iAdminService;
	
	@GetMapping("/login")
	public Admin checkLoginDetails(@RequestParam String adminEmail, String password) {
		Admin admin=iAdminService.checkLoginDetails(adminEmail,password);
		if(admin != null)
		{
			return admin;
		}
		return null;
	}
	
	@PostMapping("/addstudent")
	public Boolean addStudent(@RequestParam String studentId) {
		return iAdminService.addStudent(studentId);
	}
	
	@PostMapping("/register")
	public Boolean register(@RequestBody Admin admin) {
		if(iAdminService.addDetails(admin))
			return true;
		return false;
	}
	
	@PostMapping("/addbook")
	public Boolean addBook(@RequestParam("file") MultipartFile file1) throws IOException{
		String line,name="C:\\Users\\ssurathu\\Desktop\\"+file1.getOriginalFilename();
		FileReader fr=new FileReader(name);
		BufferedReader br=new BufferedReader(fr);
	    while((line=br.readLine())!=null) {
	    	Book book=new Book();
	    	String data[]=line.split(",");
	    	book.setBookId(data[0]);
	    	book.setBookName(data[1]);
	    	book.setBookAuthor(data[2]);
	    	book.setBookUrl(data[3]);
	    	book.setRackNumber(Integer.parseInt(data[4]));
	    	book.setNoOfBooks(Integer.parseInt(data[5]));
	    	iAdminService.addBook(book);
	    }
	    br.close();
	    return true;
	}
	
	@PostMapping("/updatebook")
	public Boolean updateBook(@RequestParam String bookId,int noOfBooksToBeAdded) {
		return iAdminService.updateBook(bookId,noOfBooksToBeAdded);
	}
	
	@DeleteMapping("/deletebook")
	public List<Book> deleteBook(@RequestParam String bookId) {
		return iAdminService.deleteBook(bookId);
	}
	
	@PostMapping("/issuebook")
	public List<Request> issueBook(@RequestParam Integer issueId,String studentId) {
		return iAdminService.issueBook(issueId,studentId);
	}
	
	@PostMapping("/rejectIssue")
	public List<Request> rejectIssue(@RequestParam Integer issueId,String studentId) {
		return iAdminService.rejectIssue(issueId,studentId);
	}
	
	@GetMapping("/calculateFine")
	public Integer calculateFine(@RequestParam String studentId) {
		return iAdminService.calculateFine(studentId);
	}

	@GetMapping("/getallbooks")
	public List<Book> getAllBooks(){
		if(iAdminService.getAllBooks().size()==0) {
			return null;
		}
		return iAdminService.getAllBooks();
	}
	
	@GetMapping("/getallrequests")
	public List<Request> getAllRequests(){
		if(iAdminService.getAllRequests().size()==0) {
			return null;
		}
		return iAdminService.getAllRequests();
	}
}
