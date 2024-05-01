import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LmsService {

  user:any;
  userName:any;
  bookId:any;

  loginUser(userCredentials:any){
    this.userName = userCredentials.name;
    let password = userCredentials.password;
    let role = userCredentials.role;
    if(role=="admin"){
      return this.http.get("http://localhost:9812/admin/login?adminEmail="+this.userName +"&password=" + password);
    }
    
    return this.http.get("http://localhost:9812/student/login?id="+this.userName+"&password="+password);
  }

  setUser(userCredentials:any){
    this.user=userCredentials;
  }

  getUser(){
    return this.user;
  }

  register(user:any){
    let input={
      "studentId":user.studentId,
      "studentName":user.name,
      "email":user.studentEmail,
      "password":user.password,
    }
    return this.http.post("http://localhost:9812/student/register",input);
  }
  
  addStudent(studentId:any){
    return this.http.post("http://localhost:9812/admin/addstudent?studentId="+studentId,true);
  }

  addProduct(file:File): Observable<any> {
    const formdata = new FormData();
    formdata.append('file', file);
    return this.http.post("http://localhost:9812/admin/addbook", formdata, {
      reportProgress: true,
      responseType: 'text'
    });
  }

  getBooks(){
    return this.http.get("http://localhost:9812/admin/getallbooks");
  }

  borrowBook(id:any){
    return this.http.post("http://localhost:9812/student/borrowbook?bookId="+id+"&studentId="+this.user.studentId,true);
  }

  calculateFine(id:any){
    return this.http.get("http://localhost:9812/admin/calculateFine?studentId="+id);
  }

  issueBook(issueId:any,studentId:any){
    return this.http.post("http://localhost:9812/admin/issuebook?issueId="+issueId+"&studentId="+studentId,true);
  }

  deleteBook(id:any){
    return this.http.delete("http://localhost:9812/admin/deletebook?bookId="+id);
  }

  reject(id:any,studentId:any){
    return this.http.post("http://localhost:9812/admin/rejectIssue?issueId="+id+"&studentId="+studentId,true);
  }
  updateBook(noOfBooksToBeAdded:any){
    return this.http.post("http://localhost:9812/admin/updatebook?bookId="+this.bookId+"&noOfBooksToBeAdded="+noOfBooksToBeAdded,true);
  }

  getAllRequests(){
    return this.http.get("http://localhost:9812/admin/getallrequests");
  }

  setBookId(id:any){
    this.bookId=id;
  }

  getBookId(){
    return this.bookId;
  }

  getCart(){
    return this.http.get("http://localhost:9812/student/getcart?studentId="+this.user.studentId);
  }

  getCard(){
    return this.http.get("http://localhost:9812/student/getcard?studentId="+this.user.studentId);
  }

  returnBook(id:any){
    return this.http.post("http://localhost:9812/student/returnbook?issueId="+id+"&studentId="+this.getUser().studentId,true);
  }

  getTotalFine(){
    console.log("going");
    return this.http.get("http://localhost:9812/student/gettotalfine?studentId="+this.getUser().studentId);
  }

  constructor(private http:HttpClient) { }
}