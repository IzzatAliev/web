import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {StudentItemsComponent} from "./components/student-items/student-items.component";
import {StudentNewComponent} from "./components/student-new/student-new.component";
import {StudentDetailsComponent} from "./components/student-details/student-details.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: StudentItemsComponent
  },
  {
    path: 'new',
    component: StudentNewComponent
  },
  {
    path: ':id',
    component: StudentDetailsComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
