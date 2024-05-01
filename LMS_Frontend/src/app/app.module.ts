import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PenaltyComponent } from './penalty/penalty.component';
import { BooksComponent } from './books/books.component';
import { ShowBooksComponent } from './show-books/show-books.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { SearchBookComponent } from './search-book/search-book.component';
import { CartComponent } from './cart/cart.component';
import { AdminHeadComponent } from './admin-head/admin-head.component';
import { StudentHeadComponent } from './student-head/student-head.component';
import { ProfileComponent } from './profile/profile.component';
import { RequestsComponent } from './requests/requests.component';
import { AddBooksComponent } from './add-books/add-books.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { StudentHomeComponent } from './student-home/student-home.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { CardComponent } from './card/card.component';
import { SearchPipe } from './search.pipe';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PenaltyComponent,
    BooksComponent,
    ShowBooksComponent,
    UpdateBookComponent,
    SearchBookComponent,
    CartComponent,
    AdminHeadComponent,
    StudentHeadComponent,
    ProfileComponent,
    RequestsComponent,
    AddBooksComponent,
    AdminHomeComponent,
    StudentHomeComponent,
    AddStudentComponent,
    CardComponent,
    SearchPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
