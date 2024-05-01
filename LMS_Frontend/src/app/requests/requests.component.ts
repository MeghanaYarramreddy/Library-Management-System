import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests:any[];
  message:string;

  accept(id:any,studentId:any){
    this.lmsService.issueBook(id,studentId).subscribe((data:any)=>{
      this.requests=data;
      if(this.requests==null){
        this.message="No requests are there!!";
      }
    });
  }
  
  decline(id:any,studentId:any){
    console.log(id+" "+ studentId);
    this.lmsService.reject(id,studentId).subscribe((data:any)=>{
      this.requests=data;
      if(this.requests==null){
        this.message="No requests are there!!";
      }
    });
  }
  
  constructor(private lmsService:LmsService) { }

  ngOnInit() {
    this.lmsService.getAllRequests().subscribe((data:any)=>{
      this.requests=data;
      if(this.requests==null){
        this.message="No Requests are there!!";
      }
    });
  }

}
