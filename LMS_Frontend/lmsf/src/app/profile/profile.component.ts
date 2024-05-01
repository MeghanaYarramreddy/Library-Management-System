import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  student:any;

  constructor(private lmsService:LmsService) { }

  ngOnInit() {
    this.student=this.lmsService.getUser();
  }

}
