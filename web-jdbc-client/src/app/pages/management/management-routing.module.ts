import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ManagementItemsComponent} from "./components/management-items/management-items.component";
import {ManagementDetailsComponent} from "./components/management-details/management-details.component";
import {ManagementNewComponent} from "./components/management-new/management-new.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: ManagementItemsComponent
  },
  {
    path: 'new',
    component: ManagementNewComponent
  },
  {
    path: ':id',
    component: ManagementDetailsComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ManagementRoutingModule { }
