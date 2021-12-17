import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagementRoutingModule } from './management-routing.module';
import {ManagementDetailsComponent} from "./components/management-details/management-details.component";
import {ManagementItemsComponent} from "./components/management-items/management-items.component";
import {ManagementNewComponent} from "./components/management-new/management-new.component";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ManagementDetailsComponent,
    ManagementItemsComponent,
    ManagementNewComponent
  ],
    imports: [
        CommonModule,
        ManagementRoutingModule,
        ReactiveFormsModule
    ]
})
export class ManagementModule { }
