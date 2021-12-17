import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import {StudentDetailsComponent} from "./components/student-details/student-details.component";
import {StudentItemsComponent} from "./components/student-items/student-items.component";
import {StudentNewComponent} from "./components/student-new/student-new.component";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    StudentDetailsComponent,
    StudentItemsComponent,
    StudentNewComponent
  ],
    imports: [
        CommonModule,
        StudentRoutingModule,
        ReactiveFormsModule
    ]
})
export class StudentModule { }
