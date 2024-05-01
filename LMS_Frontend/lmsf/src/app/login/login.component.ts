import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:any;
  message:string;

  onSubmit(loginForm:any){
    this.lmsService.loginUser(loginForm).subscribe((data:any)=>{
      this.user=data;
      if(this.user!=null)
        {
          this.lmsService.setUser(this.user);
          if(loginForm.role=="admin"){
            this.router.navigate(['/adminhome']);
          }
          else{
            this.router.navigate(['/studenthome']);
          }
        }
      else{
        this.message="Invalid UserName or password!!!";
      }
    });
  }

  register(){
    this.router.navigate(['/register']);
  }
  constructor(private router:Router,private lmsService:LmsService) { }

  ngOnInit() {
  }

}
