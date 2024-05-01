import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(bookInfo:any[],searchTerm:string): any {
    if(!bookInfo|| !searchTerm){
      return bookInfo;
    }
    return bookInfo.filter(b=>(b.bookName.toLowerCase().startsWith(searchTerm.toString().toLowerCase()))||(b.bookAuthor.toLowerCase().startsWith(searchTerm.toString().toLowerCase())));
  }

}
