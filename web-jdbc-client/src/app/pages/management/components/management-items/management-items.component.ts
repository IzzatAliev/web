import { Component, OnInit } from '@angular/core';
import {ManagementResponseDto} from "../../../../model/management-response-dto";
import {ManagementApiService} from "../../../../service/management-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-management-items',
  templateUrl: './management-items.component.html',
  styleUrls: ['./management-items.component.scss']
})
export class ManagementItemsComponent implements OnInit {

  managements: ManagementResponseDto[] | undefined;

  constructor(private _managementApiService: ManagementApiService,
              private _router: Router,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this._loadAll();
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._managementApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addManagement(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  private _loadAll(): void {
    this._managementApiService.loadAll().subscribe(managements => {
      this.managements = managements;
    });
  }

}
