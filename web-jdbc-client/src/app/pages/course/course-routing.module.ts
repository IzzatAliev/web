import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CourseNewComponent} from "./components/course-new/course-new.component";
import {CourseDetailsComponent} from "./components/course-details/course-details.component";
import {CourseItemsComponent} from "./components/course-items/course-items.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: CourseItemsComponent
  },
  {
    path: 'new',
    component: CourseNewComponent
  },
  {
    path: ':id',
    component: CourseDetailsComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class CourseRoutingModule { }
