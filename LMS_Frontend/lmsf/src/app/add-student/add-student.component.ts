import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  message:string;
  status:any;

  onSubmit(studentId:any){
    this.lmsService.addStudent(studentId.id).subscribe((data:boolean)=>{
      this.status=data;
      if(this.status){
        this.message="Student Id is added Succesfully!!"
      }
      else{
        this.message="Already exists!!"
      }
    });
  }
  constructor(private lmsService:LmsService) { }

  ngOnInit() {
  }

}
