import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  message:string;
  status:Boolean;

  onSubmit(userForm:any){
    this.lmsService.register(userForm).subscribe((data:Boolean)=>{
      this.status=data;
      if(this.status){
        this.router.navigate(['/home']);
      }
      else{
        this.message="No student with this id is present in the college!!!!!"
      }
    });
  }

  back(){
    this.router.navigate(['/home']);
  }
  
  constructor(private router:Router,private lmsService:LmsService) { }

  ngOnInit() {
  }

}
