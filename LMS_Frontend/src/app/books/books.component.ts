import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  message:string;
  books:any[];
  status:boolean;
  searchTerm:string;

  constructor(private router:Router,private lmsService:LmsService) { }

  delete(id:any){
    this.lmsService.deleteBook(id).subscribe((data:any)=>{
      this.books=data;
      if(this.books!=null){
        alert("Book with id: "+id+" is deleted");
      }
      else{
        alert("Sorry!! Unable to delete...");
      }
    });
  }

  update(id:any){
    this.lmsService.setBookId(id);
    this.router.navigate(['/updatebook']);
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
