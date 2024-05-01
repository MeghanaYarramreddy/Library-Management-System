import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-books',
  templateUrl: './show-books.component.html',
  styleUrls: ['./show-books.component.css']
})
export class ShowBooksComponent implements OnInit {

  books:any[];
  message:string;
  status:boolean;
  statusMessage:string;
  searchTerm:string;

  constructor(private router:Router,private lmsService:LmsService) { }

  borrowBook(id:string){
    this.lmsService.borrowBook(id).subscribe((data:boolean)=>{
      this.status=data;
      if(this.status){
        this.statusMessage="Successfully booked";
      }
      alert(this.statusMessage);
    });
  }

  ngOnInit() {
    this.lmsService.getBooks().subscribe((data:any)=>{
      this.books=data;
      if(this.books==null){
        this.message="No books are available in the library....";
      }
    });
  }

}
