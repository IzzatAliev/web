import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CourseRoutingModule } from './course-routing.module';
import {CourseItemsComponent} from "./components/course-items/course-items.component";
import {CourseDetailsComponent} from "./components/course-details/course-details.component";
import {CourseNewComponent} from "./components/course-new/course-new.component";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    CourseItemsComponent,
    CourseDetailsComponent,
    CourseNewComponent
  ],
  imports: [
    CommonModule,
    CourseRoutingModule,
    ReactiveFormsModule
  ]
})
export class CourseModule { }
