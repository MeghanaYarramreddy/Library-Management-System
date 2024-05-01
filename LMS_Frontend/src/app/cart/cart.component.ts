import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  bookInfos:any[];
  message:string;

  constructor(private lmsService:LmsService) { }

  ngOnInit() {
    this.lmsService.getCart().subscribe((data:any)=>{
      this.bookInfos=data;
      if(this.bookInfos==null){
        this.message="No books are in cart!!";
      }
    });
  }

}
