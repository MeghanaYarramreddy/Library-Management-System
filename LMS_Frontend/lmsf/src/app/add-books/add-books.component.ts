import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-add-books',
  templateUrl: './add-books.component.html',
  styleUrls: ['./add-books.component.css']
})
export class AddBooksComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  message:string;

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }
  
  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    this.lmsService.addProduct(this.currentFileUpload).subscribe(event => {
      alert("Successfully added....");
    });  
    this.selectedFiles = undefined;
  }

  constructor(private lmsService:LmsService) { }

  ngOnInit() {
  }

}
