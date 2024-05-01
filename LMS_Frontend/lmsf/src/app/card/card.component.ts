import { Component, OnInit } from '@angular/core';
import { LmsService } from '../lms.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  bookInfos:any[];
  totalFine:any;
  message:string;

  returnBook(id:any){
    this.lmsService.returnBook(id).subscribe((data:any)=>{
      this.bookInfos=data;
      if(this.bookInfos==null){
        this.message="No books are borrowed...";
      }
    });
  }

  state(status:string){
    if((status=="issued")||(status=="returned"))
    {
      return true;
    }
    return false;
  }

  show(status:string){
    if(status=="issued"){
      return true;
    }
    return false;
  }

  showReturnedDate(status:string){
    if(status=="returned"){
      return true;
    }
    return false;
  }
  
  constructor(private lmsService:LmsService) { }

  ngOnInit() {
    this.lmsService.getCard().subscribe((data:any)=>{
      this.bookInfos=data;
      if(this.bookInfos==null){
        this.message="No books are borrowed...";
      }
    });
    this.lmsService.getTotalFine().subscribe((data:any)=>{
      this.totalFine=data;
      console.log(this.totalFine);
    });
  }

}
