import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-penalty',
  templateUrl: './penalty.component.html',
  styleUrls: ['./penalty.component.css']
})
export class PenaltyComponent implements OnInit {

  message:string;
  fine:number;

  onSubmit(id:any){
    this.lmsService.calculateFine(id.id).subscribe((data:any)=>{
      this.fine=data;
      if(this.fine==-1){
        this.message="Oops!! student not found..."
      }
      else{
        this.message="Student with id: "+id.id+" has "+"Rs "+this.fine+" /-";
      }
    });
  }
  constructor(private lmsService:LmsService) { }

  ngOnInit() {
  }

}
