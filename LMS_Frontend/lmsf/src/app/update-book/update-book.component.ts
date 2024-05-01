import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  bookId:String;
  status:boolean;
  message:string;

  onSubmit(form:any){
    console.log(form.noOfBooksToBeAdded);
    this.lmsService.updateBook(form.noOfBooksToBeAdded).subscribe((data:boolean)=>{
      this.status=data;
      if(this.status){
        this.message="Successful...";
      }
      else{
        this.message="Sorry!! Couldn't upload....";
      }
      alert(this.message);
    });
  }

  constructor(private lmsService:LmsService,private router:Router) { }

  ngOnInit() {
    this.bookId=this.lmsService.getBookId();
  }

}
