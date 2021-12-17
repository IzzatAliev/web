import { Component, OnInit } from '@angular/core';
import {ManagementResponseDto} from "../../../../model/management-response-dto";
import {appConst} from "../../../../app.const";
import {ActivatedRoute, Router} from "@angular/router";
import {ManagementApiService} from "../../../../service/management-api.service";

@Component({
  selector: 'app-management-details',
  templateUrl: './management-details.component.html',
  styleUrls: ['./management-details.component.scss']
})
export class ManagementDetailsComponent implements OnInit {

  private _id: number | undefined;
  management: ManagementResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(private _route: ActivatedRoute, private _router: Router, private _managementApiService: ManagementApiService) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._managementApiService.loadById(this._id).subscribe(management => {
      this.management = management;
    });
  }

  showAllCourses() {
    this._router.navigateByUrl('courses?managementId=' + this.management?.id);
  }

  addNewCourses() {
    this._router.navigateByUrl('courses/new?managementId=' + this.management?.id);
  }

}
