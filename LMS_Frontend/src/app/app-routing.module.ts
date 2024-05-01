import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AdminHeadComponent } from './admin-head/admin-head.component';
import { StudentHeadComponent } from './student-head/student-head.component';
import { ProfileComponent } from './profile/profile.component';
import { BooksComponent } from './books/books.component';
import { ShowBooksComponent } from './show-books/show-books.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { PenaltyComponent } from './penalty/penalty.component';
import { RequestsComponent } from './requests/requests.component';
import { AddBooksComponent } from './add-books/add-books.component';
import { CartComponent } from './cart/cart.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { StudentHomeComponent } from './student-home/student-home.component';
import { CardComponent } from './card/card.component';


const routes: Routes = [
  {path:'home',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'adminhead',component:AdminHeadComponent},
  {path:'adminhome',component:AdminHomeComponent},
  {path:'studenthead',component:StudentHeadComponent},
  {path:'studenthome',component:StudentHomeComponent},
  {path:'addbooks',component:AddBooksComponent},
  {path:'addstudent',component:AddStudentComponent},
  {path:'penalty',component:PenaltyComponent},
  {path:'books',component:BooksComponent},
  {path:'updatebook',component:UpdateBookComponent},
  {path:'requests',component:RequestsComponent},
  {path:'profile',component:ProfileComponent},
  {path:'showbooks',component:ShowBooksComponent},
  {path:'cart',component:CartComponent},
  {path:'card',component:CardComponent},
  {path:'**',redirectTo:'/home',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
